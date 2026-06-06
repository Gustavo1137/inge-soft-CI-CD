import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instancia;

    private static final String GRIS = "\u001B[90m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARILLO = "\u001B[33m";
    private static final String ROJO = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    //Constructor
    private Logger(){
    }


    /*
    Inicializamos el objeto instancia en caso que no exista.
    Una vez creado el objeto dura toda la vida del programa.
    Retorna el objeto logger
    */
    public static Logger getInstance(){
        if(instancia == null){
            instancia = new Logger();
        }
        return instancia;
    }

    public void logError(String msg){ imprimir(ROJO, "[ERROR]" ,msg);}

    public void logInfo(String msg){  imprimir(GRIS, "[INFO]", msg); }

    public void logDebug(String msg){ imprimir(VERDE, "[DEBUG]", msg); }

    public void logWarning(String msg){ imprimir(AMARILLO, "[WARN]" , msg);}

    /*
    Muestra cuando ocurrio el error
    Por ejemplo: 2014-07-02 20:52:39.345 DEBUG msg
    */
    public void imprimir(String color, String pref, String msg){
        System.out.println(color + getTimeFormat() + " " + pref + " " + msg + RESET);
    }

    /*
     *One solution
     *Show the timestamp
     */

    private String getTimeFormat(){
        ZonedDateTime aux = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String tiempo = aux.format(formato);
        return tiempo;
    }

}