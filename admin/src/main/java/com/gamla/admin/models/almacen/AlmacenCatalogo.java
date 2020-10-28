package com.gamla.admin.models.almacen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gamla.admin.config.CustomDateSerializer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 6/5/17.
 */
@Entity
public class AlmacenCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long catalogoId;
    private String catalogo;
    private String descripcion;
    private String codigo;
    private Date reg_sistema;

    @JsonIgnore
    @OneToMany(mappedBy = "almacenCatalogo")
    private List<AlmacenCategoria> almacenCategoria;

    public AlmacenCatalogo() {
    }

    public AlmacenCatalogo(String catalogo, String descripcion, String codigo, Date reg_sistema, List<AlmacenCategoria> almacenCategoria) {
        this.catalogo = catalogo;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.reg_sistema = reg_sistema;
        this.almacenCategoria = almacenCategoria;
    }

    public Long getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(Long catalogoId) {
        this.catalogoId = catalogoId;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getReg_sistema() {
        return reg_sistema;
    }

    public void setReg_sistema(Date reg_sistema) {
        this.reg_sistema = reg_sistema;
    }

    public List<AlmacenCategoria> getAlmacenCategoria() {
        return almacenCategoria;
    }

    public void setAlmacenCategoria(List<AlmacenCategoria> almacenCategoria) {
        this.almacenCategoria = almacenCategoria;
    }

    @Override
    public String toString() {
        return "AlmacenCatalogo{" +
                "catalogoId=" + catalogoId +
                ", catalogo='" + catalogo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", codigo='" + codigo + '\'' +
                ", reg_sistema=" + reg_sistema +
                ", almacenCategoria=" + almacenCategoria +
                '}';
    }
}
