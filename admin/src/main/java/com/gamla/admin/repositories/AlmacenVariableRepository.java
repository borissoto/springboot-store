package com.gamla.admin.repositories;

import com.gamla.admin.models.almacen.AlmacenVariable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Owner on 08/08/2017.
 */
public interface AlmacenVariableRepository extends JpaRepository<AlmacenVariable,Long> {
    public List<AlmacenVariable> findByVariable(String nombreVariable);

}
