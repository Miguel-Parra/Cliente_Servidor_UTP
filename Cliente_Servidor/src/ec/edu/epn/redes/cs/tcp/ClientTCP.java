package ec.edu.epn.redes.cs.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


import javax.swing.JOptionPane;

public class ClientTCP {
	//Puerto del servidor al que nos vamos a conectar
	private static int SERVER_PORT=9095;
	
	//Runs the client
	public static void main(String[] args) throws  IOException {
		
		//Ingreso de la direccion IP de la maquina que sera nuestro server.
		String serverAdress= JOptionPane.showInputDialog("Enter IP Address of a machine that is\n"+
				"running the date on port " + SERVER_PORT); 
		
		//Establece la conexion con el servidor mediante un socket:
		Socket clientSocket =new Socket(serverAdress, SERVER_PORT);//se crea un objeto Socket
		
		String linea =JOptionPane.showInputDialog("Ingrese el primer número"); //Creamos una variable donde guardaremos los numeros para la suma
		linea=linea+"+"+JOptionPane.showInputDialog("Ingrese el segundo número");//Ingresamos el segundo numero en el contenido de la variable linea
		
		//Declaramos e instanciamos el objeto DataOutputStream
		//el cual nos servira para enviar datos al servidor destino
		DataOutputStream dos1= new DataOutputStream(clientSocket.getOutputStream());
		dos1.writeUTF(linea);//Enviamos el String de la variable linea 
		

		//obtiene el mensaje enviado por el servidor a traves del socket
		InputStreamReader inputStream= new InputStreamReader (clientSocket.getInputStream());
		
		//el socket del cliente recibe el flujo y para poder interpretar debomos programas lo siguiente:
		
		//lee los datos del mensaje
		BufferedReader input = new BufferedReader(inputStream);
		String answer=input.readLine();
		
		//Imprime los datos del mensaje
		//
		
		JOptionPane.showMessageDialog(null, answer);
		
		System.exit(0);
	}

}
