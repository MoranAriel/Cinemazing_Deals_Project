

import java.util.Scanner;

import beans.Category;
import beans.Customer;
import dao.CouponsDBDAO;
import dao.CustomersDBDAO;
import database.ConnectionPool;
import database.DBManager;
import exceptions.LoginFailException;
import login.ClientType;
import login.LoginManager;

public class Tester {

    public static void main(String[] args){
        LoginManager.testAll();
    }


}
