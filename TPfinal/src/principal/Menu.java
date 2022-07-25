package principal;

import personal.Empleado;
import personal.Proveedor;
import producto.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner teclado;

    public void menuPrincipal(Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            String fuente;
            try {
                teclado = new Scanner(System.in);
                System.out.println("█████████MENÚ PRINCIPAL PANADERÍA MJR█████████");
                System.out.println("1. Ver menú empleados");
                System.out.println("2. Ver menú proveedores");
                System.out.println("3. Ver menú inventario");
                System.out.println("4. Ver menú tickets");
                System.out.println("5. Ver recaudación total");
                System.out.println("6. Terminar");
                int op;
                op = teclado.nextInt(); //excepción si ingresa otro tipo de dato
                switch (op) {
                    case 1: //menu empleados
                        menuEmpleados(sistema);
                        break;
                    case 2: //menu proveedores
                        menuProveedores(sistema);
                        break;
                    case 3: //menu inventario
                        menuInventario(sistema);
                        break;
                    case 4: // menu tickets
                        menuTickets(sistema);
                        break;
                    case 5: //muestra recaudación
                        System.out.println("La recaudación total es de $" + sistema.getRecaudacion());
                        break;
                    case 6: //json
                        System.out.println("[...]");
                        teclado.nextLine();
                        //se genera el json
                        System.out.println("[SE HA GENERADO UN ARCHIVO DE JSON PARA EMPLEADOS]");
                        ProcesamientoJSON.cargarEmpleadosJSON(sistema.getEmpleados());
                        fuente = ProcesamientoJSON.leer("empleadosJSON");
                        System.out.println(fuente);
                        System.out.println("[SE HA GENERADO UN ARCHIVO DE JSON PARA PROVEEDORES]");
                        ProcesamientoJSON.cargarProveedoresJSON(sistema.getProveedores());
                        fuente = ProcesamientoJSON.leer("proveedoresJSON");
                        System.out.println(fuente);
                        System.out.println("[SE HA GENERADO UN ARCHIVO DE JSON PARA INVENTARIO]");
                        ProcesamientoJSON.cargarIventarioJSON(sistema);
                        fuente = ProcesamientoJSON.leer("inventarioJSON");
                        System.out.println(fuente);
                        //se persisten los datos
                        teclado.nextLine();
                        sistema.cargar_datos();
                        System.out.println("[LOS DATOS HAN SIDO SUBIDOS AL ARCHIVO]");
                        System.out.println("[...]");
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                        teclado.nextLine();
                        menuPrincipal(sistema);
                        break;
                }
            } catch (InputMismatchException e) {
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nIngrese un número entero por favor. Presione Enter para ver el menù nuevamente");
                teclado.nextLine();
            }
        }while(lectura == false);
        teclado.close();
    }





    public void menuEmpleados (Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            try{
                teclado = new Scanner(System.in);
                System.out.println("█████████MENÚ ADMINISTRATIVO DE EMPLEADOS█████████");
                System.out.println("1. Ver empleados");
                System.out.println("2. Registrar pago de sueldos");
                System.out.println("3. Dar de alta/baja a empleado");
                System.out.println("4. Agregar empleado");
                System.out.println("5. Volver al menú principal");
                int op = teclado.nextInt();
                switch (op){
                    case 1: //menú de ver empleados
                        menuVerEmpleados(sistema);
                        break;
                    case 2: //menú de registro de pago de sueldos
                        menuPagoSueldos(sistema);
                        break;
                    case 3: //menú de dar de baja o alta empleado
                        menuAltaEmpleados(sistema);
                        break;
                    case 4: //agregar empleado
                        Main.ingresarEmpleado(sistema.getEmpleados());
                        break;
                    case 5:
                        menuPrincipal(sistema);
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                        teclado.nextLine();
                        menuEmpleados(sistema);
                        break;
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nIngrese un número entero por favor. Presione Enter para ver el menù nuevamente");
                teclado.nextLine();
            }
        }while(lectura == false);
        menuEmpleados(sistema);
        teclado.close();

    }



    public void menuAltaEmpleados(Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            try{
                teclado = new Scanner(System.in);
                System.out.println("█████████DAR DE ALTA/BAJA EMPLEADOS█████████");
                System.out.println("1. Dar de baja a un empleado");
                System.out.println("2. Dar de alta a un empleado");
                System.out.println("3. Volver al menú anterior");
                int op = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Ingrese DNI del empleado buscado");
                String dni = teclado.nextLine();
                Empleado buscado = sistema.buscarEmpleado(dni);
                if(buscado != null){
                    switch (op){
                        case 1:
                            if(buscado.isAlta()){
                                buscado.setAlta(false);
                            }else{
                                System.out.println("El empleado ya se encontraba dado de baja");
                            }
                            break;
                        case 2:
                            if(buscado.isAlta()){
                                System.out.println("El empleado ya se encontraba dado de alta");
                            }else{
                                buscado.setAlta(true);
                            }
                            break;
                        case 3:
                            menuEmpleados(sistema);
                            break;
                        default:
                            teclado.nextLine();
                            System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                            teclado.nextLine();
                            menuEmpleados(sistema);
                            break;
                    }
                }
                else{
                    System.out.println("El empleado no ha sido encontrado");
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nIngrese un número entero por favor. Presione Enter para ver el menù nuevamente");
                teclado.nextLine();
            }
        }while(lectura == false);

        menuEmpleados(sistema);
        teclado.close();

    }



    public void menuPagoSueldos(Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            try{
                teclado = new Scanner(System.in);
                System.out.println("█████████REGISTRAR PAGO DE SUELDOS█████████");
                System.out.println("1. Registrar pago a todos los empleados");
                System.out.println("2. Registrar pago a empleado según DNI");
                System.out.println("3. Volver al menú anterior");
                int op = teclado.nextInt();
                switch (op){
                    case 1:
                        sistema.pagarSueldos();
                        System.out.println("Se ha registrado el pago de sueldo de todos los empleados");
                        System.out.println(sistema.getEmpleados());
                        break;
                    case 2:
                        teclado.nextLine();
                        System.out.println("Ingrese DNI del empleado buscado");
                        String dni = teclado.nextLine();
                        Empleado buscado = sistema.buscarEmpleado(dni);
                        if(buscado != null){
                            if(buscado.isSueldoPago()){
                                System.out.println("Ya se le había pagado el sueldo al empleado buscado");
                            }else{
                                buscado.pagar();
                                System.out.println("Se le ha pagado al empleado");
                                System.out.println(buscado.toString());
                            }
                        }else{
                            System.out.println("El empleado no ha sido encontrado");
                        }
                        break;
                    case 3:
                        menuEmpleados(sistema);
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                        teclado.nextLine();
                        menuEmpleados(sistema);
                        break;
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nIngrese un número entero por favor. Presione Enter para ver el menù nuevamente");
                teclado.nextLine();
            }
        }while(lectura == false);

        menuEmpleados(sistema);
        teclado.close();

    }


    public void menuVerEmpleados(Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            try{
                teclado = new Scanner(System.in);
                System.out.println("█████████VER EMPLEADOS█████████");
                System.out.println("1. Ver todos los empleados");
                System.out.println("2. Ver empleados activos (de alta)");
                System.out.println("3. Ver empleados dados de baja");
                System.out.println("4. Ver vendedores");
                System.out.println("5. Ver cocineros");
                System.out.println("6. Ver empleado según DNI");
                System.out.println("7. Volver al menú anterior");
                int op = teclado.nextInt();
                switch (op){
                    case 1:
                        System.out.println(sistema.getEmpleados());
                        break;
                    case 2:
                        Main.mostrarEmpleadosDeAlta(sistema);
                        break;
                    case 3:
                        Main.mostrarEmpleadosDeBaja(sistema);
                        break;
                    case 4:
                        Main.mostrarVendedores(sistema);
                        break;
                    case 5:
                        Main.mostrarCocineros(sistema);
                        break;
                    case 6:
                        teclado.nextLine();
                        System.out.println("Ingrese DNI del empleado buscado");
                        String dni = teclado.nextLine();
                        Empleado buscado = sistema.buscarEmpleado(dni);
                        if(buscado != null){
                            System.out.println(buscado.toString());
                        }else{
                            System.out.println("El empleado no ha sido encontrado");
                        }
                        break;
                    case 7:
                        menuEmpleados(sistema);
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                        teclado.nextLine();
                        menuEmpleados(sistema);
                        break;
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nIngrese un número entero por favor. Presione Enter para ver el menù nuevamente");
                teclado.nextLine();
            }
        }while(lectura == false);

        menuVerEmpleados(sistema);
        teclado.close();

    }


    public void menuProveedores (Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            try{
                teclado = new Scanner(System.in);
                System.out.println("█████████MENÚ ADMINISTRATIVO DE PROVEEDORES█████████");
                System.out.println("1. Ver proveedores");
                System.out.println("2. Registrar pago de deuda");
                System.out.println("3. Volver al menú principal");
                int op = teclado.nextInt();
                switch (op){
                    case 1: //menú de ver proveedores
                        System.out.println(sistema.getProveedores());
                        break;
                    case 2: //menú de registro de pago de deudas
                        menuPagarProveedores(sistema);
                        break;
                    case 3:
                        menuPrincipal(sistema);
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                        teclado.nextLine();
                        menuEmpleados(sistema);
                        break;
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nIngrese un número entero por favor. Presione Enter para ver el menù nuevamente");
                teclado.nextLine();
            }
        }while(lectura == false);

        menuProveedores(sistema);
        teclado.close();

    }



    public void menuPagarProveedores(Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            try{
                teclado = new Scanner(System.in);
                System.out.println("█████████PAGAR DEUDAS CON PROVEEDORES█████████");
                System.out.println("1. Registrar pago de una deuda");
                System.out.println("2. Registrar pago de todas las deudas");
                System.out.println("3. Volver al menú anterior");
                int op = teclado.nextInt();
                switch (op){
                    case 1:
                        teclado.nextLine();
                        System.out.println("Ingrese el nombre del proveedor al que desea saldarle la deuda");
                        String nombre = teclado.nextLine();
                        Proveedor buscado = sistema.buscarProveedor(nombre);
                        if(buscado != null){
                            buscado.pagar();
                            System.out.println("Se ha pagado la deuda");
                            System.out.println(buscado.toString());
                        }else{
                            System.out.println("No se ha encontrado al proveedor '"+nombre+"'");
                        }
                        break;
                    case 2:
                        sistema.pagarTodosProveedores();
                        System.out.println("Se ha saldado la deuda con todos los proovedores");
                        System.out.println(sistema.getProveedores());
                        break;
                    case 3:
                        menuProveedores(sistema);
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                        teclado.nextLine();
                        menuEmpleados(sistema);
                        break;
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nIngrese un número entero por favor. Presione Enter para ver el menù nuevamente");
                teclado.nextLine();
            }
        }while(lectura == false);

        menuProveedores(sistema);
        teclado.close();
    }

    public void menuAgregarNuevoProducto(Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            try{
                teclado = new Scanner(System.in);
                System.out.println("Tipo de producto que desea ingresar");
                System.out.println("1. Producto de reventa");
                System.out.println("2. Pan");
                System.out.println("3. Budin");
                System.out.println("4. Factura");
                System.out.println("5. Galletita");
                System.out.println("6. Sanguche de miga");
                System.out.println("7. Torta");
                System.out.println("8. Volver al menú anterior");
                int op = teclado.nextInt();
                boolean presencia;
                int key;
                switch(op){
                    case 1:
                        ProductoReventa productoReventa = new ProductoReventa();
                        do {
                            teclado.nextLine();
                            System.out.println("Ingrese ID del producto");
                            key = teclado.nextInt();
                            presencia = sistema.getStock().getStock().containsKey(key);
                            if(presencia == true || key <= 0){
                                System.out.println("Ha ingresado un ID ya ocupado o negativo, intente de nuevo.");
                            }
                        }while(presencia == false || key <= 0);

                        System.out.println("Ingrese detalle:");
                        productoReventa.setDetalle(teclado.nextLine());
                        System.out.println("Ingrese cantidad:");
                        productoReventa.setCantidad(teclado.nextInt());
                        System.out.println("Ingrese precio de venta:");
                        productoReventa.setPrecioVenta(teclado.nextFloat());
                        System.out.println("Ingrese precio de compra");
                        productoReventa.setPrecioCompra(teclado.nextFloat());
                        sistema.getStock().agregar(key,productoReventa);
                        System.out.println("Se ha cargado el producto correctamente");
                        System.out.println(sistema.getStock().getStock().get(key).toString());
                        break;
                    case 2:
                        Pan pan = new Pan();
                        do {
                            teclado.nextLine();
                            System.out.println("Ingrese ID del producto");
                            key = teclado.nextInt();
                            presencia = sistema.getStock().getStock().containsKey(key);
                            if(presencia == true || key <= 0){
                                System.out.println("Ha ingresado un ID ya ocupado o negativo, intente de nuevo.");
                            }
                        }while(presencia == false || key <= 0);
                        System.out.println("Ingrese detalle:");
                        pan.setDetalle(teclado.nextLine());
                        System.out.println("Ingrese cantidad:");
                        pan.setCantidad(teclado.nextInt());
                        System.out.println("Ingrese precioVenta:");
                        pan.setPrecioVenta(teclado.nextFloat());
                        System.out.println("¿Es saborizado? true/false");
                        pan.setSaborizado(teclado.nextBoolean());
                        sistema.getStock().agregar(key,pan);
                        System.out.println("Se ha cargado el producto correctamente");
                        System.out.println(sistema.getStock().getStock().get(key).toString());
                        break;
                    case 3:
                        Budin budin = new Budin();
                        do {
                            teclado.nextLine();
                            System.out.println("Ingrese ID del producto");
                            key = teclado.nextInt();
                            presencia = sistema.getStock().getStock().containsKey(key);
                            if(presencia == true || key <= 0){
                                System.out.println("Ha ingresado un ID ya ocupado o negativo, intente de nuevo.");
                            }
                        }while(presencia == false || key <= 0);
                        System.out.println("Ingrese detalle:");
                        budin.setDetalle(teclado.nextLine());
                        System.out.println("Ingrese cantidad:");
                        budin.setCantidad(teclado.nextInt());
                        System.out.println("Ingrese precioVenta:");
                        budin.setPrecioVenta(teclado.nextFloat());
                        System.out.println("¿Es celíaco? true/false");
                        budin.setCeliaco(teclado.nextBoolean());
                        sistema.getStock().agregar(key,budin);
                        System.out.println("Se ha cargado el producto correctamente");
                        System.out.println(sistema.getStock().getStock().get(key).toString());
                        break;
                    case 4:
                        Factura factura = new Factura();
                        do {
                            teclado.nextLine();
                            System.out.println("Ingrese ID del producto");
                            key = teclado.nextInt();
                            presencia = sistema.getStock().getStock().containsKey(key);
                            if(presencia == true || key <= 0){
                                System.out.println("Ha ingresado un ID ya ocupado o negativo, intente de nuevo.");
                            }
                        }while(presencia == false || key <= 0);
                        System.out.println("Ingrese detalle:");
                        factura.setDetalle(teclado.nextLine());
                        System.out.println("Ingrese cantidad:");
                        factura.setCantidad(teclado.nextInt());
                        System.out.println("Ingrese precioVenta:");
                        factura.setPrecioVenta(teclado.nextFloat());
                        System.out.println("¿Es rellena? true/false");
                        factura.setRellena(teclado.nextBoolean());
                        sistema.getStock().agregar(key,factura);
                        System.out.println("Se ha cargado el producto correctamente");
                        System.out.println(sistema.getStock().getStock().get(key).toString());
                        break;
                    case 5:
                        Galletita galletita = new Galletita();
                        do {
                            teclado.nextLine();
                            System.out.println("Ingrese ID del producto");
                            key = teclado.nextInt();
                            presencia = sistema.getStock().getStock().containsKey(key);
                            if(presencia == true || key <= 0){
                                System.out.println("Ha ingresado un ID ya ocupado o negativo, intente de nuevo.");
                            }
                        }while(presencia == false || key <= 0);
                        System.out.println("Ingrese detalle:");
                        galletita.setDetalle(teclado.nextLine());
                        System.out.println("Ingrese cantidad:");
                        galletita.setCantidad(teclado.nextInt());
                        System.out.println("Ingrese precioVenta:");
                        galletita.setPrecioVenta(teclado.nextFloat());
                        System.out.println("Sabor dulce o salada. S/D");
                        galletita.setSabor(teclado.nextLine().charAt(0));
                        sistema.getStock().agregar(key,galletita);
                        System.out.println("Se ha cargado el producto correctamente");
                        System.out.println(sistema.getStock().getStock().get(key).toString());
                        break;
                    case 6:
                        SangucheMiga sangucheMiga = new SangucheMiga();
                        do {
                            teclado.nextLine();
                            System.out.println("Ingrese ID del producto");
                            key = teclado.nextInt();
                            presencia = sistema.getStock().getStock().containsKey(key);
                            if(presencia == true || key <= 0){
                                System.out.println("Ha ingresado un ID ya ocupado o negativo, intente de nuevo.");
                            }
                        }while(presencia == false || key <= 0);
                        System.out.println("Ingrese detalle:");
                        sangucheMiga.setDetalle(teclado.nextLine());
                        System.out.println("Ingrese cantidad:");
                        sangucheMiga.setCantidad(teclado.nextInt());
                        System.out.println("Ingrese precioVenta:");
                        sangucheMiga.setPrecioVenta(teclado.nextFloat());
                        System.out.println("Tipo de sanguche: Simple o Triple S/T");
                        sangucheMiga.setTipoSanguche(teclado.nextLine().charAt(0));
                        System.out.println("Tipo de pan: Blanco o Negro B/S");
                        sangucheMiga.setTipoPan(teclado.nextLine().charAt(0));
                        System.out.println("¿Es vegetariano?");
                        sangucheMiga.setVegetariano(teclado.nextBoolean());
                        sistema.getStock().agregar(key,sangucheMiga);
                        System.out.println("Se ha cargado el producto correctamente");
                        System.out.println(sistema.getStock().getStock().get(key).toString());
                        break;
                    case 7:
                        Torta torta = new Torta();
                        do {
                            teclado.nextLine();
                            System.out.println("Ingrese ID del producto");
                            key = teclado.nextInt();
                            presencia = sistema.getStock().getStock().containsKey(key);
                            if(presencia == true || key <= 0){
                                System.out.println("Ha ingresado un ID ya ocupado o negativo, intente de nuevo.");
                            }
                        }while(presencia == false || key <= 0);
                        System.out.println("Ingrese detalle:");
                        torta.setDetalle(teclado.nextLine());
                        System.out.println("Ingrese cantidad:");
                        torta.setCantidad(teclado.nextInt());
                        System.out.println("Ingrese precioVenta:");
                        torta.setPrecioVenta(teclado.nextFloat());
                        System.out.println("¿Es helada? true/false");
                        torta.setHelada(teclado.nextBoolean());
                        sistema.getStock().agregar(key,torta);
                        System.out.println("Se ha cargado el producto correctamente");
                        System.out.println(sistema.getStock().getStock().get(key).toString());
                        break;
                    case 8:
                        menuInventario(sistema);
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                        teclado.nextLine();
                        menuAgregarNuevoProducto(sistema);
                        break;
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nSe ha ingresado un tipo de dato incorrecto. Presione Enter para reiniciar el proceso de ingresar nuevo producto");
                teclado.nextLine();
            }

        }while(lectura == false);

        menuInventario(sistema);
        teclado.close();
    }

    public void menuInventario(Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            try{
                teclado = new Scanner(System.in);
                System.out.println("█████████MENÚ ADMINISTRATIVO DE INVENTARIO█████████");
                System.out.println("1. Ver inventario");
                System.out.println("2. Registrar compra de artículos");
                System.out.println("3. Agregar una variedad de un producto ya existente");
                System.out.println("4. Volver al menú principal");
                int op = teclado.nextInt();
                switch (op){
                    case 1: //ver inventario
                        System.out.println(sistema.getStock().getStock());
                        break;
                    case 2: //registro de compra de articulos
                        Main.hacerPedido(sistema);
                        break;
                    case 3:
                        menuAgregarNuevoProducto(sistema);
                        break;
                    case 4:
                        menuPrincipal(sistema);
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                        teclado.nextLine();
                        menuEmpleados(sistema);
                        break;
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nIngrese un número entero por favor. Presione Enter para ver el menù nuevamente");
                teclado.nextLine();
            }
        }while(lectura == false);

        menuInventario(sistema);;
        teclado.close();

    }

    public void menuTickets(Sistema sistema){
        boolean lectura;
        do{
            lectura = true;
            try{
                teclado = new Scanner(System.in);
                System.out.println("█████████MENÚ ADMINISTRATIVO DE TICKETS█████████");
                System.out.println("1. Ver tickets");
                System.out.println("2. Cargar ventas");
                System.out.println("3. Volver al menú principal");
                int op = teclado.nextInt();
                switch (op){
                    case 1: //menú de ver tickets
                        System.out.println(sistema.getTickets());
                        break;
                    case 2:
                        sistema.administrarTickets();
                        break;
                    case 3:
                        menuPrincipal(sistema);
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("\n----\nPor favor ingrese una opción válida. Presione Enter para ver el menú nuevamente");
                        teclado.nextLine();
                        menuEmpleados(sistema);
                        break;
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("\n----\nIngrese un número entero por favor. Presione Enter para ver el menù nuevamente");
                teclado.nextLine();
            }
        }while(lectura == false);

        menuTickets(sistema);
        teclado.close();
    }
}
