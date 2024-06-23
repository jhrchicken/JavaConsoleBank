package banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;

public class AccountManager {

	HashSet<Account> accountSet;

	public AccountManager() {
		accountSet = new HashSet<Account>();
	}

	// 메뉴 보여주기
	public void menuShow() {
		System.out.println("==== Menu ====");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 계좌정보삭제");
		System.out.println("6. 저장옵션");
		System.out.println("7. 프로그램종료");
		System.out.print("선택 : ");
	}

	// 계좌개설
	public void makeAccount() {
		System.out.println("** 신규계좌개설 **");
		System.out.println("-- 계좌선택 --");
		System.out.println("1. 보통계좌");
		System.out.println("2. 신용신뢰계좌");
		System.out.println("3. 특판계좌");

		Scanner scanner = new Scanner(System.in);
		System.out.print("선택 : ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		System.out.print("계좌번호 : ");
		String number = scanner.nextLine();
		System.out.print("고객이름 : ");
		String name = scanner.nextLine();
		System.out.print("잔고 : ");
		int balance = scanner.nextInt();

		if (choice == 1) {
			System.out.print("기본이자 : ");
			int rate = scanner.nextInt();
			scanner.nextLine();

			NormalAccount normal = new NormalAccount(number, name, balance, rate);
			// 중복계좌
			for (Account account : accountSet) {
				if (account.equals(normal)) {
					System.err.println("중복계좌가 발견되었습니다.");
					System.out.println();
					System.out.print("기존 정보를 덮어쓰시겠습니까? (y or n) : ");
					// 덮어쓰기
					char select = scanner.nextLine().charAt(0);
					if (select == 'y') {
						accountSet.remove(account);
						accountSet.add(normal);
						System.err.println("새로운 정보로 갱신되었습니다.");
						System.out.println();
					}
				}
			}
			accountSet.add(normal);
		}

		if (choice == 2) {
			System.out.print("기본이자 : ");
			int rate = scanner.nextInt();
			scanner.nextLine();
			System.out.print("신용등급 : ");
			char credit = scanner.nextLine().charAt(0);

			HighCreditAccount high = new HighCreditAccount(number, name, balance, rate, credit);
			// 중복계좌
			for (Account account : accountSet) {
				if (account.equals(high)) {
					System.err.println("중복계좌가 발견되었습니다.");
					System.out.println();
					System.out.print("기존 정보를 덮어쓰시겠습니까? (y or n) : ");
					// 덮어쓰기
					char select = scanner.nextLine().charAt(0);
					if (select == 'y') {
						accountSet.remove(account);
						accountSet.add(high);
						System.err.println("새로운 정보로 갱신되었습니다.");
						System.out.println();
						return;
					}
					if (select == 'n') {
						return;
					}
				}
			}
			accountSet.add(high);
		}

		if (choice == 3) {
			System.out.print("기본이자 : ");
			int rate = scanner.nextInt();
			scanner.nextLine();

			SpecialAccount special = new SpecialAccount(number, name, balance, rate);
			// 중복계좌
			for (Account account : accountSet) {
				if (account.equals(special)) {
					System.err.println("중복계좌가 발견되었습니다.");
					System.out.println();
					System.out.print("기존 정보를 덮어쓰시겠습니까? (y or n) : ");
					// 덮어쓰기
					char select = scanner.nextLine().charAt(0);
					if (select == 'y') {
						accountSet.remove(account);
						accountSet.add(special);
						System.err.println("새로운 정보로 갱신되었습니다.");
						System.out.println();
						return;
					}
					if (select == 'n') {
						return;
					}
				}
			}
			accountSet.add(special);

		}

		System.err.println("계좌가 개설되었습니다.");
		System.out.println();
	}

	// 입금
	public void depositMoney() {
		System.out.println("** 입금 **");
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");

		Scanner scanner = new Scanner(System.in);
		System.out.print("계좌번호 : ");
		String number = scanner.nextLine();
		System.out.print("입금액 : ");
		int deposit = scanner.nextInt();

		if (deposit < 0) {
			System.err.println("음수는 입금 불가합니다.");
			return;
		}

		if (deposit % 500 != 0) {
			System.err.println("500원 단위로만 입금이 가능합니다.");
			return;
		}

		for (Account account : accountSet) {
			if (account.getNumber().equals(number)) {
				// 특판계좌
				if (account instanceof SpecialAccount) {
					SpecialAccount special = (SpecialAccount) account;
					int balance = special.getBalance();
					int rate = special.getRate();
					special.setCount(special.getCount() + 1);
					int count = special.getCount();
					if (count % 2 == 0) {
						special.setBalance((int) (balance + (balance * rate / 100) + deposit) + 500);
					} else {
						special.setBalance((int) (balance + (balance * rate / 100) + deposit));
					}
					break;
				}
				// 보통예금계좌
				if (account instanceof NormalAccount) {
					NormalAccount normal = (NormalAccount) account;
					int balance = normal.getBalance();
					int rate = normal.getRate();
					normal.setBalance((int) (balance + (balance * rate / 100) + deposit));
					break;
				}
				// 신용신뢰계좌
				if (account instanceof HighCreditAccount) {
					HighCreditAccount high = (HighCreditAccount) account;
					int balance = high.getBalance();
					int rate = high.getRate();
					char credit = high.getCredit();
					if (credit == 'A') {
						high.setBalance((int) (balance + (balance * rate / 100) + (balance * 7 / 100) + deposit));
					}
					if (credit == 'B') {
						high.setBalance((int) (balance + (balance * rate / 100) + (balance * 4 / 100) + deposit));
					}
					if (credit == 'C') {
						high.setBalance((int) (balance + (balance * rate / 100) + (balance * 2 / 100) + deposit));
					}
					break;
				}
			}
		}

		System.err.println("입금이 완료되었습니다.");
		System.out.println();

	}

	// 출금
	public void withdrawMoney() {
		System.out.println("** 출금 **");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");

		Scanner scanner = new Scanner(System.in);
		System.out.print("계좌번호 : ");
		String number = scanner.nextLine();
		System.out.print("출금액 : ");
		int money = scanner.nextInt();
		scanner.nextLine();

		for (Account account : accountSet) {
			if (account.getNumber().equals(number)) {
				if (account.getBalance() < money) {
					System.err.print("잔고가 부족합니다. 금액전체를 출력할까요? (y or n) : ");
					char select = scanner.nextLine().charAt(0);

					if (select == 'y') {
						account.setBalance(0);
						System.err.println("출금이 완료되었습니다.");
					}
					return;
				}

				account.setBalance(account.getBalance() - money);
				break;
			}
		}

		System.err.println("출금이 완료되었습니다.");
		System.out.println();
	}

	// 계좌정보출력
	public void showAccInfo() {
		System.out.println("** 계좌정보출력 **");

		for (Account account : accountSet) {
			account.showBasicInfo();
		}

		System.err.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}

	// 계좌정보삭제
	public void deleteAccount() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("** 계좌번호삭제 **");
		System.out.println("삭제할 계좌번호를 입력하세요.");

		System.out.print("계좌번호 : ");
		String number = scanner.nextLine();

		int isDelete = -1;
		for (Account account : accountSet) {
			if (number.compareTo(account.getNumber()) == 0) {
				accountSet.remove(account);
				isDelete = 1;
				System.err.println("계좌를 삭제하였습니다.");
				System.out.println();
			}
		}
		if (isDelete == -1) {
			System.err.println("일치하는 계좌가 없습니다.");
			System.out.println();
		}
	}

	// 저장옵션
	public AutoSaver autoSaveAccount(AutoSaver autoSaver) {
		System.out.println("** 저장옵션 **");
		System.err.println("저장옵션을 선택하세요.");

		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 자동저장 On 2. 자동저장 Off");
		System.out.print("선택 : ");
		int select = scanner.nextInt();
		scanner.nextLine();

		// 자동저장 On/Off
		if (select == 1) {
			if (autoSaver == null) {
				System.err.println("자동저장을 시작합니다.");
				autoSaver = new AutoSaver(accountSet);
				autoSaver.setDaemon(true);
				autoSaver.start();
			} else {
				if (autoSaver.isAlive()) {
					System.err.println("이미 자동저장이 실행중입니다.");
				}
			}
		}
		if (select == 2) {
			if (autoSaver != null && autoSaver.isAlive()) {
				autoSaver.interrupt();
				System.err.println("자동저장을 중지합니다.");
			} else {
				System.err.println("자동저장이 실행중이 아닙니다.");
			}
		}
		return autoSaver;

	}

	// 저장(직렬화)
	void saveAccountInfo() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/banking/AccountInfo.obj"));

			for (Account account : accountSet) {
				out.writeObject(account);
			}
			out.close();
		} catch (IOException e) {
			System.err.println("계좌정보를 저장할 수 없습니다.");
			System.out.println();
		} catch (Exception e) {
			System.err.println("계좌정보를 저장할 수 없습니다.");
			System.out.println();
		}
		System.err.println("계좌정보가 저장되었습니다.");
		System.out.println();
	}

	// 역직렬화
	public void readAccountInfo() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/banking/AccountInfo.obj"));

			while (true) {
				Account account = (Account) in.readObject();
				accountSet.add(account);
			}
		} catch (FileNotFoundException e) {
			System.err.println("저장된 계좌정보가 없습니다.");
			System.out.println();
		} catch (IOException e) {
			System.err.println("계좌정보가 복원되었습니다.");
			System.out.println();
		} catch (Exception e) {
			System.err.println("계좌정보를 불러올 수 없습니다.");
			System.out.println();
		}
	}
}
