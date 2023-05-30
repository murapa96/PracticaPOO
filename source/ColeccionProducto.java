import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Write a description of class ColeccionProducto here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionProducto
{
    // instance variables - replace the example below with your own
    private int ultimoId;
    private ArrayList<Producto> coleccion;
    /**
     * Constructor for objects of class ColeccionProducto
     */
    public ColeccionProducto()
    {
        // initialise instance variables
        ultimoId = 0;
        coleccion = new ArrayList();
    }

    public Producto crear(String nName,Double nRendimiento, boolean nPerenne, double precio){
        Producto temp = new Producto(ultimoId++, nName, nRendimiento, nPerenne, precio);
        coleccion.add(temp);

        return temp;
    }

    public void eliminar(int id){
        Iterator<Producto> i = coleccion.iterator();
        boolean eliminado = false;
        do{
            Producto prod = i.next();
            if (prod.getId() == id) {
                i.remove();
                eliminado = true;
                System.out.println("Producto eliminado: " + prod);
            }
        }while (i.hasNext() && !eliminado);
        if(!eliminado){
            System.out.println("No se ha encontrado el producto a eliminar");
        }

    }

     Producto get(int id){
        Iterator<Producto> i = coleccion.iterator();
        while(i.hasNext()){
            Producto prod = i.next();
            if(prod.getId()==id){
                return prod;
            }
        }
        return null;
    }

    Producto get(String nombre){
        Iterator<Producto> i = coleccion.iterator();
        while(i.hasNext()){
            Producto prod = i.next();
            if(prod.getNombre().equals(nombre)){
                return prod;
            }
        }
        return null;
    }

    void imprimirPrecios(){
        Iterator<Producto> i = coleccion.iterator();
        while(i.hasNext()){
            Producto prod = i.next();
            System.out.println(prod.getNombre());
            prod.imprimirPrecios();
        }
    }

    void revisarPrecios(){
        Iterator<Producto> i = coleccion.iterator();
        Scanner sc = new Scanner(System.in);
        while(i.hasNext()){
            Producto prod = i.next();
            System.out.println("Introduce el nuevo precio para :" + prod.getNombre());
            prod.setPrecio(sc.nextDouble(), LocalDate.now());
        }
        sc.close();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Lista de Productos disponibles: \n");
        Iterator<Producto> i = coleccion.iterator();
        while(i.hasNext()){
            Producto prod = i.next();
            sb.append("--");
            sb.append(prod);
            sb.append("\n");
        }
        return sb.toString();
    }

}
