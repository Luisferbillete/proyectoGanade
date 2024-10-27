package com.gandec.ganadecs.DTO.Clientes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteCmbDTO {
    private long id;
    private String fullname;

    public ClienteCmbDTO(long id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }
}
