package com.zapstore.id_type.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.id_type.domain.entity.IdType;

public interface IdTypeService {
    void createIdType(IdType IdType);
    void updateIdType(IdType IdType);
    IdType deleteIdType(String name);
    Optional<IdType> findIdTypeByName (String name);
    Optional<IdType> findIdTypeById (int Id);
    List<IdType> findAllIdType();
}
