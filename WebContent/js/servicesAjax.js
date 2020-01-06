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
                                        <a href="#!" class="breadcrumb">Registro</a>
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
                                                        <table>

                                                            <tr>
                                                                <td><b class="etiqueta">Nombre:</b></td>
                                                                <td>
                                                                    <h6> <input type="text" value="${datos.nombre}" id="nombreVehiculo"></h6>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td><b class="etiqueta">Placa:</b></td>
                                                                <td>
                                                                    <h6> <input type="text" value="${datos.placa}" id="placa"></h6>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td><b class="etiqueta">Marca:</b></td>
                                                                <td>
                                                                    <h6> <input type="text" value="${datos.marca}" id="marca"></h6>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td><b class="etiqueta">Modelo:</b></td>
                                                                <td>
                                                                    <h6> <input type="text" value="${datos.modelo}" id="modelo"></h6>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    
                                                </div>
                                                <div class="divider mb-3 mt-3"></div>
                                                <div class="row">
                                                    <div class="col m6 s12">
                                                        <br>
                                                        <div class="invoice-action-btn">
                                                            <a href="#!" onclick="actualizarVehiculo(${datos.idVehiculo})" class="waves-effect waves-light btn">
                                                                <span>Modificar</span>
                                                            </a>                                                            
                                                            <a href="#!" onClick="listarDocumentos(${datos.idVehiculo})" class="waves-effect waves-light btn">
                                                                <span>Documentos</span>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="documentos"></div>`;
        }
    }


    ;


}

function actualizarVehiculo(idVehiculo) {

    //alert('actualizando vehiculo: ' + idVehiculo);

    let nombre = document.querySelector('#nombreVehiculo').value;
    let marca = document.querySelector('#marca').value;
    let modelo = document.querySelector('#modelo').value;
    let placa = document.querySelector('#placa').value;

    console.log(idVehiculo);
    console.log(nombre);
    console.log(marca);
    console.log(modelo);
    console.log(placa);

    let serviceUrl = `vehiculo/actualizar?idVehiculo=${idVehiculo}&nombre=${nombre}&marca=${marca}&modelo=${modelo}&placa=${placa}`;

    console.log(serviceUrl);

    const request = new XMLHttpRequest();

    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            let res = JSON.parse(this.responseText);
            alert(res.mensaje);

        }
    }

}


function eliminarVehiculo(idVehiculo) {

    //alert("eliminando vehiculo " + idVehiculo);

    let serviceUrl = `vehiculo/eliminar?idVehiculo=${idVehiculo}`;

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

function mostrarFormRegistroDocumento() {

    let elemento = document.querySelector('#vehiculos');

    elemento.innerHTML = `<nav>
                                <div class="nav-wrapper blue darken-2">
                                    <div class="col s12">
                                        <a href="vehiculos.html" class="breadcrumb"> Vehiculos</a>
                                        <a href="#!" onClick="verDetallesVehiculo(obtenerValorCookie('idVehiculo'));" class="breadcrumb">Documentos</a>
                                        <a href="#!" class="breadcrumb">Registro</a>
                                    </div>
                                </div>
                            </nav>
                            <br>
                            <div class="row">
                                <div class="col s10 offset-s3">
                                    <h4>Registro de documento</h4>
                                </div>
                                <form action="#" method="GET">
                                    <div class="col s9 offset-s3">
                                        <div class="row">
                                            <div class="input-field col s7">
                                                <label class="active">Numero</label>
                                                <input type="text" id="numero" class="validate">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s7">
                                                <label class="active">Tipo Documento</label>
                                                <select id="tDocumento" class="browser-default">
                                                    <option value="1">Soat</option>
                                                    <option value="2">Tecno-Mecánica</option>
                                                    <option value="3">Impuesto Vehicular</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s7">
                                                <label class="active">Fecha Vencimiento</label>
                                                <input type="date" id="fExpedicion" value="2020-01-01">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s7">
                                                <label class="active">Fecha Vencimiento</label>
                                                <input type="date" id="fVencimiento" value="2020-01-01">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col s10 offset-s4">
                                        <div class="row">
                                            <div class="col s6">
                                                <a class="waves-effect waves-light btn" id="validar" onclick="registrarDocumento()">Registrar</a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>`;
}

function registrarDocumento() {

    let idVehiculo = obtenerValorCookie('idVehiculo');
    let numero = document.querySelector('#numero').value;
    let tDocumento = document.querySelector('#tDocumento').value;
    let fExpedicion = document.querySelector('#fExpedicion').value;
    let fVencimiento = document.querySelector('#fVencimiento').value;

    console.log(idVehiculo);
    console.log(numero);
    console.log(tDocumento);
    console.log(fExpedicion);
    console.log(fVencimiento);

    let serviceUrl = `vehiculo/documento/registrar?idVehiculo=${idVehiculo}&numero=${numero}&tipoDocumento=${tDocumento}&fechaExpedicion=${fExpedicion}&fechaVencimiento=${fVencimiento}`;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let res = JSON.parse(this.responseText);
            console.log(res);

            if (!res.error) {
                alert(res.mensaje);
                verDetallesVehiculo(obtenerValorCookie('idVehiculo'));
                listarDocumentos(obtenerValorCookie('idVehiculo'));
            } else {
                alert(res.mensaje);
            }

        }
    }

}

function listarDocumentos(idVehiculo) {

    let serviceUrl = `vehiculo/documento/listarPorId?idVehiculo=${idVehiculo}`;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //console.log(this.responseText);
            let datos = JSON.parse(this.responseText);
            console.log(datos);

            let documentos = document.querySelector('#documentos');

            if (datos.length != 0) {

                let html = `<div class="col xl9 m8 s12">
                            <div class="card">
                                <div class="card-content invoice-print-area">
                                    <h4>Documentos</h4>`;

                for (let item of datos) {

                    if (item.tipoDocumento == 1) {
                        select = `<select id="tDocumento" class="browser-default">
                                    <option value="1" selected>Soat</option>
                                    <option value="2">Tecno-Mecánica</option>
                                    <option value="3">Impuesto Vehicular</option>
                                </select>`;
                    } else if (item.tipoDocumento == 2) {
                        select = `<select id="tDocumento" class="browser-default">
                                    <option value="1">Soat</option>
                                    <option value="2" selected>Tecno-Mecánica</option>
                                    <option value="3">Impuesto Vehicular</option>
                                </select>`;
                    } else if (item.tipoDocumento == 3) {
                        select = `<select id="tDocumento" class="browser-default">
                                    <option value="1">Soat</option>
                                    <option value="2">Tecno-Mecánica</option>
                                    <option value="3" selected>Impuesto Vehicular</option>
                                </select>`;
                    }

                    html += `<div class="documento" id="d${item.idDocumento}">
                            <ul class="collection">
                                <li class="collection-item avatar">
                                    <i class="material-icons circle red">contact_mail</i>
                                    <div class="row">
                                        <div class="input-field col s5">
                                            <label class="active">Numero</label>
                                            <input type="text" id="numero" value="${item.numero}" class="validate">                                                                    
                                        </div>
                                        <div class="input-field col s5">                                    
                                            <label class="active">Tipo Documento</label>
                                            ${select}
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s5">
                                            <label class="active">Fecha Expedicion</label>
                                            <input type="date" id="fExpedicion" value="${item.fechaExpedicion}">
                                        </div>
                                        <div class="input-field col s5">
                                            <label class="active">Fecha Vencimiento</label>
                                            <input type="date" id="fVencimiento" value="${item.fechaVencimiento}">
                                        </div>
                                    </div>
                                    <br>
                                    <a hred="#!" onClick="actualizarDocumento(${item.idDocumento});" class="waves-effect waves-light btn">Modificar</a>
                                    <a hred="#!" onClick="eliminarDocumento(${item.idDocumento})" class="waves-effect waves-light btn">Eliminar</a>
                                    <div><br></div>
                                </li>
                            </ul>
                        </div>`;
                }
                html += `<div>
                            <a class="waves-effect waves-light btn" href="#!" onclick="mostrarFormRegistroDocumento();">Añadir</a>
                        </div>
                        </div> </div> </div>`;
                documentos.innerHTML = html;
            } else {
                documentos.innerHTML = `<blockquote>El vehiculo no tiene documentos registrados</blockquote>
                                        <div>
                                            <a class="waves-effect waves-light btn" href="#!" onclick="mostrarFormRegistroDocumento();">Añadir</a>
                                        </div>`;
            }
        }
    }

}

function actualizarDocumento(idDocumento) {

    let elemento = document.querySelector(`#d${idDocumento}`);

    let numero = elemento.querySelector('#numero').value;
    let tDocumento = elemento.querySelector('#tDocumento').value;
    let fExpedicion = elemento.querySelector('#fExpedicion').value;
    let fVencimiento = elemento.querySelector('#fVencimiento').value;

    console.log(numero);
    console.log(tDocumento);
    console.log(fExpedicion);
    console.log(fVencimiento);

    let serviceUrl = `vehiculo/documento/actualizar?idDocumento=${idDocumento}&numero=${numero}&fechaExpedicion=${fExpedicion}&fechaVencimiento=${fVencimiento}&tipoDocumento=${tDocumento}`;

    console.log(serviceUrl);

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let res = JSON.parse(this.responseText);
            console.log(res);
            if (!res.error) {
                alert(res.mensaje);
                verDetallesVehiculo(obtenerValorCookie('idVehiculo'));
                listarDocumentos(obtenerValorCookie('idVehiculo'));
            } else {
                alert(res.mensaje);
            }
        }
    }
}

function eliminarDocumento(idDocumento) {

    let serviceUrl = `vehiculo/documento/eliminar?idDocumento=${idDocumento}`;

    const request = new XMLHttpRequest();
    request.open('GET', serviceUrl, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let res = JSON.parse(this.responseText);
            console.log(res);

            if (!res.error) {
                alert(res.mensaje);
                verDetallesVehiculo(obtenerValorCookie('idVehiculo'));
                listarDocumentos(obtenerValorCookie('idVehiculo'));
            } else {
                alert(res.mensaje);
            }
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
                    <p>${item.tipoNombre}</p>
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

    let serviceUrl = `core/verCronograma?idUsuario=${obtenerValorCookie('idUsuario')}`;

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
            if (datos.documentos.length != 0) {
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
            } else {
                documentos.innerHTML = `<blockquote>Aún no existen documentos registrados</blockquote>`;
            }


            console.log("Externos");
            if (datos.externos.length != 0) {
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
            } else {
                externos.innerHTML = `<blockquote>No hay eventos externos</blockquote>`;
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
    document.cookie = 'idVehiculo' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';



    location.href = "login.html";

}