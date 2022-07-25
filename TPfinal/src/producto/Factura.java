package producto;

public class Factura extends ElaboradoPorUnidad {
    private boolean rellena;

    public Factura(String detalle, int cantidad, float precioVenta, boolean rellena) {
        super(detalle, cantidad, precioVenta);
        this.rellena = rellena;
    }

    public Factura(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + '\n' + "FACTURA " + '\n' + "rellena: " + rellena + "\n-------";
    }

    public boolean isRellena() {
        return rellena;
    }

    public void setRellena(boolean rellena) {
        this.rellena = rellena;
    }
}
