package facade;

import dao.*;
import database.ConnectionPool;

public abstract class ClientFacade {
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;
    protected CouponsDAO couponsDAO;

    ClientFacade() {
        this.companiesDAO = new CompaniesDBDAO(ConnectionPool.getInstance());
        this.customersDAO = new CustomersDBDAO(ConnectionPool.getInstance());
        this.couponsDAO = new CouponsDBDAO(ConnectionPool.getInstance());
    }


    public abstract boolean login(String email, String password);

}
