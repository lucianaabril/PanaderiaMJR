package producto;

public abstract class ProductoElaborado extends Producto{
    public ProductoElaborado(String detalle, int cantidad, float precioVenta){
        super(detalle, cantidad, precioVenta);
    }
    public ProductoElaborado(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "ELABORADO ";
    }
}
