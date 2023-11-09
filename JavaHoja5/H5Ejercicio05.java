import java.util.Scanner;

public class Hoja5_4 {
    public static void main (String[] args) {

        System.out.print("introduce la cantidad de filas: ");

        Scanner sc = new Scanner (System.in);
        int n = sc.nextInt();

        sc.close();

        int numberToPrint = 1;
        int spaces = 0;

        for (int filas = 1; filas <= n; filas++) {
            
            numberToPrint = filas % 10;
             
            for (spaces = 1; spaces <= n - filas; spaces++) {
                System.out.print("  ");
            }

            for (int numbers = 1; numbers <= (filas * 2) - 1; numbers++) {
                System.out.print(numberToPrint + " ");
                if (n < (spaces + numbers)) numberToPrint--;
                else numberToPrint++;

                if (numberToPrint < 0) numberToPrint = 9;
                else if (numberToPrint > 9) numberToPrint = 0;
            }

            System.out.println();
        }
    }
}
