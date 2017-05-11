package ec.edu.epn.redes.cs.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerTCP {
	//puerto del servidor
	private static int PORT=9095; 
	
	//Runs the server
	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket= new ServerSocket(PORT); //Va a esperar o estar activo en un puerto local y es un socket diferente: serverSocket 
		int a,b,c; //creamos las variables donde guardaremos el valor de los numeros obtenidos
		String d; //creamos la variable donde 
		
		System.out.println("Server listening on port "+PORT);
		
		
		//********codificacion para el server dedicado******
		try{
			
				Socket socket1 =serverSocket.accept();
				//el server Socket es local, entonces creamos este otro socket para poder comunicarnos(Response) remotamente;
				//y como ya tenemos el puerto entonces vamos aceptar la comunicacion remota
				try{
					//declaramos e instanciamos el objeto DataInputStream
					//la cual nos permitira recibir datos del cliente
					DataInputStream d1=new DataInputStream(socket1.getInputStream());
					d=d1.readUTF(); //asignamos el string recibido a la variable d. 
					
					StringTokenizer token= new StringTokenizer(d,"+");//Dividimos el string en substrings en base al delimitador en este caso +
					a=Integer.parseInt(token.nextToken());//convertimos el primer numero a entero
					b=Integer.parseInt(token.nextToken());//convertimes el segundo numero a entero
					System.out.println("Primer numero "+a);//Imprimimos en consola el primer numero
					System.out.println("Segundo numero "+b);//Imprimimos en consola el segundo numero
					
					c=a+b;//Se realiza la suma de los numeros obtenidos por el cliente
					
					//PrintWriter: escibe datos hacia algun objeto de salida (puede ser disco, pantalla o socket)
					PrintWriter out= new PrintWriter(socket1.getOutputStream(),true);
					out.println("La suma de los numeros ingresados es: "+c);//Escribir el mensaje que vamos a enviar al cliente, en este caso la suma.
				
					}finally{
					socket1.close();
					}	
		}
		finally {
			serverSocket.close(); //cerramos los sockets( ya que si no se los cierra consumen recursos innecesarios. 
		}
	}
	
	
}
