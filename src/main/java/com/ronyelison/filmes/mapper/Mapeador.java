package com.ronyelison.filmes.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapeador {
    private static ModelMapper mapper = new ModelMapper();

    public static <O,D> D converterObjeto(O origem, Class<D> destino){
        return mapper.map(origem,destino);
    }

    public static <O,D>List<D> converterListaDeObjetos(List<O> listaDeOrigem, Class<D> destino){
        List<D> listaDeDestino = new ArrayList<>();
        for (O origem : listaDeOrigem){
            listaDeDestino.add(mapper.map(origem,destino));
        }
        return listaDeDestino;
    }
}
