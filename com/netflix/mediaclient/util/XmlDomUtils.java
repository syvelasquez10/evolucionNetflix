// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import org.w3c.dom.Text;
import org.w3c.dom.Node;
import java.util.ArrayList;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import java.io.Reader;
import java.io.StringReader;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public final class XmlDomUtils
{
    public static Document createDocument(final String s) {
        final DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        final InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(s));
        return documentBuilder.parse(inputSource);
    }
    
    public static boolean getBooleanAttribute(final Element element, final String s) {
        final String attribute = element.getAttribute(s);
        return attribute != null && attribute.equalsIgnoreCase("true");
    }
    
    public static Element getChildElementByTagName(final Element element, final String s) {
        final NodeList childNodes = element.getChildNodes();
        for (int length = childNodes.getLength(), i = 0; i < length; ++i) {
            if (childNodes.item(i).getNodeType() == 1 && s.equals(childNodes.item(i).getNodeName())) {
                return (Element)childNodes.item(i);
            }
        }
        return null;
    }
    
    public static int getChildElementCountByTagName(final Element element, final String s) {
        int n = 0;
        final NodeList childNodes = element.getChildNodes();
        int n2;
        for (int length = childNodes.getLength(), i = 0; i < length; ++i, n = n2) {
            n2 = n;
            if (childNodes.item(i).getNodeType() == 1) {
                n2 = n;
                if (s.equals(childNodes.item(i).getNodeName())) {
                    n2 = n + 1;
                }
            }
        }
        return n;
    }
    
    public static Element[] getChildElementsByTagName(final Element element, final String s) {
        final ArrayList<Node> list = new ArrayList<Node>();
        final NodeList childNodes = element.getChildNodes();
        for (int length = childNodes.getLength(), i = 0; i < length; ++i) {
            if (childNodes.item(i).getNodeType() == 1 && s.equals(childNodes.item(i).getNodeName())) {
                list.add(childNodes.item(i));
            }
        }
        return list.toArray(new Element[list.size()]);
    }
    
    public static String getChildValueByTagName(Element childElementByTagName, final String s) {
        childElementByTagName = getChildElementByTagName(childElementByTagName, s);
        if (childElementByTagName != null) {
            return getElementText(childElementByTagName);
        }
        return null;
    }
    
    public static String getElementText(final Element element) {
        final NodeList childNodes = element.getChildNodes();
        for (int length = childNodes.getLength(), i = 0; i < length; ++i) {
            if (childNodes.item(i) instanceof Text) {
                return ((Text)childNodes.item(i)).getData();
            }
        }
        return null;
    }
    
    public static Element getFirstFoundElementByTagName(final Element element, final String s) {
        final NodeList elementsByTagName = element.getElementsByTagName(s);
        for (int length = elementsByTagName.getLength(), i = 0; i < length; ++i) {
            if (elementsByTagName.item(i).getNodeType() == 1) {
                return (Element)elementsByTagName.item(i);
            }
        }
        return null;
    }
    
    public static String getFirstFoundElementValueByTagName(Element firstFoundElementByTagName, final String s) {
        firstFoundElementByTagName = getFirstFoundElementByTagName(firstFoundElementByTagName, s);
        if (firstFoundElementByTagName != null) {
            return getElementText(firstFoundElementByTagName);
        }
        return null;
    }
}
