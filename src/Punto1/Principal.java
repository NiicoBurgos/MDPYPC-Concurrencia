package Punto1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

///////////////////////////////////////
////////////Punto 1 OK////////////////
/////////////////////////////////////

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		
		List<Integer>simples = new ArrayList<>();		//Lista para guardar los menu simples generados
		List<Integer>postres = new ArrayList<>();		//Lista para guardar los menu con postres generados
		Semaphore espera = new Semaphore(1);			//Semaforo para que no se solapen los productos al generarse
		Semaphore cliente = new Semaphore(0);			//Semaforo para que los clientes esperen si no hay un menu del tipo que desean
		int i=1;										//Identificador de cliente
		Random ran = new Random();		
		Pedidos productor = new Pedidos(simples, postres, espera, cliente);
		Thread hiloProductor = new Thread(productor);
		hiloProductor.start();				//Ya posee un while(true) en su estructura así que lo lanzo una vez
		
		while(true) {
			Cliente consumidor = new Cliente (simples, postres, i, cliente);
			Thread hiloConsumidor = new Thread(consumidor);
			hiloConsumidor.start();
			i++;
			Thread.sleep(ran.nextInt(501)+1000);	//Aplico sleep para que lleguen cada determinado tiempo entre			
		}											//1000 y 1500 ms
	}

}
