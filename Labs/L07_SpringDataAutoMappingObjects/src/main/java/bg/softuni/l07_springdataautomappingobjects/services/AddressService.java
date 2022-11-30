package bg.softuni.l07_springdataautomappingobjects.services;

import bg.softuni.l07_springdataautomappingobjects.entities.Address;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.AddressDTO;

public interface AddressService {
    Address create(AddressDTO data);
}
