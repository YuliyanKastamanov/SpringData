package bg.softuni.l07_springdataautomappingobjects;

import bg.softuni.l07_springdataautomappingobjects.entities.Address;
import bg.softuni.l07_springdataautomappingobjects.entities.Employee;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class ModelMapperMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        ModelMapper mapper = new ModelMapper();

        //1st mapping
        /*PropertyMap<Employee, EmployeeDTO> propertyMap = new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                map().setCity(source.getAddress().getCity());

            }
        };
        mapper.addMappings(propertyMap);*/

        //2nd mapping
        TypeMap<Employee, EmployeeDTO> typeMap = mapper.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap.addMappings(mapping -> mapping.map(source -> source.getAddress().getCity(),
                (EmployeeDTO::setAddressCity)));
        //typeMap.validate();

        Address address = new Address("Bulgaria", "Sofia");
        Employee employee = new Employee("First", BigDecimal.TEN, address);


        //EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);

        EmployeeDTO employeeDTO = typeMap.map(employee);

        System.out.println(employeeDTO);
    }
}
