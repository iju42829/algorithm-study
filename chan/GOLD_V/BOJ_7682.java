package chan.GOLD_V;

import java.util.Scanner;

public class BOJ_7682 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();
            // 프로그램 종료 조건 확인
            if (input.equals("end")) {
                break;
            }
            if (input.length() != 9) {
                System.out.println("invalid");
                // 입력이 유효하지 않다면 다음 루프로 이동
                continue;
            }
            if (!isValidInput(input)) {
                System.out.println("invalid");
                // 입력이 유효하지 않다면 다음 루프로 이동
                continue;
            }
            char[][] board = getBoard(input);

            if (isValid(board)) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }
    private static boolean isValidInput(String input) {
        for (char c : input.toCharArray()) {
            if (c != 'X' && c != 'O' && c != '.') {
                return false;
            }
        }
        return true;
    }
    public static char[][] getBoard(String input) {
        char[][] board = new char[3][3];
        int index = 0;

        // 문자열을 2차원 배열로 변환
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (index < input.length()) {
                    board[i][j] = input.charAt(index);
                    index++;
                } else {
                    board[i][j] = '.';
                }
            }
        }
        return board;
    }
    public static int getWinner(char[][] board) {
        int countX = 0;
        int countO = 0;

        // 가로축으로 3개가 연속되는지 확인
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '.' &&
                    board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'X') {
                    countX = 1;
                } else if (board[i][0] == 'O'){
                    countO = 1;
                }
            }
        }
        // 세로축으로 3개가 연속되는지 확인
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != '.' &&
                    board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == 'X') {
                    countX = 1;
                } else if (board[0][i] == 'O') {
                    countO = 1;
                }
            }
        }
        //대각선으로 3개가 연속되는지 확인
        if (board[0][0] != '.' &&
                board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') {
                countX = 1;
            } else if (board[0][0] == 'O') {
                countO = 1;
            }
        }
        if (board[0][2] != '.' &&
                board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X') {
                countX = 1;
            } else if (board[0][2] == 'O') {
                countO = 1;
            }
        }
        int wins = countX + countO;
        if (wins == 1){
            if (countX == 1) {
                return 1;
            } else if (countO == 1) {
                return 2;
            }
        } else if (wins == 0) {
            return 3;
        }else {
            return -1;
        }
        return -1;
    }
    public static int checkDiff(char[][] board) {
        int xCount = 0;
        int oCount = 0;

        // X와 O의 개수를 센다.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <board[i].length; j++) {
                if (board[i][j] == 'X') {
                    xCount++;
                } else if (board[i][j] == 'O') {
                    oCount++;
                }
            }
        }
        int diff = xCount - oCount;

        return diff;
    }
    public static boolean isFull(char[][] board) {
        // 보드판이 꽉 찼는지 확인
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <board[i].length; j++) {
                if (board[i][j] != '.') {
                    count++;
                }
            }
        }
        if (count == 9) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isValid(char[][] board) {
        switch  (checkDiff(board)) {
            case 0:
                if(getWinner(board) == 2){
                    return true;
                } else {
                    return false;
                }
            case 1:
                if(getWinner(board) == 1){
                    return true;
                }else if(getWinner(board) == 3 && isFull(board)){
                    return true;
                } else {
                    return false;
                }

            default:
                return false;
        }
    }
}