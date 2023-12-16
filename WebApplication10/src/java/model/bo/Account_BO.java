package model.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Account;
import model.bean.Detail_account;
import model.bean.Product;
import model.dao.Account_DAO;

public class Account_BO {
	private Account_DAO account_DAO;
	public Account_BO() {
		this.account_DAO = new Account_DAO();
	}
	
	public ArrayList<Account> getListAccount_BO(){
		
		return account_DAO.getListAccount();
	}
        
        public ArrayList<Detail_account> getListDetailAccount_BO(){
		
		return account_DAO.getListDetailAccount();
	}
        
        public ArrayList<Product> getListProduct_BO(){
		
		return account_DAO.get_List_Product();
	}
        
        public ArrayList<Product> getListProductPaging_BO(int page, int recordsPerPage, String category){
		
		return account_DAO.get_List_Product_Paging(page, recordsPerPage, category);
	}
        
        public int getTotal_BO(){
		
		return account_DAO.getTotal();
	}
	
	public void addAccount_BO(Account account, Detail_account detail_account) throws SQLException {
		account_DAO.addAccount(account, detail_account);
	}
	
	public ArrayList<String> getListUser_BO(){
		return account_DAO.getListUser();
	}
        
        public int get_Role_By_Id(int id)
        {
            return account_DAO.get_Role_By_Id(id);
        }
        
        public int get_id_by_username(String username){
            return account_DAO.get_id_by_username(username);
        }
}