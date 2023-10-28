package ahorcandoSwing;

public abstract class JuegoAhorcado {
	private int intentos;
	private char[] palabraOculta, palabraRandom;
	private int errores;
	private int aciertos;
	
	public JuegoAhorcado() {
		intentos = 8;
		palabraRandom = getPalabra();
		palabraOculta = palabraEnGuiones();
		errores = 0;
		aciertos = 0;
	}

	public char[] getPalabraOculta() {
		return palabraOculta;
	}

	public abstract char[] getPalabra();
	
	public char[] palabraEnGuiones() {  
		char[] guiones = new char[palabraRandom.length];
		for (int i = 0; i < palabraRandom.length; i++) {
				guiones[i] = '-' ;
		}
		return guiones;
	}
	
	public void validarLetra(String letra) {
		boolean letraCorrecta = false;
		char caracter = extraerCaracter(letra);
		for (int i = 0; i < palabraOculta.length; i++) {
			if(caracter == palabraRandom[i]) {
				palabraOculta[i] = palabraRandom[i];
				letraCorrecta = true;
				aciertos++;
			}
		}
		if(!letraCorrecta) {
			intentos--;
			errores++;
		}
	}
	
	public boolean gano() {
		return aciertos == palabraRandom.length;
	}
	
	public boolean perdio() {
		return intentos == 0;
	}
	
	public int getErrores() {
		return errores;
	}
	
	private char extraerCaracter(String letra) {
		return letra.charAt(0);
	}
	
	public int getIntentos() {
		return intentos;
	}

	public void reiniciar() {
		intentos = 8;
		errores = 0;
		palabraRandom = getPalabra();
		palabraOculta = palabraEnGuiones();
		aciertos = 0;
	}

	public String getPalabraRandom() {
		return String.valueOf(palabraRandom);
	}

	public abstract String getCategoria();
}
