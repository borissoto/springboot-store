package com.gamla.admin.repositories;

import com.gamla.admin.models.almacen.AlmacenCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by root on 6/5/17.
 */
public interface AlmacenCategoriaRepository extends JpaRepository<AlmacenCategoria, Long> {

    public List<AlmacenCategoria> findByAlmacenCatalogo_CatalogoId(Long id);

    public Long countByAlmacenCatalogoCatalogoId(Long catalogoId);


}
