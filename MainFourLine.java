package FourLine;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainFourLine {
    private static String[][] tablero = new String[6][7];

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        play();
    }
    public static void play(){
        System.out.println("--- Connect 4 ---");
        fillTablero();
        boolean turno = true;
        int col = 0;
        do {
            mostrarTablero();
            if(turno){
                do {
                    try {
                        System.out.println("Jugador 1 - Escoga una fila");
                        col = sc.nextInt();
                        if (!(col > 0 && col < 8) || !validColumn(col)) {
                            System.out.println("Columna no valida, escoja otra por favor");
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Introduzca un numero del 1 al 7!!");
                        sc.nextLine();
                    }
                }while(!(col > 0 && col < 8) || !validColumn(col));
            }else{
                do {
                    try{
                        System.out.println("Jugador 2 - Escoga una fila");
                        col = sc.nextInt();
                        if(!(col > 0 && col < 8) || !validColumn(col)){
                            System.out.println("Columna no valida, escoja otra por favor");
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Introduzca un numero del 1 al 7!!");
                        sc.nextLine();
                    }
                }while(!(col > 0 && col < 8) || !validColumn(col));
            }
            addFicha((turno ? "X" : "O"), col);

            turno = !turno;
        } while (!tableroLleno() && !victoria("X") && !victoria("O"));
        mostrarTablero();
        if(victoria("X")){
            System.out.println("Enhorabuena Jugador 1");
        }else if(victoria("O")){
            System.out.println("Enhorabuena Jugador 2");
        }else{
            System.out.println("EMPATE");
        }
    }

    public static void fillTablero(){
        for (String[] strings : tablero) {
            Arrays.fill(strings, ".");
        }
    }

    public static void mostrarTablero(){
        System.out.println("    1   2   3   4   5   6   7   ");
        System.out.println("+--- --- --- --- --- --- --- ---+");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("|   ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + "   ");
            }
            System.out.println("|");
        }
        System.out.println("+-------------------------------+");
        // System.out.println("+---.---.---.---.---.---.---.---+");
    }

    public static boolean validColumn(int col){
        for (int i = tablero.length-1; i >= 0; i--) {
            if(tablero[i][col-1].equals(".")){
                return true;
            }
        }
        return false;
    }

    public static void addFicha(String player, int col){
        for (int i = tablero.length-1; i >= 0; i--) {
            if(tablero[i][col-1].equals(".")){
                tablero[i][col-1] = player;
                return;
            }
        }
    }

    public static boolean tableroLleno(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if(tablero[i][j].equals(".")) return false;
            }
        }
        return true;
    }

    public static boolean victoria(String player){
        // Horizontal Check
        for (int j = 0; j < tablero[0].length -3; j++) {
            for (int i = 0; i < tablero.length; i++) {
                if(tablero[i][j].equals(player) && tablero[i][j+1].equals(player) && tablero[i][j+2].equals(player) && tablero[i][j+3].equals(player)){
                    return true;
                }
            }
        }

        //Vertical Check
        for (int i = 0; i < tablero.length-3; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if(tablero[i][j].equals(player) && tablero[i+1][j].equals(player) && tablero[i+2][j].equals(player) && tablero[i+3][j].equals(player)){
                    return true;
                }
            }
        }

        //Ascending Diagonal Check
        for (int i = 3; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length-3; j++) {
                if(tablero[i][j].equals(player) && tablero[i-1][j+1].equals(player) && tablero[i-2][j+2].equals(player) && tablero[i-3][j+3].equals(player)){
                    return true;
                }
            }
        }

        //Descending Diagonal Check
        for (int i = 3; i < tablero.length; i++) {
            for (int j = 3; j < tablero[i].length; j++) {
                if(tablero[i][j].equals(player) && tablero[i-1][j-1].equals(player) && tablero[i-2][j-2].equals(player) && tablero[i-3][j-3].equals(player)){
                    return true;
                }
            }
        }
        return false;
    }
}
