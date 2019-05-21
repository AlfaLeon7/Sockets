/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author AlfaLeon
 */
public class Cliente {
   public static void main(String[] args) throws IOException {
DatagramSocket send = new DatagramSocket(10010,InetAddress.getByName("127.0.0.1"));
Scanner input = new Scanner(System.in);
String ruta = input.next();
System.out.println("Introduzca la ruta del archivo origen");
File f2 = new File(ruta);

FileInputStream bis = new FileInputStream(f2);
byte[] buf = new byte[63*1024];
int len;

DatagramPacket pkg = new DatagramPacket(buf, buf.length,InetAddress.getByName("127.0.0.1"),100000);
while((len=bis.read(buf))!=-1)
{
send.send(pkg);
}
buf = "end".getBytes();
DatagramPacket endpkg = new DatagramPacket(buf, buf.length,InetAddress.getByName("127.0.0.1"),10000);
System.out.println("Archivo enviado.");
send.send(endpkg);
bis.close();
send.close();
   }
}
