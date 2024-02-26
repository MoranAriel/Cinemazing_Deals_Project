package beans;

import java.time.LocalDate;

public class Coupon {

  private int id;
  private int companyID;
  private Category category;
  private String title;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private int amount;
  private double price;
  private String image;
  private static int counter = 1;

  public Coupon(){

  }
  public Coupon(int companyID, Category category, String title,
      String description, LocalDate startDate, LocalDate endDate,
      int amount, double price, String image) {
    this.id = counter;
    this.companyID = companyID;
    this.category = category;
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.amount = amount;
    this.price = price;
    this.image = image;
    counter++;
  }

  public Coupon(int id, int companyID, Category category, String title,
      String description, LocalDate startDate, LocalDate endDate,
      int amount, double price, String image) {
    this.id = id;
    this.companyID = companyID;
    this.category = category;
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.amount = amount;
    this.price = price;
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCompanyID() {
    return companyID;
  }

  public void setCompanyID(int companyID) {
    this.companyID = companyID;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category){
    this.category = category;
  }

  public void setCategory(int id) {
    Category newCategory = Category.DEFAULT;

        switch (id) {
            case 1 -> newCategory = Category.NEW_GEAR;
            case 2 -> newCategory = Category.RENTAL_GEAR;
            case 3 -> newCategory = Category.PRODUCTION;
            case 4 -> newCategory = Category.POST_PRODUCTION;
            case 5 -> newCategory = Category.MOVIE_THEATERS;
            case 6 -> newCategory = Category.STREAMING;
            case 7 -> newCategory = Category.DVD_AND_BLUERAY;
            default -> System.out.println("There was a problem with your input, please try again.");
    }
    this.category = newCategory;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "Coupon{" +
        "id=" + id +
        ", companyID=" + companyID +
        ", category=" + category +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", amount=" + amount +
        ", price=" + price +
        ", image='" + image + '\'' +
        '}';
  }
}
