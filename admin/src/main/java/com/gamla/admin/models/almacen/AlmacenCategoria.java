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
public class AlmacenCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoriaId;
    private String categoria;
    private String descripcion;
    private String codigo;
    private Date reg_sistema;


    @ManyToOne
    @JoinColumn(name = "catalogoId")
    private AlmacenCatalogo almacenCatalogo;

    @JsonIgnore
    @OneToMany(mappedBy = "almacenCategoria")
    private List<AlmacenProducto> almacenProducto;

    public AlmacenCategoria() {
    }

    public AlmacenCategoria(String categoria, String descripcion, String codigo, Date reg_sistema, AlmacenCatalogo almacenCatalogo, List<AlmacenProducto> almacenProducto) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.reg_sistema = reg_sistema;
        this.almacenCatalogo = almacenCatalogo;
        this.almacenProducto = almacenProducto;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public AlmacenCatalogo getAlmacenCatalogo() {
        return almacenCatalogo;
    }

    public void setAlmacenCatalogo(AlmacenCatalogo almacenCatalogo) {
        this.almacenCatalogo = almacenCatalogo;
    }

    public List<AlmacenProducto> getAlmacenProducto() {
        return almacenProducto;
    }

    public void setAlmacenProducto(List<AlmacenProducto> almacenProducto) {
        this.almacenProducto = almacenProducto;
    }

    @Override
    public String toString() {
        return "AlmacenCategoria{" +
                "categoriaId=" + categoriaId +
                ", categoria='" + categoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", codigo='" + codigo + '\'' +
                ", reg_sistema=" + reg_sistema +
                ", almacenCatalogo=" + almacenCatalogo +
                ", almacenProducto=" + almacenProducto +
                '}';
    }
}
