package producto;

public class Budin extends ElaboradoPorUnidad {
    private boolean celiaco;

    public Budin(String detalle, int cantidad, float precioVenta, boolean celiaco){
        super(detalle, cantidad, precioVenta);
        this.celiaco = celiaco;
    }
    public Budin(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + '\n' + "BUDIN " + '\n' + "celiaco: " + celiaco + "\n-------";
    }

    public boolean isCeliaco() {
        return celiaco;
    }

    public void setCeliaco(boolean celiaco) {
        this.celiaco = celiaco;
    }
}
