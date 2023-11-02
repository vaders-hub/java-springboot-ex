package vader.lab.demo.domain;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Employee implements Serializable {

    private static final Long serialVersionUID = -7817224776021728682L;

    private Integer empId;
    private String empName;
    private Double empSalary;
}