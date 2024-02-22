

import java.util.Scanner;

import database.DBManager;
import login.ClientType;
import login.LoginManager;

public class Tester {

    public static void main(String[] args){

        testAll();
    }

    public static void testAll(){
        DBManager.createDataBase();
        LoginManager loginManager = LoginManager.getInstance();

        Scanner scanner = new Scanner(System.in);
        int input = 0;
        String email;
        String password;
        ClientType clientType = ClientType.Default;

        while (input!=1 && input!=2 && input!=3) {
            System.out.println("Enter Client Type: " +
                    "1. Administrator, " +
                    "2. Company, " +
                    "3. Customer ");
            input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1 -> clientType = ClientType.Administrator;
                case 2 -> clientType = ClientType.Company;
                case 3 -> clientType = ClientType.Customer;
                default -> System.out.println("There's a Problem with your input. Please Try Again.");
            }
        }
        System.out.println("Enter Email Address: ");
        email = scanner.nextLine();
        System.out.println("Enter Password: ");
        password = scanner.nextLine();

        loginManager.login(email, password, clientType);
    }
}
