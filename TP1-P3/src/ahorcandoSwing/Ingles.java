package ahorcandoSwing;

import java.util.Random;

public class Ingles extends JuegoAhorcado {
	private static String[][] palabras = {{"football","volleyball","basketball","tennis","hockey"},{"argentina","brazil","spain","france","germany"},
			{"dog","cat","snake","tiger","monkey"},{"yellow","pink","red","green","blue"},{"apple","orange","kiwi","banana","strawberry"}};
	
	private String categoria;
	
	@Override
	public char[] getPalabra() {
		Random r = new Random();
		Random c = new Random();
		int azar = r.nextInt(palabras.length);
		int categ = c.nextInt(palabras[0].length);
	
		if(categ == 0) {
			categoria = "Sports";
		}
		else if(categ == 1) {
			categoria = "Countries";
		}
		else if(categ == 2) {
			categoria = "Animals";
		}
		else if(categ == 3) {
			categoria = "Colors";
		}
		else if(categ == 4) {
			categoria = "Fruits";
		}
		return palabras[categ][azar].toCharArray();
	}
	
	@Override
	public String getCategoria() {
		return categoria;
	}
}
