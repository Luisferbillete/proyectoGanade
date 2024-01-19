package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.Bovinos.BovinesDTO;
import com.gandec.ganadecs.DTO.Detalles_venta_interna.DetalleVentaInternaDTO;
import com.gandec.ganadecs.Mapeador.Util.CalculadoraEdadUtil;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.DetalleVentaInternaRepository;
import com.gandec.ganadecs.Services.DetallesVentaInternaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetallesVentaInternaServiceImpl implements DetallesVentaInternaService {
    private final DetalleVentaInternaRepository detalleVentaInternaRepository;
    private final BovinoRepository bovinoRepository;
    private final CalculadoraEdadUtil calculadoraEdadUtil;
    @Override
    public List<DetalleVentaInternaDTO> findByVentaInternaId(Long ventaInternaId) {
        List<DetalleVentaInternaDTO> detalleVentaInternaDTOList= new ArrayList<>();
      List<DetalleVentaInternaDTO> detalleVentaInternaDTOS = detalleVentaInternaRepository.findByVentaInternaId(ventaInternaId);
        for (DetalleVentaInternaDTO detalle:detalleVentaInternaDTOS) {
            Optional<BovinesDTO> bovino = bovinoRepository.findBovinoByNumero(detalle.getBovino());
            if (bovino.isPresent()) {
                String categoria = calculadoraEdadUtil.calcularCategoria(bovino.get().
                        getFecha_de_nacimiento(), bovino.get().getSexo());
                detalle.setCategoria(categoria);
                detalleVentaInternaDTOList.add(detalle);
            }else {
                throw new IllegalStateException("bovino numero : " + detalle.getBovino() + " encontrado");
            }

        }
        return detalleVentaInternaDTOList;
    }
}
