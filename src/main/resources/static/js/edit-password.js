document.getElementById("confirmar-pass-change").addEventListener("click", function() {
    var currentPass = document.getElementById("pass").value;
    var newPass = document.getElementById("password-nueva").value;
    var recheckPass = document.getElementById("password-nueva-recheck").value;

    console.log("Contraseña actual: " + currentPass);
    console.log("Nueva contraseña: " + newPass);
    console.log("Confirmación de contraseña: " + recheckPass);

    // Si quieres mostrarlo en la página (solo con fines de prueba, no recomendado en producción)
    alert("Contraseña actual: " + currentPass + "\nNueva contraseña: " + newPass + "\nConfirmación de contraseña: " + recheckPass);
});