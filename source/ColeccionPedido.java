import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.time.LocalDate;

/**
 * Write a description of class ColeccionPedidos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionPedido
{
    // instance variables - replace the example below with your own
    private int ultimoId;
    private List<Pedido> coleccion;


    /**
     * Constructor for objects of class ColeccionPedidos
     */
    public ColeccionPedido()
    {
        this.ultimoId = 0;
        coleccion = new ArrayList();
    }

    public Pedido crear(Cliente _cliente, Logistica _logistica,Producto prod, double cantidadKg, String _destino, int _distancia, LocalDate fechaCompra, LocalDate fechaEntregaEstimada){
        Pedido ped = new Pedido(this.ultimoId++, _cliente, _logistica, prod, cantidadKg, _destino, _distancia, fechaCompra, fechaEntregaEstimada);
        coleccion.add(ped);
        return ped;
    }

    public void eliminar(int id){
        Iterator<Pedido> i = coleccion.iterator();
        boolean eliminado = false;
        do{
            Pedido ped = i.next();
            if (ped.getId() == id) {
                i.remove();
                eliminado = true;
                System.out.println("Pedido eliminado: " + ped);
            }
        }while (i.hasNext() && !eliminado);
        if(!eliminado){
            System.out.println("No se ha encontrado el pedente a eliminar");
        }

    }

    Pedido get(int id){
        Iterator<Pedido> i = coleccion.iterator();
        while(i.hasNext()){
            Pedido ped = i.next();
            if(ped.getId()==id){
                return ped;
            }
        }
        return null;
    }
    
    List<Pedido> get(Cliente cliente){
        Iterator<Pedido> i = coleccion.iterator();
        List<Pedido> aux = new ArrayList();
        while(i.hasNext()){
            Pedido ped = i.next();
            if(ped.getCliente().equals(cliente)){
                aux.add(ped);
            }
        }
        return aux;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Lista de Logisticas: \n");
        Iterator<Pedido> i = coleccion.iterator();
        while(i.hasNext()){
            Pedido ped = i.next();
            sb.append("--");
            sb.append(ped);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void PrintToString(){
        System.out.println(this.toString());
    }
}


