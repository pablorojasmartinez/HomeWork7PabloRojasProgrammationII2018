package data;

import domain.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pablo Rojas
 */
public class XMLPersonFile {

    private Document document;
    private Element root;
    private String path;
    private int position;

    public XMLPersonFile(String path) throws JDOMException, IOException {
        this.path = path;
        this.position = 0;
        File file = new File(this.path);

        if (file.exists()) {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            this.document = saxBuilder.build(this.path);
            this.root = this.document.getRootElement();
        }//if
        else {
            this.root = new Element("People");
            this.document = new Document(this.root);
            storeXML();
        }//else

    }//constructor

    private void storeXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(this.path));
    }//storeXML

    private Element newNode(Person person) throws IOException {
        Element ePerson = new Element("person");
        // Agregar un atributo al elemento
        ePerson.setAttribute("id", person.getId());

        //crear el resto de atributos de la clase Person
        Element eName = new Element("name");
        eName.addContent(person.getName());

        Element eFirstLastname = new Element("firstLastname");
        eFirstLastname.addContent(person.getFistLastname());

        Element eSecondLastname = new Element("secondLastname");
        eSecondLastname.addContent(person.getSecondLastname());

        Element eBirthdate = new Element("birthdate");
        eBirthdate.addContent(person.getBirthdate());

        Element eCountry = new Element("country");
        eCountry.addContent(person.getCountry());

        Element eParentId = new Element("parentId");
        eParentId.addContent(person.getParentId());

        //Agregarlos al elemento person (id)
        ePerson.addContent(eName);
        ePerson.addContent(eFirstLastname);
        ePerson.addContent(eSecondLastname);
        ePerson.addContent(eBirthdate);
        ePerson.addContent(eCountry);
        ePerson.addContent(eParentId);

        return ePerson;

    }//newNode

    public void insertPerson(Person person) throws IOException {
        this.root.addContent(newNode(person));
        storeXML();
    }//insertPerson

    public boolean delete(String id) throws IOException {
        List elementList = this.root.getChildren();
        int count = 0;
        for (Object currentObject : elementList) {
            Element currentElement = (Element) currentObject;
            if (currentElement.getAttributeValue("id").equals(id)) {
                this.root.removeContent(count);
                storeXML();
                return true;
            }//if
            count++;
        }//for
        return false;
    }//delete

    public Person[] getPeople() {
        int peopleQuantity = this.root.getContentSize();
        List elementList = this.root.getChildren();
        Person[] arrayPerson = new Person[peopleQuantity];
        int count = 0;
        for (Object currentObject : elementList) {
            Element currentElement = (Element) currentObject;
            Person currentPerson = new Person();
            currentPerson.setId(currentElement.getAttributeValue("id"));
            currentPerson.setName(currentElement.getChild("name").getValue());
            currentPerson.setFirstLastname(currentElement.getChild("firstLastname").getValue());
            currentPerson.setSecondLastname(currentElement.getChild("secondLastname").getValue());

            //Sirve para sacar los años entre dos fechas en el formato mes/dia/año
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate birthDate = LocalDate.parse(currentElement.getChild("birthdate").getValue(), fmt);
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            String years = String.valueOf(period.getYears());
            
            //Esta linea sirve para que aparezca la fecha y no los años
            //currentPerson.setBirthdate(currentElement.getChild("birthdate").getValue());
            
            //Esta linea sirve para que aparezca los años y no la fecha
            currentPerson.setBirthdate(years);
            currentPerson.setCountry(currentElement.getChild("country").getValue());
            currentPerson.setParentId(currentElement.getChild("parentId").getValue());

            arrayPerson[count++] = currentPerson;
        }//for
        return arrayPerson;
    }//getPeople

    private boolean search(String id) {
        List elementList = this.root.getChildren();
        boolean exists = false;
        for (Object currentObject : elementList) {
            Element currentElement = (Element) currentObject;
            Person currentPerson = new Person(currentElement.getAttributeValue("id"), currentElement.getChild("name").getValue(),
                    currentElement.getChild("firstLastname").getValue(), currentElement.getChild("secondLastname").getValue(),
                    currentElement.getChild("birthdate").getValue(), currentElement.getChild("country").getValue(),
                    currentElement.getChild("parentId").getValue());
            if (currentPerson.getId().equalsIgnoreCase(id)) {
                exists = true;
                break;
            }//if
            else {
                this.position++;
            }//else
        }//for
        return exists;
    }//search

    public String searchPerson(String id) {
        List elementList = this.root.getChildren();
        for (Object currentObject : elementList) {
            Element currentElement = (Element) currentObject;
            Person currentPerson = new Person(currentElement.getAttributeValue("id"), currentElement.getChild("name").getValue(),
                    currentElement.getChild("firstLastname").getValue(), currentElement.getChild("secondLastname").getValue(),
                    currentElement.getChild("birthdate").getValue(), currentElement.getChild("country").getValue(),
                    currentElement.getChild("parentId").getValue());
            if (currentPerson.getId().equalsIgnoreCase(id)) {
                return currentPerson.toString();
            }//if

        }//for
        return null;
    }//searchPerson

    public void update(String name, Person person) {
        if (search(name)) {
            try {
                Element node = newNode(person);
                this.root.setContent(this.position, node);
            }//try
            catch (Exception ex) {
            }//catch
        }//if
        try {
            storeXML();
        }//try
        catch (IOException ioe) {
        }//catch
        this.position = 0;
    }//update

}//class
