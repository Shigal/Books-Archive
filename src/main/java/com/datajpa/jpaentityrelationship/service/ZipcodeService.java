package com.datajpa.jpaentityrelationship.service;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.dto.requestdto.CityRequestDto;
import com.datajpa.jpaentityrelationship.dto.requestdto.ZipcodeRequestDto;
import com.datajpa.jpaentityrelationship.model.City;
import com.datajpa.jpaentityrelationship.model.Zipcode;

import java.util.List;

public interface ZipcodeService {
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto);
    public List<Zipcode> getZipcodes();
    public Zipcode getZipcode(Long zipcodeId);
    public Zipcode deleteZipcode(Long zipcodeId);
    public Zipcode editZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto);
    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId);
    public Zipcode removeCityFromZipcode(Long zipcodeId);
}
