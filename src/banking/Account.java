package banking;

import java.io.Serializable;
import java.util.Objects;

abstract public class Account implements Serializable {

	private String number;
	private String username;
	private int balance;

	public Account(String number, String name, int balance) {
		this.number = number;
		this.username = name;
		this.balance = balance;
	}

	public abstract void showBasicInfo();

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		Account account = (Account) obj;
		if (number.equals(account.number)) {
			return true;
		} else {
			return false;
		}
	}

	public String getNumber() {
		return number;
	}

	public String getUsername() {
		return username;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
