package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Company;
import database.ConnectionPool;
import database.DBManager;

public class CompaniesDBDAO implements CompaniesDAO {


  private ConnectionPool connectionPool;

  // Constructor that takes a Connection
  public CompaniesDBDAO(ConnectionPool connectionPool) {

    this.connectionPool = connectionPool;
  }

  // Implement methods from CompaniesDAO interface
  @Override

  public boolean isCompanyExists(String email, String password) {
    Connection connection = null;
    boolean result = false;

    try {
      connection = connectionPool.getConnection();

      String query = "SELECT EXISTS (SELECT * FROM" + DBManager.DB + ".`companies`" + "WHERE email = ? AND password = ?) AS res;";

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        result = resultSet.getBoolean("res");
      }

    } catch (InterruptedException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      connectionPool.restoreConnection(connection);
    }
    return result;
  }

  @Override
  public void addCompany(Company company) {
    Connection connection = null;
    try {
      connection = connectionPool.getConnection();
      String query = "INSERT INTO " + DBManager.DB + ".`companies` " + "(name,email,password) VALUES(?,?,?)" ;
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, company.getName());
      preparedStatement.setString(2, company.getEmail());
      preparedStatement.setString(3, company.getPassword());
      preparedStatement.execute();
    } catch (InterruptedException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      connectionPool.restoreConnection(connection);
    }

  }

  @Override
  public void updateCompany(Company company) {
    Connection connection = null;

    try {
      connection = connectionPool.getConnection();
      String query = "UPDATE"+ DBManager.DB + ".`companies`" + "SET email = ?, name= ?, password = ? WHERE id = ?;";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, company.getEmail());
      preparedStatement.setString(2, company.getName());
      preparedStatement.setString(3, company.getPassword());
      preparedStatement.setInt(4, company.getId());
      preparedStatement.execute();
    } catch (InterruptedException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      connectionPool.restoreConnection(connection);
    }
  }

  @Override
  public void deleteCompany(int companyID) {
    Connection connection = null;
    String query = "DELETE FROM " + DBManager.DB + ".`companies` WHERE (`ID` = '" + companyID + "')";
    try {
      connection = connectionPool.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();
    } catch (InterruptedException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      connectionPool.restoreConnection(connection);
    }
  }

//  // TODO: update this method to delete company from database
  //V
//  private void removeCompanyFromList(int companyID) {
//    // for (Company company : companies) {
//    //   if (companyID == company.getId()) {
//    //     companies.remove(company);
//    //     break;
//    //   }
//    // }
//  }

  @Override
  public List<Company> getAllCompanies() {
    Connection connection = null;
    List<Company> result = new ArrayList<>();

    try {
      connection = connectionPool.getConnection();
      String query = "SELECT * FROM " + DBManager.DB + ".`companies`";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Company company = new Company();
        company.setId(resultSet.getInt("id"));
        company.setEmail(resultSet.getString("email"));
        company.setName(resultSet.getString("name"));
        company.setPassword(resultSet.getString("password"));
        result.add(company);
      }
    } catch (InterruptedException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      connectionPool.restoreConnection(connection);
    }
    return result;
  }

  // TODO: update this method to get company from database
  // V
   @Override
   public Company getOneCompany(int companyID) {
     Connection connection = null;
     Company result = new Company();


     try {
       connection = connectionPool.getConnection();
       String query = "SELECT * FROM " + DBManager.DB + ".`companies`";
       PreparedStatement preparedStatement = connection.prepareStatement(query);
       ResultSet resultSet = preparedStatement.executeQuery();
       while (resultSet.next()) {
         result.setId(resultSet.getInt("id"));
         result.setEmail(resultSet.getString("email"));
         result.setName(resultSet.getString("name"));
         result.setPassword(resultSet.getString("password"));
       }
     } catch (InterruptedException | SQLException e) {
       System.out.println(e.getMessage());
     } finally {
       connectionPool.restoreConnection(connection);
     }
     return result;
   }

}
