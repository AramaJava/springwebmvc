package ru.maxima.springwebmvc.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author AramaJava 26.07.2023
 */

public class Person {
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 3, max= 100, message = "ФИО должно быть от 3 до 100 символов")
    private String fullname;

    @Min(value = 1900, message = "Год рождения > 1900")
    private int yearOfBirth;

    public Person() {
    }

    public Person(int id, String fullname, int yearOfBirth) {
        this.id = id;
        this.fullname = fullname;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
