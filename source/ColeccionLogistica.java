import java.util.ArrayList;
import java.util.Iterator;

/**
 * Write a description of class ColeccionLogistica here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionLogistica
{
    // instance variables - replace the example below with your own
    private int ultimoId;
    private ArrayList<Logistica> coleccion;

    /**
     * Constructor for objects of class ColeccionLogistica
     */
    public ColeccionLogistica()
    {
        // initialise instance variables
        ultimoId = 0;
        coleccion = new ArrayList();

    }

    public Logistica crear(String _nombre, double _precioGran, double _precioPequeña){
        Logistica log = new Logistica(this.ultimoId++, _nombre, _precioGran, _precioPequeña);
        coleccion.add(log);
        return log;
    }

    public void eliminar(int id){
        Iterator<Logistica> i = coleccion.iterator();
        boolean eliminado = false;
        do{
            Logistica log = i.next();
            if (log.getId() == id) {
                i.remove();
                eliminado = true;
                System.out.println("Producto eliminado: " + log);
            }
        }while (i.hasNext() && !eliminado);
        if(!eliminado){
            System.out.println("No se ha encontrado el producto a eliminar");
        }

    }

    Logistica get(int id){
        Iterator<Logistica> i = coleccion.iterator();
        while(i.hasNext()){
            Logistica log = i.next();
            if(log.getId()==id){
                return log;
            }
        }
        return null;
    }

    Logistica get(String nombre){
        Iterator<Logistica> i = coleccion.iterator();
        while(i.hasNext()){
            Logistica log = i.next();
            if(log.getNombre().equals(nombre)){
                return log;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Lista de Logisticas: \n");
        Iterator<Logistica> i = coleccion.iterator();
        while(i.hasNext()){
            Logistica log = i.next();
            sb.append("--");
            sb.append(log);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void PrintToString(){
        System.out.println(this.toString());
    }

}
