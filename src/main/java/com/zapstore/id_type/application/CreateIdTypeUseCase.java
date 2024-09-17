package com.zapstore.id_type.application;

import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;

public class CreateIdTypeUseCase {
    private final IdTypeService id_typeService;

    public CreateIdTypeUseCase(IdTypeService id_typeService){
        this.id_typeService = id_typeService;
    }

    public void execute(IdType IdType){
        id_typeService.createIdType(IdType);
    }
}
