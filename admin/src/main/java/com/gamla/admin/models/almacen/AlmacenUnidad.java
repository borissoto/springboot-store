package com.gamla.admin.models.almacen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gamla.admin.config.CustomDateSerializer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 6/3/17.
 */

@Entity
public class AlmacenUnidad {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long unidadId;
    private String unidadMedida;
    private String abreviatura;
    private Date reg_sistema;

    @JsonIgnore
    @OneToMany(mappedBy = "almacenUnidad")
    private List<AlmacenProducto> almacenProducto;

    public AlmacenUnidad() {
    }

    public AlmacenUnidad(String unidadMedida, String abreviatura, Date reg_sistema, List<AlmacenProducto> almacenProducto) {
        this.unidadMedida = unidadMedida;
        this.abreviatura = abreviatura;
        this.reg_sistema = reg_sistema;
        this.almacenProducto = almacenProducto;
    }

    public Long getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(Long unidadId) {
        this.unidadId = unidadId;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getReg_sistema() {
        return reg_sistema;
    }

    public void setReg_sistema(Date reg_sistema) {
        this.reg_sistema = reg_sistema;
    }

    public List<AlmacenProducto> getAlmacenProducto() {
        return almacenProducto;
    }

    public void setAlmacenProducto(List<AlmacenProducto> almacenProducto) {
        this.almacenProducto = almacenProducto;
    }

    @Override
    public String toString() {
        return "AlmacenUnidad{" +
                "unidadId=" + unidadId +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                ", reg_sistema=" + reg_sistema +
                ", almacenProducto=" + almacenProducto +
                '}';
    }
}
