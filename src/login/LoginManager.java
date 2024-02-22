package login;

import dao.CompaniesDAO;
import dao.CompaniesDBDAO;
import dao.CustomersDAO;
import dao.CustomersDBDAO;
import database.ConnectionPool;
import facade.AdminFacade;
import facade.ClientFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;

public class LoginManager {

    private static LoginManager instance = null;

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

    private LoginManager() {

    }

    public ClientFacade login(String email, String password, ClientType clientType) {
        switch (clientType) {
            case Administrator:
                AdminFacade adminFacade = new AdminFacade();
                if (adminFacade.login(email, password))
                    return adminFacade;
            case Company:
                CompanyFacade companyFacade = new CompanyFacade();
                if (companyFacade.login(email, password))
                    return companyFacade;
            case Customer:
                CustomerFacade customerFacade = new CustomerFacade();
                if (customerFacade.login(email, password))
                    return customerFacade;
        }
        return null;
        }
//      if (clientType == ClientType.Administrator && adminLogin(email, password)){
//          return new AdminFacade();
//      }if (clientType == ClientType.Company && companyLogin(email, password)){
//          return new CompanyFacade(CompanyFacade.getIdThroughLogin(email, password));
//      }if (clientType == ClientType.Customer && customerLogin(email, password)){
//          return new CustomerFacade(CustomerFacade.getIdThroughLogin(email, password));
//      }else {
//          return null;
//        }


    public static boolean customerLogin(String email, String password) {
        CustomersDAO customersDAO = new CustomersDBDAO(ConnectionPool.getInstance());
        return customersDAO.isCustomerExists(email, password);
    }

    public static boolean companyLogin(String email, String password) {
        CompaniesDAO companiesDAO = new CompaniesDBDAO(ConnectionPool.getInstance());
        return companiesDAO.isCompanyExists(email, password);
    }

    public static boolean adminLogin(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }
}
