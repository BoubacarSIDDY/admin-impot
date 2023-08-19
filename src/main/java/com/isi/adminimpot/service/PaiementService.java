package com.isi.adminimpot.service;

import com.isi.adminimpot.dto.PaiementDto;
import com.isi.adminimpot.exception.RequestException;
import com.isi.adminimpot.mapping.PaiementMapper;
import com.isi.adminimpot.repositories.IPaiementRepository;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PaiementService {
    private IPaiementRepository iPaiementRepository;
    private PaiementMapper paiementMapper;
    MessageSource messageSource;

    public PaiementService(IPaiementRepository iPaiementRepository, PaiementMapper declarationMapper, MessageSource messageSource) {
        this.iPaiementRepository = iPaiementRepository;
        this.paiementMapper = declarationMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<PaiementDto> getPaiements(){
        return StreamSupport.stream(iPaiementRepository.findAll().spliterator(), false)
                .map(paiementMapper::toPaiementDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PaiementDto getPaiement(int id){
        return paiementMapper.toPaiementDto(iPaiementRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("paiement.notfound", new Object[]{id}, Locale.getDefault()))));
    }

    @Transactional
    public PaiementDto createPaiement(PaiementDto paiementDto){
        return paiementMapper.toPaiementDto(iPaiementRepository.save(paiementMapper.fromPaiment(paiementDto)));
    }

    @Transactional
    public PaiementDto updatePaiement(int id, PaiementDto paiementDto){
        return iPaiementRepository.findById((long) id).map(entity -> {
            paiementDto.setId(id);
            return paiementMapper.toPaiementDto(iPaiementRepository.save(paiementMapper.fromPaiment(paiementDto)));
        }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("paiement.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @Transactional
    public void deleteById(int id){
        try{
            iPaiementRepository.deleteById((long)id);
        } catch (Exception e){
            throw new RequestException(messageSource.getMessage("paiement.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
