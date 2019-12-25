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
                location.href = "vehiculos.html";
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

            let res = JSON.parse(this.responseText);
            console.log(res);

            if (!res.error) {
                alert(res.mensaje);
                location.href = "login.html";
            } else {
                alert(res.mensaje);
            }
        }
    }
}

function mostrarFormRegistroVehiculo() {

    let vehiculos = document.querySelector('#vehiculos');

    vehiculos.innerHTML = `<nav>
                                <div class="nav-wrapper blue darken-2">
                                    <div class="col s12">
                                        <a href="vehiculos.html" class="breadcrumb"> Vehiculos</a>
                                        <a href="#!" class="breadcrumb">Detalles</a>
                                    </div>
                                </div>
                            </nav>
                            <br>
                            <div class="row">
                                <div class="col s10 offset-s3">
                                    <h4>Registro de vehiculo</h4>
                                </div>
                                <form action="#" method="GET">
                                    <div class="col s9 offset-s3">
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input type="text" class="validate" id="nombreVehiculo">
                                                <label for="text">Nombre</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input type="text" class="validate" id="marca">
                                                <label for="text">Marca</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input type="text" class="validate" id="modelo">
                                                <label for="text">Modelo</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input type="text" class="validate" id="placa">
                                                <label for="text">Placa</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col s10 offset-s4">
                                        <div class="row">
                                            <div class="col s6">
                                                <a class="waves-effect waves-light btn" id="validar" onclick="registrarVehiculo();">Registrar</a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>`;

}

function registrarVehiculo() {

    let nombre = document.querySelector('#nombreVehiculo').value;
    let marca = document.querySelector('#marca').value;
    let modelo = document.querySelector('#modelo').value;
    let placa = document.querySelector('#placa').value;

    console.log(nombre);
    console.log(marca);
    console.log(modelo);
    console.log(placa);

    let serviceUrl = `vehiculo/registrar?nombre=${nombre}&marca=${marca}&modelo=${modelo}&placa=${placa}&idUsuario=${obtenerValorCookie('idUsuario')}`;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    console.log("Antes de insertar vehiculo");
    console.log(serviceUrl);

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            let res = JSON.parse(this.responseText);
            console.log(res);

            if (!res.error) {
                alert(res.mensaje);
                location.href = "vehiculos.html";
            } else {
                alert(res.mensaje);
            }
        }
    }
}

function listarVehiculos() {
    console.log("Estoy listando vehiculos!!");

    let serviceUrl = `vehiculo/listarPorId?idUsuario=${obtenerValorCookie('idUsuario')}`;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            let listaVehiculos = document.querySelector('#listaVehiculos');

            let datos = JSON.parse(this.responseText);
            console.log(datos);
            console.log(datos.length);
            console.log("Hola");

            if (datos.length != 0) {
                for (let item of datos) {

                    listaVehiculos.innerHTML += `<div class="col s12 m7">
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
                                                <a href="#!" onClick="verDetallesVehiculo(${item.idVehiculo});">Detalles</a>
                                                <a href="#!" onClick="eliminarVehiculo(${item.idVehiculo});">Eliminar</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>`;
                }
            } else {
                listaVehiculos.innerHTML = ` <blockquote> No tienes vehiculos registrados </blockquote>`;
            }

        }
    }
}

function verDetallesVehiculo(idVehiculo) {

    let serviceUrl = `vehiculo/verDetallesPorId?idVehiculo=${idVehiculo}`;

    const request = new XMLHttpRequest;
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            //alert("DETALLESS vehichulo: " + idVehiculo);
            let datos = JSON.parse(this.responseText);
            console.log(datos);

            let vehiculos = document.querySelector('#vehiculos');

            vehiculos.innerHTML = `<nav>
                                        <div class="nav-wrapper blue darken-2">
                                            <div class="col s12">
                                                <a href="vehiculos.html" class="breadcrumb"> Vehiculos</a>
                                                <a href="#!" class="breadcrumb">Detalles</a>
                                            </div>
                                        </div>
                                    </nav>
                                    <br>
                                    <div class="col xl9 m8 s12">
                                        <div class="card">
                                            <div class="card-content invoice-print-area">
                                                <div class="row">
                                                    <div class="col m6 s12 display-flex invoice-logo mt-1 push-m6">
                                                        <img src="${datos.urlFoto}" width="220" height="126">
                                                    </div>
                                                    <div class="col m6 s12 pull-m6">
                                                        <h4 class="indigo-text">${datos.nombre}</h4>
                                                        <h6><b class="etiqueta">Placa: </b>${datos.placa}</h6>
                                                        <h6><b class="etiqueta">Marca: </b>${datos.marca}</h6>
                                                        <h6><b class="etiqueta">Modelo: </b>${datos.modelo}</h6>
                                                    </div>
                                                </div>
                                                <div class="divider mb-3 mt-3"></div>
                                                <div class="row">
                                                    <div class="col m6 s12">
                                                        <br>
                                                        <div class="invoice-action-btn">
                                                            <a href="#!" class="waves-effect waves-light btn">
                                                                <span>Modificar</span>
                                                            </a>
                                                            <br>
                                                            <a href="#!" class="waves-effect waves-light btn">
                                                                <span>Documentos</span>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>`;
        }
    }


    ;


}

function eliminarVehiculo(idVehiculo) {

    //alert("eliminando vehiculo " + idVehiculo);

    let serviceUrl = 'vehiculo/eliminar?idVehiculo=' + idVehiculo;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let res = JSON.parse(this.responseText);
            if (!res.error) {
                alert(res.mensaje);
                location.reload();
            } else {
                alert(res.mensaje);
            }

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