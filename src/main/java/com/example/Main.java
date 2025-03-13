package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Insertion de données
        Adresse address1 = new Adresse("100 Pine Lane", "Oakville");
        Student student1 = new Student("Olivia White", address1);
        em.persist(student1);

        Email email1 = new Email("olivia.w@email.com");
        student1.setEmail(email1);
        email1.setStudent(student1);
        em.persist(email1);

        Module module1 = new Module("Advanced Algorithms");
        Module module2 = new Module("Cloud Computing");
        Module module3 = new Module("Data Science");
        em.persist(module1);
        em.persist(module2);
        em.persist(module3);

        student1.getModules().add(module1);
        student1.getModules().add(module2);

        Adresse address2 = new Adresse("200 Maple Drive", "Birchwood");
        Student student2 = new Student("Noah Green", address2);
        em.persist(student2);

        student2.getModules().add(module2);
        student2.getModules().add(module3);

        Adresse address3 = new Adresse("300 Oak Street", "Riverdale");
        Student student3 = new Student("Liam Brown", address3);
        em.persist(student3);

        student3.getModules().add(module3);
        student3.getModules().add(module1);

        em.getTransaction().commit();

        // Récupération et affichage
        System.out.println("--- Récupération des données ---");

        // Student from Adresse
        TypedQuery<Student> studentFromAddressQuery = em.createQuery("SELECT s FROM Student s WHERE s.address.id = :addressId", Student.class);
        studentFromAddressQuery.setParameter("addressId", address1.getId());
        Student studentFromAddress = studentFromAddressQuery.getSingleResult();
        System.out.println("Student from Adresse: " + studentFromAddress.getName());

        // Student from Module
        TypedQuery<Student> studentsFromModuleQuery = em.createQuery("SELECT s FROM Student s JOIN s.modules m WHERE m.id = :moduleId", Student.class);
        studentsFromModuleQuery.setParameter("moduleId", module1.getId());
        List<Student> studentsFromModule = studentsFromModuleQuery.getResultList();
        System.out.println("Students from Module: " + studentsFromModule.stream().map(Student::getName).collect(Collectors.toList()));

        // Module from Student
        Student retrievedStudent1 = em.find(Student.class, student1.getId());
        if (retrievedStudent1 != null) {
            System.out.println("Modules from Student: " + retrievedStudent1.getModules().stream().map(Module::getModuleName).collect(Collectors.toList()));
        }

        // Student from Email
        Email retrievedEmail1 = em.find(Email.class, email1.getId());
        if (retrievedEmail1 != null) {
            System.out.println("Student from Email: " + retrievedEmail1.getStudent().getName());
        }



        // Student from Modules (ajouté)
        TypedQuery<Student> studentsFromModule2Query = em.createQuery("SELECT s FROM Student s JOIN s.modules m WHERE m.id = :moduleId", Student.class);
        studentsFromModule2Query.setParameter("moduleId", module2.getId());
        List<Student> studentsFromModule2 = studentsFromModule2Query.getResultList();
        System.out.println("Students from Module (2): " + studentsFromModule2.stream().map(Student::getName).collect(Collectors.toList()));

        // Student from Emails (ajouté)
        TypedQuery<Student> studentsFromEmailQuery = em.createQuery("SELECT s FROM Student s WHERE s.email.id = :emailId", Student.class);
        studentsFromEmailQuery.setParameter("emailId", email1.getId());
        Student studentFromEmail = studentsFromEmailQuery.getSingleResult();
        System.out.println("Student from Email (2): " + studentFromEmail.getName());

        // Emails from Student (ajouté)
        Student retrievedStudent2 = em.find(Student.class, student2.getId());
        if (retrievedStudent2 != null && retrievedStudent2.getEmail() != null) {
            System.out.println("Email from Student (2): " + retrievedStudent2.getEmail().getEmailAddress());
        } else {
            System.out.println("Email from Student (2): Not found or student has no email");
        }

        em.close();
        emf.close();
    }
}