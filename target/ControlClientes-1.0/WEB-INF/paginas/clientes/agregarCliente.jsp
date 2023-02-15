<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar cliente</h5>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
                  method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group mb-2">
                        <label for="nombre" class="form-label mx-1">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required>
                    </div>
                    <div class="form-group mb-2">
                        <label for="direccion" class="form-label mx-1">Dirección</label>
                        <input type="text" class="form-control" name="direccion" required>
                    </div>
                    <div class="form-group mb-2">
                        <label for="telefono" class="form-label mx-1">Teléfono</label>
                        <input type="text" class="form-control" name="telefono" required>
                    </div>
                    <div class="form-group mb-2">
                        <label for="email" class="form-label mx-1">Email</label>
                        <input type="email" class="form-control" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="saldo" class="form-label mx-1">Saldo</label>
                        <input type="number" step="0.01" class="form-control" name="saldo" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>