package banking;

import java.util.Scanner;

public class BankingSystemMain {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		AccountManager manager = new AccountManager();
		AutoSaver autoSaver = null;

		manager.readAccountInfo();

		while (true) {
			manager.menuShow();
			String input = scanner.nextLine();

			try {
				int choice = Integer.parseInt(input);
				if ((choice < 1) || (choice > 7)) {
					MenuSelectException e = new MenuSelectException();
					throw e;
				}

				switch (choice) {
				case ICustomDefine.MAKE:
					manager.makeAccount();
					break;
				case ICustomDefine.DEPOSIT:
					manager.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					manager.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					manager.showAccInfo();
					break;
				case ICustomDefine.DELETE:
					manager.deleteAccount();
					break;
				case ICustomDefine.SAVE:
					autoSaver = manager.autoSaveAccount(autoSaver);
					break;
				case ICustomDefine.EXIT:
					manager.saveAccountInfo();
					System.err.println("프로그램이 종료됩니다.");
					return;
				}
			} catch (MenuSelectException e) {
				System.err.println(e.getMessage());
				System.out.println();
			} catch (NumberFormatException e) {
				System.err.println("메뉴는 숫자입니다. 메뉴를 다시 입력하세요.");
				System.out.println();
			} catch (Exception e) {
				System.err.println("알 수 없는 오류가 발생했습니다.");
			}

		}

	}

}
