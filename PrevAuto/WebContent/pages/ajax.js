console.log("HOLA");

window.onload = function() {

    document.querySelector('#boton').addEventListener('click', traerDatos);
}



function traerDatos() {

    console.log("Dentro de la funcion");

    const request = new XMLHttpRequest();
	
	//let url = 'http://localhost:9090/PrevAuto/core/listarSitiosRecomendados';

    request.open('GET', 'respuesta.json', true);

    request.send();

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //console.log(this.responseText);
        	let datos = JSON.parse(this.responseText);
        	console.log(datos);
        	
        	let res = document.querySelector('#res');
        	
        	
        	for(let item of datos){
        		res.innerHTML += `<tr>
						<td> ${item.idDocumento} </td>
						<td> ${item.numero} </td>
						<td> ${item.fechaExpedicion} </td>
						<td> ${item.fechaVencimiento} </td>
						<td> ${item.tipoDocumentoNombre} </td>
						</tr>`;		
						
        	}
			
            
        }
    }

   


}