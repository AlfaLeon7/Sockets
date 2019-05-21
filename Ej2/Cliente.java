/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author AlfaLeon
 */
public class Cliente {
    public static void main (String[] args) throws IOException{
 DataInputStream input;
 BufferedInputStream bis;
 BufferedOutputStream bos;
 Scanner fichero = new Scanner(System.in);
 int in;
 byte[] byteArray;
 //Fichero a transferir
 String filename = "";
 
 filename = fichero.next();
 
try{
 final File localFile = new File( filename );
 if(!localFile.exists()){
     System.out.println("Error 1: el fichero no existe");
     System.exit(0);
 }else{
     System.out.println("0. Fichero existente." + "\n" + localFile.getAbsolutePath() + "\n" + localFile.getName() + 
             "\n" + localFile.canRead() + "\n" + localFile.getTotalSpace());
     
  Socket client = new Socket("localhost", 5000);
 bis = new BufferedInputStream(new FileInputStream(localFile));
 bos = new BufferedOutputStream(client.getOutputStream());
 //Enviamos el nombre del fichero
 DataOutputStream dos=new DataOutputStream(client.getOutputStream());
 dos.writeUTF(localFile.getName());
 //Enviamos el fichero
 byteArray = new byte[8192];
 while ((in = bis.read(byteArray)) != -1){
 bos.write(byteArray,0,in);
 }
 
 
bis.close();
 bos.close();
 }

}catch ( Exception e ) {
 System.err.println(e);
 }

 }

}
