package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {

	private List<String> dizionario;
	
	public Model() {
		dizionario=new ArrayList<String>();
	}
	
	public void loadDictionary(String language) {
		String nomeFile="src/main/resources/"+language+".txt";
		dizionario.clear();
		try {
			FileReader fr=new FileReader(nomeFile);
			BufferedReader br=new BufferedReader(fr);
			String riga;
			while((riga=br.readLine())!=null) {
				dizionario.add(riga);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERRORE nella lettura del file.");
		}
		
	}
	
	public List<RichWord> spellCheckTest(List<String> inputTesto){
		List<RichWord> paroleControllate=new ArrayList<RichWord>(inputTesto.size());
		for(String s1:inputTesto) {
			RichWord nuovaParola=new RichWord(s1);
			for(String s2: dizionario) {
				if(s1.equals(s2)) {
					nuovaParola.setCorretta();
					break;
				}
			}
			paroleControllate.add(nuovaParola);
		}
		return paroleControllate;
	}
}
