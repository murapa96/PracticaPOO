import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

/**
 * Clase que describe un producto dado.
 *
 * Un producto contiene un rendimiento y un nombre.
 *
 * @author Pablo Ramos (pramos280)
 * @version 280323
 */
public class Producto
{
    /*
     * nombre: Nombre del producto, describe el nombre que tiene el producto en el sistema.
     * rendimiento: Rendimiento del producto, descrito como T/ha.
     * esPerenne: Describe si el producto es perenne o no.
     * tablaPrecios: Relaciona una fecha con un precio del producto en centimos.
     */
    private int id;
    private double rendimiento;
    private String nombre;
    private boolean esPerenne;
    private int ventas;
    private Map<LocalDate, Double> tablaPrecios = new HashMap();
    /**
     * Constructor for objects of class Producto
     */
    public Producto(int id, String nNombre,Double nRendimiento, boolean nPerenne, double precio)
    {
        this.id = id;
        this.nombre = nNombre;
        this.rendimiento = nRendimiento;
        this.esPerenne = nPerenne;
        this.tablaPrecios.put(LocalDate.now(), precio);
        this.ventas = 0;

    }

    /*
     * TODO: Possible more constructors.
     */

    /**
     * getNombre
     *
     * @param      void
     * @return     String: el nombre de el producto.
     */

    String getNombre(){
        return this.nombre;
    }

    /**
     * setNombre
     *
     * @param      String nNombre: nuevo nombre de el producto.
     * @return     bool: Si el nombre ha sido guardado apropiadamente.
     */

    boolean setNombre(String nNombre){
        /*
         * TODO: Revisar esta implementacion, posible isEmpty?
         */
        if(nNombre.equals("")){
            return false; //Un producto no puede tener un nombre vacio.
        }
        this.nombre = nNombre;
        return true;
    }

    /**
     * getRendimiento
     *
     * @param      void
     * @return     Double: el rendimiento de el producto.
     */

    Double getRendimiento(){
        return this.rendimiento;
    }

    /**
     * setRendimiento
     *
     * @param      Double nRendimiento: nuevo rendimiento de el producto.
     * @return     bool: Si el rendimiento ha sido guardado apropiadamente.
     */

    boolean setRendimiento(Double nRendimiento){
        /*
         * TODO: Revisar esta implementacion, acotar rendimientos.
         */
        this.rendimiento = nRendimiento;
        return true;
    }

    int getId(){
        return id;
    }

    void setPrecio(double precio, LocalDate fecha){
         this.tablaPrecios.put(fecha, precio);
    }

    double getPrecio(LocalDate fecha){
        return this.tablaPrecios.get(fecha);
    }

    double getPrecio(){
    LocalDate ultimaFecha = null;
    for (Map.Entry<LocalDate, Double> entry : tablaPrecios.entrySet()) {
        LocalDate fecha = entry.getKey();
        if (ultimaFecha == null || fecha.isAfter(ultimaFecha)) {
            ultimaFecha = fecha;
        }
    }

    return tablaPrecios.get(ultimaFecha);
    }

    boolean esPerenne(){
        return this.esPerenne;
    }

    int getVentas(){
        return this.ventas;
    }

    void setVentas(int ventas){
        this.ventas = ventas;
    }


    void imprimirPrecios(){
        for (Map.Entry<LocalDate, Double> entry : tablaPrecios.entrySet()) {
            LocalDate fecha = entry.getKey();
            Double precio = entry.getValue();
            System.out.println("Fecha: " + fecha + " Precio: " + precio);
        }

    }



    @Override
    public String toString(){
        return "Producto " + id + ": " + nombre + "\n\t" + this.getPrecio() +"€" + "\n\tRendimiento: " + rendimiento + "T/ha" + "\n\tPerenne: " + esPerenne + "\n\tVentas: " + ventas;
     }

}
