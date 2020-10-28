package com.gamla.admin.models.utils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by root on 6/3/17.
 */
@Entity
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long sedeId;
    private String sede;
    private String sigla;

    public Sede() {
    }

    public Sede(String sede, String sigla) {
        this.sede = sede;
        this.sigla = sigla;
    }

    public Long getSedeId() {
        return sedeId;
    }

    public void setSedeId(Long sedeId) {
        this.sedeId = sedeId;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "sedeId=" + sedeId +
                ", sede='" + sede + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}
