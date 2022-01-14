/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioexamen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ordenador
 */
public class EjercicioExamen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce filas");
        int n = sc.nextInt();
        System.out.println("Introduce Columnas");
        int m = sc.nextInt();

        int matriz[][] = new int[n][m];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.println("Introduce elemento " + i + " " + j);
                matriz[i][j] = sc.nextInt();
            }
        }
        System.out.println("Array");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        int vector[] = new int[matriz.length * matriz[0].length];
        int posv = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] % 7 == 0) {
                    vector[posv++] = matriz[i][j];
                }
            }
        }
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + " ");
        }

    }

    private static void juego() {
//        System.out.println("Introduce filas");
//        int n = sc.nextInt();
//        System.out.println("Introduce Columnas");
//        int m = sc.nextInt();
//        int jugadas = m;
        Scanner sc = new Scanner(System.in);
        int n = 6;
        int m = 5;
        int mover;
        int jugadas = m;
        String[][] tablero = new String[n][m];

        rellenarTablero(tablero);
        System.out.println("Introduce posicion inicial");
        int posi = sc.nextInt();
        tablero[0][posi] = "O";
        int posicionA[] = {0, posi};
        boolean fin = false;
        do {
            posicionA[0]++;
            try {

                if (tablero[posicionA[0]][posicionA[1]] == "F") {
                    tablero[posicionA[0]][posicionA[1]] = "O";
                    mostrarTablero(tablero);
                    fin = true;
                    System.out.println("Juego terminado ha ganado");
                } else if (tablero[posicionA[0]][posicionA[1]] == "X") {
                    mostrarTablero(tablero);
                    System.out.println("Mueve la bola derecha:2; Izquierda:1");
                    do {
                        mover = sc.nextInt();
                    } while (mover != 1 && mover != 2);
                    switch (mover) {
                        case 1:
                            posicionA[1]--;
                            posicionA[0]--;
                            break;

                        case 2:
                            posicionA[0]--;
                            posicionA[1]++;
                            break;
                    }
                    mostrarTablero(tablero);
                }

                tablero[posicionA[0]][posicionA[1]] = "O";
            } catch (ArrayIndexOutOfBoundsException ex) {
                mostrarTablero(tablero);
                System.out.println("Juego terminado ha perdido");
                fin = true;
            }
        } while (jugadas > 0 && !fin);
    }

    private static void mostrarTablero(String tablero[][]) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    private static void rellenarTablero(String tablero[][]) {
        File f = new File("./ocupadas.txt");
        int posicionF;
        int posicionC;
        String linea;
        try {
            Scanner sc = new Scanner(f);
            do {
                posicionF = sc.nextInt();
                posicionC = sc.nextInt();
                tablero[posicionF][posicionC] = "X";
            } while (sc.hasNextInt());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EjercicioExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != "X") {
                    tablero[i][j] = ".";
                }
            }
        }
        tablero[tablero.length - 1][tablero[0].length / 2] = "F";
    }
}
