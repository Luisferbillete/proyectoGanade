package com.gandec.ganadecs.DTO.Clientes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteCmbDTO {
    private long id;
    private String fullName;

    public ClienteCmbDTO(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
