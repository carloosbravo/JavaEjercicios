package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import objects.Cita;
import objects.Mascota;

public class Main {
	public static ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

	public static void main(String[] args) {

		System.out.println("Iniciando programa");
		leerXML();
		System.out.println("serializando objetos");
		serializar();
		
		

	}

	public static void leerXML() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance(); // se crea el objeto
		DocumentBuilder db;
		Document document;
		File file;
		XPath xPath;
		NodeList nodeList;
		String path;

		try {
			db = dbf.newDocumentBuilder();
			file = new File("mascotas.xml");
			document = db.parse(file);
			
			document.normalizeDocument();
			xPath = XPathFactory.newInstance().newXPath();
			
			path = "//mascota";
			
			nodeList = (NodeList) xPath.compile(path).evaluate(document,XPathConstants.NODESET);
			
			for (int i = 0;i< nodeList.getLength();i++) {
				Element elementoMascota  = (Element) nodeList.item(i);
				ArrayList<Cita> citasMascota = new ArrayList<Cita>();
				String idMascota = elementoMascota.getElementsByTagName("id").item(0).getTextContent();
				String nombreMascota = elementoMascota.getElementsByTagName("nombre").item(0).getTextContent();
				String especieMacota = elementoMascota.getElementsByTagName("especie").item(0).getTextContent();
				String sexoMascota = elementoMascota.getElementsByTagName("sexo").item(0).getTextContent();
				String edadMascota = elementoMascota.getElementsByTagName("edad").item(0).getTextContent();
				
				Mascota m = new Mascota(idMascota,nombreMascota,especieMacota,sexoMascota,edadMascota,citasMascota);
				mascotas.add(m);
				path = "//cita";
				
					for(int x = 0;x<nodeList.getLength();x++) {
						Element elementoCita  = (Element) nodeList.item(x);
						String fechaCita = elementoCita.getElementsByTagName("fecha").item(0).getTextContent();
						String motivoCita = elementoCita.getElementsByTagName("motivo").item(0).getTextContent();
						Cita c = new Cita(fechaCita,motivoCita);
						mascotas.get(i).getCitas().add(c);
					}
					
					

				System.out.println("----");
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void serializar() {
		String path = "mascotas.ser";
		System.out.println("Archivo xml leido, serializando datos...");
		try {
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(mascotas);

			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public static void deserializar() {
		String path = "mascotas.ser";

		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);

			mascotas = (ArrayList<Mascota>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
