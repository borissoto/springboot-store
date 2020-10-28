package com.gamla.admin.models.almacen;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gamla.admin.config.CustomDateSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Owner on 19/07/2017.
 */
@Entity
public class AlmacenProductoCodigo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productoCodigoId;
    private String codigo_barras;
    private BigDecimal cantidad;
    private Date vecimiento;

    @ManyToOne
    @JoinColumn(name="ingresoId")
    private AlmacenIngreso almacenIngreso;

    @ManyToOne
    @JoinColumn(name = "sedeId")
    private AlmacenSede almacenSede;

    public AlmacenProductoCodigo() {
    }

    public AlmacenProductoCodigo(String codigo_barras, BigDecimal cantidad, Date vecimiento, AlmacenIngreso almacenIngreso, AlmacenSede almacenSede) {
        this.codigo_barras = codigo_barras;
        this.cantidad = cantidad;
        this.vecimiento = vecimiento;
        this.almacenIngreso = almacenIngreso;
        this.almacenSede = almacenSede;
    }

    public Long getProductoCodigoId() {
        return productoCodigoId;
    }

    public void setProductoCodigoId(Long productoCodigoId) {
        this.productoCodigoId = productoCodigoId;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getVecimiento() {
        return vecimiento;
    }

    public void setVecimiento(Date vecimiento) {
        this.vecimiento = vecimiento;
    }

    public AlmacenIngreso getAlmacenIngreso() {
        return almacenIngreso;
    }

    public void setAlmacenIngreso(AlmacenIngreso almacenIngreso) {
        this.almacenIngreso = almacenIngreso;
    }

    public AlmacenSede getAlmacenSede() {
        return almacenSede;
    }

    public void setAlmacenSede(AlmacenSede almacenSede) {
        this.almacenSede = almacenSede;
    }

    @Override
    public String toString() {
        return "AlmacenProductoCodigo{" +
                "productoCodigoId=" + productoCodigoId +
                ", codigo_barras='" + codigo_barras + '\'' +
                ", cantidad=" + cantidad +
                ", vecimiento=" + vecimiento +
                ", almacenIngreso=" + almacenIngreso +
                ", almacenSede=" + almacenSede +
                '}';
    }
}
