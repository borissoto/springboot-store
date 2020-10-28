package com.gamla.admin.models.admin;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Owner on 20/07/2017.
 */
@Entity
public class AdminRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long roleId;

    private String role;
    private String descripcion;

    @OneToMany(mappedBy = "adminRole")
    private List<AdminUser> adminUser;

    public AdminRole() {
    }

    public AdminRole(String role, String descripcion, List<AdminUser> adminUser) {
        this.role = role;
        this.descripcion = descripcion;
        this.adminUser = adminUser;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<AdminUser> getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(List<AdminUser> adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", adminUser=" + adminUser +
                '}';
    }

}
