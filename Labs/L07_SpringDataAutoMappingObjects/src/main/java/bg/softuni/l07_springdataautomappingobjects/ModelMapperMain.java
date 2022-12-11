package bg.softuni.l07_springdataautomappingobjects;

import bg.softuni.l07_springdataautomappingobjects.converter.Birthday;
import bg.softuni.l07_springdataautomappingobjects.converter.BirthdayDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.Address;
import bg.softuni.l07_springdataautomappingobjects.entities.Employee;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.EmployeeDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class ModelMapperMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        ModelMapper mapper = new ModelMapper();

        dateConverter();

        //1st mapping
        /*PropertyMap<Employee, EmployeeDTO> propertyMap = new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                map().setCity(source.getAddress().getCity());

            }
        };
        mapper.addMappings(propertyMap);*/

        //2nd mapping




      /*  TypeMap<Employee, EmployeeDTO> typeMap = mapper.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap.addMappings(mapping -> mapping.map(source -> source.getAddress().getCity(),
                (EmployeeDTO::setAddressCity)));*/
        //typeMap.validate();

        /*Address address = new Address("Bulgaria", "Sofia");
        Employee employee = new Employee("First", BigDecimal.TEN, address);


        //EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);

        EmployeeDTO employeeDTO = typeMap.map(employee);

        System.out.println(employeeDTO);*/

    }

    private void dateConverter() {
        ModelMapper mapper = new ModelMapper();
        Converter<String , LocalDate> localDateConverter = context -> LocalDate.parse(context.getSource());
        
        mapper.addConverter(localDateConverter, String.class, LocalDate.class);
        Birthday birthday = new Birthday();
        birthday.setDate(LocalDate.now());

        BirthdayDTO stringDate = mapper.map(birthday, BirthdayDTO.class);
        System.out.println(stringDate.getDate());


        Birthday date = mapper.map(stringDate, Birthday.class);

        System.out.println(date.getDate());

    }
}
