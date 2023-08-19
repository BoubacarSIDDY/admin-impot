package com.isi.adminimpot.mapping;

import com.isi.adminimpot.dto.PaiementDto;
import com.isi.adminimpot.entities.Paiement;
import org.mapstruct.Mapper;

@Mapper
public interface PaiementMapper {
    PaiementDto toPaiementDto(Paiement paiement);
    Paiement fromPaiment(PaiementDto paiementDto);
}
