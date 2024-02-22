

import java.time.LocalDate;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.*;
import database.ConnectionPool;
import database.DBManager;
import facade.CompanyFacade;
import login.ClientType;
import login.LoginManager;

public class Tester {

    public static void main(String[] args) {

        DBManager.createDataBase();
        CompaniesDAO companiesDAO = new CompaniesDBDAO(ConnectionPool.getInstance());
        CustomersDAO customersDAO = new CustomersDBDAO(ConnectionPool.getInstance());
        CouponsDAO couponsDAO = new CouponsDBDAO(ConnectionPool.getInstance());

        Company company = new Company("Yes Plant", "yesplanet@gmail.com","yesplanet");
        Company company2 = new Company("Cinema City", "cinemacity@gmail.com","cinemacity");
        Company company3 = new Company("Hot Cinema", "hotcinema@gmail.com","hotcinema");
        Company company4 = new Company("Movie Land", "movieland@gmail.com","movieland");




     /* companiesDAO.addCompany(company);
      companiesDAO.addCompany(company2);
        companiesDAO.addCompany(company3);
       companiesDAO.addCompany(company4);*/

       // companiesDAO.addCompany(company3);

       // companiesDAO.getOneCompany(4).setEmail("globusmax@gmail.com");
       // companiesDAO.getOneCompany(4).setName("globusmax");

     //   companiesDAO.updateCompany(companiesDAO.getOneCompany(4));

   //   companiesDAO.deleteCompany(3);

    }
}
