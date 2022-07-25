package principal;
import producto.Producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Ticket implements Serializable {
    private String dni;
    private float montoTotal;
    private HashMap<Integer, Producto> detalle;
    private char validez;

    public Ticket(String dni, float montoTotal, HashMap<Integer,Producto> detalle, char validez){
        this.dni = dni;
        this.montoTotal = montoTotal;
        this.detalle = detalle;
        this.validez = validez;
    }

    public HashMap<Integer, Producto> getDetalle() {
        return detalle;
    }

    public String getDni() {
        return dni;
    }

    public float getMontoTotal() { //controlador
        return montoTotal;
    }

    public char getValidez() {
        return validez;
    }

    public boolean esValido(){
        if(validez == 'v'){
            return true;
        }else{return false;}
    }

    public void hacerTicketNoValido() {
        this.validez = 'n';
    }

    public void hacerTicketValido() {
        this.validez = 'v';
    }

    @Override
    public String toString() {
        return "TICKET " + '\n' +
                "dni del vendedor: " + dni + '\n' +
                "monto total: " + montoTotal + '\n' +
                "detalle: " + detalle + '\n' +
                "validez: " + validez + '\n';
    }
}
