import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrer le nombre de lignes et de colonnes
        System.out.print("Nombre de lignes : ");
        int lignes = scanner.nextInt();

        System.out.print("Nombre de colonnes : ");
        int colonnes = scanner.nextInt();

        Solver solver = new Solver(lignes, colonnes);

        // Entrer les coefficients de la matrice P
        System.out.println("Entrez les coefficients de la matrice P (forme a/b) :");
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                System.out.print("P[" + i + "][" + j + "] = ");
                String fractionStr = scanner.next();
                solver.P[i][j] = parseFraction(fractionStr);
            }
        }

        // Entrer les valeurs du second membre B
        System.out.println("Entrez les valeurs du second membre B (forme a/b) :");
        for (int i = 0; i < lignes; i++) {
            System.out.print("B[" + i + "] = ");
            String fractionStr = scanner.next();
            solver.B[i] = parseFraction(fractionStr);
        }

        // Résoudre
        solver.solve();
        solver.printSolution();
    }

    // Convertit une chaîne "a/b" ou "a" en Fraction
    private static Fraction parseFraction(String input) {
        if (input.contains("/")) {
            String[] parts = input.split("/");
            int num = Integer.parseInt(parts[0]);
            int den = Integer.parseInt(parts[1]);
            return new Fraction(num, den);
        } else {
            return new Fraction(Integer.parseInt(input), 1);
        }
    }
}
