package com.gamla.admin.models.almacen;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gamla.admin.config.CustomDateSerializer;
import com.gamla.admin.models.admin.AdminUser;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 6/3/17.
 */

@Entity
public class AlmacenComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long comprobanteId;

    private Long registro;
    private String proveedor;
    private Long nro_documento;
    private String observaciones;
    private Date fecha;
    private Date reg_sistema;


    @OneToMany(mappedBy = "almacenComprobante")
    private List<AlmacenIngreso> almacenIngreso;

    @ManyToOne
    @JoinColumn(name = "adminUserId")
    private AdminUser adminUser;

    @ManyToOne
    @JoinColumn(name = "almacenDocumento")
    private AlmacenDocumento almacenDocumento;

    @ManyToOne
    @JoinColumn(name = "almacenSede")
    private AlmacenSede almacenSede;

    public AlmacenComprobante() {
    }

    public AlmacenComprobante(Long registro, String proveedor, Long nro_documento, String observaciones, Date fecha, Date reg_sistema, List<AlmacenIngreso> almacenIngreso, AdminUser adminUser, AlmacenDocumento almacenDocumento, AlmacenSede almacenSede) {
        this.registro = registro;
        this.proveedor = proveedor;
        this.nro_documento = nro_documento;
        this.observaciones = observaciones;
        this.fecha = fecha;
        this.reg_sistema = reg_sistema;
        this.almacenIngreso = almacenIngreso;
        this.adminUser = adminUser;
        this.almacenDocumento = almacenDocumento;
        this.almacenSede = almacenSede;
    }

    public Long getComprobanteId() {
        return comprobanteId;
    }

    public void setComprobanteId(Long comprobanteId) {
        this.comprobanteId = comprobanteId;
    }

    public Long getRegistro() {
        return registro;
    }

    public void setRegistro(Long registro) {
        this.registro = registro;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Long getNro_documento() {
        return nro_documento;
    }

    public void setNro_documento(Long nro_documento) {
        this.nro_documento = nro_documento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getReg_sistema() {
        return reg_sistema;
    }

    public void setReg_sistema(Date reg_sistema) {
        this.reg_sistema = reg_sistema;
    }

    public List<AlmacenIngreso> getAlmacenIngreso() {
        return almacenIngreso;
    }

    public void setAlmacenIngreso(List<AlmacenIngreso> almacenIngreso) {
        this.almacenIngreso = almacenIngreso;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public AlmacenDocumento getAlmacenDocumento() {
        return almacenDocumento;
    }

    public void setAlmacenDocumento(AlmacenDocumento almacenDocumento) {
        this.almacenDocumento = almacenDocumento;
    }

    public AlmacenSede getAlmacenSede() {
        return almacenSede;
    }

    public void setAlmacenSede(AlmacenSede almacenSede) {
        this.almacenSede = almacenSede;
    }

    @Override
    public String toString() {
        return "AlmacenComprobante{" +
                "comprobanteId=" + comprobanteId +
                ", registro=" + registro +
                ", proveedor='" + proveedor + '\'' +
                ", nro_documento=" + nro_documento +
                ", observaciones='" + observaciones + '\'' +
                ", fecha=" + fecha +
                ", reg_sistema=" + reg_sistema +
                ", almacenIngreso=" + almacenIngreso +
                ", adminUser=" + adminUser +
                ", almacenDocumento=" + almacenDocumento +
                ", almacenSede=" + almacenSede +
                '}';
    }
}
