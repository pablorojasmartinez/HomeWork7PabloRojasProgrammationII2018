/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.XMLPersonFile;
import domain.Person;
import java.io.IOException;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdom.JDOMException;

/**
 *
 * @author Pablo Rojas
 */
public class ReadWindow extends JInternalFrame {

    JTable jTable;
    DefaultTableModel defaultTableModel;

    public ReadWindow() throws JDOMException, IOException {
        super();
        this.setSize(800, 600);
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
        for (int i = 0; i < personList.length; i++) {
            this.defaultTableModel.addRow(new Object[]{personList[i].getId(),personList[i].getName(),personList[i].getFistLastname(),
            personList[i].getSecondLastname(),personList[i].getBirthdate(),personList[i].getCountry(),personList[i].getParentId()});
        }//for
        this.jTable = new JTable(this.defaultTableModel);
        JScrollPane jsp = new JScrollPane(this.jTable);
        this.getContentPane().add(jsp);

    }//init

}
