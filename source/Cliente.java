
/**
 * Write a description of class Cliente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cliente
{
    private int id;
    private String nombre;
    private TipoCliente tipo;
    private String localizacion;
    
    
    /**
     * Constructor for objects of class Cliente
     */
    public Cliente(int _id, String _nombre, TipoCliente _tipo, String _localizacion)
    {
        this.id = _id;
        this.nombre = _nombre;
        this.tipo = _tipo;
        this.localizacion = _localizacion;
    }
    
      public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(!nombre.equals(""))
            this.nombre = nombre;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
    
}
