package com.crophealer.rest.v1.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.DiagnosedProblemPicture;
import com.crophealer.domain.Languages;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.model.upload.FileUploadForm;
import com.crophealer.rest.v1.DiagnosedProblemResource;
import com.crophealer.rest.v1.DiagnosedProblemResourceAssembler;
import com.crophealer.rest.v1.controller.est.BadRequestException;
import com.crophealer.rest.v1.controller.est.ResourceNotFoundException;
import com.crophealer.security.Users;

@Service
public class DiagnosedProblemRestService extends GenericRestService
{

    private FileUploadForm uploadForm;

    private Users user;

    private String pictureDirectory;

    private DiagnosedProblem diagnosedProblem;

    public ResponseEntity < DiagnosedProblemResource > getDiagnosedProblemByLanguage( Long id, Languages language )
    {
        System.out.println( "getDiagnosedProblemByLanguage - try to get for id:" + id + " lang:" + language );

        if ( id == null || language == null )
        {
            throw new BadRequestException( "ID or Language missing" );
        }

        DiagnosedProblem dp = DiagnosedProblem.findDiagnosedProblem( id );
        if ( dp == null )
        {
            throw new ResourceNotFoundException( "DiagnosedProblem isn't found for ID: " + id );
        }

        DiagnosedProblemResourceAssembler dprAsm = new DiagnosedProblemResourceAssembler();
        DiagnosedProblemResource dpr = dprAsm.toResource( dp, language );

        return new ResponseEntity < DiagnosedProblemResource >( dpr, HttpStatus.OK );

    }

    public ResponseEntity < Void > saveDiagnosedProblemByLanguage( FileUploadForm uf, Languages language )
    {
        System.out.println( "saveDiagnosedProblemByLanguage - start" );
        uploadForm = uf;

        if ( uploadForm == null )
        {
            throw new ResourceNotFoundException( "FileUploadForm is null" );
        }

        PlantPartPhaseProblem pppProblem = getPlantPartPhaseProblem();
        if ( pppProblem == null )
        {
            throw new ResourceNotFoundException( "PlantPartPhaseProblem isn't found for ID: "
                    + uploadForm.getPppProblemId() );
        }

        // Get user
        user = getUserFromAuth();
        if ( user == null )
        {
            throw new BadRequestException( "User can't be authorized." );
        }

        DiagnosedProblem dp = new DiagnosedProblem();
        dp.setUsr( user );
        dp.setPlantPartPhaseProblem( pppProblem );
        dp.setLocation( uploadForm.getLocation() );
        dp.setSymptomIDsCSV( uploadForm.getSymptoms() );
        dp.setCreatedTimestamp( Calendar.getInstance().getTime() );
        dp.persist();

        diagnosedProblem = dp;
        storePictures();

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment( dp.getId().toString() ).build()
                .toUri();
        headers.setLocation( location );
        return new ResponseEntity < Void >( headers, HttpStatus.CREATED );
    }

    private PlantPartPhaseProblem getPlantPartPhaseProblem()
    {
        String pppProblemIdStr = uploadForm.getPppProblemId();
        if ( pppProblemIdStr == null || pppProblemIdStr.isEmpty() )
        {
            return null;
        }

        Long pppProblemId = Long.parseLong( pppProblemIdStr );
        if ( pppProblemId == null )
        {
            return null;
        }

        return PlantPartPhaseProblem.findPlantPartPhaseProblem( pppProblemId );
    }

    private void storePictures()
    {
        pictureDirectory = FileManagementRestService.getUserPictureDirectoryByUsername( user.getId().toString() );
        if ( pictureDirectory == null )
        {
            System.out.println( "ERROR: Failed to get pictureDirectory." );
            return;
        }

        List < MultipartFile > files = uploadForm.getFiles();

        if ( null != files && files.size() > 0 )
        {
            for ( MultipartFile multipartFile : files )
            {
                storePicture( multipartFile );
            }
        }
    }

    private void storePicture( MultipartFile multipartFile )
    {
        String origName = multipartFile.getOriginalFilename();
        if ( "".equalsIgnoreCase( origName ) )
        {
            return;
        }

        DiagnosedProblemPicture dpp = new DiagnosedProblemPicture();
        dpp.setDiagnosedProblem( diagnosedProblem );
        dpp.persist();
        dpp.flush();

        String ext = FilenameUtils.getExtension( origName );
        String filePath = pictureDirectory + dpp.getId() + "." + ext;

        try
        {
            multipartFile.transferTo( new File( filePath ) );

            dpp.setName( origName );
            dpp.setPictureUrl( filePath );
            dpp.merge();

        }
        catch ( IllegalStateException e )
        {
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

}
