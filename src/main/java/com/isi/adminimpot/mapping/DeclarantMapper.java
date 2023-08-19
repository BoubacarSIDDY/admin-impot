package com.isi.adminimpot.mapping;


import com.isi.adminimpot.dto.DeclarantDto;
import com.isi.adminimpot.entities.Declarant;
import org.mapstruct.Mapper;
@Mapper
public interface DeclarantMapper {
    DeclarantDto toDeclarantDto(Declarant declarant);
    Declarant fromDeclarant(DeclarantDto declarantDto);
}
