package bg.softuni.l07_springdataautomappingobjects;

import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.AddressXmlDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.CountryXmlDto;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



//@Component
public class XmlTestMain implements CommandLineRunner {


    private final JAXBContext addressJaxbContext;
    private final JAXBContext countryContext;

    public XmlTestMain(@Qualifier("addressContext") JAXBContext addressJaxbContext, JAXBContext countryContext) {
        this.addressJaxbContext = addressJaxbContext;
        this.countryContext = countryContext;
    }

    @Override
    public void run(String... args) throws Exception {

        CountryXmlDto countryXmlDto = new CountryXmlDto("Bulgaria");

        AddressXmlDTO xmlDTO = new AddressXmlDTO(5, "Bulgaria", "Sofia");

        //JAXBContext context = JAXBContext.newInstance(AddressXmlDTO.class);

        Marshaller countryMarshaller = countryContext.createMarshaller();
        countryMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        countryMarshaller.marshal(countryXmlDto, System.out);

        Marshaller addressMarshaller = addressJaxbContext.createMarshaller();
        addressMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        addressMarshaller.marshal(xmlDTO, System.out);

//<?xml version="1.0" encoding="UTF-8" standalone="yes"?><addressXmlDTO><id>5</id><countries><country value="Bulgaria"/><country value="Bulgaria"/><country value="Bulgaria"/></countries><city>Sofia</city></addressXmlDTO>
       /* Unmarshaller unmarshaller = context.createUnmarshaller();
        AddressXmlDTO unmarshal = (AddressXmlDTO) unmarshaller.unmarshal(System.in);

        System.out.println(unmarshal);*/


    }
}
