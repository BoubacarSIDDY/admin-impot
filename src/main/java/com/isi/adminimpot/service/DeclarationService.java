package com.isi.adminimpot.service;

import com.isi.adminimpot.dto.DeclarationDto;
import com.isi.adminimpot.exception.RequestException;
import com.isi.adminimpot.mapping.DeclarationMapper;
import com.isi.adminimpot.repositories.IDeclarationRepository;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.isi.adminimpot.exception;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
@Service
public class DeclarationService {
    private IDeclarationRepository iDeclarationRepository;
    private DeclarationMapper declarationMapper;
    MessageSource messageSource;

    public DeclarationService(IDeclarationRepository iDeclarationRepository, DeclarationMapper declarationMapper, MessageSource messageSource) {
        this.iDeclarationRepository = iDeclarationRepository;
        this.declarationMapper = declarationMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<DeclarationDto> getDeclarations(){
        return StreamSupport.stream(iDeclarationRepository.findAll().spliterator(), false)
                .map(declarationMapper::toDeclarationDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DeclarationDto getDeclaration(int id){
        return declarationMapper.toDeclarationDto(iDeclarationRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("declaration.notfound", new Object[]{id}, Locale.getDefault()))));
    }

    @Transactional
    public DeclarationDto createDeclaration(DeclarationDto declarationDto){
        return DeclarationMapper.toDeclarationDto(iDeclarationRepository.save(declarationMapper.fromDeclaration(declarationDto)));
    }

    @Transactional
    public DeclarationDto updateDeclaration(int id, DeclarationDto declarationDto){
        return iDeclarationRepository.findById((long) id).map(entity -> {
            declarationDto.setId(id);
            return declarationMapper.toDeclarationDto(iDeclarationRepository.save(declarationMapper.fromDeclaration(declarationDto)));
        }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("declaration.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @Transactional
    public void deleteById(int id){
        try{
            iDeclarationRepository.deleteById((long)id);
        } catch (Exception e){
            throw new RequestException(messageSource.getMessage("declaration.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }

}
