console.log("SALUDO");

function listarVehiculos() {
    console.log("Estoy listando vehiculos!!");


    let idUsuario = '1';
    let serviceUrl = 'http://localhost:9090/PrevAuto/vehiculo/listarPorId?idUsuario=' + idUsuario;

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

    let serviceUrl = 'http://localhost:9090/PrevAuto/vehiculo/eliminar?idVehiculo=4' + idVehiculo;

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

    let serviceUrl = 'http://localhost:9090/PrevAuto/core/listarSitiosRecomendados';

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
    let serviceUrl = 'http://localhost:9090/PrevAuto/core/verCronograma?idUsuario=' + idUsuario;

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