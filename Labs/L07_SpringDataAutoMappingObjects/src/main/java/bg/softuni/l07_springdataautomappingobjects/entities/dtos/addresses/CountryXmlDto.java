package bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryXmlDto {


    @XmlAttribute
    private String value;

    public CountryXmlDto() {
    }

    public CountryXmlDto(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CountryXmlDto{" +
                "value='" + value + '\'' +
                '}';
    }
}
