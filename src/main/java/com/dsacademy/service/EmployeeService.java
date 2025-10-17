package com.dsacademy.service;

import com.dsacademy.exception.EmployeeNotFoundException;
import com.dsacademy.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Класс {@code EmployeeService} содержит бизнес-логику для работы с сотрудниками.
 * Включает методы для поиска сотрудников по идентификатору,
 * фильтрации по уровню зарплаты и преобразования списка сотрудников в карту.
 * @author Pochoev M.
 * @version 1.0
 */

public class EmployeeService {

    /**
     * Находит сотрудника по его уникальному идентификатору.
     * <p>
     * Если сотрудник с указанным {@code id} не найден в списке,
     * выбрасывается исключение {@link EmployeeNotFoundException}.
     *
     * @param id         идентификатор сотрудника, которого необходимо найти
     * @param employees  список сотрудников, среди которых выполняется поиск
     * @return объект {@link Employee}, соответствующий указанному {@code id}
     * @throws EmployeeNotFoundException если сотрудник с данным идентификатором отсутствует в списке
     */
    public Employee getEmployeeById(int id, List<Employee> employees) throws EmployeeNotFoundException {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с id = " + id + " не найден"));
    }


    /**
     * Возвращает список сотрудников, чья зарплата больше или равна заданному значению.
     *
     * @param targetSalary минимальное значение зарплаты
     * @param employees    список сотрудников, из которого выполняется выборка
     * @return список {@link Employee}, у которых {@code salary >= targetSalary}
     */
    public List<Employee> getEmployeesBySalaryGreaterThan(int targetSalary, List<Employee> employees){
        return employees.stream()
                .filter(employee -> employee.getSalary() >= targetSalary)
                .collect(Collectors.toList());
    }


    /**
     * Преобразует список сотрудников в карту.
     * <p>
     * Ключом карты является строка в формате {@code "id" + id},
     * а значением — соответствующий объект {@link Employee}.
     * <p>
     * В случае дублирующихся идентификаторов сохраняется первый найденный элемент.
     *
     * @param employees список сотрудников для преобразования
     * @return карта, где ключ — строка вида {@code "id1"}, а значение — объект {@link Employee}
     */
    public Map<String, Employee> getEmployeeMap(List<Employee> employees){
        return employees.stream()
                .collect(Collectors.toMap(
                        employee -> "id" + employee.getId(),
                        employee -> employee,
                        (existing, replacement) -> existing,
                        HashMap::new
                ));
    }
}
