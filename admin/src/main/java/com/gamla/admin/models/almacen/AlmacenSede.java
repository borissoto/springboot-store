package com.gamla.admin.models.almacen;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class AlmacenSede {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long sedeId;
    private String sede;
    private String sigla;
    private String descripcion;

    @OneToMany(mappedBy = "almacenSede")
    private List<AlmacenProductoCodigo> almacenProductoCodigo;

    @JsonIgnore
    @OneToMany(mappedBy = "almacenSede")
    private List<AlmacenComprobante> almacenComprobante;

    public AlmacenSede() {
    }

    public AlmacenSede(String sede, String sigla, String descripcion, List<AlmacenProductoCodigo> almacenProductoCodigo, List<AlmacenComprobante> almacenComprobante) {
        this.sede = sede;
        this.sigla = sigla;
        this.descripcion = descripcion;
        this.almacenProductoCodigo = almacenProductoCodigo;
        this.almacenComprobante = almacenComprobante;
    }

    public Long getSedeId() {
        return sedeId;
    }

    public void setSedeId(Long sedeId) {
        this.sedeId = sedeId;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<AlmacenProductoCodigo> getAlmacenProductoCodigo() {
        return almacenProductoCodigo;
    }

    public void setAlmacenProductoCodigo(List<AlmacenProductoCodigo> almacenProductoCodigo) {
        this.almacenProductoCodigo = almacenProductoCodigo;
    }

    public List<AlmacenComprobante> getAlmacenComprobante() {
        return almacenComprobante;
    }

    public void setAlmacenComprobante(List<AlmacenComprobante> almacenComprobante) {
        this.almacenComprobante = almacenComprobante;
    }

    @Override
    public String toString() {
        return "AlmacenSede{" +
                "sedeId=" + sedeId +
                ", sede='" + sede + '\'' +
                ", sigla='" + sigla + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", almacenProductoCodigo=" + almacenProductoCodigo +
                ", almacenComprobante=" + almacenComprobante +
                '}';
    }
}
