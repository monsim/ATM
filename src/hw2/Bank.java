package hw2;

import java.util.ArrayList;

public class Bank {
	private String bankID;
	private ArrayList<Account> accounts;
	
	/**
	 * makes a new Bank object
	 * @param bank the bankID
	 */
	public Bank(String bank) {
 		this.bankID = bank; 
 		accounts = new ArrayList<Account>();
	}
	
	/**
	 * returns the bankID
	 * @return the bankID
	 */
	public String getBankID() {
		return this.bankID.toLowerCase();
	}
	
	/**
	 * returns the Accounts of which the Bank has
	 * @return the Accounts of which the Bank has
	 */
	public ArrayList<Account> getAccounts(){
		return this.accounts;
	}
	
	/**
	 * adds an Account to the Bank
	 * @param a the Account to add to the Bank
	 */
	public void addToAccounts(Account a) {
		accounts.add(a);
	}
	
	/**
	 * verifies that the inputted String matches the bankID
	 * @param id the String to see if it is the bankID
	 * @return true if id is equal to the bankID. false if id is not equal to bankID
	 */
	public boolean verifyBank(String id) {
		return bankID.toLowerCase().equals(id);
	}
	
	/**
	 * returns a String representation of the Bank's state including the customers, account numbers, and expiration dates
	 * @return a String representation of the Bank's state including the customers, account numbers, and expiration dates
	 */
	public String bankState() {
		String state = "";
		int numCustomers = accounts.size();
		if (numCustomers == 0) {
			return "(no customers)";
		}
		state += "(" + numCustomers + " customers)\n";
		for(Account a : accounts) {
			state += a.getName() + " with Cash Card (bankid: " + bankID + ", account number: " + a.getAccountID() + ", expires on: " + a.getCard().getStringOfExpiryDate() + ", password: " + a.getPassword() +"\n";
		}
		return state;
	}
}
