public class Main {
    public static void main(String[] args) {
        Solver solver = new Solver(3, 3); // Exemple : système 3x3

        // Initialiser P (matrice)
        solver.P[0][0] = new Fraction(2, 1);
        solver.P[0][1] = new Fraction(1, 1);
        solver.P[0][2] = new Fraction(-1, 1);
        solver.P[1][0] = new Fraction(-3, 1);
        solver.P[1][1] = new Fraction(-1, 1);
        solver.P[1][2] = new Fraction(2, 1);
        solver.P[2][0] = new Fraction(-2, 1);
        solver.P[2][1] = new Fraction(1, 1);
        solver.P[2][2] = new Fraction(2, 1);

        // Initialiser B
        solver.B[0] = new Fraction(8, 1);
        solver.B[1] = new Fraction(-11, 1);
        solver.B[2] = new Fraction(-3, 1);

        // Résoudre
        solver.solve();
        solver.printSolution();
    }
}
