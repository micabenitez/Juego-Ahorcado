package idioma;

import java.io.IOException;
import java.util.Properties;
 
public class Idioma extends Properties{
	private String lenguaje;
    private static final long serialVersionUID = 1L;
 
    public Idioma(String idioma){
    	lenguaje = idioma;
        switch(idioma){
            case "español":
                    getProperties("espanol.properties");
                    break;
            case "ingles":
                    getProperties("ingles.properties");
                    break;
            default:
                    getProperties("espanol.properties");
        }
 
    }
 
    private void getProperties(String idioma) {
        try {
            this.load( getClass().getResourceAsStream(idioma) );
        } catch (IOException ex) {
 
        }
   }

	public String getLenguaje() {
		return lenguaje;
	}
}