
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/

import java.io.IOException;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    Wearable wearable;
    Phones phones;
    Laptops laptops;
    VoiceAssistant voiceAssistant;

	public static HashMap<String, Product> products;

	Product product;
    static HashMap<String, Wearable> wearables;
    static HashMap<String, Phones> phone;
    static HashMap<String, Laptops> laptop;
    static HashMap<String, VoiceAssistant> voice;
    String consoleXmlFileName;

    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
    this.consoleXmlFileName = consoleXmlFileName;
    wearables = new HashMap<String, Wearable>();
	phone=new  HashMap<String, Phones>();
	laptop=new HashMap<String, Laptops>();
	voice=new HashMap<String, VoiceAssistant>();
	parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for wearable,games etc
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("wearable"))
		{
			currentElement="wearable";
			wearable = new Wearable();
            wearable.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("laptops"))
		{
			currentElement="laptops";
			laptops = new Laptops();
            laptops.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("phones"))
		{
			currentElement="phones";
			phones = new Phones();
            phones.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("voiceAssistant"))
		{
			currentElement="voiceAssistant";
			voiceAssistant =new VoiceAssistant();
			voiceAssistant.setId(attributes.getValue("id"));
	    }


    }
	// when xml end element is parsed store the data into respective hashmap for wearable,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("wearable")) {
			wearables.put(wearable.getId(), wearable);
			return;
        }
 
        if (element.equals("laptops")) {
			laptop.put(laptops.getId(), laptops);
			return;
        }
        if (element.equals("phones")) {
			phone.put(phones.getId(), phones);
			return;
        }
        if (element.equals("voiceAssistant")) {
			voice.put(voiceAssistant.getId(), voiceAssistant);
			return; 
        }

		if (element.equalsIgnoreCase("rebate")) {
			if(currentElement.equals("wearable"))
				wearable.setRebate(elementValueRead);
			if(currentElement.equals("phones"))
				phones.setRebate(elementValueRead);
			if(currentElement.equals("laptops"))
				laptops.setRebate(elementValueRead);
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setRebate(elementValueRead);
			return;
		}
		if (element.equalsIgnoreCase("inventory")) {
			if(currentElement.equals("wearable"))
				wearable.setInventory(Integer.parseInt(elementValueRead));
			if(currentElement.equals("phones"))
				phones.setInventory(Integer.parseInt(elementValueRead));
			if(currentElement.equals("laptops"))
				laptops.setInventory(Integer.parseInt(elementValueRead));
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setInventory(Integer.parseInt(elementValueRead));
			return;
		}
		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("wearable"))
				wearable.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("phones"))
				phones.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptops"))
				laptops.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setDiscount(Double.parseDouble(elementValueRead));
			return;
	    }

		if (element.equalsIgnoreCase("description")) {
			if(currentElement.equals("wearable"))
				wearable.setDescription(elementValueRead);
			if(currentElement.equals("phones"))
				phones.setDescription(elementValueRead);
			if(currentElement.equals("laptops"))
				laptops.setDescription(elementValueRead);
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setDescription(elementValueRead);
			return;
		}
		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("wearable"))
				wearable.setCondition(elementValueRead);
        	if(currentElement.equals("phones"))
				phones.setCondition(elementValueRead);
            if(currentElement.equals("laptops"))
				laptops.setCondition(elementValueRead);
            if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setCondition(elementValueRead);
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("wearable"))
				wearable.setRetailer(elementValueRead);
        	if(currentElement.equals("phones"))
				phones.setRetailer(elementValueRead);
            if(currentElement.equals("laptops"))
				laptops.setRetailer(elementValueRead);
            if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setRetailer(elementValueRead);
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("wearable"))
				wearable.setName(elementValueRead);
        	if(currentElement.equals("phones"))
				phones.setName(elementValueRead);
            if(currentElement.equals("laptops"))
				laptops.setName(elementValueRead);
            if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setName(elementValueRead);
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("wearable"))
				wearable.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("phones"))
				phones.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptops"))
				laptops.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setPrice(Double.parseDouble(elementValueRead));
			return;
        }

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {

		new SaxParserDataStore("/Users/mingxi/apache-tomcat-9.0.52/webapps/AS3/ProductCatalog.xml");
    } 
}
