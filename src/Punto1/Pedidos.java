package Punto1;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Pedidos implements Runnable{
private String tipo;
private List<Integer> simples;
private List<Integer> postres;
private Semaphore espera; 
private Semaphore cliente;

public Pedidos(List<Integer> simples, List<Integer> postres, Semaphore esp, Semaphore cli) {
	this.simples=simples;
	this.postres=postres;
	this.espera=esp;
	this.cliente=cli;
}


	@Override
	public void run() {
		while(true) {						//Para que esté constantemente produciendo
		Random ran = new Random();
		obtenerTipo(ran.nextInt(2));		//Obtengo un producto aleatorio entre menu simple y con postre
		try {
			if (tipo=="Simple") {
				Thread.sleep(ran.nextInt(201)+300);
				espera.acquire();										//Seccion critica  
				simples.add(1);											//para que no se produzcan dos menus simples 
				System.out.println("+Se ha producido un menu simple");	//al mismo tiempo
				espera.release();										//Fin Seccion critica
				cliente.release();										//Libero este semaforo para que el cliente se fije 
			}else {														//Si se generó el menú que pidió
				Thread.sleep(ran.nextInt(201)+400);	
				espera.acquire();										//Seccion critica 
				postres.add(1);											//para que no se produzcan dos menus con postre
				System.out.println("-Se ha producido un menu postre");	//al mismo tiempo
				espera.release();										//Fin Seccion critica
				cliente.release();										//Libero este semaforo para que el cliente se fije 
			}															//Si se generó el menú que pidió	
		} catch (InterruptedException e) {		
			e.printStackTrace();
		}
		}
	}
	
	public void obtenerTipo(int m){										//Metodo que genera un tipo aleatorio de menu
		if(m==0) {
			tipo="Simple";
		}else {
			tipo="Postre";
		}
	}

}
