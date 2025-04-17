public class Solver {
    int nbLine, nbColumn;
    Fraction[][] P;
    Fraction[] B;
    Fraction[] Solution;
    Fraction Pivot;
    int key_line, key_column;
    Fraction key_value;

    public Solver(int nbLine, int nbColumn) {
        this.nbLine = nbLine;
        this.nbColumn = nbColumn;
        P = new Fraction[nbLine][nbColumn];
        B = new Fraction[nbLine];
        Solution = new Fraction[nbColumn];
    }

    public void printAugmentedMatrix() {
        System.out.println("Matrice augmentée (P | B) :");
        for (int i = 0; i < nbLine; i++) {
            System.out.print("| ");
            for (int j = 0; j < nbColumn; j++) {
                System.out.print(P[i][j] + " ");
            }
            System.out.print("| " + B[i] + " |");
            System.out.println();
        }
        System.out.println();
    }

    public void elimination(int Lpivot, int Cpivot) {
        // Mise à l’échelle du pivot
        Fraction pivot = P[Lpivot][Cpivot];
        P[Lpivot][Cpivot] = pivot.inverse();
        B[Lpivot] = B[Lpivot].multiply(pivot.inverse());

        // Élimination vers le bas
        for (int k = Lpivot + 1; k < nbLine; k++) {
            Fraction coeff = P[k][Cpivot];
            for (int column = 0; column < nbColumn; column++) {
                P[k][column] = P[k][column].subtract(coeff.multiply(P[Lpivot][column]));
            }
            B[k] = B[k].subtract(coeff.multiply(B[Lpivot]));
        }

        // Élimination vers le haut
        for (int x = Lpivot - 1; x >= 0; x--) {
            Fraction coeff = P[x][Cpivot];
            for (int col = 0; col < nbColumn; col++) {
                P[x][col] = P[x][col].subtract(coeff.multiply(P[Lpivot][col]));
            }
            B[x] = B[x].subtract(coeff.multiply(B[Lpivot]));
        }

        printAugmentedMatrix();
    }

    public void reduceToEchelonForm() {
        for (int l = 0; l < nbLine; l++) {
            for (int c = 0; c < nbColumn; c++) {
                if (!P[l][c].isZero()) {
                    int countZero = 0;
                    for (int j = l; j < nbLine; j++) {
                        if (P[j][c].isZero()) {
                            countZero++;
                        }
                    }

                    if (countZero != (nbLine - l)) {
                        // Normaliser la ligne du pivot
                        Fraction pivot = P[l][c];
                        for (int col = 0; col < nbColumn; col++) {
                            P[l][col] = P[l][col].divide(pivot);
                        }
                        B[l] = B[l].divide(pivot);

                        // Appliquer élimination
                        elimination(l, c);
                        break;
                    }
                }
            }
        }
    }

    public boolean verify_value_key() {
        for (int i = 0; i < nbLine; i++) {
            int conteur = 0;
            int jValid = -1;
            for (int j = 0; j < nbColumn; j++) {
                if (P[i][j].isZero()) conteur++;
                else jValid = j;
            }
            if (conteur == nbColumn - 1) {
                key_value = P[i][jValid];
                key_line = i;
                key_column = jValid;
                return true;
            }
        }
        return false;
    }

    public void fillSolution() {
        Solution[key_column] = B[key_column];

        // Réduire les lignes suivantes
        for (int Li = key_line + 1; Li < nbLine; Li++) {
            P[Li][key_column] = P[Li][key_column].subtract(P[Li][key_column]);
            B[Li] = P[Li][key_column].multiply(new Fraction(-1, 1));
        }

        // Réduire les lignes précédentes
        for (int li = key_line - 1; li >= 0; li--) {
            P[li][key_column] = P[li][key_column].subtract(P[li][key_column]);
            B[li] = P[li][key_column].multiply(new Fraction(-1, 1));
        }
    }

    public void solve() {
        reduceToEchelonForm();
    
        // Vérifie s’il existe une contradiction (0 = b ≠ 0)
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
    
        // Sinon, lecture directe de la solution :
        for (int i = 0; i < nbColumn; i++) {
            Solution[i] = B[i];
        }
    
        // printSolution();
    }    

    public void printSolution() {
        for (int i = 0; i < Solution.length; i++) {
            System.out.println("X[" + i + "] = " + (Solution[i] != null ? Solution[i] : "Indéterminé"));
        }
    }
}
