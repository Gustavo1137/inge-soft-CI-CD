import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Logger log = Logger.getInstance();              //Creo la instancia logger (primera letra con minuscula) llamada log
        log.logInfo("Iniciando");


        TransportStrategy Taxi = new Taxi();                      //Creo la instancia de la estrategia Taxi, Colectivo y Bicicleta (primera letra con mayuscula) llamada Taxi
        TransportStrategy Colectivo = new Colectivo();  
        TransportStrategy Bicicleta = new Bicicleta();  
        
        TransportMonitor monitor = new TransportMonitor(Taxi);

        ConsolePrinter console = new ConsolePrinter();
        AlertObserver alerta = new AlertObserver(10000.0 , 20);

        monitor.subscribe(console);
        monitor.subscribe(alerta);

        Thread hiloMonitor= new Thread(()-> {
            try {
                monitor.start(5000);
            } catch (InterruptedException e) {
                log.logError("El hilo se interrumpio");
            }
        });
        hiloMonitor.start();
        
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        while(opcion != 6){

            System.out.println("Menu de Estrategias");
            System.out.println("1- Seleccionar Taxi");
            System.out.println("2- Seleccionar Colectivo");
            System.out.println("3- Seleccionar Bicicleta");
            System.out.println("4- Salir del Menu");
            System.out.println("5- Iniciar programa");
            System.out.println("Elija alguna opcion con el teclado");
            
            opcion = scanner.nextInt();

            switch(opcion) {
                case 1:
                    monitor.setStrategy(Taxi);
                    log.logInfo("Se cambio de estrategia a: Taxi");
                    break;
                case 2:
                    monitor.setStrategy(Colectivo);
                    log.logInfo("Se cambio de estrategia a: Colectivo");
                    break;
                case 3:
                    monitor.setStrategy(Bicicleta);
                    log.logInfo("Se cambio de estrategia a: Bicicleta");
                    break;
                case 4:
                    log.logInfo("Saliendo del programa");
                    monitor.stop();
                    break;
                case 5:
                    Thread hiloMonitor_2= new Thread(()-> {
                        try {
                            monitor.start(5000);
                        } catch (InterruptedException e) {
                            log.logError("El hilo se interrumpio");
                        }
                    });
                    hiloMonitor_2.start();
                    break;
                default:
                    log.logWarning("Opcion Invalida, Intentelo otra vez");
            }
        }   
        scanner.close();
    }
} //lo pruebo con intell j