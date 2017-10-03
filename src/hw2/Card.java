package hw2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Card {
	private ArrayList<Double> transactions;
	private String cardNumber; 
	private GregorianCalendar expiryDate;
	
	/**
	 * creates a new Card with the given card number
	 * @param cardNumber the card number
	 */
	public Card(final String cardNumber) {
		this.cardNumber = cardNumber;
		this.expiryDate = new GregorianCalendar(); 
		this.expiryDate.add(Calendar.YEAR, 4);
		transactions = new ArrayList<Double>();
	}
	
	/**
	 * creates a new Card with the given card number and given expiration date
	 * @param cardNumber the card number. bankID + account number
	 * @param expiryYear year of desired expiration date
	 * @param expiryMonth month of desired expiration date
	 * @param expiryDate date of month of desired expiration date
	 */
	public Card(final String cardNumber, int expiryYear, int expiryMonth, int expiryDate) {
		this.cardNumber = cardNumber;
		this.expiryDate = new GregorianCalendar(expiryYear, expiryMonth, expiryDate);
		transactions = new ArrayList<Double>();
	}
	
	/**
	 * returns the card number
	 * @return card number
	 */
	public String getCardNumber() {
		return this.cardNumber;
	}
	
	/**
	 * adds the transaction amount to the transaction log
	 * @param amount the amount to add
	 */
	public void addTransaction(double amount) {
		transactions.add(amount);
	}
	
	/**
	 * method to check if a card is expired 
	 * @param card the card to check if it is expired or not
	 * @return false if it is not expired, true if it is
	 */
	public boolean isExpired() {
		GregorianCalendar today = new GregorianCalendar();
		if (this.expiryDate.before(today)) {
			return true;
		} else {
			return false;
		}
	}
	 
	/**
	 * returns string representation of the expiration date
	 * @return string representation of the expiration date
	 */
	public String getStringOfExpiryDate() {
		String year = Integer.toString(expiryDate.get(Calendar.YEAR));
		String month = Integer.toString(expiryDate.get(Calendar.MONTH)+1);
		String day = Integer.toString(expiryDate.get(Calendar.DAY_OF_MONTH));
		return month + "/" + day + "/" + year;
	}
}
