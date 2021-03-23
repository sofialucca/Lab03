/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Model;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	Model model=new Model();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> choiceLingua;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button bttnCheck;

    @FXML
    private TextArea txtRisposta;

    @FXML
    private Label labelErrori;

    @FXML
    private Button bttnClear;

    @FXML
    private Label labelTempo;

    @FXML
    private Label labelSpellCheck;
    
    @FXML
    void sceltaLingua(ActionEvent event) {
    	this.bttnCheck.setDisable(false);
    	this.txtInput.setEditable(true);
    	model.loadDictionary(this.choiceLingua.getValue());
    }
    
    @FXML
    void doClear(ActionEvent event) {
    	this.choiceLingua.setDisable(false);
    	this.txtInput.clear();
    	this.txtInput.setEditable(false);
    	this.bttnCheck.setDisable(true);
    	this.txtRisposta.clear();
    	this.bttnClear.setDisable(true);
    	this.labelErrori.setText(null);
    	this.labelTempo.setText(null);
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long tempo=System.nanoTime();
    	if(txtInput.getText().isBlank()) {
    		this.labelSpellCheck.setText("ERRORE:inserire testo");
    		return;
    	}
    	
    	String inputSoloParole= this.txtInput.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").toLowerCase();
    	List<String> listaInput=new ArrayList<String>();
    	for(String s:inputSoloParole.split(" ")) {
    		listaInput.add(s);
    	}
    	
    	String paroleSbagliate="";
    	int count=0;
    	/*for(RichWord rw:this.model.spellCheckTest(listaInput)) {
    		if(!rw.isCorretta()) {
    			paroleSbagliate+=rw.getParola()+"\n";
    			count++;
    		}
    	}*/ //list.contains
    	
    	for(RichWord rw:this.model.spellCheckTestLinear(listaInput)) {
    		if(!rw.isCorretta()) {
    			paroleSbagliate+=rw.getParola()+"\n";
    			count++;
    		}
    	} //ricerca lineare
    	
    	/*for(RichWord rw:this.model.spellCheckTestDichotomic(listaInput)) {
    		if(!rw.isCorretta()) {
    			paroleSbagliate+=rw.getParola()+"\n";
    			count++;
    		}
    	}*/ //ricerca dicotomica
    	
    	this.labelSpellCheck.setText("");
    	txtInput.setEditable(false);
    	this.bttnCheck.setDisable(true);
    	this.bttnClear.setDisable(false);
    	this.txtRisposta.setText(paroleSbagliate);
    	this.labelErrori.setText("Il testo contiene: "+count+" errori");
    	tempo=(System.nanoTime()-tempo)/1000;
    	this.labelTempo.setText("Spell check completato in: "+tempo+" milli secondi");
    	this.choiceLingua.setDisable(true);
    }

    @FXML
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnCheck != null : "fx:id=\"bttnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisposta != null : "fx:id=\"txtRisposta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelErrori != null : "fx:id=\"labelErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnClear != null : "fx:id=\"bttnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelTempo != null : "fx:id=\"labelTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        ObservableList<String> lingue= FXCollections.observableArrayList("English","Italian");
        this.choiceLingua.setItems(lingue);
        assert labelSpellCheck != null : "fx:id=\"labelSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
}

