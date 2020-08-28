package Punto1;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente implements Runnable{
	private String tipo;
	private List<Integer> simples;
	private List<Integer> postres;
	private Semaphore cliente;
	private int i;
	
	public Cliente(List<Integer> simples, List<Integer> postres, int i, Semaphore cliente) {
		this.simples=simples;
		this.postres=postres;
		this.i=i;
		this.cliente=cliente;
	}
	
	@Override
	public void run() {
		Random ran = new Random();
		obtenerTipo(ran.nextInt(2));													//Obtengo un tipo aleatorio de menu
		System.out.println("*Llega el cliente "+i+" y pide un menu de tipo: "+tipo+"*");				
		try {
			if(tipo=="Simple") {
				while(simples.isEmpty()) {									//Si no hay menus simple disponibles el cliente espera
					System.out.println("*Cliente "+i+" esperando*");		
					cliente.acquire();										//Semaforo que es liberado en la clase Pedidos
				}
			}else {
				while(postres.isEmpty()) {									//Si no hay menus con postre disponibles el cliente espera
					System.out.println("*Cliente "+i+" esperando*");
					cliente.acquire();										//Semaforo que es liberado en la clase Pedidos
				}
			}
			System.out.println("*Cliente "+i+" siendo atendido*");			//Si hay el menu que pidió, es atendido
			if (tipo=="Simple") {	
				simples.remove(0);											//Quito de la lista un elemento de tipo menu simple
			}else {
				postres.remove(0);											//Quito de la lista un elemento de tipo menu con postre
			}
			Thread.sleep(ran.nextInt(301)+100);
			System.out.println("*Cliente "+i+" se retira*");
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
			
	
	}
	
	public void obtenerTipo(int m){						//Metodo para obtener un tipo de menu aleatorio
		if(m==0) {
			tipo="Simple";
		}else {
			tipo="Postre";
		}
	}


}
