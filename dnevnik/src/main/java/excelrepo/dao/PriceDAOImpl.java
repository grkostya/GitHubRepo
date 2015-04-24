package excelrepo.dao;

/**
 * Created by kgrouzhevsky on 4/18/2015.
 */

import excelrepo.domain.PriceList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// Ask spring to create the bean
//
@Repository
public  class PriceDAOImpl implements PriceDAO {

    // Init session
    //
    @Autowired
    private SessionFactory sessionFactory;

    // add position
    //
    public void addPrice(PriceList price) {
        sessionFactory.getCurrentSession().save(price);
    }

    // Get all ...
    //
    public List<PriceList> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from PriceList order by priceCode").list();
    }

    // Get sign about existence position with certain priceCode
    //
    public boolean findById(Integer Id) {

        Boolean existFlag=false;
        Session session = sessionFactory.getCurrentSession();

        try {
             Criteria criteria = session.createCriteria(PriceList.class);
             criteria.add(Restrictions.eq("priceCode", Id));
             PriceList price = (PriceList) criteria.uniqueResult();
             if (price!=null) {
                 existFlag = true;
             }
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return existFlag;
    }

    // like bulk update ;-)
    //
    public  void  updateAll(PriceList price){
        sessionFactory.getCurrentSession().update(price);
      }

    public void removePrice(Integer id) {
        PriceList price = (PriceList) sessionFactory.getCurrentSession().load(PriceList.class, id);
        if (null != price) {
            sessionFactory.getCurrentSession().delete(price);
        }

    }
}