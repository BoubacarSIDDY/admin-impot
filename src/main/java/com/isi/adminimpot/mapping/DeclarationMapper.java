package com.isi.adminimpot.mapping;

import com.isi.adminimpot.dto.DeclarationDto;
import com.isi.adminimpot.entities.Declaration;
import org.mapstruct.Mapper;
@Mapper
public interface DeclarationMapper {
    DeclarationDto toDeclarationDto(Declaration declaration);
    Declaration fromDeclaration(DeclarationDto declarationDto);
}
