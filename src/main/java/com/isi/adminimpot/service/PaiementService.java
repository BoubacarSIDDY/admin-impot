package com.isi.adminimpot.service;

import com.isi.adminimpot.dto.DeclarationDto;
import com.isi.adminimpot.dto.PaiementDto;
import com.isi.adminimpot.entities.Paiement;
import com.isi.adminimpot.exception.EntityNotFoundException;
import com.isi.adminimpot.exception.RequestException;
import com.isi.adminimpot.mapping.PaiementMapper;
import com.isi.adminimpot.repositories.IDeclarationRepository;
import com.isi.adminimpot.repositories.IPaiementRepository;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PaiementService {
    private IPaiementRepository iPaiementRepository;
    private IDeclarationRepository iDeclarationRepository;
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
        // Récupérer la déclaration liée au paiement
        DeclarationDto declarationDto = iDeclarationRepository.findById(paiementDto.getDeclarationId()).orElse(null);
        if (declarationDto == null) {
            throw new Exception(messageSource.getMessage("declaration.notfound", paiementDto.getDeclaration().getId()));
        }
        // Calcul du montant total des paiements déjà effectués sur cette déclaration
        Double montantTotalPaiements = iPaiementRepository.getTotalPaiementsForDeclaration(declarationDto.getId());

        if (montantTotalPaiements == null) {
            montantTotalPaiements = 0.0;
        }

        // Calculer le montant restant à payer sur la déclaration
        Double montantRestant = declarationDto.getMontantDeclaration() - montantTotalPaiements;

        if (paiementDto.getMontantPaiement() > montantRestant) {
            throw new Exception(messageSource.getMessage("paiement.errordepassement", paiementDto.getDeclaration().getId()));
        }
        return paiementMapper.toPaiementDto(iPaiementRepository.save(paiementMapper.fromPaiment(paiementDto)));
    }

    @Transactional
    public PaiementDto updatePaiement(int id, PaiementDto paiementDto){
        return iPaiementRepository.findById((long) id).map(entity -> {
            paiementDto.setId((long) id);
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
