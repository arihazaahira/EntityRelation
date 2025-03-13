package com.example;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Module {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String moduleName;

        // Constructeurs, getters et setters
        public Module() {}

        public Module(String moduleName) {
            this.moduleName = moduleName;
        }


    private List<Student> students = new ArrayList<>();


    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}