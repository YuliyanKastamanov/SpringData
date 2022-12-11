package bg.softuni.l07_springdataautomappingobjects;

import bg.softuni.l07_springdataautomappingobjects.entities.Employee;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.AddressDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.CreateAddressDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.CreateEmployeeDTO;
import bg.softuni.l07_springdataautomappingobjects.services.AddressService;
import bg.softuni.l07_springdataautomappingobjects.services.EmployeeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

//@Component
public class AppMain implements CommandLineRunner {

    private final AddressService addressService;
    private final EmployeeService employeeService;
    private Scanner scanner;

    private final Gson gson;

    @Autowired
    public AppMain(AddressService addressService, EmployeeService employeeService, Scanner scanner, Gson gson) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
        this.gson = gson;
    }


    @Override
    public void run(String... args) throws Exception {


        createAddress();

        //createEmployee(scanner);

        //printAllEmployees();

    }

    private void printAllEmployees() {

        this.employeeService.findAll()
                .forEach(System.out::println);
    }

    private void createEmployee() {

        String firstName = scanner.nextLine();
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        LocalDate birthday = LocalDate.parse(scanner.nextLine());

        //long addressId = Long.parseLong(scanner.nextLine());

        String country = scanner.nextLine();
        String city = scanner.nextLine();

        CreateAddressDTO address = new CreateAddressDTO(country, city);

        CreateEmployeeDTO employeeDTO =
                new CreateEmployeeDTO(firstName, null, salary, birthday, address);

        Employee employee = this.employeeService.create(employeeDTO);

        System.out.println(employee);

    }

    private void createAddress() {
        String input = this.scanner.nextLine();
        CreateAddressDTO data = this.gson.fromJson(input, CreateAddressDTO.class);

        AddressDTO created = addressService.create(data);

        System.out.println(this.gson.toJson(created));
    }
}
