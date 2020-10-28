package com.gamla.admin.models.almacen;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Owner on 08/08/2017.
 */

@Entity
public class AlmacenDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long documentoId;
    private String documento;
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "almacenDocumento")
    private List<AlmacenComprobante> almacenComprobante;

    public AlmacenDocumento() {
    }

    public AlmacenDocumento(String documento, String descripcion, List<AlmacenComprobante> almacenComprobante) {
        this.documento = documento;
        this.descripcion = descripcion;
        this.almacenComprobante = almacenComprobante;
    }

    public Long getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Long documentoId) {
        this.documentoId = documentoId;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<AlmacenComprobante> getAlmacenComprobante() {
        return almacenComprobante;
    }

    public void setAlmacenComprobante(List<AlmacenComprobante> almacenComprobante) {
        this.almacenComprobante = almacenComprobante;
    }

    @Override
    public String toString() {
        return "AlmacenDocumento{" +
                "documentoId=" + documentoId +
                ", documento='" + documento + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", almacenComprobante=" + almacenComprobante +
                '}';
    }
}
