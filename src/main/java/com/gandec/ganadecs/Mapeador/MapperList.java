package com.gandec.ganadecs.Mapeador;

import com.gandec.ganadecs.Entity.Potrero;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperList {
    private static ModelMapper mapper = new ModelMapper();
    private MapperList(){

    }
    public static  <S,T> List<T> mapList(List<S> source, Class<T> targetClass){
        return source
                .stream()
                .map( element -> mapper.map(element,targetClass))
                .collect(Collectors.toList());
    }
    public static long potreroToLong(Potrero potrero) {
        return potrero.getId();
    }
}
