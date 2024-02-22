package dao;

import beans.Customer;

import java.util.List;

public interface CustomersDAO {

    public boolean isCustomerExists(String email, String password);

    public void addCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void deleteCustomer(int customerID);

    public List<Customer> getAllCustomers();

    public Customer getOneCustomer(int customerID);
}
