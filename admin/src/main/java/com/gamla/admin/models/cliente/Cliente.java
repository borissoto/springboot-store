package com.gamla.admin.models.cliente;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Owner on 20/07/2017.
 */
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long clienteId;

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String username;
    private String password;
    private String email;
    private Boolean activo;
    private Date reg_sistema;

    @OneToMany(mappedBy = "cliente")
    private List<ClienteTelefono> clienteTelefono;

    @OneToMany(mappedBy = "cliente")
    private List<Domicilio> domicilio;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido1, String apellido2, String username, String password, String email, Boolean activo, Date reg_sistema, List<ClienteTelefono> clienteTelefono, List<Domicilio> domicilio) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.username = username;
        this.password = password;
        this.email = email;
        this.activo = activo;
        this.reg_sistema = reg_sistema;
        this.clienteTelefono = clienteTelefono;
        this.domicilio = domicilio;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getReg_sistema() {
        return reg_sistema;
    }

    public void setReg_sistema(Date reg_sistema) {
        this.reg_sistema = reg_sistema;
    }

    public List<ClienteTelefono> getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(List<ClienteTelefono> clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    public List<Domicilio> getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(List<Domicilio> domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", activo=" + activo +
                ", reg_sistema=" + reg_sistema +
                ", clienteTelefono=" + clienteTelefono +
                ", domicilio=" + domicilio +
                '}';
    }
}
