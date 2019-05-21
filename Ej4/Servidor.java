/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author AlfaLeon
 */
public class Servidor {
    public static void main(String[] args) throws IOException {
DatagramSocket dsoc = new DatagramSocket(10000);

File f1 = new File("d:\\aaaa.bmp");
//f1.createNewFile();
FileOutputStream bos = new FileOutputStream(f1);
byte[] buf = new byte[63*1024];
DatagramPacket pkg = new DatagramPacket(buf, buf.length);

while(true)
{
dsoc.receive(pkg);
if (new String(pkg.getData(), 0, pkg.getLength()).equals("end"))
{
System.out.println("Informacion copiada");
bos.close();
dsoc.close();
break;
}
bos.write(pkg.getData(), 0, pkg.getLength());
bos.flush();
}
bos.close();
dsoc.close();
}
}
