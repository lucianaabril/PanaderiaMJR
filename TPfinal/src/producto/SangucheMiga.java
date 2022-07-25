package producto;

public class SangucheMiga extends ElaboradoPorUnidad {
    private char tipoSanguche; //S o T
    private char tipoPan; //B o S
    private boolean vegetariano;

    public SangucheMiga(String detalle, int cantidad, float precioVenta, char tipoSanguche, char tipoPan, boolean vegetariano){
        super(detalle, cantidad, precioVenta);
        this.tipoSanguche = tipoSanguche;
        this.tipoPan = tipoPan;
        this.vegetariano = vegetariano;
    }
    public SangucheMiga(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "SANGUCHE DE MIGA "  + '\n' +
                "tipo de sanguche: " + tipoSanguche + '\n' +
                "tipo de pan: " + tipoPan + '\n' +
                "vegetariano: " + vegetariano + "\n-------";
    }

    public boolean isVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }

    public char getTipoPan() {
        return tipoPan;
    }

    public void setTipoPan(char tipoPan) {
        this.tipoPan = tipoPan;
    }

    public char getTipoSanguche() {
        return tipoSanguche;
    }

    public void setTipoSanguche(char tipoSanguche) {
        this.tipoSanguche = tipoSanguche;
    }
}
