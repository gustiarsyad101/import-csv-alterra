package com.importcsv.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "upload_csv_file")
@Data
@NoArgsConstructor
public class UploadFile {

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "product_name")
    private String productName;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private double price;

    @Column(name = "images")
    private String images;

//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public String getSku() {
//        return sku;
//    }
//
//    public void setSku(String sku) {
//        this.sku = sku;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public String getImages() {
//        return images;
//    }
//
//    public void setImages(String images) {
//        this.images = images;
//    }

}
