// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import org.json.JSONException;
import com.facebook.internal.Logger;
import java.util.Iterator;
import java.util.List;
import android.content.SharedPreferences$Editor;
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
    
    private void deserializeKey(final String s, final Bundle bundle) {
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        final int n4 = 0;
        final int n5 = 0;
        final int n6 = 0;
        int i = 0;
        final JSONObject jsonObject = new JSONObject(this.cache.getString(s, "{}"));
        final String string = jsonObject.getString("valueType");
        if (string.equals("bool")) {
            bundle.putBoolean(s, jsonObject.getBoolean("value"));
        }
        else {
            if (string.equals("bool[]")) {
                final JSONArray jsonArray = jsonObject.getJSONArray("value");
                boolean[] array;
                for (array = new boolean[jsonArray.length()]; i < array.length; ++i) {
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
                for (int j = n; j < array2.length; ++j) {
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
                for (int k = n2; k < array3.length; ++k) {
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
                for (int l = n3; l < array4.length; ++l) {
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
                for (int n7 = n4; n7 < array5.length; ++n7) {
                    array5[n7] = jsonArray5.getLong(n7);
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
                for (int n8 = n5; n8 < array6.length; ++n8) {
                    array6[n8] = (float)jsonArray6.getDouble(n8);
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
                for (int n9 = n6; n9 < array7.length; ++n9) {
                    array7[n9] = jsonArray7.getDouble(n9);
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
                    for (int n10 = 0; n10 < array8.length; ++n10) {
                        final String string3 = jsonArray8.getString(n10);
                        if (string3 != null && string3.length() == 1) {
                            array8[n10] = string3.charAt(0);
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
                    for (int n11 = 0; n11 < length; ++n11) {
                        final Object value = jsonArray9.get(n11);
                        String s2;
                        if (value == JSONObject.NULL) {
                            s2 = null;
                        }
                        else {
                            s2 = (String)value;
                        }
                        list.add(n11, s2);
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
    
    private void serializeKey(final String s, final Bundle bundle, final SharedPreferences$Editor sharedPreferences$Editor) {
        Object o = null;
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        final int n4 = 0;
        final int n5 = 0;
        final int n6 = 0;
        final int n7 = 0;
        int i = 0;
        final Object value = bundle.get(s);
        if (value != null) {
            final JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray;
            if (value instanceof Byte) {
                jsonObject.put("value", (int)value);
                jsonArray = null;
                o = "byte";
            }
            else if (value instanceof Short) {
                jsonObject.put("value", (int)value);
                jsonArray = null;
                o = "short";
            }
            else if (value instanceof Integer) {
                jsonObject.put("value", (int)value);
                jsonArray = null;
                o = "int";
            }
            else if (value instanceof Long) {
                jsonObject.put("value", (long)value);
                jsonArray = null;
                o = "long";
            }
            else if (value instanceof Float) {
                jsonObject.put("value", (double)value);
                jsonArray = null;
                o = "float";
            }
            else if (value instanceof Double) {
                jsonObject.put("value", (double)value);
                jsonArray = null;
                o = "double";
            }
            else if (value instanceof Boolean) {
                jsonObject.put("value", (boolean)value);
                jsonArray = null;
                o = "bool";
            }
            else if (value instanceof Character) {
                jsonObject.put("value", (Object)value.toString());
                jsonArray = null;
                o = "char";
            }
            else if (value instanceof String) {
                jsonObject.put("value", (Object)value);
                jsonArray = null;
                o = "string";
            }
            else if (value instanceof Enum) {
                jsonObject.put("value", (Object)value.toString());
                jsonObject.put("enumType", (Object)((List<String>)value).getClass().getName());
                jsonArray = null;
                o = "enum";
            }
            else {
                jsonArray = new JSONArray();
                if (value instanceof byte[]) {
                    o = "byte[]";
                    for (byte[] array = (byte[])value; i < array.length; ++i) {
                        jsonArray.put((int)array[i]);
                    }
                }
                else if (value instanceof short[]) {
                    o = "short[]";
                    final short[] array2 = (short[])value;
                    for (int length = array2.length, j = n; j < length; ++j) {
                        jsonArray.put((int)array2[j]);
                    }
                }
                else if (value instanceof int[]) {
                    o = "int[]";
                    final int[] array3 = (int[])value;
                    for (int length2 = array3.length, k = n2; k < length2; ++k) {
                        jsonArray.put(array3[k]);
                    }
                }
                else if (value instanceof long[]) {
                    o = "long[]";
                    final long[] array4 = (long[])value;
                    for (int length3 = array4.length, l = n3; l < length3; ++l) {
                        jsonArray.put(array4[l]);
                    }
                }
                else if (value instanceof float[]) {
                    o = "float[]";
                    final float[] array5 = (float[])value;
                    for (int length4 = array5.length, n8 = n4; n8 < length4; ++n8) {
                        jsonArray.put((double)array5[n8]);
                    }
                }
                else if (value instanceof double[]) {
                    o = "double[]";
                    final double[] array6 = (double[])value;
                    for (int length5 = array6.length, n9 = n5; n9 < length5; ++n9) {
                        jsonArray.put(array6[n9]);
                    }
                }
                else if (value instanceof boolean[]) {
                    o = "bool[]";
                    final boolean[] array7 = (boolean[])value;
                    for (int length6 = array7.length, n10 = n6; n10 < length6; ++n10) {
                        jsonArray.put(array7[n10]);
                    }
                }
                else if (value instanceof char[]) {
                    o = "char[]";
                    final char[] array8 = (char[])value;
                    for (int length7 = array8.length, n11 = n7; n11 < length7; ++n11) {
                        jsonArray.put((Object)String.valueOf(array8[n11]));
                    }
                }
                else if (value instanceof List) {
                    final String s2 = "stringList";
                    final Iterator<String> iterator = ((List<String>)value).iterator();
                    while (iterator.hasNext()) {
                        Object null;
                        if ((null = iterator.next()) == null) {
                            null = JSONObject.NULL;
                        }
                        jsonArray.put(null);
                    }
                    o = s2;
                }
                else {
                    jsonArray = null;
                }
            }
            if (o != null) {
                jsonObject.put("valueType", o);
                if (jsonArray != null) {
                    jsonObject.putOpt("value", (Object)jsonArray);
                }
                sharedPreferences$Editor.putString(s, jsonObject.toString());
            }
        }
    }
    
    @Override
    public void clear() {
        this.cache.edit().clear().apply();
    }
    
    @Override
    public Bundle load() {
        final Bundle bundle = new Bundle();
        for (final String s : this.cache.getAll().keySet()) {
            try {
                this.deserializeKey(s, bundle);
                continue;
            }
            catch (JSONException ex) {
                Logger.log(LoggingBehavior.CACHE, 5, SharedPreferencesTokenCachingStrategy.TAG, "Error reading cached value for key: '" + s + "' -- " + ex);
                return null;
            }
            break;
        }
        return bundle;
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
                return;
            }
            break;
        }
        edit.apply();
    }
}
