package com.gamla.admin.models.almacen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gamla.admin.config.CustomDateSerializer;
import com.gamla.admin.models.admin.AdminUser;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 6/3/17.
 */
@Entity
public class AlmacenIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ingresoId;

    private BigDecimal cantidad;
    private BigDecimal costoUnitario;
    private BigDecimal costoTotal;
    private BigDecimal precioTemporal;
    private String descripcion;
    private Date vencimiento;
    private BigDecimal porcentajeTemporal;
    private Date reg_sistema;

    @ManyToOne
    @JoinColumn(name = "productoId")
    private AlmacenProducto almacenProducto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "comprobanteId")
    private AlmacenComprobante almacenComprobante;

    @OneToMany(mappedBy = "almacenIngreso")
    private List<AlmacenProductoCodigo> almacenProductoCodigo;

    @ManyToOne
    @JoinColumn(name = "adminUserId")
    private AdminUser adminUser;

    public AlmacenIngreso() {
    }

    public AlmacenIngreso(BigDecimal cantidad, BigDecimal costoUnitario, BigDecimal costoTotal, BigDecimal precioTemporal, String descripcion, Date vencimiento, BigDecimal porcentajeTemporal, Date reg_sistema, AlmacenProducto almacenProducto, AlmacenComprobante almacenComprobante, List<AlmacenProductoCodigo> almacenProductoCodigo, AdminUser adminUser) {
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.costoTotal = costoTotal;
        this.precioTemporal = precioTemporal;
        this.descripcion = descripcion;
        this.vencimiento = vencimiento;
        this.porcentajeTemporal = porcentajeTemporal;
        this.reg_sistema = reg_sistema;
        this.almacenProducto = almacenProducto;
        this.almacenComprobante = almacenComprobante;
        this.almacenProductoCodigo = almacenProductoCodigo;
        this.adminUser = adminUser;
    }

    public Long getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(Long ingresoId) {
        this.ingresoId = ingresoId;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public BigDecimal getPrecioTemporal() {
        return precioTemporal;
    }

    public void setPrecioTemporal(BigDecimal precioTemporal) {
        this.precioTemporal = precioTemporal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public BigDecimal getPorcentajeTemporal() {
        return porcentajeTemporal;
    }

    public void setPorcentajeTemporal(BigDecimal porcentajeTemporal) {
        this.porcentajeTemporal = porcentajeTemporal;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getReg_sistema() {
        return reg_sistema;
    }

    public void setReg_sistema(Date reg_sistema) {
        this.reg_sistema = reg_sistema;
    }

    public AlmacenProducto getAlmacenProducto() {
        return almacenProducto;
    }

    public void setAlmacenProducto(AlmacenProducto almacenProducto) {
        this.almacenProducto = almacenProducto;
    }

    public AlmacenComprobante getAlmacenComprobante() {
        return almacenComprobante;
    }

    public void setAlmacenComprobante(AlmacenComprobante almacenComprobante) {
        this.almacenComprobante = almacenComprobante;
    }

    public List<AlmacenProductoCodigo> getAlmacenProductoCodigo() {
        return almacenProductoCodigo;
    }

    public void setAlmacenProductoCodigo(List<AlmacenProductoCodigo> almacenProductoCodigo) {
        this.almacenProductoCodigo = almacenProductoCodigo;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public String toString() {
        return "AlmacenIngreso{" +
                "ingresoId=" + ingresoId +
                ", cantidad=" + cantidad +
                ", costoUnitario=" + costoUnitario +
                ", costoTotal=" + costoTotal +
                ", precioTemporal=" + precioTemporal +
                ", descripcion='" + descripcion + '\'' +
                ", vencimiento=" + vencimiento +
                ", porcentajeTemporal=" + porcentajeTemporal +
                ", reg_sistema=" + reg_sistema +
                ", almacenProducto=" + almacenProducto +
                ", almacenComprobante=" + almacenComprobante +
                ", almacenProductoCodigo=" + almacenProductoCodigo +
                ", adminUser=" + adminUser +
                '}';
    }
}
