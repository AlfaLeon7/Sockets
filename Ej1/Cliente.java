/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej1;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

public static void main(String[] args) {

//socket para conectarnos con el servidor
Socket cliente = null;
String num1= null;
String num2=null;

//Clases para recibir y mandar informacion con el servidor
BufferedReader entrada = null;
DataOutputStream salida = null;


String ipServidor = "localhost";

try {
// conectamos con el servidor con la ip y el puerto definidos
cliente = new Socket(ipServidor, 5948);
//conexion para recepcion de datos
entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
//conexion para envio de datos
salida = new DataOutputStream(cliente.getOutputStream());

} catch (UnknownHostException excepcion) {
System.err.println("EL servidor no está encendido");
} catch (Exception e) {
System.err.println("Error: " + e);
}

//envio de la orden
try {
//linea que vamos a recibir del servidor con el resultado
String linea_recibida;



Scanner input = new Scanner(System.in);

    System.out.println("Escriba la operación que quiere realizar: (+,-+/,*,F,A) y los dos números que van a operar, separados por un espacio");
String operacion = input.nextLine();
//String lineas[] = operacion.split(" ");
System.out.println(operacion);

 salida.writeBytes(operacion+"\n"); 
   




//recibimos el resultado de la operacion
linea_recibida = entrada.readLine();
// lo mostramos por consola
System.out.println("MENSAJE DEL SERVIDOR: " + linea_recibida);

// cerramos los sockets y los buffers de conexion
salida.close();
entrada.close();
cliente.close();

} catch (UnknownHostException excepcion) {
System.err.println("No se puede encontrar el servidor, en la dirección" + ipServidor);
} catch (IOException excepcion) {
System.err.println("Error de entrada/salida");
} catch (Exception e) {
System.err.println("Error: " + e);

}
}
}

