/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author AlfaLeon
 */
public class Servidor {



public static void main(String args[]) {

// Socket que iniciará nuestro servidor
ServerSocket servidor = null;
// donde almacenaremos el mensaje recibido
String linea_recibida;
// para la recepcion de datos
BufferedReader entrada;
// para el envio de datos
PrintStream salida;
// socket para aceptar conexiones de los clientes
Socket socket_conectado = null;

// variables numericas para los calculos de las operaciones
float resultado = 0;
float numero1 = 0;
float numero2 = 0;
// contador para sumar las operaciones realizadas en el servidor
int contador = 0;


try {
servidor = new ServerSocket(5948);
} catch (IOException excepcion) {
System.out.println(excepcion);
}

// bucle principal del programa (a la espera de un cliente)
try {
boolean salir = false;
while (!salir) {

// esperamos una conexion del cliente
socket_conectado = servidor.accept();
//conectamos la entrada y la salida con el cliente
entrada = new BufferedReader(new InputStreamReader(socket_conectado.getInputStream()));
salida = new PrintStream(socket_conectado.getOutputStream());

//recibimos una linea string
linea_recibida = entrada.readLine();
//realizamos la separacion de las partes(operando y numeros a operar)
String linea[] = linea_recibida.split(" ");
//string para almacenar la operacionan pedida para concadenarla
//en el mensaje
String operacion = "";

//en caso de tres datos recibidos
if (linea.length == 3) {

//extraemos los numeros para operar
numero1 = Float.valueOf(linea[1]);
numero2 = Float.valueOf(linea[2]);

//segun el operando recibido realizamos dicha operacion
switch (linea[0]) {
case "+":
operacion = "Suma";
resultado = numero1 + numero2;
contador++;
break;
case "*":
operacion = "Multiplicacion";
resultado = numero1 * numero2;
contador++;
break;
case "/":
operacion = "Division";
resultado = numero1 / numero2;
break;
case "-":
operacion = "Resta";
resultado = numero1 - numero2;
contador++;
break;
case "F":
    operacion = "Salir del servidor";
    System.out.println("Gracias por usar la calculadora online");
    socket_conectado.close();
    break;
case "A":
    operacion = "El servidor ha cerrado";
    System.out.println("El servidor ha sido suspendido");
    servidor.close();

}
}  else if (linea.length == 1) {
if(linea[0]=="F"){
    System.out.println("Te has desconectado");
    socket_conectado.close();
    
}else if(linea[0]=="A"){
    System.out.println("Has cerrado la conexión con el servido");
    servidor.close();
}
}

// mensaje que enviamos al cliente con el resultado
if (salir) {
salida.println("Apagando el servidor, ¡hasta luego!");
} else {
salida.println("La "+operacion+" de "+numero1+
" y "+numero2+" da como resultado: " + resultado +  "\n Nºoperaciones = " + contador);
}

// cerramos los buffer de conexion con el cliente
salida.close();
entrada.close();
}

// cerramos el socket para cerrar el servidor
socket_conectado.close();

} catch (IOException excepcion) {
System.out.println(excepcion);
}
}
}