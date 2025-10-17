package com.dsacademy;

import com.dsacademy.exception.EmployeeNotFoundException;
import com.dsacademy.exception.FileLoadException;
import com.dsacademy.model.Employee;
import com.dsacademy.service.EmployeeService;
import com.dsacademy.service.FileService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws EmployeeNotFoundException, FileLoadException {

        EmployeeService employeeService = new EmployeeService();
        FileService fileService = new FileService();

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Иван", "Иванов", 50000));
        employees.add(new Employee(2, "Петр", "Петров", 60000));
        employees.add(new Employee(3, "Мария", "Сидорова", 45000));
        employees.add(new Employee(4, "Анна", "Козлова", 52000));
        employees.add(new Employee(5, "Сергей", "Васильев", 48000));
        employees.add(new Employee(6, "Ольга", "Николаева", 51000));
        employees.add(new Employee(7, "Дмитрий", "Семенов", 58000));

        // Печатаем карту сотрудников, где ключ — "idX", значение — объект Employee
        System.out.println(employeeService.getEmployeeMap(employees));

        // Находим сотрудника с ID = 7 и печатаем его
        System.out.println(employeeService.getEmployeeById(7, employees));

        // Получаем и печатаем список сотрудников с зарплатой >= 50000
        System.out.println(employeeService.getEmployeesBySalaryGreaterThan(50000, employees));

        // Пробуем найти сотрудника с ID = 10 (если нет — выбросится EmployeeNotFoundException)
        System.out.println(employeeService.getEmployeeById(10, employees));

        // Сохраняем список сотрудников в файл с именем "List"
        fileService.saveEmployeesToFile(employees, "List");

        // Загружаем список сотрудников из файла "List" и выводим его на экран
        System.out.println(fileService.loadEmployeesFromFile("List"));

    }
}