<section id="actions" class="py-4 mb-2 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-2 mb-2 flex-grow-1">
                <a href="index.jsp" class="btn btn-outline-secondary btn-block">
                    <i class="fas fa-arrow-left"></i> Regresar al inicio
                </a>
            </div>
            <div class="col-md-2 mb-2 ms-auto">
                <button type="sumbit" class="btn btn-success btn-block">
                    <i class="fas fa-check"></i> Guardar cliente
                </button>
            </div>
            <div class="col-md-2 ms-auto">
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${cliente.idCliente}"
                   class="btn btn-danger btn-block">
                    <i class="fas fa-trash"></i> Eliminar cliente
                </a>
            </div>
        </div>
    </div>
</section>