package com.crophealer.web.upload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.crophealer.model.upload.FileUploadForm;
import com.crophealer.rest.v1.service.FileManagementRestService;

@RequestMapping("/upload")
@Controller
public class FileUploadController
{

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String displayForm()
    {
        return "file_upload_form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save( @ModelAttribute("uploadForm")
    FileUploadForm uploadForm, Model map )
    {

        List < MultipartFile > files = uploadForm.getFiles();
        String userId = "loll";
        String saveDirectory = FileManagementRestService.getUserPictureDirectoryByUsername( userId );

        List < String > fileNames = new ArrayList < String >();

        if ( null != files && files.size() > 0 )
        {
            for ( MultipartFile multipartFile : files )
            {

                String fileName = multipartFile.getOriginalFilename();
                if ( !"".equalsIgnoreCase( fileName ) )
                {

                    try
                    {
                        multipartFile.transferTo( new File( saveDirectory + fileName ) );
                    }
                    catch ( IllegalStateException e )
                    {
                        e.printStackTrace();
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                    fileNames.add( fileName );
                }

            }
        }

        map.addAttribute( "files", fileNames );
        return "file_upload_success";
    }
}
