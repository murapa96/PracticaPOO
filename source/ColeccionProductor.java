import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Write a description of class ColeccionProductor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionProductor
{
    // instance variables - replace the example below with your own
    private int ultimoId;
    private ArrayList<Productor> coleccion;
    private double limiteHectareas;

    /**
     * Constructor for objects of class ColeccionProductor
     */
    public ColeccionProductor(double limiteHectareas)
    {
        // initialise instance variables
        this.ultimoId = 0;
        this.limiteHectareas = limiteHectareas;
        this.coleccion = new ArrayList();
    }

    public Productor crear(String _nombre, double _hectareas){
        Productor productor;
        if(limiteHectareas >= _hectareas){
            productor = new PequeñoProductor( this.ultimoId++, _nombre, _hectareas);
        }else{
            productor = new GranProductor(this.ultimoId++, _nombre, _hectareas);
        }

        coleccion.add(productor);
        return productor;
    }

    public ProductorFederado crearFederado(String _nombre, Producto productoFederado){
        //Primero buscamos si existe ya un productor federado con el mismo producto.
        ProductorFederado productorProducto = this.getFederado(productoFederado);
        if(productorProducto == null){
            productorProducto = new ProductorFederado(this.ultimoId++, _nombre, productoFederado);
            coleccion.add(productorProducto);
        }
        return productorProducto;
    }

    public void eliminar(int id){
        Iterator<Productor> i = coleccion.iterator();
        boolean eliminado = false;
        do{
            Productor prod = i.next();
            if (prod.getId() == id) {
                i.remove();
                eliminado = true;
                System.out.println("Productor eliminado: " + prod);
            }
        }while (i.hasNext() && !eliminado);
        if(!eliminado){
            System.out.println("No se ha encontrado el productor a eliminar");
        }

    }

    public Productor get(int id){
        Iterator<Productor> i = coleccion.iterator();
        while(i.hasNext()){
            Productor prod = i.next();
            if(prod.getId()==id){
                return prod;
            }
        }
        return null;
    }

    public Productor get(String nombre){
        Iterator<Productor> i = coleccion.iterator();
        while(i.hasNext()){
            Productor prod = i.next();
            if(prod.getNombre().equals(nombre)){
                return prod;
            }
        }
        return null;
    }

       public ProductorFederado getFederado(Producto producto){
        Iterator<Productor> i = coleccion.iterator();
        while(i.hasNext()){
            Productor prod = i.next();
            if(prod.tieneProducto(producto) && prod instanceof ProductorFederado){
                return (ProductorFederado)prod;
            }
        }
        return null;
    }
    public List<Productor> get(Producto producto){
        List<Productor> aux = new ArrayList();
        Iterator<Productor> i = coleccion.iterator();
        while(i.hasNext()){
            Productor prod = i.next();
            if(prod.tieneProducto(producto)){
                aux.add(prod);
            }
        }
        return aux;
    }

    public List<Productor> filtrarProductores(TipoProductor tipo){
        List<Productor> aux = new ArrayList();
        Iterator<Productor> i = coleccion.iterator();
        while(i.hasNext()){
            Productor prod = i.next();
            if(prod.getTipo()==tipo){
                aux.add(prod);
            }
        }
        return aux;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Lista de Productores: \n");
        Iterator<Productor> i = coleccion.iterator();
        while(i.hasNext()){
            Productor prod = i.next();
            sb.append("\t");
            sb.append(prod);
            sb.append("\n");
        }
        return sb.toString();
    }

}
