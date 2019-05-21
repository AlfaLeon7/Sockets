package ej1_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AtiendeACliente implements Runnable { 
	
	final DataInputStream recibe; 
	final DataOutputStream envia; 
	final Socket cliente; 
	FileOutputStream archivo=null;
	byte resultado=1;
	long longitud=0;

	// Constructor de la clase
	public AtiendeACliente(Socket cliente, DataInputStream entrada, DataOutputStream salida) { 
		this.cliente = cliente; 
		this.recibe = entrada; 
		this.envia = salida; 
	} 

	@Override
	public void run() { 
		while (true) { 
			try { 

				int contador = 1;
				while (true) {

					char operacion = recibe.readChar();
					
					if(operacion=='a'||operacion=='A') {
						
						
						envia.writeUTF("Operaci�n abortada");
						
						break;
					}
					
					
					long num1 = recibe.readLong();
					long num2 = recibe.readLong();

					System.out.println("Mensaje de cliente " + cliente + ": " + operacion + " " + num1 + " " + num2);
					double resultado = 0;
					switch (operacion) {
					case '+':
						resultado = num1 + num2;

						salida(envia, contador, operacion, num1, num2, resultado);
						break;
					case '-':
						resultado = num1 - num2;
						salida(envia, contador, operacion, num1, num2, resultado);
						break;
					case '*':
						resultado = num1 * num2;
						salida(envia, contador, operacion, num1, num2, resultado);
						break;
					case '/':
						if (num2 == 0) {
							envia.writeUTF("No es posible dividir entre 0");
						} else {
							resultado = (double) num1 / num2;
							salida(envia, contador, operacion, num1, num2, resultado);
						}
						break;
					default:
						envia.writeUTF("No ha marcado ninguna operaci�n v�lida");
						break;
					}

					contador++;

				}

			} catch (IOException e) { 
				
			} 
		} 
		
		
	} 
	public void salida(DataOutputStream envia, int contador, char operacion, long num1, long num2, double resultado)
			throws IOException {
		envia.writeUTF("N�mero de operaci�n: " + contador + " Resultado: " + resultado + " Operaci�n realizada: "
				+ num1 + " " + operacion + " " + num2);
		
		
	}
} 
