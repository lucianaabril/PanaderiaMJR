package principal;

import excepciones.TicketInvalidoException;
import personal.Cocinero;
import personal.Empleado;
import personal.Proveedor;
import personal.Vendedor;
import producto.*;

import java.util.*;

public class Main {
    static Scanner teclado;
    static float utilidad;
    public static void main(String[] args) {
        utilidad = 20;
        Scanner teclado = new Scanner(System.in);

        Sistema sistema = new Sistema();
        sistema.descargar_datos();
        Menu menu = new Menu();
        menu.menuPrincipal(sistema);





        teclado.close();
    }

    //EMPLEADOS
    public static void ingresarEmpleado(HashSet<Empleado> empleados){
        teclado = new Scanner(System.in);
        boolean lectura;
        System.out.println("ESTÁ A PUNTO DE INGRESAR UN NUEVO EMPLEADO");
        do{
            lectura = true;
            try{
                System.out.println("Ingrese nombre del empleado:");
                String nombre = teclado.nextLine();

                System.out.println("Ingrese apellido:");
                String apellido = teclado.nextLine();

                System.out.println("Ingrese DNI:");
                String dni = teclado.nextLine();

                int salario = 0;

                boolean sueldoPago = false;

                System.out.println("¿El empleado se encuentra dado de alta? true/false");
                boolean alta = teclado.nextBoolean();

                teclado.nextLine();
                System.out.println("¿El empleado es cocinero o vendedor?");
                String tipo = teclado.nextLine();

                switch (tipo) {
                    case "vendedor":
                        System.out.println("Ingrese el sueldo base:");
                        float sueldoBase = teclado.nextFloat();
                        int ventas = 0;
                        System.out.println("Ingrese la comision por venta:");
                        float comision = teclado.nextFloat();
                        Vendedor vendedor = new Vendedor(nombre, apellido, dni, salario, sueldoPago, alta, sueldoBase, ventas, comision);
                        empleados.add(vendedor);
                        break;

                    case "cocinero":
                        System.out.println("Ingrese las horas trabajadas:");
                        int horas = teclado.nextInt();
                        System.out.println("Ingrese sueldo por hora:");
                        float sueldoXhora = teclado.nextFloat();
                        Cocinero cocinero = new Cocinero(nombre, apellido, dni, salario, sueldoPago, alta, horas, sueldoXhora);
                        empleados.add(cocinero);
                        break;
                    }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nSe ha ingresado un tipo de dato incorrecto. Presione Enter para reiniciar el proceso de ingresar nuevo empleado");
                teclado.nextLine();
            }
        }while(lectura == false);
        System.out.println("Se ha ingresado el nuevo empleado exitosamente");
    }
    public static void mostrarVendedores(Sistema sistema){
        Iterator<Empleado> iterator = sistema.getEmpleados().iterator();
        while(iterator.hasNext()){
            Empleado aux = iterator.next();
            if(aux instanceof Vendedor){
                System.out.println(aux.toString());
            }
        }
    }

    public static void mostrarCocineros(Sistema sistema){
        Iterator<Empleado> iterator = sistema.getEmpleados().iterator();
        while(iterator.hasNext()){
            Empleado aux = iterator.next();
            if(aux instanceof Cocinero){
                System.out.println(aux.toString());
            }
        }
    }

    public static void mostrarEmpleadosDeAlta(Sistema sistema){
        Iterator<Empleado> iterator = sistema.getEmpleados().iterator();
        while(iterator.hasNext()){
            Empleado aux = iterator.next();
            if(aux.isAlta()){
                System.out.println(aux.toString());
            }
        }
    }

    public static void mostrarEmpleadosDeBaja(Sistema sistema){
        Iterator<Empleado> iterator = sistema.getEmpleados().iterator();
        while(iterator.hasNext()){
            Empleado aux = iterator.next();
            if(!aux.isAlta()){
                System.out.println(aux.toString());
            }
        }
    }

    //PEDIDO
    public static void hacerPedido(Sistema sistema){
        teclado = new Scanner(System.in);
        String op, op2;
        boolean clave, clave2;

        do {
            HashMap<Integer,Integer> pedido = new HashMap<>();
            Proveedor proveedor;
            do {
                System.out.println("Ingrese el nombre del proveedor: ");
                String nombre = teclado.nextLine();
                proveedor = sistema.buscarProveedor(nombre);

                if (proveedor!=null) {
                    do {
                        do {
                            System.out.println("Ingrese el ID del producto: ");
                            int key = teclado.nextInt();
                            clave2 = true;
                            Producto producto = sistema.getStock().getStock().get(key);

                            if (producto instanceof ProductoReventa) {
                                System.out.println("Ingrese la cantidad: ");
                                int cant = teclado.nextInt();
                                pedido.put(key, cant);
                                clave = true;
                            } else {
                                System.out.println("El ID del producto es invalido. ");
                                clave = false;
                            }
                        }while (!clave);

                        teclado.nextLine();
                        System.out.println("Desea ingresar otro producto al pedido? y/n , puede que deba ingresar dos veces su opcion ");
                        op2 = teclado.nextLine();

                    } while (op2.equals("y"));
                }else {
                    System.out.println("El proveedor es invalido. ");
                    clave2 = false;
                }

            } while (!clave2);

            sistema.cargarPedido(pedido);
            sistema.calcularDeuda(pedido,proveedor);

            teclado.nextLine();
            System.out.println("Desea ingresar otro pedido? y/n ");
            op = teclado.nextLine();

        }while(op.equals("y"));


    }



    //CARGAR LAS COLECCIONES PARA PERSISTIR
    public static ArrayList<Ticket> cargarTickets(){
        ArrayList<Ticket> tickets = new ArrayList<>();
        teclado = new Scanner(System.in);
        String op, op2;
        do {
            System.out.println("Ingrese el DNI del vendedor: ");
            String dni = teclado.nextLine();
            System.out.println("Ingrese el valor total de la venta: ");
            float total = teclado.nextFloat();
            char validez = 'v';
            HashMap<Integer,Producto> hashmap_detalle = new HashMap<>();
            do{
                System.out.println("Ingrese el ID del producto: ");
                int key = teclado.nextInt();

                teclado.nextLine();
                System.out.println("Ingrese el detalle del producto: ");
                String detalle = teclado.nextLine();
                System.out.println("Ingrese la cantidad: ");
                int cantidad = teclado.nextInt();
                System.out.println("Ingrese el precio de venta: ");
                float precioventa = teclado.nextFloat();
                teclado.nextLine();

                if(key<=21) {
                    System.out.println("PRODUCTO ELABORADO ");
                    if(key<=4){
                        System.out.println("FACTURA ");
                        System.out.println("Es rellena? true/false ");
                        boolean rellena = teclado.nextBoolean();
                        Factura factura = new Factura(detalle, cantidad, precioventa, rellena);
                        hashmap_detalle.put(key, factura);
                    }
                    else if(key<=8){
                        System.out.println("SANGUCHE ");
                        System.out.println("Ingrese el tipo de sanguche: S/T ");
                        char tipoSanguche = teclado.nextLine().charAt(0);
                        System.out.println("Ingrese el tipo de pan: B/S ");
                        char tipoPan = teclado.nextLine().charAt(0);
                        System.out.println("Es vegetariano? true/false ");
                        boolean vegetariano = teclado.nextBoolean();
                        SangucheMiga sanguchemiga = new SangucheMiga(detalle, cantidad, precioventa, tipoSanguche, tipoPan, vegetariano);
                        hashmap_detalle.put(key, sanguchemiga);
                    }
                    else if(key<=11){
                        System.out.println("TORTA ");
                        System.out.println("Es helada? true/false ");
                        boolean helada = teclado.nextBoolean();
                        Torta torta = new Torta(detalle, cantidad, precioventa, helada);
                        hashmap_detalle.put(key, torta);
                    }
                    else if(key<=14){
                        System.out.println("BUDUIN ");
                        System.out.println("Es celiaco? true/false ");
                        boolean celiaco = teclado.nextBoolean();
                        Budin budin = new Budin(detalle, cantidad, precioventa, celiaco);
                        hashmap_detalle.put(key, budin);
                    }
                    else if(key<=18){
                        System.out.println("PAN");
                        System.out.println("Es saborizado? true/false ");
                        boolean saborizado = teclado.nextBoolean();
                        Pan pan = new Pan(detalle, cantidad, precioventa, saborizado);
                        hashmap_detalle.put(key, pan);
                    }
                    else if(key<=21){
                        System.out.println("GALLETITA ");
                        System.out.println("Es dulce o salada? D/S ");
                        char sabor = teclado.nextLine().charAt(0);
                        Galletita galletita = new Galletita(detalle, cantidad, precioventa, sabor);
                        hashmap_detalle.put(key, galletita);
                    }
                }
                else{
                    System.out.println("PRODUCTO DE REVENTA ");
                    System.out.println("Ingrese el precio de compra: ");
                    float preciocompra = teclado.nextFloat();
                    if(key<=23){
                        System.out.println("GASEOSA ");
                        ProductoReventa gaseosa = new ProductoReventa(detalle, cantidad, precioventa, preciocompra);
                        hashmap_detalle.put(key, gaseosa);
                    }
                    else if(key==24){
                        System.out.println("AGUA MINERAL ");
                        ProductoReventa aguaM = new ProductoReventa(detalle, cantidad, precioventa, preciocompra);
                        hashmap_detalle.put(key, aguaM);
                    }
                    else if (key<=26){
                        System.out.println("AGUA SABORIZADA ");
                        ProductoReventa aguaS = new ProductoReventa(detalle, cantidad, precioventa, preciocompra);
                        hashmap_detalle.put(key, aguaS);
                    }
                    else if(key<=28){
                        System.out.println("JUGO ");
                        ProductoReventa jugo = new ProductoReventa(detalle, cantidad, precioventa, preciocompra);
                        hashmap_detalle.put(key, jugo);
                    }
                }
                teclado.nextLine();
                System.out.println("Desea cargar otro producto? ");
                op2 = teclado.nextLine();
            }while(op2.equals("y"));
            Ticket nuevo = new Ticket(dni,total,hashmap_detalle,validez);
            tickets.add(nuevo);
            System.out.println("Desea cargar otro ticket? ");
            op = teclado.nextLine();
        } while(op.equals("y"));
        return tickets;
    }

    public static HashSet<Proveedor> cargarProveedores(){
        HashSet<Proveedor> proveedores = new HashSet<>();
        teclado = new Scanner(System.in);
        String op;
        do{
            System.out.println("Ingrese el nombre del proveedor: ");
            String nombre = teclado.nextLine();
            System.out.println("Ingrese la deuda que posee con el mismo: ");
            float deuda = teclado.nextFloat();
            proveedores.add(new Proveedor(nombre,deuda));
            teclado.nextLine();
            System.out.println("Desea seguir ingresando proveedores? y/n");
            op = teclado.nextLine();
        }while(op.equals("y"));
        return proveedores;
    }

    public static HashSet<Empleado> cargarEmpleados(){
        HashSet<Empleado> empleados = new HashSet<>();
        teclado = new Scanner(System.in);
        String op;
        do{
            System.out.println("Ingrese nombre del empleado:");
            String nombre = teclado.nextLine();

            System.out.println("Ingrese apellido:");
            String apellido = teclado.nextLine();

            System.out.println("Ingrese DNI:");
            String dni = teclado.nextLine();

            int salario = 0;

            boolean sueldoPago = false;

            System.out.println("¿El empleado se encuentra dado de alta? true/false");
            boolean alta = teclado.nextBoolean();

            teclado.nextLine();
            System.out.println("¿El empleado es cocinero o vendedor?");
            String tipo = teclado.nextLine();

            switch (tipo) {
                case "vendedor" -> {
                    System.out.println("Ingrese el sueldo base:");
                    float sueldoBase = teclado.nextFloat();
                    System.out.println("Ingrese la cant de ventas realizadas:");
                    int ventas = teclado.nextInt();
                    System.out.println("Ingrese la comision por venta:");
                    float comision = teclado.nextFloat();
                    Vendedor vendedor = new Vendedor(nombre, apellido, dni, salario, sueldoPago, alta, sueldoBase, ventas, comision);
                    empleados.add(vendedor);
                }
                case "cocinero" -> {
                    System.out.println("Ingrese las horas trabajadas:");
                    int horas = teclado.nextInt();
                    System.out.println("Ingrese sueldo por hora:");
                    float sueldoXhora = teclado.nextFloat();
                    Cocinero cocinero = new Cocinero(nombre, apellido, dni, salario, sueldoPago, alta, horas, sueldoXhora);
                    empleados.add(cocinero);
                }
            }
            teclado.nextLine();
            System.out.println("\n  DESEA SEGUIR INGRESANDO EMPLEADOS? y/n");
            op = teclado.nextLine();
        }while(op.equals("y"));
        return empleados;
    }

    public static  Inventario<Producto> cargarIventario(){
        HashMap<Integer,Producto> hashmap_inventario = new HashMap<>();
        int cantidadTotal = 0;
        teclado = new Scanner(System.in);
        String op;
         do {
            System.out.println("Ingrese el ID del producto: ");
            int key = teclado.nextInt();

            teclado.nextLine();
            System.out.println("Ingrese el detalle del producto: ");
            String detalle = teclado.nextLine();
            System.out.println("Ingrese la cantidad: ");
            int cantidad = teclado.nextInt();
            cantidadTotal += cantidad;
            System.out.println("Ingrese el precio de venta: ");
            float precioventa = teclado.nextFloat();
            teclado.nextLine();

            if(key<=21) {
                System.out.println("PRODUCTO ELABORADO ");
                 if(key<=4){
                     System.out.println("FACTURA ");
                     System.out.println("Es rellena? true/false ");
                     boolean rellena = teclado.nextBoolean();
                     Factura factura = new Factura(detalle, cantidad, precioventa, rellena);
                     hashmap_inventario.put(key, factura);
                 }
                 else if(key<=8){
                     System.out.println("SANGUCHE ");
                     System.out.println("Ingrese el tipo de sanguche: S/T ");
                     char tipoSanguche = teclado.nextLine().charAt(0);
                     System.out.println("Ingrese el tipo de pan: B/S ");
                     char tipoPan = teclado.nextLine().charAt(0);
                     System.out.println("Es vegetariano? true/false ");
                     boolean vegetariano = teclado.nextBoolean();
                     SangucheMiga sanguchemiga = new SangucheMiga(detalle, cantidad, precioventa, tipoSanguche, tipoPan, vegetariano);
                     hashmap_inventario.put(key, sanguchemiga);
                 }
                 else if(key<=11){
                     System.out.println("TORTA ");
                     System.out.println("Es helada? true/false ");
                     boolean helada = teclado.nextBoolean();
                     Torta torta = new Torta(detalle, cantidad, precioventa, helada);
                     hashmap_inventario.put(key, torta);
                 }
                 else if(key<=14){
                     System.out.println("BUDUIN ");
                     System.out.println("Es celiaco? true/false ");
                     boolean celiaco = teclado.nextBoolean();
                     Budin budin = new Budin(detalle, cantidad, precioventa, celiaco);
                     hashmap_inventario.put(key, budin);
                 }
                 else if(key<=18){
                     System.out.println("PAN");
                     System.out.println("Es saborizado? true/false ");
                     boolean saborizado = teclado.nextBoolean();
                     Pan pan = new Pan(detalle, cantidad, precioventa, saborizado);
                     hashmap_inventario.put(key, pan);
                 }
                 else if(key<=21){
                     System.out.println("GALLETITA ");
                     System.out.println("Es dulce o salada? D/S ");
                     char sabor = teclado.nextLine().charAt(0);
                     Galletita galletita = new Galletita(detalle, cantidad, precioventa, sabor);
                     hashmap_inventario.put(key, galletita);
                 }
            }
            else{
                 System.out.println("PRODUCTO DE REVENTA ");
                 System.out.println("Ingrese el precio de compra: ");
                 float preciocompra = teclado.nextFloat();
                if(key<=23){
                    System.out.println("GASEOSA ");
                    ProductoReventa gaseosa = new ProductoReventa(detalle, cantidad, precioventa, preciocompra);
                    hashmap_inventario.put(key, gaseosa);
                }
                else if(key==24){
                    System.out.println("AGUA MINERAL ");
                    ProductoReventa aguaM = new ProductoReventa(detalle, cantidad, precioventa, preciocompra);
                    hashmap_inventario.put(key, aguaM);
                }
                else if(key<=26){
                    System.out.println("AGUA SABORIZADA ");
                    ProductoReventa aguaS = new ProductoReventa(detalle, cantidad, precioventa, preciocompra);
                    hashmap_inventario.put(key, aguaS);
                }
                else if(key<=28){
                    System.out.println("JUGO ");
                    ProductoReventa jugo = new ProductoReventa(detalle, cantidad, precioventa, preciocompra);
                    hashmap_inventario.put(key, jugo);
                }
            }
            teclado.nextLine();
            System.out.println("Desea cargar otro producto al inventario? y/n ");
            op = teclado.nextLine();
        }while(op.equals("y"));

         Inventario<Producto> aux = new Inventario<>();
         aux.setStock(hashmap_inventario);
         aux.setCantMercaderia(cantidadTotal);
         return aux;
    }
}
