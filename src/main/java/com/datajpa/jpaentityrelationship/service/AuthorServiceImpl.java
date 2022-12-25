package com.datajpa.jpaentityrelationship.service;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.dto.Mapper;
import com.datajpa.jpaentityrelationship.dto.requestdto.AuthorRequestDto;
import com.datajpa.jpaentityrelationship.dto.responsedto.AuthorResponseDto;
import com.datajpa.jpaentityrelationship.model.Author;
import com.datajpa.jpaentityrelationship.model.Zipcode;
import com.datajpa.jpaentityrelationship.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final ZipcodeService zipcodeService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipcodeService zipcodeService) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;
    }

    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipcodeId() == null) {
            throw new IllegalArgumentException("Author should have a zipcode.");
        }
        Zipcode zipcode = zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
        author.setZipcode(zipcode);
        authorRepository.save(author);
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {
        List<Author> authors = StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return Mapper.authorToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {
        return Mapper.authorToAuthorResponseDto(getAuthor(authorId));
    }

    @Override
    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() ->
                new IllegalArgumentException("Author with id: " + authorId + " couldn't be found."));
    }

    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        authorRepository.delete(author);
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        Author authorToEdit = getAuthor(authorId);
        authorToEdit.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipcodeId() != null) {
            Zipcode zipcode = zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
            authorToEdit.setZipcode(zipcode);
        }
        authorRepository.save(authorToEdit);
        return Mapper.authorToAuthorResponseDto(authorToEdit);
    }

    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId) {
        Author author = getAuthor(authorId);
        Zipcode zipcode = zipcodeService.getZipcode(zipcodeId);
        if(Objects.nonNull(author.getZipcode())) {
            throw new RuntimeException("Author already has a zipcode.");
        }
        author.setZipcode(zipcode);
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipcode(null);
        return Mapper.authorToAuthorResponseDto(author);
    }
}
