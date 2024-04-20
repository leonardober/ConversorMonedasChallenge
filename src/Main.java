import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {            //SE AGREGA LA EXCEPCION DE SEND
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("bienvenido al convertidor Alura!");
        //Creamos Menu en un ciclo infinito
        EXTERNA:
        while (true){
            System.out.println("CONVERSOR DE MONEDAS");
            System.out.println("1. Dollar a Peso Argentino");

            System.out.println("2. Dollar a Real Brasileño");

            System.out.println("3. Dollar a Peso Colombiano");

            System.out.println("4. Salir");

            System.out.print("Elige una opción: ");
            Scanner leer = new Scanner(System.in);
            char opcion = leer.next().charAt(0);

            switch (opcion){
                case '1':
                    convertir(871.25, "Argentino Peso");
                    break;
                case '2':
                    convertir(5.20,"Brasileño Real");
                    break;
                case '3':
                    convertir(3909.07,"Colombiano Peso");
                    break;
                case '4':
                    System.out.println("Salir del Programa");
                    break EXTERNA;
                default:
                    System.out.println("OPCION ERRADA");
                    break;

            }
        }
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/51ce989f4d4a09775b8c0dcd/latest/USD"))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
          //Funcion/Metodo convertidor de Moneda Dolar reutilizable
    static void convertir(double valorDolar, String pais){
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el valor de %s" + pais);
        double cantidadMoneda = leer.nextDouble();
        double dolares = cantidadMoneda / valorDolar;
        //usamos dos decimales
        dolares = (double) Math.round(dolares * 100d / 100);

        System.out.println("******************************");
        System.out.println("******Posees $: " + dolares + "Dolares en total****");
        System.out.println("******************************");
    }
}