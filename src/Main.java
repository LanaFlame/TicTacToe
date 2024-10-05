import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] square = createSquare();
        char s = 'X';
        char n = 'O';
        char tmp;
        boolean leftDiagonalCheck;
        boolean rightDiagonalCheck;
        Scanner scanner = new Scanner(System.in);
        drawSquare(square);
        for (int i = 0; i < 9; i++) {
            int move = scanner.nextInt();
            if ((move==1 || move==2 || move==3) && square[0][move-1] == ' ') {
                square[0][move-1] = s;
            } else if ((move==4 || move==5 || move==6) && square[1][move-4] == ' ') {
                square[1][move-4] = s;
            } else if ((move==7 || move==8 || move==9) && square[2][move-7] == ' ') {
                square[2][move-7] = s;
            } else {
                System.out.println("invalid number entered or cell is occupied");
                i--;
                continue;
            }
            drawSquare(square);
            tmp = s;
            s = n;
            n = tmp;
            leftDiagonalCheck = square[0][0] == square[1][1] && square[0][0] == square[2][2] && square[0][0] != ' ';
            rightDiagonalCheck = square[0][2] == square[1][1] && square[0][2] == square[2][0] && square[0][2] != ' ';
            for (int j = 0; j < 3; j++) {
                if ((square[j][0] == square[j][1] && square[j][0] == square[j][2] && square[j][0] != ' ')
                        || (square[0][j] == square[1][j] && square[0][j] == square[2][j] && square[0][j] != ' ')) {
                    System.out.println("You win");
                    return;
                }
            }
            if (leftDiagonalCheck || rightDiagonalCheck) {
                System.out.println("You win");
                return;
            }
        }
    }

    private static char[][] createSquare() {
        char[][] square = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                square[i][j] = ' ';
            }
        }
        return square;
    }

    public static void drawSquare(char[][] square) {
        System.out.println(" - - -");
        System.out.println("|"+square[0][0]+"|"+square[0][1]+"|"+square[0][2]+"|");
        System.out.println(" - - -");
        System.out.println("|"+square[1][0]+"|"+square[1][1]+"|"+square[1][2]+"|");
        System.out.println(" - - -");
        System.out.println("|"+square[2][0]+"|"+square[2][1]+"|"+square[2][2]+"|");
        System.out.println(" - - -");
    }
}