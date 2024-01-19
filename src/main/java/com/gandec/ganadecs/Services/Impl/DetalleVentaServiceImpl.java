package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.Bovinos.BovinesDTO;
import com.gandec.ganadecs.DTO.Detalles_ventas.DetallesVentasDTO;
import com.gandec.ganadecs.Mapeador.Util.CalculadoraEdadUtil;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.DetalleVentaRepository;
import com.gandec.ganadecs.Services.DetallesVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleVentaServiceImpl implements DetallesVentaService {
    private final DetalleVentaRepository detalleVentaRepository;
    private final BovinoRepository bovinoRepository;
    private final CalculadoraEdadUtil calculadoraEdadUtil;
    @Override
    public List<DetallesVentasDTO> findByVentaId(Long ventaId) {
        List<DetallesVentasDTO> detallesVentasDTOS = new ArrayList<>();
        List<DetallesVentasDTO>detalles=detalleVentaRepository.findByVentaId(ventaId);
        for (DetallesVentasDTO detalle:detalles) {
            Optional<BovinesDTO> bovino = bovinoRepository.findBovinoByNumero(detalle.getBovino());
            if (bovino.isPresent()) {
                String categoria = calculadoraEdadUtil.calcularCategoria(bovino.get().
                        getFecha_de_nacimiento(), bovino.get().getSexo());
                detalle.setCategoria(categoria);
                detallesVentasDTOS.add(detalle);
            }else {
                throw new IllegalStateException("bovino numero : " + detalle.getBovino() + " encontrado");

            }
        }
        return detallesVentasDTOS;

    }
}
