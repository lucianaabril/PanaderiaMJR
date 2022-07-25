package producto;

public class Galletita extends ElaboradoPorKilo {
    private char sabor; //S o D

    public Galletita(String detalle, int cantidad, float precioVenta, char sabor){
        super(detalle, cantidad, precioVenta);
        this.sabor = sabor;
    }
    public Galletita(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + '\n' + "GALLETITA " + '\n' + "sabor: " + sabor + "\n-------";
    }

    public char getSabor() {
        return sabor;
    }

    public void setSabor(char sabor) {
        this.sabor = sabor;
    }
}
