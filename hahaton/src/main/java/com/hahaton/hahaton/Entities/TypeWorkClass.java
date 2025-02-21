package com.hahaton.hahaton.Entities;

import com.hahaton.hahaton.Entities.DTO.TypeWorkClassDTO;
import com.hahaton.hahaton.Enums.TypeWorkEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TypeWorkClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeWorkEnum typeWork;

    public TypeWorkClass(TypeWorkClassDTO typeWorkClassDTO){
        this.id = typeWorkClassDTO.getId();
        this.typeWork = typeWorkClassDTO.getTypeWorkEnum();
    }

    public TypeWorkClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeWorkEnum getTypeWork() {
        return typeWork;
    }

    public void setTypeWork(TypeWorkEnum typeWork) {
        this.typeWork = typeWork;
    }
}
