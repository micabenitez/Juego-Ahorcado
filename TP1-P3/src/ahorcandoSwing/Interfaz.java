package ahorcandoSwing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import idioma.Idioma;

public class Interfaz {
	private ImageIcon imagenes[];
	private JFrame frame;
	private JuegoAhorcado ahorcado;
	private JLabel lblPalabraOculta, labelCateg, labelImagen,labelTitulo,lblCategoria;
	private JButton botones[];
	private JButton boton1, boton2, boton3, boton4,boton5, boton6, boton7,boton8,boton9,boton10,boton11,boton12,boton13,
	boton14,boton15,boton16,boton17,boton18,boton19,boton20,boton21,boton22,boton23,boton24,boton25,boton26,boton27;
	private JMenu menu;
	private Idioma idioma;
	private JMenuItem reiniciar, cambiarIdioma;
	private JRadioButtonMenuItem espanol, ingles;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			  JFrame.setDefaultLookAndFeelDecorated(true);
			  JDialog.setDefaultLookAndFeelDecorated(true);		 
			  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
			catch (Exception e)
			 {
			 e.printStackTrace();
			 }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ahorcado = new Castellano();
		Fondo fondo = new Fondo();
		idioma = new Idioma("español");
		
		frame = new JFrame();
		frame.setTitle("Juego del ahorcado");
		frame.setContentPane(fondo);
		frame.getContentPane().setBackground(new Color(255, 255, 153));
		frame.setBounds(100, 100, 670, 477);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		labelImagen = new JLabel("");
		labelImagen.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		labelImagen.setBounds(34, 118, 227, 299);
		
	    imagenes = new ImageIcon[9];
	    imagenes[0] = new ImageIcon(getClass().getResource("0.jpg"));
	    imagenes[1] = new ImageIcon(getClass().getResource("1.jpg"));
        imagenes[2] = new ImageIcon(getClass().getResource("2.jpg"));
        imagenes[3] = new ImageIcon(getClass().getResource("3.jpg"));
        imagenes[4] = new ImageIcon(getClass().getResource("4.jpg"));
        imagenes[5] = new ImageIcon(getClass().getResource("5.jpg"));
        imagenes[6] = new ImageIcon(getClass().getResource("6.jpg"));
        imagenes[7] = new ImageIcon(getClass().getResource("7.jpg"));
        imagenes[8] = new ImageIcon(getClass().getResource("8.jpg"));
       
	      
        //redimensiono las imagenes
        escalarImagenes();

		labelImagen.setIcon(imagenes[0]);
		frame.getContentPane().add(labelImagen);
		
		//inicializo y nombro los botones del teclado
		inicializarBotones();
		nombrarBotones();
		
		//se asigna un evento a cada letra para comprobar que exista en la palabra a adivinar
        for (int i = 0; i < botones.length; i++) {
            botones[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    validarLetra(e);     	
                }
            });
        } 
     //-----------------Label Titulo -----------------
		labelTitulo = new JLabel("Juego del ahorcado");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelTitulo.setForeground(new Color(204, 0, 51));
		labelTitulo.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 28));
		labelTitulo.setBounds(124, 54, 422, 40);
		frame.getContentPane().add(labelTitulo);
		
	//-----------------Label Palabra oculta -----------------
		lblPalabraOculta = new JLabel("", SwingConstants.CENTER);
		lblPalabraOculta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPalabraOculta.setOpaque(true);
		lblPalabraOculta.setBackground(Color.white);
		lblPalabraOculta.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblPalabraOculta.setText(String.valueOf(ahorcado.getPalabraOculta()));
		lblPalabraOculta.setBounds(346, 155, 244, 50);
		frame.getContentPane().add(lblPalabraOculta);
		
	//----------------- Label Categoria -----------------
		lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 15));
		lblCategoria.setBounds(384, 127, 87, 20);
		frame.getContentPane().add(lblCategoria);
		
		labelCateg = new JLabel("");
		labelCateg.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 15));
		labelCateg.setBounds(483, 130, 99, 14);
		labelCateg.setText(ahorcado.getCategoria());
		frame.getContentPane().add(labelCateg);
		
	//----------------- MENU -----------------
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 666, 22);
		fondo.add(menuBar);
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		reiniciar = new JMenuItem("Reiniciar");
		reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			}
		});
		menu.add(reiniciar);
		
		cambiarIdioma = new JMenu("Cambiar Idioma");
		menu.add(cambiarIdioma);
		
		ButtonGroup idiomas = new ButtonGroup();
		
		ingles = new JRadioButtonMenuItem("Ingles");
		ingles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarIdioma("ingles");
				ahorcado = new Ingles(); 
				reiniciar();
			}
		});

		espanol = new JRadioButtonMenuItem("Español");
		espanol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarIdioma("español");
				ahorcado = new Castellano();
				reiniciar();
			}
		});
		
		idiomas.add(espanol);
		idiomas.add(ingles);
		cambiarIdioma.add(espanol);
		cambiarIdioma.add(ingles);
		
		//----------- Teclado ----------
		configurarTeclado();
	}

	private void validarLetra(ActionEvent e) {
		JButton boton = (JButton) e.getSource();
		for (int i = 0; i < botones.length; i++) {
			if(botones[i] == boton) {
				String letra = boton.getText().toLowerCase();
				
				ahorcado.validarLetra(letra);
				
				lblPalabraOculta.setText(String.valueOf(ahorcado.getPalabraOculta()));
				
				labelImagen.setIcon(imagenes[ahorcado.getErrores()]);
				
				boton.setEnabled(false);
	
				consultarEstadoDeJuego();
				
			break;
			}	
		}
			
	}

	private void consultarEstadoDeJuego() {
		ImageIcon ganador = new ImageIcon(getClass().getResource("ganador.png"));
		ImageIcon perdedor = new ImageIcon(getClass().getResource("perdedor.png"));
		int opcion = 0;

		if(ahorcado.gano()) 
			if(idioma.getLenguaje().equals("español")) {
				opcion = JOptionPane.showConfirmDialog(null,"GANASTE!\n ¿Reiniciar juego?","Ahorcado",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,ganador);
				verOpcion(opcion);
			}else {
				opcion = JOptionPane.showConfirmDialog(null,"YOU WON!\n ¿Restart game?","Hangman Game",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,ganador);
				verOpcion(opcion);	
			}
		else if(ahorcado.perdio()) {
				if(idioma.getLenguaje().equals("español")) {
					opcion = JOptionPane.showConfirmDialog(null,"Perdiste, la palabra era: "+ahorcado.getPalabraRandom()+"\n ¿Reiniciar juego?","Ahorcado",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,perdedor);	
					verOpcion(opcion);
				}else 
					opcion = JOptionPane.showConfirmDialog(null,"You lost, the word was: "+ahorcado.getPalabraRandom()+"\n ¿Restart game?","Hangman Game",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,perdedor);
				verOpcion(opcion);	
		}	
	}
	
	private void verOpcion(int opcion) {
		if(opcion == JOptionPane.YES_OPTION)
			reiniciar();
		else if(opcion == JOptionPane.NO_OPTION) 
			System.exit(0);
	}
	
	private void reiniciar() {
		ahorcado.reiniciar();
		
		for (int i = 0; i < botones.length; i++) {
	            botones[i].setEnabled(true);
	        }
		 
		lblPalabraOculta.setText(String.valueOf(ahorcado.getPalabraOculta()));
		labelCateg.setText(String.valueOf(ahorcado.getCategoria()));
		labelImagen.setIcon(imagenes[ahorcado.getErrores()]);
	}
	
	private void cambiarIdioma(String nombreIdioma){
        idioma = new Idioma(nombreIdioma);
        frame.setTitle(idioma.getProperty("titulo"));
        labelTitulo.setText(idioma.getProperty("labelTitulo"));
        menu.setText(idioma.getProperty("menu"));
        reiniciar.setText(idioma.getProperty("reiniciar"));
        cambiarIdioma.setText(idioma.getProperty("cambiarIdioma"));
        ingles.setText(idioma.getProperty("ingles"));
        espanol.setText(idioma.getProperty("espanol"));
        lblCategoria.setText(idioma.getProperty("categoria"));
	}
	
	private void nombrarBotones() {
		boton1.setText("A");
		boton1.setBounds(10, 29, 40, 23);
		
		boton2.setText("B");
		boton2.setBounds(279, 29, 40, 23);
		
		boton3.setText("C");
		boton3.setBounds(210, 63, 40, 23);

		boton4.setText("D");
		boton4.setBounds(160, 97, 40, 23);

		boton5.setText("E");
		boton5.setBounds(210, 97, 40, 23);
		
		boton6.setText("F");
		boton6.setBounds(260, 63, 40, 23);
		
		boton7.setText("G");
		boton7.setBounds(10, 63, 40, 23);
		
		boton8.setText("H");
		boton8.setBounds(60, 29, 40, 23);
		
		boton9.setText("I");
		boton9.setBounds(60, 63, 40, 23);
		
		boton10.setText("J");
		boton10.setBounds(10, 97, 40, 23);
		
		boton11.setText("K");
		boton11.setBounds(260, 97, 40, 23);
		
		boton12.setText("L");
		boton12.setBounds(110, 29, 40, 23);
		
		boton13.setText("M");
		boton13.setBounds(160, 29, 40, 23);
		
		boton14.setText("N");
		boton14.setBounds(210, 29, 40, 23);
		
		boton15.setText("O");
		boton15.setBounds(110, 63, 40, 23);
		
		boton16.setText("P");
		boton16.setBounds(160, 63, 40, 23);
		
		boton17.setText("Q");
		boton17.setBounds(60, 97, 40, 23);
		
		boton18.setText("R");
		boton18.setBounds(110, 97, 40, 23);
		
		boton19.setText("S");
		boton19.setBounds(10, 131, 40, 23);
		
		boton20.setText("T");
		boton20.setBounds(60, 131, 40, 23);
		
		boton21.setText("U");
		boton21.setBounds(110, 131, 40, 23);
		
		boton22.setText("V");
		boton22.setBounds(160, 131, 40, 23);
		
		boton23.setText("W");
		boton23.setBounds(210, 131, 40, 23);
		
		boton24.setText("X");
		boton24.setBounds(260, 131, 40, 23);
		
		boton25.setText("Y");
		boton25.setBounds(110, 157, 40, 23);
		
		boton26.setText("Z");
		boton26.setBounds(160, 157, 40, 23);
		
		boton27.setText("\u00D1");
	}

	private void configurarTeclado() {
		JPanel panelTeclado = new JPanel();
		panelTeclado.setForeground(Color.WHITE);
		panelTeclado.setBackground(new Color(204, 0, 51));
		panelTeclado.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelTeclado.setBounds(310, 223, 309, 182);
		panelTeclado.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		frame.getContentPane().add(panelTeclado);
		
		
		GroupLayout gl_panelTeclado = new GroupLayout(panelTeclado);
		gl_panelTeclado.setHorizontalGroup(
			gl_panelTeclado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTeclado.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTeclado.createSequentialGroup()
							.addGroup(gl_panelTeclado.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addComponent(boton8, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(boton7, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(boton9, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(boton10, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(boton11)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(boton12, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
									.addGap(5))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addComponent(boton1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(boton2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(boton3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(boton4, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(boton5)
									.addGap(4)
									.addComponent(boton6, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addGap(7))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addComponent(boton13, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(boton14, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(boton27, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(boton15)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(boton16, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(boton17)
									.addGap(4)))
							.addGap(179))
						.addGroup(gl_panelTeclado.createSequentialGroup()
							.addGap(53)
							.addComponent(boton23)
							.addGap(7)
							.addComponent(boton24, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(boton25, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(boton26)))
					.addGap(19))
				.addGroup(gl_panelTeclado.createSequentialGroup()
					.addGap(30)
					.addComponent(boton18)
					.addGap(7)
					.addComponent(boton19, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(boton20, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(boton21, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(boton22)
					.addContainerGap(232, Short.MAX_VALUE))
		);
		gl_panelTeclado.setVerticalGroup(
			gl_panelTeclado.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelTeclado.createSequentialGroup()
					.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelTeclado.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(18)
									.addComponent(boton1)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(18)
									.addComponent(boton3)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(18)
									.addComponent(boton2)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panelTeclado.createSequentialGroup()
								.addContainerGap()
								.addComponent(boton4)
								.addGap(6))
							.addGroup(gl_panelTeclado.createSequentialGroup()
								.addContainerGap()
								.addComponent(boton5)
								.addGap(6)))
						.addGroup(Alignment.TRAILING, gl_panelTeclado.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(boton6)
							.addGap(6)))
					.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTeclado.createSequentialGroup()
							.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(boton8))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(1)
									.addComponent(boton11))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(boton12))
								.addComponent(boton7)
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(boton9)))
							.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(5)
									.addComponent(boton13))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(6)
									.addComponent(boton14))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(7)
									.addComponent(boton27))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(6)
									.addComponent(boton16))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(6)
									.addComponent(boton17))))
						.addGroup(gl_panelTeclado.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(boton10, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(boton15)))
					.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
									.addComponent(boton19)
									.addGap(5))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(6)
									.addComponent(boton21))
								.addGroup(gl_panelTeclado.createSequentialGroup()
									.addGap(6)
									.addComponent(boton22)))
							.addGroup(gl_panelTeclado.createSequentialGroup()
								.addGap(6)
								.addComponent(boton20)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_panelTeclado.createSequentialGroup()
							.addGap(5)
							.addComponent(boton18)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panelTeclado.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTeclado.createSequentialGroup()
							.addGap(2)
							.addComponent(boton25))
						.addGroup(gl_panelTeclado.createSequentialGroup()
							.addGap(2)
							.addComponent(boton24))
						.addGroup(gl_panelTeclado.createSequentialGroup()
							.addGap(2)
							.addComponent(boton26))
						.addGroup(gl_panelTeclado.createSequentialGroup()
							.addGap(2)
							.addComponent(boton23)))
					.addGap(19))
		);
		panelTeclado.setLayout(gl_panelTeclado);
	}
	

	private void inicializarBotones() {
		botones = new JButton[27];
		boton1 = new JButton();
		boton2 = new JButton();
		boton3 = new JButton();
		boton4 = new JButton();
		boton5 = new JButton();
		boton6 = new JButton();
		boton7 = new JButton();
		boton8 = new JButton();
		boton9 = new JButton();
		boton10 = new JButton();
		boton11 = new JButton();
		boton12 = new JButton();
		boton13 = new JButton();
		boton14 = new JButton();
		boton15 = new JButton();
		boton16 = new JButton();
		boton17 = new JButton();
		boton18 = new JButton();
		boton19 = new JButton();
		boton20 = new JButton();
		boton21 = new JButton();
		boton22 = new JButton();
		boton23 = new JButton();
		boton24 = new JButton();
		boton25 = new JButton();
		boton26 = new JButton();
		boton27 = new JButton();
		
		botones[0] = boton1;
		botones[1] = boton2;
		botones[2] = boton3;
		botones[3] = boton4;
		botones[4] = boton5;
		botones[5] = boton6;
		botones[6] = boton7;
		botones[7] = boton8;
		botones[8] = boton9;
		botones[9] = boton10;
		botones[10] = boton11;
		botones[11] = boton12;
		botones[12] = boton13;
		botones[13] = boton14;
		botones[14] = boton15;
		botones[15] = boton16;
		botones[16] = boton17;
       	botones[17] = boton18;
       	botones[18] = boton19;
       	botones[19] = boton20;
       	botones[20] = boton21;
       	botones[21] = boton22;
       	botones[22] = boton23;
       	botones[23] = boton24;
       	botones[24] = boton25;
       	botones[25] = boton26;
    	botones[26] = boton27;
	}

	private void escalarImagenes() {
		imagenes[0] = new ImageIcon(imagenes[0].getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),Image.SCALE_SMOOTH));
		imagenes[1] = new ImageIcon(imagenes[1].getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),Image.SCALE_SMOOTH));
		imagenes[2] = new ImageIcon(imagenes[2].getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),Image.SCALE_SMOOTH));
		imagenes[3] = new ImageIcon(imagenes[3].getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),Image.SCALE_SMOOTH));
		imagenes[4] = new ImageIcon(imagenes[4].getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),Image.SCALE_SMOOTH));
		imagenes[5] = new ImageIcon(imagenes[5].getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),Image.SCALE_SMOOTH));
		imagenes[6] = new ImageIcon(imagenes[6].getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),Image.SCALE_SMOOTH));
		imagenes[7] = new ImageIcon(imagenes[7].getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),Image.SCALE_SMOOTH));
		imagenes[8] = new ImageIcon(imagenes[8].getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),Image.SCALE_SMOOTH));
	}

}
