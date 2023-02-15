package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion;
        accion = request.getParameter("accion");

        if (accion != null) {
            if (accion.equals("editar")) {
                this.editarCliente(request, response);
            } else if (accion.equals("eliminar")) {
                this.eliminarCliente(request, response);
            } else {
                this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("Clientes: " + clientes);

        HttpSession session = request.getSession();

        session.setAttribute("clientes", clientes); //Adjuntamos el listado de clientes al request. Teoricamente la colocamos en el alcance de request.
        session.setAttribute("totalClientes", clientes.size());
        session.setAttribute("saldoTotal", this.getSaldoClientes(clientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response); //Enviamos la informacion hacia el jsp de clientes. El problema que tiene esto es que no resetea la URL lo que nos trae problemas de duplicacion.

        response.sendRedirect("clientes.jsp");
    }

    private double getSaldoClientes(List<Cliente> clientes) {
        double saldoTotal = 0;

        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }

        return saldoTotal;
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        Cliente cliente = new ClienteDaoJDBC().buscar(idCliente);

        request.setAttribute("cliente", cliente);

        String jspEditar = "/WEB-INF/paginas/clientes/editarCliente.jsp";

        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Primero recuperamos los valores del form.
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        //Lo insertamos en la db.
        int registrosModificados = new ClienteDaoJDBC().eliminar(idCliente);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia la accion por default.
        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            if (accion.equals("insertar")) {
                this.insertarCliente(request, response);
            } else if (accion.equals("modificar")) {
                this.actualizarCliente(request, response);
            } else {
                this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Primero recuperamos los valores del form.
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        double saldo = 0;

        if (!request.getParameter("saldo").equals("") && request.getParameter("saldo") != null) {
            saldo = Double.parseDouble(request.getParameter("saldo"));
        }

        //Creamos el objeto del cliente (modelo).
        Cliente cliente = new Cliente(nombre, direccion, telefono, email, saldo);

        //Lo insertamos en la db.
        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia la accion por default.
        this.accionDefault(request, response);
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Primero recuperamos los valores del form.
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        double saldo = 0;

        if (!request.getParameter("saldo").equals("") && request.getParameter("saldo") != null) {
            saldo = Double.parseDouble(request.getParameter("saldo"));
        }

        //Creamos el objeto del cliente (modelo).
        Cliente cliente = new Cliente(idCliente, nombre, direccion, telefono, email, saldo);

        //Lo insertamos en la db.
        int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia la accion por default.
        this.accionDefault(request, response);
    }
}
