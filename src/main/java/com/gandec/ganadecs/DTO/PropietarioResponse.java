package com.gandec.ganadecs.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
public class PropietarioResponse {
    private List<PropietarioDTO> propietarioDTOSlist;
    public PropietarioResponse(){
        super();
        propietarioDTOSlist = new ArrayList<PropietarioDTO>();
    }
    public  void addpropietarioDTOSlist(PropietarioDTO propietarioDTO){
        this.propietarioDTOSlist.add(propietarioDTO);
    }
    public void setPropietarioDTOSlist(List<PropietarioDTO> propietarioDTOSlist){
        this.propietarioDTOSlist=propietarioDTOSlist;
    }
    public  List<PropietarioDTO>getPropietarioDTOSlist(){
        return propietarioDTOSlist;
    }
}
