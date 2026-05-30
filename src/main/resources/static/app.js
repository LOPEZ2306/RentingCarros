const API_URL = '/api';

function showSection(sectionId) {
    document.querySelectorAll('.section').forEach(sec => sec.classList.remove('active'));
    document.querySelectorAll('.menu-btn').forEach(btn => btn.classList.remove('active'));
    
    document.getElementById(sectionId).classList.add('active');
    event.currentTarget.classList.add('active');

    if(sectionId === 'clientes') cargarClientes();
    if(sectionId === 'vehiculos') cargarVehiculos();
}

async function guardarCliente(method) {
    const cliente = {
        cedula: document.getElementById('c-cedula').value,
        nombre: document.getElementById('c-nombre').value,
        apellido: document.getElementById('c-apellido').value,
        telefono: document.getElementById('c-telefono').value,
        direccion: document.getElementById('c-direccion').value,
        licenciaConduccion: document.getElementById('c-licencia').value
    };

    try {
        const res = await fetch(`${API_URL}/clientes`, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cliente)
        });
        const text = await res.text();
        if(res.ok) {
            alert(text);
            cargarClientes();
        } else {
            alert("Error: " + text);
        }
    } catch(err) {
        alert("Error de red");
    }
}

async function cargarClientes() {
    const res = await fetch(`${API_URL}/clientes`);
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
}

function toggleVehiculoCampos() {
    const tipo = document.getElementById('v-tipo').value;
    if(tipo === 'sedan') {
        document.querySelector('.sedan-campos').style.display = 'flex';
        document.querySelector('.suv-campos').style.display = 'none';
    } else {
        document.querySelector('.sedan-campos').style.display = 'none';
        document.querySelector('.suv-campos').style.display = 'flex';
    }
}

async function guardarVehiculo(method) {
    const tipo = document.getElementById('v-tipo').value;
    const v = {
        tipo: tipo,
        placa: document.getElementById('v-placa').value,
        marca: document.getElementById('v-marca').value,
        modelo: document.getElementById('v-modelo').value,
        precioDiario: document.getElementById('v-precio').value,
        estado: document.getElementById('v-estado').value
    };

    if (tipo === 'sedan') {
        v.tipoCombustible = document.getElementById('v-combustible').value;
        v.transmision = document.getElementById('v-transmision').value;
    } else {
        v.traccion = document.getElementById('v-traccion').value;
        v.capacidadMaletero = document.getElementById('v-maletero').value;
    }

    const res = await fetch(`${API_URL}/vehiculos`, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(v)
    });
    const text = await res.text();
    if(res.ok) {
        alert(text);
        cargarVehiculos();
    } else {
        alert("Error: " + text);
    }
}

async function cargarVehiculos() {
    const res = await fetch(`${API_URL}/vehiculos`);
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
}

async function registrarContrato() {
    const c = {
        idContrato: document.getElementById('r-id').value,
        cedulaCliente: document.getElementById('r-cedula').value,
        placaVehiculo: document.getElementById('r-placa').value,
        fechaInicio: document.getElementById('r-fechaini').value,
        fechaFin: document.getElementById('r-fechafin').value,
        totalDias: document.getElementById('r-dias').value,
        valorTotal: document.getElementById('r-valor').value
    };

    const res = await fetch(`${API_URL}/renting`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(c)
    });
    const text = await res.text();
    alert(text);
}

async function finalizarContrato() {
    const id = document.getElementById('r-buscar-id').value;
    const res = await fetch(`${API_URL}/renting/${id}/finalizar`, { method: 'POST' });
    const text = await res.text();
    alert(text);
}

cargarClientes();
