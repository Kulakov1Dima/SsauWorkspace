package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.TypeWorkClass;
import com.hahaton.hahaton.Enums.TypeWorkEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TypeWorkClassDTO {

    private Long id;
    private TypeWorkEnum typeWorkEnum;

    public TypeWorkClassDTO(TypeWorkClass typeWorkClass){
        this.id = typeWorkClass.getId();
        this.typeWorkEnum = typeWorkClass.getTypeWork();
    }

    public TypeWorkClassDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeWorkEnum getTypeWorkEnum() {
        return typeWorkEnum;
    }

    public void setTypeWorkEnum(TypeWorkEnum typeWorkEnum) {
        this.typeWorkEnum = typeWorkEnum;
    }
}
