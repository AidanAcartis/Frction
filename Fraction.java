public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Dénominateur ne peut pas être 0");
        }
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
    }

    public Fraction(int numerator) {
        this(numerator,1);
    }

    // Méthodes utilitaires
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public Fraction add(Fraction other) {
        if (this.denominator == other.denominator) {
            return new Fraction(this.numerator + other.numerator, this.denominator);
        } else {
            int num = this.numerator * other.denominator + other.numerator * this.denominator;
            int den = this.denominator * other.denominator;
            return new Fraction(num, den);
        }
    }

    public Fraction add(int value) {
        return this.add(new Fraction(value));
    }

    public Fraction subtract(Fraction other) {
        if (this.denominator == other.denominator) {
            return new Fraction(this.numerator - other.numerator, this.denominator);
        } else {
            int num = this.numerator * other.denominator - other.numerator * this.denominator;
            int den = this.denominator * other.denominator;
            return new Fraction(num, den);
        }
    }

    public Fraction subtract(int value) {
        return this.subtract(new Fraction(value));
    }

     // Inverse d'une fraction
     public Fraction inverse() {
        if (this.numerator == 0) {
            throw new ArithmeticException("Impossible d'inverser une fraction nulle");
        }
        return new Fraction(this.denominator, this.numerator);
    }


    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    public Fraction multiply(int value) {
        return this.multiply(new Fraction(value));
    }

    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Division par zéro");
        }
        return new Fraction(this.numerator * other.denominator, this.denominator * other.numerator);
    }

    public boolean isZero() {
        return this.numerator == 0;
    }

    @Override
    public String toString() {
        if (denominator == 1) return String.valueOf(numerator);
        return numerator + "/" + denominator;
    }

    // Getters (si besoin)
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
}