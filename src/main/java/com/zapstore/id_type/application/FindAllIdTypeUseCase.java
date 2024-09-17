package com.zapstore.id_type.application;

import java.util.List;

import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;

public class FindAllIdTypeUseCase {
    private final IdTypeService id_typeService;

    public FindAllIdTypeUseCase(IdTypeService id_typeService) {
        this.id_typeService = id_typeService;
    }

    public List<IdType> findAllIdType() {
        return id_typeService.findAllIdType();
    }
}
