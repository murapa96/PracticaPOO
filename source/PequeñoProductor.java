
/**
 * Write a description of class PequeñoProductor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PequeñoProductor extends Productor
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class PequeñoProductor
     */
    public PequeñoProductor(int _id, String _nombre, double _hectareas)
    {
        // initialise instance variables
        super(_id, _nombre, _hectareas);
        super.tipo = TipoProductor.PEQUEÑO;
    }

    public void añadirPlantacion(Producto producto, double hectareasCultivo){
        if(super.plantaciones.size() < Configuracion.LIMITE_CULTIVOS_PEQUEÑO_PRODUCTOR){
            super.añadirPlantacion(producto, hectareasCultivo);
        }else{
            System.err.println("No se ha podido añadir la plantacion, el productor " + super.nombre + " ya tiene 5 cultivos");
        }
    }

}
