import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Write a description of class ColeccionCliente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionCliente
{
    // instance variables - replace the example below with your own
    private int ultimoId;
    private List<Cliente> coleccion;

    /**
     * Constructor for objects of class ColeccionCliente
     */
    public ColeccionCliente()
    {
        // initialise instance variables
        this.ultimoId = 0;
        this.coleccion = new ArrayList();
    }

     public Cliente crear(String _nombre, TipoCliente _tipo, String _localizacion){
        Cliente cli = new Cliente(this.ultimoId++, _nombre, _tipo, _localizacion);
        coleccion.add(cli);
        return cli;
    }

    public void eliminar(int id){
        Iterator<Cliente> i = coleccion.iterator();
        boolean eliminado = false;
        do{
            Cliente cli = i.next();
            if (cli.getId() == id) {
                i.remove();
                eliminado = true;
                System.out.println("Cliente eliminado: " + cli);
            }
        }while (i.hasNext() && !eliminado);
        if(!eliminado){
            System.out.println("No se ha encontrado el cliente a eliminar");
        }

    }

    Cliente get(int id){
        Iterator<Cliente> i = coleccion.iterator();
        while(i.hasNext()){
            Cliente cli = i.next();
            if(cli.getId()==id){
                return cli;
            }
        }
        return null;
    }

    Cliente get(String nombre){
        Iterator<Cliente> i = coleccion.iterator();
        while(i.hasNext()){
            Cliente cli = i.next();
            if(cli.getNombre().equals(nombre)){
                return cli;
            }
        }
        return null;
    }

    List<Cliente> getAll(){
        return coleccion;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Lista de Clientes: \n");
        Iterator<Cliente> i = coleccion.iterator();
        while(i.hasNext()){
            Cliente cli = i.next();
            sb.append("--");
            sb.append(cli);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void PrintToString(){
        System.out.println(this.toString());
    }
}
