/*
    Supongamos que una determinada colección consta de 225 cromos que se venden en sobres
    conteniendo cada uno 6 cromos distintos entre sí. Elaborar un programa que simule la compra
    de sobres mostrando el estado de la colección en cada momento y finalmente el número de
    sobres que han sido necesarios para completar la colección una vez conseguida.
*/

import java.util.Scanner;

public class H7Ejercicio04 {

    public static void dibujo1(boolean[] vector, int pagina) {
        int cont = pagina * 25;
        int aux;
        for (int filas = 0; filas < 5; filas++) {
            aux = cont;

            // dibujo de X o O

            for (int colum = 0; colum < 5; colum++) {

                if (!vector[cont]) {
                    System.out.print("O     ");
                } else {
                    System.out.print("X     ");
                }

                cont++;
            }

            System.out.println();

            cont = aux;

            for (int colum = 0; colum < 5; colum++) {

                System.out.print(cont + 1);

                for (int spaces = 0; spaces < 5 - ("" + cont).length() + 1; spaces++) {
                    System.out.print(" ");
                }

                cont++;

            }

            System.out.println("\n");

        }

    }

    public static void dibujarPagina(boolean[] vector, int pagina) {
        dibujo1(vector, pagina);
        System.out.println("PAGINA " + (pagina + 1) + " / 9");
        System.out.println("d -> siguiente pagina | a -> pagina anterior | s -> salir del programa | c -> comprar sobre");
    }

    public static boolean[] comprarSobre(boolean[] vector) {

        final int cromosPorSobre = 6;
        
        int cromo;
        int[] sobre = new int[6]; // {2, 0, 0, 0, 0}

        for (int x = 0; x < sobre.length; x++) {
            sobre[x] = -1;
        }
        
        boolean contieneCromo;

        for (int i = 0; i < cromosPorSobre; i++) {
            do {
                contieneCromo = false;

                cromo = (int) (Math.random() * 225); // 0 - 224

                for (int j = 0; j < cromosPorSobre && !contieneCromo; j++) {
                    if (sobre[j] == cromo) contieneCromo = true;
                }

            } while (contieneCromo);

            vector[cromo] = true;
            System.out.print("Te ha tocado el cromo " + (cromo + 1) + "\n");
        }

        
        return vector;
    }

    
    public static boolean comprobarAlbum(boolean[] vector) {
        boolean albumCompleto = true;
        
        for (int i = 0; i < vector.length && albumCompleto; i++) {
            if (!vector[i]) {
                albumCompleto = false;
            }
        }

        return albumCompleto;
    }
    // main que pide dos vectores de n elementos
    public static void main(String[] args) {

        boolean[] vector = new boolean[225];
        for (int x = 0; x < 225; x++) {
            vector[x] = false;
        }

        Scanner in = new Scanner(System.in);

        int pagina = 0;
        char decisionUsuario = ' ';
        boolean albumCompleto = false;
        while (decisionUsuario != 's' && !albumCompleto) {
            
            dibujarPagina(vector, pagina);
            
            try {
                decisionUsuario = in.nextLine().charAt(0);
            } catch (Exception e) {
                decisionUsuario = ' ';
            }

            switch (decisionUsuario) {
                case 'd':
                    pagina = (pagina >= 8) ? 0 : pagina + 1;
                    break;
                case 'a':
                    pagina = (pagina <= 0) ? 8 : pagina - 1;
                    break;
                case 'c': 
                    vector = comprarSobre(vector);
                    break;
                case 's':
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("*********************");
                    System.out.println("Opcion no valida");
                    System.out.println("*********************\n");
                    break;
            }
            
            albumCompleto = comprobarAlbum(vector);
            
            if (albumCompleto) {
                System.out.println("Enhorabuena, has completado el album");
            }
            
        }

        in.close();

    }
}
