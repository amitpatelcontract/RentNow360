package com.firebase.samples.logindemo.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arms on 2/16/16.
 */
public class Product implements Serializable {

    private String productId;
    private String productTitle;
    private String productPrice;
    private String productPriceUnit;
    private String productDescription;
    private ArrayList<String> productImagesList;
    private double[] productLocation; // user location
    private String productMajorCategoryId;
    private String productMinorCategoryId;
    private String userIdFromFacebook;

    public void Product(){

    }

    public Product(String productDescription, String productId, ArrayList<String> productImagesList, double[] productLocation, String productMajorCategoryId, String productMinorCategoryId, String productPrice, String productPriceUnit, String productTitle, String userIdFromFacebook, String userIdFromPush) {
        this.productDescription = productDescription;
        this.productId = productId;
        this.productImagesList = productImagesList;
        this.productLocation = productLocation;
        this.productMajorCategoryId = productMajorCategoryId;
        this.productMinorCategoryId = productMinorCategoryId;
        this.productPrice = productPrice;
        this.productPriceUnit = productPriceUnit;
        this.productTitle = productTitle;
        this.userIdFromFacebook = userIdFromFacebook;
        this.userIdFromPush = userIdFromPush;
    }

    public String getUserIdFromPush() {
        return userIdFromPush;
    }

    public void setUserIdFromPush(String userIdFromPush) {
        this.userIdFromPush = userIdFromPush;
    }

    public String getUserIdFromFacebook() {
        return userIdFromFacebook;
    }

    public void setUserIdFromFacebook(String userIdFromFacebook) {
        this.userIdFromFacebook = userIdFromFacebook;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductMinorCategoryId() {
        return productMinorCategoryId;
    }

    public void setProductMinorCategoryId(String productMinorCategoryId) {
        this.productMinorCategoryId = productMinorCategoryId;
    }

    public String getProductMajorCategoryId() {
        return productMajorCategoryId;
    }

    public void setProductMajorCategoryId(String productMajorCategoryId) {
        this.productMajorCategoryId = productMajorCategoryId;
    }

    public double[] getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(double[] productLocation) {
        this.productLocation = productLocation;
    }

    public ArrayList<String> getProductImagesList() {
        return productImagesList;
    }

    public void setProductImagesList(ArrayList<String> productImagesList) {
        this.productImagesList = productImagesList;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    private String userIdFromPush;



}
