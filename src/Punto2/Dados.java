package Punto2;

import java.util.Random;

public class Dados implements Runnable{
	private int dado1;
	private int dado2;
	private int dado3;
	private int dado4;
	private int dado5;
	private int dado6;
	private int suma;
	
	@Override
	public void run() {
		
		LanzarDados();											//metodo que genera los 6 dados aleatorios
		ImprimirDados();										//metodo que calcula la suma de los dados y dice si es par o impar
		if ((dado1!=dado2)&&(dado1!=dado3)&&(dado1!=dado4)&&(dado1!=dado5)&&(dado1!=dado6)) {	//Controla que no se repitan 
			if((dado2!=dado3)&&(dado2!=dado4)&&(dado2!=dado5)&&(dado2!=dado6)) {				//Los numeros de los dados
				if((dado3!=dado4)&&(dado3!=dado5)&&(dado3!=dado6)) {							//Para saber si hay una
					if((dado4!=dado5)&&(dado4!=dado6)) {										//Escalera
						if((dado5!=dado6)) {
							System.out.println("***SE HA PRODUCIDO UNA ESCALERA!!!***");
						}
					}
				}					
			}
		}			
		}	
	
	public void LanzarDados() {										//metodo que genera los 6 dados aleatorios
		Random ran=new Random();
		dado1=ran.nextInt(6)+1;
		dado2=ran.nextInt(6)+1;
		dado3=ran.nextInt(6)+1;
		dado4=ran.nextInt(6)+1;
		dado5=ran.nextInt(6)+1;
		dado6=ran.nextInt(6)+1;
	}
	
	public void ImprimirDados() {									//metodo que calcula la suma de los dados y dice si es par o impar
		suma = dado1+dado2+dado3+dado4+dado5+dado6;
		if (suma%2==0) {
			System.out.println("+La suma de los dados es par. Suma= "+suma);
			System.out.println(dado1+","+dado2+","+dado3+","+dado4+","+dado5+","+dado6);
		}else {
			System.out.println("-La suma de los dados es impar. Suma= "+suma);
			System.out.println(dado1+","+dado2+","+dado3+","+dado4+","+dado5+","+dado6);
		}
	}

}
