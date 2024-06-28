package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.TblUsuariocl2;

public class ClassUsuario {

    // Datos de la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/BDHerreraSanchezcl2?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Añade tu contraseña aquí

    // Método para obtener la conexión a la base de datos
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para validar el usuario
    public TblUsuariocl2 validarUsuario(String usuario, String password) {
        TblUsuariocl2 user = null;
        String query = "SELECT * FROM tbl_usuariocl2 WHERE USUARIOCL2 = ? AND PASSWORDCL2 = ?";
        
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, usuario);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new TblUsuariocl2();
                    user.setIdusuariocl2(rs.getInt("IDUSUARIOCL2"));
                    user.setUsuariocl2(rs.getString("USUARIOCL2"));
                    user.setPasswordcl2(rs.getString("PASSWORDCL2"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
}