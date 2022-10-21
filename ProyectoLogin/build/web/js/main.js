var libros = [];

function inicializarModulo()
{
    setDetalleVisible(false);
    refrescarTabla();
}

function guardar()
{
    var libro = new Object();
    
    libro.id = 0;
    libro.titulo = $('#txtTitulo').val();
    libro.tema = $('#txtTema').val();
    libro.descripcion = $('#txtDescripcion').val();
    
    if($('#txtCodigo').val().length > 0)
    {
        libros.id = parseInt($('#txtCodigo').val());
    }
    $.ajax({
                type : "POST",
                url  : "api/libros/save",
                data : {
                            idLibro : libro.id,
                            titulo : libro.titulo,
                            tema : libro.tema,
                            descripcion : libro.descripcion
                            
                        }
            })
    .done(function (data){
        if(data.error != null)
        {
            Swal.fire('Error', data.error, 'warning');
        }
        else
        {
            refrescarTabla();
            limpiarFormulario();
            libro = data;
            $('#txtCodigo').val(libro.id);
            Swal.fire('Movimiento realizado','Datos de libro guardados correctamente','success');
        }
    });
}

function eliminar()
{
    var id = parseInt($('#txtCodigo').val());
    
    $.ajax({
                type : "POST",
                url  : "api/libros/delete",
                data : {
                         idLibro : id,
                       }
           })
    .done(function(data){
        if(data.error != null)
        {
          Swal.fire('Error', data.error, "warning");
        }
        else
        {
            refrescarTabla();
            limpiarFormulario();
            Swal.fire('Movimiento Realizado','Registro Eliminado','success');
        }
    });
}

function refrescarTabla()
{
    var contenido = '';
    $.ajax({
                type : "GET",
                url  : "api/libros/getAll"
           })
    .done(function (data){
        if(data.error != null)
        {
           Swal.fire('Error', data.error, "warning"); 
        }
        else
        {
            libros = data;
            for (var i = 0; i < data.length; i++)
            {
                contenido = contenido + '<tr>' + 
                                            '<td>' + libros[i].idLibro + '</td>' +
                                            '<td>' + libros[i].titulo + '</td>' +
                                            '<td>' + libros[i].tema + '</td>' +
                                            '<td>' + libros[i].descripcion + '</td>' +
                                            '<td><a href="#" onclick="mostrarDetalle('+ libros[i].idLibro + ');"><i class="fas fa-eye text-dark"></i></a>' + '</td>' +
                                        '</tr>';
            }
            $('#tbodyLibros').html(contenido);
        }
    });
}

function mostrarDetalle(idLibro)
{
    var pos = buscarPosicionPorId(idLibro);
    
    var l = libros[pos];
    
    $('#txtCodigo').val(l.idLibro);
    $('#txtTitulo').val(l.titulo);
    $('#txtTema').val(l.tema);
    $('#txtDescripcion').val(l.descripcion);
    
    setDetalleVisible(true);
}

function buscarPosicionPorId(idLibro)
{
    for (var i = 0; i < libros.length; i++)
    {
        if(libros[i].idLibro === idLibro)
        {
            return i;
        }
    }
    
    return -1;
}

function limpiarFormulario()
{
    $('#txtCodigo').val('');
    $('#txtTitulo').val('');
    $('#txtTema').val('');
    $('#txtDescripcion').val('');
}

function setDetalleVisible(valor)
{
    if(valor)
    {
        $('#divTabla').removeClass('col-12');
        $('#divTabla').addClass('col-8');
        $('#divDetalle').show();
    }
    else
    {
        $('#divTabla').removeClass('col-8');
        $('#divTabla').addClass('col-12');
        $('#divDetalle').hide();
    }
}

function cerrarModulo()
{
    $("#contenedorPrincipal").html('');
}



