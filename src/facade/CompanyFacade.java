package facade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import beans.Category;
import beans.Company;
import beans.Coupon;
import database.UserLists;

public class CompanyFacade extends ClientFacade {

    private int companyID;

    public CompanyFacade() {
    }

    public CompanyFacade(String email, String password) {
        this.companyID = getIdThroughLogin(email, password);
        System.out.println("ID Number: " + getCompanyID());
        Company thisCompany = getCompanyDetails();
        List<Coupon> companyCoupons = getCompanyCoupons();
        thisCompany.getCoupons().addAll(companyCoupons);


        while (true){
            Scanner s = new Scanner(System.in);
            int input;
            Category category;
            int categoryNum;
            menu();

            input = s.nextInt();
            s.nextLine();

            switch (input){
                case 1:
                    System.out.println("Select Coupon Category: ");
                    System.out.println("1. New Gear");
                    System.out.println("2. Rental Gear");
                    System.out.println("3. Production");
                    System.out.println("4. Post Production");
                    System.out.println("5. Movie Theaters");
                    System.out.println("6. Streaming");
                    System.out.println("7. DVD and Bluray");

                    category = Category.DEAFAULT;
                    categoryNum = s.nextInt();
                    s.nextLine();
                    category.setId(categoryNum);

                    System.out.println("Enter Coupon Title: ");
                    String title = s.nextLine();

                    System.out.println("Enter Coupon Description: ");
                    String description = s.nextLine();

                    System.out.println("Enter Coupon Expiration Date - Year: ");
                    int year = s.nextInt();
                    s.nextLine();
                    System.out.println("Enter Coupon Expiration Date - Month: ");
                    int month = s.nextInt();
                    s.nextLine();
                    System.out.println("Enter Coupon Expiration Date - Day: ");
                    int day = s.nextInt();
                    s.nextLine();
                    LocalDate endDate = LocalDate.of(year,month,day);

                    System.out.println("Enter Coupon Amount: ");
                    int amount = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter Coupon Price: ");
                    double price = s.nextDouble();
                    s.nextLine();

                    System.out.println("Copy Image Link: ");
                    String image = s.nextLine();

                    Coupon coupon = new Coupon(companyID, category, title, description, LocalDate.now(), endDate, amount, price, image);
                    addCoupon(coupon);
                    thisCompany.getCoupons().add(coupon);
                    System.out.println("Coupon Added Successfully");
                    break;
                case 2:
                    System.out.println("Select ID of coupon you'd like to update: ");
                    int id = s.nextInt();
                    s.nextLine();

                    Coupon upCoup = couponsDAO.getOneCoupon(id);

                    if (upCoup.getCompanyID()!=companyID){
                        System.out.println("Coupon doesn't belong to this Company!");
                    }else {

                        System.out.println("Update Category: ");
                        System.out.println("1. New Gear");
                        System.out.println("2. Rental Gear");
                        System.out.println("3. Production");
                        System.out.println("4. Post Production");
                        System.out.println("5. Movie Theaters");
                        System.out.println("6. Streaming");
                        System.out.println("7. DVD and Bluray");
                        categoryNum = s.nextInt();
                        s.nextLine();
                        upCoup.setCategory(categoryNum);

                        System.out.println("Update Title: ");
                        String upTitle = s.nextLine();
                        upCoup.setTitle(upTitle);

                        System.out.println("Update Description: ");
                        String upDesc = s.nextLine();
                        upCoup.setDescription(upDesc);

                        System.out.println("Update End Date - Year: ");
                        int upYear = s.nextInt();
                        s.nextLine();
                        System.out.println("Update End Date - Month: ");
                        int upMonth = s.nextInt();
                        s.nextLine();
                        System.out.println("Update End Date - Day: ");
                        int upDay = s.nextInt();
                        s.nextLine();
                        LocalDate updateDate = LocalDate.of(upYear, upMonth, upDay);
                        upCoup.setEndDate(updateDate);

                        System.out.println("Update Amount: ");
                        int upAmount = s.nextInt();
                        s.nextLine();
                        upCoup.setAmount(upAmount);

                        System.out.println("Update Price: ");
                        double upPrice = s.nextDouble();
                        s.nextLine();
                        upCoup.setPrice(upPrice);

                        System.out.println("Update Image Link: ");
                        String upImage = s.nextLine();
                        upCoup.setImage(upImage);

                        updateCoupon(upCoup);
                        System.out.println("Coupon Updated Successfully");
                    }
                    break;
                case 3:
                    System.out.println("Select ID of coupon you'd like to delete: ");
                    id = s.nextInt();
                    s.nextLine();

                    deleteCoupon(id);
                    System.out.println("Coupon Deleted Successfully");
                    break;
                case 4:
                    System.out.println(getCompanyCoupons());
                    break;
                case 5:
                    System.out.println("Select Desired Coupon Category: ");
                    System.out.println("1. New Gear");
                    System.out.println("2. Rental Gear");
                    System.out.println("3. Production");
                    System.out.println("4. Post Production");
                    System.out.println("5. Movie Theaters");
                    System.out.println("6. Streaming");
                    System.out.println("7. DVD and Bluray");

                    categoryNum = s.nextInt();
                    s.nextLine();
                    category = switchCategory(categoryNum);

                    System.out.println(getCompanyCoupons(category));
                    break;
                case 6:
                    System.out.println("Select Max Price: ");
                    int maxPrice = s.nextInt();
                    s.nextLine();

                    System.out.println(getCompanyCoupons(maxPrice));
                    break;
                case 7:
                    System.out.println(thisCompany);
                    break;
                case 0:
                    s.close();
                    exit();
                    break;
                default:
                    System.out.println("There was a problem with your input.");
                    break;
            }

        }
    }

    private void exit() {
        System.out.println("Goodbye");
        System.exit(0);
    }

    private void menu() {
        System.out.println("Choose an Action:");
        System.out.println("1. Add Coupon");
        System.out.println("2. Update Coupon");
        System.out.println("3. Delete Coupon");
        System.out.println("4. Get Company Coupons");
        System.out.println("5. Get Company Coupons by Category");
        System.out.println("6. Get Company Coupons by Maximum Price");
        System.out.println("7. Get Company Details");
        System.out.println("0. Exit");
    }

    public int getCompanyID() {
        return companyID;
    }

    @Override
    public boolean login(String email, String password) {
        return companiesDAO.isCompanyExists(email, password);
    }

    public int getIdThroughLogin(String email, String password) {
        int id = 0;
        for (Company company : companiesDAO.getAllCompanies()) {
            if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
                id = company.getId();
            }
        }
        return id;
    }

    public void addCoupon(Coupon coupon) {
        couponsDAO.addCoupon(coupon);
    }

    public void updateCoupon(Coupon coupon) {
        couponsDAO.updateCoupon(coupon);
    }

    public void deleteCoupon(int couponID) {
        couponsDAO.deleteCoupon(couponID);
    }

    public ArrayList<Coupon> getCompanyCoupons() {
       ArrayList<Coupon> desiredCoupons = new ArrayList<>();
       for (Coupon coupon:couponsDAO.getAllCoupons()){
           if (coupon.getCompanyID()==companyID){
               desiredCoupons.add(coupon);
           }
       }
       return desiredCoupons;
    }

    public ArrayList<Coupon> getCompanyCoupons(Category category) {
        ArrayList<Coupon> desiredCoupons = new ArrayList<>();
        for (Coupon coupon:getCompanyCoupons()){
            if (coupon.getCategory()==category){
                desiredCoupons.add(coupon);
            }
        }
        return desiredCoupons;
    }

    public Category switchCategory(int id){
        Category newCategory = Category.DEAFAULT;

        switch (id) {
            case 1 -> newCategory = Category.NEW_GEAR;
            case 2 -> newCategory = Category.RENTAL_GEAR;
            case 3 -> newCategory = Category.PRODUCTION;
            case 4 -> newCategory = Category.POST_PRODUCTION;
            case 5 -> newCategory = Category.MOVIE_THEATERS;
            case 6 -> newCategory = Category.STREAMING;
            case 7 -> newCategory = Category.DVD_AND_BLURAY;
            default -> System.out.println("There was a problem with your input, please try again.");
        }
        return newCategory;
    }

    public ArrayList<Coupon> getCompanyCoupons(double maxPrice) {
        ArrayList<Coupon> desiredCoupons = new ArrayList<>();
        for (Coupon coupon:getCompanyCoupons()){
            if (coupon.getPrice()<=maxPrice){
                desiredCoupons.add(coupon);
            }
        }
        return desiredCoupons;
    }

    public Company getCompanyDetails() {
        return companiesDAO.getOneCompany(this.companyID);
    }
}
