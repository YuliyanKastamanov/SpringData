package bg.softuni.l07_springdataautomappingobjects.services;

import bg.softuni.l07_springdataautomappingobjects.entities.Employee;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.CreateEmployeeDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
     Employee create(CreateEmployeeDTO employeeDTO);

     List<EmployeeDTO> findAll();
}
