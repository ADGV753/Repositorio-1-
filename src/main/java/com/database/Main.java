package com.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Probar conexión
        probarConexionBD();

        // Inicializar DAOs
        UserDAQ userDAO = new UserDAQ();
        VentDAQ ventDAO = new VentDAQ();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Operaciones con Usuarios");
            System.out.println("2. Operaciones con Ventas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    menuUsuarios(userDAO, scanner);
                    break;
                case 2:
                    menuVentas(ventDAO, scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (option != 3);

        scanner.close();
    }

    private static void probarConexionBD() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Conexión exitosa a la base de datos.");
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void menuUsuarios(UserDAQ userDAO, Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- CRUD Usuarios ---");
            System.out.println("1. Insertar usuario");
            System.out.println("2. Consultar usuarios");
            System.out.println("3. Actualizar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Ingrese CC: ");
                    int CC = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese el nombre: ");
                    String Nombre = scanner.nextLine();
                    System.out.print("Ingrese el correo: ");
                    String Email = scanner.nextLine();
                    System.out.print("Ingrese la contraseña: ");
                    String Contraseña = scanner.nextLine();
                    System.out.print("Ingrese el rol: ");
                    String Rol = scanner.nextLine();
                    User newUser = new User(0, CC, Nombre, Email, Contraseña, Rol);
                    userDAO.insertUser(newUser);
                    break;

                case 2:
                    userDAO.getAllusuario().forEach(user ->
                            System.out.println("ID: " + user.getID_Usuario()
                                    + " ,CC: " + user.getCC()
                                    + ", Nombre: " + user.getNombre()
                                    + ", Email: " + user.getEmail()
                                    + ", Contraseña: " + user.getContraseña()
                                    + ", Rol: " + user.getRol())
                    );
                    break;

                case 3:
                    System.out.print("Ingrese el ID del usuario a actualizar: ");
                    int updateID_Usuario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese la nueva CC: ");
                    int updateCC = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre: ");
                    String newNombre = scanner.nextLine();
                    System.out.print("Ingrese el nuevo correo: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Ingrese la nueva contraseña: ");
                    String newContraseña = scanner.nextLine();
                    System.out.print("Ingrese el nuevo rol: ");
                    String newRol = scanner.nextLine();
                    userDAO.updateUser(updateID_Usuario, updateCC, newNombre, newEmail, newContraseña, newRol);
                    break;

                case 4:
                    System.out.print("Ingrese el ID del usuario a eliminar: ");
                    int deleteID_Usuario = scanner.nextInt();
                    scanner.nextLine();
                    userDAO.deleteUser(deleteID_Usuario);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (option != 5);
    }

    private static void menuVentas(VentDAQ ventDAO, Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- CRUD Ventas ---");
            System.out.println("1. Insertar venta");
            System.out.println("2. Consultar ventas");
            System.out.println("3. Actualizar venta");
            System.out.println("4. Eliminar venta");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Ingrese Fecha (YYYY-MM-DD): ");
                    Date Fecha = Date.valueOf(scanner.nextLine());
                    System.out.print("Ingrese el Total: ");
                    BigDecimal Total = scanner.nextBigDecimal();
                    scanner.nextLine();
                    System.out.print("Ingrese el método de pago: ");
                    String Metodo_Pago = scanner.nextLine();
                    System.out.print("Ingrese su ID_Usuario: ");
                    int ID_Usuario = scanner.nextInt();
                    scanner.nextLine();
                    Vent newVent = new Vent(0, Fecha, Total, Metodo_Pago, ID_Usuario);
                    ventDAO.insertVent(newVent);
                    break;

                case 2:
                    ventDAO.getAllventa().forEach(vent ->
                            System.out.println("ID_Venta: " + vent.getID_Venta()
                                    + " ,Fecha: " + vent.getFecha()
                                    + ", Total: " + vent.getTotal()
                                    + ", Método de pago: " + vent.getMetodo_Pago()
                                    + ", ID_Usuario: " + vent.getID_Usuario())
                    );
                    break;

                case 3:
                    System.out.print("Ingrese el ID de la venta a actualizar: ");
                    int updateID_Venta = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese la nueva fecha (YYYY-MM-DD): ");
                    Date updateFecha = Date.valueOf(scanner.nextLine());
                    System.out.print("Ingrese el nuevo total: ");
                    BigDecimal newTotal = scanner.nextBigDecimal();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo método de pago: ");
                    String newMetodo_Pago = scanner.nextLine();
                    System.out.print("Ingrese el nuevo ID_Usuario: ");
                    int updateID_Usuario = scanner.nextInt();
                    scanner.nextLine();
                    ventDAO.updateVent(updateID_Venta, updateFecha, newTotal, newMetodo_Pago, updateID_Usuario);
                    break;

                case 4:
                    System.out.print("Ingrese el ID de la venta a eliminar: ");
                    int deleteID_Venta = scanner.nextInt();
                    scanner.nextLine();
                    ventDAO.deleteVent(deleteID_Venta);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (option != 5);
    }
}

