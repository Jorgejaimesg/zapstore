package com.zapstore.id_type.application;

import java.util.Optional;

import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;

public class FindIdTypeByIdUseCase {
    private final IdTypeService id_typeService;
    public FindIdTypeByIdUseCase(IdTypeService id_typeService) {
        this.id_typeService = id_typeService;
    }

    public Optional<IdType> findIdTypeById(int id) {
        return id_typeService.findIdTypeById(id);
    }
}
