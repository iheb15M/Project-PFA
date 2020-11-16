/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package formulaireae;

import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author hp
 */
/*
public class FXMLDocumentController implements Initializable {
    
    //@FXML
    //private Label label;
    
    
    @FXML
    public AnchorPane AnchorPane;
    public TextField nom;
    public TextField prenom;
    public TextField datenaiss;
    public TextField telephone;
    public TextField cin;
    public TextField email;
    public TextField zip;
    public ComboBox poste;
    public CheckBox compte;
    public Label label;
    
   
    
    
    
        
        public void NomEmploye(KeyEvent keyEvent) throws Exception {
        boolean v = verifText(nom.getText());}
        
        
        public void PrenomEmploye(KeyEvent keyEvent) throws Exception {
        boolean v = verifText(prenom.getText()); 
        }
        
    
    
     
     private void handleButtonAction(ActionEvent event) throws SQLException {
        
            
    
 
            if (NomEmploye()==false) {
                
             nom.setText("Nom de l'employe incorrect!",
                    " Saisir de nouveau  ");
          

        } else {
            
            String sql = " insert into EMPLOYE (NOM, PRENOM, DATE_NAISS, ID_POSTE, CIN, NUM_TEL, MAIL, GENDER, ZIP) values (?, ?, ?,?, ?, ?,?, ?, ?)";
            PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
            stmt.setString(1, nom.getText());//remarque chaine de caractère entre "" non pas entre ''
            stmt.setString(2, prenom.getText());
            stmt.setString(3, datenaiss.getText());
            stmt.setInt(4, 0);
            stmt.setInt(5, Integer.parseInt(cin.getText()));
            stmt.setInt(6, Integer.parseInt(telephone.getText()));
            stmt.setString(7, email.getText());
            stmt.setString(8, null);
            stmt.setInt(9, Integer.parseInt(zip.getText()));
    
  
            stmt.execute();
   
        
        }

   
     }

    


    

    
    // Controle de saisie
    private boolean verifText(String texte) {
        boolean v = true;
        
        
        //Verification de la langeur de champ
        if ((texte.length() < 3) && (texte.length() > 20)) {
            label.setText("Ce champ doit contenir au min 3 characters et au max 20 charactéres ");
            v = false;
        } else {
            label.setText("");
            }
        
        return v;
    }
    
}
*/