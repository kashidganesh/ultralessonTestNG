package com.eventplanner.pages;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attendee {
    private int id;
    private String name;
    private String email;

    public Attendee(int id, String name, String email) {
        if (!isValidEmail(email))
            throw new IllegalArgumentException("Invalid email format: " + email);
        else if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name is should not be null");
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return id == attendee.id &&
                Objects.equals(name, attendee.name) &&
                Objects.equals(email, attendee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
