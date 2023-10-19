package vader.lab.demo.components;

import vader.lab.demo.service.EmployeeService;
import vader.lab.demo.domain.Employee;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class RedisOpertionsRunner implements CommandLineRunner {

    @Autowired
    private EmployeeService empDao;

    @Override
    public void run(String... args) throws Exception {
        Employee[] empList = {new Employee(501, "Emp1", 2150.0), new Employee(502, "Emp2", 2150.0)};

        //saving one employee
        empDao.saveEmployee(new Employee(500, "Emp0", 2150.0));
        empDao.saveEmployee(new Employee(501, "Emp1", 2150.0));

        for (Employee x : empList) {
            //log.info("loop :: " + x.getEmpName());
        }

        // Java 9
        //        empDao.saveAllEmployees(
        //                Map.of(501, new Employee(501, "Emp1", 2396.0),
        //                        502, new Employee(502, "Emp2", 2499.5),
        //                        503, new Employee(503, "Emp4", 2324.75)
        //                )
        //        );

        empDao.saveAllEmployees(
                new HashMap<Integer, Employee>() {
                    {
                        put(501, new Employee(501, "Emp1", 2396.0));
                        put(502, new Employee(502, "Emp2", 2396.0));
                        put(503, new Employee(503, "Emp3", 2396.0));
                    }
                }
        );

        //modifying employee with empId 503
        empDao.updateEmployee(new Employee(503, "Emp3", 2325.25));

        //deleting employee with empID 500
        empDao.deleteEmployee(500);

        //retrieving all employees
        empDao.getAllEmployees().forEach((k, v) -> log.info(k + " : " + v));

        //retrieving employee with empID 501
        //log.info("Emp details for 501 : " + empDao.getOneEmployee(501));
    }
}