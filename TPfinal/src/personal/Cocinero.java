package personal;

public class Cocinero extends Empleado {
    private int horasTrabajadas;
    private float sueldoXhora;

    public Cocinero(String nombre, String apellido, String dni, float salario, boolean sueldoPago, boolean alta,int horasTrabajadas, float sueldoXhora){
        super(nombre, apellido, dni, salario, sueldoPago, alta);
        this.horasTrabajadas = horasTrabajadas;
        this.sueldoXhora = sueldoXhora;
    }

    @Override
    public void pagar() {
        setSueldoPago(true);
    }

    @Override
    public float calcularSueldo() {
        float sueldo;
        sueldo = horasTrabajadas * sueldoXhora;
        return sueldo;
    }

    @Override
    public String toString() {
        return super.toString() + "COCINERO " + '\n' +
                "horas trabajadas: " + horasTrabajadas + '\n' +
                "sueldo por hora: " + sueldoXhora +
                "\n-------";
    }

    public float getSueldoXhora() {
        return sueldoXhora;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public void setSueldoXhora(float sueldoXhora) {
        this.sueldoXhora = sueldoXhora;
    }
}
