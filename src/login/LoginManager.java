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

    public ClientFacade login(String email, String password, ClientType clientType){
           ClientFacade clientFacade;
        switch (clientType) {
               case Administrator:
                   clientFacade = new AdminFacade();
                   if (clientFacade.login(email, password)){
                       System.out.println("Welcome Admin!");
                       clientFacade = new AdminFacade(true);
                       return clientFacade;
                   }
                   else System.out.println("There was a problem with your email and/or password!");
                   break;
               case Company:
                   clientFacade = new CompanyFacade();
                   if (clientFacade.login(email, password)) {
                       System.out.println("Welcome Company!");
                       return clientFacade;
                   }
                   else System.out.println("There was a problem with your email and/or password!");
                   break;
               case Customer:
                   clientFacade = new CustomerFacade();
                   if (clientFacade.login(email, password)) {
                       System.out.println("Welcome Customer!");
                       return clientFacade;
                   }
                   else System.out.println("There was a problem with your email and/or password!");
                   break;
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

}
