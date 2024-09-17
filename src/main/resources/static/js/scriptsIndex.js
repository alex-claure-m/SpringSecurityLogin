/*
function toggleTextbox(){
        var textboxContainer = document.getElementById("textbox-container");
        if(textboxContainer.style.display ==="none"){
            textboxContainer.style.display = "block";
        }else{
            textboxContainer.style.display === "none";
        }
    }
*/
function replaceButtonWithTextbox() {
    const button = document.querySelector('.custom-button'); // tomo la clase del boton para luego..
    const textbox = document.getElementById('searchTextbox'); // tomo la id del boton del textbox
    const imgbox =  document.getElementById('searchImage'); // tomo la id de la imagen!

    button.style.display = 'none'; // ..ocultarlo
    textbox.style.display = 'block'; // y aparezca y bloquee
    imgbox.style.display='inline-block';

}
function searchKeyEnter(){
    document.getElementById('searchTextbox').addEventListener('keypress', function (e) {
        if (e.key === 'Enter') {
            e.preventDefault(); // Evita el envío del formulario al presionar Enter
            this.closest('form').submit(); // Envía el formulario
        }
    });
}
function showSearchTextbox() {
    document.querySelector(".custom-button").style.display = 'none';
    document.getElementById("searchContent").style.display = 'inline-block';
}

function replaceButtonWithButtonGeoRef(){
    document.getElementById('searchGeoRef').style.display='none';
    document.getElementById('geoRefContainer').style.display = 'flex'; // si esta block no aplica el css de mover, si es flex, si!

}
function goToCustomService() {
    window.location.href = "/custom_service"; // Reemplaza con la URL adecuada
}
function getLocationGeoRef(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(enviarGeoToJava,showError);

    }else{
        alert("si se abrio esta ventana es por que fallo");
    }
}

function enviarGeoToJava(posicion){
    const latitude = posicion.coords.latitude;
    const longitude = posicion.coords.longitude;
    // creo objeto para enviarselo al backend
    const locationData = {
        lat: latitude,
        lng: longitude
    };
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    //endpoint al cual debo enviar los datos
    fetch('/geo-location-position-search', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken // llamo al token debido a que springboot security tiene esta proteccion
            // el csrf( crooss site request forgery), es decir una proteccion para ataques en el que
            // puede enganar a un user autenticado para que realice acciones no deseadas en una app web
            // l etoken es para asegurar que las solicitudes del user provienen de fuentes legitimas
            // el html genera un token (hay un <meta>) y valido en el javascript
        },
        body: JSON.stringify(locationData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al enviar la ubicación.");
            }
            return response.json(); // Convierte la respuesta a JSON
        })
        .then(url => {
            if (url.redireccionlista) {
                // es complicado de entender
                /*pero dice algo como que se redirecciona a la url.redireccionLista del controller
                * y le agrega en base a que es lo que haya en el JSON.stringify(url.listaInstancias) -que es pasado del response.put - backend */
                window.location.href = url.redireccionlista  + "?listaInstancias=" + encodeURIComponent(JSON.stringify(url.listaInstancias));;
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Ocurrió un error al intentar enviar la ubicación.");
        });
}

function showError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            alert("Usuario negó la solicitud de Geolocalización.");
            break;
        case error.POSITION_UNAVAILABLE:
            alert("La información de la ubicación no está disponible.");
            break;
        case error.TIMEOUT:
            alert("La solicitud para obtener la ubicación expiró.");
            break;
        case error.UNKNOWN_ERROR:
            alert("Ocurrió un error desconocido.");
            break;
    }
}