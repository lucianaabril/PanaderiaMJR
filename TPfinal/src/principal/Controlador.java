package principal;

import personal.Empleado;
import personal.Proveedor;
import producto.Producto;

import java.io.*;
import java.util.*;

public class Controlador {

    public Controlador() {
    }

    public HashSet<Empleado> desp_empleados(String archivo){ //del archivo al hashset
        HashSet<Empleado> hashset_aux = new HashSet<>();
        try {
            FileInputStream fileinputstream = new FileInputStream(archivo);
            ObjectInputStream objinputstream = new ObjectInputStream(fileinputstream);

            int lectura = 1;
            while(lectura == 1){ //recorre el archivo
                Empleado aux = (Empleado)objinputstream.readObject();
                hashset_aux.add(aux); //guarda en el hashmap
            }
            fileinputstream.close();
        }
        catch (EOFException e){
            System.out.println("Fin del archivo. \n");
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado. \n");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return hashset_aux;
    }

    public void persistir_empleados(String archivo, HashSet<Empleado> empleados){ //del hashset al archivo
        try{
            FileOutputStream fileoutputstream = new FileOutputStream(archivo);
            ObjectOutputStream objoutputstream = new ObjectOutputStream(fileoutputstream);

            Iterator<Empleado> iterator = empleados.iterator();
            while(iterator.hasNext()){
                Empleado aux = iterator.next();
                objoutputstream.writeObject(aux);
            }
            fileoutputstream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado. \n");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public HashSet<Proveedor> desp_proveedores(String archivo){
        HashSet<Proveedor> hashset_aux = new HashSet<>();
        try{
            FileInputStream fileinputstream = new FileInputStream(archivo);
            ObjectInputStream objinputstream = new ObjectInputStream(fileinputstream);

            int lectura = 1;
            while(lectura == 1){
                Proveedor aux = (Proveedor)objinputstream.readObject();
                hashset_aux.add(aux);
            }
            fileinputstream.close();
        }
        catch(EOFException e){
            System.out.println("Fin del archivo. \n");
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado. \n");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return hashset_aux;
    }

    public void persistir_proveedores(String archivo, HashSet<Proveedor> proveedores){ //del hashset al archivo
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(archivo);
            ObjectOutputStream objoutputstream = new ObjectOutputStream(fileoutputstream);

            Iterator<Proveedor> iterator = proveedores.iterator();
            while (iterator.hasNext()) {
                Proveedor aux = iterator.next();
                objoutputstream.writeObject(aux);
            }
            fileoutputstream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado. \n");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Ticket> desp_tickets(String archivo){
        ArrayList<Ticket> arraylist_aux = new ArrayList<>();

        try{
            FileInputStream fileinputstream = new FileInputStream(archivo);
            ObjectInputStream objinputstream = new ObjectInputStream(fileinputstream);

            int lectura = 1;
            while(lectura == 1){
                Ticket aux = (Ticket)objinputstream.readObject();
                arraylist_aux.add(aux);
            }
            fileinputstream.close();
        }
        catch(EOFException e){
            System.out.println("Fin del archivo. \n");
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado. \n");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return arraylist_aux;
    }

    public void persistir_tickets(String archivo, ArrayList<Ticket> tickets){
        try{
            FileOutputStream fileoutputstream = new FileOutputStream(archivo);
            ObjectOutputStream objoutputstream = new ObjectOutputStream(fileoutputstream);

            for(int i=0;i<tickets.size();i++){
                Ticket aux = tickets.get(i);
                objoutputstream.writeObject(aux);
            }
            fileoutputstream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado. \n");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public Inventario<Producto> desp_inventario(String archivo){
        Inventario<Producto> aux = new Inventario<>();
        aux = aux.despersistirinventario(archivo);
        return aux;
    }

    public void persistir_inventario(String archivo, Inventario<Producto> inventario){
        Inventario<Producto> aux = new Inventario<>();
        aux = inventario;
        aux.persistirinventario(archivo,aux);
    }

}
