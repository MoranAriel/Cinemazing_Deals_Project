package facade;

import beans.Category;
import beans.Coupon;
import beans.Customer;
import database.UserLists;
import login.LoginManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

public class CustomerFacade extends ClientFacade {

    private int customerID;

    public CustomerFacade() {
    }

    public CustomerFacade(String email, String password) {
        this.customerID = getIdThroughLogin(email, password);
        System.out.println("ID Number: " + getCustomerID());
        Customer thisCustomer = getCustomerDetails();
        List<Coupon> customerCoupons = getCustomerCoupons();
        thisCustomer.setCoupons(customerCoupons);

        while (true) {
            Scanner s = new Scanner(System.in);
            int input;
            menu();

            input = s.nextInt();
            s.nextLine();

            switch (input){
                case 1:
                    System.out.println("Select the Coupon you'd like to Purchase: ");
                    int couponID = s.nextInt();
                    s.nextLine();

                    Coupon coupon = couponsDAO.getOneCoupon(couponID);
                    if (getCustomerCoupons().contains(coupon))
                        System.out.println("Coupon Already Purchased!");
                    if (coupon.getAmount()<0)
                        System.out.println("This coupon is out of stock!");
                    if (coupon.getEndDate().isBefore(LocalDate.now()))
                        System.out.println("This coupon is out of date!");
                    else{
                        purchaseCoupon(coupon);
                        thisCustomer.getCoupons().add(coupon);
                        System.out.println("Coupon Purchased Successfully");
                    }
                    break;
                case 2:
                    System.out.println(thisCustomer.getCoupons());
                    break;
                case 3:
                    System.out.println("Select Desired Coupon Category: ");
                    System.out.println("1. New Gear");
                    System.out.println("2. Rental Gear");
                    System.out.println("3. Production");
                    System.out.println("4. Post Production");
                    System.out.println("5. Movie Theaters");
                    System.out.println("6. Streaming");
                    System.out.println("7. DVD and Bluray");

                    int categoryNum = s.nextInt();
                    s.nextLine();
                    Category category = switchCategory(categoryNum);

                    System.out.println(getCustomerCoupons(category));
                    break;
                case 4:
                    System.out.println("Select Max Price: ");
                    int maxPrice = s.nextInt();
                    s.nextLine();

                    System.out.println(getCustomerCoupons(maxPrice));
                    break;
                case 5:
                    System.out.println(thisCustomer);
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
        System.out.println("1. Purchase Coupon");
        System.out.println("2. Get Customer Coupons");
        System.out.println("3. Get Customer Coupons by Category");
        System.out.println("4. Get Customer Coupons by Maximum Price");
        System.out.println("5. Get Customer Details");
        System.out.println("0. Exit");
    }

    public int getIdThroughLogin(String email, String password) {
        int id = 0;
        for (Customer customer : customersDAO.getAllCustomers()) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                id = customer.getId();
            }
        }
        return id;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public boolean login(String email, String password) {
        return customersDAO.isCustomerExists(email, password);
    }

    public void purchaseCoupon(Coupon coupon) {
        couponsDAO.addCouponPurchase(customerID, coupon.getId());
        coupon.setAmount(coupon.getAmount() - 1);
        couponsDAO.updateCoupon(coupon);
    }

    public ArrayList<Coupon> getCustomerCoupons() {
        ArrayList<Integer> purchasedCouponIds = couponsDAO.getCouponPurchaseHistory(customersDAO.getAllCustomers()).get(customerID);
        ArrayList<Coupon> purchasedCoupons = new ArrayList<>();
        for (Integer couponID:purchasedCouponIds){
            Coupon coupon = couponsDAO.getOneCoupon(couponID);
            purchasedCoupons.add(coupon);
        }
        return purchasedCoupons;
    }

    public ArrayList<Coupon> getCustomerCoupons(Category category) {
        ArrayList<Coupon> desiredCoupons = new ArrayList<>();
        for (Coupon coupon:getCustomerCoupons()){
            if (coupon.getCategory()==category){
                desiredCoupons.add(coupon);
            }
        }
        return desiredCoupons;
    }

    public Category switchCategory(int id){
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
        return newCategory;
    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) {
        ArrayList<Coupon> desiredCoupons = new ArrayList<>();
        for (Coupon coupon:getCustomerCoupons()){
            if (coupon.getPrice()<=maxPrice){
                desiredCoupons.add(coupon);
            }
        }
        return desiredCoupons;
    }

    public Customer getCustomerDetails() {
        return customersDAO.getOneCustomer(this.customerID);
    }
}
