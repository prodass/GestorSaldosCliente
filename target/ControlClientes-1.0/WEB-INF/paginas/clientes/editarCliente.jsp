<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/c553fd1611.js" crossorigin="anonymous"></script>
        <title>Editar cliente</title>
    </head>
    <body>

        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>

        <form action="${pageContext.request.contextPath}/ServletControlador?accion=modificar&idCliente=${cliente.idCliente}"
              method="POST" class="was-validated">

            <jsp:include page="/WEB-INF/paginas/comunes/btn-nav-editar.jsp"/>

            <section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>
                                        Editar cliente 
                                    </h4>
                                </div>
                                <div class="card-body">
                                    <div class="form-group mb-2">
                                        <label for="nombre" class="form-label mx-1">Nombre</label>
                                        <input type="text" class="form-control" name="nombre" required value="${cliente.nombre}">
                                    </div>
                                    <div class="form-group mb-2">
                                        <label for="direccion" class="form-label mx-1">Dirección</label>
                                        <input type="text" class="form-control" name="direccion" required value="${cliente.direccion}">
                                    </div>
                                    <div class="form-group mb-2">
                                        <label for="telefono" class="form-label mx-1">Teléfono</label>
                                        <input type="text" class="form-control" name="telefono" required value="${cliente.telefono}">
                                    </div>
                                    <div class="form-group mb-2">
                                        <label for="email" class="form-label mx-1">Email</label>
                                        <input type="email" class="form-control" name="email" required value="${cliente.email}">
                                    </div>
                                    <div class="form-group">
                                        <label for="saldo" class="form-label mx-1">Saldo</label>
                                        <input type="number" step="0.01" class="form-control" name="saldo" required value="${cliente.saldo}">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>

        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp"/>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
    </body>
</html>
