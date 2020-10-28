package com.gamla.admin.repositories;

import com.gamla.admin.models.almacen.AlmacenProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by root on 6/19/17.
 */
public interface AlmacenProductoRepository extends JpaRepository<AlmacenProducto,Long> {

    public List<AlmacenProducto> findByAlmacenCategoria_CategoriaId(Long id);

    public Long countByAlmacenCategoriaCategoriaId(Long categoriaId);

}
