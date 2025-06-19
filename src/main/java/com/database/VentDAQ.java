package com.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date; // Importa java.util.Date para el uso en el método updateVent

public class VentDAQ {

    // Método para insertar una venta
    public void insertVent(Vent vent) {
        // CORRECCIÓN CLAVE: La sentencia SQL INSERT debe incluir la columna ID_Usuario
        // ya que en la base de datos está definida como NOT NULL y sin valor predeterminado.
        String sql = "INSERT INTO Venta (Fecha, Total, Metodo_Pago, ID_Usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Asignación de valores a los placeholders (?) en el orden correcto
            pstmt.setDate(1, new java.sql.Date(vent.getFecha().getTime())); // Convierte java.util.Date a java.sql.Date
            pstmt.setBigDecimal(2, vent.getTotal());
            pstmt.setString(3, vent.getMetodo_Pago());
            pstmt.setInt(4, vent.getID_Usuario()); // AHORA se envía el valor de ID_Usuario

            pstmt.executeUpdate(); // Ejecuta la inserción
            System.out.println("Venta insertada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar venta: " + e.getMessage());
        }
    }

    // Método para obtener todas las ventas
    public List<Vent> getAllventa() {
        List<Vent> venta = new ArrayList<>();
        String sql = "SELECT * FROM Venta"; // Se usa "Venta" (mayúscula) para consistencia con la tabla
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vent vent = new Vent(
                        rs.getInt("ID_Venta"),
                        rs.getDate("Fecha"),
                        rs.getBigDecimal("Total"),
                        rs.getString("Metodo_Pago"),
                        rs.getInt("ID_Usuario")
                );
                venta.add(vent);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ventas: " + e.getMessage());
        }
        return venta;
    }

    // Método para actualizar una venta
    // Se asegura de que el java.util.Date se convierta a java.sql.Date
    public void updateVent(int ID_Venta, Date Fecha, BigDecimal Total, String Metodo_Pago, int ID_Usuario) {
        String sql = "UPDATE Venta SET Fecha = ?, Total = ?, Metodo_Pago = ?, ID_Usuario = ? WHERE ID_Venta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(Fecha.getTime()));
            preparedStatement.setBigDecimal(2, Total);
            preparedStatement.setString(3, Metodo_Pago);
            preparedStatement.setInt(4, ID_Usuario);
            preparedStatement.setInt(5, ID_Venta);

            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Venta actualizada. Filas afectadas: " + rowsUpdated);
        } catch (SQLException e) {
            System.err.println("Error al actualizar la venta: " + e.getMessage());
        }
    }

    // Método para eliminar una venta
    // CORRECCIÓN: La tabla a eliminar es "Venta", no "usuario"
    public void deleteVent(int ID_Venta) {
        String sql = "DELETE FROM Venta WHERE ID_Venta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID_Venta);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Venta eliminada exitosamente.");
            } else {
                System.out.println("No se encontró la venta con ID: " + ID_Venta);
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar venta: " + e.getMessage());
        }
    }
}