package principal;

import excepciones.TicketInvalidoException;
import personal.Empleado;
import personal.Proveedor;
import personal.Vendedor;
import producto.Producto;
import producto.ProductoReventa;

import java.util.*;

public class Sistema {
    private Inventario<Producto> stock;
    private HashSet<Empleado> empleados;
    private HashSet<Proveedor> proveedores;
    private ArrayList<Ticket> tickets;
    private float recaudacion;

    public Sistema(){
        Inventario<Producto> stock = new Inventario<>();
        this.empleados = new HashSet<>();
        this.proveedores = new HashSet<>();
        this.tickets = new ArrayList<>();
        this.recaudacion = 0;
    }

    @Override
    public String toString() {
        return "Sistema{" +
                "stock=" + stock +
                ", empleados=" + empleados +
                ", proveedores=" + proveedores +
                ", tickets=" + tickets +
                ", recaudacion=" + recaudacion +
                '}';
    }

    public void administrarTickets() {
        for (int i = 0; i < tickets.size(); i++) {
            validarTicket(i);
        }
    }

    public void validarTicket(int i) {
        try {
            float total = 0;
            Empleado aux = buscarEmpleado(tickets.get(i).getDni());
            if (aux instanceof Vendedor) {
                Iterator<Map.Entry<Integer, Producto>> iterator = tickets.get(i).getDetalle().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Producto> fila = iterator.next();
                    total += (fila.getValue().getCantidad()) * (fila.getValue().getPrecioVenta());
                    int cant = stock.getStock().get(fila.getKey()).getCantidad();
                    if (fila.getValue().getCantidad() > cant) {
                        tickets.get(i).hacerTicketNoValido();
                        throw new TicketInvalidoException(2);
                    }
                }
                recaudacion += total;
                ((Vendedor) aux).setVentasRealizadas(1);
                stock.descontarProductos(tickets.get(i).getDetalle());
            } else {
                tickets.get(i).hacerTicketNoValido();
                throw new TicketInvalidoException(1);
            }
        } catch(TicketInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

    public void cargarPedido(HashMap<Integer,Integer> pedido){
        Iterator<Map.Entry<Integer,Integer>> iterator = pedido.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<Integer,Integer> fila = iterator.next();
            stock.sumar(fila.getKey(), fila.getValue());
        }
    }

    public void calcularDeuda(HashMap<Integer,Integer> pedido, Proveedor proveedor){
        float total = 0;
        Iterator<Map.Entry<Integer,Integer>> iterator = pedido.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,Integer> fila = iterator.next();
            ProductoReventa aux = (ProductoReventa) stock.getStock().get(fila.getKey());
            total += aux.getPrecioCompra()*fila.getValue();
        }
        proveedor.aumentarDedua(total);
    }

    public Proveedor buscarProveedor(String nombre){
        Iterator<Proveedor> iterator = proveedores.iterator();
        Proveedor buscado = null;
        while(iterator.hasNext()){
            Proveedor aux = iterator.next();
            if(aux.getNombre().equals(nombre)){
                buscado = aux;
            }
        }
        return buscado;
    }

    public void pagarTodosProveedores(){
        Iterator<Proveedor> iterator = proveedores.iterator();
        while(iterator.hasNext()){
            Proveedor aux = iterator.next();
            if(aux.getDeuda() != 0){
                aux.pagar();
            }
        }
    }

    public Empleado buscarEmpleado(String dni){
        Iterator<Empleado> iterator = empleados.iterator();
        Empleado buscado = null;
        while(iterator.hasNext()){
            Empleado aux = iterator.next();
            if(aux.getDni().equals(dni)){
                buscado = aux;
            }
        }
        return buscado;
    }

    public void pagarSueldos(){
        Iterator<Empleado> iterator = empleados.iterator();
        while(iterator.hasNext()){
            Empleado aux = iterator.next();
            if(!aux.isSueldoPago()){
                aux.pagar();
            }
        }
    }

    public void descargar_datos(){
        Controlador controlador = new Controlador();
        stock = controlador.desp_inventario("inventario.bin");
        empleados = controlador.desp_empleados("empleados.bin");
        proveedores = controlador.desp_proveedores("proveedores.bin");
        tickets = controlador.desp_tickets("ventas.bin");
    }

    public void cargar_datos(){
        Controlador controlador = new Controlador();
        controlador.persistir_inventario("inventario.bin",stock);
        controlador.persistir_empleados("empleados.bin",empleados);
        controlador.persistir_proveedores("proveedores.bin",proveedores);
        controlador.persistir_tickets("ventas.bin",tickets);
    }

    public float getRecaudacion() {
        return recaudacion;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public HashSet<Proveedor> getProveedores() {
        return proveedores;
    }

    public HashSet<Empleado> getEmpleados() {
        return empleados;
    }

    public Inventario<Producto> getStock() {
        return stock;
    }

    public void setStock(Inventario<Producto> stock) {
        this.stock = stock;
    }

    public void setRecaudacion(float recaudacion) {
        this.recaudacion = recaudacion;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setProveedores(HashSet<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public void setEmpleados(HashSet<Empleado> empleados) {
        this.empleados = empleados;
    }

}
