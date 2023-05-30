
/**
 * Write a description of class GranProductor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GranProductor extends Productor
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class GranProductor
     */
    public GranProductor(int _id, String _nombre, double _hectareas)
    {
        super(_id, _nombre, _hectareas);
        super.tipo = TipoProductor.GRAN;
    }

}
