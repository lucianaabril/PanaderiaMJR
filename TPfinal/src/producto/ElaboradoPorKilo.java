package producto;

public abstract class ElaboradoPorKilo extends ProductoElaborado {
    public ElaboradoPorKilo(String detalle, int cantidad, float precioVenta){
        super(detalle, cantidad, precioVenta);
    }

    public ElaboradoPorKilo(){
        super();
    }
    @Override
    public String toString() {
        return super.toString() + "POR KILO " ;
    }
}
