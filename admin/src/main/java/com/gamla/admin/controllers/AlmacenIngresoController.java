package com.gamla.admin.controllers;

import com.gamla.admin.models.almacen.*;
import com.gamla.admin.repositories.*;
import com.sun.org.apache.regexp.internal.RE;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 6/5/17.
 */
@SessionAttributes("comprobante")
@RequestMapping("/ingreso")
@Controller
public class AlmacenIngresoController {

    @Autowired
    AlmacenCatalogoRepository catalogoRepository;

    @Autowired
    AlmacenCategoriaRepository categoriaRepository;

    @Autowired
    AlmacenComprobanteRepository comprobanteRepository;

    @Autowired
    AlmacenProductoRepository productoRepository;

    @Autowired
    AlmacenUnidadRepository unidadRepository;

    @Autowired
    AlmacenIngresoRepository ingresoRepository;

    @Autowired
    AlmacenDocumentoRepository documentoRepository;

    @Autowired
    AlmacenSedeRepository sedeRepository;

    @Autowired
    DataSource dataSource;

    @ModelAttribute("AlmacenComprobante")
    public AlmacenComprobante almacenComprobante(){
        return new AlmacenComprobante();
    }

    @ModelAttribute("newAlmacenProducto")
    public AlmacenProducto almacenProducto(){
        return new AlmacenProducto();
    }

    @ModelAttribute("newIngresoProducto")
    public AlmacenIngreso almacenIngreso(){
        return new AlmacenIngreso();
    }

    @ModelAttribute("todosCatalogos")
    public List<AlmacenCatalogo> catalogos(){
        List<AlmacenCatalogo> almacenCatalogos = catalogoRepository.findAll();
        return almacenCatalogos;
    }

    @ModelAttribute("todasCategorias")
    public List<AlmacenCategoria> productos(){
        List<AlmacenCategoria> almacenCategorias = categoriaRepository.findAll();
        return almacenCategorias;
    }

    @ModelAttribute("unidades")
    public List<AlmacenUnidad> unidades(){
        List <AlmacenUnidad> almacenUnidadList = unidadRepository.findAll();
        return almacenUnidadList;
    }

    @ModelAttribute("todosDocumentos")
    public List<AlmacenDocumento> documentos(){
        List <AlmacenDocumento> almacenDocumentos = documentoRepository.findAll();
        return almacenDocumentos;
    }

    @ModelAttribute("todasSedes")
    public List<AlmacenSede> sedes(){
        List <AlmacenSede> almacenSedes = sedeRepository.findAll();
        return almacenSedes;
    }

    @GetMapping("/comprobantes")
    public String ingresoInicio(){
        return "almacenIngreso";
    }

    @RequestMapping("/comprobantes/lista")
    @ResponseBody
    public List<AlmacenComprobante> listaIngresos(){
        List<AlmacenComprobante> comprobantes = comprobanteRepository.findAll();
        return comprobantes;
    }

    @RequestMapping("/catalogos")
    @ResponseBody
    public List<AlmacenCatalogo> listaCatalogos(){
        List<AlmacenCatalogo> catalogos = catalogoRepository.findAll();
        return catalogos;
    }

    @RequestMapping("/categorias")
    @ResponseBody
    public List<AlmacenCategoria> listaCategorias(@RequestParam("id") Long id){
        //Long catalogoId = Long.valueOf(id);
        List<AlmacenCategoria> categorias = categoriaRepository.findByAlmacenCatalogo_CatalogoId(id);
        return categorias;
    }


    @RequestMapping("/comprobante/nuevo")
    public String nuevoComprobante(){
        return "almacenIngresoCompNuevo";
    }

    /*@RequestMapping("/comprobante/{comprobanteId}")
    public String editarComprobante(){
        return "almacenEditarComprobante";
    }*/



    /*********************************************** INGRESOS COMPROBANTE *******************************************************/

    /*@RequestMapping("/productos")
    @ResponseBody
    public List<AlmacenProducto> listaProductos(){
        List<AlmacenProducto> listaProductos = productoRepository.findAll();
        return listaProductos;
    }*/

  /*  @RequestMapping("/productos/{id}")
    @ResponseBody
    public List<AlmacenProducto> listaProductos(@PathVariable("id") String id){
        Long categoriaId = Long.valueOf(id);
        List<AlmacenProducto> listaProductos = productoRepository.findByAlmacenCategoria_CategoriaId(categoriaId);
        return listaProductos;
    }*/

    @RequestMapping(value = "/comprobante/guardar", method = RequestMethod.POST)
    public String guardarComprobante(@Valid AlmacenComprobante almacenComprobante, Model model ,HttpServletRequest request){
        System.out.println("Guardando Comprobante");

        Long count = comprobanteRepository.count()+1;

        almacenComprobante.setRegistro(count);
        almacenComprobante.setReg_sistema(new Date());

        comprobanteRepository.saveAndFlush(almacenComprobante);
        Long comprobanteId = almacenComprobante.getComprobanteId();
        String id = String.valueOf(comprobanteId);

        request.setAttribute("comprobante", id);

        //model.addAttribute("macenComprobante",almacenComprobante);
        return "forward:/ingreso/comprobante/guardar  ";
    }

    @RequestMapping(value = "/comprobante/guardar", params = "guardarCompNuevo")
    public String guardarComprobanteNuevo(@Valid AlmacenComprobante almacenComprobante, Model model ,
                                          HttpServletRequest request, RedirectAttributes redirectAttributes){
        System.out.println("Guardando Comprobante... en guardarCompNuevo");

        Long count = comprobanteRepository.count()+1;

        almacenComprobante.setRegistro(count);
        almacenComprobante.setReg_sistema(new Date());

        comprobanteRepository.saveAndFlush(almacenComprobante);
        Long comprobanteId = almacenComprobante.getComprobanteId();
        String id = String.valueOf(comprobanteId);

        request.setAttribute("id", id);

        //model.addAttribute("macenComprobante",almacenComprobante);
        redirectAttributes.addAttribute("id", comprobanteId);
        return "redirect:/ingreso/comprobante/";
    }

    @RequestMapping(value = "/comprobante/guardar", params = "guardarProducto")
    public String guardarProductoComprobante(@Valid AlmacenIngreso almacenIngreso, Model model ,
                                          HttpServletRequest request, RedirectAttributes redirectAttributes){
        System.out.println("Guardando Producto... en guardar ProdComp " + almacenIngreso.toString());
        Long comprobanteId = almacenIngreso.getAlmacenComprobante().getComprobanteId();

        //Long count = comprobanteRepository.count()+1;

        //almacenIngreso.setRegistro(count);
        almacenIngreso.setReg_sistema(new Date());

        ingresoRepository.save(almacenIngreso);

        //comprobanteRepository.saveAndFlush(almacenComprobante);
        //Long comprobanteId = almacenComprobante.getComprobanteId();
        //String id = String.valueOf(comprobanteId);

        //request.setAttribute("id", id);

        //model.addAttribute("macenComprobante",almacenComprobante);*/
        redirectAttributes.addAttribute("id", comprobanteId);
        return "redirect:/ingreso/comprobante/";
    }

    @RequestMapping(value = "/comprobante/", method = RequestMethod.GET)
    public String guardarComprobante(HttpServletRequest request, Model model ){
        String param = (String) request.getParameter("id");
        System.out.println("Comprobante" + param);
        Long comprobante = Long.valueOf(param);

        AlmacenComprobante almacenComprobante = comprobanteRepository.findOne(comprobante);
        List<AlmacenIngreso> productosLista = ingresoRepository.findByAlmacenComprobante_ComprobanteId(comprobante);

        //model.addAttribute("selectedRecibo",almacenComprobante.getAlmacenDocumento().getDocumentoId());
        model.addAttribute("Comprobante",almacenComprobante);
        model.addAttribute("listaProductos", productosLista);

        return "almacenIngresoCompProd";
    }
    /*********************************************** IMPRIMIR COMPROBANTE******************************************************/

    @RequestMapping(value = "/imprimir/")
    @ResponseBody
    public void imprimirComprobante(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException, JRException {
        String param = (String) request.getParameter("id");
        System.out.println("Impresion Comprobante" + param);
        Long comprobante = Long.valueOf(param);

        Map<String,Object> params = new HashMap<>();
        params.put("comprobante", comprobante );
        System.out.println("generando pdf..");
        InputStream employeeReportStream = getClass().getResourceAsStream("/static/comprobante_ingreso.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);
        System.out.println("vamo bien");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        //return "reportBillList";
    }


    /*********************************************** INGRESOS PRODUCTOS *******************************************************/

    /*@RequestMapping("/productos")
    @ResponseBody
    public List<AlmacenProducto> listaProductos(){
        List<AlmacenProducto> listaProductos = productoRepository.findAll();
        return listaProductos;
    }*/

    @RequestMapping("/productos/{id}")
    @ResponseBody
    public List<AlmacenProducto> listaProductos(@PathVariable("id") String id){
        Long categoriaId = Long.valueOf(id);
        List<AlmacenProducto> listaProductos = productoRepository.findByAlmacenCategoria_CategoriaId(categoriaId);
        return listaProductos;
    }

    @RequestMapping(value = "/producto/guardar", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody void guardarProducto(@Valid AlmacenProducto almacenProducto){
        System.out.println("Guardando Producto");
        productoRepository.save(almacenProducto);
    }
}
