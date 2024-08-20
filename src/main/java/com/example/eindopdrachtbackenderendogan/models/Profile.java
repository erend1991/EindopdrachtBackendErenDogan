package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


    @Entity
    @Table(name = "profiles")
    public class Profile {
        @Id
        private String username;
        private String lastname;
        private String firstname;
        private String address;
        private String bankAccount;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String surname) {
            this.lastname = surname;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }


