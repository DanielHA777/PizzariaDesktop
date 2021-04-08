package br.senai.sp.pizzariaOfc.modelo;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class ThreadVoz extends SwingWorker<Object, Object>{

		private static LiveSpeechRecognizer reconhecedor;
		private String resultadoDoReconhecedor;
		private boolean reconhecedorRodando = false;
		public static boolean limparCacheMic = true;
		
		private ControladaPorVoz tela;
		private JButton btn1;
		
		public ThreadVoz(ControladaPorVoz tela, JButton btnLigaVoz) {
			this.btn1 = btn1;
			this.tela = tela;		
			Configuration configuracao = new Configuration();
			configuracao.setAcousticModelPath("lib:/edu/cmu/sphinx/models/en-us/en-us");
			configuracao.setDictionaryPath("lib:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
			configuracao.setGrammarPath("lib:/grammars");
			configuracao.setGrammarName("grammar");
			configuracao.setUseGrammar(true);
			try {
				if(limparCacheMic)
					reconhecedor = new LiveSpeechRecognizer(configuracao);
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		}
		
		public void iniciarReconhecedor() {	
			if(reconhecedorRodando)
				System.out.println("Reconhecedor já foi iniciado");
			else {
				reconhecedorRodando = true;	
				System.out.println("aqui");
				reconhecedor.startRecognition(limparCacheMic);
				limparCacheMic = false;
				btn1.setText("Ouvindo...");
				System.out.println("Ouvindo...");			
				try {
					while(reconhecedorRodando) {
						SpeechResult resultadoDaFala = reconhecedor.getResult();
						if (resultadoDaFala == null)
							System.out.println("Não consegui entender o que você falou :(");
						else {
							resultadoDoReconhecedor = resultadoDaFala.getHypothesis();
							System.out.println("Você disse '" + resultadoDoReconhecedor + "'");	
							reconhecedorRodando = false; 
							tela.executaComandoPorVoz(resultadoDoReconhecedor);
							btn1.doClick();
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
					reconhecedorRodando = false;
				}
				System.out.println("Espero ter te ajudado :)");
				reconhecedor.stopRecognition();
			}
			//btnLigaVoz.setText("START");
		}
		
		public Object doInBackground() throws Exception {		
			iniciarReconhecedor();		
			return null;
		}	
}
