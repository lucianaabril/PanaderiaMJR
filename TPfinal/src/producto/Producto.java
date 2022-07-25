package producto;

import java.io.Serializable;

public abstract class Producto implements Serializable {
    private String detalle;
    private int cantidad;
    private float precioVenta;

    public Producto(String detalle, int cantidad, float precioVenta){
        this.detalle = detalle;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }
    public Producto(){}

    @Override
    public String toString() {
        return " PRODUCTO " + '\n' +
                "detalle: " + detalle + '\n' +
                "cantidad: " + cantidad + '\n' +
                "precio de venta: " + precioVenta + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != this.getClass()){
            return false;
        } else{
            return (this.detalle.equals(((Producto) o).detalle));
        }
    }


    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
