---

# Résolution de Systèmes Linéaires avec Fractions en Java

Ce projet Java permet de résoudre un système d'équations linéaires à l'aide de la méthode d'échelonnement (réduction de Gauss), en travaillant avec des **fractions exactes** plutôt que des flottants pour éviter les erreurs d'arrondi.

## 🧠 Fonctionnalités

- Entrée d'une matrice `P` de coefficients sous forme de fractions (ex: `2/3`, `5`, etc.).
- Entrée du vecteur `B` (second membre) également sous forme de fractions.
- Résolution du système via la méthode de réduction en forme échelonnée.
- Affichage de la solution du système (ou détection d'incompatibilité).

## 📂 Structure du projet

```
.
├── Main.java          // Point d'entrée du programme
├── Solver.java        // Logique de résolution
├── MatrixUtils.java   // Méthodes utilitaires pour manipuler les matrices
└── Fraction.java      // Classe de gestion des fractions
```

## 🔧 Compilation & Exécution

### 1. Compilation

```bash
javac Main.java Solver.java MatrixUtils.java Fraction.java
```

### 2. Exécution

```bash
java Main

```

### Exemple de session

```
Nombre de lignes : 2
Nombre de colonnes : 2
Entrez les coefficients de la matrice P (forme a/b) :
P[0][0] = 1
P[0][1] = 1
P[1][0] = 1
P[1][1] = -1
Entrez les valeurs du second membre B (forme a/b) :
B[0] = 3
B[1] = 1
Matrice réordonnée (lignes nulles en bas) :
...
X[0] = 2
X[1] = 1
```

## 📌 Remarques

- Le format accepté pour les fractions est `a/b` ou simplement `a`.
- Toute opération (addition, soustraction, multiplication, division, inversion) est faite avec des entiers exacts.

## 📘 Classes principales

### `Fraction`
- Gère toutes les opérations arithmétiques sur des fractions.
- Réduction automatique au plus petit dénominateur commun.

### `Solver`
- Contient la logique principale pour résoudre un système linéaire via la méthode de Gauss.

### `MatrixUtils`
- Fournit les fonctions de manipulation de matrices (échelonnement, impression, réorganisation...).

## ⚠️ Gestion des erreurs

- Division par zéro détectée et levée via une exception.
- Système incompatible détecté lorsqu’une ligne nulle correspond à un second membre non nul.

## 📄 Licence

Ce projet est libre pour un usage personnel ou éducatif.

---
