package personal;

public class Vendedor extends Empleado {
    private float sueldoBase;
    private int ventasRealizadas;
    private float comisionXventa;

    public Vendedor(String nombre, String apellido, String dni, float salario, boolean sueldoPago, boolean alta, float sueldoBase, int ventasRealizadas, float comisionXventa){
        super(nombre, apellido, dni, salario, sueldoPago, alta);
        this.sueldoBase = sueldoBase;
        this.ventasRealizadas = ventasRealizadas;
        this.comisionXventa = comisionXventa;
    }

    @Override
    public void pagar() {
        setSueldoPago(true);
    }

    @Override
    public float calcularSueldo() {
        float sueldo;
        sueldo = sueldoBase + (ventasRealizadas * comisionXventa);
        return sueldo;
    }

    @Override
    public String toString() {
        return super.toString() + "VENDEDOR " +
                "sueldo base: " + sueldoBase + '\n' +
                "ventas realizadas: " + ventasRealizadas + '\n' +
                "comisionXventa: " + comisionXventa +
                "\n-------";
    }

    public float getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(float sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public int getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void setVentasRealizadas(int ventasRealizadas) {
        this.ventasRealizadas += ventasRealizadas;
    }

    public float getComisionXventa() {
        return comisionXventa;
    }

    public void setComisionXventa(float comisionXventa) {
        this.comisionXventa = comisionXventa;
    }
}
