package excelrepo.service;

import excelrepo.dao.PriceDAO;
import excelrepo.domain.PriceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

        // Init DAO layer
        //
        @Autowired
        private PriceDAO priceListDAO;

        // Implements methods, about goals see comments in PriceDAO interface
        //
        @Transactional
        public void addPrice(PriceList price) {

            priceListDAO.addPrice(price);

        }

        @Transactional
        public List<PriceList> findAll() {

            return priceListDAO.findAll();
        }

       @Transactional
       public boolean findById(Integer Id) {

        return priceListDAO.findById(Id);

        }

        @Transactional
        public void updateAll(PriceList price) {

         priceListDAO.updateAll(price);

        }

         @Transactional
         public void removePrice(Integer id) {
            priceListDAO.removePrice(id);
        }
}