package bg.softuni.l07_springdataautomappingobjects.services;

import bg.softuni.l07_springdataautomappingobjects.entities.Address;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.AddressDTO;
import bg.softuni.l07_springdataautomappingobjects.entities.dtos.addresses.CreateAddressDTO;
import bg.softuni.l07_springdataautomappingobjects.repositories.AddressRepository;
import org.modelmapper.ModelMapper;

public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;


    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public AddressDTO create(CreateAddressDTO data) {


        Address address = mapper.map(data, Address.class);


        Address saved = this.addressRepository.save(address);

        return this.mapper.map(saved, AddressDTO.class);
    }
}
