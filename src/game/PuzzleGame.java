package game;

import java.util.Random;
import java.util.Scanner;

public class PuzzleGame {

	public static void main(String[] args) {

		while (true) {

			// 배열선언 그리고 좌표
			char[][] puzzle = new char[][] { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', 'X' } };
			int x = 2;
			int y = 2;

			System.out.println("*** 새로운 게임을 시작합니다 ***");

			// 셔플
			Random random = new Random();
			for (int i = 0; i < 100; i++) {
				int move = (random.nextInt(5) - 2);
				if ((move == 1) && (y < 2)) {
					puzzle[x][y] = puzzle[x][y + 1];
					puzzle[x][y + 1] = 'X';
					y++;
				}
				if ((move == -1) && (y > 0)) {
					puzzle[x][y] = puzzle[x][y - 1];
					puzzle[x][y - 1] = 'X';
					y--;
				}
				if ((move == 2) && (x > 0)) {
					puzzle[x][y] = puzzle[x - 1][y];
					puzzle[x - 1][y] = 'X';
					x--;
				}
				if ((move == -2) && (x < 2)) {
					puzzle[x][y] = puzzle[x + 1][y];
					puzzle[x + 1][y] = 'X';
					x++;
				}
			}

			// 게임초기상태 출력
			System.out.println("=====");
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(puzzle[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("=====");
			System.out.println();

			System.out.println("[이동] a:Left, d:Right, w:Up s:Down");
			System.out.println("[종료] x:Exit");

			// 게임하기
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.print("키를 입력해주세요 : ");
				char move = scanner.nextLine().charAt(0);

				if ((move == 'd') && (y < 2)) {
					puzzle[x][y] = puzzle[x][y + 1];
					puzzle[x][y + 1] = 'X';
					y++;
				}
				if ((move == 'a') && (y > 0)) {
					puzzle[x][y] = puzzle[x][y - 1];
					puzzle[x][y - 1] = 'X';
					y--;
				}
				if ((move == 'w') && (x > 0)) {
					puzzle[x][y] = puzzle[x - 1][y];
					puzzle[x - 1][y] = 'X';
					x--;
				}
				if ((move == 's') && (x < 2)) {
					puzzle[x][y] = puzzle[x + 1][y];
					puzzle[x + 1][y] = 'X';
					x++;
				}

				System.out.println("=====");
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						System.out.print(puzzle[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("=====");

				// 맞췄는지 확인
				if ((x == 2) && (y == 2)) {
					if (puzzle[0][0] == '1' && puzzle[0][1] == '2' && puzzle[0][2] == '3' && puzzle[1][0] == '4'
							&& puzzle[1][1] == '5' && puzzle[1][2] == '6' && puzzle[2][0] == '7'
							&& puzzle[2][1] == '8') {
						System.out.println("정답입니다.");
						break;
					}
				}
			}

			// 게임 재시작 여부 확인
			System.out.println("재시작 하시겠습니까? (y)");
			char exe = scanner.nextLine().charAt(0);
			if (exe != 'y') {
				break;
			}

		}
		System.out.println("게임이 종료됩니다.");

	}

}
