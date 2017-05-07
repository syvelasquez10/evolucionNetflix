// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.internal.Logger;
import java.util.Iterator;
import java.util.List;
import android.content.SharedPreferences$Editor;
import org.json.JSONException;
import org.json.JSONArray;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONObject;
import android.os.Bundle;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesTokenCachingStrategy extends TokenCachingStrategy
{
    private static final String DEFAULT_CACHE_KEY = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VALUE_ENUM_TYPE = "enumType";
    private static final String JSON_VALUE_TYPE = "valueType";
    private static final String TAG;
    private static final String TYPE_BOOLEAN = "bool";
    private static final String TYPE_BOOLEAN_ARRAY = "bool[]";
    private static final String TYPE_BYTE = "byte";
    private static final String TYPE_BYTE_ARRAY = "byte[]";
    private static final String TYPE_CHAR = "char";
    private static final String TYPE_CHAR_ARRAY = "char[]";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_DOUBLE_ARRAY = "double[]";
    private static final String TYPE_ENUM = "enum";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_FLOAT_ARRAY = "float[]";
    private static final String TYPE_INTEGER = "int";
    private static final String TYPE_INTEGER_ARRAY = "int[]";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_LONG_ARRAY = "long[]";
    private static final String TYPE_SHORT = "short";
    private static final String TYPE_SHORT_ARRAY = "short[]";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_STRING_LIST = "stringList";
    private SharedPreferences cache;
    private String cacheKey;
    
    static {
        TAG = SharedPreferencesTokenCachingStrategy.class.getSimpleName();
    }
    
    public SharedPreferencesTokenCachingStrategy(final Context context) {
        this(context, null);
    }
    
    public SharedPreferencesTokenCachingStrategy(Context context, final String s) {
        Validate.notNull(context, "context");
        String cacheKey = s;
        if (Utility.isNullOrEmpty(s)) {
            cacheKey = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
        }
        this.cacheKey = cacheKey;
        final Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.cache = context.getSharedPreferences(this.cacheKey, 0);
    }
    
    private void deserializeKey(final String s, final Bundle bundle) throws JSONException {
        final JSONObject jsonObject = new JSONObject(this.cache.getString(s, "{}"));
        final String string = jsonObject.getString("valueType");
        if (string.equals("bool")) {
            bundle.putBoolean(s, jsonObject.getBoolean("value"));
        }
        else {
            if (string.equals("bool[]")) {
                final JSONArray jsonArray = jsonObject.getJSONArray("value");
                final boolean[] array = new boolean[jsonArray.length()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = jsonArray.getBoolean(i);
                }
                bundle.putBooleanArray(s, array);
                return;
            }
            if (string.equals("byte")) {
                bundle.putByte(s, (byte)jsonObject.getInt("value"));
                return;
            }
            if (string.equals("byte[]")) {
                final JSONArray jsonArray2 = jsonObject.getJSONArray("value");
                final byte[] array2 = new byte[jsonArray2.length()];
                for (int j = 0; j < array2.length; ++j) {
                    array2[j] = (byte)jsonArray2.getInt(j);
                }
                bundle.putByteArray(s, array2);
                return;
            }
            if (string.equals("short")) {
                bundle.putShort(s, (short)jsonObject.getInt("value"));
                return;
            }
            if (string.equals("short[]")) {
                final JSONArray jsonArray3 = jsonObject.getJSONArray("value");
                final short[] array3 = new short[jsonArray3.length()];
                for (int k = 0; k < array3.length; ++k) {
                    array3[k] = (short)jsonArray3.getInt(k);
                }
                bundle.putShortArray(s, array3);
                return;
            }
            if (string.equals("int")) {
                bundle.putInt(s, jsonObject.getInt("value"));
                return;
            }
            if (string.equals("int[]")) {
                final JSONArray jsonArray4 = jsonObject.getJSONArray("value");
                final int[] array4 = new int[jsonArray4.length()];
                for (int l = 0; l < array4.length; ++l) {
                    array4[l] = jsonArray4.getInt(l);
                }
                bundle.putIntArray(s, array4);
                return;
            }
            if (string.equals("long")) {
                bundle.putLong(s, jsonObject.getLong("value"));
                return;
            }
            if (string.equals("long[]")) {
                final JSONArray jsonArray5 = jsonObject.getJSONArray("value");
                final long[] array5 = new long[jsonArray5.length()];
                for (int n = 0; n < array5.length; ++n) {
                    array5[n] = jsonArray5.getLong(n);
                }
                bundle.putLongArray(s, array5);
                return;
            }
            if (string.equals("float")) {
                bundle.putFloat(s, (float)jsonObject.getDouble("value"));
                return;
            }
            if (string.equals("float[]")) {
                final JSONArray jsonArray6 = jsonObject.getJSONArray("value");
                final float[] array6 = new float[jsonArray6.length()];
                for (int n2 = 0; n2 < array6.length; ++n2) {
                    array6[n2] = (float)jsonArray6.getDouble(n2);
                }
                bundle.putFloatArray(s, array6);
                return;
            }
            if (string.equals("double")) {
                bundle.putDouble(s, jsonObject.getDouble("value"));
                return;
            }
            if (string.equals("double[]")) {
                final JSONArray jsonArray7 = jsonObject.getJSONArray("value");
                final double[] array7 = new double[jsonArray7.length()];
                for (int n3 = 0; n3 < array7.length; ++n3) {
                    array7[n3] = jsonArray7.getDouble(n3);
                }
                bundle.putDoubleArray(s, array7);
                return;
            }
            if (string.equals("char")) {
                final String string2 = jsonObject.getString("value");
                if (string2 != null && string2.length() == 1) {
                    bundle.putChar(s, string2.charAt(0));
                }
            }
            else {
                if (string.equals("char[]")) {
                    final JSONArray jsonArray8 = jsonObject.getJSONArray("value");
                    final char[] array8 = new char[jsonArray8.length()];
                    for (int n4 = 0; n4 < array8.length; ++n4) {
                        final String string3 = jsonArray8.getString(n4);
                        if (string3 != null && string3.length() == 1) {
                            array8[n4] = string3.charAt(0);
                        }
                    }
                    bundle.putCharArray(s, array8);
                    return;
                }
                if (string.equals("string")) {
                    bundle.putString(s, jsonObject.getString("value"));
                    return;
                }
                if (string.equals("stringList")) {
                    final JSONArray jsonArray9 = jsonObject.getJSONArray("value");
                    final int length = jsonArray9.length();
                    final ArrayList list = new ArrayList<String>(length);
                    for (int n5 = 0; n5 < length; ++n5) {
                        final Object value = jsonArray9.get(n5);
                        String s2;
                        if (value == JSONObject.NULL) {
                            s2 = null;
                        }
                        else {
                            s2 = (String)value;
                        }
                        list.add(n5, s2);
                    }
                    bundle.putStringArrayList(s, list);
                    return;
                }
                if (string.equals("enum")) {
                    try {
                        bundle.putSerializable(s, Enum.valueOf(Class.forName(jsonObject.getString("enumType")), jsonObject.getString("value")));
                    }
                    catch (ClassNotFoundException ex) {}
                    catch (IllegalArgumentException ex2) {}
                }
            }
        }
    }
    
    private void serializeKey(final String s, final Bundle bundle, final SharedPreferences$Editor sharedPreferences$Editor) throws JSONException {
        final Object value = bundle.get(s);
        if (value != null) {
            Object o = null;
            Object o2 = null;
            final JSONObject jsonObject = new JSONObject();
            if (value instanceof Byte) {
                o = "byte";
                jsonObject.put("value", (int)value);
            }
            else if (value instanceof Short) {
                o = "short";
                jsonObject.put("value", (int)value);
            }
            else if (value instanceof Integer) {
                o = "int";
                jsonObject.put("value", (int)value);
            }
            else if (value instanceof Long) {
                o = "long";
                jsonObject.put("value", (long)value);
            }
            else if (value instanceof Float) {
                o = "float";
                jsonObject.put("value", (double)value);
            }
            else if (value instanceof Double) {
                o = "double";
                jsonObject.put("value", (double)value);
            }
            else if (value instanceof Boolean) {
                o = "bool";
                jsonObject.put("value", (boolean)value);
            }
            else if (value instanceof Character) {
                o = "char";
                jsonObject.put("value", (Object)value.toString());
            }
            else if (value instanceof String) {
                o = "string";
                jsonObject.put("value", (Object)value);
            }
            else if (value instanceof Enum) {
                o = "enum";
                jsonObject.put("value", (Object)value.toString());
                jsonObject.put("enumType", (Object)((List<String>)value).getClass().getName());
            }
            else {
                final JSONArray jsonArray = new JSONArray();
                if (value instanceof byte[]) {
                    final String s2 = "byte[]";
                    final byte[] array = (byte[])value;
                    final int length = array.length;
                    int n = 0;
                    while (true) {
                        o2 = jsonArray;
                        o = s2;
                        if (n >= length) {
                            break;
                        }
                        jsonArray.put((int)array[n]);
                        ++n;
                    }
                }
                else if (value instanceof short[]) {
                    final String s3 = "short[]";
                    final short[] array2 = (short[])value;
                    final int length2 = array2.length;
                    int n2 = 0;
                    while (true) {
                        o2 = jsonArray;
                        o = s3;
                        if (n2 >= length2) {
                            break;
                        }
                        jsonArray.put((int)array2[n2]);
                        ++n2;
                    }
                }
                else if (value instanceof int[]) {
                    final String s4 = "int[]";
                    final int[] array3 = (int[])value;
                    final int length3 = array3.length;
                    int n3 = 0;
                    while (true) {
                        o2 = jsonArray;
                        o = s4;
                        if (n3 >= length3) {
                            break;
                        }
                        jsonArray.put(array3[n3]);
                        ++n3;
                    }
                }
                else if (value instanceof long[]) {
                    final String s5 = "long[]";
                    final long[] array4 = (long[])value;
                    final int length4 = array4.length;
                    int n4 = 0;
                    while (true) {
                        o2 = jsonArray;
                        o = s5;
                        if (n4 >= length4) {
                            break;
                        }
                        jsonArray.put(array4[n4]);
                        ++n4;
                    }
                }
                else if (value instanceof float[]) {
                    final String s6 = "float[]";
                    final float[] array5 = (float[])value;
                    final int length5 = array5.length;
                    int n5 = 0;
                    while (true) {
                        o2 = jsonArray;
                        o = s6;
                        if (n5 >= length5) {
                            break;
                        }
                        jsonArray.put((double)array5[n5]);
                        ++n5;
                    }
                }
                else if (value instanceof double[]) {
                    final String s7 = "double[]";
                    final double[] array6 = (double[])value;
                    final int length6 = array6.length;
                    int n6 = 0;
                    while (true) {
                        o2 = jsonArray;
                        o = s7;
                        if (n6 >= length6) {
                            break;
                        }
                        jsonArray.put(array6[n6]);
                        ++n6;
                    }
                }
                else if (value instanceof boolean[]) {
                    final String s8 = "bool[]";
                    final boolean[] array7 = (boolean[])value;
                    final int length7 = array7.length;
                    int n7 = 0;
                    while (true) {
                        o2 = jsonArray;
                        o = s8;
                        if (n7 >= length7) {
                            break;
                        }
                        jsonArray.put(array7[n7]);
                        ++n7;
                    }
                }
                else if (value instanceof char[]) {
                    final String s9 = "char[]";
                    final char[] array8 = (char[])value;
                    final int length8 = array8.length;
                    int n8 = 0;
                    while (true) {
                        o2 = jsonArray;
                        o = s9;
                        if (n8 >= length8) {
                            break;
                        }
                        jsonArray.put((Object)String.valueOf(array8[n8]));
                        ++n8;
                    }
                }
                else if (value instanceof List) {
                    final String s10 = "stringList";
                    final Iterator<String> iterator = ((List<String>)value).iterator();
                    while (true) {
                        o2 = jsonArray;
                        o = s10;
                        if (!iterator.hasNext()) {
                            break;
                        }
                        Object null;
                        if ((null = iterator.next()) == null) {
                            null = JSONObject.NULL;
                        }
                        jsonArray.put(null);
                    }
                }
                else {
                    o2 = null;
                }
            }
            if (o != null) {
                jsonObject.put("valueType", o);
                if (o2 != null) {
                    jsonObject.putOpt("value", o2);
                }
                sharedPreferences$Editor.putString(s, jsonObject.toString());
            }
        }
    }
    
    @Override
    public void clear() {
        this.cache.edit().clear().commit();
    }
    
    @Override
    public Bundle load() {
        final Bundle bundle = new Bundle();
        final Iterator<String> iterator = this.cache.getAll().keySet().iterator();
        Bundle bundle2;
        while (true) {
            bundle2 = bundle;
            if (iterator.hasNext()) {
                final String s = iterator.next();
                try {
                    this.deserializeKey(s, bundle);
                    continue;
                }
                catch (JSONException ex) {
                    Logger.log(LoggingBehavior.CACHE, 5, SharedPreferencesTokenCachingStrategy.TAG, "Error reading cached value for key: '" + s + "' -- " + ex);
                    bundle2 = null;
                }
                break;
            }
            break;
        }
        return bundle2;
    }
    
    @Override
    public void save(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        final SharedPreferences$Editor edit = this.cache.edit();
        for (final String s : bundle.keySet()) {
            try {
                this.serializeKey(s, bundle, edit);
                continue;
            }
            catch (JSONException ex) {
                Logger.log(LoggingBehavior.CACHE, 5, SharedPreferencesTokenCachingStrategy.TAG, "Error processing value for key: '" + s + "' -- " + ex);
            }
            return;
        }
        if (!edit.commit()) {
            Logger.log(LoggingBehavior.CACHE, 5, SharedPreferencesTokenCachingStrategy.TAG, "SharedPreferences.Editor.commit() was not successful");
        }
    }
}
