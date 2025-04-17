public class Solver {
    int nbLine, nbColumn;
    Fraction[][] P;
    Fraction[] B;
    Fraction[] Solution;
    Fraction key_value;
    int key_line, key_column;

    public Solver(int nbLine, int nbColumn) {
        this.nbLine = nbLine;
        this.nbColumn = nbColumn;
        P = new Fraction[nbLine][nbColumn];
        B = new Fraction[nbLine];
        Solution = new Fraction[nbColumn];
    }

    public void solve() {
        MatrixUtils.reduceToEchelonForm(this);
        
        // Vérifie s’il existe une contradiction
        for (int i = 0; i < nbLine; i++) {
            boolean allZero = true;
            for (int j = 0; j < nbColumn; j++) {
                if (!P[i][j].isZero()) {
                    allZero = false;
                    break;
                }
            }
            if (allZero && !B[i].isZero()) {
                System.out.println("Système incompatible !");
                return;
            }
        }

        MatrixUtils.reorderRows(this);
        System.out.println("Matrice réordonnée (lignes nulles en bas) :");
        MatrixUtils.printAugmentedMatrix(this);

        for (int i = 0; i < nbColumn; i++) {
            Solution[i] = B[i];
        }
    }

    public void printSolution() {
        for (int i = 0; i < Solution.length; i++) {
            System.out.println("X[" + i + "] = " + (Solution[i] != null ? Solution[i] : "Indéterminé"));
        }
    }
}
