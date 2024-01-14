package com.gandec.ganadecs.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PropietaryComboDto implements EntityDTO{
    private long id;
    private  String fullname;

    public PropietaryComboDto(long id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }
}
