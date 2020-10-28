package com.gamla.admin.models.almacen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gamla.admin.config.CustomDateSerializer;
import com.gamla.admin.models.admin.AdminUser;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 6/3/17.
 */
@Entity
public class AlmacenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productoId;
    @NotNull
    private String producto;
    private BigDecimal cantidad;
    private BigDecimal precio;
    private BigDecimal oferta;
    private String caracteristica;
    private String descripcion;
    private String codigo;
    private String marca;
    private Boolean estado;
    private BigDecimal porcentaje;
    private Date reg_sistema;

    @Transient
    private MultipartFile imagenproducto;

    @ManyToOne
    @JoinColumn(name = "unidadId")
    private AlmacenUnidad almacenUnidad;

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private AlmacenCategoria almacenCategoria;

    @JsonIgnore
    @OneToMany(mappedBy = "almacenProducto")
    private List<AlmacenIngreso> almacenIngreso;

    @ManyToOne
    @JoinColumn(name = "adminUserId")
    private AdminUser adminUser;

    public AlmacenProducto() {
    }

    public AlmacenProducto(String producto, BigDecimal cantidad, BigDecimal precio, BigDecimal oferta, String caracteristica, String descripcion, String codigo, String marca, Boolean estado, BigDecimal porcentaje, Date reg_sistema, MultipartFile imagenproducto, AlmacenUnidad almacenUnidad, AlmacenCategoria almacenCategoria, List<AlmacenIngreso> almacenIngreso, AdminUser adminUser) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.oferta = oferta;
        this.caracteristica = caracteristica;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.marca = marca;
        this.estado = estado;
        this.porcentaje = porcentaje;
        this.reg_sistema = reg_sistema;
        this.imagenproducto = imagenproducto;
        this.almacenUnidad = almacenUnidad;
        this.almacenCategoria = almacenCategoria;
        this.almacenIngreso = almacenIngreso;
        this.adminUser = adminUser;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getOferta() {
        return oferta;
    }

    public void setOferta(BigDecimal oferta) {
        this.oferta = oferta;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getReg_sistema() {
        return reg_sistema;
    }

    public void setReg_sistema(Date reg_sistema) {
        this.reg_sistema = reg_sistema;
    }

    public MultipartFile getImagenproducto() {
        return imagenproducto;
    }

    public void setImagenproducto(MultipartFile imagenproducto) {
        this.imagenproducto = imagenproducto;
    }

    public AlmacenCategoria getAlmacenCategoria() {
        return almacenCategoria;
    }

    public void setAlmacenCategoria(AlmacenCategoria almacenCategoria) {
        this.almacenCategoria = almacenCategoria;
    }

    public List<AlmacenIngreso> getAlmacenIngreso() {
        return almacenIngreso;
    }

    public void setAlmacenIngreso(List<AlmacenIngreso> almacenIngreso) {
        this.almacenIngreso = almacenIngreso;
    }

    public AlmacenUnidad getAlmacenUnidad() {
        return almacenUnidad;
    }

    public void setAlmacenUnidad(AlmacenUnidad almacenUnidad) {
        this.almacenUnidad = almacenUnidad;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public String toString() {
        return "AlmacenProducto{" +
                "productoId=" + productoId +
                ", producto='" + producto + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", oferta=" + oferta +
                ", caracteristica='" + caracteristica + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", codigo='" + codigo + '\'' +
                ", marca='" + marca + '\'' +
                ", estado=" + estado +
                ", porcentaje=" + porcentaje +
                ", reg_sistema=" + reg_sistema +
                ", imagenproducto=" + imagenproducto +
                ", almacenUnidad=" + almacenUnidad +
                ", almacenCategoria=" + almacenCategoria +
                ", almacenIngreso=" + almacenIngreso +
                ", adminUser=" + adminUser +
                '}';
    }
}
