package bg.softuni.l07_springdataautomappingobjects.services;

import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.AddressDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.CreateAddressDTO;

public interface AddressService {
    AddressDTO create(CreateAddressDTO data);
}
