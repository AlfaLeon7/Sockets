/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej1_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author AlfaLeon
 */
public class Cliente {
    public static void main(String[]args) throws SocketException, UnknownHostException, IOException{
        Scanner input = new Scanner(System.in);
        //Crear el socket
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        String mensaje = null;
        byte buf [] = null;
        
        while(true){
            System.out.println("Introduzca operación a realizar y dos números para operar");
            String entrada = input.nextLine();
            
            //Convertir la entrada en un array de bytes
            
           buf = entrada.getBytes();
           
           //Crear el datagrampacket para enviar los datos al servidor
            DatagramPacket dgp = new DatagramPacket(buf, buf.length, ip, 5948);
           
           ds.send(dgp);
           
            DatagramPacket peticion = new DatagramPacket(buf, buf.length);
 
            //Recibo la respuesta
            ds.receive(peticion);
            System.out.println("Recibo la peticion");
 
            //Cojo los datos y lo muestro
            mensaje = new String(peticion.getData());
            System.out.println(mensaje);
 
            //cierro el socket
            ds.close();
           
           if(entrada.equals("salir")){
               break;
           }
        }
    }
}
