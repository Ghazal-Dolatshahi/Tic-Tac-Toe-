import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * <h2><span style="font-style:italic ; color: #778899 "> Tic-Tac-Toe game !!</h2>
 @author <span style="font-style:italic ; color:#E9967A"><b>Ghazal Dolatshahi</b></span><hr>
  * <span style="color:#E9967A">Email:</span>
  * <span style="font-style:italic ; color: #778899 ">  <a href="mailto:ghazalldolatshahi.p@gmail.com">(ghazalldolatshahi.p@gmail.com)</a><hr>
  * @since <span style="font-style:italic ; color:#E9967A">2023-03-08</span>
 */
public class Main {

    public static void main(String[] args) {
        menu();

    }

    public static void menu() {

        boolean bool = true;
        while (bool) {
            int command = printMenu();
            switch (command) {

                case 1:
                    System.out.print("\033[H\033[2J");
                    twoPlayerGame();
                    restartGame(command);
                    bool = false;
                    break;

                case 2:
                    System.out.print("\033[H\033[2J");
                    computerGame();
                    restartGame(command);
                    bool = false;
                    break;

                case 3:
                    bool = false;
                    break;

                default:
                    System.out.print("\033[H\033[2J");
                    System.out.println("\033[1mInvalid number :( \u001b[0m\n please try again\n");
            }
        }
    }

    /**
     * print and show the start menu.
     * @return number that player has selected.
     */
    public static int printMenu() {

        Scanner input = new Scanner(System.in);
        System.out.println("\033[91mWelcome to TIC_TAC_TOE game ☺ \u001b[0m\n");
        System.out.println("1-play with other players");
        System.out.println("2-play with computer");
        System.out.println("3-Exit");
        System.out.println("\nPlease enter your command :");
        return input.nextInt();
    }

    /**
     * show the new board after each selection of players.
     * @param array1
     *              has the random cell numbers
     * @param matrix1
     *              it is used for printing the board.
     * @param matrix2
     *              it is used for calculation and comparing the numbers.
     * @param player1
     *              the number that selected by the first player.
     * @param player2
     *              the number that selected by the second player.
     */
    public static void showBoard(int[] array1 , String[][] matrix1 , int[][] matrix2 , int player1 , int player2){

        System.out.println("  \033[91m   << TIC_TAC_TOE >>      \u001b[0m    ");
        System.out.println("-------------------------------");

        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                if ((matrix2[j][k] == array1[0]) || matrix2[j][k] == array1[1] || matrix2[j][k] == array1[2])
                    matrix1[j][k] = "\033[91mL\u001b[0m";

                if (matrix2[j][k] == player1)
                    matrix1[j][k] = "\u001b[33mX\u001b[0m";

                if (matrix2[j][k] == player2)
                    matrix1[j][k] = "\033[95mO\u001b[0m";
                System.out.print( matrix1[j][k]);
                System.out.print("\t|\t");
            }
            System.out.println("\n-------------------------------");

        }
    }

    /**
     *  swap two cells of array.
     * @param array include the numbers one to sixteen.
     * @param i index of array.
     * @param change the random index of array.
     */
    public static void swap(int[] array, int i, int change) {

        int temp = array[i];
        array[i] = array[change];
        array[change] = temp;
    }

    /**
     * choose the random number from zero to fifteen (the indexes of array).
     * @param array include numbers one to sixteen.
     */
    public static void random(int[] array) {
        int length = array.length;
        Random random = new Random();
        random.nextInt();

        for (int i = 0; i < 16; i++) {
            int change = i + random.nextInt(length - i);
            swap(array, i, change);
        }
    }

    /**
     * show two choices between replaying or ending the program.
     * @param command the number that selected in the start menu by the player.
     */
    public static void restartGame(int command){
        Scanner input = new Scanner(System.in);

        boolean bool2 = true;
        while (bool2) {
            System.out.println("\nDo you want to play a gain ?");
            System.out.println(" 1-YES \n 2-NO");
            int command2 = input.nextInt();
            System.out.print("\033[H\033[2J");

            if(command2 == 1) {
                System.out.println("Please enter \033[91m'R'\u001b[0m character on your keyboard to start again");
                String replay = input.next();
                if (Objects.equals(replay, "R")) {
                    System.out.print("\033[H\033[2J");
                    if (command == 1) {
                        twoPlayerGame();

                    }else if(command == 2) {
                        computerGame();
                    }
                }
            }

            if (command2 == 2) {
                bool2 = false;
            }

            if (command2 != 2 && command2 != 1) {
                System.out.println("\033[1mInvalid number :( \u001b[0m\nPlease try again :");
            }
        }
    }

    /**
     * based on the location that selected by the players,
     * checks whether the player has won or not :)
     * @param k
     *          the column of the cell that selected by the player.
     * @param j
     *          the row of the cell that selected by the player.
     * @param matrix1
     *          it is used for comparing the characters in the board.
     * @return
     *          true if the player has won, false if the player hasn't won.
     */
    public static boolean winner(int k, int j, String[][] matrix1) {

        int row = j;
        int count = 0;
        int column = k;

        while (row > 0) {
            row--;
            if (matrix1[row][k] == matrix1[j][k]) {
                count++;
            } else
                break;
        }

        column = k;
        row = j;

        while (row < 3) {
            row++;
            if (matrix1[row][k] == matrix1[j][k]) {
                count++;
            } else
                break;
        }
        if (count >= 2) {
            return (true);
        }

        count = 0;
        column = k;
        row = j;

        while (column > 0 ) {
            column--;
            if (matrix1[j][column] == matrix1[j][k]) {
                count++;
            } else
                break;
        }
        column = k;
        row = j;
        while (column < 3) {
            column++;
            if (matrix1[j][column] == matrix1[j][k]) {
                count++;
            } else
                break;
        }
        if (count >= 2) {
            return (true);
        }

        count = 0;
        column = k;
        row = j;

        while (row < 3 && column > 0) {
            column--;
            row++;
            if (matrix1[row][column] == matrix1[j][k]) {
                count++;
            } else
                break;
        }
        column = k;
        row = j;

        while (column < 3 && row > 0) {
            row--;
            column++;
            if (matrix1[row][column] == matrix1[j][k]) {
                count++;
            } else
                break;
        }
        if (count >= 2) {
            return (true);
        }

        count = 0;
        column = k;
        row = j;

        while (row < 3 && column < 3 ) {
            column++;
            row++;
            if (matrix1[row][column] == matrix1[j][k]) {
                count++;
            } else
                break;
        }

        column = k;
        row = j;
        while (column > 0 && row > 0 ) {
            row--;
            column--;
            if (matrix1[row][column] == matrix1[j][k]) {
                count++;
            } else
                break;
        }
        if (count >= 2) {
            return (true);
        }
        return (false);
    }

    public static void twoPlayerGame() {
        Scanner input = new Scanner(System.in);
        String[][] matrix1 = new String[][]{{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
        int[][] matrix2 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[] array1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        random(array1);
        int player2 = 0 ;
        int player1 = 0 ;
        showBoard(array1, matrix1 , matrix2 , player1 , player2);
        int x = array1[0];
        int y = array1[1];
        int z = array1[2];
        int[] array2 = new int[16];
        array2[0] = x;
        array2[1] = y;
        array2[2] = z;
        int count = 0;
        int temp = 3;
        boolean endgame = false ;

        for (int i = 0; i < 8; i++) {
            System.out.println("\u001b[33mfirst player >> \u001b[0mplease choose a cell number :");
            player1 = input.nextInt();
            count++;

            for (int j = 0; j < 16; j++) {
                if (player1 == array2[j] || player1 > 16) {
                    System.out.println("\u001b[33mfirst player >>\u001b[0m please choose a cell number again :");
                    player1 = input.nextInt();
                    j = 0;
                }
            }
            System.out.print("\033[H\033[2J");
            showBoard(array1, matrix1 , matrix2 , player1 , player2);

            for(int j = 0 ; j < 4 ; j++){
                for (int k = 0 ; k < 4 ; k++){
                    if(player1 == matrix2[j][k]){
                        if(winner(k, j, matrix1)){
                            System.out.println("\u001b[33mCongratulation !!!!\nThe first player has won ;)\u001b[0m");
                            endgame = true ;
                        }
                    }
                }
            }

            if(endgame){
                break;
            }

            array2[temp] = player1;

            if (count == 13) {
                System.out.println(" \033[91moops!!!!\nplayers have the same score :(\u001b[0m");
                break;
            }

            temp++;
            System.out.println("\033[95msecond player >> \u001b[0mplease choose a cell number :");
            player2 = input.nextInt();
            count++;

            for (int j = 0; j < 16; j++) {
                if (player2 == array2[j] || player2 > 16) {
                    System.out.println("\033[95msecond player >> \u001b[0mplease choose a cell number again :");
                    player2 = input.nextInt();
                    j = 0;
                }
            }

            System.out.print("\033[H\033[2j");
            showBoard(array1, matrix1 , matrix2 , player1 , player2);

            for(int j = 0 ; j < 4 ; j++){
                for (int k = 0 ; k < 4 ; k++){
                    if(player2 == matrix2[j][k]){
                        if(winner(k,j,matrix1)){
                            System.out.println("\033[95mCongratulation !!!!\nThe second player has won ;)\u001b[0m");
                            endgame = true ;
                        }
                    }
                }
            }

            if(endgame){
                break;
            }

            array2[temp] = player2;
            temp++;

        }
    }

    public static void computerGame(){
        Scanner input = new Scanner(System.in);
        String[][] matrix1 = new String[][]{{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
        int[][] matrix2 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[] array1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        random(array1);
        int player2 = 0 ;
        int player1 = 0 ;
        showBoard(array1, matrix1 , matrix2 , player1 , player2);
        int x = array1[0];
        int y = array1[1];
        int z = array1[2];
        int[] array2 = new int[16];
        array2[0] = x;
        array2[1] = y;
        array2[2] = z;
        int count = 0;
        int temp = 3;
        int count2 = 3;
        boolean endgame = false ;

        for (int i = 0; i < 8; i++) {
            System.out.println("\u001b[33mHuman >>\u001b[0m please choose a cell number :");
            player1 = input.nextInt();
            count++;

            for (int j = 0; j < 16; j++) {
                if (player1 == array2[j] || player1 > 16) {
                    System.out.println("\u001b[33mHuman >>\u001b[0m please choose a cell number again :");
                    player1 = input.nextInt();
                    j = 0;
                }
            }
            System.out.print("\033[H\033[2J");
            showBoard(array1, matrix1 , matrix2 , player1 , player2);

            for(int j = 0 ; j < 4 ; j++){
                for (int k = 0 ; k < 4 ; k++){
                    if(player1 == matrix2[j][k]){
                        if(winner(k, j, matrix1)){
                            System.out.println("\u001b[33mCongratulation !!!!\nThe Human has won ;)\u001b[0m");
                            endgame = true ;
                        }
                    }
                }
            }

            if(endgame){
                break;
            }

            array2[temp] = player1;

            if (count == 13) {
                System.out.println(" \033[91moops!!!!\nhuman and computer have the same score :(\u001b[0m");
                break;
            }

            temp++;
            System.out.println("************************************");
            player2 = array1[count2];
            count++;

            for (int j = 0; j < 16; j++) {
                if (player2 == array2[j]) {
                    count2++;
                    player2 = array1[count2];
                    j = 0;
                }
            }
            System.out.print("\033[H\033[2J");
            showBoard(array1, matrix1 , matrix2 , player1 , player2);

            for(int j = 0 ; j < 4 ; j++){
                for (int k = 0 ; k < 4 ; k++){
                    if(player2 == matrix2[j][k]){
                        if(winner(k,j,matrix1)){
                            System.out.println("\033[95mCongratulation !!!!\nThe computer has won ;)\u001b[0m");
                            endgame = true ;
                        }
                    }
                }
            }

            if(endgame){
                break;
            }

            array2[temp] = player2;
            temp++;

        }
    }
}