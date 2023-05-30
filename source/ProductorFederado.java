import java.util.Map;

/**
 * Write a description of class ProductorFederado here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProductorFederado extends Productor
{
    // instance variables - replace the example below with your own
    private Producto productoFederado;
    private Map<Productor, Double> productoresMiembros;

    /**
     * Constructor for objects of class ProductorFederado
     */
    public ProductorFederado(int _id, String _nombre, Producto _productoFederado)
    {
        super(_id, _nombre, 0.0);
        this.productoFederado = _productoFederado;
        super.tipo = TipoProductor.FEDERADO;
    }

    void añadirMiembro(Productor productorMiembro){

        if(productorMiembro instanceof PequeñoProductor && productorMiembro.tieneProducto(productoFederado) && !productoresMiembros.containsKey(productorMiembro)){
            double hectareasMiembro = productorMiembro.getHectareasProducto(this.productoFederado);
            productoresMiembros.put(productorMiembro, hectareasMiembro);
            super.hectareas += hectareasMiembro;
        }else{
            System.out.println("Error al añadir miembro al productor federado " + super.nombre + ".");
        }

    }

    void eliminarMiembro(Productor productorMiembro){
        if(productoresMiembros.containsKey(productorMiembro)){
            super.hectareas -= productoresMiembros.get(productorMiembro);
            productoresMiembros.remove(productorMiembro);
        }
    }

    Producto getProductoFederado(){
        return productoFederado;
    }

    //Como el federado no puede tener más de un producto, no tiene sentido que tenga un método para añadir un producto.
    //Por eso, se sobreescribe el método de la clase padre para que no haga nada.

    void añadirPlantacion(Producto producto, double _hectareas){
        System.out.println("No se pueden añadir plantaciones a un productor federado." );

    }

    //Lo mismo con añadir

    void eliminarPlantacion(Producto producto){
        System.out.println("No se pueden añadir plantaciones a un productor federado." );

    }

    boolean tieneProducto(Producto producto){
        return productoFederado.equals(producto);
    }







}
