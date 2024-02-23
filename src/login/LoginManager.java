package login;

import dao.CompaniesDAO;
import dao.CompaniesDBDAO;
import dao.CustomersDAO;
import dao.CustomersDBDAO;
import database.ConnectionPool;
import exceptions.LoginFailException;
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

    public ClientFacade login(String email, String password, ClientType clientType) throws LoginFailException{
           ClientFacade clientFacade;
        switch (clientType) {
               case Administrator:
                   clientFacade = new AdminFacade();
                   if (clientFacade.login(email, password)){
                       System.out.println("Welcome Admin!");
                       clientFacade = new AdminFacade(true);
                       return clientFacade;
                   }
                   else throw new LoginFailException("Admin Login Failed. Problem with Email or Password.");
               case Company:
                   clientFacade = new CompanyFacade();
                   if (clientFacade.login(email, password)) {
                       System.out.println("Welcome Company!");
                       clientFacade = new CompanyFacade(email, password);
                       return clientFacade;
                   }
                   else throw new LoginFailException("Company Login Failed. Problem with Email or Password.");
               case Customer:
                   clientFacade = new CustomerFacade();
                   if (clientFacade.login(email, password)) {
                       System.out.println("Welcome Customer!");
                       return clientFacade;
                   }
                   else throw new LoginFailException("Customer Login Failed. Problem with Email or Password.");
           }
        return null;
        }


    public static boolean customerLogin(String email, String password) {
        CustomersDAO customersDAO = new CustomersDBDAO(ConnectionPool.getInstance());
        return customersDAO.isCustomerExists(email, password);
    }

    public static boolean companyLogin(String email, String password) {
        CompaniesDAO companiesDAO = new CompaniesDBDAO(ConnectionPool.getInstance());
        return companiesDAO.isCompanyExists(email, password);
    }

}
