import java.util.Random;
import java.util.Scanner;

public class Main {

    private static char[][] map;
    private final static int MAP_SIZE = 3;
    private final static int DOT_COUNT_TO_WIN = 3;
    private final static char DOT_X = 'X';
    private final static char DOT_O = 'O';
    private final static char DOT_EMPTY = '•';
    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();


    public static void main(String[] args) {

        field();
        view();
        while(true) {
            humanTurn();
            view();
            if (checkWin(DOT_X)){
                System.out.println("YOU WIN");
                break;
            }
            if (checkDraw()){
                System.out.println("DRAW");
                break;
            }
            computerTurn();
            view();
            if (checkWin(DOT_O)){
                System.out.println("COMPUTER WIN");
                break;
            }
            if (checkDraw()){
                System.out.println("DRAW");
                break;
            }
        }

    }

    private static boolean checkWin(char dot) {
        int count = 0;
        int column = 0;
        int diagonal = 0;

        // check row
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == dot) {
                    count++;
                }
                if (count == 3) {
                    return true;
                }

            }
            count = 0;

        }

        //check column
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if ( i >= 1 && map[i][j] == dot && map[i - 1][j] == dot){
                    column++;
                }
                if (column == 2){
                    return true;
                }

            }
        }
        // check diagonals
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == dot) {

                    if (i == j || i + j == 2) {
                        diagonal++;
                    }
                    if (diagonal == 3) {
                        return true;

                    }
                }

            }

        }

        return false;
    }




    private static boolean checkDraw() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }

            }

        }
        return true;
    }


        private static void computerTurn() {
        int x, y;
        do {
            System.out.println("Computer turn");
            x = random.nextInt(MAP_SIZE);
            y = random.nextInt(MAP_SIZE);
        } while(!cellValidation(x + 1, y + 1, DOT_O));
        map[x][y] = DOT_O;
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Human turn");
            while (true) {
                System.out.println("Please input coordinates in format 'x y'");
                if (sc.hasNextInt()) {
                    x = sc.nextInt();
                } else {
                    System.out.println("You input wrong X coordinate");
                    sc.hasNextLine();
                    continue;
                }
                if (sc.hasNextInt()) {
                    y = sc.nextInt();
                    break;
                } else {
                    System.out.println("You input wrong Y coordinate");
                    sc.hasNextLine();
                }
            }
        } while (!cellValidation(x, y, DOT_X)) ;
        map[x -  1][y - 1] = DOT_X;
    }

    private static boolean cellValidation ( int x, int y, char dot){
            if (x < 1 || x > MAP_SIZE || y < 1 || y > MAP_SIZE) {
                System.out.println("Exit field sizes");
                return false;
            }
            boolean check =  map[x - 1][y - 1] == DOT_EMPTY;

            if (check){
                return check;
            } else {
                System.out.println("Cell is busy");
                return false;
            }
        }

        private static void view () {
            System.out.print("  ");
            for (int i = 0; i < map.length; i++) {
                System.out.print((i + 1) + " ");
            }
            System.out.println();


            for (int i = 0; i < map.length; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < map[i].length; j++) {

                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }

        private static void field() {
            map = new char[MAP_SIZE][MAP_SIZE];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = DOT_EMPTY;
                }
            }
    }
}
