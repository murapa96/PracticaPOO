import java.time.LocalDate;
import java.util.List;

/**
 * Write a description of class Pedidos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pedido {
    // instance variables - replace the example below with your own
    private int id;

    private Cliente cliente;
    private Logistica logistica;

    private double cantidadKg;
    private Producto producto;

    private double precioTotal;
    private double precioLogistica;
    private double precioProductos;

    private List<Productor> productores;

    private String destino;
    private int distancia;
    private LocalDate fechaCompra;
    private LocalDate fechaEntregaEstimada;

    boolean finalizado;

    /**
     * Constructor for objects of class Pedidos
     */
    public Pedido(int _id, Cliente _cliente, Logistica _logistica, Producto prod, double cantidadKg, String _destino,
            int _distancia, LocalDate fechaCompra, LocalDate fechaEntregaEstimada) {
        this.id = _id;
        this.cliente = _cliente;
        this.logistica = _logistica;
        this.producto = prod;
        this.cantidadKg = cantidadKg;
        this.destino = _destino;
        this.distancia = _distancia;
        this.fechaCompra = fechaCompra;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.finalizado = false;
        this.calcularPrecio();
        this.calcularPrecioLogistica(this.producto.getPrecio());
        this.precioTotal = this.precioLogistica + this.precioProductos;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCli(Cliente cli) {
        if (!finalizado) {
            this.cliente = cli;
        } else {
            System.out.println("No se puede editar un pedido finalizado.");
        }
    }

    public Logistica getLogistica() {
        return logistica;
    }

    public void setLogistica(Logistica log) {
        if (!finalizado) {
            this.logistica = log;
            this.calcularPrecioLogistica(this.producto.getPrecio());

        } else {
            System.out.println("No se puede editar un pedido finalizado.");
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        if (!finalizado) {
            this.producto = producto;
            this.calcularPrecio();
        } else {
            System.out.println("No se puede editar un pedido finalizado.");
        }
    }

    public List<Productor> getProductores() {
        return productores;
    }

    public void setProductores(List<Productor> productores) {
        if (!finalizado) {
            this.productores = productores;
        } else {
            System.out.println("No se puede editar un pedido finalizado.");
        }
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public double getPrecioLogistica() {
        return precioLogistica;
    }

    public double getPrecioProductos() {
        return precioProductos;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        if (!finalizado) {
            this.destino = destino;
        } else {
            System.out.println("No se puede editar un pedido finalizado.");
        }
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        if (!finalizado) {
            this.distancia = distancia;
        } else {
            System.out.println("No se puede editar un pedido finalizado.");
        }
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        if (!finalizado) {
            this.fechaCompra = fechaCompra;
        } else {
            System.out.println("No se puede editar un pedido finalizado.");
        }
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        if (!finalizado) {
            this.fechaEntregaEstimada = fechaEntregaEstimada;
        } else {
            System.out.println("No se puede editar un pedido finalizado.");
        }
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void finalizar() {
        if(this.validarCantidad()){
            this.finalizado = true;
            this.calcularPrecio();
        }else{
            System.out.println("No se puede finalizar el pedido, la cantidad de kg no es valida.");
        }
    }

    private void calcularPrecio() {
        // Tomamos el precio base del producto
        double precioReferencia = this.producto.getPrecio();
        double precioKg = precioReferencia * this.getPorcentajeGanancia();
        this.precioProductos = precioKg * this.cantidadKg;
        this.precioTotal = this.precioProductos + this.calcularPrecioLogistica(precioReferencia);
        if(this.cliente.getTipo() == TipoCliente.CONSUMIDOR){
            this.precioTotal = this.precioTotal * 1.10;

        }

    }

    private double calcularPrecioLogistica(double precioReferencia) {
        // Tomamos el precio base del producto y logistica

        double precioKg = precioReferencia * this.getPorcentajeGanancia();
        double precioKmGranLogistica = this.logistica.getPrecioGran();
        double precioKmPequeñaLogistica = this.logistica.getPrecioPequeña();

        // Calculamos el numero de trayectos que se deben hacer
        double toneladas = Math.ceil(this.cantidadKg / Configuracion.TONELADAS);

        int numeroDeGranLogisticas = this.distancia / Configuracion.LIMITE_GRAN_LOGISTICA;
        double kmGranLogistica = numeroDeGranLogisticas * Configuracion.LIMITE_GRAN_LOGISTICA;
        if(this.producto.esPerenne() && this.distancia > 100){
            System.out.println("El producto es perenne y la distancia es mayor a 100km, se debe hacer un trayecto mas para llegar a la capital.");
            numeroDeGranLogisticas++;
        }
        // Calculamos km pequeña logistica.
        double kmPequeñaLogistica = this.distancia %  Configuracion.LIMITE_GRAN_LOGISTICA;

        // Calculamos el precio de la gran logistica

        double precioGranLogistica = Configuracion.CONSTANTE_GRAN_LOGISTICA * (precioKg * (this.cantidadKg / toneladas)) * numeroDeGranLogisticas;
        precioGranLogistica += kmGranLogistica * precioKmGranLogistica;
        precioGranLogistica = precioGranLogistica * toneladas;

        // Calculamos el precio de la pequeña logistica

        double precioPequeñaLogistica = precioKmPequeñaLogistica * kmPequeñaLogistica * this.cantidadKg;
        precioPequeñaLogistica = precioPequeñaLogistica * toneladas;
        this.precioLogistica = precioGranLogistica + precioPequeñaLogistica;
        return this.precioLogistica;
    }

    public boolean validarCantidad() {
        // TODO: Evitar numeros magicos, buscar donde guardar esa configuracion.
        TipoCliente tipo = this.cliente.getTipo();
        return (tipo == TipoCliente.DISTRIBUIDOR && this.cantidadKg >= Configuracion.LIMITE_DISTRIBUIDOR)
                || (tipo == TipoCliente.CONSUMIDOR && this.cantidadKg <= Configuracion.LIMITE_CONSUMIDOR);

    }

    private double getPorcentajeGanancia() {
        return this.cliente.getTipo() == TipoCliente.DISTRIBUIDOR ? Configuracion.GANANCIA_DISTRIBUIDOR : Configuracion.GANANCIA_CONSUMIDOR;
    }

    @Override
    public String toString(){
        return "Pedido " + id + ": " + producto.getNombre() + " - " + cantidadKg + "kg - " + precioTotal + "€";

     }

}
