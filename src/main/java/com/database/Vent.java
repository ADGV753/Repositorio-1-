package com.database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.math.BigDecimal;

public class Vent{
    private int ID_Venta;
    private Date Fecha;
    private BigDecimal Total;
    private String Metodo_Pago;
    private int ID_Usuario;
    //constructor vacio

    public Vent() {
    }

    public Vent(int ID_Venta, Date fecha, BigDecimal total, String metodo_Pago, int ID_Usuario) {
        this.ID_Venta = ID_Venta;
        Fecha = fecha;
        Total = total;
        Metodo_Pago = metodo_Pago;
        this.ID_Usuario = ID_Usuario;
    }

    public int getID_Venta() {
        return ID_Venta;
    }

    public void setID_Venta(int ID_Venta) {
        this.ID_Venta = ID_Venta;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    public String getMetodo_Pago() {
        return Metodo_Pago;
    }

    public void setMetodo_Pago(String metodo_Pago) {
        Metodo_Pago = metodo_Pago;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    @Override
    public String toString() {
        return "Vent{" +
                "ID_Venta=" + ID_Venta +
                ", Fecha=" + Fecha +
                ", Total=" + Total +
                ", Metodo_Pago='" + Metodo_Pago + '\'' +
                ", ID_Usuario=" + ID_Usuario +
                '}';
    }
}