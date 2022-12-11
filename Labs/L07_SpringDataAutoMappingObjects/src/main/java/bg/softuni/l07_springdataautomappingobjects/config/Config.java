package bg.softuni.l07_springdataautomappingobjects.config;

import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.AddressXmlDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.CountryXmlDto;
import bg.softuni.l07_springdataautomappingobjects.repositories.AddressRepository;
import bg.softuni.l07_springdataautomappingobjects.services.AddressService;
import bg.softuni.l07_springdataautomappingobjects.services.AddressServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Config {

    @Bean("addressContext")
    public JAXBContext createAddressContext() throws JAXBException {
        return JAXBContext.newInstance(AddressXmlDTO.class);
    }
    @Bean("countryContext")
    public JAXBContext createCountryContext() throws JAXBException {
        return JAXBContext.newInstance(CountryXmlDto.class);
    }

    @Bean
    public ModelMapper createModelMapper(){

        return new ModelMapper();
    }

    @Bean
    public Scanner createScanner(){

        return new Scanner(System.in);
    }

    @Bean
    public AddressService createAddressService(AddressRepository repo, ModelMapper mapper){

        return new AddressServiceImpl(repo, mapper);
    }

    @Bean
    public Gson createGson(){

        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}
