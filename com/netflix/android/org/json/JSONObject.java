// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.util.Set;
import java.util.Collection;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

public class JSONObject
{
    public static final Object NULL;
    private final Map map;
    
    static {
        NULL = new JSONObject$Null(null);
    }
    
    public JSONObject() {
        this.map = new HashMap();
    }
    
    public JSONObject(final JSONObject jsonObject, final String[] array) {
        this();
        int n = 0;
    Label_0027_Outer:
        while (true) {
            if (n >= array.length) {
                return;
            }
            while (true) {
                try {
                    this.putOnce(array[n], jsonObject.opt(array[n]));
                    ++n;
                    continue Label_0027_Outer;
                }
                catch (Exception ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public JSONObject(final JSONTokener jsonTokener) {
        this();
        if (jsonTokener.nextClean() != '{') {
            throw jsonTokener.syntaxError("A JSONObject text must begin with '{'");
        }
        Label_0024: {
            break Label_0024;
            do {
                jsonTokener.back();
                switch (jsonTokener.nextClean()) {
                    default: {
                        jsonTokener.back();
                        final String string = jsonTokener.nextValue().toString();
                        if (jsonTokener.nextClean() != ':') {
                            throw jsonTokener.syntaxError("Expected a ':' after a key");
                        }
                        this.putOnce(string, jsonTokener.nextValue());
                        switch (jsonTokener.nextClean()) {
                            default: {
                                throw jsonTokener.syntaxError("Expected a ',' or '}'");
                            }
                            case ',':
                            case ';': {
                                continue;
                            }
                            case '}': {
                                break Label_0024;
                            }
                        }
                        break;
                    }
                    case '\0': {
                        throw jsonTokener.syntaxError("A JSONObject text must end with '}'");
                    }
                    case '}': {
                        break Label_0024;
                    }
                }
            } while (jsonTokener.nextClean() != '}');
        }
    }
    
    public JSONObject(final Object o) {
        this();
        this.populateMap(o);
    }
    
    public JSONObject(final Object o, final String[] array) {
        this();
        final Class<?> class1 = o.getClass();
        int n = 0;
    Label_0041_Outer:
        while (true) {
            if (n >= array.length) {
                return;
            }
            final String s = array[n];
            while (true) {
                try {
                    this.putOpt(s, class1.getField(s).get(o));
                    ++n;
                    continue Label_0041_Outer;
                }
                catch (Exception ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public JSONObject(final String s) {
        this(new JSONTokener(s));
    }
    
    public JSONObject(final String s, final Locale locale) {
        this();
        final ResourceBundle bundle = ResourceBundle.getBundle(s, locale, Thread.currentThread().getContextClassLoader());
        final Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            final String nextElement = keys.nextElement();
            if (nextElement instanceof String) {
                final String[] split = nextElement.split("\\.");
                final int n = split.length - 1;
                int i = 0;
                JSONObject jsonObject = this;
                while (i < n) {
                    final String s2 = split[i];
                    JSONObject optJSONObject;
                    if ((optJSONObject = jsonObject.optJSONObject(s2)) == null) {
                        optJSONObject = new JSONObject();
                        jsonObject.put(s2, optJSONObject);
                    }
                    ++i;
                    jsonObject = optJSONObject;
                }
                jsonObject.put(split[n], bundle.getString(nextElement));
            }
        }
    }
    
    public JSONObject(final Map map) {
        this.map = new HashMap();
        if (map != null) {
            for (final Map.Entry<K, Object> entry : map.entrySet()) {
                final Object value = entry.getValue();
                if (value != null) {
                    this.map.put(entry.getKey(), wrap(value));
                }
            }
        }
    }
    
    public static String doubleToString(final double n) {
        String string;
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            string = "null";
        }
        else {
            String substring = string = Double.toString(n);
            if (substring.indexOf(46) > 0) {
                string = substring;
                if (substring.indexOf(101) < 0) {
                    string = substring;
                    if (substring.indexOf(69) < 0) {
                        while (substring.endsWith("0")) {
                            substring = substring.substring(0, substring.length() - 1);
                        }
                        string = substring;
                        if (substring.endsWith(".")) {
                            return substring.substring(0, substring.length() - 1);
                        }
                    }
                }
            }
        }
        return string;
    }
    
    public static String[] getNames(final JSONObject jsonObject) {
        final int length = jsonObject.length();
        if (length == 0) {
            return null;
        }
        final Iterator keys = jsonObject.keys();
        final String[] array = new String[length];
        int n = 0;
        while (keys.hasNext()) {
            array[n] = keys.next();
            ++n;
        }
        return array;
    }
    
    public static String[] getNames(final Object o) {
        final String[] array = null;
        String[] array2;
        if (o == null) {
            array2 = array;
        }
        else {
            final Field[] fields = o.getClass().getFields();
            final int length = fields.length;
            array2 = array;
            if (length != 0) {
                final String[] array3 = new String[length];
                int n = 0;
                while (true) {
                    array2 = array3;
                    if (n >= length) {
                        break;
                    }
                    array3[n] = fields[n].getName();
                    ++n;
                }
            }
        }
        return array2;
    }
    
    static final void indent(final Writer writer, final int n) {
        for (int i = 0; i < n; ++i) {
            writer.write(32);
        }
    }
    
    public static String numberToString(final Number n) {
        if (n == null) {
            throw new JSONException("Null pointer");
        }
        testValidity(n);
        String s;
        String substring = s = n.toString();
        if (substring.indexOf(46) > 0) {
            s = substring;
            if (substring.indexOf(101) < 0) {
                s = substring;
                if (substring.indexOf(69) < 0) {
                    while (substring.endsWith("0")) {
                        substring = substring.substring(0, substring.length() - 1);
                    }
                    s = substring;
                    if (substring.endsWith(".")) {
                        s = substring.substring(0, substring.length() - 1);
                    }
                }
            }
        }
        return s;
    }
    
    private void populateMap(final Object o) {
        final boolean b = false;
        final Class<?> class1 = o.getClass();
    Label_0194_Outer:
        while (true) {
            int n = 0;
            Method[] array = null;
            Label_0031: {
                while (true) {
                    Label_0018: {
                        if (class1.getClassLoader() != null) {
                            n = 1;
                            break Label_0018;
                        }
                        Label_0189: {
                            break Label_0189;
                            while (true) {
                                final Method method = array[n];
                                while (true) {
                                    Label_0297: {
                                        while (true) {
                                            Label_0294: {
                                                while (true) {
                                                    try {
                                                        if (Modifier.isPublic(method.getModifiers())) {
                                                            final String name = method.getName();
                                                            String s = "";
                                                            if (name.startsWith("get")) {
                                                                if ("getClass".equals(name) || "getDeclaringClass".equals(name)) {
                                                                    break Label_0297;
                                                                }
                                                                s = name.substring(3);
                                                            }
                                                            else if (name.startsWith("is")) {
                                                                s = name.substring(2);
                                                            }
                                                            if (s.length() > 0 && Character.isUpperCase(s.charAt(0)) && method.getParameterTypes().length == 0) {
                                                                if (s.length() == 1) {
                                                                    s = s.toLowerCase();
                                                                }
                                                                else {
                                                                    if (Character.isUpperCase(s.charAt(1))) {
                                                                        break Label_0294;
                                                                    }
                                                                    s = s.substring(0, 1).toLowerCase() + s.substring(1);
                                                                }
                                                                final Object invoke = method.invoke(o, (Object[])null);
                                                                if (invoke != null) {
                                                                    this.map.put(s, wrap(invoke));
                                                                }
                                                            }
                                                        }
                                                        ++n;
                                                        break Label_0031;
                                                        n = 0;
                                                        break;
                                                        array = class1.getDeclaredMethods();
                                                        n = (b ? 1 : 0);
                                                        break Label_0031;
                                                    }
                                                    catch (Exception ex) {
                                                        continue Label_0194_Outer;
                                                    }
                                                    break;
                                                }
                                            }
                                            continue Label_0194_Outer;
                                        }
                                    }
                                    String s = "";
                                    continue Label_0194_Outer;
                                }
                            }
                        }
                    }
                    if (n == 0) {
                        continue;
                    }
                    break;
                }
                array = class1.getMethods();
                n = (b ? 1 : 0);
            }
            if (n < array.length) {
                continue;
            }
            break;
        }
    }
    
    public static Writer quote(final String s, final Writer writer) {
        if (s == null || s.length() == 0) {
            writer.write("\"\"");
            return writer;
        }
        final int length = s.length();
        writer.write(34);
        int i = 0;
        int n = 0;
        while (i < length) {
            final char char1 = s.charAt(i);
            switch (char1) {
                default: {
                    if (char1 < ' ' || (char1 >= '\u0080' && char1 < ' ') || (char1 >= '\u2000' && char1 < '\u2100')) {
                        writer.write("\\u");
                        final String hexString = Integer.toHexString(char1);
                        writer.write("0000", 0, 4 - hexString.length());
                        writer.write(hexString);
                        break;
                    }
                    writer.write(char1);
                    break;
                }
                case 34:
                case 92: {
                    writer.write(92);
                    writer.write(char1);
                    break;
                }
                case 47: {
                    if (n == 60) {
                        writer.write(92);
                    }
                    writer.write(char1);
                    break;
                }
                case 8: {
                    writer.write("\\b");
                    break;
                }
                case 9: {
                    writer.write("\\t");
                    break;
                }
                case 10: {
                    writer.write("\\n");
                    break;
                }
                case 12: {
                    writer.write("\\f");
                    break;
                }
                case 13: {
                    writer.write("\\r");
                    break;
                }
            }
            ++i;
            n = char1;
        }
        writer.write(34);
        return writer;
    }
    
    public static String quote(String string) {
        final StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            try {
                string = quote(string, stringWriter).toString();
                return string;
            }
            catch (IOException ex) {
                return "";
            }
        }
    }
    
    public static Object stringToValue(final String s) {
        if (!s.equals("")) {
            if (s.equalsIgnoreCase("true")) {
                return Boolean.TRUE;
            }
            if (s.equalsIgnoreCase("false")) {
                return Boolean.FALSE;
            }
            if (s.equalsIgnoreCase("null")) {
                return JSONObject.NULL;
            }
            final char char1 = s.charAt(0);
            if (char1 < '0' || char1 > '9') {
                if (char1 != '-') {
                    return s;
                }
            }
            try {
                if (s.indexOf(46) > -1 || s.indexOf(101) > -1 || s.indexOf(69) > -1) {
                    final Double value = Double.valueOf(s);
                    if (!value.isInfinite() && !value.isNaN()) {
                        return value;
                    }
                }
                else {
                    final Long n = new Long(s);
                    if (s.equals(n.toString())) {
                        if (n == (int)(Object)n) {
                            return new Integer((int)(Object)n);
                        }
                        return n;
                    }
                }
            }
            catch (Exception ex) {
                return s;
            }
        }
        return s;
    }
    
    public static void testValidity(final Object o) {
        if (o != null) {
            if (o instanceof Double) {
                if (((Double)o).isInfinite() || ((Double)o).isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
            else if (o instanceof Float && (((Float)o).isInfinite() || ((Float)o).isNaN())) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }
    
    public static String valueToString(final Object o) {
        if (o == null || o.equals(null)) {
            return "null";
        }
        if (o instanceof JSONString) {
            String jsonString;
            try {
                jsonString = ((JSONString)o).toJSONString();
                if (jsonString instanceof String) {
                    return jsonString;
                }
            }
            catch (Exception ex) {
                throw new JSONException(ex);
            }
            throw new JSONException("Bad value from toJSONString: " + (Object)jsonString);
        }
        if (o instanceof Number) {
            return numberToString((Number)o);
        }
        if (o instanceof Boolean || o instanceof JSONObject || o instanceof JSONArray) {
            return o.toString();
        }
        if (o instanceof Map) {
            return new JSONObject((Map)o).toString();
        }
        if (o instanceof Collection) {
            return new JSONArray((Collection)o).toString();
        }
        if (o.getClass().isArray()) {
            return new JSONArray(o).toString();
        }
        return quote(o.toString());
    }
    
    public static Object wrap(Object o) {
        while (true) {
            if (o == null) {
                Package package1;
                Object o2 = null;
                String name;
                Block_5_Outer:Block_12_Outer:Label_0202_Outer:
                while (true) {
                    while (true) {
                        try {
                            return JSONObject.NULL;
                            Label_0166: {
                                return new JSONObject((Map)o);
                            }
                            // iftrue(Label_0185:, !o instanceof Map)
                            Label_0185: {
                                package1 = o.getClass().getPackage();
                            }
                            // iftrue(Label_0253:, package1 == null)
                            // iftrue(Label_0166:, !o.getClass().isArray())
                            // iftrue(Label_0251:, o instanceof Short)
                            // iftrue(Label_0251:, o instanceof JSONString)
                            // iftrue(Label_0251:, o instanceof Byte)
                            // iftrue(Label_0251:, o instanceof JSONArray)
                            // iftrue(Label_0251:, o instanceof Integer)
                            // iftrue(Label_0251:, o instanceof Character)
                            // iftrue(Label_0251:, o instanceof Float)
                            // iftrue(Label_0251:, o instanceof JSONObject)
                            // iftrue(Label_0251:, JSONObject.NULL.equals(o))
                            // iftrue(Label_0251:, o instanceof Double)
                            // iftrue(Label_0237:, !name.startsWith("java.") && !name.startsWith("javax.") && o.getClass().getClassLoader() != null)
                            // iftrue(Label_0251:, o instanceof Boolean)
                            // iftrue(Label_0147:, !o instanceof Collection)
                            // iftrue(Label_0251:, o instanceof String)
                            while (true) {
                                Block_10: {
                                    while (true) {
                                        Block_14: {
                                            while (true) {
                                                Block_19: {
                                                    break Block_19;
                                                    Label_0147:
                                                    return new JSONArray(o);
                                                    Block_13: {
                                                    Block_3_Outer:
                                                        while (true) {
                                                        Block_4:
                                                            while (true) {
                                                                while (true) {
                                                                    o2 = o;
                                                                    Block_7: {
                                                                        Block_9: {
                                                                            break Block_9;
                                                                            o2 = o;
                                                                            o2 = o;
                                                                            break Block_7;
                                                                            o2 = o;
                                                                            break Block_4;
                                                                        }
                                                                        o2 = o;
                                                                        break Block_10;
                                                                    }
                                                                    o2 = o;
                                                                    continue Block_5_Outer;
                                                                }
                                                                o2 = o;
                                                                break Block_13;
                                                                Label_0237:
                                                                o = new JSONObject(o);
                                                                return o;
                                                                o2 = o;
                                                                continue Block_12_Outer;
                                                            }
                                                            o2 = o;
                                                            continue Block_3_Outer;
                                                        }
                                                    }
                                                    o2 = o;
                                                    break Block_14;
                                                }
                                                name = package1.getName();
                                                return o.toString();
                                                o2 = o;
                                                continue Label_0202_Outer;
                                            }
                                            return new JSONArray((Collection)o);
                                        }
                                        o2 = o;
                                        continue;
                                    }
                                }
                                o2 = o;
                                continue;
                            }
                        }
                        // iftrue(Label_0251:, o instanceof Long)
                        catch (Exception ex) {
                            o2 = null;
                        }
                        break;
                        Label_0253: {
                            name = "";
                        }
                        continue;
                    }
                }
                Label_0251: {
                    return o2;
                }
            }
            continue;
        }
    }
    
    static final Writer writeValue(final Writer writer, final Object o, final int n, final int n2) {
        if (o == null || o.equals(null)) {
            writer.write("null");
            return writer;
        }
        if (o instanceof JSONObject) {
            ((JSONObject)o).write(writer, n, n2);
            return writer;
        }
        if (o instanceof JSONArray) {
            ((JSONArray)o).write(writer, n, n2);
            return writer;
        }
        if (o instanceof Map) {
            new JSONObject((Map)o).write(writer, n, n2);
            return writer;
        }
        if (o instanceof Collection) {
            new JSONArray((Collection)o).write(writer, n, n2);
            return writer;
        }
        if (o.getClass().isArray()) {
            new JSONArray(o).write(writer, n, n2);
            return writer;
        }
        if (o instanceof Number) {
            writer.write(numberToString((Number)o));
            return writer;
        }
        if (o instanceof Boolean) {
            writer.write(o.toString());
            return writer;
        }
        if (!(o instanceof JSONString)) {
            quote(o.toString(), writer);
            return writer;
        }
        while (true) {
            while (true) {
                try {
                    final String jsonString = ((JSONString)o).toJSONString();
                    if (jsonString != null) {
                        final String s = jsonString.toString();
                        writer.write(s);
                        return writer;
                    }
                }
                catch (Exception ex) {
                    throw new JSONException(ex);
                }
                final String s = quote(o.toString());
                continue;
            }
        }
    }
    
    public JSONObject accumulate(final String s, final Object o) {
        testValidity(o);
        final Object opt = this.opt(s);
        if (opt == null) {
            Object put = o;
            if (o instanceof JSONArray) {
                put = new JSONArray().put(o);
            }
            this.put(s, put);
            return this;
        }
        if (opt instanceof JSONArray) {
            ((JSONArray)opt).put(o);
            return this;
        }
        this.put(s, new JSONArray().put(opt).put(o));
        return this;
    }
    
    public JSONObject append(final String s, final Object o) {
        testValidity(o);
        final Object opt = this.opt(s);
        if (opt == null) {
            this.put(s, new JSONArray().put(o));
            return this;
        }
        if (opt instanceof JSONArray) {
            this.put(s, ((JSONArray)opt).put(o));
            return this;
        }
        throw new JSONException("JSONObject[" + s + "] is not a JSONArray.");
    }
    
    public Object get(final String s) {
        if (s == null) {
            throw new JSONException("Null key.");
        }
        final Object opt = this.opt(s);
        if (opt == null) {
            throw new JSONException("JSONObject[" + quote(s) + "] not found.");
        }
        return opt;
    }
    
    public boolean getBoolean(final String s) {
        final Object value = this.get(s);
        if (value.equals(Boolean.FALSE) || (value instanceof String && ((String)value).equalsIgnoreCase("false"))) {
            return false;
        }
        if (value.equals(Boolean.TRUE) || (value instanceof String && ((String)value).equalsIgnoreCase("true"))) {
            return true;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] is not a Boolean.");
    }
    
    public double getDouble(final String s) {
        final Object value = this.get(s);
        try {
            if (value instanceof Number) {
                return ((Number)value).doubleValue();
            }
            return Double.parseDouble((String)value);
        }
        catch (Exception ex) {
            throw new JSONException("JSONObject[" + quote(s) + "] is not a number.");
        }
    }
    
    public int getInt(final String s) {
        final Object value = this.get(s);
        try {
            if (value instanceof Number) {
                return ((Number)value).intValue();
            }
            return Integer.parseInt((String)value);
        }
        catch (Exception ex) {
            throw new JSONException("JSONObject[" + quote(s) + "] is not an int.");
        }
    }
    
    public JSONArray getJSONArray(final String s) {
        final Object value = this.get(s);
        if (value instanceof JSONArray) {
            return (JSONArray)value;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] is not a JSONArray.");
    }
    
    public JSONObject getJSONObject(final String s) {
        final Object value = this.get(s);
        if (value instanceof JSONObject) {
            return (JSONObject)value;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] is not a JSONObject.");
    }
    
    public long getLong(final String s) {
        final Object value = this.get(s);
        try {
            if (value instanceof Number) {
                return ((Number)value).longValue();
            }
            return Long.parseLong((String)value);
        }
        catch (Exception ex) {
            throw new JSONException("JSONObject[" + quote(s) + "] is not a long.");
        }
    }
    
    public String getString(final String s) {
        final Object value = this.get(s);
        if (value instanceof String) {
            return (String)value;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] not a string.");
    }
    
    public boolean has(final String s) {
        return this.map.containsKey(s);
    }
    
    public JSONObject increment(final String s) {
        final Object opt = this.opt(s);
        if (opt == null) {
            this.put(s, 1);
            return this;
        }
        if (opt instanceof Integer) {
            this.put(s, (int)opt + 1);
            return this;
        }
        if (opt instanceof Long) {
            this.put(s, (long)opt + 1L);
            return this;
        }
        if (opt instanceof Double) {
            this.put(s, (double)opt + 1.0);
            return this;
        }
        if (opt instanceof Float) {
            this.put(s, (float)opt + 1.0f);
            return this;
        }
        throw new JSONException("Unable to increment [" + quote(s) + "].");
    }
    
    public boolean isNull(final String s) {
        return JSONObject.NULL.equals(this.opt(s));
    }
    
    public Set keySet() {
        return this.map.keySet();
    }
    
    public Iterator keys() {
        return this.keySet().iterator();
    }
    
    public int length() {
        return this.map.size();
    }
    
    public JSONArray names() {
        final JSONArray jsonArray = new JSONArray();
        final Iterator keys = this.keys();
        while (keys.hasNext()) {
            jsonArray.put(keys.next());
        }
        JSONArray jsonArray2 = jsonArray;
        if (jsonArray.length() == 0) {
            jsonArray2 = null;
        }
        return jsonArray2;
    }
    
    public Object opt(final String s) {
        if (s == null) {
            return null;
        }
        return this.map.get(s);
    }
    
    public boolean optBoolean(final String s) {
        return this.optBoolean(s, false);
    }
    
    public boolean optBoolean(final String s, final boolean b) {
        try {
            return this.getBoolean(s);
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    public double optDouble(final String s) {
        return this.optDouble(s, Double.NaN);
    }
    
    public double optDouble(final String s, final double n) {
        try {
            return this.getDouble(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public int optInt(final String s) {
        return this.optInt(s, 0);
    }
    
    public int optInt(final String s, final int n) {
        try {
            return this.getInt(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public JSONArray optJSONArray(final String s) {
        final Object opt = this.opt(s);
        if (opt instanceof JSONArray) {
            return (JSONArray)opt;
        }
        return null;
    }
    
    public JSONObject optJSONObject(final String s) {
        final Object opt = this.opt(s);
        if (opt instanceof JSONObject) {
            return (JSONObject)opt;
        }
        return null;
    }
    
    public long optLong(final String s) {
        return this.optLong(s, 0L);
    }
    
    public long optLong(final String s, final long n) {
        try {
            return this.getLong(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public String optString(final String s) {
        return this.optString(s, "");
    }
    
    public String optString(final String s, final String s2) {
        final Object opt = this.opt(s);
        if (JSONObject.NULL.equals(opt)) {
            return s2;
        }
        return opt.toString();
    }
    
    public JSONObject put(final String s, final double n) {
        this.put(s, new Double(n));
        return this;
    }
    
    public JSONObject put(final String s, final int n) {
        this.put(s, new Integer(n));
        return this;
    }
    
    public JSONObject put(final String s, final long n) {
        this.put(s, new Long(n));
        return this;
    }
    
    public JSONObject put(final String s, final Object o) {
        if (s == null) {
            throw new NullPointerException("Null key.");
        }
        if (o != null) {
            testValidity(o);
            this.map.put(s, o);
            return this;
        }
        this.remove(s);
        return this;
    }
    
    public JSONObject put(final String s, final Collection collection) {
        this.put(s, new JSONArray(collection));
        return this;
    }
    
    public JSONObject put(final String s, final Map map) {
        this.put(s, new JSONObject(map));
        return this;
    }
    
    public JSONObject put(final String s, final boolean b) {
        Boolean b2;
        if (b) {
            b2 = Boolean.TRUE;
        }
        else {
            b2 = Boolean.FALSE;
        }
        this.put(s, b2);
        return this;
    }
    
    public JSONObject putOnce(final String s, final Object o) {
        if (s != null && o != null) {
            if (this.opt(s) != null) {
                throw new JSONException("Duplicate key \"" + s + "\"");
            }
            this.put(s, o);
        }
        return this;
    }
    
    public JSONObject putOpt(final String s, final Object o) {
        if (s != null && o != null) {
            this.put(s, o);
        }
        return this;
    }
    
    public Object remove(final String s) {
        return this.map.remove(s);
    }
    
    public JSONArray toJSONArray(final JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }
        final JSONArray jsonArray2 = new JSONArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            jsonArray2.put(this.opt(jsonArray.getString(i)));
        }
        return jsonArray2;
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
        Label_0090_Outer:
            while (true) {
                int length = 0;
                Iterator keys;
                Object next;
                Object next2;
                int n4;
                Label_0164_Outer:Block_9_Outer:Label_0130_Outer:
                while (true) {
                    Label_0217: {
                        try {
                            length = this.length();
                            keys = this.keys();
                            writer.write(123);
                            if (length == 1) {
                                next = keys.next();
                                writer.write(quote(next.toString()));
                                writer.write(58);
                                if (n > 0) {
                                    writer.write(32);
                                }
                                writeValue(writer, this.map.get(next), n, n2);
                                writer.write(125);
                                return writer;
                            }
                            break Label_0217;
                        Label_0189:
                            while (true) {
                                Block_8: {
                                    Block_10: {
                                        break Block_10;
                                        while (true) {
                                            Block_6: {
                                                break Block_6;
                                                break Block_8;
                                            }
                                            next2 = keys.next();
                                            writer.write(44);
                                            continue Block_9_Outer;
                                        }
                                    }
                                    writer.write(10);
                                    while (true) {
                                        Label_0199: {
                                            break Label_0199;
                                            while (true) {
                                                writeValue(writer, this.map.get(next2), n, n4);
                                                n3 = 1;
                                                continue Label_0164_Outer;
                                                writer.write(32);
                                                continue Block_9_Outer;
                                            }
                                        }
                                        indent(writer, n2);
                                        continue Label_0090_Outer;
                                        indent(writer, n4);
                                        writer.write(quote(next2.toString()));
                                        writer.write(58);
                                        continue Label_0130_Outer;
                                    }
                                }
                                writer.write(10);
                                continue;
                            }
                        }
                        // iftrue(Label_0199:, n <= 0)
                        // iftrue(Label_0189:, !keys.hasNext())
                        // iftrue(Label_0130:, n <= 0)
                        // iftrue(Label_0120:, n3 == 0)
                        // iftrue(Label_0164:, n <= 0)
                        catch (IOException ex) {
                            throw new JSONException(ex);
                        }
                    }
                    if (length != 0) {
                        n4 = n2 + n;
                        continue;
                    }
                    break;
                }
                continue Label_0090_Outer;
            }
        }
    }
}
