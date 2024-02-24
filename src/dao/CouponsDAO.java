package dao;

import beans.Coupon;
import beans.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon);
    public void updateCoupon(Coupon coupon);
    public void deleteCoupon (int couponID);
    public List<Coupon> getAllCoupons();
    public Coupon getOneCoupon(int couponID);
    public void addCouponPurchase(int customerID, int couponID);
    public void deleteCouponPurchase(int customerID, int couponID);
    public Map<Integer, ArrayList<Integer>> getCouponPurchaseHistory(List<Customer> customers);
}
