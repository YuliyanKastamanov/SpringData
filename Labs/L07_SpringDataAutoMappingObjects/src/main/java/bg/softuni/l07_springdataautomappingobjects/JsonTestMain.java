package bg.softuni.l07_springdataautomappingobjects;

import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.CreateAddressDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.CompanyDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.CreateEmployeeDTO;
import com.google.gson.*;
import org.springframework.boot.CommandLineRunner;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//@Component
public class JsonTestMain implements CommandLineRunner {


/*    {
        "firstName": "first",
            "lastName": "last",
            "salary": 1,
            "address": {
        "country": "Bulgaria",
                "city": "Burgas"
    }
    }*/

    private final Scanner scanner;
    private final Gson gson;

    public JsonTestMain(Scanner scanner) {
        this.scanner = scanner;

        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .setDateFormat("YYYY-MM-DD")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                //.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) -> null)
                .create();
    }
    static class LocalDateAdapter implements JsonSerializer<LocalDate>{

        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context){
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));


        }
    }


    @Override
    public void run(String... args) throws Exception {



        CreateAddressDTO addressDTO1 = new CreateAddressDTO("Bulgaria", "Burgas");
        CreateEmployeeDTO employee1 = new CreateEmployeeDTO(
                "first", "last", BigDecimal.ONE, LocalDate.now(), addressDTO1);


        CreateAddressDTO addressDTO2 = new CreateAddressDTO("Bulgaria", "Varna");
        CreateEmployeeDTO employee2 = new CreateEmployeeDTO(
                "second", "last", BigDecimal.TEN, LocalDate.now(), addressDTO2);


        CreateAddressDTO addressDTO3 = new CreateAddressDTO("Bulgaria", "Ruse");
        CreateEmployeeDTO employee3 = new CreateEmployeeDTO(
                "third", "last", BigDecimal.ZERO, LocalDate.now(), addressDTO3);

        CompanyDTO mega = new CompanyDTO("Mega", List.of(employee1, employee2, employee3));

        System.out.println(this.gson.toJson(mega));

        String input = this.scanner.nextLine();
        CompanyDTO parsed = this.gson.fromJson(input, CompanyDTO.class);
        System.out.println();


    }

    private void test1(){



        CreateAddressDTO addressDTO1 = new CreateAddressDTO("Bulgaria", "Burgas");


        CreateAddressDTO addressDTO2 = new CreateAddressDTO("Bulgaria", "Varna");
        CreateAddressDTO addressDTO3 = new CreateAddressDTO("Bulgaria", "Ruse");

        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(
                "first", "last", BigDecimal.ONE, LocalDate.now(), addressDTO1);

        String json = gson.toJson(createEmployeeDTO);

        System.out.println(json);

        List<CreateAddressDTO> addressList = List.of(addressDTO1, addressDTO2, addressDTO3);

        System.out.println(gson.toJson(addressList));

        String input = this.scanner.nextLine();

        CreateEmployeeDTO parseDTO = gson.fromJson(input, CreateEmployeeDTO.class);

        System.out.println(parseDTO);

        CreateAddressDTO[] list = gson.fromJson(input, CreateAddressDTO[].class);

        System.out.println(list);

    }
}
