package facade;

import java.util.ArrayList;

import beans.Category;
import beans.Company;
import beans.Coupon;
import database.UserLists;

public class CompanyFacade extends ClientFacade{

    private int companyID;

    public CompanyFacade() {
        this.companyID = companyID;
    }

    public CompanyFacade(int companyID) {
        this.companyID = companyID;
    }

    public int getCompanyID() {
        return companyID;
    }

    @Override
    public boolean login(String email, String password) {
      return companiesDAO.isCompanyExists(email, password);
    }

    public static int getIdThroughLogin(String email, String password){
        int id = 0;
        for (Company company: UserLists.getAllCompanies()){
            if (company.getEmail().equals(email) && company.getPassword().equals(password)){
               id = company.getId();
            }
        }
        return id;
    }

    public void addCoupon(Coupon coupon){
        couponsDAO.addCoupon(coupon);
    }

    public void updateCoupon (Coupon coupon){
        couponsDAO.updateCoupon(coupon);
    }

    public void deleteCoupon (int couponID){
        couponsDAO.deleteCoupon(couponID);
    }

    public ArrayList<Coupon> getCompanyCoupons(){
        return null;
    }

    public ArrayList<Coupon> getCompanyCoupons(Category category){
        return null;
    }

    public ArrayList<Coupon> getCompanyCoupons(double maxPrice){
        return null;
    }

    public Company getCompanyDetails(){
        return companiesDAO.getOneCompany(this.companyID);
    }
}
