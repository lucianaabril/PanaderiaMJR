package personal;

import principal.I_Pagar;

import java.io.Serializable;

public class Proveedor implements I_Pagar, Serializable {
    private String nombre;
    private float deuda;

    public Proveedor(String nombre, float deuda){
        this.nombre = nombre;
        this.deuda = deuda;
    }

    @Override
    public void pagar() {
        deuda = 0;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }else{
            return this.nombre.equals(((Proveedor) o).nombre);
        }
    }

    @Override
    public String toString() {
        return '\n' + "PROVEEDOR " + '\n' +
                "nombre: " + nombre + '\n' +
                "deuda: " + deuda +
                "\n-------";
    }


    public void aumentarDedua(float monto){
        deuda += monto;
    }

    public float getDeuda() {
        return deuda;
    }

    public void setDeuda(float deuda) {
        this.deuda = deuda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
