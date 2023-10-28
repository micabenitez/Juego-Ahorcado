package ahorcandoSwing;

import java.util.Random;

public class Castellano extends JuegoAhorcado {
	private static String[][] palabras = {{"futbol","voley","basquet","hockey","tenis"},{"argentina","brasil","peru","paraguay","españa"},
			{"perro","gato","leon","puma","mono"},{"amarillo","azul","verde","rojo","rosa"}, {"manzana","naranja","banana","frutilla","kiwi"}} ;
	private String categoria;
	
	@Override
	public char[] getPalabra() {
		Random r = new Random();
		Random c = new Random();
		int azar = r.nextInt(palabras.length);
		int categ = c.nextInt(palabras[0].length);
		
		if(categ == 0) {
			categoria = "Deportes";
		}
		else if(categ == 1) {
			categoria = "Paises";
		}
		else if(categ == 2) {
			categoria = "Animales";
		}
		else if(categ == 3) {
			categoria = "Colores";
		}
		else if(categ == 4) {
			categoria = "Frutas";
		}
		return palabras[categ][azar].toCharArray();
	}
	
	@Override
	public String getCategoria() {
		return categoria;
	}
}