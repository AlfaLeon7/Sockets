package ej3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AtiendeACliente implements Runnable { 
	
	final DataInputStream entrada; 
	final DataOutputStream salida; 
	final Socket cliente; 
	FileOutputStream archivo=null;
	byte resultado=1;
	long longitud=0;

	// Constructor de la clase
	public AtiendeACliente(Socket cliente, DataInputStream entrada, DataOutputStream salida) { 
		this.cliente = cliente; 
		this.entrada = entrada; 
		this.salida = salida; 
	} 

	@Override
	public void run() { 
		while (true) { 
			try { 

				while (true) {
					//envio el mensaje de bienvenida
					salida.writeUTF(
							"- Cliente descarga de ficheros -\n(Deje en blanco para finalizar)\nRuta de fichero en servidor");
					//
					//recibo la ruta del archivo del cliente
					String ruta = entrada.readUTF();
					//
					File file = new File(ruta);
					System.out.println("LONGITUD DEL ARCHIVO: " + file.length());
					if (!file.exists()) {

						salida.writeByte(resultado);
						salida.writeUTF("NO EXISTE EL ARCHIVO");

					} else {
						resultado = 0;

						salida.writeByte(resultado);
						salida.writeUTF("Longitud del archivo: " + file.length() + "\nElija el destino");

						System.out.println(ruta);
						
						//Creo el archivo en destino
						archivo = new FileOutputStream(entrada.readUTF());
						
						byte[] buffer = new byte[4096];

						while (true) {

							longitud = entrada.read(buffer);

							if (longitud == -1) {
								break;
							}

							archivo.write(buffer, 0, (int) longitud);
						}
						archivo.close();
						System.out.println("Transferencia completada\n");

					} 
				}
			} catch (IOException e) { 
				
			} 
		} 
		
		
	} 
} 
