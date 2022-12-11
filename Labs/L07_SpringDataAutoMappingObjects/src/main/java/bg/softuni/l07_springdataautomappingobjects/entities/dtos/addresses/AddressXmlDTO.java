package bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses;


import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressXmlDTO {


    @XmlElement
    private int id;

    @XmlElement
    private String country;


    @XmlElement
    private String city;


    public AddressXmlDTO() {
    }

    public AddressXmlDTO(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressXmlDTO{" +
                "id=" + id +
                ", country=" + country +
                ", city='" + city + '\'' +
                '}';
    }
}
