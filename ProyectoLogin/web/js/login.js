function validarUsuario(){
    var nombreUsuario = $('#txtUsuario').val();
    var contrasenia = $('#txtContrasenia').val();
    
    $.ajax({
        type: "GET",
        url : "api/login/validar",
        data: {
            nombreUsuario : nombreUsuario,
            contrasenia : contrasenia
        }
    }).done(function (data){
        if(data == null){
            Swal.fire('', data.error,'warning');
            $('#txtUsuario').val('');
            $('#txtContrasenia').val('');
        }else if(data.error != null){
             Swal.fire('Error', data.error, 'warning');
        }else if(data.id != 0){
            window.location.replace("main.html");
            Swal.fire({
                icon: 'success',
                title: 'Bienvenido',
                showConfirmButton: false,
            });
        }
    });
}

function out(){
    var data = {"idUsuario": sessionStorage.getItem("idUsuario")};
    $.ajax({
        type: "POST",
        url : "api/login/out",
        data : data
    }).done(function(data){
        if(data.result != ''){
            sessionStorage.clear();
            Swal.fire({
                title: 'Estás Seguro?',
                text: "Se cerrara la sesón",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, estoy seguro!'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.replace("index.html");
                    Swal.fire(
                            'Hasta Luego',
                            'Buen Día',
                            'success'
                            );
                }
            });
        }else if(data.error != ''){
            Swal.fire("Cierre de sesión fallido", '','error');
        }
    });
    
}