package datos;

import dominio.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDaoJDBC {

    //Definimos las querys.
    private static final String SQL_SELECT = "SELECT id_cliente, nombre, direccion, telefono, email, saldo"
            + " FROM cliente";

    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, direccion, telefono, email, saldo"
            + " FROM cliente WHERE id_cliente = ?";

    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, direccion, telefono, email, saldo)"
            + " VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE cliente SET nombre = ?, direccion = ?, telefono = ?, email = ?, saldo = ?"
            + " WHERE id_cliente = ?";

    private static final String SQL_DELETE = "DELETE FROM cliente WHRE id_cliente = ?";

    public List<Cliente> listar() {
        //Inicializamos cada una de las componentes en nulo.
        Connection conn = null;
        PreparedStatement stmt = null; //El stmt lo que hace es trabajar con la query. Por ejemplo mandarle parametros, etc.
        ResultSet rs = null; //El result set basicamente sirve cuando queremos recuperar informacion.
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            //Preparamos la conexion.
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            //Con esto recuperamos cada uno de los valores de la db.
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                double saldo = rs.getDouble("saldo");

                //Creamos el objeto con cada uno de los parametros.
                cliente = new Cliente(idCliente, nombre, direccion, telefono, email, saldo);

                //Lo agregmos al listado.
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return clientes;
    }

    public Cliente buscar(int idCliente){
         Cliente cliente = null;

        List<Cliente> clientes = new ArrayList();
        
        clientes = this.listar();
        
        for(Cliente elemento : clientes){
            if(elemento.getIdCliente() == idCliente){
                    cliente = elemento;
                    break;
            }
        }
        return cliente;
    }
    
    public Cliente encontrar(int idCliente) {
        //Inicializamos cada una de las componentes en nulo.
        Connection conn = null;
        PreparedStatement stmt = null; //El stmt lo que hace es trabajar con la query. Por ejemplo mandarle parametros, etc.
        ResultSet rs = null; //El result set basicamente sirve cuando queremos recuperar informacion.
        Cliente cliente = null;
        
        try {
            //Preparamos la conexion.
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            rs.next(); //Nos posicionamos en el primer valor devuelto.

            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            double saldo = rs.getDouble("saldo");
            
            cliente = new Cliente();
            
            cliente.setNombre(nombre);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            cliente.setEmail(email);
            cliente.setSaldo(saldo);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cliente;
    }
    
    public int insertar(Cliente cliente) {
        //Inicializamos cada una de las componentes en nulo.
        Connection conn = null;
        PreparedStatement stmt = null; //El stmt lo que hace es trabajar con la query. Por ejemplo mandarle parametros, etc.
        int rows = 0;
        try {
            //Preparamos la conexion.
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setDouble(5, cliente.getSaldo());

            rows = stmt.executeUpdate(); //Utilizamos esta funcion para saber cuantos registros fueron modificados.
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
    
    public int actualizar(Cliente cliente) {
        //Inicializamos cada una de las componentes en nulo.
        Connection conn = null;
        PreparedStatement stmt = null; //El stmt lo que hace es trabajar con la query. Por ejemplo mandarle parametros, etc.
        int rows = 0;
        try {
            //Preparamos la conexion.
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente()); 
            
            rows = stmt.executeUpdate(); //Utilizamos esta funcion para saber cuantos registros fueron modificados.
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;    
    }
    
    public int eliminar(Cliente cliente){
        //Inicializamos cada una de las componentes en nulo.
        Connection conn = null;
        PreparedStatement stmt = null; //El stmt lo que hace es trabajar con la query. Por ejemplo mandarle parametros, etc.
        int rows = 0;
        try {
            //Preparamos la conexion.
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getIdCliente());
            
            rows = stmt.executeUpdate(); //Utilizamos esta funcion para saber cuantos registros fueron modificados.
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows; 
    }
}
