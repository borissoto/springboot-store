package com.gamla.admin.controllers;

import com.gamla.admin.config.FileUploadUtility;
import com.gamla.admin.models.almacen.*;
import com.gamla.admin.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 6/8/17.
 */
@RequestMapping("/admin")
@Controller
public class AlmacenAdminController {

    @Autowired
    AlmacenCatalogoRepository catalogoRepository;

    @Autowired
    AlmacenCategoriaRepository categoriaRepository;

    @Autowired
    AlmacenProductoRepository productoRepository;

    @Autowired
    AlmacenUnidadRepository unidadRepository;

    @Autowired
    AlmacenDocumentoRepository documentoRepository;

    @Autowired
    AlmacenSedeRepository sedeRepository;

    @Autowired
    AlmacenVariableRepository variableRepository;

    /**** models al view */
    @ModelAttribute("AlmacenCatalogo")
    public AlmacenCatalogo almacenCatalogo(){
        return new AlmacenCatalogo();
    }

    @ModelAttribute("AlmacenCategoria")
    public AlmacenCategoria almacenCategoria(){
        return new AlmacenCategoria();
    }

    @ModelAttribute("AlmacenProducto")
    public AlmacenProducto almacenProducto(){
        return new AlmacenProducto();
    }

    @ModelAttribute("UnidadMedida")
    public AlmacenUnidad unidad(){ return new AlmacenUnidad();}

    @ModelAttribute("TipoDocumento")
    public AlmacenDocumento documento(){ return new AlmacenDocumento();}

    @ModelAttribute("Sucursal")
    public AlmacenSede sede(){ return new AlmacenSede();}

    @ModelAttribute("Variable")
    public AlmacenVariable variable(){ return new AlmacenVariable();}

    @ModelAttribute("losCatalogos")
    public List<AlmacenCatalogo> getCatalogos(){
            List<AlmacenCatalogo> listaCatalogos = catalogoRepository.findAll();
        return listaCatalogos;
    }

    @ModelAttribute("unidades")
    public List<AlmacenUnidad> getUnidades(){
        List <AlmacenUnidad> almacenUnidadList = unidadRepository.findAll();
        return almacenUnidadList;
    }

    @ModelAttribute("losPorcentajes")
    public List<AlmacenVariable> getPorcentajes(){
        List<AlmacenVariable> listaVariables = variableRepository.findByVariable("PORCENTAJE");
        return listaVariables;
    }

    /***********Administracion de Almacenes*********************/

    /***********Administracion de Almacenes Catalogo*******************************************************************/

    @RequestMapping("/catalogos")
    public String administracion(){
        return "almacenAdminCatalogo";
    }

    @RequestMapping("/catalogos/lista")
    @ResponseBody
    public List<AlmacenCatalogo> listaCatalogo(){
        List<AlmacenCatalogo> catalogos = catalogoRepository.findAll();
        return catalogos;
    }

    /*@RequestMapping(value = "/catalogo/{catalogoId}", method = RequestMethod.GET)
    @ResponseBody
    public AlmacenCatalogo verCatalogo(@RequestParam("catalogoId") Long catalogoId){
        AlmacenCatalogo catalogo = catalogoRepository.findOne(catalogoId);
        return catalogo;
    }*/

    @RequestMapping(value = "/catalogo/guardar", method = RequestMethod.POST)
    public String guardarCatalogo(@Valid AlmacenCatalogo almacenCatalogo, Model model){
        Long count = catalogoRepository.count()+1;
        String codigo = "";
        if(count<10) codigo = "0"+ count;
        almacenCatalogo.setCodigo(codigo);

        almacenCatalogo.setReg_sistema(new Date());

        System.out.println("Guardando Catalogo");
        catalogoRepository.save(almacenCatalogo);
        /*model.addAttribute("verCatalogo", re);*/
        return "redirect:/admin/catalogos";
    }

    @RequestMapping(value = "/catalogo/editar/{id}", method = RequestMethod.POST)
    public String editarCatalogo(@PathVariable("id") Long id, @Valid AlmacenCatalogo almacenCatalogo){

        System.out.println("editando Catalogo"+ id);
        AlmacenCatalogo catalogo = catalogoRepository.findOne(id);
        catalogo.setCatalogo(almacenCatalogo.getCatalogo());
        catalogo.setDescripcion(almacenCatalogo.getDescripcion());
        //System.out.println("Editado "+catalogo);
        catalogoRepository.save(catalogo);
        return "redirect:/admin/catalogos";
    }

    @RequestMapping(value = "/catalogo/eliminar/{id}", method = RequestMethod.POST)
    public String elminarCatalogo(@PathVariable("id") Long id){

        System.out.println("eliminar Catalogo"+ id);
        //AlmacenCatalogo catalogo = catalogoRepository.findOne(id);
        //System.out.println("Editado "+catalogo);
        catalogoRepository.delete(id);
        return "redirect:/admin/catalogos";
    }


    /***********Administracion de Almacenes Categoria******************************************************************/

    @RequestMapping("/categorias")
    public String administracionCategoria(){
        return "almacenAdminCategoria";
    }

    @RequestMapping("/categorias/lista")
    @ResponseBody
    public List<AlmacenCategoria> listaCategorias(){
        List<AlmacenCategoria> categorias = categoriaRepository.findAll();
        return categorias;
    }

    /*@RequestMapping(value = "/catalogo/{catalogoId}", method = RequestMethod.GET)
    @ResponseBody
    public AlmacenCatalogo verCatalogo(@RequestParam("catalogoId") Long catalogoId){
        AlmacenCatalogo catalogo = catalogoRepository.findOne(catalogoId);
        return catalogo;
    }*/

    @RequestMapping(value = "/categoria/guardar", method = RequestMethod.POST)
    public String guardarCategoria(@Valid AlmacenCategoria almacenCategoria, Model model){

        //Long count = categoriaRepository.count()+1;
        String codigo = "";
        Long catId = almacenCategoria.getAlmacenCatalogo().getCatalogoId();
        AlmacenCatalogo catCodigo = catalogoRepository.findOne(catId);
        String catalogoCodigo = catCodigo.getCodigo();
        Long countByCategoria = categoriaRepository.countByAlmacenCatalogoCatalogoId(catId);
        Long contador = countByCategoria+1;

        if(countByCategoria<10) codigo = "0"+contador;

        almacenCategoria.setCodigo(catalogoCodigo+codigo);
        almacenCategoria.setReg_sistema(new Date());

        System.out.println("Guardando Categoria:" + catId + "cantidad por Catalogo" + countByCategoria + " catalogo cod: " + catalogoCodigo);

        categoriaRepository.save(almacenCategoria);
        /*model.addAttribute("verCatalogo", re);*/
        return "redirect:/admin/categorias";
    }

    @RequestMapping(value = "/categoria/editar/{id}", method = RequestMethod.POST)
    public String editarCategoria(@PathVariable("id") Long id, @Valid AlmacenCategoria almacenCategoria){

        System.out.println("editando Categoria"+ id);
        AlmacenCategoria categoria = categoriaRepository.findOne(id);
        categoria.setCategoria(almacenCategoria.getCategoria());
        categoria.setCodigo(almacenCategoria.getCodigo());
        categoria.setDescripcion(almacenCategoria.getDescripcion());
        categoria.setAlmacenCatalogo(almacenCategoria.getAlmacenCatalogo());
        //System.out.println("Editado "+catalogo);
        categoriaRepository.save(categoria);
        return "redirect:/admin/categorias";
    }

    @RequestMapping(value = "/categoria/eliminar/{id}", method = RequestMethod.POST)
    public String elminarCategoria(@PathVariable("id") Long id){

        System.out.println("eliminar Categoria"+ id);
        //AlmacenCatalogo catalogo = catalogoRepository.findOne(id);
        //System.out.println("Editado "+catalogo);
        categoriaRepository.delete(id);
        return "redirect:/admin/categorias";
    }


    /***********Administracion de Almacenes PRODUCtO*******************************************************************/

    @RequestMapping("/productos")
    public String administracionProductos(){
        return "almacenAdminProducto";
    }

    @RequestMapping("/productos/lista")
    @ResponseBody
    public List<AlmacenProducto> listaProductos(){
        List<AlmacenProducto> productos = productoRepository.findAll();
        return productos;
    }

    // Ajax para el select de categorias ppor catalogo
    @RequestMapping(value="/producto/selectcat")
    @ResponseBody
    public List<AlmacenCategoria> treatmentList(@RequestParam("catalogo") String catalogoId){
        Long catalogo = Long.valueOf(catalogoId);
        List<AlmacenCategoria> result =  categoriaRepository.findByAlmacenCatalogo_CatalogoId(catalogo);
        //System.out.println("ajax tGrupos" + result.toString());
        return result;
    }


    @RequestMapping(value = "/producto/guardar", method = RequestMethod.POST)
    public String guardarProducto(@Valid AlmacenProducto almacenProducto, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            System.out.println("Error Field En: "+result.getFieldError());
            return "redirect:/admin/productos";
        }else{
            //Long catId = almacenProducto.getAlmacenCategoria().getCategoriaId();
            //productoRepository.getOne(catId);
            String codigo = "";
            Long cateId = almacenProducto.getAlmacenCategoria().getCategoriaId();
            AlmacenCategoria cateCodigo = categoriaRepository.findOne(cateId);
            String categoriaCodigo = cateCodigo.getCodigo();
            Long countByProducto = productoRepository.countByAlmacenCategoriaCategoriaId(cateId);
            Long contador = countByProducto+1;

            if(countByProducto<1000) codigo = "0"+contador;
            if(countByProducto<100) codigo = "00"+contador;
            if(countByProducto<10) codigo = "000"+contador;

            almacenProducto.setCodigo(categoriaCodigo+codigo);
            almacenProducto.setReg_sistema(new Date());

            System.out.println("Guardando Producto:" + cateId + "cantidad por Catalogo" + countByProducto + " catalogo cod: " + categoriaCodigo);

            productoRepository.save(almacenProducto);
            if(!almacenProducto.getImagenproducto().getOriginalFilename().equals("")){
                //System.out.println("Imagen no es null");
                FileUploadUtility.uploadFile(request, almacenProducto.getImagenproducto(), almacenProducto.getProductoId());
            }
            return "redirect:/admin/productos";
        }

    }

    @RequestMapping(value = "/producto/editar/{id}", method = RequestMethod.POST)
    public String editarProducto(@PathVariable("id") Long id, @Valid AlmacenProducto almacenProducto){

        System.out.println("editando Producto"+ id);
        AlmacenProducto producto = productoRepository.findOne(id);
        producto.setProducto(almacenProducto.getProducto());
        producto.setPrecio(almacenProducto.getPrecio());
        producto.setOferta(almacenProducto.getOferta());
        producto.setCaracteristica(almacenProducto.getCaracteristica());
        producto.setMarca(almacenProducto.getMarca());
        producto.setDescripcion(almacenProducto.getDescripcion());
        producto.setCodigo(almacenProducto.getCodigo());
        producto.setEstado(almacenProducto.getEstado());


        producto.setAlmacenCategoria(almacenProducto.getAlmacenCategoria());

        //System.out.println("Editado "+catalogo);
        productoRepository.save(producto);
        return "redirect:/admin/productos";
    }

    @RequestMapping(value = "/producto/eliminar/{id}", method = RequestMethod.POST)
    public String elminarProducto(@PathVariable("id") Long id){

        System.out.println("eliminar Producto"+ id);
        productoRepository.delete(id);
        return "redirect:/admin/productos";
    }


    /***********Administracion de Almacenes Unidades*******************************************************************/

    @RequestMapping("/unidades")
    public String unidades(){
        return "almacenAdminUnidad";
    }

    @RequestMapping("/unidades/lista")
    @ResponseBody
    public List<AlmacenUnidad> listaUnidades(){
        List<AlmacenUnidad> unidades = unidadRepository.findAll();
        return unidades;
    }

    @RequestMapping(value = "/unidad/guardar", method = RequestMethod.POST)
    public String guardarUnidad(@Valid AlmacenUnidad almacenUnidad, BindingResult result){
        if(result.hasErrors()){
            System.out.println("hay errores al guardar");
            return "redirect:/admin/unidades";
        }else{
            System.out.println("Guardando AlmacenUnidad");
            unidadRepository.save(almacenUnidad);
            return "redirect:/admin/unidades";
        }

    }

    @RequestMapping(value = "/unidad/editar/{id}", method = RequestMethod.POST)
    public String editarUnidad(@PathVariable("id") Long id, @Valid AlmacenUnidad utilsUnidadAlmacen){

        System.out.println("editando AlmacenUnidad"+ id);
        AlmacenUnidad almacenUnidad = unidadRepository.findOne(id);
        almacenUnidad.setUnidadMedida(utilsUnidadAlmacen.getUnidadMedida());
        almacenUnidad.setAbreviatura(utilsUnidadAlmacen.getAbreviatura());
        //System.out.println("Editado "+catalogo);
        unidadRepository.save(almacenUnidad);
        return "redirect:/admin/unidades";
    }

    @RequestMapping(value = "/unidad/eliminar/{id}", method = RequestMethod.POST)
    public String eliminarUnidad(@PathVariable("id") Long id){
        System.out.println("eliminar Catalogo"+ id);
        unidadRepository.delete(id);
        return "redirect:/admin/unidades";
    }

    /***********Administracion de Almacenes Documento******************************************************************/

    @RequestMapping("/documentos")
    public String documentos(){
        return "almacenAdminDocumento";
    }

    @RequestMapping("/documentos/lista")
    @ResponseBody
    public List<AlmacenDocumento> listaDocumentos(){
        List<AlmacenDocumento> documentos = documentoRepository.findAll();
        return documentos;
    }

    @RequestMapping(value = "/documento/guardar", method = RequestMethod.POST)
    public String guardarDocumento(@Valid AlmacenDocumento almacenDocumento, Model model){
        System.out.println("Guardando documento");
        documentoRepository.save(almacenDocumento);
        /*model.addAttribute("verCatalogo", re);*/
        return "redirect:/admin/documentos";
    }

    @RequestMapping(value = "/documento/editar/{id}", method = RequestMethod.POST)
    public String editarDocumento(@PathVariable("id") Long id, @Valid AlmacenDocumento almacenDocumento){

        System.out.println("editando Documento"+ id);
        AlmacenDocumento documento = documentoRepository.findOne(id);
        documento.setDocumento(almacenDocumento.getDocumento());
        documento.setDescripcion(almacenDocumento.getDescripcion());
        documentoRepository.save(documento);
        return "redirect:/admin/documentos";
    }

    @RequestMapping(value = "/documento/eliminar/{id}", method = RequestMethod.POST)
    public String elminarDocumento(@PathVariable("id") Long id){

        System.out.println("eliminar Documento"+ id);
        //AlmacenCatalogo catalogo = catalogoRepository.findOne(id);
        //System.out.println("Editado "+catalogo);
        documentoRepository.delete(id);
        return "redirect:/admin/documentos";
    }

    /***********Administracion de Almacenes Sedes *********************************************************************/

    @RequestMapping("/sedes")
    public String sedes(){
        return "almacenAdminSede";
    }

    @RequestMapping("/sedes/lista")
    @ResponseBody
    public List<AlmacenSede> listaSedes(){
        List<AlmacenSede> sedes = sedeRepository.findAll();
        return sedes;
    }

    @RequestMapping(value = "/sede/guardar", method = RequestMethod.POST)
    public String guardarSede(@Valid AlmacenSede almacenSede, Model model){
        System.out.println("Guardando Sede");
        sedeRepository.save(almacenSede);
        /*model.addAttribute("verCatalogo", re);*/
        return "redirect:/admin/sedes";
    }

    @RequestMapping(value = "/sede/editar/{id}", method = RequestMethod.POST)
    public String editarSede(@PathVariable("id") Long id, @Valid AlmacenSede almacenSede){

        System.out.println("editando Sede"+ id);
        AlmacenSede sede = sedeRepository.findOne(id);
        sede.setSede(almacenSede.getSede());
        sede.setSigla(almacenSede.getSigla());
        sede.setDescripcion(almacenSede.getDescripcion());
        sedeRepository.save(sede);
        return "redirect:/admin/sedes";
    }

    @RequestMapping(value = "/sede/eliminar/{id}", method = RequestMethod.POST)
    public String elminarSede(@PathVariable("id") Long id){
        System.out.println("eliminar Sede"+ id);
        //AlmacenCatalogo catalogo = catalogoRepository.findOne(id);
        //System.out.println("Editado "+catalogo);
        sedeRepository.delete(id);
        return "redirect:/admin/sedes";
    }

    /***********Administracion de Variables Porcentajes *********************************************************************/

    @RequestMapping("/variables")
    public String variables(){
        return "almacenAdminVariables";
    }

    @RequestMapping("/variables/lista")
    @ResponseBody
    public List<AlmacenVariable> listaVariables(){
        List<AlmacenVariable> variables = variableRepository.findAll();
        return variables;
    }

    @RequestMapping(value = "/variable/guardar", method = RequestMethod.POST)
    public String guardarVariable(@Valid AlmacenVariable almacenVariable, Model model){
        System.out.println("Guardando Variable");
        variableRepository.save(almacenVariable);
        /*model.addAttribute("verCatalogo", re);*/
        return "redirect:/admin/variables";
    }

    @RequestMapping(value = "/variable/editar/{id}", method = RequestMethod.POST)
    public String editarVariable(@PathVariable("id") Long id, @Valid AlmacenVariable almacenVariable){

        System.out.println("editando Variable"+ id);
        AlmacenVariable variable = variableRepository.findOne(id);
        variable.setVariable(almacenVariable.getVariable());
        variable.setValor(almacenVariable.getValor());
        variable.setDescripcion(almacenVariable.getDescripcion());
        variableRepository.save(variable);
        return "redirect:/admin/variables";
    }

    @RequestMapping(value = "/variable/eliminar/{id}", method = RequestMethod.POST)
    public String elminarVariable(@PathVariable("id") Long id){
        System.out.println("eliminar Variable"+ id);
        //AlmacenCatalogo catalogo = catalogoRepository.findOne(id);
        //System.out.println("Editado "+catalogo);
        variableRepository.delete(id);
        return "redirect:/admin/variables";
    }


    /***********Administracion de Almacenes PRODUCtO PROCENTAJES LISTA ******************************************************/

    @RequestMapping("/prodporcentajes")
    public String porcentajesLista(){
        return "almacenAdminPorcentaje";
    }

    @RequestMapping("/prodporcentajes/lista")
    @ResponseBody
    public List<AlmacenProducto> listaProductosPorcentajes(){
        List<AlmacenProducto> productos = productoRepository.findAll();
        return productos;
    }

/*
    @RequestMapping(value = "/producto/guardar", method = RequestMethod.POST)
    public String guardarProducto(@Valid AlmacenProducto almacenProducto, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            System.out.println("Error Field En: "+result.getFieldError());
            return "redirect:/admin/productos";
        }else{
            //Long catId = almacenProducto.getAlmacenCategoria().getCategoriaId();
            //productoRepository.getOne(catId);
            String codigo = "";
            Long cateId = almacenProducto.getAlmacenCategoria().getCategoriaId();
            AlmacenCategoria cateCodigo = categoriaRepository.findOne(cateId);
            String categoriaCodigo = cateCodigo.getCodigo();
            Long countByProducto = productoRepository.countByAlmacenCategoriaCategoriaId(cateId);
            Long contador = countByProducto+1;

            if(countByProducto<1000) codigo = "0"+contador;
            if(countByProducto<100) codigo = "00"+contador;
            if(countByProducto<10) codigo = "000"+contador;

            almacenProducto.setCodigo(categoriaCodigo+codigo);
            almacenProducto.setReg_sistema(new Date());

            System.out.println("Guardando Producto:" + cateId + "cantidad por Catalogo" + countByProducto + " catalogo cod: " + categoriaCodigo);

            productoRepository.save(almacenProducto);
            if(!almacenProducto.getImagenproducto().getOriginalFilename().equals("")){
                //System.out.println("Imagen no es null");
                FileUploadUtility.uploadFile(request, almacenProducto.getImagenproducto(), almacenProducto.getProductoId());
            }
            return "redirect:/admin/productos";
        }

    }

    @RequestMapping(value = "/producto/editar/{id}", method = RequestMethod.POST)
    public String editarProducto(@PathVariable("id") Long id, @Valid AlmacenProducto almacenProducto){

        System.out.println("editando Producto"+ id);
        AlmacenProducto producto = productoRepository.findOne(id);
        producto.setProducto(almacenProducto.getProducto());
        producto.setPrecio(almacenProducto.getPrecio());
        producto.setOferta(almacenProducto.getOferta());
        producto.setCaracteristica(almacenProducto.getCaracteristica());
        producto.setMarca(almacenProducto.getMarca());
        producto.setDescripcion(almacenProducto.getDescripcion());
        producto.setCodigo(almacenProducto.getCodigo());
        producto.setEstado(almacenProducto.getEstado());


        producto.setAlmacenCategoria(almacenProducto.getAlmacenCategoria());

        //System.out.println("Editado "+catalogo);
        productoRepository.save(producto);
        return "redirect:/admin/productos";
    }

    @RequestMapping(value = "/producto/eliminar/{id}", method = RequestMethod.POST)
    public String elminarProducto(@PathVariable("id") Long id){

        System.out.println("eliminar Producto"+ id);
        productoRepository.delete(id);
        return "redirect:/admin/productos";
    }*/



}
