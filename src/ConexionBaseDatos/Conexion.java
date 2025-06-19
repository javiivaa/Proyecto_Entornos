package ConexionBaseDatos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

	private static final String NOMBRE_BD = "universidad";

	private static final String UBICACION = "localhost";// IP de la máquina donde se encuentra el gestor de BBDD
	private static final String PUERTO = "3306";
	private static final String USUARIO = "root";
	private static final String CLAVE = "root";

	// Para versión mysql-conector-java-5.1.6.jar + mysql Server 5.7
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	//private static final String URL = "jdbc:mysql://" + UBICACION + ":" + PUERTO + "/" + NOMBRE_BD
			//+ "?useUnicode=true&characterEncoding=utf-8";

	// RECUERDA CAMBIAR!!

	// Para versión mysql-conector-java-8.0.11.jar + mysql Server 8.0.33

	// private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";

	
	  private static final String URL = "jdbc:mysql://localhost:3306/" + NOMBRE_BD  +
	  "?useUnicode=true&use" +
	 "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" +
	 
	 
	  "serverTimezone=UTC";
	 

	static {

		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			// * TODO Auto-generated catch block
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}

	public Connection conectar() {
		Connection conexion = null;

		try {

			// Establecemos la conexión para eso java nos prporciona conexion =
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);

			System.out.println("Conexión correctamente establecida con la base de datos " + NOMBRE_BD);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;
	}
	public static boolean hayStock(int productoId, int cantidadSolicitada) {
    String sql = "SELECT cantidad FROM Producto WHERE id = ?";
    try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, productoId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int stock = rs.getInt("cantidad");
            return stock >= cantidadSolicitada;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

	public static boolean clienteValido(int clienteId) {
		String sql = "SELECT COUNT(*) FROM Cliente WHERE id = ?";
		try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, clienteId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // Si hay al menos un cliente con ese ID
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void insertarPedido(int clienteId, int producto1Id, int cantidad1, int producto2Id, int cantidad2) {
	    String sql = "INSERT INTO Pedido (cliente_id, producto1_id, producto2_id, cantidad1, cantidad2, fechaPedido) VALUES (?, ?, ?, ?, ?, NOW())";
	    try (Connection conn = new Conexion().conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, clienteId);
	        stmt.setInt(2, producto1Id);
	        if (producto2Id != -1) {
	            stmt.setInt(3, producto2Id);
	        } else {
	            stmt.setNull(3, java.sql.Types.INTEGER);
	        }
	        stmt.setInt(4, cantidad1);
	        if (producto2Id != -1) {
	            stmt.setInt(5, cantidad2);
	        } else {
	            stmt.setNull(5, java.sql.Types.INTEGER);
	        }
	        stmt.executeUpdate();
	        System.out.println("Pedido guardado en la base de datos.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
