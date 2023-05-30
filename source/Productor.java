import java.util.Map;
import java.util.HashMap;

/**
 * Write a description of class Productor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Productor
{
    // instance variables - replace the example below with your own
    protected int id;
    protected String nombre;
    protected double hectareas;
    protected Map<Producto, Double> plantaciones;
    protected TipoProductor tipo;
    protected double ganancias;

    /**
     * Constructor for objects of class Productor
     */
    public Productor(int _id, String _nombre, double _hectareas)
    {
        id = _id;
        nombre = _nombre;
        hectareas = _hectareas;
        plantaciones = new HashMap();
    }

    public Productor(int _id, String _nombre, double _hectareas, Map<Producto, Double> _plantaciones){
        id = _id;
        nombre = _nombre;
        hectareas = _hectareas;
        plantaciones = _plantaciones;
    }

    void setNombre(String _nombre){
        if(!_nombre.equals("")){
            nombre = _nombre;
        }
    }

    String getNombre(){
        return nombre;
    }

    int getId(){
        return id;
    }

    double getHectareas(){
        return hectareas;
    }

    void añadirPlantacion(Producto producto, double _hectareas){
        double hectareasCultivadas = 0;
        for(Map.Entry<Producto,Double> plantacion: plantaciones.entrySet()){
            if(producto == plantacion.getKey()){
                _hectareas += plantacion.getValue();
            }else{
                hectareasCultivadas += plantacion.getValue();
            }
        }

        if(hectareasCultivadas <= this.hectareas){
            plantaciones.put(producto,_hectareas);
        }

    }


    void eliminarPlantacion(Producto producto){
        plantaciones.remove(producto);
    }

    double getTotalHectareasCultivadas(){
        double hectareasCultivadas = 0;
        for(Map.Entry<Producto,Double> plantacion: plantaciones.entrySet()){
            hectareasCultivadas += plantacion.getValue();
        }
        return hectareasCultivadas;
    }

    double getHectareasProducto(Producto producto){
        return plantaciones.get(producto);
    }

    Map<Producto,Double> getPlantaciones(){
        return this.plantaciones;
    }

    void setPlantaciones(Map<Producto,Double> _plantaciones){
        this.plantaciones = _plantaciones;
    }

    boolean tieneProducto(Producto producto){
        return plantaciones.containsKey(producto);
    }

    TipoProductor getTipo(){
        return tipo;
    }

    double getGanancias(){
        return ganancias;
    }

    void setGanancias(double _ganancias){
        ganancias = _ganancias;
    }

    @Override
    public String toString(){
        return "Productor " + id + ": " + nombre + "\n\t" + "Hectareas: " + hectareas + "\n\t" + "Plantaciones: " + plantaciones.toString() + "\n\t" + "Tipo: " + tipo + "\n\t" + "Ganancias: " + ganancias;
     }

}
