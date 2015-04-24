package excelrepo.dao;

import excelrepo.domain.PriceList;

import java.util.List;

/**
 * Created by kgrouzhevsky on 4/18/2015.
 */
// Data access interface
//
public interface PriceDAO {

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
