package hw2;

public class Account {
	private double balance;
	private String password;
	private Card card = null;
	private String accountID = "";
	
	/**
	 * creates a new Account object
	 * @param password the password of the Account
	 * @param balance the starting balance of the Account
	 * @param accountID the accountID
	 */
	public Account(String password, final double balance, final String accountID) { 
		this.balance = balance;
		this.password = password;
		this.accountID = accountID;
	}
	
	/**
	 * returns the current balance of the Account
	 * @return the current balance of the Account
	 */
	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * returns the password of the Account
	 * @return the password of the Account
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * withdraws the amount of money specified from balance 
	 * @param amount amount of money to withdraw
	 */
	public void withdrawMoney(double amount) {
		balance-=amount;
	}
	
	/**
	 * sets the Card associated with the Account
	 * @param c the Card associated with the Account
	 */
	public void setCard(Card c) {
		this.card = c;
	}

	/**
	 * sets the password associated with the Account
	 * @param password  the password to be associated with the Account
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * returns the accountID
	 * @return the accountID
	 */
	public String getAccountID() {
		return accountID;
	}
	
	/**
	 * returns the Card associated with the Account
	 * @return the Card associated with the Account
	 */
	public Card getCard() {
		return this.card;
	}
	
}
