package facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Company;
import beans.Customer;
import login.LoginManager;

import static java.lang.System.exit;

public class AdminFacade extends ClientFacade {

    public AdminFacade() {

    }

    public AdminFacade(boolean login) {
        while (true) {
            Scanner s = new Scanner(System.in);
            int input;
            menu();

            input = s.nextInt();
            s.nextLine();

            switch (input) {
                case 1: //add company
                    System.out.println("Company Name: ");
                    String name = s.nextLine();
                    System.out.println("Company Email: ");
                    String email = s.nextLine();
                    System.out.println("Password: ");
                    String password = s.nextLine();

                    Company addComp = new Company(name, email, password);

                    addCompany(addComp);
                    System.out.println("Company has been added");
                    break;

                case 2: //update company
                    System.out.println("Pick the ID of the company you wish to update: ");
                    int id = s.nextInt();
                    s.nextLine();

                    Company upComp = getOneCompany(id);

                    System.out.println("Update Email: ");
                    String newEmail = s.nextLine();
                    upComp.setEmail(newEmail);

                    System.out.println("Update Password: ");
                    String newPassword = s.nextLine();
                    upComp.setPassword(newPassword);

                    updateCompany(upComp);
                    System.out.println("Company Updated");
                    break;

                case 3: //delete company
                    System.out.println("Choose ID of company you'd like to delete: ");
                    int delId = s.nextInt();
                    s.nextLine();

                    deleteCompany(delId);
                    System.out.println("Company Deleted");
                    break;
                case 4: //get all companies
                    System.out.println(getAllCompanies());
                    break;
                case 5: //get one company
                    System.out.println("Type ID of Company: ");
                    int compId = s.nextInt();
                    s.nextLine();

                    System.out.println(getOneCompany(compId));
                    break;

                case 6: //add customer
                    System.out.println("Enter First Name: ");
                    String firstName = s.nextLine();

                    System.out.println("Enter Last Name: ");
                    String lastName = s.nextLine();

                    System.out.println("Customer Email: ");
                    String cusEmail = s.nextLine();

                    System.out.println("Password: ");
                    String cusPass = s.nextLine();

                    Customer customer = new Customer(firstName, lastName, cusEmail, cusPass);
                    addCustomer(customer);
                    System.out.println("Customer has been added");
                    break;

                case 7: //update customer
                    System.out.println("Pick the ID of the Customer you wish to update: ");
                    int upId = s.nextInt();
                    s.nextLine();
                    Customer upCus = getOneCustomer(upId);

                    System.out.println("Update First Name: ");
                    String newFirstName = s.nextLine();
                    upCus.setFirstName(newFirstName);

                    System.out.println("Update Last Name: ");
                    String newLastName = s.nextLine();
                    upCus.setLastName(newLastName);

                    System.out.println("Update Email: ");
                    String newCusEmail = s.nextLine();
                    upCus.setEmail(newCusEmail);

                    System.out.println("Update Password: ");
                    String newCusPass = s.nextLine();
                    upCus.setPassword(newCusPass);

                    updateCustomer(upCus);
                    System.out.println("Customer Updated");
                    break;

                case 8: //delete customer
                    System.out.println("Select the ID of the customer you'd like to delete: ");
                    int delCusId = s.nextInt();
                    s.nextLine();

                    deleteCustomer(delCusId);
                    System.out.println("Customer Deleted");
                    break;

                case 9: //get all customers
                    System.out.println(getAllCustomers());
                    break;

                case 10: //get one customer
                    System.out.println("Type ID of Customer: ");
                    int cusId = s.nextInt();
                    s.nextLine();

                    System.out.println(getOneCompany(cusId));
                    break;

                case 0: //exit system
                    s.close();
                    exit();
                    break;

                default:
                    System.out.println("There was a problem with your input.");
                    break;
            }
        }
    }

    private void exit() {
        System.out.println("Goodbye");
        System.exit(0);
    }

    private void menu() {
        System.out.println("Choose an Action:");
        System.out.println("1. Add Company");
        System.out.println("2. Update Company");
        System.out.println("3. Delete Company");
        System.out.println("4. Get All Companies");
        System.out.println("5. Get One Company");
        System.out.println("6. Add Customer");
        System.out.println("7. Update Customer");
        System.out.println("8. Delete Customer");
        System.out.println("9. Get All Customers");
        System.out.println("10. Get One Customer");
        System.out.println("0. Exit");

    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");

    }

    @Override
    public int getIdThroughLogin(String email, String password) {
        return 0;
    }

    public void addCompany(Company company) {
        companiesDAO.addCompany(company);
    }

    public void updateCompany(Company company) {
        companiesDAO.updateCompany(company);
    }

    public void deleteCompany(int companyID) {
        companiesDAO.deleteCompany(companyID);
    }

    public List<Company> getAllCompanies() {
        return companiesDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyID) {
        return companiesDAO.getOneCompany(companyID);
    }

    public void addCustomer(Customer customer) {
        customersDAO.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        customersDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerID) {
        customersDAO.deleteCustomer(customerID);
    }

    public List<Customer> getAllCustomers() {
        return customersDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerID) {
        return customersDAO.getOneCustomer(customerID);
    }
}
