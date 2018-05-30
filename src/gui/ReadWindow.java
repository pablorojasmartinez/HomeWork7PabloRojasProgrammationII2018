/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.XMLPersonFile;
import domain.Dat;
import domain.Person;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdom.JDOMException;

/**
 *
 * @author Pablo Rojas
 */
public class ReadWindow extends JInternalFrame implements ActionListener {

    JTable jTable;
    JTextField txtTexto;
    JButton btn;
    DefaultTableModel defaultTableModel;

    public ReadWindow() throws JDOMException, IOException {
        super();
        this.setSize(800, 600);
        this.setLayout(null);
        init();
        this.setVisible(true);
        this.setClosable(true);
       
      
    }//constructor

    public void init() throws JDOMException, IOException {
        this.defaultTableModel = new DefaultTableModel();
        this.defaultTableModel.addColumn("Id");
        this.defaultTableModel.addColumn("Name");
        this.defaultTableModel.addColumn("First Lastname");
        this.defaultTableModel.addColumn("Second Lastname");
        this.defaultTableModel.addColumn("Birthdate");
        this.defaultTableModel.addColumn("Country");
        this.defaultTableModel.addColumn("Parent Id");
        XMLPersonFile xlmPersonFile = new XMLPersonFile("./data/my_family_tree.xml");
        Person[] personList = xlmPersonFile.getPeople();
//        for (int i = 0; i < personList.length; i++) {
//            this.defaultTableModel.addRow(new Object[]{personList[i].getId(),personList[i].getName(),personList[i].getFistLastname(),
//            personList[i].getSecondLastname(),personList[i].getBirthdate(),personList[i].getCountry(),personList[i].getParentId()});
//        }//for
        this.txtTexto=new JTextField();
        this.btn=new JButton("aceptar");
        
        this.txtTexto.setBounds(0, 0, 100, 20);
        this.btn.setBounds(150, 0, 100, 20);
  this.btn.addActionListener(this);
        this.jTable = new JTable(this.defaultTableModel);
        JScrollPane jsp = new JScrollPane(this.jTable);
              jsp.setBounds(0,100, 500, 500);
        this.getContentPane().add(txtTexto);
        this.getContentPane().add(btn);
        this.getContentPane().add(jsp);

    }//init

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==btn){
      
          try {
              XMLPersonFile archivo =new XMLPersonFile("./data/my_family_tree.xml");
           Person []personList=   archivo.getTable(txtTexto.getText());
                 for (int i=0;i<personList.length;i++) {
            this.defaultTableModel.addRow(new Object[]{personList[i].getId(),personList[i].getName(),personList[i].getFistLastname(),
            personList[i].getSecondLastname(),personList[i].getBirthdate(),personList[i].getCountry(),personList[i].getParentId()});



        }//for
              
          } catch (JDOMException ex) {
              Logger.getLogger(ReadWindow.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(ReadWindow.class.getName()).log(Level.SEVERE, null, ex);
          }
      
      }
    }

}
