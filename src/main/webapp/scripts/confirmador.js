/**
 * 
 */

function confirmar(idcontacto){
	let resposta = confirm("Confirma a exclusão deste contacto?")
	if(resposta === true){
		window.location.href = "delete?idcontacto="+idcontacto
	}
}