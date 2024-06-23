package banking;

public class MenuSelectException extends Exception {
	public MenuSelectException() {
		super("해당 메뉴가 없습니다. 메뉴를 다시 입력하세요.");
	}
}
