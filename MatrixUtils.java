public class MatrixUtils {
    public static void printAugmentedMatrix(Solver solver) {
        System.out.println("Matrice augmentée (P | B) :");
        for (int i = 0; i < solver.nbLine; i++) {
            System.out.print("| ");
            for (int j = 0; j < solver.nbColumn; j++) {
                System.out.print(solver.P[i][j] + " ");
            }
            System.out.print("| " + solver.B[i] + " |");
            System.out.println();
        }
        System.out.println();
    }

    public static void elimination(Solver solver, int Lpivot, int Cpivot) {
        Fraction pivot = solver.P[Lpivot][Cpivot];
        solver.P[Lpivot][Cpivot] = pivot.inverse();
        solver.B[Lpivot] = solver.B[Lpivot].multiply(pivot.inverse());

        for (int k = Lpivot + 1; k < solver.nbLine; k++) {
            Fraction coeff = solver.P[k][Cpivot];
            for (int column = 0; column < solver.nbColumn; column++) {
                solver.P[k][column] = solver.P[k][column].subtract(coeff.multiply(solver.P[Lpivot][column]));
            }
            solver.B[k] = solver.B[k].subtract(coeff.multiply(solver.B[Lpivot]));
        }

        for (int x = Lpivot - 1; x >= 0; x--) {
            Fraction coeff = solver.P[x][Cpivot];
            for (int col = 0; col < solver.nbColumn; col++) {
                solver.P[x][col] = solver.P[x][col].subtract(coeff.multiply(solver.P[Lpivot][col]));
            }
            solver.B[x] = solver.B[x].subtract(coeff.multiply(solver.B[Lpivot]));
        }

        printAugmentedMatrix(solver);
    }

    public static void reduceToEchelonForm(Solver solver) {
        for (int l = 0; l < solver.nbLine; l++) {
            for (int c = 0; c < solver.nbColumn; c++) {
                if (!solver.P[l][c].isZero()) {
                    int countZero = 0;
                    for (int j = l; j < solver.nbLine; j++) {
                        if (solver.P[j][c].isZero()) countZero++;
                    }

                    if (countZero != (solver.nbLine - l)) {
                        Fraction pivot = solver.P[l][c];
                        for (int col = 0; col < solver.nbColumn; col++) {
                            solver.P[l][col] = solver.P[l][col].divide(pivot);
                        }
                        solver.B[l] = solver.B[l].divide(pivot);
                        elimination(solver, l, c);
                        break;
                    }
                }
            }
        }
    }

    public static void reorderRows(Solver solver) {
        for (int i = 0; i < solver.nbLine - 1; i++) {
            for (int j = 0; j < solver.nbLine - i - 1; j++) {
                boolean rowJZero = isRowZero(solver.P[j]);
                boolean rowJ1Zero = isRowZero(solver.P[j + 1]);

                if (rowJZero && !rowJ1Zero) {
                    Fraction[] tempRow = solver.P[j];
                    solver.P[j] = solver.P[j + 1];
                    solver.P[j + 1] = tempRow;

                    Fraction tempB = solver.B[j];
                    solver.B[j] = solver.B[j + 1];
                    solver.B[j + 1] = tempB;
                }
            }
        }
    }

    private static boolean isRowZero(Fraction[] row) {
        for (Fraction f : row) {
            if (!f.isZero()) return false;
        }
        return true;
    }
}
