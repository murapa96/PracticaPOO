
/**
 * Write a description of class Logistica here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Logistica
{
    // instance variables - replace the example below with your own
    private int id;
    private String nombre;
    private double precioGran;
    private double precioPequeña;
    private double ganancias;
    /**
     * Constructor for objects of class Logistica
     */
    public Logistica(int _id, String _nombre, double _precioGran, double _precioPequeña)
    {
        this.id = _id;
        this.nombre = _nombre;
        this.precioGran = _precioGran;
        this.precioPequeña = _precioPequeña;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return this.nombre;
    }

     public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioGran() {
        return precioGran;
    }

    public void setPrecioGran(double precioGran) {
        this.precioGran = precioGran;
    }

    public double getPrecioPequeña() {
        return precioPequeña;
    }

    public void setPrecioPequeña(double precioPequeña) {
        this.precioPequeña = precioPequeña;
    }

    public double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }

    @Override
    public String toString(){
        return "Logistica " + this.id + ": " + this.nombre + "\n\t" + "Precio Gran: " + this.precioGran + "€/km" + "\n\t" + "Precio Pequeña: " + this.precioPequeña + "€/km" + "\n\t" + "Ganancias: " + this.ganancias + "€";
    }



}
