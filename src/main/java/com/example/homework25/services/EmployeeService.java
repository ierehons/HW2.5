package com.example.homework25.services;

import com.example.homework25.domain.Employee;
import com.example.homework25.exception.EmployeeAlreadyAddedException;
import com.example.homework25.exception.EmployeeNotFoundException;
import com.example.homework25.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final int SIZE = 4;
    private final Employee[] employees = new Employee[SIZE];

    public Employee add(String firstName, String lastName) {
        int indexForAdding = -1;
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null && indexForAdding == -1) {
                indexForAdding = i;
            }
            if (employees[i] != null && employees[i].equals(employee)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        if (indexForAdding == -1) {
            throw new EmployeeStorageIsFullException();
        }
        employees[indexForAdding] = employee;
        return employees[indexForAdding];
    }

       public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (Employee empl : employees) {
            if (employee.equals(empl)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employee.equals(employees[i])) {
                employees[i] = null;
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }
}
