// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Properties;

public class Property
{
    public static JSONObject toJSONObject(final Properties properties) {
        final JSONObject jsonObject = new JSONObject();
        if (properties != null && !properties.isEmpty()) {
            final Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                final String s = (String)propertyNames.nextElement();
                jsonObject.put(s, properties.getProperty(s));
            }
        }
        return jsonObject;
    }
    
    public static Properties toProperties(final JSONObject jsonObject) {
        final Properties properties = new Properties();
        if (jsonObject != null) {
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String string = keys.next().toString();
                ((Hashtable<String, String>)properties).put(string, jsonObject.getString(string));
            }
        }
        return properties;
    }
}
