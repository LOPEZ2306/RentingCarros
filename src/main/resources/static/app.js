const API_URL = '/api';


function showSection(sectionId) {
    document.querySelectorAll('.section').forEach(sec => sec.classList.remove('active'));
    document.querySelectorAll('.menu-btn').forEach(btn => btn.classList.remove('active'));

    document.getElementById(sectionId).classList.add('active');
    event.currentTarget.classList.add('active');

    if (sectionId === 'clientes')  cargarClientes();
    if (sectionId === 'vehiculos') cargarVehiculos();
    if (sectionId === 'informes')  cargarTodosLosInformes();
}


async function guardarCliente(method) {
    const cliente = {
        cedula:             document.getElementById('c-cedula').value,
        nombre:             document.getElementById('c-nombre').value,
        apellido:           document.getElementById('c-apellido').value,
        telefono:           document.getElementById('c-telefono').value,
        direccion:          document.getElementById('c-direccion').value,
        licenciaConduccion: document.getElementById('c-licencia').value
    };

    try {
        const res  = await fetch(`${API_URL}/clientes`, {
            method:  method,
            headers: { 'Content-Type': 'application/json' },
            body:    JSON.stringify(cliente)
        });
        const data = await res.json().catch(() => res.text());

        if (res.ok) {
            alert(typeof data === 'string' ? data : 'Operación exitosa.');
            cargarClientes();
        } else {
            alert('Error: ' + (data.error || data));
        }
    } catch (err) {
        alert('Error de red. Verifica que el servidor esté corriendo.');
    }
}

async function eliminarCliente() {
    const cedula = document.getElementById('c-buscar-cedula').value;
    if (!cedula) {
        alert('Ingresa una cédula para eliminar.');
        return;
    }

    try {
        const res  = await fetch(`${API_URL}/clientes/${cedula}`, { method: 'DELETE' });
        const data = await res.json().catch(() => res.text());

        if (res.ok) {
            alert(typeof data === 'string' ? data : 'Cliente eliminado.');
            cargarClientes();
        } else {
            alert('Error: ' + (data.error || data));
        }
    } catch (err) {
        alert('Error de red.');
    }
}

async function cargarClientes() {
    try {
        const res  = await fetch(`${API_URL}/clientes`);
        const data = await res.json();
        const tbody = document.getElementById('tabla-clientes');
        tbody.innerHTML = '';
        data.forEach(c => {
            tbody.innerHTML += `<tr>
                <td>${c.cedula}</td>
                <td>${c.nombre}</td>
                <td>${c.apellido}</td>
                <td>${c.telefono}</td>
            </tr>`;
        });
    } catch (err) {
        console.error('Error cargando clientes:', err);
    }
}

function toggleVehiculoCampos() {
    const tipo = document.getElementById('v-tipo').value;
    if (tipo === 'sedan') {
        document.querySelector('.sedan-campos').style.display = 'flex';
        document.querySelector('.suv-campos').style.display   = 'none';
    } else {
        document.querySelector('.sedan-campos').style.display = 'none';
        document.querySelector('.suv-campos').style.display   = 'flex';
    }
}

async function guardarVehiculo(method) {
    const tipo = document.getElementById('v-tipo').value;
    const v = {
        tipo:        tipo,
        placa:       document.getElementById('v-placa').value,
        marca:       document.getElementById('v-marca').value,
        modelo:      document.getElementById('v-modelo').value,
        precioDiario:document.getElementById('v-precio').value,
        estado:      document.getElementById('v-estado').value
    };

    if (tipo === 'sedan') {
        v.tipoCombustible = document.getElementById('v-combustible').value;
        v.transmision     = document.getElementById('v-transmision').value;
    } else {
        v.traccion          = document.getElementById('v-traccion').value;
        v.capacidadMaletero = document.getElementById('v-maletero').value;
    }

    try {
        const res  = await fetch(`${API_URL}/vehiculos`, {
            method:  method,
            headers: { 'Content-Type': 'application/json' },
            body:    JSON.stringify(v)
        });
        const data = await res.json().catch(() => res.text());

        if (res.ok) {
            alert(typeof data === 'string' ? data : 'Operación exitosa.');
            cargarVehiculos();
        } else {
            alert('Error: ' + (data.error || data));
        }
    } catch (err) {
        alert('Error de red.');
    }
}

async function cargarVehiculos() {
    try {
        const res  = await fetch(`${API_URL}/vehiculos`);
        const data = await res.json();
        const tbody = document.getElementById('tabla-vehiculos');
        tbody.innerHTML = '';
        data.forEach(v => {
            tbody.innerHTML += `<tr>
                <td>${v.placa}</td>
                <td>${v.marca}</td>
                <td>${v.modelo}</td>
                <td>${v.precioDiario}</td>
                <td>${v.estado}</td>
            </tr>`;
        });
    } catch (err) {
        console.error('Error cargando vehículos:', err);
    }
}

async function registrarContrato() {
    const c = {
        idContrato:    document.getElementById('r-id').value,
        cedulaCliente: document.getElementById('r-cedula').value,
        placaVehiculo: document.getElementById('r-placa').value,
        fechaInicio:   document.getElementById('r-fechaini').value,
        fechaFin:      document.getElementById('r-fechafin').value,
        totalDias:     document.getElementById('r-dias').value,
        valorTotal:    document.getElementById('r-valor').value
    };

    try {
        const res  = await fetch(`${API_URL}/renting`, {
            method:  'POST',
            headers: { 'Content-Type': 'application/json' },
            body:    JSON.stringify(c)
        });
        const data = await res.json().catch(() => res.text());

        if (res.ok) {
            alert(typeof data === 'string' ? data : 'Contrato registrado.');
        } else {
            alert('Error: ' + (data.error || data));
        }
    } catch (err) {
        alert('Error de red.');
    }
}

async function finalizarContrato() {
    const id = document.getElementById('r-buscar-id').value;
    if (!id) {
        alert('Ingresa el ID del contrato a finalizar.');
        return;
    }

    try {
        const res  = await fetch(`${API_URL}/renting/${id}/finalizar`, { method: 'POST' });
        const data = await res.json().catch(() => res.text());

        if (res.ok) {
            alert(typeof data === 'string' ? data : 'Contrato finalizado.');
        } else {
            alert('Error: ' + (data.error || data));
        }
    } catch (err) {
        alert('Error de red.');
    }
}


async function cargarTodosLosInformes() {
    await cargarInformeContratos();
    await cargarInformeVehiculos();
    await cargarInformeIngresos();
}


async function cargarInformeContratos() {
    try {
        const res  = await fetch(`${API_URL}/informes/contratos`);
        const data = await res.json();

        document.getElementById('informe-contratos-activos').textContent =
            data.activos.length;

        llenarTablaContratos('tabla-contratos-activos', data.activos);
        llenarTablaContratos('tabla-contratos-finalizados', data.finalizados);

    } catch (err) {
        console.error('Error cargando informe de contratos:', err);
    }
}

async function cargarInformeVehiculos() {
    try {
        const res  = await fetch(`${API_URL}/informes/vehiculos`);
        const data = await res.json();

        document.getElementById('informe-vehiculos-disponibles').textContent =
            data.disponibles;
        document.getElementById('informe-vehiculos-alquilados').textContent =
            data.alquilados;

    } catch (err) {
        console.error('Error cargando informe de vehículos:', err);
    }
}

async function cargarInformeIngresos() {
    try {
        const res  = await fetch(`${API_URL}/informes/ingresos`);
        const data = await res.json();

        const formatted = new Intl.NumberFormat('es-CO', {
            style:    'currency',
            currency: 'COP',
            maximumFractionDigits: 0
        }).format(data.ingresosTotales);

        document.getElementById('informe-ingresos').textContent = formatted;

    } catch (err) {
        console.error('Error cargando informe de ingresos:', err);
    }
}

function llenarTablaContratos(idTabla, contratos) {
    const tbody = document.getElementById(idTabla);
    tbody.innerHTML = '';

    if (contratos.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6">No hay contratos para mostrar.</td></tr>';
        return;
    }

    contratos.forEach(c => {
        tbody.innerHTML += `<tr>
            <td>${c.idContrato}</td>
            <td>${c.cedulaCliente}</td>
            <td>${c.placaVehiculo}</td>
            <td>${c.fechaInicio}</td>
            <td>${c.fechaFin}</td>
            <td>${c.valorTotal}</td>
        </tr>`;
    });
}

cargarClientes();