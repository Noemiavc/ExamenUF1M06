package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<ArticlesCompra> llistaCompra = reader();

        // Operacions amb XML
        generarXML(llistaCompra);
        llegirArxiuXML();

        // Operacions amb serialització binaria
        serialitzarLlista(llistaCompra);
        ArrayList<ArticlesCompra> llistaCompraDeserialitzada = deserialitzarLlista();

        // Mostrar los articulos de la lista deserializada
        for (ArticlesCompra article : llistaCompraDeserialitzada) {
            System.out.println(article.toString());
        }

    } // End of the main method

    private static ArrayList<ArticlesCompra> reader() throws IOException {
        ArrayList<ArticlesCompra> llistaCompra = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("Introduce la descripción del artículo (o escriba 'exit' para finalizar): ");
            String descripcio = bufferedReader.readLine();
            if (descripcio.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Introduce la cantidad del articulo: ");
            double quantitat = Double.parseDouble(bufferedReader.readLine());

            System.out.print("Introduce la unidad del articulo: ");
            String unitat = bufferedReader.readLine();

            System.out.print("Introduce la sección del articulo: ");
            String seccio = bufferedReader.readLine();

            System.out.print("Introduce el precio del articulo: ");
            double preu = Double.parseDouble(bufferedReader.readLine());

            ArticlesCompra articlesCompra = new ArticlesCompra(descripcio, unitat, seccio, quantitat,preu);
            llistaCompra.add(articlesCompra);
        }

        return llistaCompra;
    }

    private static void generarXML(ArrayList<ArticlesCompra> llistaCompra) {
        try {
            PrintWriter printWriter = new PrintWriter("llista_compra.xml", StandardCharsets.UTF_8);
            printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            printWriter.println("<llistaCompra>");
            for (ArticlesCompra article : llistaCompra) {
                printWriter.println("   <article>");
                printWriter.println("       <descripcio>" + article.getDescripcio() + "</descripcio>");
                printWriter.println("       <quantitat>" + article.getQuantitat() + "</quantitat>");
                printWriter.println("       <unitat>" + article.getUnitat() + "</unitat>");
                printWriter.println("       <seccio>" + article.getSeccio() + "</seccio>");
                printWriter.println("       <preu>" + article.getPreu() + "</preu>");
                printWriter.println("   </article>");
            }
            printWriter.println("</llistaCompra>");
            printWriter.close();
            System.out.println("Archivo XML generado con éxito.");
        } catch (IOException e) {
            System.out.printf("Error al generar el archivo XML: %s\n", e.toString());
        }
    }

    private static void llegirArxiuXML() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("./llista_compra.xml"));

            NodeList llistaArticles = document.getElementsByTagName("article");

            for (int i = 0; i < llistaArticles.getLength(); i++) {
                Node node = llistaArticles.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String descripcio = element.getElementsByTagName("descripcio").item(0).getTextContent();
                    String quantitat = element.getElementsByTagName("quantitat").item(0).getTextContent();
                    String unitat = element.getElementsByTagName("unitat").item(0).getTextContent();
                    String seccio = element.getElementsByTagName("seccio").item(0).getTextContent();
                    String preu = element.getElementsByTagName("preu").item(0).getTextContent();

                    System.out.println("ARTICLE");
                    System.out.println("- Descripció: " + descripcio);
                    System.out.println("- Quantitat: " + quantitat);
                    System.out.println("- Unitat: " + unitat);
                    System.out.println("- Secció: " + seccio);
                    System.out.println("- Preu: " + preu);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.toString());
        }
    }

    private static void serialitzarLlista(ArrayList<ArticlesCompra> llistaCompra) {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./llista_compra.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(llistaCompra);
            System.out.println("Archivo serializado con éxito.");
        } catch (IOException e) {
            System.out.printf("Error al serializar el objeto: %s\n", e.getMessage());
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static ArrayList<ArticlesCompra> deserialitzarLlista() {
        ArrayList<ArticlesCompra> llistaCompra = null;
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("./llista_compra.ser");
            objectInputStream = new ObjectInputStream(fileInputStream);
            llistaCompra = (ArrayList<ArticlesCompra>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("Error al deserializar el objeto: %s\n", e.getMessage());
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return llistaCompra;
    }
} // End of the Main class