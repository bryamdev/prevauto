console.log("SALUDO");


function validarLogin() {
    console.log("Dentro de validar Login");

    let email = document.querySelector('#email').value;
    let password = document.querySelector('#password').value;

    console.log(email + " - " + password);

    let serviceUrl = `usuario/validar?email=${email}&password=${password}`;

    const request = new XMLHttpRequest();

    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //console.log(this.responseText);
            let datos = JSON.parse(this.responseText);
            console.log(datos);
            if (!datos.error) {
                alert("Bienvenido!");
                location.href = "cronograma.html";
            } else {
                alert(datos.mensaje);
            }
        }
    }
}

function registrarUsuario() {

    let nombre = document.querySelector('#nombre').value;
    let apellido = document.querySelector('#apellido').value;
    let email = document.querySelector('#email').value;
    let password = document.querySelector('#password').value;
    let cedula = document.querySelector('#cedula').value;
    let telefono = document.querySelector('#telefono').value;
    console.log(nombre);
    console.log(apellido);
    console.log(email);
    console.log(password);
    console.log(cedula);
    console.log(telefono);

    let serviceUrl = `usuario/registrar?nombre=${nombre}&apellido=${apellido}&email=${email}&password=${password}&cedula=${cedula}&telefono=${telefono}`;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    console.log("Antes de verificar");
    console.log(serviceUrl);
    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            let res = JSON.parse(this.responseText);

            if (!res.error) {
                alert(res.mensaje);
                location.href = "login.html";
            } else {
                alert(res.mensaje);
            }
        }
    }
}


function listarVehiculos() {
    console.log("Estoy listando vehiculos!!");


    let idUsuario = '1';
    let serviceUrl = 'vehiculo/listarPorId?idUsuario=' + idUsuario;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            let vehiculos = document.querySelector('#vehiculos');

            let datos = JSON.parse(this.responseText);
            console.log(datos);
            console.log("Hola");

            for (let item of datos) {

                vehiculos.innerHTML += `<div class="col s12 m7">
                                    <div class="card horizontal">
                                        <div class="card-image">
                                            <img src="${item.urlFoto}">
                                        </div>
                                        <div class="card-stacked">
                                            <div class="card-content">
                                                <p><b>${item.nombre}</b></p>
                                                <p>${item.marca}</p>
                                            </div>
                                            <div class="card-action">
                                                <a href="#">Detalles</a>
                                                <a href="#" onClick="eliminarVehiculo(${item.idVehiculo});">Eliminar</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>`;
            }
        }
    }
}

function eliminarVehiculo(idVehiculo) {

    //alert("eliminando vehiculo " + idVehiculo);

    let serviceUrl = 'vehiculo/eliminar?idVehiculo=4' + idVehiculo;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let res = JSON.parse(this.responseText);
            alert(res.mensaje);
            console.log(res.mensaje);

        }
    }
}

function listarSitiosRecomendados() {
    console.log("Listando sitios");

    let serviceUrl = 'core/listarSitiosRecomendados';

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            let sitiosRecomentados = document.querySelector('#sitiosRecomendados');

            let datos = JSON.parse(this.responseText);
            console.log(datos);

            for (item of datos) {

                sitiosRecomentados.innerHTML += `<ul class="collection">
                <li class="collection-item avatar">
                    <i class="material-icons circle green">place</i>
                    <span class="title">${item.nombre}</span>
                    <p>${item.tipo}</p>
                    <p>${item.telefono}</p>
                    <p>${item.direccion}</p>
                    <p>${item.horario}</p>
                    <p>${item.descripcion}</p>
                    <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>
                </li>
            </ul>`;
            }
        }
    }
}

function verCronograma() {

    console.log("Cargando cronograma");

    let idUsuario = 1;
    let serviceUrl = 'core/verCronograma?idUsuario=' + idUsuario;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            let datos = JSON.parse(this.responseText);
            console.log(datos);

            let documentos = document.querySelector('#documentos');
            let externos = document.querySelector('#externos');

            console.log("Documentos");
            for (let item of datos.documentos) {
                //console.log(item.idDocumento);
                documentos.innerHTML += `<ul class="collection">
                                            <li class="collection-item avatar">
                                                <i class="material-icons circle red">contact_mail</i>
                                                <span class="title">${item.fechaVencimiento}</span>
                                                <p>${item.nombreVehiculo}</p>
                                                <p>${item.tipoDocumentoNombre}</p>
                                            </li>
                                        </ul>`;
            }

            console.log("Externos");
            for (let item of datos.externos) {
                //console.log(item.nombre);
                externos.innerHTML += `<ul class="collection">
                                            <li class="collection-item avatar">
                                                <i class="material-icons circle green">public</i>
                                                <span class="title">${item.fecha}</span>
                                                <p>${item.nombre}</p>
                                                <p>${item.tipoEvento}</p>
                                                <p>${item.descripcion}</p>
                                            </li>
                                        </ul>`;
            }
        }
    }
}


function obtenerValorCookie(clave) {

    var nameEQ = clave + "=";
    var ca = document.cookie.split(';');

    for (var i = 0; i < ca.length; i++) {

        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) {
            return decodeURIComponent(c.substring(nameEQ.length, c.length));
        }

    }

    return null;

}

function leerCookies() {

    let nombre = document.querySelector('#nombre');
    let email = document.querySelector('#email');
    let urlFoto = document.querySelector('#urlFoto');

    nombre.innerHTML = obtenerValorCookie('nombre');
    email.innerHTML = obtenerValorCookie('email');
    urlFoto.innerHTML = `<img class="circle" src="${obtenerValorCookie('urlFoto')}">`;

}



function cerrarSesion() {

    console.log("Eliminando cookies");
    document.cookie = 'nombre' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    document.cookie = 'email' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    document.cookie = 'urlFoto' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    document.cookie = 'idUsuario' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';

    location.href = "login.html";

}