/**
 * Created by root on 6/5/17.
 */

$(function(){
    

    $('#datepicker-ingreso').datepicker({
        todayHighlight:true,
        language:'es'
    });

    $('#datepicker-ingreso-vencimiento').datepicker({
        todayHighlight:true,
        language:'es'
    });



    /*****************************************************INGRESO PRODUCTOS*********************************************************/
    /*('#select-grupo-productos').hide();*/

    /*$('#submit-comprobante').on('click',function (e) {
        e.preventDefault();
        $('#select-grupo-productos').show();
        loadCatalogos();

    });*/

    /*function loadCatalogos() {
        var options;
        $.ajax({
            url: "/ingreso/catalogos",
            dataType:'json',
            success: function (data) {
                for (var i=0; i < data.length; i++){
                    options += '<option value=" '+ data[i].catalogoId+' ">'+ data[i].catalogo+ '</option> ';
                }
                $('#select-catalogo').html(options);
            }

        });
        $('#submit-comprobante').hide();
    }*/

    /* $('#select-catalogo').on('click',function () {
     var catalogoId = $(this).val();
     //alert('id '+catalogoId);
     if(catalogoId==null) catalogoId=0;
     if(catalogoId != 0){
     alert('on click')
     loadCategorias(catalogoId);
     }
     });*/
    $('#select-catalogo').on('change',function () {
        /*$('#div_tabla_productos').hide();*/
        var catalogoId = $(this).val();
        //alert('id '+catalogoId);
        if(catalogoId==null) catalogoId=0;
        if(catalogoId != 0){
            $('#select-categoria').empty();
            loadCategorias(catalogoId);
        }else{
            alert('Error seleccione de nuevo');
        }
    });
    function notificacion(){
        /* new PNotify({
         title: 'Oh No!',
         text: 'Something terrible happened.',
         type: 'error',
         styling: 'bootstrap3'
         });*/
        alert('No hay Categorias Guardadas!')
    }
    function loadCategorias(catalogoId) {
        var options;
        var id = parseInt(catalogoId);
        $.ajax({
            url: "/ingreso/categorias",
            dataType:'json',
            data:{ id : id},
            success: function (data) {
                if(data.length==0){
                    notificacion();
                    //alert('no existe');
                }else {
                    for (var i = 0; i < data.length; i++) {
                        options += '<option value=" ' + data[i].categoriaId + ' ">' + data[i].categoria + '</option> ';
                    }
                }
                $('#select-categoria').html(options);
            }
        });
    }

    var categoriaId;
    //var categoriaNombre;
    /*$('#div_tabla_productos').hide();*/

    $('#select-categoria').on('change',function () {
        categoriaId = $(this).val();
        //categoriaNombre = $('#select-categoria option:selected').text();
        //alert('id '+categoriaId+' cat '+ categoriaNombre);
        $('#div_tabla_productos').show();
        if(categoriaId==null) categoriaId=0;
        if(categoriaId != 0){
            //$('#select-categoria').empty();
            tablaProductos.dataTable().fnDestroy();

            loadProductos(categoriaId);

        }else{
            alert('Error seleccione de nuevo');
        }
    });

    $('#btn-nuevo-producto').on('click', function() {
        //$('#nuevo-producto-select option[value='+categoriaId+']').attr('selected','selected');
        $('#nuevo-producto-select option[value='+categoriaId+']').prop('selected',true);
        $('#nuevo-producto-select').val(categoriaId);
        $('#modalNuevoProducto').modal();

        $('#form-nuevo-producto').attr('action','../producto/guardar');
    });

    var tablaProductos = $('#tabla-productos');

    function loadProductos(categoriaId) {
        var options;
        var id = parseInt(categoriaId);
        /*$.ajax({
            url: "/ingreso/productos/id",
            dataType:'json',
            data:{ id : id},
            success: function (data) {*/
               /* if(data.length==0){
                    notificacion();

                }else {*/
                var productos = tablaProductos.DataTable({
                        lengthMenu: [[5, 10, 20, -1], ['5 Registros', '10 Registros', '20 Registros', 'Todos']],
                        pageLength: 10,
                        "language": {
                            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
                        },
                        ajax: {
                            url: '/ingreso/productos/'+id,
                            dataSrc:''
                        },
                        columns: [
                            {
                                data:null
                            },
                            {
                                data: 'productoId'
                            },
                            {
                                data: 'codigo'
                            },
                            {
                                data: 'producto'
                            },
                            {
                                data:function (data,type,dataset){
                                    return data['caracteristica'] +' '+data['almacenUnidad'].unidadMedida;
                                }
                            },
                            {
                                data: 'almacenUnidad.unidadMedida'
                            },

                            {
                                data:'productoId',
                                bSortable: false,
                                mRender:function(data,type,row){
                                    var str='';
                                    str += '<div class="btn-group btn-group-xs" role="group">';
                                    str += '<a href="#" class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                                    str += '<a href="#" class="btn-ingreso btn btn-default" value="'+data+'">Ingresar</a>';
                                    str += '</div>'
                                    return str;
                                }
                            }
                        ],
                        "columnDefs": [ {
                            "searchable": false,
                            "orderable": false,
                            "targets": 0
                        } ],
                        "order": [[ 1, 'asc' ]]

                    });
        productos.on( 'order.dt search.dt', function () {
            productos.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                cell.innerHTML = i+1;
            } );
        } ).draw();
        productos.on('click','a.btn-ver', function(e) {
            /*alert("hola");*/
            e.preventDefault();
            var productoInfo = productos.row($(this).parents('tr')).data();
            var id = productoInfo['productoId'];
            var nombre = productoInfo['producto'];
            var precio = productoInfo['precio'];
            var oferta = productoInfo['oferta'];
            var codigo = productoInfo['codigo'];
            var cantidad = productoInfo['cantidad'];
            var caracteristica = productoInfo['caracteristica'];
            var marca = productoInfo['marca'];
            var descripcion = productoInfo['descripcion'];
            var unidad = productoInfo['almacenUnidad'].unidadMedida;
            var categoria = productoInfo['almacenCategoria'].categoria;

            $('#modalVerProducto').modal();

            checkImage('/images/'+id+'.jpg');

            function checkImage(src){
                var img = new Image();
                img.onload = function(){
                    $('#ver-producto-imagen').attr('src','/images/'+id+'.jpg');
                };
                img.onerror = function(){
                    $('#ver-producto-imagen').attr('src','/images/noimagen.gif');
                }
                img.src = src;
            }
            $('#ver-producto-id').html(id).val(id);
            $('#ver-producto-nombre').html(nombre).val(nombre);
            $('#ver-producto-precio').html(precio).val(precio);
            $('#ver-producto-oferta').html(oferta).val(oferta);
            $('#ver-producto-codigo').html(codigo).val(codigo);
            $('#ver-producto-cantidad').html(cantidad).val(cantidad);
            $('#ver-producto-caracteristica').html(caracteristica).val(caracteristica);
            $('#ver-producto-marca').html(marca).val(marca);
            $('#ver-producto-descripcion').html(descripcion).val(descripcion);
            $('#ver-producto-unidad').html(unidad).val(unidad);
            $('#ver-producto-categoria').html(categoria).val(categoria);

        });

        productos.on('click','a.btn-ingreso', function(e) {
            //alert("hola");
            e.preventDefault();
            var productoInfo = productos.row($(this).parents('tr')).data();
            var id = parseInt(productoInfo['productoId']);
            var nombre = productoInfo['producto'];
            var precio = productoInfo['precio'];
            var oferta = productoInfo['oferta'];
            var codigo = productoInfo['codigo'];
            var cantidad = productoInfo['cantidad'];
            var porcentaje = productoInfo['porcentaje'];
            var caracteristica = productoInfo['caracteristica'];
            var marca = productoInfo['marca'];
            var descripcion = productoInfo['descripcion'];
            var unidad = productoInfo['almacenUnidad'].unidadMedida;
            var categoria = productoInfo['almacenCategoria'].categoria;

            $('#modalNuevoIngreso').modal();

            checkImage('/images/'+id+'.jpg');

            function checkImage(src){
                var img = new Image();
                img.onload = function(){
                    $('#ingreso-producto-imagen').attr('src','/images/'+id+'.jpg');
                };
                img.onerror = function(){
                    $('#ingreso-producto-imagen').attr('src','/images/noimagen.gif');
                }
                img.src = src;
            }
            /*** Calcular costo unitario***/
            $('#ingreso-costo-total').blur(function(){
                var cantidad = parseFloat($('#ingreso-cantidad').val());
                var costototal = parseFloat($(this).val());
                var costounitario = costototal/cantidad;
                $('#ingreso-costo-unitario').html(costounitario).val(costounitario);
            });

            $('#productoIngresoId').html(id).val(id);
            $('#ingreso-producto-id').html(id).val(id);
            $('#ingreso-producto-nombre').html(nombre).val(nombre);
            $('#ingreso-producto-precio').html(precio).val(precio);
            $('#ingreso-producto-oferta').html(oferta).val(oferta);
            $('#ingreso-producto-codigo').html(codigo).val(codigo);
            $('#ingreso-producto-cantidad').html(cantidad).val(cantidad);
            $('#ingreso-producto-porcentaje').html(porcentaje).val(porcentaje);
            $('#ingreso-producto-caracteristica').html(caracteristica).val(caracteristica);
            $('#ingreso-producto-marca').html(marca).val(marca);
            $('#ingreso-producto-descripcion').html(descripcion).val(descripcion);
            $('#ingreso-producto-unidad').html(unidad).val(unidad);
            $('#ingreso-producto-categoria').html(categoria).val(categoria);

            //$('#form-ingreso-producto').attr('action','categoria/editar/'+id);

        });
                    /*for (var i = 0; i < data.length; i++) {
                        options += '<option value=" ' + data[i].categoriaId + ' ">' + data[i].categoria + '</option> ';
                    }*/
               // }
                //$('#select-categoria').html(options);
          //  }
        //});
    }



    /*****************************************************Comprobantes****************************************************/

    var tablaIngreso = $("#tabla-ingreso").DataTable({
            lengthMenu: [[5, 10, 20, -1], ['5 Registros', '10 Registros', '20 Registros', 'Todos']],
            pageLength: 10,
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
            },
            ajax: {
                url: '/ingreso/comprobantes/lista',
                dataSrc:''
            },
            columns: [
                {
                    data:null
                },
                {
                    data:function(data,type,dataset){
                        var str = ''
                        if(parseInt(data['registro']) < 1000 ) {str = '0' + data['registro']}
                        if(parseInt(data['registro']) < 100 ) {str = '00' + data['registro']}
                        if(parseInt(data['registro']) < 10 ) {str = '000' + data['registro']}
                        return str;
                    }

                },
                {
                    data: 'proveedor'
                },
                {
                    data: 'fecha'
                },
                {
                    data:function (data,type,dataset){
                        return data['almacenDocumento'].documento +' '+data['nro_documento'];
                    }

                },
                {
                    data: 'almacenSede.sede'
                },
                {
                    data:'comprobanteId',
                    bSortable: false,
                    mRender:function(data,type,row){
                        var str='';
                        str += '<div class="btn-group btn-group-xs" role="group">';
                        str += '<a href="/ingreso/comprobante/?id='+data+' " class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                        str += '<a href="/ingreso/imprimir/?id='+data+' " class="btn-editar btn btn-default" value="'+data+'">Imprimir</a>';
                        str += '<a href="#" class="btn-eliminar btn btn-default" value="'+data+'">Eliminar</a>';
                        str += '</div>'
                        return str;
                    }

                }
            ],
            "columnDefs": [ {
                "searchable": false,
                "orderable": false,
                "targets": 0
            } ],
            "order": [[ 1, 'asc' ]]
    });
    tablaIngreso.on( 'order.dt search.dt', function () {
        tablaIngreso.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();


    /**************************************** PRODUCTOS ***************************************************************/
    $('#nuevo-producto').on('click', function() {
        $("#modalNuevoProducto").modal();

        //resetamos los valores de la imagen
        $('#nueva-imagen').val('');
        $('#nuevo-producto-imagen').attr('src','/images/noimagen.gif');

        //cambio de imagen previsualizacion
        $('#nueva-imagen').on('change',function(){
            readURL(this);
        });
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#nuevo-producto-imagen').attr('src', e.target.result);
                };
                reader.readAsDataURL(input.files[0]);
            }
        }
    });

    //para seleccionar categorias deacuerdo al catalogo
    $('#nuevo-producto-select-catalogo').on('change',function(){
        var idCatalogo = this.value
        var params = {
            catalogo : idCatalogo
        }
        $.getJSON("/admin/producto/selectcat", params,mostrarCategorias);
    });

    function mostrarCategorias(results) {
        if (results.length == 0) {
            alert("No se ha registrado Categorias en este Grupo!");
        } else {
            var html = '<option value="">---Seleccionar Categoria---</option>'
            var len = results.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + results[i].categoriaId + '">'
                    + results[i].categoria + ' </option>';
            }
            html += '</option>';
            $('#nuevo-producto-select-categoria').html(html);
        }
    };

    $('#editar-producto-catalogo').on('change',function(){
        var idCatalogo = this.value
        var params = {
            catalogo : idCatalogo
        }
        $.getJSON("/admin/producto/selectcat", params,mostrarCategoriasEdit());
    });

    function mostrarCategoriasEdit(results){
        //alert("display"+results);

        if(results.length == 0){
            alert("No se ha registrado Tratamientos en este Grupo!");
        }else{
            var html = '<option value="">---Seleccionar Categoria---</option>'
            var len = results.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + results[i].categoriaId + '">'
                    + results[i].categoria + ' </option>';
            }
            html += '</option>';
            $('#editar-producto-categoria').html(html);
        }
    };

    /*"autowidth":true,
     responsive:true,*/
    var tablaProducto = $("#tabla-producto").DataTable({
        lengthMenu: [[5, 10, 20, -1], ['5', '10', '20', 'Todos']],
        pageLength: 10,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        "autowidth":true,
        ajax: {

            url: '/admin/productos/lista',
            dataSrc:''
        },
        columns: [
            {
                data:null
            },
            {
                data: 'almacenCategoria.categoria'
            },
            {
                data: 'codigo'
            },
            {
                data: 'producto'
            },
            {
                data: 'precio'
            },
            {
                data: 'oferta'
            },
            {
                data: 'cantidad'
            },
            {
                data: 'caracteristica'
            },
            {
                data: 'almacenUnidad.unidadMedida'
            },
            {
                data:'productoId',
                bSortable: false,
                mRender:function(data,type,row){
                    var str='';
                    str += '<div class="btn-group btn-group-xs" role="group">';
                    str += '<a href="#" class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                    str += '<a href="#" class="btn-editar btn btn-default" value="'+data+'">Editar</a>';
                    str += '<a href="#" class="btn-eliminar btn btn-default" value="'+data+'">Eliminar</a>';
                    str += '</div>'
                    return str;
                }
            }
        ],
        "columnDefs": [ {
            "searchable": false,
            "orderable": false,
            "targets": 0
        } ],
        "order": [[ 1, 'asc' ]]

    });
    //INDEX de la Tabla
    tablaProducto.on( 'order.dt search.dt', function () {
        tablaProducto.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    tablaProducto.on('click','a.btn-ver', function(e) {
        //alert('pver');
        e.preventDefault();
        var productoInfo = tablaProducto.row($(this).parents('tr')).data();
        var id = productoInfo['productoId'];
        var nombre = productoInfo['producto'];
        var precio = productoInfo['precio'];
        var oferta = productoInfo['oferta'];
        var codigo = productoInfo['codigo'];
        var cantidad = productoInfo['cantidad'];
        var caracteristica = productoInfo['caracteristica'];
        var marca = productoInfo['marca'];
        var descripcion = productoInfo['descripcion'];
        var codigo = productoInfo['codigo'];
        var unidad = productoInfo['almacenUnidad'].unidadMedida;
        var categoria = productoInfo['almacenCategoria'].categoria;

        $('#modalVerProducto').modal();

        checkImage('/images/'+id+'.jpg');

        function checkImage(src){
            var img = new Image();
            img.onload = function(){
                $('#ver-producto-imagen').attr('src','/images/'+id+'.jpg');
            };
            img.onerror = function(){
                $('#ver-producto-imagen').attr('src','/images/noimagen.gif');
            }
            img.src = src;
        }
        $('#ver-producto-id').html(id).val(id);
        $('#ver-producto-nombre').html(nombre).val(nombre);
        $('#ver-producto-precio').html(precio).val(precio);
        $('#ver-producto-oferta').html(oferta).val(oferta);
        $('#ver-producto-codigo').html(codigo).val(codigo);
        $('#ver-producto-cantidad').html(cantidad).val(cantidad);
        $('#ver-producto-caracteristica').html(caracteristica).val(caracteristica);
        $('#ver-producto-marca').html(marca).val(marca);
        $('#ver-producto-descripcion').html(descripcion).val(descripcion);
        $('#ver-producto-codigo').html(codigo).val(codigo);
        $('#ver-producto-unidad').html(unidad).val(unidad);
        $('#ver-producto-categoria').html(categoria).val(categoria);

    });

    tablaProducto.on('click','a.btn-editar', function(e) {
        e.preventDefault();
        var productoInfo = tablaProducto.row($(this).parents('tr')).data();
        var id = productoInfo['productoId'];
        var nombre = productoInfo['producto'];
        var precio = productoInfo['precio'];
        var oferta = productoInfo['oferta'];
        var codigo = productoInfo['codigo'];
        var cantidad = productoInfo['cantidad'];
        var caracteristica = productoInfo['caracteristica'];
        var marca = productoInfo['marca'];
        var descripcion = productoInfo['descripcion'];
        var codigo = productoInfo['codigo'];
        var unidadId = productoInfo['almacenUnidad'].unidadId;
        var categoriaId = productoInfo['almacenCategoria'].categoriaId;
        var catalogoId = productoInfo['almacenCategoria'].almacenCatalogo.catalogoId;

        $('#modalEditarProducto').modal();
        checkImage('/resources/images/'+id+'.jpg');

        function checkImage(src){
            var img = new Image();
            img.onload = function(){
                $('#editar-producto-imagen').attr('src','/images/'+id+'.jpg');
            };
            img.onerror = function(){
                $('#editar-producto-imagen').attr('src','/images/noimagen.gif');
            }
            img.src = src;
        }
        $('#editar-producto-id').html(id).val(id);
        $('#editar-producto-nombre').html(nombre).val(nombre);
        $('#editar-producto-precio').html(precio).val(precio);
        $('#editar-producto-oferta').html(oferta).val(oferta);
        $('#editar-producto-codigo').html(codigo).val(codigo);
        $('#editar-producto-cantidad').html(cantidad).val(cantidad);
        $('#editar-producto-caracteristica').html(caracteristica).val(caracteristica);
        $('#editar-producto-marca').html(marca).val(marca);
        $('#editar-producto-descripcion').html(descripcion).val(descripcion);
        $('#editar-producto-codigo').html(codigo).val(codigo);
        $('#editar-producto-unidad').val(unidadId);
        $('#editar-producto-categoria').val(categoriaId);
        $('#editar-producto-catalogo').val(catalogoId);

        $('#form-edit-categoria').attr('action','categoria/editar/'+id);
    });

    tablaProducto.on('click','a.btn-eliminar', function(e) {
        e.preventDefault();
        var categoriaInfo = tablaProducto.row($(this).parents('tr')).data();
        var id = parseInt(categoriaInfo['categoriaId']);
        var nombre = categoriaInfo['categoria'];
        var codigo = categoriaInfo['codigo'];
        var descripcion = categoriaInfo['descripcion'];
        var catalogoObject = categoriaInfo['almacenCatalogo'].catalogo;

        $('#modalEliminarCategoria').modal();
        $('#eliminar-categoria-id').html(id).val(id);
        $('#eliminar-categoria-nombre').html(nombre).val(nombre);
        $('#eliminar-categoria-codigo').html(codigo).val(codigo);
        $('#eliminar-categoria-descripcion').html(descripcion).val(descripcion);
        $('#eliminar-categoria-catalogo').html(catalogoObject).val(catalogoObject);

        $('#form-eliminar-categoria').attr('action','categoria/eliminar/'+id);
    });


    /**************************Catalogo********************************************************************************/
    $('#nuevo-catalogo').on('click', function() {
        $("#modalNuevoCatalogo").modal();

    });
    /*"autowidth":true,
     responsive:true,*/
    var tablaCatalogo = $("#tabla-catalogo").DataTable({
            lengthMenu: [[5, 10, 20, -1], ['5', '10', '20', 'Todos']],
            pageLength: 10,
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
            },
            ajax: {

                url: '/admin/catalogos/lista',
                dataSrc:''
            },
            columns: [
                {
                    data:null
                },
                {
                    data: 'codigo'
                },
                {
                    data: 'catalogo'
                },
                {
                    data: 'descripcion'
                },
                {
                    data:'catalogoId',
                    bSortable: false,
                    mRender:function(data,type,row){
                        var str='';
                        str += '<div class="btn-group btn-group-xs" role="group">';
                        str += '<a href="#" class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                        str += '<a href="#" class="btn-editar btn btn-default" value="'+data+'">Editar</a>';
                        str += '<a href="#" class="btn-eliminar btn btn-default" value="'+data+'">Eliminar</a>';
                        str += '</div>'
                        return str;
                    }
                }
            ],
            "columnDefs": [ {
                "searchable": false,
                "orderable": false,
                "targets": 0
            } ],
            "order": [[ 1, 'asc' ]]

    });
    //INDEX de la Tabla
    tablaCatalogo.on( 'order.dt search.dt', function () {
        tablaCatalogo.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();


        tablaCatalogo.on('click','a.btn-ver', function(e) {
            e.preventDefault();
            var catalogoInfo = tablaCatalogo.row($(this).parents('tr')).data();
            var codigo = catalogoInfo['codigo'];
            var nombre = catalogoInfo['catalogo'];
            var descripcion = catalogoInfo['descripcion'];
            $('#modalVerCatalogo').modal();
            $('#ver-catalogo-codigo').html(codigo).val(codigo);
            $('#ver-catalogo-nombre').html(nombre).val(nombre);
            $('#ver-catalogo-descripcion').html(descripcion).val(descripcion);

        });

    tablaCatalogo.on('click','a.btn-editar', function(e) {
        e.preventDefault();
        catalogoInfo = tablaCatalogo.row($(this).parents('tr')).data();
        var id = parseInt(catalogoInfo['catalogoId']);
        var codigo = catalogoInfo['codigo'];
        var nombre = catalogoInfo['catalogo'];
        var descripcion = catalogoInfo['descripcion'];
        $('#modalEditarCatalogo').modal();
        $('#editar-catalogo-codigo').html(codigo).val(codigo);
        $('#editar-catalogo-nombre').html(nombre).val(nombre);
        $('#editar-catalogo-descripcion').html(descripcion).val(descripcion);

        $('#form-edit-catalogo').attr('action','catalogo/editar/'+id);
    });

    tablaCatalogo.on('click','a.btn-eliminar', function(e) {
        e.preventDefault();
        catalogoInfo = tablaCatalogo.row($(this).parents('tr')).data();
        var id = parseInt(catalogoInfo['catalogoId']);
        var codigo = catalogoInfo['codigo'];
        var nombre = catalogoInfo['catalogo'];
        var descripcion = catalogoInfo['descripcion'];
        $('#modalEliminarCatalogo').modal();
        $('#eliminar-catalogo-codigo').html(codigo).val(codigo);
        $('#eliminar-catalogo-nombre').html(nombre).val(nombre);
        $('#eliminar-catalogo-descripcion').html(descripcion).val(descripcion);

        $('#form-eliminar-catalogo').attr('action','catalogo/eliminar/'+id);
    });


    /**************************Categoria*******************************************************************************/
    $('#nueva-categoria').on('click', function() {
        $("#modalNuevaCategoria").modal();
        //var catalogoId = $(this).data('catalogoid');
        //var id = tablaCatalogo.fnGetData(this)[0];
        //alert ('id'+catalogoId);
    });
    /*"autowidth":true,
     responsive:true,*/
    var tablaCategoria = $("#tabla-categoria").DataTable({
        lengthMenu: [[5, 10, 20, -1], ['5', '10', '20', 'Todos']],
        pageLength: 10,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        "autowidth":true,
        ajax: {

            url: '/admin/categorias/lista',
            dataSrc:''
        },
        columns: [
            {
                data:null
            },
            {
                data: 'codigo'
            },
            {
                data: 'categoria'
            },
            {
                data: 'descripcion'
            },
            {
                data: 'almacenCatalogo.catalogo'
            },
            {
                data:'categoriaId',
                bSortable: false,
                mRender:function(data,type,row){
                    var str='';
                    str += '<div class="btn-group btn-group-xs" role="group">';
                    str += '<a href="#" class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                    str += '<a href="#" class="btn-editar btn btn-default" value="'+data+'">Editar</a>';
                    str += '<a href="#" class="btn-eliminar btn btn-default" value="'+data+'">Eliminar</a>';
                    str += '</div>'
                    return str;
                }
            }
        ],
        "columnDefs": [ {
            "searchable": false,
            "orderable": false,
            "targets": 0
        } ],
        "order": [[ 1, 'asc' ]]

    });
    //INDEX de la Tabla
    tablaCategoria.on( 'order.dt search.dt', function () {
        tablaCategoria.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    tablaCategoria.on('click','a.btn-ver', function(e) {
        e.preventDefault();
        var categoriaInfo = tablaCategoria.row($(this).parents('tr')).data();
        var nombre = categoriaInfo['categoria'];
        var descripcion = categoriaInfo['descripcion'];
        var codigo = categoriaInfo['codigo'];
        var catalogoDes = categoriaInfo['almacenCatalogo'].catalogo;
        //var catalogoDes= (catalogoObject['catalogo']);/*JSON.stringify(catalogoDes)*/
        $('#modalVerCategoria').modal();
        $('#ver-categoria-nombre').html(nombre).val(nombre);
        $('#ver-categoria-descripcion').html(descripcion).val(descripcion);
        $('#ver-categoria-codigo').html(codigo).val(codigo);
        $('#ver-categoria-catalogo').html(catalogoDes).val(catalogoDes);

    });

    tablaCategoria.on('click','a.btn-editar', function(e) {
        e.preventDefault();
        var categoriaInfo = tablaCategoria.row($(this).parents('tr')).data();
        var id = parseInt(categoriaInfo['categoriaId']);
        var codigo = categoriaInfo['codigo'];
        var nombre = categoriaInfo['categoria'];
        var descripcion = categoriaInfo['descripcion'];
        var catalogoObject = categoriaInfo['almacenCatalogo'].catalogoId;

        $('#modalEditarCategoria').modal();
        $('#editar-categoria-id').html(id).val(id);
        $('#editar-categoria-nombre').html(nombre).val(nombre);
        $('#editar-categoria-descripcion').html(descripcion).val(descripcion);
        $('#editar-categoria-select').val(catalogoObject);


        $('#form-edit-categoria').attr('action','categoria/editar/'+id);
    });

    tablaCategoria.on('click','a.btn-eliminar', function(e) {
        e.preventDefault();
        var categoriaInfo = tablaCategoria.row($(this).parents('tr')).data();
        var id = parseInt(categoriaInfo['categoriaId']);
        var nombre = categoriaInfo['categoria'];
        var codigo = categoriaInfo['codigo'];
        var descripcion = categoriaInfo['descripcion'];
        var catalogoObject = categoriaInfo['almacenCatalogo'].catalogo;

        $('#modalEliminarCategoria').modal();
        $('#eliminar-categoria-id').html(id).val(id);
        $('#eliminar-categoria-nombre').html(nombre).val(nombre);
        $('#eliminar-categoria-codigo').html(codigo).val(codigo);
        $('#eliminar-categoria-descripcion').html(descripcion).val(descripcion);
        $('#eliminar-categoria-catalogo').html(catalogoObject).val(catalogoObject);

        $('#form-eliminar-categoria').attr('action','categoria/eliminar/'+id);
    });


    /**************************Unidad de Medida***********************************************************************/
    $('#nueva-unidad').on('click', function() {
        $("#modalNuevaUnidad").modal();

    });
    /*"autowidth":true,
     responsive:true,*/
    var tablaUnidad = $("#tabla-unidad").DataTable({
        lengthMenu: [[5, 10, 20, -1], ['5', '10', '20', 'Todos']],
        pageLength: 10,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        ajax: {

            url: '/admin/unidades/lista',
            dataSrc:''
        },
        columns: [
            {
                data:null
            },
            {
                data: 'unidadId'
            },
            {
                data: 'unidadMedida'
            },
            {
                data: 'abreviatura'
            },
            {
                data:'unidadId',
                bSortable: false,
                mRender:function(data,type,row){
                    var str='';
                    str += '<div class="btn-group btn-group-xs" role="group">';
                    str += '<a href="#" class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                    str += '<a href="#" class="btn-editar btn btn-default" value="'+data+'">Editar</a>';
                    str += '<a href="#" class="btn-eliminar btn btn-default" value="'+data+'">Eliminar</a>';
                    str += '</div>'
                    return str;
                }
            }
        ],
        "columnDefs": [ {
            "searchable": false,
            "orderable": false,
            "targets": 0
        } ],
        "order": [[ 1, 'asc' ]]

    });
    //INDEX de la Tabla
    tablaUnidad.on( 'order.dt search.dt', function () {
        tablaUnidad.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    tablaUnidad.on('click','a.btn-ver', function(e) {
        e.preventDefault();
        var unidadInfo = tablaUnidad.row($(this).parents('tr')).data();
        var nombre = unidadInfo['unidadMedida'];
        var abreviatura = unidadInfo['abreviatura'];
        $('#modalVerUnidad').modal();
        $('#ver-unidad-nombre').html(nombre).val(nombre);
        $('#ver-unidad-abreviatura').html(abreviatura).val(abreviatura);
    });

    tablaUnidad.on('click','a.btn-editar', function(e) {
        e.preventDefault();
        var unidadInfo = tablaUnidad.row($(this).parents('tr')).data();
        var id = parseInt(unidadInfo['unidadId']);
        var nombre = unidadInfo['unidadMedida'];
        var abreviatura = unidadInfo['abreviatura'];
        $('#modalEditarUnidad').modal();
        $('#editar-unidad-id').html(id).val(id);
        $('#editar-unidad-nombre').html(nombre).val(nombre);
        $('#editar-unidad-abreviatura').html(abreviatura).val(abreviatura);

        $('#form-edit-unidad').attr('action','unidad/editar/'+id);
    });

    tablaUnidad.on('click','a.btn-eliminar', function(e) {
        e.preventDefault();
        var unidadInfo = tablaUnidad.row($(this).parents('tr')).data();
        var id = parseInt(unidadInfo['unidadId']);
        var nombre = unidadInfo['unidadMedida'];
        var abreviatura = unidadInfo['abreviatura'];
        $('#modalEliminarUnidad').modal();
        $('#eliminar-unidad-id').html(id).val(id);
        $('#eliminar-unidad-nombre').html(nombre).val(nombre);
        $('#eliminar-unidad-abreviatura').html(abreviatura).val(abreviatura);

        $('#form-eliminar-unidad').attr('action','unidad/eliminar/'+id);
    });


    /**************************Tipo de Documentos *********************************************************************/
    $('#nuevo-documento').on('click', function() {
        $("#modalNuevoDocumento").modal();

    });
    /*"autowidth":true,
     responsive:true,*/
    var tablaDocumento = $("#tabla-documento").DataTable({
        lengthMenu: [[5, 10, 20, -1], ['5', '10', '20', 'Todos']],
        pageLength: 10,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        ajax: {

            url: '/admin/documentos/lista',
            dataSrc:''
        },
        columns: [
            {
                data:null
            },
            {
                data: 'documentoId'
            },
            {
                data: 'documento'
            },
            {
                data: 'descripcion'
            },
            {
                data:'documentoId',
                bSortable: false,
                mRender:function(data,type,row){
                    var str='';
                    str += '<div class="btn-group btn-group-xs" role="group">';
                    str += '<a href="#" class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                    str += '<a href="#" class="btn-editar btn btn-default" value="'+data+'">Editar</a>';
                    str += '<a href="#" class="btn-eliminar btn btn-default" value="'+data+'">Eliminar</a>';
                    str += '</div>'
                    return str;
                }
            }
        ],
        "columnDefs": [ {
            "searchable": false,
            "orderable": false,
            "targets": 0
        } ],
        "order": [[ 1, 'asc' ]]

    });
    //INDEX de la Tabla
    tablaDocumento.on( 'order.dt search.dt', function () {
        tablaDocumento.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    tablaDocumento.on('click','a.btn-ver', function(e) {
        e.preventDefault();
        var docInfo = tablaDocumento.row($(this).parents('tr')).data();
        var nombre = docInfo['documento'];
        var descripcion = docInfo['descripcion'];
        $('#modalVerDocumento').modal();
        $('#ver-documento-nombre').html(nombre).val(nombre);
        $('#ver-documento-descripcion').html(descripcion).val(descripcion);
    });

    tablaDocumento.on('click','a.btn-editar', function(e) {
        e.preventDefault();
        var docInfo = tablaDocumento.row($(this).parents('tr')).data();
        var id = parseInt(docInfo['documentoId']);
        var nombre = docInfo['documento'];
        var descripcion = docInfo['descripcion'];
        $('#modalEditarDocumento').modal();
        $('#editar-documento-id').html(id).val(id);
        $('#editar-documento-nombre').html(nombre).val(nombre);
        $('#editar-documento-descripcion').html(descripcion).val(descripcion);

        $('#form-edit-documento').attr('action','documento/editar/'+id);
    });

    tablaDocumento.on('click','a.btn-eliminar', function(e) {
        e.preventDefault();
        var docInfo = tablaDocumento.row($(this).parents('tr')).data();
        var id = parseInt(docInfo['documentoId']);
        var nombre = docInfo['documento'];
        var descripcion = docInfo['descripcion'];
        $('#modalEliminarDocumento').modal();
        $('#eliminar-documento-id').html(id).val(id);
        $('#eliminar-documento-nombre').html(nombre).val(nombre);
        $('#eliminar-documento-descripcion').html(descripcion).val(descripcion);

        $('#form-eliminar-documento').attr('action','documento/eliminar/'+id);
    });

    /**************************Sucursal / Sede ** *********************************************************************/
    $('#nueva-sucursal').on('click', function() {
        $("#modalNuevaSucursal").modal();

    });
    /*"autowidth":true,
     responsive:true,*/
    var tablaSucursal = $("#tabla-sucursal").DataTable({
        lengthMenu: [[5, 10, 20, -1], ['5', '10', '20', 'Todos']],
        pageLength: 10,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        ajax: {

            url: '/admin/sedes/lista',
            dataSrc:''
        },
        columns: [
            {
                data:null
            },
            {
                data: 'sedeId'
            },
            {
                data: 'sede'
            },
            {
                data: 'sigla'
            },
            {
                data: 'descripcion'
            },
            {
                data:'sedeId',
                bSortable: false,
                mRender:function(data,type,row){
                    var str='';
                    str += '<div class="btn-group btn-group-xs" role="group">';
                    str += '<a href="#" class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                    str += '<a href="#" class="btn-editar btn btn-default" value="'+data+'">Editar</a>';
                    str += '<a href="#" class="btn-eliminar btn btn-default" value="'+data+'">Eliminar</a>';
                    str += '</div>'
                    return str;
                }
            }
        ],
        "columnDefs": [ {
            "searchable": false,
            "orderable": false,
            "targets": 0
        } ],
        "order": [[ 1, 'asc' ]]

    });
    //INDEX de la Tabla
    tablaSucursal.on( 'order.dt search.dt', function () {
        tablaSucursal.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    tablaSucursal.on('click','a.btn-ver', function(e) {
        e.preventDefault();
        var sucursalInfo = tablaSucursal.row($(this).parents('tr')).data();
        var nombre = sucursalInfo['sede'];
        var sigla = sucursalInfo['sigla'];
        var descripcion = sucursalInfo['descripcion'];
        $('#modalVerSucursal').modal();
        $('#ver-sucursal-nombre').html(nombre).val(nombre);
        $('#ver-sucursal-sigla').html(sigla).val(sigla);
        $('#ver-sucursal-descripcion').html(descripcion).val(descripcion);
    });

    tablaSucursal.on('click','a.btn-editar', function(e) {
        e.preventDefault();
        var sucursalInfo = tablaSucursal.row($(this).parents('tr')).data();
        var id = parseInt(sucursalInfo['sedeId']);
        var nombre = sucursalInfo['sede'];
        var sigla = sucursalInfo['sigla'];
        var descripcion = sucursalInfo['descripcion'];
        $('#modalEditarSucursal').modal();
        $('#editar-sucursal-id').html(id).val(id);
        $('#editar-sucursal-nombre').html(nombre).val(nombre);
        $('#editar-sucursal-sigla').html(sigla).val(sigla);
        $('#editar-sucursal-descripcion').html(descripcion).val(descripcion);

        $('#form-edit-sucursal').attr('action','sede/editar/'+id);
    });

    tablaSucursal.on('click','a.btn-eliminar', function(e) {
        e.preventDefault();
        var sucursalInfo = tablaSucursal.row($(this).parents('tr')).data();
        var id = parseInt(sucursalInfo['sedeId']);
        var nombre = sucursalInfo['sede'];
        var sigla = sucursalInfo['sigla'];
        var descripcion = sucursalInfo['descripcion'];
        $('#modalEliminarSucursal').modal();
        $('#eliminar-sucursal-id').html(id).val(id);
        $('#eliminar-sucursal-nombre').html(nombre).val(nombre);
        $('#eliminar-sucursal-sigla').html(sigla).val(sigla);
        $('#eliminar-sucursal-descripcion').html(descripcion).val(descripcion);

        $('#form-eliminar-sucursal').attr('action','sede/eliminar/'+id);
    });

    /**************************VARIABLES / PORCENTAJES *********************************************************************/
    $('#nueva-variable').on('click', function() {
        $("#modalNuevaVariable").modal();

    });
    /*"autowidth":true,
     responsive:true,*/
    var tablaVariable = $("#tabla-variables").DataTable({
        lengthMenu: [[5, 10, 20, -1], ['5', '10', '20', 'Todos']],
        pageLength: 10,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        ajax: {

            url: '/admin/variables/lista',
            dataSrc:''
        },
        columns: [
            {
                data:null
            },
            {
                data: 'variableId'
            },
            {
                data: 'variable'
            },
            {
                data: 'valor'
            },
            {
                data: 'descripcion'
            },
            {
                data:'variableId',
                bSortable: false,
                mRender:function(data,type,row){
                    var str='';
                    str += '<div class="btn-group btn-group-xs" role="group">';
                    str += '<a href="#" class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                    str += '<a href="#" class="btn-editar btn btn-default" value="'+data+'">Editar</a>';
                    str += '<a href="#" class="btn-eliminar btn btn-default" value="'+data+'">Eliminar</a>';
                    str += '</div>'
                    return str;
                }
            }
        ],
        "columnDefs": [ {
            "searchable": false,
            "orderable": false,
            "targets": 0
        } ],
        "order": [[ 1, 'asc' ]]

    });
    //INDEX de la Tabla
    tablaVariable.on( 'order.dt search.dt', function () {
        tablaVariable.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    tablaVariable.on('click','a.btn-ver', function(e) {
        e.preventDefault();
        var variableInfo = tablaVariable.row($(this).parents('tr')).data();
        var nombre = variableInfo['variable'];
        var valor = variableInfo['valor'];
        var descripcion = variableInfo['descripcion'];
        $('#modalVerVariable').modal();
        $('#ver-variable-nombre').html(nombre).val(nombre);
        $('#ver-variable-sigla').html(valor).val(valor);
        $('#ver-variable-descripcion').html(descripcion).val(descripcion);
    });

    tablaVariable.on('click','a.btn-editar', function(e) {
        e.preventDefault();
        var variableInfo = tablaVariable.row($(this).parents('tr')).data();
        var id = parseInt(variableInfo['variableId']);
        var nombre = variableInfo['variable'];
        var valor = variableInfo['valor'];
        var descripcion = variableInfo['descripcion'];
        $('#modalEditarVariable').modal();
        $('#editar-variable-id').html(id).val(id);
        $('#editar-variable-nombre').html(nombre).val(nombre);
        $('#editar-variable-sigla').html(valor).val(valor);
        $('#editar-variable-descripcion').html(descripcion).val(descripcion);

        $('#form-edit-variable').attr('action','variable/editar/'+id);
    });

    tablaVariable.on('click','a.btn-eliminar', function(e) {
        e.preventDefault();
        var variableInfo = tablaVariable.row($(this).parents('tr')).data();
        var id = parseInt(variableInfo['variableId']);
        var nombre = variableInfo['variable'];
        var valor = variableInfo['valor'];
        var descripcion = variableInfo['descripcion'];
        $('#modalEliminarVariable').modal();
        $('#eliminar-variable-id').html(id).val(id);
        $('#eliminar-variable-nombre').html(nombre).val(nombre);
        $('#eliminar-variable-sigla').html(valor).val(valor);
        $('#eliminar-variable-descripcion').html(descripcion).val(descripcion);

        $('#form-eliminar-variable').attr('action','variable/eliminar/'+id);
    });

    /************************** PORCENTAJES ******************************************************************************/

    var tablaProdPorcentaje = $("#tabla-prodporcentaje").DataTable({
        lengthMenu: [[5, 10, 20, -1], ['5', '10', '20', 'Todos']],
        pageLength: 10,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        "autowidth":true,
        ajax: {

            url: '/admin/prodporcentajes/lista',
            dataSrc:''
        },
        columns: [
            {
                data:null
            },
            {
                data: 'almacenCategoria.categoria'
            },
            {
                data: 'codigo'
            },
            {
                data: 'producto'
            },
            {
                data:function (data,type,dataset){
                    return data['porcentaje']+'%';
                }
            },
            {
                data: 'precio'
            },
            {
                data: 'oferta'
            },
            {
                data: 'cantidad'
            },
            {
                data: 'caracteristica'
            },
            {
                data: 'almacenUnidad.unidadMedida'
            },
            {
                data:'productoId',
                bSortable: false,
                mRender:function(data,type,row){
                    var str='';
                    str += '<div class="btn-group btn-group-xs" role="group">';
                    str += '<a href="#" class="btn-ver btn btn-default" value="'+data+'">Ver</a>';
                    str += '<a href="#" class="btn-editar btn btn-default" value="'+data+'">Porcentaje</a>';
                    str += '</div>'
                    return str;
                }
            }
        ],
        "columnDefs": [ {
            "searchable": false,
            "orderable": false,
            "targets": 0
        } ],
        "order": [[ 1, 'asc' ]]

    });
    //INDEX de la Tabla
    tablaProdPorcentaje.on( 'order.dt search.dt', function () {
        tablaProdPorcentaje.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    tablaProdPorcentaje.on('click','a.btn-ver', function(e) {
        //alert('pver');
        e.preventDefault();
        var productoInfo = tablaProdPorcentaje.row($(this).parents('tr')).data();
        var id = productoInfo['productoId'];
        var nombre = productoInfo['producto'];
        var precio = productoInfo['precio'];
        var oferta = productoInfo['oferta'];
        var codigo = productoInfo['codigo'];
        var cantidad = productoInfo['cantidad'];
        var caracteristica = productoInfo['caracteristica'];
        var marca = productoInfo['marca'];
        var descripcion = productoInfo['descripcion'];
        var codigo = productoInfo['codigo'];
        var unidad = productoInfo['almacenUnidad'].unidadMedida;
        var categoria = productoInfo['almacenCategoria'].categoria;

        $('#modalVerProducto').modal();

        checkImage('/images/'+id+'.jpg');

        function checkImage(src){
            var img = new Image();
            img.onload = function(){
                $('#ver-producto-imagen').attr('src','/images/'+id+'.jpg');
            };
            img.onerror = function(){
                $('#ver-producto-imagen').attr('src','/images/noimagen.gif');
            }
            img.src = src;
        }
        $('#ver-producto-id').html(id).val(id);
        $('#ver-producto-nombre').html(nombre).val(nombre);
        $('#ver-producto-precio').html(precio).val(precio);
        $('#ver-producto-oferta').html(oferta).val(oferta);
        $('#ver-producto-codigo').html(codigo).val(codigo);
        $('#ver-producto-cantidad').html(cantidad).val(cantidad);
        $('#ver-producto-caracteristica').html(caracteristica).val(caracteristica);
        $('#ver-producto-marca').html(marca).val(marca);
        $('#ver-producto-descripcion').html(descripcion).val(descripcion);
        $('#ver-producto-codigo').html(codigo).val(codigo);
        $('#ver-producto-unidad').html(unidad).val(unidad);
        $('#ver-producto-categoria').html(categoria).val(categoria);

    });

    tablaProdPorcentaje.on('click','a.btn-editar', function(e) {
        e.preventDefault();
        var productoInfo = tablaProdPorcentaje.row($(this).parents('tr')).data();
        var id = productoInfo['productoId'];
        var nombre = productoInfo['producto'];
        var precio = productoInfo['precio'];
        var oferta = productoInfo['oferta'];
        var codigo = productoInfo['codigo'];
        var cantidad = productoInfo['cantidad'];
        var caracteristica = productoInfo['caracteristica'];
        var marca = productoInfo['marca'];
        var descripcion = productoInfo['descripcion'];
        var codigo = productoInfo['codigo'];
        var unidadId = productoInfo['almacenUnidad'].unidadId;
        var categoriaId = productoInfo['almacenCategoria'].categoriaId;
        var catalogoId = productoInfo['almacenCategoria'].almacenCatalogo.catalogoId;

        $('#modalEditarPorcentaje').modal();
        checkImage('/resources/images/'+id+'.jpg');

        function checkImage(src){
            var img = new Image();
            img.onload = function(){
                $('#editar-producto-imagen').attr('src','/images/'+id+'.jpg');
            };
            img.onerror = function(){
                $('#editar-producto-imagen').attr('src','/images/noimagen.gif');
            }
            img.src = src;
        }
        $('#editar-producto-id').html(id).val(id);
        $('#editar-producto-nombre').html(nombre).val(nombre);
        $('#editar-producto-precio').html(precio).val(precio);
        $('#editar-producto-oferta').html(oferta).val(oferta);
        $('#editar-producto-codigo').html(codigo).val(codigo);
        $('#editar-producto-cantidad').html(cantidad).val(cantidad);
        $('#editar-producto-caracteristica').html(caracteristica).val(caracteristica);
        $('#editar-producto-marca').html(marca).val(marca);
        $('#editar-producto-descripcion').html(descripcion).val(descripcion);
        $('#editar-producto-codigo').html(codigo).val(codigo);
        $('#editar-producto-unidad').val(unidadId);
        $('#editar-producto-categoria').val(categoriaId);
        $('#editar-producto-catalogo').val(catalogoId);

        $('#form-editar-porcentaje').attr('action','porcentaje/editar/'+id);
    });

    tablaProducto.on('click','a.btn-eliminar', function(e) {
        e.preventDefault();
        var categoriaInfo = tablaProducto.row($(this).parents('tr')).data();
        var id = parseInt(categoriaInfo['categoriaId']);
        var nombre = categoriaInfo['categoria'];
        var codigo = categoriaInfo['codigo'];
        var descripcion = categoriaInfo['descripcion'];
        var catalogoObject = categoriaInfo['almacenCatalogo'].catalogo;

        $('#modalEliminarCategoria').modal();
        $('#eliminar-categoria-id').html(id).val(id);
        $('#eliminar-categoria-nombre').html(nombre).val(nombre);
        $('#eliminar-categoria-codigo').html(codigo).val(codigo);
        $('#eliminar-categoria-descripcion').html(descripcion).val(descripcion);
        $('#eliminar-categoria-catalogo').html(catalogoObject).val(catalogoObject);

        $('#form-eliminar-categoria').attr('action','categoria/eliminar/'+id);
    });


    /************************** CODIGO BARRAS *****************************************************************************/

    $('.boton-codigobarras').on("click", function(){
        productoId = $(this).data("producto-id")
        //alert(productoId);

    });

    function addBill(){
        var treatmentId = $("#tId").val();
        var ticket= $("#tTicket").val();
        var name = $("#tName").val();
        var patient = $("#bill-patient").html();
        var piece = $("#tPiece").val();
        var totalAmount = $("#tTotal").val();
        var toPaid = parseFloat($("#tPaid").val());
        var amount = parseFloat($("#tPayAmount").val());

        /*var date = $(".payment-date").val(); */
        var balance = (totalAmount-toPaid) - amount;
        var totalpaid = parseFloat(amount) + parseFloat(toPaid);
        // insertando en objeto

        var element = {};

        element.paymentAmount = amount
        element.paymentPatient = patient;
        element.paymentPaid = toPaid;
        //element.paymentDate = "nada"

        element.treatment = {treatmentId:treatmentId, treatmentTotalPaid:parseFloat(totalpaid)}
        //element.treatment = {treatmentId:tId, treatmentTotalPaid:parseFloat(totalpaid)}

        paylist.push(element);


        //createJson(amount,totalpaid);
        //
        if(treatmentId != null){

            $("#btn-save-payment").show();
            $("#resultTable-payment").append(
                "<tr> " + "<td>" + ticket  +"</td>"
                + "<td>" + name +"</td>"
                + "<td>" + piece +"</td>"
                + "<td>" + totalAmount +"</td>"
                + "<td>" + toPaid +"</td>"
                + "<td class='text-green'>" + amount +"</td>"
                + "<td>" + balance +"</td>"
                + "<td>"
                + "<div class= 'btn-group btn-group-sm'>"
                + "<button class='btn btn-default'><i class='fa fa-pencil fa-fw'></i></button>"
                + "<button class='btn btn-default btn-delete-pay' data-idrow='"+treatmentId+"'><i class='fa fa-trash fa-fw'></i></button>"
                + "</div>"
                + "</td>"
                + "</tr>");
        }
    }

    //borrar Row de la table
    $('#resultTable-payment').on('click','.btn-delete-pay',function(){
        //event.preventDefault();

        var idRow = $(this).data("idrow")
        //alert('id row'+idRow);

        /*paylist = $.grep(paylist , function (value) {
         alert('value grep'+ value.treatmentId);
         return value != idRow;
         });*/

        // Remove object from array
        for(i=0; i < paylist.length ; i++){
            if(paylist[i].treatment.treatmentId == idRow){
                //alert('id del pay'+paylist[i].treatment.treatmentId);
                paylist.splice(i,1);
            }

        }
        // Remove el table row seleccionado
        $(this).closest('tr').remove();

        if(paylist.length == 0){
            //alert('no existe filas' +paylist.length);
            $('#btn-save-bill').prop('disabled',true);
        }

        //return false;
        //document.getElementById("test").innerHTML=element;
    });


})