package com.isi.adminimpot.service;

import com.isi.adminimpot.dto.DeclarantDto;
import com.isi.adminimpot.exception.*;
import com.isi.adminimpot.mapping.DeclarantMapper;
import com.isi.adminimpot.repositories.IDeclarantRepository;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DeclarantService {
    private IDeclarantRepository iDeclarantRepository;
    private DeclarantMapper declarantMapper;
    MessageSource messageSource;

    public DeclarantService(IDeclarantRepository iDeclarantRepository, DeclarantMapper declarantMapper, MessageSource messageSource) {
        this.iDeclarantRepository = iDeclarantRepository;
        this.declarantMapper = declarantMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<DeclarantDto> getDeclarants(){
        return StreamSupport.stream(iDeclarantRepository.findAll().spliterator(), false)
                .map(declarantMapper::toDeclarantDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DeclarantDto getDeclarant(int id){
        return declarantMapper.toDeclarantDto(iDeclarantRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("declarant.notfound", new Object[]{id}, Locale.getDefault()))));
    }

    @Transactional
    public DeclarantDto createDeclarant(DeclarantDto declarantDto){
        return declarantMapper.toDeclarantDto(iDeclarantRepository.save(declarantMapper.fromDeclarant(declarantDto)));
    }

    @Transactional
    public DeclarantDto updateDeclarant(int id, DeclarantDto declarantDto){
        return iDeclarantRepository.findById((long) id).map(entity -> {
            declarantDto.setId((long)id);
            return declarantMapper.toDeclarantDto(iDeclarantRepository.save(declarantMapper.fromDeclarant(declarantDto)));
        }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("declaration.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @Transactional
    public void deleteById(int id){
        try{
            iDeclarantRepository.deleteById((long)id);
        } catch (Exception e){
            throw new RequestException(messageSource.getMessage("declaration.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
