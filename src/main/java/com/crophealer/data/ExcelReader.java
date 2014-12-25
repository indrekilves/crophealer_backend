package com.crophealer.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReader implements SpreadSheetReader
{

    /** Accepts files in Excel **/

    private String fileName;

    // private FileInputStream stream;
    private InputStream stream;

    private HSSFWorkbook workBook;

    private Integer activeSheetNum;

    @Override
    public Integer getActiveSheetNum()
    {
        return activeSheetNum;
    }

    @Override
    public void setActiveSheetNum( Integer activeSheetNum )
    {
        this.activeSheetNum = activeSheetNum;
    }

    @Override
    public void setFileName( String fileName )
    {
        this.fileName = fileName;
    }

    @Override
    public Integer getLastColumn()
    {
        return 21;
    }

    @Override
    public Integer getLastRow()
    {
        return 53;
    }

    @Override
    public Integer getDiseaseLatinColumn()
    {
        return 7;
    }

    @Override
    public Integer getPhaseOffset()
    {
        return 1;
    }

    @Override
    public Integer getPlantPartOffset()
    {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public Integer getSymptomOffset()
    {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public Integer getYieldLossOffset()
    {
        // TODO Auto-generated method stub
        return 4;
    }

    @Override
    public Integer getActivIngredientOffset()
    {
        // TODO Auto-generated method stub
        return 5;
    }

    @Override
    public Integer getHeaderRow()
    {
        // TODO Auto-generated method stub
        return 4;
    }

    @Override
    public Integer getCountryRow()
    {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public Integer getLanguageRow()
    {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public void loadWorkBook()
    {
        try
        {
            this.stream = new FileInputStream( new File( this.fileName ) );
            // this.stream =
            // DataLoader.class.getResourceAsStream(this.fileName);

            // this.workBook = new HSSFWorkbook(this.stream);
            this.workBook = new HSSFWorkbook( this.stream );
        }
        catch ( FileNotFoundException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public String getCellContent( Integer rowNum, Integer column )
    {
        String cellStr = "";
        try
        {
            HSSFSheet sheet = this.workBook.getSheetAt( this.getActiveSheetNum() );
            Row row = sheet.getRow( rowNum );
            Cell cell = row.getCell( column );
            if ( cell.getCellType() == Cell.CELL_TYPE_NUMERIC )
            {
                Double tmp = cell.getNumericCellValue();
                cellStr = tmp.toString();
            }
            else
            {
                cellStr = cell.getStringCellValue();
            }

        }
        catch ( Exception e )
        {
        }
        return cellStr;
    }

    @Override
    public List < String > getRowAsArray( Integer rowNum )
    {
        List < String > rowArr = new ArrayList < String >();
        HSSFSheet sheet = this.workBook.getSheetAt( this.getActiveSheetNum() );
        Row row = sheet.getRow( rowNum );

        for ( int i = 0; i < this.getLastColumn(); i++ )
        {
            Cell c = row.getCell( i, Row.CREATE_NULL_AS_BLANK );
            if ( c.getCellType() == Cell.CELL_TYPE_NUMERIC )
            {
                Double tmp = round( c.getNumericCellValue(), 1 );
                rowArr.add( tmp.toString() );
                // c.setCellType(1);
                // rowArr.add(c.getStringCellValue());
            }
            else
            {
                rowArr.add( c.getStringCellValue() );
            }
        }

        return rowArr;
    }

    public static double round( double value, int places )
    {
        if ( places < 0 )
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal( value );
        bd = bd.setScale( places, RoundingMode.HALF_UP );
        return bd.doubleValue();
    }

    @Override
    public List < String > getColumnAsArray( Integer columnNum )
    {
        List < String > colArr = new ArrayList < String >();
        HSSFSheet sheet = this.workBook.getSheetAt( this.getActiveSheetNum() );

        for ( int i = 0; i <= sheet.getLastRowNum(); i++ )
        {
            Row row = sheet.getRow( i );
            if ( row != null )
            {
                Cell c = row.getCell( columnNum, Row.CREATE_NULL_AS_BLANK );
                colArr.add( c.getStringCellValue() );
            }
            else
            {
                colArr.add( "" );
            }
        }

        return colArr;
    }

    @Override
    public List < String > getColumnAsArray( Integer columnNum, Integer fromRow )
    {
        List < String > colArr = new ArrayList < String >();
        List < String > fullArr = this.getColumnAsArray( columnNum );

        for ( int i = fromRow; i < fullArr.size(); i++ )
        {
            colArr.add( fullArr.get( i ) );
        }

        return colArr;
    }

    @Override
    public List < String > getColumnAsArray( Integer columnNum, Integer fromRow, Integer toRow )
    {
        List < String > colArr = new ArrayList < String >();
        List < String > fullArr = this.getColumnAsArray( columnNum );

        for ( int i = fromRow; ( i < fullArr.size() && i <= toRow ); i++ )
        {
            colArr.add( fullArr.get( i ) );
        }

        return colArr;
    }

    @Override
    public Integer getNextFilledRowNum( Integer column, Integer fromRow )
    {
        List < String > allRows = this.getColumnAsArray( column, fromRow + 1 );

        if ( allRows.size() <= 0 )
            return null;

        for ( int i = 0; i < allRows.size(); i++ )
        {
            Integer realRowNum = i + fromRow + 1;
            String cellContent = allRows.get( i );

            if ( !cellContent.isEmpty() )
            {
                return realRowNum;
            }
        }
        return null;
    }

    @Override
    public Integer getNextFilledColumnNum( Integer rowNum, Integer fromColumn )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List < String > getRowAsArray( Integer rowNum, Integer colFrom )
    {
        List < String > fullRow = this.getRowAsArray( rowNum );
        List < String > resRow = new ArrayList < String >();

        for ( int i = colFrom; i < fullRow.size(); i++ )
        {
            resRow.add( fullRow.get( i ) );
        }

        return resRow;
    }

}
