package banking;

public class HighCreditAccount extends Account {

	private int rate;
	private char credit;

	public HighCreditAccount(String number, String username, int balance, int rate, char credit) {
		super(number, username, balance);
		this.rate = rate;
		this.credit = credit;
	}

	@Override
	public void showBasicInfo() {
		System.out.println("-----------");
		System.out.println("계좌번호 : " + getNumber());
		System.out.println("고객이름 : " + getUsername());
		System.out.println("잔고 : " + getBalance());
		System.out.println("기본이자 : " + this.rate + "%");
		System.out.println("신용등급 : " + this.credit);
		System.out.println("-----------");
	}

	public int getRate() {
		return rate;
	}

	public char getCredit() {
		return credit;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public void setCredit(char credit) {
		this.credit = credit;
	}

}
