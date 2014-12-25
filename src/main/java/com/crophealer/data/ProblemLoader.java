package com.crophealer.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Country;
import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantPart;
import com.crophealer.domain.PlantPartPhase;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.PlantPartTranslation;
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemPicture;
import com.crophealer.domain.ProblemTranslation;
import com.crophealer.domain.Symptom;
import com.crophealer.domain.SymptomPicture;
import com.crophealer.domain.SymptomTranslation;

public class ProblemLoader extends GenericLoader
{
    protected Integer activeSheetNum = 0;

    private final Integer latinNameCol = 9;

    private final Integer phaseCol = latinNameCol + 1;

    private final Integer indexCol = latinNameCol + 2;

    private final Integer sympIndexCol = latinNameCol + 5;

    private final Integer firstCountryCol = latinNameCol + 3;

    private LinkedHashMap < String, Integer > countryCols;

    // private final Integer problemsStartRow = 162;
    private final Integer problemsStartRow = 5;

    private final Integer countryRow = 2;

    private final Integer headerRow = 4;

    // offsets from Country column
    private final Integer plantPartOS = 1;

    private final Integer warningOS = 4;

    private final Integer symptomOS = 3;

    public ProblemLoader( SpreadSheetReader ssReader )
    {
        super( ssReader );
        this.setActiveSheetNum( this.activeSheetNum );
        this.loadCountryStartCols();
    }

    private void loadCountryStartCols()
    {

        this.countryCols = new LinkedHashMap < String, Integer >();
        List < String > countries = ssReader.getRowAsArray( countryRow );
        for ( int i = 0; i < countries.size(); i++ )
        {
            String country = countries.get( i );
            if ( !country.isEmpty() )
            {
                if ( Country.getSingleCountryByName( country ) != null )
                {
                    this.countryCols.put( country, i );
                }
            }
        }
    }

    public void loadProblems()
    {
        List < String > latinNames = this.ssReader.getColumnAsArray( this.latinNameCol, this.problemsStartRow );

        for ( int i = 0; i < latinNames.size(); i++ )
        {
            Integer realRowNum = i + this.problemsStartRow;
            String pLatinName = latinNames.get( i ).trim();

            if ( !pLatinName.isEmpty() )
            {
                if ( pLatinName.equals( "Puccinia hordei" ) )
                {
                    // int ih=0;
                    // ih++;
                }
                System.out.println( "Loading symptoms for " + realRowNum + " - " + pLatinName );
                this.processProblemSector( pLatinName, realRowNum );
            }
        }
    }

    private void processProblemSector( String problemLatin, int pRow )
    {
        if ( !this.canLoadProblemSector( problemLatin, pRow ) )
            return;

        Problem problem = new Problem();

        if ( !this.problemExists( problemLatin ) )
        {
            problem = this.addProblemByLatin( problemLatin );
            this.addProblemPicture( problem, pRow );
            this.loadProblemTranslations( problem, pRow );
        }
        else
        {
            problem = this.getProblemByLatin( problemLatin );
        }

        List < Plant > plants = this.getPlantsForProblem( problem, pRow );
        List < GrowthPhase > phases = this.getPhasesForProblem( plants, problem, pRow );
        List < PlantPart > plantParts = this.loadAndGetPlantPartsForProblem( pRow );
        List < Symptom > symptoms = this.loadAndGetSymptomsForProblem( problem, pRow );

        // hurray to relations!

        for ( Plant plant : plants )
        {
            for ( GrowthPhase phase : phases )
            {
                for ( PlantPart pPart : plantParts )
                {

                    System.out.println( "Linking " + plant.getComment() + " - " + phase.getComment() + " - "
                            + pPart.getComment() );

                    PlantPartPhase ppp;
                    ppp = this.getPlantPlantPartPhase( plant, phase, pPart );
                    if ( ppp == null )
                    {
                        ppp = new PlantPartPhase();
                        ppp.setPlantPart( pPart );
                        ppp.setGrowthPhase( phase );
                        ppp.setPlant( plant );
                        ppp.setComment( plant.getComment() + " - " + phase.getComment() + " - " + pPart.getComment() );
                        ppp.persist();
                    }

                    PlantPartPhaseProblem pppp = new PlantPartPhaseProblem();
                    pppp.setProblem( problem );
                    pppp.setComment( problem.getLatinName() + " - " + ppp.getComment() );
                    pppp.persist();

                    for ( Symptom symptom : symptoms )
                    {
                        PlantPartPhaseSymptom ppps = new PlantPartPhaseSymptom();
                        ppps.setComment( pppp.getComment() + " - " + symptom.getComment() );
                        ppps.setPlantPartPhase( ppp );
                        ppps.setSymptom( symptom );
                        ppps.setProblem( pppp );
                        ppps.persist();
                    }

                }
            }
        }
    }

    private boolean canLoadProblemSector( String problemLatin, int pRow )
    {
        if ( problemLatin.isEmpty() )
        {
            System.out.println( "ERROR: Cannot load problem sector, latin name is empty." );
            return false;
        }

        List < String > sectionStartRow = ssReader.getRowAsArray( pRow );

        if ( sectionStartRow.get( this.phaseCol ).isEmpty() )
        {
            System.out.println( "ERROR: Cannot load problem sector, phase cell is empty." );
            return false;
        }

        if ( sectionStartRow.get( this.firstCountryCol + this.plantPartOS ).isEmpty() )
        {
            System.out.println( "ERROR: Cannot load problem sector, plantpart cell is empty." );
            return false;
        }

        return true;
    }

    private void addProblemPicture( Problem problem, int pRow )
    {
        ProblemPicture probPic = new ProblemPicture();
        String probPicStr = ssReader.getCellContent( pRow, indexCol );

        if ( probPicStr.isEmpty() )
            return;

        probPic.setName( probPicStr );
        probPic.setPictureUrl( "resources/images/problems/" + probPicStr );
        probPic.setProblem( problem );
        probPic.persist();
    }

    private void addSymptomPicture( Symptom s, int pRow )
    {
        SymptomPicture pic = new SymptomPicture();
        String sympPicStr = ssReader.getCellContent( pRow, sympIndexCol );

        if ( sympPicStr.isEmpty() )
            return;

        pic.setName( sympPicStr );
        pic.setPictureUrl( "resources/images/symptoms/" + sympPicStr );
        pic.setSymptom( s );
        pic.persist();

    }

    private List < Symptom > loadAndGetSymptomsForProblem( Problem problem, int pRow )
    {
        List < Symptom > symptomList = new ArrayList < Symptom >();
        Integer nextProblemRow = this.getNextProblemRowNum( pRow );

        if ( nextProblemRow == null )
        {
            nextProblemRow = getLastFilledRowBySymptoms( pRow );
            if ( nextProblemRow == null )
                return symptomList;
        }

        for ( Map.Entry < String, Integer > countryCol : countryCols.entrySet() )
        {
            Integer countryColNum = countryCol.getValue();
            List < String > symptomStrs = ssReader.getColumnAsArray( countryColNum + symptomOS, pRow,
                    nextProblemRow - 1 );

            // loop symptoms for problem and add translations
            for ( int i = 0; i < symptomStrs.size(); i++ )
            {
                int realRow = i + pRow;
                if ( !symptomStrs.get( i ).isEmpty() )
                {
                    Symptom symp = this.addSymptomByRowWithTrans( realRow, symptomStrs.get( i ), countryColNum );
                    symptomList.add( symp );
                }
                else
                {
                    System.out.println( "Symptom was empty on row " + realRow );
                }

            }

            // only loop once - to load only estonian?
            break;
        }

        return symptomList;
    }

    private Symptom addSymptomByRowWithTrans( Integer sympRow, String symptomStr, Integer baseCountryCol )
    {
        // add new base rec

        Symptom symptom = Symptom.getSingleSymptomByComment( symptomStr );
        if ( symptom == null )
        {
            symptom = new Symptom();
            symptom.setComment( symptomStr );
        }
        else
        {
            return symptom;
        }

        Country country = this.getCountryByCountryCol( baseCountryCol );
        if ( country != null )
            symptom.setCountry( country );
        symptom.persist();
        this.addSymptomPicture( symptom, sympRow );

        for ( Map.Entry < String, Integer > countryCol : countryCols.entrySet() )
        {
            Integer countryColNum = countryCol.getValue();
            // if(countryColNum.equals(baseCountryCol))
            // continue;

            // add symptom translations
            String symptomTransStr = ssReader.getCellContent( sympRow, countryColNum + symptomOS );

            SymptomTranslation sympTrans = new SymptomTranslation();
            sympTrans.setName( symptomTransStr );
            sympTrans.setDescription( symptomTransStr );
            sympTrans.setSymptom( symptom );

            Languages lang = this.getLanguageByCountryCol( countryColNum );
            if ( lang != null )
                sympTrans.setLang( lang );

            sympTrans.persist();
        }
        return symptom;
    }

    private PlantPartPhase getPlantPlantPartPhase( Plant plant, GrowthPhase phase, PlantPart pPart )
    {
        try
        {
            TypedQuery < PlantPartPhase > pppQ = PlantPartPhase.findPlantPartPhasesByPlantAndGrowthPhaseAndPlantPart(
                    plant, phase, pPart );
            if ( pppQ.getResultList().size() > 0 )
                return pppQ.getSingleResult();
            else
                return null;
        }
        catch ( Exception e )
        {
            return null;
        }
    }

    private List < Plant > getPlantsForProblem( Problem problem, int pRow )
    {
        List < Plant > plants = new ArrayList < Plant >();
        Plant plant;
        String plantStr;

        for ( int i = this.latinNameCol; i >= 0; i-- )
        {
            String plantCell = ssReader.getCellContent( pRow, i );
            if ( plantCell.equalsIgnoreCase( "yes" ) )
            {
                plantStr = ssReader.getCellContent( this.headerRow, i );
                plant = Plant.getSinglePlantByName( plantStr );

                if ( plant != null )
                    plants.add( plant );
            }
        }
        return plants;
    }

    private boolean problemExists( String problemLatin )
    {
        try
        {
            TypedQuery < Problem > problemQ = Problem.findProblemsByLatinNameEquals( problemLatin );
            if ( problemQ.getResultList().size() > 0 )
                return true;
            else
                return false;
        }
        catch ( Exception e )
        {
            return false;
        }
    }

    private Problem getProblemByLatin( String problemLatin )
    {
        Problem problem = null;
        try
        {
            TypedQuery < Problem > problemQ = Problem.findProblemsByLatinNameEquals( problemLatin );
            if ( problemQ.getResultList().size() > 0 )
            {
                problem = problemQ.getSingleResult();
            }
        }
        catch ( Exception e )
        {
        }
        return problem;
    }

    private Problem addProblemByLatin( String problemLatin )
    {
        Problem p = new Problem();
        p.setLatinName( problemLatin );
        p.setCountry( Country.getSingleCountryByName( "Estonia" ) );
        p.persist();
        return p;
    }

    private void loadProblemTranslations( Problem problem, Integer realRowNum )
    {
        for ( Map.Entry < String, Integer > countryCol : countryCols.entrySet() )
        {
            Integer countryColNum = countryCol.getValue();
            String problemTrans = ssReader.getCellContent( realRowNum, countryColNum );
            if ( !problemTrans.isEmpty() )
            {
                ProblemTranslation pTrans = new ProblemTranslation();
                pTrans.setProblem( problem );
                pTrans.setName( problemTrans );
                pTrans.setDescription( problemTrans );

                Languages language = this.getLanguageByCountryCol( countryColNum );
                if ( language != null )
                {
                    pTrans.setLang( language );
                }
                String warning = ssReader.getCellContent( realRowNum, countryColNum + warningOS );
                pTrans.setWarning( warning );
                pTrans.persist();
            }
        }
    }

    private Integer getNextProblemRowNum( Integer curProblemRow )
    {
        List < String > problemRows = this.ssReader.getColumnAsArray( this.latinNameCol, curProblemRow + 1 );

        if ( problemRows.size() <= 0 )
        {
            return null;
        }
        for ( int i = 0; i < problemRows.size(); i++ )
        {
            Integer realRowNum = i + curProblemRow + 1;
            String pLatinName = problemRows.get( i );

            if ( !pLatinName.isEmpty() )
            {
                return realRowNum;
            }
        }
        return null;
    }

    private Integer getLastFilledRowBySymptoms( Integer curProblemRow )
    {
        // return last filled comment row + 1
        List < String > symptomRows = this.ssReader.getColumnAsArray( this.firstCountryCol + this.symptomOS,
                curProblemRow + 1 );
        for ( int i = 0; i < symptomRows.size(); i++ )
        {
            Integer realRowNum = i + curProblemRow + 1;
            String symptomName = symptomRows.get( i );

            if ( symptomName.isEmpty() )
            {
                return realRowNum;
            }
        }
        return null;
    }

    private List < GrowthPhase > getPhasesForProblem( List < Plant > plants, Problem problem, int row )
    {
        List < GrowthPhase > phaseList = new ArrayList < GrowthPhase >();
        String phaseCell = ssReader.getCellContent( row, phaseCol );
        String[] phasePcs = phaseCell.split( "," );

        for ( String phaseStr : phasePcs )
        {
            if ( phaseStr.isEmpty() )
                continue;
            try
            {
                String phaseF;

                if ( this.areOSRPlants( plants ) )
                    phaseF = "FR" + phaseStr.trim();
                else
                    phaseF = "F" + phaseStr.trim();

                GrowthPhase phase = GrowthPhase.findGrowthPhasesByCommentEquals( phaseF ).getSingleResult();
                phaseList.add( phase );
            }
            catch ( Exception e )
            {
            }
        }
        return phaseList;
    }

    private boolean areOSRPlants( List < Plant > plants )
    {
        if ( plants.size() > 0 )
        {
            Plant plant = plants.get( 0 );
            if ( plant.isOSR() )
                return true;
            else
                return false;
        }
        return false;
    }

    private List < PlantPart > loadAndGetPlantPartsForProblem( int row )
    {
        List < PlantPart > ppList = new ArrayList < PlantPart >();

        Integer firstPPColumn = this.getFirstTypeColumn( plantPartOS );

        if ( firstPPColumn == null )
            return ppList;

        String basePlantPartStr = ssReader.getCellContent( row, firstPPColumn );
        PlantPart existingPP = PlantPart.getSinglePlantPartByName( basePlantPartStr );

        if ( existingPP != null )
        {
            ppList.add( existingPP );
            return ppList;
        }

        // firstone will be root plant
        PlantPart pp = new PlantPart();

        for ( Map.Entry < String, Integer > countryCol : countryCols.entrySet() )
        {
            Integer countryColNum = countryCol.getValue();
            String plantPartStr = ssReader.getCellContent( row, countryColNum + plantPartOS );

            if ( plantPartStr == "" )
                continue;

            if ( pp.getComment() == null )
            {
                pp.setComment( plantPartStr );
                pp.persist();
                ppList.add( pp );
            }

            PlantPartTranslation ppTrans = new PlantPartTranslation();
            ppTrans.setPlantPart( pp );
            ppTrans.setName( plantPartStr );

            Languages language = this.getLanguageByCountryCol( countryColNum );
            if ( language != null )
            {
                ppTrans.setLang( language );
            }
            ppTrans.persist();
        }

        return ppList;
    }

    // private Boolean plantPartExistsByName(String ppName)
    // {
    // if (ppName.isEmpty())
    // return false;
    //
    // PlantPart existingPP = PlantPart.getSinglePlantPartByName(ppName);
    // if (existingPP != null)
    // {
    // return true;
    // }
    // else
    // return false;
    //
    // }

    private Integer getFirstTypeColumn( Integer offSet )
    {
        for ( Map.Entry < String, Integer > countryCol : countryCols.entrySet() )
        {
            Integer countryColNum = countryCol.getValue();
            return countryColNum + offSet;
        }
        return null;
    }

    private Languages getLanguageByCountryCol( Integer countryCol )
    {
        String languageStr = ssReader.getCellContent( this.countryRow + 1, countryCol );
        Languages language = Languages.getSingleLanguageByName( languageStr );
        return language;
    }

    private Country getCountryByCountryCol( Integer countryCol )
    {
        String countryStr = ssReader.getCellContent( this.countryRow, countryCol );
        Country country = Country.getSingleCountryByName( countryStr );
        return country;
    }

}
