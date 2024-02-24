package dao;

import beans.Category;
import beans.Company;
import beans.Coupon;
import database.ConnectionPool;
import database.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponsDBDAO implements CouponsDAO {

    private ConnectionPool connectionPool;

    public CouponsDBDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addCoupon(Coupon coupon) {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String query = "INSERT INTO " + DBManager.DB + ".`coupons` " +
                    "(company_id,category_id,title,description,start_date,end_date,amount,price,image) " +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, coupon.getCompanyID());
            preparedStatement.setInt(2, coupon.getCategory().getId());
            preparedStatement.setString(3, coupon.getTitle());
            preparedStatement.setString(4, coupon.getDescription());
            preparedStatement.setDate(5, Date.valueOf(coupon.getStartDate()));
            preparedStatement.setDate(6, Date.valueOf(coupon.getEndDate()));
            preparedStatement.setInt(7, coupon.getAmount());
            preparedStatement.setDouble(8, coupon.getPrice());
            preparedStatement.setString(9, coupon.getImage());
            preparedStatement.execute();
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    @Override
    public void updateCoupon(Coupon coupon) {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String query = "UPDATE " + DBManager.DB
                    + ".`coupons` SET `CATEGORY_ID` = '" + coupon.getCategory().getId() + "', " +
                    "`TITLE` = '" + coupon.getTitle() + "', " +
                    "`DESCRIPTION` = '" + coupon.getDescription() + "', " +
                    "`START_DATE` = '" + Date.valueOf(coupon.getStartDate()) + "', " +
                    "`END_DATE` = '" + Date.valueOf(coupon.getEndDate()) + "', " +
                    "`AMOUNT` = '" + coupon.getAmount() + "', " +
                    "`PRICE` = '" + coupon.getPrice() + "', " +
                    "`IMAGE` = '" + coupon.getImage() + "' " +
                    "WHERE (`ID` = '" + coupon.getId() + "')";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    @Override
    public void deleteCoupon(int couponID) {
        Connection connection = null;
        String query = "DELETE FROM " + DBManager.DB
                + ".`coupons` WHERE (`ID` = '" + couponID + "')";
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    @Override
    public List<Coupon> getAllCoupons() {
        Connection connection = null;
        List<Coupon> result = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            String query = "SELECT * FROM " + DBManager.DB + ".`coupons`";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Coupon coupon = new Coupon();
                coupon.setId(resultSet.getInt("ID"));
                coupon.setCompanyID((resultSet.getInt("COMPANY_ID")));
                coupon.setCategory((resultSet.getInt("CATEGORY_ID")));
                coupon.setTitle(resultSet.getString("TITLE"));
                coupon.setDescription(resultSet.getString("DESCRIPTION"));
                coupon.setStartDate(resultSet.getDate("START_DATE").toLocalDate());
                coupon.setEndDate(resultSet.getDate("END_DATE").toLocalDate());
                coupon.setAmount((resultSet.getInt("AMOUNT")));
                coupon.setPrice(resultSet.getInt("PRICE"));
                coupon.setImage(resultSet.getString("IMAGE"));
                result.add(coupon);
            }
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return result;
    }

    @Override
    public Coupon getOneCoupon(int couponID) {
        Connection connection = null;
        Coupon result = new Coupon();

        try {
            connection = connectionPool.getConnection();
            String query = "SELECT * FROM " + DBManager.DB + ".`coupons` WHERE (`ID` = '"+couponID+"')";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt("ID"));
                result.setCompanyID(resultSet.getInt("COMPANY_ID"));
                result.setCategory((resultSet.getInt("CATEGORY_ID")));
                result.setTitle(resultSet.getString("TITLE"));
                result.setDescription(resultSet.getString("DESCRIPTION"));
                result.setStartDate(resultSet.getDate("START_DATE").toLocalDate());
                result.setEndDate(resultSet.getDate("END_DATE").toLocalDate());
                result.setAmount(resultSet.getInt("AMOUNT"));
                result.setPrice(resultSet.getInt("PRICE"));
                result.setImage(resultSet.getString("IMAGE"));
            }
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return result;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(DBManager.ADD_CUSTOMER_VS_COUPON);
            String query = "INSERT INTO " + DBManager.DB + ".`customers_vs_coupons` " +
                    "(customer_id,coupon_id) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerID);
            preparedStatement.setInt(2, couponID);
            preparedStatement.execute();
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            String query = "DELETE FROM " + DBManager.DB + ".`customers_vs_coupons` " +
                    "WHERE (`CUSTOMER_ID` = '" + customerID + "') and (`COUPON_ID` = '" + couponID + "')";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }
}
