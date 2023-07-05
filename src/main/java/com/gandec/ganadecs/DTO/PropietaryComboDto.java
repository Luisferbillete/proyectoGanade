package com.gandec.ganadecs.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropietaryComboDto implements EntityDTO{
    private long id;
    private  String fullname;

}
