package Punto2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

///////////////////////////////////////
////////////Punto 2 OK////////////////
/////////////////////////////////////

public class Principal {

	public static void main(String[] args) {		
		ScheduledExecutorService BusquedaRepetitiva = Executors.newScheduledThreadPool(1);		//tipo Scheduled para repetirlo
		Runnable dados = new Dados();															//cada 2 segundos
		BusquedaRepetitiva.scheduleAtFixedRate(dados, 0, 2, TimeUnit.SECONDS);					 // revision cada 2 seg
	}
}
