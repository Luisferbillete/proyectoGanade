package com.gandec.ganadecs.Services.Impl;


import com.gandec.ganadecs.DTO.Bovinos.BovinesDTO;
import com.gandec.ganadecs.DTO.Detalles_ventas.DetallesVentasDTO;
import com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino;
import com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient;
import com.gandec.ganadecs.Entity.*;
import com.gandec.ganadecs.Excepciones.BovinoVendidoException;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Util.CalculadoraEdadUtil;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.ClienteRepository;
import com.gandec.ganadecs.Repository.DetalleVentaRepository;
import com.gandec.ganadecs.Repository.VentaRepository;
import com.gandec.ganadecs.Services.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class VentaServiceImp implements VentaService {
    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final BovinoRepository bovinoRepository;
    private final CalculadoraEdadUtil calculadoraEdadUtil;



    @Override
    @Transactional
    public void save(Venta venta, List<Detalle_Venta> detalle_ventas, long IdCliente) {

         if (venta!=null && detalle_ventas!=null){
             venta.setDetalle_ventas(detalle_ventas);
             for (Detalle_Venta detalle_venta:detalle_ventas){
                 detalle_venta.setVenta(venta);
             }
             for (Detalle_Venta detalle_venta:detalle_ventas){
                 boolean bovinovendido=BovinoVendido(detalle_venta.getBovino());
                    if (bovinovendido){
                        throw new BovinoVendidoException("El bovino ya fue vendido: " + detalle_venta.getBovino().getNumero());
                    }
             }

             Cliente cliente= clienteRepository.findById(IdCliente).orElseThrow(()->
                     new ResourceNotFoundExcepcion("Client ","id", IdCliente));
                venta.setCliente(cliente);
                venta.setFecha(venta.getFecha());

                ventaRepository.save(venta);
         }else {
             //assert venta != null;
             throw new ResourceNotFoundExcepcion("Venta o Detalles de Venta", "id", IdCliente);
         }
    }

    @Override
    public boolean BovinoVendido(Bovino bovino) {
        List<Detalle_Venta> detalle_ventas=detalleVentaRepository.findByBovino(bovino);
        return !detalle_ventas.isEmpty();
    }

    @Override
    public List<VentaPropietaryClient> findAllByAnnoAndPropietarioIdOrderByFecha(int anno, Long propietarioId) {
        return ventaRepository.findAllByAnnoAndPropietarioIdOrderByFecha(anno,propietarioId);
    }

    @Override
    public List<VentaPropietaryClient> getAll() {
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaRepository.getAll();
        return getVentaPropietaryClients(ventaPropietaryClientList);
    }

    @Override
    public List<VentaPropietaryClient> getAllByFecha(LocalDate fecha) {
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaRepository.getAllByFecha(fecha);
        return getVentaPropietaryClients(ventaPropietaryClientList);
    }

    @Override
    public List<VentaPropietaryClient> getAllByLugar(String lugar) {
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaRepository.getAllByLugar(lugar);
        return getVentaPropietaryClients(ventaPropietaryClientList);
    }

    @Override
    public List<VentaClienteDetalleBovino> findDetailsForSoldBovino(String numero) {
        List<VentaClienteDetalleBovino> ventaClienteDetalleBovinos=new ArrayList<>();
        List<VentaClienteDetalleBovino> ventaClienteDetalleBovinos1=ventaRepository.findDetailsForSoldBovino(numero);
        for (VentaClienteDetalleBovino ventaClienteDetalleBovino:ventaClienteDetalleBovinos1){
            Optional<BovinesDTO> bovino = bovinoRepository.findBovinoByNumero(numero);
            if (bovino.isPresent()) {
                String categoria = calculadoraEdadUtil.calcularCategoria(bovino.get().
                        getFecha_de_nacimiento(), bovino.get().getSexo());
                ventaClienteDetalleBovino.setCategoria(categoria);
                ventaClienteDetalleBovinos.add(ventaClienteDetalleBovino);
            }else {
                throw new IllegalStateException("bovino no encontrado");
            }
        }

        return ventaClienteDetalleBovinos;
    }

    @Override
    public List<VentaPropietaryClient> findByFechaBetween(LocalDate fecha1, LocalDate fecha2) {
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaRepository.findByFechaBetween(fecha1,fecha2);
        return getVentaPropietaryClients(ventaPropietaryClientList);
    }

    @Override
    public List<VentaPropietaryClient> findByClienteIdAndFechaBetween(long clienteid, LocalDate fecha1, LocalDate fecha2) {
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaRepository.findByClienteIdAndFechaBetween(clienteid,fecha1,fecha2);
        return getVentaPropietaryClients(ventaPropietaryClientList);
    }

    @Override
    public List<VentaPropietaryClient> findByCliente(long clienteId) {
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaRepository.findByCliente(clienteId);
        return getVentaPropietaryClients(ventaPropietaryClientList);
    }

    private List<VentaPropietaryClient> getVentaPropietaryClients(List<VentaPropietaryClient> ventaPropietaryClientList) {
        List<VentaPropietaryClient> UpdatedVentaPropietaryClientList=new ArrayList<>();
        for (VentaPropietaryClient ventaPropietaryClient:ventaPropietaryClientList){
            double totalVenta=0;
            List<DetallesVentasDTO> detallesVentasDTOS=detalleVentaRepository.findByVentaId(ventaPropietaryClient.getId());
            for (DetallesVentasDTO detallesVentasDTO:detallesVentasDTOS){
                totalVenta+=detallesVentasDTO.getPrecio() * detallesVentasDTO.getPeso();

            }
            VentaPropietaryClient ventaPropietaryClient1=new VentaPropietaryClient(ventaPropietaryClient.getId(),
                    ventaPropietaryClient.getFecha(),ventaPropietaryClient.getLugar(),ventaPropietaryClient.getCliente(),
                    totalVenta);

            UpdatedVentaPropietaryClientList.add(ventaPropietaryClient1);

        }
        return UpdatedVentaPropietaryClientList;
    }


}
