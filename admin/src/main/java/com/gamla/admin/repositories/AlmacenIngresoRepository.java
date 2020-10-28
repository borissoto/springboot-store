package com.gamla.admin.repositories;

import com.gamla.admin.models.almacen.AlmacenIngreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by boris on 6/30/17.
 */
public interface AlmacenIngresoRepository extends JpaRepository<AlmacenIngreso, Long>{

    public List<AlmacenIngreso> findByAlmacenComprobante_ComprobanteId(Long comprobanteId);
}
