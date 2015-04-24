package excelrepo.service;

/**
 * Created by kgrouzhevsky on 4/20/2015.
 */

import excelrepo.domain.PriceList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Parse in data into collection of PriceLists
// it has to be clear...
//
public class Parser {

    public static List<PriceList> Excel2Array(String filePath)
    {

        List<String> list = new ArrayList<String>();

        PriceList pl = new PriceList();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        List<PriceList> prices = new ArrayList<PriceList>();

        try
        {
            //Excel document to be imported
            String filename = filePath;
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("en", "EN"));
            Workbook w = Workbook.getWorkbook(new File(filename),ws);
            //
            // Gets the sheets from workbook
            //
            for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++)
            {
                Sheet s = w.getSheet(sheet);

                Cell[] row = null;

                // Gets the cells from sheet
                //
                for (int i = 1 ; i < s.getRows() ; i++)
                {
                    row = s.getRow(i);

                    if (row.length > 0)
                    {
                        for (int j = 0; j < row.length; j++)
                        {
                            list.add(row[j].getContents());
                        }
                    }


                   // All values can be empty exclude of priceCode
                   //
                   pl.setPriceCode(Integer.parseInt(list.get(0)));

                   // it's needs for processing excel population magic:
                   //  sometimes excel ignores null and brings not fully populated sheet
                   try{
                       pl.setName(list.get(1));
                   }catch (IndexOutOfBoundsException e) {
                            pl.setName(String.valueOf(""));
                    }
                    //
                    try {
                          pl.setPrice(Double.valueOf(list.get(2)));
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                           pl.setPrice(0.0);
                      }
                    //
                    try {
                        pl.setPricedate(formatter.parse(list.get(3)));
                    } catch (IndexOutOfBoundsException | ParseException e) {
                              pl.setPricedate(null);
                    }
                    prices.add(pl);

                    pl = new PriceList();
                    list = new ArrayList<String>();

                }
            }
        }
        catch (UnsupportedEncodingException e)
        {
            System.err.println(e.toString());
        }
        catch (IOException e)
        {
            System.err.println(e.toString());
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
        }

        return prices;
    }

}