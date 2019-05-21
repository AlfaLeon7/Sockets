package ej3;


//It contains two classes : Server and ClientHandler 
//Save file as Server.java 

import java.io.*; 
import java.net.*; 

//Server class 
public class Servidor { 
	public static void main(String[] args) throws IOException { 
		// el servidor va a escuchar por el puerto 4444
		ServerSocket socketServidor = new ServerSocket(4444); 
		
	
		System.out.println("Iniciado el servidor");
		//bucle infinito esperando peticiones de los clientes
		while (true){ 
			Socket socketCliente = null; 
			
			try{ 
 
				//se acepta la conexion del cliente con el servidor
				socketCliente = socketServidor.accept(); 
				
				System.out.println("Nuevo cliente : " + socketCliente); 
				
				// se establecen los canales de entrada y salida
				DataInputStream entrada = new DataInputStream(socketCliente.getInputStream()); 
				DataOutputStream salida = new DataOutputStream(socketCliente.getOutputStream()); 
				
				System.out.println("Asignando un nuevo hilo al cliente"); 

				
				//se crea el hilo
				new Thread(new AtiendeACliente(socketCliente, entrada, salida)).start();
				
				
				
			} 
			catch (Exception e){ 
				socketCliente.close(); 
				
				
			} 
		} 
	} 
} 

