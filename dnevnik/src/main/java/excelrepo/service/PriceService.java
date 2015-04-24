package excelrepo.service;

import excelrepo.domain.PriceList;

import java.util.List;


// next layer after DAO
// It will be used in controller for accessing to select and DML operations
//
public interface PriceService {

    // find out the position exists or not, if it's - back true
    //
    public  boolean findById(Integer id);
    //
    // Add position
    //
    public void addPrice(PriceList price);
    //
    // bulk update positions
    //
    public  void updateAll(PriceList prices);
    //
    // Get all positions
    //
    public  List<PriceList> findAll();
    //
    // Remove certain position
    //
    public void removePrice(Integer id);

 }

