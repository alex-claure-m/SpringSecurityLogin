/*document.getElementById('editar-perfil').addEventListener('click', function() {
    const inputs = document.querySelectorAll('input[readonly]');
    inputs.forEach(input => {
        input.removeAttribute('readonly');
        input.style.backgroundColor = '#fff'; // Cambia el fondo para asegurar que es editable
        input.style.cursor = 'text'; // Asegura que el cursor indique que se puede escribir
        console.log(input); // Verifica si los inputs ahora son editables
    });
});
*/
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('editar-perfil').addEventListener('click', function() {
        const boxEditables = ['nombre','apellido','email','usuario'];
        //lista de los id que seran editables
        //para cada uno de la lista, traeme su elemento del html que responda a su id
        boxEditables.forEach(id => {
            const input = document.getElementById(id);
            //y a cada uno le sacas el atributo readonly
            if(input){
                input.removeAttribute('readonly')
            }
        });
    });

    document.getElementById('confirm-edit').addEventListener('submit', function(event) {
        event.preventDefault(); // Evita el envío del formulario por defecto
        //el FormData toda todos los datos del FORMULARIO HTML
        const formatoData= {
          nombre: document.getElementById('nombre').value,
          apellido: document.getElementById('apellido').value,
          email: document.getElementById('email').value,
          usuario: document.getElementById('usuario').value
        };

        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        //envio con este endpoint al backend -> controller
        fetch('/confirm-perfil', {
            method:'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(formatoData)
        })
            // el response es el nombre de la variable en si que va a enviar al back a est parte del front
            .then(response=> {
                if(response.ok){
                    return response.text(); // Obtén el texto de la respuesta
                	}else {
                    // Si no es exitosa, lanza un error para que caiga en el catch
                    return response.text().then(error => {
                        throw new Error(error);
                    });
            	}
            })
            .then(data => {
                // Muestra un mensaje de éxito al usuario
                alert(data); // El mensaje "Perfil actualizado con éxito" será mostrado
                console.log(data);
            })
            .catch(error => {
                // Muestra el error en la consola y un alert si ocurre un problema
                console.error('Error:', error);
                alert('Error al actualizar el perfil: ' + error.message);
            });
    });
});