package com.gamla.admin.models.admin;

import com.gamla.admin.models.almacen.AlmacenComprobante;
import com.gamla.admin.models.almacen.AlmacenIngreso;
import com.gamla.admin.models.almacen.AlmacenProducto;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Owner on 20/07/2017.
 */
@Entity
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String username;
    private String password;
    private String email;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private AdminRole adminRole;

    @OneToMany(mappedBy = "adminUser")
    private List<AlmacenProducto> almacenProducto;

    @OneToMany(mappedBy = "adminUser")
    private List<AlmacenComprobante> almacenComprobante;

    @OneToMany(mappedBy = "adminUser")
    private List<AlmacenIngreso> almacenIngreso;

    public AdminUser() {
    }

    public AdminUser(String nombre, String apellido1, String apellido2, String username, String password, String email, String telefono, AdminRole adminRole, List<AlmacenProducto> almacenProducto, List<AlmacenComprobante> almacenComprobante, List<AlmacenIngreso> almacenIngreso) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.adminRole = adminRole;
        this.almacenProducto = almacenProducto;
        this.almacenComprobante = almacenComprobante;
        this.almacenIngreso = almacenIngreso;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    public List<AlmacenProducto> getAlmacenProducto() {
        return almacenProducto;
    }

    public void setAlmacenProducto(List<AlmacenProducto> almacenProducto) {
        this.almacenProducto = almacenProducto;
    }

    public List<AlmacenComprobante> getAlmacenComprobante() {
        return almacenComprobante;
    }

    public void setAlmacenComprobante(List<AlmacenComprobante> almacenComprobante) {
        this.almacenComprobante = almacenComprobante;
    }

    public List<AlmacenIngreso> getAlmacenIngreso() {
        return almacenIngreso;
    }

    public void setAlmacenIngreso(List<AlmacenIngreso> almacenIngreso) {
        this.almacenIngreso = almacenIngreso;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "userId=" + userId +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", adminRole=" + adminRole +
                ", almacenProducto=" + almacenProducto +
                ", almacenComprobante=" + almacenComprobante +
                ", almacenIngreso=" + almacenIngreso +
                '}';
    }
}
