const API_URL = '/api';

function showSection(sectionId) {
    document.querySelectorAll('.section').forEach(sec => sec.classList.remove('active'));
    document.querySelectorAll('.menu-btn').forEach(btn => btn.classList.remove('active'));
    document.getElementById(sectionId).classList.add('active');
    event.currentTarget.classList.add('active');

    if (sectionId === 'clientes')  cargarClientes();
    if (sectionId === 'vehiculos') cargarVehiculos();
    if (sectionId === 'renting')   cargarContratos();
    if (sectionId === 'informes')  cargarTodosLosInformes();
}

async function leerRespuesta(res) {
    const texto = await res.text();
    try {
        const json = JSON.parse(texto);
        return json.error || json.message || texto;
    } catch (_) {
        return texto;
    }
}

// =============================================================================
// CLIENTES
// =============================================================================

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
        const res     = await fetch(`${API_URL}/clientes`, {
            method:  method,
            headers: { 'Content-Type': 'application/json' },
            body:    JSON.stringify(cliente)
        });
        const mensaje = await leerRespuesta(res);
        if (res.ok) {
            alert(mensaje || 'Cliente guardado exitosamente.');
            cargarClientes();
        } else {
            alert('Error: ' + mensaje);
        }
    } catch (err) {
        alert('Error de red. Verifica que el servidor esté corriendo.');
    }
}

async function modificarClienteInline(cedula) {
    const nombre    = document.getElementById(`edit-nombre-${cedula}`).value;
    const apellido  = document.getElementById(`edit-apellido-${cedula}`).value;
    const telefono  = document.getElementById(`edit-telefono-${cedula}`).value;
    const direccion = document.getElementById(`edit-direccion-${cedula}`).value;
    const licencia  = document.getElementById(`edit-licencia-${cedula}`).value;

    const cliente = { cedula, nombre, apellido, telefono, direccion, licenciaConduccion: licencia };

    try {
        const res     = await fetch(`${API_URL}/clientes`, {
            method:  'PUT',
            headers: { 'Content-Type': 'application/json' },
            body:    JSON.stringify(cliente)
        });
        const mensaje = await leerRespuesta(res);
        if (res.ok) {
            alert(mensaje || 'Cliente modificado exitosamente.');
            cargarClientes();
        } else {
            alert('Error: ' + mensaje);
        }
    } catch (err) {
        alert('Error de red.');
    }
}

async function eliminarCliente(cedula) {
    if (!confirm(`¿Seguro que deseas eliminar el cliente con cédula ${cedula}?`)) return;
    try {
        const res     = await fetch(`${API_URL}/clientes/${cedula}`, { method: 'DELETE' });
        const mensaje = await leerRespuesta(res);
        if (res.ok) {
            alert(mensaje || 'Cliente eliminado.');
            cargarClientes();
        } else {
            alert('Error: ' + mensaje);
        }
    } catch (err) {
        alert('Error de red.');
    }
}

function toggleEditCliente(cedula) {
    const fila = document.getElementById(`edit-form-cliente-${cedula}`);
    fila.classList.toggle('visible');
}

async function cargarClientes() {
    try {
        const res   = await fetch(`${API_URL}/clientes`);
        const data  = await res.json();
        const tbody = document.getElementById('tabla-clientes');
        tbody.innerHTML = '';
        data.forEach(c => {
            tbody.innerHTML += `
                <tr>
                    <td>${c.cedula}</td>
                    <td>${c.nombre}</td>
                    <td>${c.apellido}</td>
                    <td>${c.telefono}</td>
                    <td>
                        <button class="btn-secondary" onclick="toggleEditCliente('${c.cedula}')">Modificar</button>
                        <button class="btn-danger"    onclick="eliminarCliente('${c.cedula}')">Eliminar</button>
                    </td>
                </tr>
                <tr>
                    <td colspan="5">
                        <div id="edit-form-cliente-${c.cedula}" class="inline-edit-form">
                            <input id="edit-nombre-${c.cedula}"    value="${c.nombre}"                   placeholder="Nombre">
                            <input id="edit-apellido-${c.cedula}"  value="${c.apellido}"                 placeholder="Apellido">
                            <input id="edit-telefono-${c.cedula}"  value="${c.telefono}"                 placeholder="Teléfono">
                            <input id="edit-direccion-${c.cedula}" value="${c.direccion || ''}"          placeholder="Dirección">
                            <input id="edit-licencia-${c.cedula}"  value="${c.licenciaConduccion || ''}" placeholder="Licencia">
                            <button class="btn-success"   onclick="modificarClienteInline('${c.cedula}')">Guardar</button>
                            <button class="btn-secondary" onclick="toggleEditCliente('${c.cedula}')">Cancelar</button>
                        </div>
                    </td>
                </tr>`;
        });
    } catch (err) {
        console.error('Error cargando clientes:', err);
    }
}

// =============================================================================
// VEHÍCULOS
// =============================================================================

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
        tipo:         tipo,
        placa:        document.getElementById('v-placa').value,
        marca:        document.getElementById('v-marca').value,
        modelo:       document.getElementById('v-modelo').value,
        precioDiario: document.getElementById('v-precio').value,
        estado:       document.getElementById('v-estado').value
    };
    if (tipo === 'sedan') {
        v.tipoCombustible = document.getElementById('v-combustible').value;
        v.transmision     = document.getElementById('v-transmision').value;
    } else {
        v.traccion          = document.getElementById('v-traccion').value;
        v.capacidadMaletero = document.getElementById('v-maletero').value;
    }
    try {
        const res     = await fetch(`${API_URL}/vehiculos`, {
            method:  method,
            headers: { 'Content-Type': 'application/json' },
            body:    JSON.stringify(v)
        });
        const mensaje = await leerRespuesta(res);
        if (res.ok) {
            alert(mensaje || 'Vehículo guardado exitosamente.');
            cargarVehiculos();
        } else {
            alert('Error: ' + mensaje);
        }
    } catch (err) {
        alert('Error de red.');
    }
}

async function modificarVehiculoInline(placa) {
    const marca  = document.getElementById(`edit-v-marca-${placa}`).value;
    const modelo = document.getElementById(`edit-v-modelo-${placa}`).value;
    const precio = document.getElementById(`edit-v-precio-${placa}`).value;
    const estado = document.getElementById(`edit-v-estado-${placa}`).value;
    const tipo   = document.getElementById(`edit-v-tipo-${placa}`).value;

    const v = { tipo, placa, marca, modelo, precioDiario: precio, estado };

    if (tipo === 'sedan') {
        v.tipoCombustible = document.getElementById(`edit-v-combustible-${placa}`).value;
        v.transmision     = document.getElementById(`edit-v-transmision-${placa}`).value;
    } else {
        v.traccion          = document.getElementById(`edit-v-traccion-${placa}`).value;
        v.capacidadMaletero = document.getElementById(`edit-v-maletero-${placa}`).value;
    }

    try {
        const res     = await fetch(`${API_URL}/vehiculos`, {
            method:  'PUT',
            headers: { 'Content-Type': 'application/json' },
            body:    JSON.stringify(v)
        });
        const mensaje = await leerRespuesta(res);
        if (res.ok) {
            alert(mensaje || 'Vehículo modificado exitosamente.');
            cargarVehiculos();
        } else {
            alert('Error: ' + mensaje);
        }
    } catch (err) {
        alert('Error de red.');
    }
}

async function eliminarVehiculo(placa) {
    if (!confirm(`¿Seguro que deseas eliminar el vehículo con placa ${placa}?`)) return;
    try {
        const res     = await fetch(`${API_URL}/vehiculos/${placa}`, { method: 'DELETE' });
        const mensaje = await leerRespuesta(res);
        if (res.ok) {
            alert(mensaje || 'Vehículo eliminado.');
            cargarVehiculos();
        } else {
            alert('Error: ' + mensaje);
        }
    } catch (err) {
        alert('Error de red.');
    }
}

function toggleEditVehiculo(placa) {
    const fila = document.getElementById(`edit-form-vehiculo-${placa}`);
    fila.classList.toggle('visible');
}

async function cargarVehiculos() {
    try {
        const res   = await fetch(`${API_URL}/vehiculos`);
        const data  = await res.json();
        const tbody = document.getElementById('tabla-vehiculos');
        tbody.innerHTML = '';
        data.forEach(v => {
            const esSuv   = v.traccion !== undefined && v.traccion !== null;
            const tipoVal = esSuv ? 'suv' : 'sedan';

            const camposExtra = esSuv
                ? `<input id="edit-v-traccion-${v.placa}"  value="${v.traccion || ''}"          placeholder="Tracción">
                   <input id="edit-v-maletero-${v.placa}"  value="${v.capacidadMaletero || ''}" placeholder="Maletero (L)" type="number">`
                : `<select id="edit-v-combustible-${v.placa}">
                       <option value="gasolina"  ${v.tipoCombustible === 'gasolina'  ? 'selected' : ''}>Gasolina</option>
                       <option value="eléctrico" ${v.tipoCombustible === 'eléctrico' ? 'selected' : ''}>Eléctrico</option>
                       <option value="diésel"    ${v.tipoCombustible === 'diésel'    ? 'selected' : ''}>Diésel</option>
                   </select>
                   <select id="edit-v-transmision-${v.placa}">
                       <option value="automática" ${v.transmision === 'automática' ? 'selected' : ''}>Automática</option>
                       <option value="manual"     ${v.transmision === 'manual'     ? 'selected' : ''}>Manual</option>
                   </select>`;

            tbody.innerHTML += `
                <tr>
                    <td>${v.placa}</td>
                    <td>${v.marca}</td>
                    <td>${v.modelo}</td>
                    <td>${v.precioDiario}</td>
                    <td>${v.estado}</td>
                    <td>
                        <button class="btn-secondary" onclick="toggleEditVehiculo('${v.placa}')">Modificar</button>
                        <button class="btn-danger"    onclick="eliminarVehiculo('${v.placa}')">Eliminar</button>
                    </td>
                </tr>
                <tr>
                    <td colspan="6">
                        <div id="edit-form-vehiculo-${v.placa}" class="inline-edit-form">
                            <input type="hidden" id="edit-v-tipo-${v.placa}" value="${tipoVal}">
                            <input id="edit-v-marca-${v.placa}"  value="${v.marca}"        placeholder="Marca">
                            <input id="edit-v-modelo-${v.placa}" value="${v.modelo}"       placeholder="Modelo" type="number">
                            <input id="edit-v-precio-${v.placa}" value="${v.precioDiario}" placeholder="Precio/día" type="number">
                            <select id="edit-v-estado-${v.placa}">
                                <option value="disponible" ${v.estado === 'disponible' ? 'selected' : ''}>Disponible</option>
                                <option value="alquilado"  ${v.estado === 'alquilado'  ? 'selected' : ''}>Alquilado</option>
                            </select>
                            ${camposExtra}
                            <button class="btn-success"   onclick="modificarVehiculoInline('${v.placa}')">Guardar</button>
                            <button class="btn-secondary" onclick="toggleEditVehiculo('${v.placa}')">Cancelar</button>
                        </div>
                    </td>
                </tr>`;
        });
    } catch (err) {
        console.error('Error cargando vehículos:', err);
    }
}

async function buscarClienteParaContrato() {
    const cedula      = document.getElementById('r-cedula').value.trim();
    const campoNombre = document.getElementById('r-nombre-cliente');
    if (!cedula) { campoNombre.value = ''; return; }
    try {
        const res = await fetch(`${API_URL}/clientes/${cedula}`);
        if (res.ok) {
            const c           = await res.json();
            campoNombre.value = c.nombre + ' ' + c.apellido;
        } else {
            campoNombre.value = '⚠ Cliente no encontrado';
        }
    } catch (err) {
        campoNombre.value = '⚠ Error de red';
    }
}

async function calcularPreviewDesdeBackend() {
    const placa       = document.getElementById('r-placa').value.trim();
    const fechaInicio = document.getElementById('r-fechaini').value;
    const fechaFin    = document.getElementById('r-fechafin').value;

    const campoMarca = document.getElementById('r-marca-vehiculo');
    const campoDias  = document.getElementById('r-dias');
    const campoValor = document.getElementById('r-valor');

    if (!placa || !fechaInicio || !fechaFin) return;

    try {
        const res = await fetch(
            `${API_URL}/renting/preview?placa=${placa}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`
        );
        if (res.ok) {
            const data       = await res.json();
            campoMarca.value = data.marca + ' ' + data.modelo + ' — $' + data.precioDiario + '/día (' + data.estado + ')';
            campoDias.value  = data.totalDias;
            campoValor.value = data.valorTotal;
        } else {
            const mensaje    = await leerRespuesta(res);
            campoMarca.value = '⚠ ' + mensaje;
            campoDias.value  = '';
            campoValor.value = '';
        }
    } catch (err) {
        campoMarca.value = '⚠ Error de red';
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
        const res     = await fetch(`${API_URL}/renting`, {
            method:  'POST',
            headers: { 'Content-Type': 'application/json' },
            body:    JSON.stringify(c)
        });
        const mensaje = await leerRespuesta(res);
        if (res.ok) {
            alert(mensaje || 'Contrato registrado exitosamente.');
            cargarContratos();
        } else {
            alert('Error: ' + mensaje);
        }
    } catch (err) {
        alert('Error de red.');
    }
}

async function finalizarContrato(idContrato) {
    if (!confirm(`¿Finalizar el contrato ${idContrato}?`)) return;
    try {
        const res     = await fetch(`${API_URL}/renting/${idContrato}/finalizar`, { method: 'POST' });
        const mensaje = await leerRespuesta(res);
        if (res.ok) {
            alert(mensaje || 'Contrato finalizado exitosamente.');
            cargarContratos();
        } else {
            alert('Error: ' + mensaje);
        }
    } catch (err) {
        alert('Error de red.');
    }
}

async function cargarContratos() {
    try {
        const res   = await fetch(`${API_URL}/renting`);
        const data  = await res.json();
        const tbody = document.getElementById('tabla-contratos');
        if (!tbody) return;
        tbody.innerHTML = '';
        data.forEach(c => {
            const estaActivo = c.estado === 'activo';
            tbody.innerHTML += `
                <tr>
                    <td>${c.idContrato}</td>
                    <td>${c.cedulaCliente}</td>
                    <td>${c.placaVehiculo}</td>
                    <td>${c.fechaInicio}</td>
                    <td>${c.fechaFin}</td>
                    <td>${c.totalDias}</td>
                    <td>${c.valorTotal}</td>
                    <td>${c.estado}</td>
                    <td>
                        ${estaActivo
                            ? `<button class="btn-danger" onclick="finalizarContrato('${c.idContrato}')">Finalizar</button>`
                            : '<span style="color:var(--text-muted)">Finalizado</span>'
                        }
                    </td>
                </tr>`;
        });
    } catch (err) {
        console.error('Error cargando contratos:', err);
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
        document.getElementById('informe-contratos-activos').textContent = data.activos.length;
        llenarTablaContratos('tabla-contratos-activos',     data.activos);
        llenarTablaContratos('tabla-contratos-finalizados', data.finalizados);
    } catch (err) {
        console.error('Error cargando informe de contratos:', err);
    }
}

async function cargarInformeVehiculos() {
    try {
        const res  = await fetch(`${API_URL}/informes/vehiculos`);
        const data = await res.json();
        document.getElementById('informe-vehiculos-disponibles').textContent = data.disponibles;
        document.getElementById('informe-vehiculos-alquilados').textContent  = data.alquilados;
    } catch (err) {
        console.error('Error cargando informe de vehículos:', err);
    }
}

async function cargarInformeIngresos() {
    try {
        const res  = await fetch(`${API_URL}/informes/ingresos`);
        const data = await res.json();
        const formatted = new Intl.NumberFormat('es-CO', {
            style: 'currency', currency: 'COP', maximumFractionDigits: 0
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