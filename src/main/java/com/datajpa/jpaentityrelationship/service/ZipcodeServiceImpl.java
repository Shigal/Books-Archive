package com.datajpa.jpaentityrelationship.service;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.dto.requestdto.ZipcodeRequestDto;
import com.datajpa.jpaentityrelationship.model.City;
import com.datajpa.jpaentityrelationship.model.Zipcode;
import com.datajpa.jpaentityrelationship.repository.ZipcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {

    private final ZipcodeRepository zipcodeRepository;
    private final CityService cityService;

    @Autowired
    public ZipcodeServiceImpl(ZipcodeRepository zipcodeRepository, CityService cityService) {
        this.zipcodeRepository = zipcodeRepository;
        this.cityService = cityService;
    }


    @Transactional
    @Override
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode = new Zipcode();
        zipcode.setName(zipcodeRequestDto.getName());
        if (zipcodeRequestDto.getCityId() != null) {
            City city = cityService.getCity(zipcodeRequestDto.getCityId());
            zipcode.setCity(city);
        }
        return zipcodeRepository.save(zipcode);

    }

    @Override
    public List<Zipcode> getZipcodes() {
        return StreamSupport
                .stream(zipcodeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()); // find all -> split as individual -> collect in list
    }

    @Override
    public Zipcode getZipcode(Long zipcodeId) {
        return zipcodeRepository.findById(zipcodeId).orElseThrow(() ->
                new IllegalArgumentException("Zipcode with id: " + zipcodeId + " couldn't be found."));
    }

    @Transactional
    @Override
    public Zipcode deleteZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        zipcodeRepository.delete(zipcode);
        return zipcode;
    }

    @Transactional
    @Override
    public Zipcode editZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcodeToEdit = getZipcode(zipcodeId);
        zipcodeToEdit.setName(zipcodeRequestDto.getName());
        if (zipcodeRequestDto.getCityId() != null) {
            City city = cityService.getCity(zipcodeRequestDto.getCityId());
            zipcodeToEdit.setCity(city);
        }
        return zipcodeRepository.save(zipcodeToEdit);
    }

    @Transactional
    @Override
    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        City city = cityService.getCity(cityId);
        // check if zipcode already has a city
        if(Objects.nonNull(zipcode.getCity())) {
            throw new IllegalArgumentException("Zipcode already has a city.");
        }
        else{
            zipcode.setCity(city);
            zipcodeRepository.save(zipcode);
        }
        return zipcode;
    }

    @Transactional
    @Override
    public Zipcode removeCityFromZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        if(!Objects.nonNull(zipcode.getCity())) {
            throw new IllegalArgumentException("Zipcode doesn't have a city.");
        }
        zipcode.setCity(null);
        return zipcode;
    }
}
