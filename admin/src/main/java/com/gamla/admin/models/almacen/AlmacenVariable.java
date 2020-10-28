package com.gamla.admin.models.almacen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Owner on 08/08/2017.
 */

@Entity
public class AlmacenVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long variableId;
    private String variable;
    private String valor;
    private String descripcion;

    public AlmacenVariable() {
    }

    public AlmacenVariable(String variable, String valor, String descripcion) {
        this.variable = variable;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public Long getVariableId() {
        return variableId;
    }

    public void setVariableId(Long variableId) {
        this.variableId = variableId;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "AlmacenVariable{" +
                "variableId=" + variableId +
                ", variable='" + variable + '\'' +
                ", valor='" + valor + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
