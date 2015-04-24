package excelrepo.web;

/**
 * Created by kgrouzhevsky on 4/18/2015.
 */

import excelrepo.domain.PriceList;
import excelrepo.service.FileUpLoad;
import excelrepo.service.Parser;
import excelrepo.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class PriceListController {


    // Init Service layer
    //
    @Autowired
    private PriceService pService;

    // Path to loaded data
    //
    public String strServerFilePath;

    // GET  home view
    //
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model uiModel) {

        List<PriceList> prices = pService.findAll();
        uiModel.addAttribute("prices", prices);
        return "index";

      }
      // GET  home view with path to loaded data
      //
      @RequestMapping(value = "/index", method = RequestMethod.GET)
      public String Pricelists(Model uiModel) {

        List<PriceList> prices = pService.findAll();
        uiModel.addAttribute("prices", prices);
        uiModel.addAttribute("strServerFilePath", strServerFilePath);
        return "index";

      }
       // POST data to server
       //
       @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
       public String fileUpload( Model model,
                                  FileUpLoad file) {

        List<PriceList> prices = pService.findAll();

        model.addAttribute("file", file);
        model.addAttribute("prices", prices);

            try {

                  MultipartFile multipartFile = file.getFile();
                  // Creating the directory to store file
                 String rootPath = System.getProperty("catalina.home");
                 File dir = new File(rootPath + File.separator + "tmpFiles");
                 if (!dir.exists())
                     dir.mkdirs();
                // Create the file on server
                if (!multipartFile.getOriginalFilename().isEmpty()) {

                    strServerFilePath = dir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename();
                    File serverFile = new File(dir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename());

                    FileOutputStream fos = new FileOutputStream(serverFile);
                    fos.write(multipartFile.getBytes());
                    fos.close();

                } else {

                      strServerFilePath = null;
                }

                //model.addAttribute("strServerFilePath", strServerFilePath);

            } catch (Exception e) {
                e.printStackTrace();
            }

           return "redirect:/index";
    }

    // GET  bulk update data in database by data from loaded file
    //
    @RequestMapping(value = "/UpdateFromFile", method = RequestMethod.GET)
    public String UpdateFromFile(Model model) {

       PriceList pl = new PriceList();
       List<PriceList> prices = pService.findAll();
       List<PriceList> pricesFromFile = new ArrayList<PriceList>();

       if (strServerFilePath != null) {

           pricesFromFile = Parser.Excel2Array(strServerFilePath);

           for (Iterator i = pricesFromFile.iterator(); i.hasNext(); ) {

               pl = new PriceList();
               pl = (PriceList)i.next();

               if (pService.findById(pl.getPriceCode())) {

                   pService.updateAll(pl);

               }  else {

                   pService.addPrice(pl);
               }
           }
           strServerFilePath = null;
       }
       model.addAttribute("prices", prices);
       model.addAttribute("strServerFilePath",strServerFilePath);
       return "redirect:/index";
    }

    // Remove position
    //
    @RequestMapping(value = "/delete/{priceCode}" , method = RequestMethod.GET)
    public String deletePosition(@PathVariable("priceCode") Integer priceCode) {

        pService.removePrice(priceCode);
        return "redirect:/index";
    }


}

