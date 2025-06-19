package com.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAQ {
    // Método para insertar un usuario
    public void insertUser(User user) {
        String sql = "INSERT INTO usuario (CC,Nombre,Email,Contraseña,Rol) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getCC());
            pstmt.setString(2, user.getNombre());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getContraseña());
            pstmt.setString(5, user.getRol());
            pstmt.executeUpdate();
            System.out.println("Usuario insertado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());

        }
    }

    // Método para obtener todos los usuarios
    public List<User> getAllusuario() {
        List<User> usuario = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                        rs.getInt("ID_Usuario"),
                        rs.getInt("CC"),
                        rs.getString("Nombre"),
                        rs.getString("Email"),
                        rs.getString("Contraseña"),
                        rs.getString("Rol")
                );
                usuario.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        return usuario;
    }

    // Método para actualizar un usuario
    public void updateUser(int ID_Usuario, int CC, String Nombre, String Email, String Contraseña, String Rol) {
        String sql = "UPDATE usuario SET Nombre = ?, Email = ?,Contraseña=?,Rol=?,CC=? WHERE ID_Usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, Nombre);
            preparedStatement.setString(2, Email);
            preparedStatement.setString(3, Contraseña);
            preparedStatement.setString(4, Rol);
            preparedStatement.setInt(5, ID_Usuario);
            preparedStatement.setInt(6, CC);

            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Usuario actualizado. Filas afectadas: " + rowsUpdated);
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    // Método para eliminar un usuario
    public void deleteUser(int ID_Usuario) {
        String sql = "DELETE FROM usuario WHERE ID_Usuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID_Usuario);
            pstmt.executeUpdate();
            System.out.println("Usuario eliminado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
}



