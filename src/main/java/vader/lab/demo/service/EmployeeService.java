package vader.lab.demo.service;

import java.util.Map;

import vader.lab.demo.domain.Employee;

public interface EmployeeService {
    void saveEmployee(Employee emp);

    Employee getOneEmployee(Integer id);

    void updateEmployee(Employee emp);

    Map<Integer, Employee> getAllEmployees();

    void deleteEmployee(Integer id);

    void saveAllEmployees(Map<Integer, Employee> map);
}

