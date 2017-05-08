// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.io.IOException;
import java.io.Writer;
import java.io.StringWriter;
import java.util.Map;
import java.util.Iterator;
import java.util.Collection;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class JSONArray
{
    private final ArrayList myArrayList;
    
    public JSONArray() {
        this.myArrayList = new ArrayList();
    }
    
    public JSONArray(final JSONTokener jsonTokener) {
        this();
        if (jsonTokener.nextClean() != '[') {
            throw jsonTokener.syntaxError("A JSONArray text must start with '['");
        }
        Label_0123: {
            if (jsonTokener.nextClean() != ']') {
                jsonTokener.back();
                while (true) {
                    if (jsonTokener.nextClean() == ',') {
                        jsonTokener.back();
                        this.myArrayList.add(JSONObject.NULL);
                    }
                    else {
                        jsonTokener.back();
                        this.myArrayList.add(jsonTokener.nextValue());
                    }
                    switch (jsonTokener.nextClean()) {
                        default: {
                            throw jsonTokener.syntaxError("Expected a ',' or ']'");
                        }
                        case ',': {
                            if (jsonTokener.nextClean() == ']') {
                                break Label_0123;
                            }
                            jsonTokener.back();
                            continue;
                        }
                        case ']': {
                            break Label_0123;
                        }
                    }
                }
            }
        }
    }
    
    public JSONArray(final Object o) {
        this();
        if (o.getClass().isArray()) {
            for (int length = Array.getLength(o), i = 0; i < length; ++i) {
                this.put(JSONObject.wrap(Array.get(o, i)));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }
    
    public JSONArray(final String s) {
        this(new JSONTokener(s));
    }
    
    public JSONArray(final Collection collection) {
        this.myArrayList = new ArrayList();
        if (collection != null) {
            final Iterator<Object> iterator = collection.iterator();
            while (iterator.hasNext()) {
                this.myArrayList.add(JSONObject.wrap(iterator.next()));
            }
        }
    }
    
    public Object get(final int n) {
        final Object opt = this.opt(n);
        if (opt == null) {
            throw new JSONException("JSONArray[" + n + "] not found.");
        }
        return opt;
    }
    
    public boolean getBoolean(final int n) {
        final Object value = this.get(n);
        if (value.equals(Boolean.FALSE) || (value instanceof String && ((String)value).equalsIgnoreCase("false"))) {
            return false;
        }
        if (value.equals(Boolean.TRUE) || (value instanceof String && ((String)value).equalsIgnoreCase("true"))) {
            return true;
        }
        throw new JSONException("JSONArray[" + n + "] is not a boolean.");
    }
    
    public double getDouble(final int n) {
        final Object value = this.get(n);
        try {
            if (value instanceof Number) {
                return ((Number)value).doubleValue();
            }
            return Double.parseDouble((String)value);
        }
        catch (Exception ex) {
            throw new JSONException("JSONArray[" + n + "] is not a number.");
        }
    }
    
    public int getInt(final int n) {
        final Object value = this.get(n);
        try {
            if (value instanceof Number) {
                return ((Number)value).intValue();
            }
            return Integer.parseInt((String)value);
        }
        catch (Exception ex) {
            throw new JSONException("JSONArray[" + n + "] is not a number.");
        }
    }
    
    public JSONArray getJSONArray(final int n) {
        final Object value = this.get(n);
        if (value instanceof JSONArray) {
            return (JSONArray)value;
        }
        throw new JSONException("JSONArray[" + n + "] is not a JSONArray.");
    }
    
    public JSONObject getJSONObject(final int n) {
        final Object value = this.get(n);
        if (value instanceof JSONObject) {
            return (JSONObject)value;
        }
        throw new JSONException("JSONArray[" + n + "] is not a JSONObject.");
    }
    
    public long getLong(final int n) {
        final Object value = this.get(n);
        try {
            if (value instanceof Number) {
                return ((Number)value).longValue();
            }
            return Long.parseLong((String)value);
        }
        catch (Exception ex) {
            throw new JSONException("JSONArray[" + n + "] is not a number.");
        }
    }
    
    public String getString(final int n) {
        final Object value = this.get(n);
        if (value instanceof String) {
            return (String)value;
        }
        throw new JSONException("JSONArray[" + n + "] not a string.");
    }
    
    public boolean isNull(final int n) {
        return JSONObject.NULL.equals(this.opt(n));
    }
    
    public String join(final String s) {
        final int length = this.length();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            if (i > 0) {
                sb.append(s);
            }
            sb.append(JSONObject.valueToString(this.myArrayList.get(i)));
        }
        return sb.toString();
    }
    
    public int length() {
        return this.myArrayList.size();
    }
    
    public Object opt(final int n) {
        if (n < 0 || n >= this.length()) {
            return null;
        }
        return this.myArrayList.get(n);
    }
    
    public boolean optBoolean(final int n) {
        return this.optBoolean(n, false);
    }
    
    public boolean optBoolean(final int n, final boolean b) {
        try {
            return this.getBoolean(n);
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    public double optDouble(final int n) {
        return this.optDouble(n, Double.NaN);
    }
    
    public double optDouble(final int n, final double n2) {
        try {
            return this.getDouble(n);
        }
        catch (Exception ex) {
            return n2;
        }
    }
    
    public int optInt(final int n) {
        return this.optInt(n, 0);
    }
    
    public int optInt(int int1, final int n) {
        try {
            int1 = this.getInt(int1);
            return int1;
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public JSONArray optJSONArray(final int n) {
        final Object opt = this.opt(n);
        if (opt instanceof JSONArray) {
            return (JSONArray)opt;
        }
        return null;
    }
    
    public JSONObject optJSONObject(final int n) {
        final Object opt = this.opt(n);
        if (opt instanceof JSONObject) {
            return (JSONObject)opt;
        }
        return null;
    }
    
    public long optLong(final int n) {
        return this.optLong(n, 0L);
    }
    
    public long optLong(final int n, final long n2) {
        try {
            return this.getLong(n);
        }
        catch (Exception ex) {
            return n2;
        }
    }
    
    public String optString(final int n) {
        return this.optString(n, "");
    }
    
    public String optString(final int n, final String s) {
        final Object opt = this.opt(n);
        if (JSONObject.NULL.equals(opt)) {
            return s;
        }
        return opt.toString();
    }
    
    public JSONArray put(final double n) {
        final Double n2 = new Double(n);
        JSONObject.testValidity(n2);
        this.put(n2);
        return this;
    }
    
    public JSONArray put(final int n) {
        this.put(new Integer(n));
        return this;
    }
    
    public JSONArray put(final int n, final double n2) {
        this.put(n, new Double(n2));
        return this;
    }
    
    public JSONArray put(final int n, final int n2) {
        this.put(n, new Integer(n2));
        return this;
    }
    
    public JSONArray put(final int n, final long n2) {
        this.put(n, new Long(n2));
        return this;
    }
    
    public JSONArray put(final int i, final Object o) {
        JSONObject.testValidity(o);
        if (i < 0) {
            throw new JSONException("JSONArray[" + i + "] not found.");
        }
        if (i < this.length()) {
            this.myArrayList.set(i, o);
            return this;
        }
        while (i != this.length()) {
            this.put(JSONObject.NULL);
        }
        this.put(o);
        return this;
    }
    
    public JSONArray put(final int n, final Collection collection) {
        this.put(n, new JSONArray(collection));
        return this;
    }
    
    public JSONArray put(final int n, final Map map) {
        this.put(n, new JSONObject(map));
        return this;
    }
    
    public JSONArray put(final int n, final boolean b) {
        Boolean b2;
        if (b) {
            b2 = Boolean.TRUE;
        }
        else {
            b2 = Boolean.FALSE;
        }
        this.put(n, b2);
        return this;
    }
    
    public JSONArray put(final long n) {
        this.put(new Long(n));
        return this;
    }
    
    public JSONArray put(final Object o) {
        this.myArrayList.add(o);
        return this;
    }
    
    public JSONArray put(final Collection collection) {
        this.put(new JSONArray(collection));
        return this;
    }
    
    public JSONArray put(final Map map) {
        this.put(new JSONObject(map));
        return this;
    }
    
    public JSONArray put(final boolean b) {
        Boolean b2;
        if (b) {
            b2 = Boolean.TRUE;
        }
        else {
            b2 = Boolean.FALSE;
        }
        this.put(b2);
        return this;
    }
    
    public Object remove(final int n) {
        final Object opt = this.opt(n);
        this.myArrayList.remove(n);
        return opt;
    }
    
    public JSONObject toJSONObject(final JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0 || this.length() == 0) {
            return null;
        }
        final JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.length(); ++i) {
            jsonObject.put(jsonArray.getString(i), this.opt(i));
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        try {
            return this.toString(0);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String toString(final int n) {
        final StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            return this.write(stringWriter, n, 0).toString();
        }
    }
    
    public Writer write(final Writer writer) {
        return this.write(writer, 0, 0);
    }
    
    Writer write(final Writer writer, final int n, final int n2) {
        while (true) {
            int n3 = 0;
            int length = 0;
            int n4;
            int n5;
            Block_7_Outer:Label_0044_Outer:
            while (true) {
                Label_0044:Label_0062_Outer:Block_5_Outer:
                while (true) {
                    Label_0135: {
                        try {
                            length = this.length();
                            writer.write(91);
                            if (length == 1) {
                                JSONObject.writeValue(writer, this.myArrayList.get(0), n, n2);
                                writer.write(93);
                                return writer;
                            }
                            break Label_0135;
                            // iftrue(Label_0072:, n <= 0)
                            // iftrue(Label_0062:, n5 == 0)
                            // iftrue(Label_0117:, n <= 0)
                            while (true) {
                                while (true) {
                                Block_6:
                                    while (true) {
                                        Label_0117: {
                                            while (true) {
                                                writer.write(10);
                                                Label_0072: {
                                                    JSONObject.indent(writer, n4);
                                                }
                                                JSONObject.writeValue(writer, this.myArrayList.get(n3), n, n4);
                                                ++n3;
                                                n5 = 1;
                                                break Label_0044;
                                                writer.write(10);
                                                break Label_0117;
                                                continue Label_0044_Outer;
                                            }
                                            break Block_6;
                                        }
                                        JSONObject.indent(writer, n2);
                                        continue Block_7_Outer;
                                        Label_0107: {
                                            continue Label_0062_Outer;
                                        }
                                    }
                                    writer.write(44);
                                    continue Block_5_Outer;
                                }
                                continue;
                            }
                        }
                        // iftrue(Label_0107:, n3 >= length)
                        catch (IOException ex) {
                            throw new JSONException(ex);
                        }
                    }
                    if (length != 0) {
                        n4 = n2 + n;
                        n5 = 0;
                        continue Label_0044;
                    }
                    break;
                }
                continue Label_0044_Outer;
            }
        }
    }
}
