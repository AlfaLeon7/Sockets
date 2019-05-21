package ej1_3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) throws IOException {

		Cliente cliente = new Cliente();
		cliente.run();
	}

	public void run() throws IOException {

		final int PUERTO = 4444;
		final String SERVER = "127.0.0.1";

		Socket socketCliente = null;
		DataInputStream recibe = null;
		DataOutputStream envia = null;

		try {
			socketCliente = new Socket(SERVER, PUERTO);
			recibe = new DataInputStream(socketCliente.getInputStream());
			envia = new DataOutputStream(socketCliente.getOutputStream());
		} catch (IOException e) {
			System.err.println("No es posible establecer canales de entrada/salida para la conexi�n");
			System.exit(-1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		String linea;

		try {
			while (true) {
				System.out.println(
						"- Cliente Operaciones Matem�ticas -\nSeleccione operador o" + " comando (+,-,*,/,F,A):");

				char operacion = stdIn.readLine().charAt(0);
				envia.writeChar(operacion);
				if (operacion == 'F' || operacion == 'f') {
					socketCliente.close();
					System.out.println("**Finalizada la conexi�n**");
					break;
				} else {
					System.out.println("N�mero 1: ");

					long num1 = Long.parseLong(stdIn.readLine());
					envia.writeLong(num1);
					System.out.println("N�mero 2: ");

					long num2 = Long.parseLong(stdIn.readLine());
					envia.writeLong(num2);
				}

				System.out.println("Respuesta del servidor:");
				linea = recibe.readUTF();
				System.out.println(linea + "\n");
				
			

			}

		} catch (IOException e) {
			System.out.println("Error: El servidor ha abortado la conexion");
			System.out.println(e.getMessage());
		}

		envia.close();
		recibe.close();
		stdIn.close();

		socketCliente.close();
	}

}
