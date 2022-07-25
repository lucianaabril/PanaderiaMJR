package personal;

import principal.I_Pagar;

import java.io.Serializable;

public abstract class Empleado implements I_Pagar, Serializable {
    private String nombre;
    private String apellido;
    private String dni;
    private float deudaSalario;
    private boolean sueldoPago;
    private boolean alta;

    public Empleado(String nombre, String apellido, String dni, float deudaSalario, boolean sueldoPago, boolean alta){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.deudaSalario = deudaSalario;
        this.sueldoPago = sueldoPago;
        this.alta = alta;
    }

    @Override
    public void pagar(){
        setSueldoPago(true);
        deudaSalario = 0;
    }

    public abstract float calcularSueldo();

    @Override
    public boolean equals(Object o) {
        if(o == null || this.getClass() != o.getClass()) {
            return false;
        }else{
            return ((Empleado) o).dni == this.dni;
        }
    }

    @Override
    public String toString() {
        return "EMPLEADO " +
                "nombre: " + nombre + '\n' +
                "apellido: " + apellido + '\n' +
                "dni: " + dni + '\n' +
                "deuda salario: " + deudaSalario + '\n' +
                "sueldo pago: " + sueldoPago + '\n' +
                "alta: " + alta + '\n';
    }

    public void aumentarDeuda(float deuda){
        deudaSalario += deuda;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDeudaSalario(float salario) {
        this.deudaSalario = salario;
    }

    public void setSueldoPago(boolean sueldoPago) {
        this.sueldoPago = sueldoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSalario() {
        return deudaSalario;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public boolean isAlta() {
        return alta;
    }

    public boolean isSueldoPago() {
        return sueldoPago;
    }


}
