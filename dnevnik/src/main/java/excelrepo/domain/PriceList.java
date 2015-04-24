package excelrepo.domain;

/**
 * Created by kgrouzhevsky on 4/18/2015.
 */

import javax.persistence.*;
import java.util.Date;



// Create POJO
//
@Entity
@Table(name = "PRICELIST")
public class PriceList {


    @Id
    @Column(name = "PRICECODE")
    private Integer priceCode;

    @Column(name = "NAME")
    private String name ;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "PRICE_DATE")
    private Date pricedate;

    public Integer getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(Integer priceCode) {
        this.priceCode = priceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPricedate() {
        return pricedate;
    }

    public void setPricedate(Date price_date) {
        this.pricedate = price_date;
    }
}