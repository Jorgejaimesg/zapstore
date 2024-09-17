package com.zapstore.id_type.application;

import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;

public class DeleteIdTypeUseCase {
    private final IdTypeService id_typeService;

    public DeleteIdTypeUseCase (IdTypeService id_typeService) {
        this.id_typeService = id_typeService;
    }

    public IdType execute(String Name) {
        return id_typeService.deleteIdType(Name);
    }
}
