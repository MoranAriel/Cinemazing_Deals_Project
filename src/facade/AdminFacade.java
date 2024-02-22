package facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Company;
import beans.Customer;
import login.LoginManager;

public class AdminFacade extends ClientFacade{

    public AdminFacade(){

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

                case 2:
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

                case 3:
                    System.out.println("Choose ID of company you'd like to delete: ");
                    int delId = s.nextInt();
                    s.nextLine();

                    deleteCompany(delId);
                    System.out.println("Company Deleted");
                    break;
                case 4:
                    System.out.println(getAllCompanies());
                    break;
                case 5:
                    System.out.println("Type ID of Company: ");
                    int compId = s.nextInt();
                    s.nextLine();

                    System.out.println(getOneCompany(compId));
                    break;

                case 6:
                    break;

                case 8:
                    break;

                case 9:
                    break;

                case 10:
                    break;

                case 0:
                    break;

                default:
                    System.out.println("There was a problem with your input.");
                    break;
            }
        }
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

    public void addCompany(Company company){
        companiesDAO.addCompany(company);
    }

    public void updateCompany(Company company){
        companiesDAO.updateCompany(company);
    }

    public void deleteCompany (int companyID){
        companiesDAO.deleteCompany(companyID);
    }

    public List<Company> getAllCompanies(){
        return companiesDAO.getAllCompanies();
    }

    public Company getOneCompany (int companyID){
        return companiesDAO.getOneCompany(companyID);
    }

    public void addCustomer(Customer customer){
        customersDAO.addCustomer(customer);
    }

    public void updateCustomer (Customer customer){
        customersDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerID){
        customersDAO.deleteCustomer(customerID);
    }

    public List<Customer> getAllCustomers(){
        return customersDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerID){
        return customersDAO.getOneCustomer(customerID);
    }
}
