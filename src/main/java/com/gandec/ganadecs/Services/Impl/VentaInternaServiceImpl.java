package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.Bovinos.BovinesDTO;
import com.gandec.ganadecs.DTO.Detalles_venta_interna.DetalleVentaInternaDTO;
import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor;
import com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedorBovino;
import com.gandec.ganadecs.Entity.*;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Util.CalculadoraEdadUtil;
import com.gandec.ganadecs.Repository.*;
import com.gandec.ganadecs.Services.VentaInternaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaInternaServiceImpl implements VentaInternaService {

private final ClienteReository clienteReository;
private final VentaInternaRepository ventaInternaRepository;
private final PropietariosRepository propietariosRepository;
private final BovinoRepository bovinoRepository;
private final DetalleVentaRepository detalleVentaRepository;
private final DetalleVentaInternaRepository detalleVentaInternaRepository;
private final CalculadoraEdadUtil calculadoraEdadUtil;

    @Override
    public boolean BovinoVendido(Bovino bovino) {
        List<Detalle_Venta> detalle_ventas=detalleVentaRepository.findByBovino(bovino);
        return !detalle_ventas.isEmpty();
          }

    @Override
    public Optional<VentaInternaCompradorVendedorBovino> verifificarventaInternaAnterior(String numero, long propietarioId) {
        return ventaInternaRepository.verifificarventaInternaAnterior(numero,propietarioId);
    }

    @Override
    public void save(VentaInterna ventaInterna, List<DetalleVentaInterna> detalleVentaInternaList,
                     long IdCliente, long IdPropietario) {

        List<Bovino> bovinoList=new ArrayList<>();
        Propietario vendedor=propietariosRepository.findById(IdPropietario).orElseThrow(()->
                new ResourceNotFoundExcepcion("Propietario ","id", IdPropietario));

        if (ventaInterna!=null && detalleVentaInternaList!=null) {
            ventaInterna.setDetalle_ventas_internas(detalleVentaInternaList);
            for (DetalleVentaInterna detalleVentaInterna : detalleVentaInternaList) {
                detalleVentaInterna.setVentaInterna(ventaInterna);
            }
            for (DetalleVentaInterna detalleVentaInterna : detalleVentaInternaList) {
                boolean bovinovendido = BovinoVendido(detalleVentaInterna.getBovino());
                if (bovinovendido) {
                    throw new RuntimeException("El bovino numero :" + detalleVentaInterna.getBovino().getNumero() + " ya fue vendido ");
                }
                Optional<VentaInternaCompradorVendedorBovino> ventaInternaCompradorVendedorBovinoOptional=
                        ventaInternaRepository.verifificarventaInternaAnterior(detalleVentaInterna.getBovino().getNumero(),
                                IdPropietario);
                if (ventaInternaCompradorVendedorBovinoOptional.isPresent()){
                    throw new RuntimeException("El bovino numero: " + ventaInternaCompradorVendedorBovinoOptional.get().

                            getBovino()+
                            " ya fue vendido en la  fecha "+ventaInternaCompradorVendedorBovinoOptional.get().getFecha()
                            + " por "+
                            ventaInternaCompradorVendedorBovinoOptional.get().getVendedor()+" al señor(a) "
                            + ventaInternaCompradorVendedorBovinoOptional.get().getComprador());
                }



            }
            Cliente cliente = clienteReository.findById(IdCliente).orElseThrow(() ->
                    new ResourceNotFoundExcepcion("Client ", "id", IdCliente));
            ventaInterna.setCliente(cliente);
            ventaInterna.setPropietario(vendedor);
            for (DetalleVentaInterna detalleVentaInterna : detalleVentaInternaList) {
                bovinoList.add(detalleVentaInterna.getBovino());
            }
            PropietaryComboDto propietaryComboDto=propietariosRepository.findPropietaryComboDtoByNombresAndApellidos
                    (cliente.getNombres(),cliente.getApellidos());


            Propietario propietary=propietariosRepository.findById(propietaryComboDto.getId()).orElseThrow(()->
                    new ResourceNotFoundExcepcion("Propietario ","id", IdPropietario));

            ventaInternaRepository.save(ventaInterna);
            for (Bovino bovino : bovinoList) {
                bovinoRepository.UpdatePropietarioEnBovino(bovino.getNumero(),propietary);
            }
        }else {
            assert ventaInterna != null;
            throw new ResourceNotFoundExcepcion("Venta","id",ventaInterna.getId());
        }
    }

    @Override
    public List<VentaInternaCompradorVendedor> getAll() {
        List<VentaInternaCompradorVendedor> ventaInternaCompradorVendedorList=ventaInternaRepository.getAll();
        return GetVentaInternaCompradorVendedor(ventaInternaCompradorVendedorList);
    }

    @Override
    public List<VentaInternaCompradorVendedor> findAllByAnnoAndPropietarioIdOrderByFecha(int anno, long propietarioId) {
        List<VentaInternaCompradorVendedor> ventaInternaCompradorVendedorList=ventaInternaRepository.findAllByAnnoAndPropietarioIdOrderByFecha(anno,propietarioId);
        return GetVentaInternaCompradorVendedor(ventaInternaCompradorVendedorList);
    }

    @Override
    public List<VentaInternaCompradorVendedor> findAllByPropietarioIdOrderByFecha(long propietarioId) {
        List<VentaInternaCompradorVendedor> ventaInternaCompradorVendedorList=ventaInternaRepository.findAllByPropietarioIdOrderByFecha(propietarioId);
        return GetVentaInternaCompradorVendedor(ventaInternaCompradorVendedorList);
    }

    @Override
    public List<VentaInternaCompradorVendedor> findByFecha(LocalDate fecha) {
        List<VentaInternaCompradorVendedor> ventaInternaCompradorVendedorList=ventaInternaRepository.findByFecha(fecha);
        return GetVentaInternaCompradorVendedor(ventaInternaCompradorVendedorList);
    }

    @Override
    public List<VentaInternaCompradorVendedor> findByFechaBetween(LocalDate fecha1, LocalDate fecha2) {
        List<VentaInternaCompradorVendedor> ventaInternaCompradorVendedorList=ventaInternaRepository.findByFechaBetween(fecha1,fecha2);
        return GetVentaInternaCompradorVendedor(ventaInternaCompradorVendedorList);
    }

    @Override
    public List<VentaInternaCompradorVendedor> findByPropietarioIdAndFechaBetween(long propietarioId, LocalDate fecha1, LocalDate fecha2) {
        List<VentaInternaCompradorVendedor> ventaInternaCompradorVendedorList=ventaInternaRepository.findByPropietarioIdAndFechaBetween(propietarioId,fecha1,fecha2);
        return GetVentaInternaCompradorVendedor(ventaInternaCompradorVendedorList);
    }

    @Override
    public List<VentaInternaCompradorVendedor> findByCliente(long clienteId) {
        List<VentaInternaCompradorVendedor> ventaInternaCompradorVendedorList=ventaInternaRepository.findByCliente(clienteId);
        return GetVentaInternaCompradorVendedor(ventaInternaCompradorVendedorList);
    }

    @Override
    public List<VentaInternaCompradorVendedorBovino> findByDetalle_ventas_internasBovinoNumero(String numero) {
        List<VentaInternaCompradorVendedorBovino> ventaInternaCompradorVendedorBovino=new ArrayList<>();
         List<VentaInternaCompradorVendedorBovino> ventaInternaCompradorVendedorBovinoList  =  ventaInternaRepository.findByDetalle_ventas_internasBovinoNumero(numero);
       for (VentaInternaCompradorVendedorBovino ventaInternaCompradorVendedorBovino1:ventaInternaCompradorVendedorBovinoList){
           Optional<BovinesDTO> bovino = bovinoRepository.findBovinoByNumero(numero);
           String Categoria=calculadoraEdadUtil.calcularCategoria(bovino.get().getFecha_de_nacimiento(),bovino.get().getSexo());
              ventaInternaCompradorVendedorBovino1.setCategoria(Categoria);
                ventaInternaCompradorVendedorBovino.add(ventaInternaCompradorVendedorBovino1);
       }
        return ventaInternaCompradorVendedorBovino;
    }




    private List<VentaInternaCompradorVendedor> GetVentaInternaCompradorVendedor(List<VentaInternaCompradorVendedor> ventaInternaCompradorVendedorList){
        List<VentaInternaCompradorVendedor> UpdatedVentaInternaCompradorVendedorList=new ArrayList<>();
        for (VentaInternaCompradorVendedor ventaInternaCompradorVendedor:ventaInternaCompradorVendedorList){
            List<DetalleVentaInternaDTO> detalleVentaInternaList=detalleVentaInternaRepository.findByVentaInternaId(ventaInternaCompradorVendedor.getId());
            double total_venta_Interna=0;
            for (DetalleVentaInternaDTO detalleVentaInternaDTO:detalleVentaInternaList){
                total_venta_Interna+=detalleVentaInternaDTO.getPrecio()*detalleVentaInternaDTO.getPeso();
            }
            VentaInternaCompradorVendedor ventaInternaCompradorVendedor1=new VentaInternaCompradorVendedor(ventaInternaCompradorVendedor.getId(),
                    ventaInternaCompradorVendedor.getFecha(),ventaInternaCompradorVendedor.getComprador(),
                    ventaInternaCompradorVendedor.getVendedor(),total_venta_Interna);
            UpdatedVentaInternaCompradorVendedorList.add(ventaInternaCompradorVendedor1);

        }
        return UpdatedVentaInternaCompradorVendedorList;
    }
}
