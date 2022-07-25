package producto;

public abstract class ElaboradoPorUnidad extends ProductoElaborado {
    public ElaboradoPorUnidad(String detalle, int cantidad, float precioVenta){
        super(detalle, cantidad, precioVenta);
    }

    public ElaboradoPorUnidad(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "POR UNIDAD ";
    }
}
