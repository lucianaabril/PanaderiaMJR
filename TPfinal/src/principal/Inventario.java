package principal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import producto.*;

import javax.swing.plaf.ProgressBarUI;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Inventario<T extends Producto> implements Serializable {
    int cantMercaderia;
    private HashMap<Integer,T> stock;

    public Inventario(){
        this.cantMercaderia = 0;
        this.stock = new HashMap<>();
    }

    public void agregar(int id, T mercaderia){
        stock.put(id,mercaderia);
        cantMercaderia++;
    }

    public void eliminar(int id){
        stock.remove(id);
        cantMercaderia--;
    }

    public void restar(int id, int cant){
        stock.get(id).setCantidad((stock.get(id).getCantidad())-cant);
    }

    public void sumar(int id, int cant){
        stock.get(id).setCantidad((stock.get(id).getCantidad())+cant);
    }

    public void descontarProductos(HashMap<Integer,Producto> detalle){
        Iterator<Map.Entry<Integer,Producto>> iterator = detalle.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,Producto> fila = iterator.next();
            restar(fila.getKey(),fila.getValue().getCantidad());
        }
    }

    public int getCantMercaderia() {
        return cantMercaderia;
    }

    public HashMap<Integer, T> getStock() {
        return stock;
    }

    public void setStock(HashMap<Integer, T> stock) {
        this.stock = stock;
    }

    public void setCantMercaderia(int cantMercaderia) {
        this.cantMercaderia = cantMercaderia;
    }

    public Inventario<T> despersistirinventario(String archivo) {
        HashMap<Integer,T> hashmap_aux = new HashMap<>();
        int cantidadTotal = 0;
        try {
            FileInputStream fileinputstream = new FileInputStream(archivo);
            ObjectInputStream objoutput = new ObjectInputStream(fileinputstream);

            int lectura = 1;
            while(lectura == 1){
                Integer key = (Integer) objoutput.readObject();
                T value = (T) objoutput.readObject();
                cantidadTotal += value.getCantidad();
                hashmap_aux.put(key,value);
            }
            fileinputstream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. \n");
        } catch (EOFException e) {
            System.out.println("Fin del archivo. \n");
        } catch (IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        Inventario<T> aux = new Inventario<>();
        aux.setStock(hashmap_aux);
        aux.setCantMercaderia(cantidadTotal);
        return aux;
    }

    public void persistirinventario(String archivo, Inventario<T> inventario){
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(archivo);
            ObjectOutputStream objoutputstream = new ObjectOutputStream(fileoutputstream);

            Iterator<Map.Entry<Integer, T>> iterator_invent = inventario.getStock().entrySet().iterator();
            while (iterator_invent.hasNext()) {
                Map.Entry<Integer, T> fila = iterator_invent.next();
                objoutputstream.writeObject(fila.getKey());
                objoutputstream.writeObject(fila.getValue());
            }
            fileoutputstream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado. \n");
        }
        catch(EOFException e){
            System.out.println("Fin del archivo. \n");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public JSONArray cargarJSON(JSONArray jsonArray){
        try{
            Iterator<Map.Entry<Integer, T>> iterator_invent = this.stock.entrySet().iterator();
            while (iterator_invent.hasNext()) {
                Map.Entry<Integer, T> fila = iterator_invent.next();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key",fila.getKey());
                jsonObject.put("detalle",fila.getValue().getDetalle());
                jsonObject.put("cantidad",fila.getValue().getCantidad());
                jsonObject.put("precioVenta",fila.getValue().getPrecioVenta());
                if(fila.getValue() instanceof ProductoReventa){
                    jsonObject.put("precioCompra",((ProductoReventa) fila.getValue()).getPrecioCompra());
                }
                if(fila.getValue() instanceof Pan){
                    jsonObject.put("saborizado",((Pan) fila.getValue()).isSaborizado());
                }
                if(fila.getValue() instanceof Budin){
                    jsonObject.put("celiaco",((Budin) fila.getValue()).isCeliaco());
                }
                if(fila.getValue() instanceof Factura){
                    jsonObject.put("rellena",((Factura) fila.getValue()).isRellena());
                }
                if(fila.getValue() instanceof Galletita){
                    jsonObject.put("sabor",((Galletita) fila.getValue()).getSabor());
                }
                if(fila.getValue() instanceof SangucheMiga){
                    jsonObject.put("tipoSanguche",((SangucheMiga) fila.getValue()).getTipoSanguche());
                    jsonObject.put("tipoPan",((SangucheMiga) fila.getValue()).getTipoPan());
                    jsonObject.put("vegetariano",((SangucheMiga) fila.getValue()).isVegetariano());
                }
                if(fila.getValue() instanceof Torta){
                    jsonObject.put("helada",((Torta) fila.getValue()).isHelada());
                }

                jsonArray.put(jsonObject);
            }
        }catch(JSONException e){

        }
        return jsonArray;
    }

    @Override
    public String toString() {
        return "INVENTARIO" + '\n' +
                "cantidad de mercaderia:" + cantMercaderia + '\n' +
                "stock:" + stock;
    }
}
