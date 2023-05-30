import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * Write a description of class Cooperativa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cooperativa {
    // instance variables - replace the example below with your own

    ColeccionProductor productores = new ColeccionProductor(Configuracion.LIMITE_HECTAREAS_PEQUEÑO_PRODUCTOR);
    ColeccionProducto productos = new ColeccionProducto();
    ColeccionCliente clientes = new ColeccionCliente();
    ColeccionLogistica logisticas = new ColeccionLogistica();
    ColeccionPedido pedidos = new ColeccionPedido();
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor for objects of class Cooperativa
     */
    public Cooperativa() {
        // initialise instance variables

    }
    
    static void main(){
        Cooperativa coop = new Cooperativa();
        coop.run();
        
    }

    private void cleanConsole() {
        System.out.print('\u000C');
        System.out.flush();
    }

    private void mockData(){
        this.logisticas.crear("Logistica 1", 0.05, 0.01);
        this.logisticas.crear("Logistica 2", 0.1, 0.02);
        this.logisticas.crear("Logistica 3", 0.15, 0.03);
        this.logisticas.crear("Logistica 4", 0.2, 0.04);



        Producto manzana = this.productos.crear("Manzana", 0.9, true, 0.1);
        this.productos.crear("Pera", 0.8, true, 0.2);
        this.productos.crear("Banana", 0.7, true, 0.3);
        this.productos.crear("Tomate", 0.6, false, 0.4);

        Productor prod1 =this.productores.crear("Productor 1", 4);
        Productor prod2 =this.productores.crear("Productor 2", 3);
        this.productores.crear("Productor 3", 10);
        this.productores.crear("Productor 4", 5);

        ProductorFederado prodFederado = this.productores.crearFederado("Federado Manzana", manzana);

        prodFederado.añadirMiembro(prod2);
        prodFederado.añadirMiembro(prod1);



        this.clientes.crear("Cliente 1", TipoCliente.CONSUMIDOR, "Direccion1");
        this.clientes.crear("Cliente 2", TipoCliente.CONSUMIDOR, "Direccion2");
        this.clientes.crear("Cliente 3", TipoCliente.DISTRIBUIDOR, "Direccion3");

        this.pedidos.crear(this.clientes.get(0), this.logisticas.get(0), this.productos.get(0), 100, "Destino 1", 100, LocalDate.now(), LocalDate.now());
        this.pedidos.crear(this.clientes.get(1), this.logisticas.get(1), this.productos.get(1), 200, "Destino 2", 200, LocalDate.now(), LocalDate.now());
        this.pedidos.crear(this.clientes.get(2), this.logisticas.get(2), this.productos.get(2), 300, "Destino 3", 300, LocalDate.now(), LocalDate.now());

    }

    private void cajaBienvenida() {
        System.out.println("*********************************");
        System.out.println("*                               *");
        System.out.println("*          Cooperativa          *");
        System.out.println("*                               *");
        System.out.println("*********************************");
    }

    void run() {
        this.mockData();
        this.menuPrincipal();
        this.sc.close();
    }

    private void menuPrincipal() {
        int opcion = 0;
        do {
            this.cleanConsole();
            this.cajaBienvenida();
            System.out.println("Menu Principal");
            System.out.println("1. Control de datos");
            System.out.println("2. Realizar pedido");
            System.out.println("3. Estadisticas");
            System.out.println("4. Revisar precios");
            System.out.println("0. Salir");
            System.out.println("\nIngrese una opcion: ");
            try {
                opcion = sc.nextInt();
                this.sc.nextLine();
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    this.menuControlDatos();
                    break;
                case 2:

                    this.realizarPedido();
                    break;
                case 3:
                    this.menuEstadisticas();
                    break;
                case 4:
                    this.productos.revisarPrecios();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcion != 0);
    }

    private void menuControlDatos() {
        int opcion = 0;
        // Limpiamos la consola:
        this.cleanConsole();
        System.out.println("*********************************");
        System.out.println("*                               *");
        System.out.println("*       Control de Datos        *");
        System.out.println("*                               *");
        System.out.println("*********************************");
        do {
            System.out.println("1. Productores");
            System.out.println("2. Productos");
            System.out.println("3. Clientes");
            System.out.println("4. Logistica");
            System.out.println("5. Listar Pedidos");
            System.out.println("0. Volver");
            System.out.println("\nIngrese una opcion: ");
            try {
                opcion = sc.nextInt();
                this.sc.nextLine();
            } catch (Exception e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    this.menuProductores();
                    break;
                case 2:
                    this.menuProductos();
                    break;
                case 3:
                    this.menuClientes();
                    break;
                case 4:
                    this.menuLogistica();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcion != 0);
    }

    private void menuEstadisticas(){
        int opcion = 0;
        // Limpiamos la consola:
        this.cleanConsole();
        System.out.println("*********************************");
        System.out.println("*                               *");
        System.out.println("*          Estadisticas         *");
        System.out.println("*                               *");
        System.out.println("*********************************");
        do {
            System.out.println("1. Precio Productos");
            System.out.println("2. Pedidos clientes");
            System.out.println("3. Ganancias Productores");
            System.out.println("4. Ganancias Productos");
            System.out.println("5. Ganancias Logistica");
            System.out.println("0. Volver");

            System.out.println("\nIngrese una opcion: ");
            try {
                opcion = sc.nextInt();
                this.sc.nextLine();
            } catch (Exception e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    this.productos.imprimirPrecios();

                case 2:
                    this.imprimirPedidosCliente();
                    break;
                case 3:
                    System.out.println(this.productores);
                    System.out.println("Pulse enter para continuar:");
                    this.sc.nextLine();
                    break;
                case 4:
                    System.out.println(this.productos);
                    System.out.println("Pulse enter para continuar:");
                    this.sc.nextLine();
                    break;
                case 5:
                    System.out.println(this.logisticas);
                    System.out.println("Pulse enter para continuar:");
                    this.sc.nextLine();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcion != 0);
    }

    private void imprimirPedidosCliente(){
        System.out.println("Ingrese el nombre del cliente:");
        String nombreCliente = this.sc.nextLine();
        Cliente cliente = this.clientes.get(nombreCliente);
        if(cliente == null){
            System.out.println("El cliente no existe");
            System.out.println("Presione enter para continuar...");
            this.sc.nextLine();
            return;
        }
        Iterator<Pedido> iteratorPedido = this.pedidos.get(cliente).iterator();
        while(iteratorPedido.hasNext()){
            Pedido pedido = iteratorPedido.next();
            System.out.println(pedido);
        }
        System.out.println("Presione enter para continuar...");
        this.sc.nextLine();
    }

    private void realizarPedido(){
        boolean salir = false;
        // Limpiamos la consola:
        this.cleanConsole();
        System.out.println("*********************************");
        System.out.println("*                               *");
        System.out.println("*       Realizar Pedido         *");
        System.out.println("*                               *");
        System.out.println("*********************************");
        do {
            System.out.println("Introduce el nombre del producto:");
            String nombreProducto = this.sc.nextLine();
            Producto producto = this.productos.get(nombreProducto);
            if(producto == null){
                System.out.println("El producto no existe");
                System.out.println("Presione enter para continuar...");
                this.sc.nextLine();
                break;
            }
            System.out.println("Introduce la cantidad de kilos:");
            int cantidad = this.sc.nextInt();
            this.sc.nextLine();
            System.out.println("Introduce el nombre del cliente:");
            //Se buscan los productores que contienen el producto, y se calcula rendimiento*hectareasPlantacion para comprobar que hay suficiente
            //Si no hay suficiente, se muestra un mensaje de error y se vuelve al menu principal
            double cantidadCooperativa = 0;

            List<Productor> productoresConProducto= this.productores.get(producto);

            Iterator<Productor> prodIterator = productoresConProducto.iterator();

            while(prodIterator.hasNext()){
                Productor productor = prodIterator.next();
                cantidadCooperativa += producto.getRendimiento()*productor.getHectareasProducto(producto);
            }
            if(cantidadCooperativa < cantidad){
                System.out.println("No hay suficiente producto");
                System.out.println("Presione enter para continuar...");
                this.sc.nextLine();
                break;
            }
            String nombreCliente = this.sc.nextLine();
            Cliente cliente = this.clientes.get(nombreCliente);
            if(cliente == null){
                System.out.println("El cliente no existe");
                System.out.println("Presione enter para continuar...");
                this.sc.nextLine();
                break;
            }
            System.out.println("Introduce el nombre de la logistica:");
            String nombreLogistica = this.sc.nextLine();
            Logistica logistica = this.logisticas.get(nombreLogistica);
            if(logistica == null){
                System.out.println("La logistica no existe");
                System.out.println("Presione enter para continuar...");
                this.sc.nextLine();
                break;
            }
            System.out.println("Introduzca la distancia:");
            int distancia = this.sc.nextInt();
            this.sc.nextLine();
            LocalDate ahora = LocalDate.now();
            LocalDate fechaEntrega;
            System.out.println("El pedido tiene una fecha de entrega estimada de 10 días, desea cambiarlo? (s/n)");
            System.out.println("*ATENCION*: Una fecha de entrega mayor de 15 días puede suponer un aumento de precio");
            String respuesta = this.sc.nextLine();
            if(respuesta.equals("n")){
                fechaEntrega = ahora.plusDays(10);
            }
            else{
                System.out.println("Introduzca los días de entrega:");
                int dias = this.sc.nextInt();
                this.sc.nextLine();
                fechaEntrega = ahora.plusDays(dias);
            }
           Pedido pedido = this.pedidos.crear(cliente, logistica, producto, cantidad, cliente.getLocalizacion(), distancia, ahora, fechaEntrega);

            System.out.println("Pedido realizado con éxito");
            System.out.println(pedido);
            pedido.setProductores(productoresConProducto);
            pedido.finalizar();
            if(pedido.isFinalizado()){
                logistica.setGanancias(logistica.getGanancias() + pedido.getPrecioLogistica());

                prodIterator = productoresConProducto.iterator();

                while(prodIterator.hasNext()){
                    Productor productor = prodIterator.next();
                    productor.setGanancias(productor.getGanancias() + pedido.getPrecioProductos());
                }

                producto.setVentas(producto.getVentas() + cantidad);
            }
        } while (!salir);
    }
    //////////////////////////////////////////////
    //////////////// PRODUCTORES /////////////////
    //////////////////////////////////////////////

    private void menuProductores() {
        int opcion = 0;
        // Limpiamos la consola:
        this.cleanConsole();
        System.out.println("*********************************");
        System.out.println("*                               *");
        System.out.println("*          Productores          *");
        System.out.println("*                               *");
        System.out.println("*********************************");
        do {
            System.out.println("1. Agregar productor");
            System.out.println("2. Agregar federado");
            System.out.println("3. Eliminar productor");
            System.out.println("4. Añadir cultivo a productor");
            System.out.println("5. Listar productores");
            System.out.println("0. Volver");
            System.out.println("\nIngrese una opcion: ");
            try {
                opcion = sc.nextInt();
                this.sc.nextLine();
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    this.agregarProductor();
                    break;
                case 2:
                    this.agregarProductorFederado();
                    break;
                case 3:
                    this.eliminarProductor();
                    break;
                case 4:
                    this.añadirCultivoAProductor();
                    break;
                case 5:
                    System.out.println(this.productores);
                    System.out.println("Presione enter para continuar...");
                    this.sc.nextLine();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcion != 0);
    }

    private void agregarProductor() {
        String nombre;
        double hectareas;
        this.cleanConsole();
        System.out.println("Ingrese el nombre del productor: ");
        nombre = this.sc.nextLine();
        System.out.println("Ingrese la cantidad de hectareas del productor (con decimales): ");
        try {
            hectareas = this.sc.nextDouble();
            this.productores.crear(nombre, hectareas);
        } catch (Exception e) {
            System.out.println("No se pudo agregar el productor");
        }
    }

    private void agregarProductorFederado() {
        String nombre;
        int productoId;
        boolean salir = false;
        this.cleanConsole();
        System.out.println("Ingrese el nombre del productor: ");
        nombre = this.sc.nextLine();
        do {
            System.out
                    .println("Ingrese el ID del producto o escriba -1 para listar los productos o nada para salir.: ");
            productoId = this.sc.nextInt();
            this.sc.nextLine();
            if (productoId == -1) {
                System.out.println(this.productos);
                System.out.println("Presione enter para continuar...");
                this.sc.nextLine();
            } else if (productoId == 0) {
                salir = true;
            } else {
                ProductorFederado prod = this.productores.crearFederado(nombre, this.productos.get(productoId));
                int productorId = 0;
                do {
                    System.out.println(
                            "Ingrese el ID del productor o escriba -1 para listar los productores o nada para salir.: ");
                    try {
                        productorId = this.sc.nextInt();
                        this.sc.nextLine();
                        if (productorId == -1) {
                            System.out.println(this.productores);
                            System.out.println("Presione enter para continuar...");
                            this.sc.nextLine();
                        } else if (productorId == 0) {
                            salir = true;
                        } else {
                            prod.añadirMiembro(this.productores.get(productorId));
                        }
                    } catch (Exception e) {
                        System.out.println("No se pudo eliminar el productor");
                    }
                } while (!salir);

            }
        } while (!salir);
    }

    private void eliminarProductor() {
        int id = 0;
        boolean salir = false;
        this.cleanConsole();
        do {
            System.out.println(
                    "Ingrese el ID del productor o escriba -1 para listar los productores o nada para salir.: ");
            try {
                id = this.sc.nextInt();
                this.sc.nextLine();
                if (id == -1) {
                    System.out.println(this.productores);
                    System.out.println("Presione enter para continuar...");
                    this.sc.nextLine();
                } else if (id == 0) {
                    salir = true;
                } else {
                    this.productores.eliminar(id);
                }
            } catch (Exception e) {
                System.out.println("No se pudo eliminar el productor");
            }
        } while (!salir);
    }

    private void añadirCultivoAProductor() {
        int productoId = 0;
        int productorId = 0;
        boolean salir = false;
        this.cleanConsole();
        do {
            System.out.println(
                    "Ingrese el ID del productor o escriba -1 para listar los productores o nada para salir.: ");
            try {
                productorId = this.sc.nextInt();
                if (productorId == -1) {
                    System.out.println(this.productores);
                    System.out.println("Presione enter para continuar...");
                    this.sc.nextLine();
                } else if (productorId == 0) {
                    salir = true;
                } else {
                    System.out.println(
                            "Ingrese el ID del producto o escriba -1 para listar los productos o nada para salir.: ");
                    productoId = this.sc.nextInt();
                    if (productoId == -1) {
                        System.out.println(this.productos);
                        System.out.println("Presione enter para continuar...");
                        this.sc.nextLine();
                    } else if (productoId == 0) {
                        salir = true;
                    } else {
                        System.out.println("Ingrese la cantidad de hectareas del cultivo (con decimales): ");
                        try {
                            double hectareas = this.sc.nextDouble();
                            this.productores.get(productorId).añadirPlantacion(this.productos.get(productoId),
                                    hectareas);
                            ;
                        } catch (Exception e) {
                            System.out.println("No se pudo agregar el cultivo");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("No se pudo eliminar el productor");
            }
        } while (!salir);
    }


    //////////////////////////////////////////////
    ////////////////  PRODUCTOS  /////////////////
    //////////////////////////////////////////////

    private void menuProductos() {
        int opcion = 0;
        do {
            this.cleanConsole();
            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Listar productos");
            System.out.println("0. Volver");
            System.out.println("\nIngrese una opcion: ");
            try {
                opcion = sc.nextInt();
                this.sc.nextLine();
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    this.agregarProducto();
                    break;
                case 2:
                    this.eliminarProducto();
                    break;
                case 3:
                    System.out.println(this.productos);
                    System.out.println("Presione enter para continuar...");
                    this.sc.nextLine();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcion != 0);
    }

    private void agregarProducto() {
        String nombre;
        double precio, rendimiento;
        boolean perenne;


        this.cleanConsole();
        System.out.println("Ingrese el nombre del producto: ");
        nombre = this.sc.nextLine();
        System.out.println("Ingrese el precio del producto: ");
        precio = this.sc.nextDouble();
        this.sc.nextLine();
        System.out.println("Ingrese el rendimiento del producto: ");
        rendimiento = this.sc.nextDouble();
        this.sc.nextLine();
        System.out.println("Ingrese si el producto es perenne (true/false): ");
        perenne = this.sc.nextBoolean();
        this.sc.nextLine();

        try {
            this.productos.crear(nombre, rendimiento, perenne, precio);
        } catch (Exception e) {
            System.out.println("No se pudo agregar el producto");
        }
    }

    private void eliminarProducto() {
        int id = 0;
        boolean salir = false;
        this.cleanConsole();
        do {
            System.out.println(
                    "Ingrese el ID del producto o escriba -1 para listar los productos o nada para salir.: ");
            try {
                id = this.sc.nextInt();
                this.sc.nextLine();
                if (id == -1) {
                    System.out.println(this.productos);
                    System.out.println("Presione enter para continuar...");
                    this.sc.nextLine();
                } else if (id == 0) {
                    salir = true;
                } else {
                    this.productos.eliminar(id);
                }
            } catch (Exception e) {
                System.out.println("No se pudo eliminar el producto");
            }
        } while (!salir);
    }

    //////////////////////////////////////////////
    //////////////// CLIENTES ////////////////////
    //////////////////////////////////////////////

    private void menuClientes() {
        int opcion = 0;
        do {
            this.cleanConsole();
            System.out.println("1. Agregar cliente");
            System.out.println("2. Eliminar cliente");
            System.out.println("3. Listar clientes");
            System.out.println("0. Volver");
            System.out.println("\nIngrese una opcion: ");
            try {
                opcion = sc.nextInt();
                this.sc.nextLine();
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    this.agregarCliente();
                    break;
                case 2:
                    this.eliminarCliente();
                    break;
                case 3:
                    System.out.println(this.clientes);
                    System.out.println("Presione enter para continuar...");
                    this.sc.nextLine();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcion != 0);
    }

    private void agregarCliente() {
        String nombre;
        TipoCliente tipo;
        String localizacion;

        this.cleanConsole();
        System.out.println("Ingrese el nombre del cliente: ");
        nombre = this.sc.nextLine();
        System.out.println("Ingrese el tipo del cliente (CONSUMIDOR/DISTRIBUIDOR): ");
        tipo = TipoCliente.valueOf(this.sc.nextLine().toUpperCase());
        System.out.println("Ingrese la localizacion del cliente: ");
        localizacion = this.sc.nextLine();

        try {
            this.clientes.crear(nombre, tipo, localizacion);
        } catch (Exception e) {
            System.out.println("No se pudo agregar el cliente");
        }
    }

    private void eliminarCliente() {
        int id = 0;
        boolean salir = false;
        this.cleanConsole();
        do {
            System.out.println(
                    "Ingrese el ID del cliente o escriba -1 para listar los clientes o nada para salir.: ");
            try {
                id = this.sc.nextInt();
                this.sc.nextLine();
                if (id == -1) {
                    System.out.println(this.clientes);
                    System.out.println("Presione enter para continuar...");
                    this.sc.nextLine();
                } else if (id == 0) {
                    salir = true;
                } else {
                    this.clientes.eliminar(id);
                }
            } catch (Exception e) {
                System.out.println("No se pudo eliminar el cliente");
            }
        } while (!salir);
    }

    //////////////////////////////////////////////
    /////////////// LOGISTICA ////////////////////
    //////////////////////////////////////////////

    private void menuLogistica() {
        int opcion = 0;
        do {
            this.cleanConsole();
            System.out.println("1. Agregar transporte");
            System.out.println("2. Eliminar transporte");
            System.out.println("3. Listar transportes");
            System.out.println("0. Volver");
            System.out.println("\nIngrese una opcion: ");
            try {
                opcion = sc.nextInt();
                this.sc.nextLine();
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    this.agregarTransporte();
                    break;
                case 2:
                    this.eliminarTransporte();
                    break;
                case 3:
                    System.out.println(this.logisticas);
                    System.out.println("Presione enter para continuar...");
                    this.sc.nextLine();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcion != 0);
    }

    private void agregarTransporte() {
        String nombre;
        double precioGran;
        double precioPequeña;

        this.cleanConsole();
        System.out.println("Ingrese el nombre de la logistica: ");
        nombre = this.sc.nextLine();
        System.out.println("Ingrese el precio de la gran logistica: ");
        precioGran = this.sc.nextDouble();
        this.sc.nextLine();
        System.out.println("Ingrese el precio de la pequeña logistica: ");
        precioPequeña = this.sc.nextDouble();
        this.sc.nextLine();
        try {
            this.logisticas.crear(nombre, precioGran, precioPequeña);
        } catch (Exception e) {
            System.out.println("No se pudo agregar el transporte");
        }
    }

    private void eliminarTransporte() {
        int id = 0;
        boolean salir = false;
        this.cleanConsole();
        do {
            System.out.println(
                    "Ingrese el ID del transporte o escriba -1 para listar los transportes o nada para salir.: ");
            try {
                id = this.sc.nextInt();
                this.sc.nextLine();
                if (id == -1) {
                    System.out.println(this.logisticas);
                    System.out.println("Presione enter para continuar...");
                    this.sc.nextLine();
                } else if (id == 0) {
                    salir = true;
                } else {
                    this.logisticas.eliminar(id);
                }
            } catch (Exception e) {
                System.out.println("No se pudo eliminar el transporte");
            }
        } while (!salir);
    }



}



