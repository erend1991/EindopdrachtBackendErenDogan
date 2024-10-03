package com.example.eindopdrachtbackenderendogan.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentPhoto {


    @Id
    private String fileName;

    public StudentPhoto(String fileName){
        this.fileName = fileName;
    }

    public StudentPhoto(){

    }

    public String getFileName(){
        return fileName;
    }
}

