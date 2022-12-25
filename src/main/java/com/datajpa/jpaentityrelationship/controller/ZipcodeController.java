package com.datajpa.jpaentityrelationship.controller;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.dto.requestdto.ZipcodeRequestDto;
import com.datajpa.jpaentityrelationship.model.Zipcode;
import com.datajpa.jpaentityrelationship.service.ZipcodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/zipcode")
public class ZipcodeController {

    private final ZipcodeService zipcodeService;

    public ZipcodeController(ZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Zipcode> addZipcode(@RequestBody final ZipcodeRequestDto zipcodeRequestDto){
        Zipcode zipcode = zipcodeService.addZipcode(zipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Zipcode> getZipcodeById(@PathVariable final Long id) {
        Zipcode zipcode = zipcodeService.getZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Zipcode>> getZipcodes(){
        List<Zipcode> zipcodes = zipcodeService.getZipcodes();
        return new ResponseEntity<>(zipcodes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Zipcode> deleteZipcode(@PathVariable final Long id){
        Zipcode zipcode = zipcodeService.deleteZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Zipcode> editZipcode(@PathVariable final Long id,
                                               @RequestBody ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode = zipcodeService.editZipcode(id, zipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("/addCity/{cityId}/toZipcode/{zipcodeId}")
    public ResponseEntity<Zipcode> addCityToZipcode(@PathVariable final Long cityId,
                                                    @PathVariable final Long zipcodeId) {
        Zipcode zipcode = zipcodeService.addCityToZipcode(zipcodeId, cityId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("/deleteCity/{zipcodeId}")
    public ResponseEntity<Zipcode> deleteCity(@PathVariable final Long zipcodeId){
        Zipcode zipcode = zipcodeService.removeCityFromZipcode(zipcodeId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }
}
