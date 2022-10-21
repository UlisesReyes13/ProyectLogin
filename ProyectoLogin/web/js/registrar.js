function registrar(){
    var usuario = new Object();
    
    usuario.idUsuario = 0;
    usuario.nombre = $('#txtNombre').val();
    usuario.apePaterno = $('#txtApePaterno').val();
    usuario.apeMaterno = $('#txtApeMaterno').val();
    usuario.nombreUsuario = $('#txtUsuarioRegistrar').val();
    usuario.contrasenia = $('#txtContraseniaRegistrar').val();
    console.log($('#txtNombre').val());
    console.log(usuario.nombre);
    $.ajax({
        type: "GET",
        url: "api/registro/Registrar",
        data: {
            nombre : usuario.nombre,
            apePaterno : usuario.apePaterno,
            apeMaterno : usuario.apeMaterno,
            nombreUsuario : usuario.nombreUsuario,
            contrasenia : usuario.contrasenia
        }
    }).done(function (data){
        if(data.error != null){
            swal.fire('Error', data.error, 'warning');
        }else{
            data = usuario;
            limpiarDatos();
            Swal.fire('Movimiento realizado','Datos guardados correctamente', 'success');
        }
    });   
}

function limpiarDatos(){
    $('#txtNombre').val('');
    $('#txtApePaterno').val('');
    $('#txtApeMaterno').val('');
    $('#txtUsuarioRegistrar').val('');
    $('#txtContraseniaRegistrar').val('');
}
