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

    public ClientFacade login(String email, String password, ClientType clientType) throws LoginFailException {
        ClientFacade clientFacade;
        switch (clientType) {
            case ADMINISTRATOR:
                clientFacade = new AdminFacade();
                if (clientFacade.login(email, password)) {
                    System.out.println("Welcome Admin!");
                    clientFacade = new AdminFacade(true);
                    return clientFacade;
                } else throw new LoginFailException("Admin Login Failed. Problem with Email or Password.");
            case COMPANY:
                clientFacade = new CompanyFacade();
                if (clientFacade.login(email, password)) {
                    System.out.println("Welcome Company!");
                    clientFacade = new CompanyFacade(email, password);
                    return clientFacade;
                } else throw new LoginFailException("Company Login Failed. Problem with Email or Password.");
            case CUSTOMER:
                clientFacade = new CustomerFacade();
                if (clientFacade.login(email, password)) {
                    System.out.println("Welcome Customer!");
                    clientFacade = new CustomerFacade(email, password);
                    return clientFacade;
                } else throw new LoginFailException("Customer Login Failed. Problem with Email or Password.");
        }
        return null;
    }

}
