package producto;

public class Pan extends ElaboradoPorKilo {
    private boolean saborizado;

    public Pan(String detalle, int cantidad, float precioVenta, boolean saborizado){
        super(detalle, cantidad, precioVenta);
        this.saborizado = saborizado;
    }

    public Pan(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + '\n' + "PAN " + '\n' + "saborizado: " + saborizado + "\n-------";
    }

    public boolean isSaborizado() {
        return saborizado;
    }

    public void setSaborizado(boolean saborizado) {
        this.saborizado = saborizado;
    }
}
