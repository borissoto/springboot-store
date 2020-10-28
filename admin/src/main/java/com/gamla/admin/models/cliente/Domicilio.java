package com.gamla.admin.models.cliente;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Owner on 20/07/2017.
 */
@Entity
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long domicilioId;

    private String ciudad;
    private String empresa;
    private String zona;
    private String calle;
    private String numero;
    private String edificio;
    private String piso;
    private String oficina;

    private Date reg_sistema;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    public Domicilio() {
    }

    public Domicilio(String ciudad, String empresa, String zona, String calle, String numero, String edificio, String piso, String oficina, Date reg_sistema, Cliente cliente) {
        this.ciudad = ciudad;
        this.empresa = empresa;
        this.zona = zona;
        this.calle = calle;
        this.numero = numero;
        this.edificio = edificio;
        this.piso = piso;
        this.oficina = oficina;
        this.reg_sistema = reg_sistema;
        this.cliente = cliente;
    }

    public Long getDomicilioId() {
        return domicilioId;
    }

    public void setDomicilioId(Long domicilioId) {
        this.domicilioId = domicilioId;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public Date getReg_sistema() {
        return reg_sistema;
    }

    public void setReg_sistema(Date reg_sistema) {
        this.reg_sistema = reg_sistema;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "domicilioId=" + domicilioId +
                ", ciudad='" + ciudad + '\'' +
                ", empresa='" + empresa + '\'' +
                ", zona='" + zona + '\'' +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", edificio='" + edificio + '\'' +
                ", piso='" + piso + '\'' +
                ", oficina='" + oficina + '\'' +
                ", reg_sistema=" + reg_sistema +
                ", cliente=" + cliente +
                '}';
    }
}
