package ej3;

//Java implementation for a client 
//Save file as Client.java 

import java.io.*;
import java.net.*;
import java.util.Scanner;




public class Cliente {
	
	public static void main(String[] args) throws IOException {
		Cliente cliente = new Cliente();

		cliente.run();

	}

	private void run() {

		final int PUERTO = 4444;
		final String SERVIDOR = "localhost";

		Socket socket = null;

		Scanner sc = new Scanner(System.in);
		DataInputStream entrada = null;
		DataOutputStream salida = null;
		FileInputStream archivo = null;

		try {
			
			while (true) {
				
				socket = new Socket(SERVIDOR, PUERTO);
				salida = new DataOutputStream(socket.getOutputStream());
				entrada = new DataInputStream(socket.getInputStream());
				// recibo el mensaje de bienvenida
				System.out.println(entrada.readUTF());
				//
				String ruta = sc.nextLine();
				if(ruta.equals("")) {
					salida.close();
					entrada.close();
					socket.close();
					System.exit(-1);
				}
				// envio la ruta del archivo al servidor
				salida.writeUTF(ruta);
				//
				byte resultado = entrada.readByte();
				if (resultado == 1) {
					System.out.println("RESULTADO: " + resultado);
					System.out.println("ERROR: "+entrada.readUTF());
					
				} else {
					System.out.println("RESULTADO: "+resultado);

					// imprimo la ruta donde esta el archivo
					System.out.println(entrada.readUTF());
					//

					// le mando el destino del archivo al servidor
					salida.writeUTF(sc.nextLine());
					//

					archivo = new FileInputStream(ruta);
					long longitud = 0;
					byte[] buffer = new byte[4096];

					while (true) {

						longitud = archivo.read(buffer);

						if (longitud == -1) {
							break;
						}
						salida.write(buffer, 0, (int) longitud);
					}
					System.out.println("transferencia completada");
					archivo.close();
				}
				
				salida.close();
				entrada.close();
				
			}

		} catch (UnknownHostException e) {
			System.out.println("ERROR 1: " + e.getMessage());
			
		} catch (IOException e) {
			System.out.println("ERROR 2: " + e.getMessage());
			
		}

		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("ERROR 3: " + e.getMessage());
		}
		sc.close();
	}
}
