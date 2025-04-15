public class FractionApp {
    public static void main(String[] args) {
        // Créons quelques fractions
        Fraction f1 = new Fraction(1, 2);   // 1/2
        Fraction f2 = new Fraction(3, 4);   // 3/4
        Fraction f3 = new Fraction(2, 4);   // 2/4 → doit être simplifié en 1/2
        Fraction f4 = new Fraction(3);

        // Affichons-les
        System.out.println("f1 = " + f1);
        System.out.println("f2 = " + f2);
        System.out.println("f3 = " + f3);
        System.out.println("f4 = " + f4);

        // Addition
        Fraction sum = f1.add(f2);
        System.out.println("f1 + f2 = " + sum);

        // Soustraction
        Fraction diff = f2.subtract(f1);
        System.out.println("f2 - f1 = " + diff);

        // Addition avec mêmes dénominateurs
        Fraction sumSameDen = f1.add(f3);
        System.out.println("f1 + f3 = " + sumSameDen);

        // Addition avec un entier
        System.out.println("Here I am:");
        Fraction sumWithEnter = f2.add(f4);
        System.out.println("f2 + f4 = " + sumWithEnter);

        // **Nouveau : Inverse**
        Fraction inverseF1 = f1.inverse();
        System.out.println("Inverse de f1 = " + inverseF1);

        Fraction inverseF2 = f2.inverse();
        System.out.println("Inverse de f2 = " + inverseF2);
    }
}
