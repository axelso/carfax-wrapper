package com.carfax.model;

public class Listing {
  protected String model;
  protected String finalPrice;
  protected String mileage;
  protected String trim;
  protected String url;
  protected String year;
  protected String dealer;

  public Listing(
      String model,
      String finalPrice,
      String mileage,
      String trim,
      String url,
      String year,
      String dealer) {

    this.model = model;
    this.finalPrice = finalPrice;
    this.mileage = mileage;
    this.trim = trim;
    this.url = url;
    this.year = year;
    this.dealer = dealer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(String finalPrice) {
    this.finalPrice = finalPrice;
  }

  public String getMileage() {
    return mileage;
  }

  public void setMileage(String mileage) {
    this.mileage = mileage;
  }

  public String getTrim() {
    return trim;
  }

  public void setTrim(String trim) {
    this.trim = trim;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getDealer() {
    return dealer;
  }

  public void setDealer(String dealer) {
    this.dealer = dealer;
  }

  @Override
  public String toString() {
    return "Listing{dealer="
        + dealer
        + ", finalPrice="
        + finalPrice
        + ", mileage="
        + mileage
        + ", model="
        + model
        + ", trim="
        + trim
        + ", url="
        + url
        + ", year="
        + year
        + "}";
  }
}
