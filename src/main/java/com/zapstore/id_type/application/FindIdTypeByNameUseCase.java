package com.zapstore.id_type.application;

import java.util.Optional;

import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;

public class FindIdTypeByNameUseCase {
    private final IdTypeService id_typeService;

    public FindIdTypeByNameUseCase(IdTypeService id_typeService) {
        this.id_typeService = id_typeService;
    }

    public Optional<IdType> findIdTypeByName(String id_typeName) {
        return id_typeService.findIdTypeByName(id_typeName);
    }
}
