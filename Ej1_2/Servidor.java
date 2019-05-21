/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej1_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author AlfaLeon
 */
public class Servidor {

    public static void main(String[] args) throws SocketException, IOException {
        //Crear socket para escuchar al puerto 5948
        DatagramSocket ds = new DatagramSocket(5948);
        String linea_recibida = null;
        InetAddress ip = InetAddress.getLocalHost();
        String operacion = null;
        long resultado = 0;
        
        byte[] recibir = new byte[65000];
        

        while (true) {
            DatagramPacket dprecibir = new DatagramPacket(recibir, recibir.length);

            System.out.println("Esperando paquete ...");
            //recibir en el datagrampacket
            ds.receive(dprecibir);
            System.out.println("Procesar datos");
            String linearecibida[] = datos(recibir).toString().split(" ");
            String operador = linearecibida[0];
            long numero1 = Long.valueOf(linearecibida[1]);
            long numero2 = Long.valueOf(linearecibida[2]);
            switch(linearecibida[0]){
                case "+":
                    operacion = "Suma";
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    operacion = "Resta";
                    resultado = numero1 - numero2;
                    break;
                case "*":
                    operacion = "Multiplicación";
                    resultado = numero1 * numero2;
                    break;
                case "/":
                    operacion = "Divisón";
                    resultado = numero1 / numero2;
                    break;
                    
                    
            }
            System.out.println("Cliente: " + datos(recibir));
                DatagramPacket respuesta = new DatagramPacket(recibir, recibir.length, ip, 5948);
                recibir = operacion.getBytes();
            System.out.println("La " + operacion + " entre " + numero1 + " y " + numero2 + " es " + resultado);
            ds.send(respuesta);
            

            
            
            

            //Liberar el buffer
            ds.close();
            System.exit(0);
            recibir = new byte[65000];
            
        }
     
    }
    

    //Método para convertir el array de byte en un string
    private static StringBuilder datos(byte[] a) {
        if (a == null) {
            return null;
        }
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
    
    
}
