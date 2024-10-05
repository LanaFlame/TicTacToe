import java.util.List;
import java.util.Scanner;

public class LineTicTacToe {

    private static final char X_ITEM = 'X';
    private static final char C_ITEM = 'O';
    // тут все проверки - ряды, колонки, диагонали
    private static final List<int[]> CHECKS = List.of(
            new int[]{1, 2, 3},
            new int[]{4, 5, 6},
            new int[]{7, 8, 9},
            new int[]{1, 4, 7},
            new int[]{2, 5, 8},
            new int[]{3, 6, 9},
            new int[]{1, 5, 9},
            new int[]{3, 5, 7}
    );

    public static void main(String[] args) {
        char[] field = createField();
        Scanner scanner = new Scanner(System.in);
        String nextLine;
        boolean isNewGame = true;
        char item = X_ITEM;
        while (true) {
            if (isNewGame) {
                System.out.println("New Game!");
                field = createField();
                item = X_ITEM;
                isNewGame = false;
            }
            draw(field);
            nextLine = scanner.nextLine();
            try {
                if ("exit".equals(nextLine)) {
                    return;
                }
                int num = Integer.parseInt(nextLine);
                move(field, num, item);
                if (isWin(field)) {
                    draw(field);
                    System.out.println("'" + item + "' win!");
                    isNewGame = true;
                } else {
                    // переход хода к другому игроку
                    item = (item == X_ITEM) ? C_ITEM : X_ITEM;
                }
            } catch (NumberFormatException e) {
                System.out.println("Write a number pls");
            } catch (WrongMoveException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // инициализация поля из 10 символов ' ', с размером 10 будет проще работать + есть начальные значения массива
    private static char[] createField() {
        return "          ".toCharArray();
    }

    /**
     * Метод для вывода поля на экран.
     * Нумерация клеток соответствует NumPad, так их и выводим.
     */
    private static void draw(char[] field) {
        System.out.println("┌-┬-┬-┐");
        System.out.println("|" + field[7] + "|" + field[8] + "|" + field[9] + "|");
        System.out.println("├-┼-┼-┤");
        System.out.println("|" + field[4] + "|" + field[5] + "|" + field[6] + "|");
        System.out.println("├-┼-┼-┤");
        System.out.println("|" + field[1] + "|" + field[2] + "|" + field[3] + "|");
        System.out.println("└-┴-┴-┘");
    }

    /**
     * Метод "хода" с проверками, что ход осуществляется внутрь поля и что выбранное поле еще не занято
     */
    private static void move(char[] field, int num, char item) throws WrongMoveException {
        if (num < 1 || num > 9 || field[num] != ' ') {
            throw new WrongMoveException();
        }
        field[num] = item;
    }

    private static boolean isWin(char[] field) {
        for (int[] check : CHECKS) {
            if (field[check[0]] == field[check[1]] && field[check[0]] == field[check[2]] && field[check[0]] != ' ') {
                return true;
            }
        }
        return false;
    }

    /**
     * Исключение, которое возникает при вводе неправильного хода
     */
    public static class WrongMoveException extends Exception {
        public WrongMoveException() {
            super("Wrong move!");
        }
    }
}