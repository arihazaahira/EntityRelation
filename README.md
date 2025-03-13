# Projet JPA : Gestion d'Étudiants et Modules

Ce projet illustre l'utilisation de JPA (Java Persistence API) pour gérer des relations entre différentes entités : `Student`, `Adresse`, `Email`, et `Module`.

## Description

Ce projet a pour but de démontrer les concepts de base de JPA, notamment :

* Relations One-to-One (étudiant-adresse, étudiant-email)
* Relation Many-to-Many (étudiant-module)
* Utilisation de requêtes JPA pour récupérer des données liées
* Insertion et récupération de données à partir d'une base de données MySQL

## Prérequis

* Java Development Kit (JDK) 17 ou supérieur
* Maven
* MySQL Server
* Un IDE (Integrated Development Environment) comme IntelliJ IDEA ou Eclipse

## Configuration

1.  **Clonez le dépôt :**

    ```bash
    git clone <URL_du_dépôt>
    cd <nom_du_projet>
    ```

2.  **Configurez la base de données :**

    * Créez une base de données MySQL nommée `entity`.
    * Mettez à jour les informations de connexion dans le fichier `persistence.xml` :

        ```xml
        <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/entity?useSSL=false"/>
        <property name="jakarta.persistence.jdbc.user" value="root"/>
        <property name="jakarta.persistence.jdbc.password" value="<votre_mot_de_passe>"/>
        ```

3.  **Installez les dépendances Maven :**

    ```bash
    mvn clean install
    ```

## Utilisation

1.  **Exécutez l'application :**

    * Ouvrez la classe `Main.java` dans votre IDE.
    * Exécutez la méthode `main()`.

2.  **Visualisez les résultats :**

    * Les résultats des requêtes JPA seront affichés dans la console.
    * Vous pouvez également vérifier les données dans votre base de données MySQL.

## Structure du projet

* `src/main/java/com/example/`: Contient les classes d'entités (`Student.java`, `Adresse.java`, `Email.java`, `Module.java`) et la classe `Main.java`.
* `src/main/resources/META-INF/persistence.xml`: Fichier de configuration JPA.

## Entités

* `Student`: Représente un étudiant avec un nom, une adresse et un email.
* `Adresse`: Représente une adresse avec une rue et une ville.
* `Email`: Représente une adresse email.
* `Module`: Représente un module avec un nom.

## Relations

* `Student` One-to-One `Adresse`
* `Student` One-to-One `Email` (bidirectionnelle)
* `Student` Many-to-Many `Module` (unidirectionnelle, `Student` est le propriétaire)

## Requêtes JPA

Le code `Main.java` contient des exemples de requêtes JPA pour :

* Récupérer un étudiant à partir d'une adresse
* Récupérer les étudiants inscrits à un module
* Récupérer les modules auxquels un étudiant est inscrit
* Récupérer un étudiant à partir d'un email

## Commandes SQL pour insérer des données

Voici les commandes SQL utilisées pour insérer des données dans la base de données :

```sql
-- Insertion dans la table ADRESSE
INSERT INTO ADRESSE (street, city) VALUES ('100 Pine Lane', 'Oakville');
-- ... (autres insertions)

-- Insertion dans la table STUDENT
INSERT INTO STUDENT (name, address_id, email_id) VALUES ('Olivia White', 1, 1);
-- ... (autres insertions)

-- Insertion dans la table EMAIL
INSERT INTO EMAIL (emailAddress) VALUES ('olivia.w@email.com');
-- ... (autres insertions)

-- Insertion dans la table MODULE
INSERT INTO MODULE (moduleName) VALUES ('Advanced Algorithms');
-- ... (autres insertions)

-- Insertion dans la table STUDENT_MODULE
INSERT INTO STUDENT_MODULE (student_id, module_id) VALUES (1, 1);
-- ... (autres insertions)
