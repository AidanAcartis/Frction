En Java, tu **ne peux pas automatiquement transformer un `int` en `Fraction` sans le faire explicitement** (par exemple avec `new Fraction(x, 1)`) car **Java ne prend pas en charge les conversions implicites entre types comme le ferait C++ avec les constructeurs implicites**.

Mais il existe deux solutions élégantes pour **imiter ce comportement** :

---

### ✅ **1. Ajouter un constructeur avec un seul `int` (déjà implicite côté Java)**

Ton constructeur principal prend déjà `(int numerator, int denominator)`. Tu peux ajouter ceci :

```java
public Fraction(int numerator) {
    this(numerator, 1);
}
```

➡️ Ainsi, chaque fois que tu écris `new Fraction(5)`, Java comprend `5/1`.

---

### ✅ **2. Ajouter les surcharges de méthode `add(int)`, `subtract(int)`, etc.**

Comme vu précédemment, pour permettre des opérations avec `int`, ajoute :

```java
public Fraction add(int value) {
    return this.add(new Fraction(value));
}

public Fraction subtract(int value) {
    return this.subtract(new Fraction(value));
}

public Fraction multiply(int value) {
    return this.multiply(new Fraction(value));
}

public Fraction divide(int value) {
    if (value == 0) throw new ArithmeticException("Division par zéro");
    return this.divide(new Fraction(value));
}
```

---

### ✨ Résultat : tu pourras faire

```java
Fraction f = new Fraction(3, 4);
System.out.println(f.add(2)); // Affiche : 11/4
System.out.println(f.multiply(3)); // Affiche : 9/4

Fraction x = new Fraction(7); // automatiquement 7/1
System.out.println(x); // Affiche : 7
```

---

Tu veux aussi que les entiers soient traités comme des `Fraction` dans des **collections génériques**, des **paramètres de fonction**, ou dans une **interface commune** (`Number`, `Comparable`, etc.) ?