package com.dsacademy.service;

import com.dsacademy.exception.EmployeeNotFoundException;
import com.dsacademy.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки функциональности {@link EmployeeService}.
 *
 *
 * @author Pochoev M.
 * @version 1.0
 */
class EmployeeServiceTest {

    private EmployeeService employeeService;
    private List<Employee> employees;


    /**
     * Подготавливает тестовое окружение перед выполнением каждого теста.
     *
     */
    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
        employees = Arrays.asList(
                new Employee(1, "Иван", "Иванов", 50000),
                new Employee(2, "Петр", "Петров", 60000),
                new Employee(3, "Мария", "Сидорова", 55000)
        );
    }


    /**
     * Тестирует поиск сотрудника по существующему ID.
     *
     * <p>Проверяет, что метод {@link EmployeeService#getEmployeeById(int, List)}
     * корректно возвращает сотрудника при передаче существующего ID
     */
    @Test
    void testGetEmployeeById_ExistingId() {

        Employee employee = assertDoesNotThrow(() ->
                employeeService.getEmployeeById(2, employees)
        );

        assertNotNull(employee);
        assertEquals(2, employee.getId());
        assertEquals("Петр", employee.getFirstName());


    }


    /**
     * Тестирует поиск сотрудника по несуществующему ID.
     *
     * <p>Проверяет, что метод {@link EmployeeService#getEmployeeById(int, List)}
     * выбрасывает исключение {@link EmployeeNotFoundException} при попытке
     * найти сотрудника с несуществующим ID.</p>
     */
    @Test
    void testGetEmployeeById_NonExistingId() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeById(99, employees);
        });
    }


    /**
     * Тестирует преобразование списка сотрудников в Map.
     *
     * <p>Проверяет, что метод {@link EmployeeService#getEmployeeMap(List)}
     * корректно преобразует список сотрудников в Map, где ключами являются
     * строки вида "id{ID}", а значениями - соответствующие объекты сотрудников.</p>
     */
    @Test
    void testGetEmployeeMap() {
        Map<String, Employee> employeeMap = employeeService.getEmployeeMap(employees);

        assertEquals(3, employeeMap.size());
        assertTrue(employeeMap.containsKey("id1"));
        assertTrue(employeeMap.containsKey("id2"));
        assertTrue(employeeMap.containsKey("id3"));

        Employee emp = employeeMap.get("id2");
        assertEquals("Петр", emp.getFirstName());
        assertEquals("Петров", emp.getLastName());
    }

}