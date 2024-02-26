package login;

import dao.CompaniesDAO;
import dao.CompaniesDBDAO;
import dao.CustomersDAO;
import dao.CustomersDBDAO;
import database.ConnectionPool;
import database.DBManager;
import exceptions.LoginFailException;
import facade.AdminFacade;
import facade.ClientFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;

import java.util.Scanner;

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


    public static void testAll(){
        DBManager.createDataBase();
        LoginManager loginManager = LoginManager.getInstance();

        Scanner scanner = new Scanner(System.in);
        int input = 0;
        String email;
        String password;
        ClientType clientType = ClientType.DEFAULT;

        while (input!=1 && input!=2 && input!=3) {
            System.out.println("Enter Client Type: " +
                    "1. Administrator, " +
                    "2. Company, " +
                    "3. Customer, " +
                    "0. Exit");
            input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1 -> clientType = ClientType.ADMINISTRATOR;
                case 2 -> clientType = ClientType.COMPANY;
                case 3 -> clientType = ClientType.CUSTOMER;
                case 0 -> exit();
                default -> System.out.println("There's a Problem with your input. Please Try Again.");
            }
        }
        System.out.println("Enter Email Address: ");
        email = scanner.nextLine();
        System.out.println("Enter Password: ");
        password = scanner.nextLine();

        try {
            loginManager.login(email, password, clientType);
        } catch (LoginFailException e) {
            System.out.println(e.getMessage());
            testAll();
        }
    }

    private static void exit() {
        System.out.println("Goodbye");
        System.exit(0);
    }
}
