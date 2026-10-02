package com.example.transportmanagement.service;

import com.example.transportmanagement.entity.Employee;
import com.example.transportmanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import com.example.transportmanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Employee not found with id : " + id
                        )
                );
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id
                        ));

        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(updatedEmployee.getDepartment());

        return employeeRepository.save(employee);
    }


    public void deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Employee not found with id: " + id
            );
        }

        employeeRepository.deleteById(id);
    }
}