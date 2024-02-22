package dao;

import beans.Coupon;

import java.util.ArrayList;
import java.util.List;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon);
    public void updateCoupon(Coupon coupon);
    public void deleteCoupon (int couponID);
    public List<Coupon> getAllCoupons();
    public Coupon getOneCoupon(int couponID);
    public void addCouponPurchase(int customerID, int couponID);
    public void deleteCouponPurchase(int customerID, int couponID);

}
