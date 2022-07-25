package principal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import personal.Cocinero;
import personal.Empleado;
import personal.Proveedor;
import personal.Vendedor;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;

public class ProcesamientoJSON {
    public static String leer(String name){
        String contenido = "";
        try{
            contenido = new String(Files.readAllBytes(Paths.get(name+".json")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return contenido;
    }

    public static void grabar(JSONArray array, String name) {
        try {
            FileWriter file = new FileWriter(name+".json");
            file.write(array.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarEmpleadosJSON(HashSet<Empleado> empleados){
        try{
            JSONArray jsonArray = new JSONArray();
            Iterator<Empleado> iterator = empleados.iterator();
            while(iterator.hasNext()){
                Empleado aux = iterator.next();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("nombre",aux.getNombre());
                jsonObject.put("apellido",aux.getApellido());
                jsonObject.put("dni",aux.getDni());
                jsonObject.put("deudaSalario",aux.getSalario());
                jsonObject.put("sueldoPago",aux.isSueldoPago());
                jsonObject.put("alta",aux.isAlta());
                if(aux instanceof Vendedor){
                    jsonObject.put("sueldoBase",((Vendedor) aux).getSueldoBase());
                    jsonObject.put("ventasRealizadas",((Vendedor) aux).getVentasRealizadas());
                    jsonObject.put("comisionXventa",((Vendedor) aux).getComisionXventa());
                }
                if(aux instanceof Cocinero){
                    jsonObject.put("horasTrabajadas",((Cocinero) aux).getHorasTrabajadas());
                    jsonObject.put("sueldoXhora",((Cocinero) aux).getSueldoXhora());
                }
                jsonArray.put(jsonObject);
            }
            grabar(jsonArray,"empleadosJSON");
        }catch(JSONException e){

        }
    }

    public static void cargarProveedoresJSON(HashSet<Proveedor> proveedores){
        try{
            JSONArray jsonArray = new JSONArray();
            Iterator<Proveedor> iterator = proveedores.iterator();
            while(iterator.hasNext()){
                Proveedor aux = iterator.next();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("nombre",aux.getNombre());
                jsonObject.put("deuda",aux.getDeuda());

                jsonArray.put(jsonObject);
            }
            grabar(jsonArray,"proveedoresJSON");
        }catch(JSONException e){

        }
    }

    public static void cargarIventarioJSON(Sistema sistema){
        JSONArray jsonArray = new JSONArray();
        jsonArray = sistema.getStock().cargarJSON(jsonArray);
        grabar(jsonArray,"inventarioJSON");

    }
}
