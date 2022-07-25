package producto;

import principal.Ticket;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProductoReventa extends Producto{
    private float precioCompra;

    public ProductoReventa(String detalle, int cantidad, float precioVenta, float precioCompra){
        super(detalle, cantidad, precioVenta);
        this.precioCompra = precioCompra;
    }
    public ProductoReventa(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "DE REVENTA " + '\n' + "precio de compra: " + precioCompra + '\n';
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }



}
