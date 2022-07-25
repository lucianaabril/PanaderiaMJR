package producto;

public class Torta extends ElaboradoPorUnidad {
    private boolean helada;

    public Torta(String detalle, int cantidad, float precioVenta, boolean helada){
        super(detalle, cantidad, precioVenta);
        this.helada = helada;
    }
    public Torta(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + '\n' + "TORTA " + '\n' + "helada: " + helada + "\n-------";
    }

    public boolean isHelada() {
        return helada;
    }

    public void setHelada(boolean helada) {
        this.helada = helada;
    }
}
