

import java.time.LocalDate;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.CompaniesDAO;
import dao.CompaniesDBDAO;
import database.ConnectionPool;
import database.DBManager;
import facade.CompanyFacade;
import login.ClientType;
import login.LoginManager;

public class Tester {

    public static void main(String[] args) {

        DBManager.createDataBase();
        CompaniesDAO companiesDAO = new CompaniesDBDAO(ConnectionPool.getInstance());
        CompanyFacade companyFacade = new CompanyFacade(1);

        Company company = new Company("Doron's Company", "doronbrgr@yahoo.com", "maiden666");
        Company company2 = new Company("Avital's Company", "avitalbrgr@yahoo.com", "avi920berger");

        Customer customer = new Customer("Doron", "Berger", "doronbrgr@gmail.com", "MillieB0423");

        Coupon coupon = new Coupon(1, Category.MOVIES,"1+1","Get one ticket for free", LocalDate.now(),LocalDate.of(2024,6,24),10,20.0,"image");

        companiesDAO.addCompany(company);
        companiesDAO.addCompany(company2);

        LoginManager loginManager = LoginManager.getInstance();

        System.out.println(loginManager.login(company.getEmail(), company.getPassword(), ClientType.Company));



    }
}
