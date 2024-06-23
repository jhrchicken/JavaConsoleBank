package banking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class AutoSaver extends Thread {

	HashSet<Account> accountSet;

	public AutoSaver(HashSet<Account> accountSet) {
		this.accountSet = accountSet;
	}

	private void save() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("src/banking/AutoSaveAccount.txt"));

			for (Account account : accountSet) {
				String str = null;
				if (account instanceof NormalAccount) {
					NormalAccount normal = (NormalAccount) account;
					str = String.format("[보통계좌] 계좌번호=%s, 이름=%s, 잔고=%s, 이율=%s", normal.getNumber(), normal.getUsername(),
							normal.getBalance(), normal.getRate());
				}
				if (account instanceof HighCreditAccount) {
					HighCreditAccount high = (HighCreditAccount) account;
					str = String.format("[신용신뢰계좌] 계좌번호=%s, 이름=%s, 잔고=%d, 이율=%d, 등급=%s", high.getNumber(),
							high.getUsername(), high.getBalance(), high.getRate(), high.getCredit());
				}
				out.write(str);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("오류가 발생했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("오류가 발생했습니다.");
		}
		System.err.println("저장 중 ..");
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				save();
				sleep(5000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
