package banking;

public class SpecialAccount extends NormalAccount {

	int count;

	public SpecialAccount(String number, String username, int balance, int rate) {
		super(number, username, balance, rate);
		this.count = 0;
	}

	@Override
	public void showBasicInfo() {
		System.out.println("-----------");
		System.out.println("계좌번호 : " + getNumber());
		System.out.println("고객이름 : " + getUsername());
		System.out.println("잔고 : " + getBalance());
		System.out.println("기본이자 : " + getRate() + "%");
		System.out.println("입금회차 : " + this.count + "회");
		System.out.println("-----------");
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
