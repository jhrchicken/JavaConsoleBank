package banking;

public class NormalAccount extends Account {

	private int rate;

	public NormalAccount(String number, String username, int balance, int rate) {
		super(number, username, balance);
		this.rate = rate;
	}

	@Override
	public void showBasicInfo() {
		System.out.println("-----------");
		System.out.println("계좌번호 : " + getNumber());
		System.out.println("고객이름 : " + getUsername());
		System.out.println("잔고 : " + getBalance());
		System.out.println("기본이자 : " + this.rate + "%");
		System.out.println("-----------");
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}
