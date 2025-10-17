package com.dsacademy.service;

import com.dsacademy.exception.FileLoadException;
import com.dsacademy.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс FileService отвечает за сохранение и загрузку списка сотрудников в/из файла.
 * @author Pochoev M.
 * @version 1.0
 */

public class FileService {

    /**
     * Сохраняет список сотрудников в текстовый файл
     * @param employees список сотрудников для сохранения
     * @param filename имя файла для сохранения
     * @throws IllegalArgumentException если список сотрудников равен null или имя файла пустое
     */
    public void saveEmployeesToFile(List<Employee> employees, String filename){

        if (employees == null || filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Некорректные параметры");
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){

            writer.write("ID,First Name,Last Name,Salary");
            writer.newLine();

            for(Employee employee : employees){
                String line = String.format("%d,%s,%s,%d",
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary()
                );
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Читает данные о сотрудниках из файла и возвращает список объектов Employee
     * @param filename имя файла для загрузки
     * @return список сотрудников
     * @throws FileLoadException если файл не найден или произошла критическая ошибка чтения
     */

    public List<Employee> loadEmployeesFromFile(String filename) throws FileLoadException {

        List<Employee> employees = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null){
                try {
                    String[] parts = line.split(",");
                    if (parts.length != 4) {
                        throw new IllegalArgumentException("Неверное количество полей: " + line);
                    }

                    int id = Integer.parseInt(parts[0].trim());
                    String firstName = parts[1].trim();
                    String lastName = parts[2].trim();
                    int salary = Integer.parseInt(parts[3].trim());

                    employees.add(new Employee(id, firstName, lastName, salary));

                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка при чтении строки: " + line + " — " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            throw new FileLoadException("Файл не найден: " + filename, e);
        } catch (IOException e) {
            throw new FileLoadException("Ошибка при чтении файла: " + filename, e);
        }

        return employees;
    }
}
