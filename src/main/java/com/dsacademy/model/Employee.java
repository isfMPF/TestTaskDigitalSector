package com.dsacademy.model;

import java.util.Objects;


/**
 * Класс {@code Employee} представляет модель сотрудника организации.
 * <p>
 * Содержит основную информацию о сотруднике:
 * <ul>
 *   <li>уникальный идентификатор {@link #id}</li>
 *   <li>имя {@link #firstName}</li>
 *   <li>фамилия {@link #lastName}</li>
 *   <li>зарплата {@link #salary}</li>
 * </ul>
 *
 * Реализованы принципы инкапсуляции — доступ к полям осуществляется через
 * геттеры и сеттеры. Также переопределены методы {@link #toString()},
 * {@link #equals(Object)} и {@link #hashCode()}.
 *
 * @author Pochoev M.
 * @version 1.0
 */

public class Employee {

    private int id;

    private String firstName;

    private String lastName;

    private int salary;

    public Employee() {}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee(int id, String firstName, String lastName, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
