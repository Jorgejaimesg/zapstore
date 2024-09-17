package com.zapstore.id_type.application;

import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;

public class UpdateIdTypeUseCase {
    private final IdTypeService id_typeService;

    public UpdateIdTypeUseCase(IdTypeService id_typeService){
        this.id_typeService = id_typeService;
    }

    public void execute(IdType id_type){
        id_typeService.updateIdType(id_type);
    }
}
