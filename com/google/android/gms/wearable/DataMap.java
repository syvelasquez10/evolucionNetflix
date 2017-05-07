// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.internal.pm;
import java.util.Set;
import com.google.android.gms.internal.pl;
import com.google.android.gms.internal.pb;
import java.util.List;
import com.google.android.gms.internal.pb$a;
import com.google.android.gms.internal.pc;
import java.util.Arrays;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.Parcelable;
import android.os.Bundle;
import java.util.HashMap;

public class DataMap
{
    public static final String TAG = "DataMap";
    private final HashMap<String, Object> auL;
    
    public DataMap() {
        this.auL = new HashMap<String, Object>();
    }
    
    private static void a(final Bundle bundle, final String s, final Object o) {
        if (o instanceof String) {
            bundle.putString(s, (String)o);
        }
        else {
            if (o instanceof Integer) {
                bundle.putInt(s, (int)o);
                return;
            }
            if (o instanceof Long) {
                bundle.putLong(s, (long)o);
                return;
            }
            if (o instanceof Double) {
                bundle.putDouble(s, (double)o);
                return;
            }
            if (o instanceof Float) {
                bundle.putFloat(s, (float)o);
                return;
            }
            if (o instanceof Boolean) {
                bundle.putBoolean(s, (boolean)o);
                return;
            }
            if (o instanceof Byte) {
                bundle.putByte(s, (byte)o);
                return;
            }
            if (o instanceof byte[]) {
                bundle.putByteArray(s, (byte[])o);
                return;
            }
            if (o instanceof String[]) {
                bundle.putStringArray(s, (String[])o);
                return;
            }
            if (o instanceof long[]) {
                bundle.putLongArray(s, (long[])o);
                return;
            }
            if (o instanceof float[]) {
                bundle.putFloatArray(s, (float[])o);
                return;
            }
            if (o instanceof Asset) {
                bundle.putParcelable(s, (Parcelable)o);
                return;
            }
            if (o instanceof DataMap) {
                bundle.putParcelable(s, (Parcelable)((DataMap)o).toBundle());
                return;
            }
            if (o instanceof ArrayList) {
                switch (d((ArrayList<?>)o)) {
                    default: {}
                    case 0: {
                        bundle.putStringArrayList(s, (ArrayList)o);
                    }
                    case 1: {
                        bundle.putStringArrayList(s, (ArrayList)o);
                    }
                    case 3: {
                        bundle.putStringArrayList(s, (ArrayList)o);
                    }
                    case 2: {
                        bundle.putIntegerArrayList(s, (ArrayList)o);
                    }
                    case 4: {
                        final ArrayList<Bundle> list = new ArrayList<Bundle>();
                        final Iterator<DataMap> iterator = ((ArrayList)o).iterator();
                        while (iterator.hasNext()) {
                            list.add(iterator.next().toBundle());
                        }
                        bundle.putParcelableArrayList(s, (ArrayList)list);
                    }
                }
            }
        }
    }
    
    private static void a(final DataMap dataMap, final String s, final Object o) {
        if (o instanceof String) {
            dataMap.putString(s, (String)o);
        }
        else {
            if (o instanceof Integer) {
                dataMap.putInt(s, (int)o);
                return;
            }
            if (o instanceof Long) {
                dataMap.putLong(s, (long)o);
                return;
            }
            if (o instanceof Double) {
                dataMap.putDouble(s, (double)o);
                return;
            }
            if (o instanceof Float) {
                dataMap.putFloat(s, (float)o);
                return;
            }
            if (o instanceof Boolean) {
                dataMap.putBoolean(s, (boolean)o);
                return;
            }
            if (o instanceof Byte) {
                dataMap.putByte(s, (byte)o);
                return;
            }
            if (o instanceof byte[]) {
                dataMap.putByteArray(s, (byte[])o);
                return;
            }
            if (o instanceof String[]) {
                dataMap.putStringArray(s, (String[])o);
                return;
            }
            if (o instanceof long[]) {
                dataMap.putLongArray(s, (long[])o);
                return;
            }
            if (o instanceof float[]) {
                dataMap.putFloatArray(s, (float[])o);
                return;
            }
            if (o instanceof Asset) {
                dataMap.putAsset(s, (Asset)o);
                return;
            }
            if (o instanceof Bundle) {
                dataMap.putDataMap(s, fromBundle((Bundle)o));
                return;
            }
            if (o instanceof ArrayList) {
                switch (d((ArrayList<?>)o)) {
                    default: {}
                    case 0: {
                        dataMap.putStringArrayList(s, (ArrayList<String>)o);
                    }
                    case 1: {
                        dataMap.putStringArrayList(s, (ArrayList<String>)o);
                    }
                    case 3: {
                        dataMap.putStringArrayList(s, (ArrayList<String>)o);
                    }
                    case 2: {
                        dataMap.putIntegerArrayList(s, (ArrayList<Integer>)o);
                    }
                    case 5: {
                        dataMap.putDataMapArrayList(s, arrayListFromBundleArrayList((ArrayList<Bundle>)o));
                    }
                }
            }
        }
    }
    
    private void a(final String s, final Object o, final String s2, final ClassCastException ex) {
        this.a(s, o, s2, "<null>", ex);
    }
    
    private void a(final String s, final Object o, final String s2, final Object o2, final ClassCastException ex) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Key ");
        sb.append(s);
        sb.append(" expected ");
        sb.append(s2);
        sb.append(" but value was a ");
        sb.append(o.getClass().getName());
        sb.append(".  The default value ");
        sb.append(o2);
        sb.append(" was returned.");
        Log.w("DataMap", sb.toString());
        Log.w("DataMap", "Attempt to cast generated internal exception:", (Throwable)ex);
    }
    
    private static boolean a(final Asset asset, final Asset asset2) {
        if (asset == null || asset2 == null) {
            return asset == asset2;
        }
        if (!TextUtils.isEmpty((CharSequence)asset.getDigest())) {
            return asset.getDigest().equals(asset2.getDigest());
        }
        return Arrays.equals(asset.getData(), asset2.getData());
    }
    
    private static boolean a(final DataMap dataMap, final DataMap dataMap2) {
        if (dataMap.size() == dataMap2.size()) {
            for (final String s : dataMap.keySet()) {
                final Object value = dataMap.get(s);
                final Object value2 = dataMap2.get(s);
                if (value instanceof Asset) {
                    if (!(value2 instanceof Asset)) {
                        return false;
                    }
                    if (!a((Asset)value, (Asset)value2)) {
                        return false;
                    }
                    continue;
                }
                else if (value instanceof String[]) {
                    if (!(value2 instanceof String[])) {
                        return false;
                    }
                    if (!Arrays.equals((Object[])value, (Object[])value2)) {
                        return false;
                    }
                    continue;
                }
                else if (value instanceof long[]) {
                    if (!(value2 instanceof long[])) {
                        return false;
                    }
                    if (!Arrays.equals((long[])value, (long[])value2)) {
                        return false;
                    }
                    continue;
                }
                else if (value instanceof float[]) {
                    if (!(value2 instanceof float[])) {
                        return false;
                    }
                    if (!Arrays.equals((float[])value, (float[])value2)) {
                        return false;
                    }
                    continue;
                }
                else if (value instanceof byte[]) {
                    if (!(value2 instanceof byte[])) {
                        return false;
                    }
                    if (!Arrays.equals((byte[])value, (byte[])value2)) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (value == null || value2 == null) {
                        return value == value2;
                    }
                    if (!value.equals(value2)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        return false;
    }
    
    public static ArrayList<DataMap> arrayListFromBundleArrayList(final ArrayList<Bundle> list) {
        final ArrayList<DataMap> list2 = new ArrayList<DataMap>();
        final Iterator<Bundle> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(fromBundle(iterator.next()));
        }
        return list2;
    }
    
    private static int d(final ArrayList<?> list) {
        if (list.isEmpty()) {
            return 0;
        }
        for (final Object next : list) {
            if (next != null) {
                if (next instanceof Integer) {
                    return 2;
                }
                if (next instanceof String) {
                    return 3;
                }
                if (next instanceof DataMap) {
                    return 4;
                }
                if (next instanceof Bundle) {
                    return 5;
                }
                continue;
            }
        }
        return 1;
    }
    
    public static DataMap fromBundle(final Bundle bundle) {
        bundle.setClassLoader(Asset.class.getClassLoader());
        final DataMap dataMap = new DataMap();
        for (final String s : bundle.keySet()) {
            a(dataMap, s, bundle.get(s));
        }
        return dataMap;
    }
    
    public static DataMap fromByteArray(final byte[] array) {
        try {
            return pb.a(new pb$a(pc.n(array), new ArrayList<Asset>()));
        }
        catch (pl pl) {
            throw new IllegalArgumentException("Unable to convert data", pl);
        }
    }
    
    public void clear() {
        this.auL.clear();
    }
    
    public boolean containsKey(final String s) {
        return this.auL.containsKey(s);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof DataMap && a(this, (DataMap)o);
    }
    
    public <T> T get(final String s) {
        return (T)this.auL.get(s);
    }
    
    public Asset getAsset(final String s) {
        final Asset value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "Asset", ex);
            return null;
        }
    }
    
    public boolean getBoolean(final String s) {
        return this.getBoolean(s, false);
    }
    
    public boolean getBoolean(final String s, final boolean b) {
        final Boolean value = this.auL.get(s);
        if (value == null) {
            return b;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "Boolean", b, ex);
            return b;
        }
    }
    
    public byte getByte(final String s) {
        return this.getByte(s, (byte)0);
    }
    
    public byte getByte(final String s, final byte b) {
        final Byte value = this.auL.get(s);
        if (value == null) {
            return b;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "Byte", b, ex);
            return b;
        }
    }
    
    public byte[] getByteArray(final String s) {
        final byte[] value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "byte[]", ex);
            return null;
        }
    }
    
    public DataMap getDataMap(final String s) {
        final DataMap value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "DataMap", ex);
            return null;
        }
    }
    
    public ArrayList<DataMap> getDataMapArrayList(final String s) {
        final ArrayList<DataMap> value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "ArrayList<DataMap>", ex);
            return null;
        }
    }
    
    public double getDouble(final String s) {
        return this.getDouble(s, 0.0);
    }
    
    public double getDouble(final String s, final double n) {
        final Double value = this.auL.get(s);
        if (value == null) {
            return n;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "Double", n, ex);
            return n;
        }
    }
    
    public float getFloat(final String s) {
        return this.getFloat(s, 0.0f);
    }
    
    public float getFloat(final String s, final float n) {
        final Float value = this.auL.get(s);
        if (value == null) {
            return n;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "Float", n, ex);
            return n;
        }
    }
    
    public float[] getFloatArray(final String s) {
        final float[] value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "float[]", ex);
            return null;
        }
    }
    
    public int getInt(final String s) {
        return this.getInt(s, 0);
    }
    
    public int getInt(final String s, final int n) {
        final Integer value = this.auL.get(s);
        if (value == null) {
            return n;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "Integer", ex);
            return n;
        }
    }
    
    public ArrayList<Integer> getIntegerArrayList(final String s) {
        final ArrayList<Integer> value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "ArrayList<Integer>", ex);
            return null;
        }
    }
    
    public long getLong(final String s) {
        return this.getLong(s, 0L);
    }
    
    public long getLong(final String s, final long n) {
        final Long value = this.auL.get(s);
        if (value == null) {
            return n;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "long", ex);
            return n;
        }
    }
    
    public long[] getLongArray(final String s) {
        final long[] value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "long[]", ex);
            return null;
        }
    }
    
    public String getString(final String s) {
        final String value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "String", ex);
            return null;
        }
    }
    
    public String getString(String string, final String s) {
        string = this.getString(string);
        if (string == null) {
            return s;
        }
        return string;
    }
    
    public String[] getStringArray(final String s) {
        final String[] value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "String[]", ex);
            return null;
        }
    }
    
    public ArrayList<String> getStringArrayList(final String s) {
        final ArrayList<String> value = this.auL.get(s);
        if (value == null) {
            return null;
        }
        try {
            return value;
        }
        catch (ClassCastException ex) {
            this.a(s, value, "ArrayList<String>", ex);
            return null;
        }
    }
    
    @Override
    public int hashCode() {
        return this.auL.hashCode() * 29;
    }
    
    public boolean isEmpty() {
        return this.auL.isEmpty();
    }
    
    public Set<String> keySet() {
        return this.auL.keySet();
    }
    
    public void putAll(final DataMap dataMap) {
        for (final String s : dataMap.keySet()) {
            this.auL.put(s, dataMap.get(s));
        }
    }
    
    public void putAsset(final String s, final Asset asset) {
        this.auL.put(s, asset);
    }
    
    public void putBoolean(final String s, final boolean b) {
        this.auL.put(s, b);
    }
    
    public void putByte(final String s, final byte b) {
        this.auL.put(s, b);
    }
    
    public void putByteArray(final String s, final byte[] array) {
        this.auL.put(s, array);
    }
    
    public void putDataMap(final String s, final DataMap dataMap) {
        this.auL.put(s, dataMap);
    }
    
    public void putDataMapArrayList(final String s, final ArrayList<DataMap> list) {
        this.auL.put(s, list);
    }
    
    public void putDouble(final String s, final double n) {
        this.auL.put(s, n);
    }
    
    public void putFloat(final String s, final float n) {
        this.auL.put(s, n);
    }
    
    public void putFloatArray(final String s, final float[] array) {
        this.auL.put(s, array);
    }
    
    public void putInt(final String s, final int n) {
        this.auL.put(s, n);
    }
    
    public void putIntegerArrayList(final String s, final ArrayList<Integer> list) {
        this.auL.put(s, list);
    }
    
    public void putLong(final String s, final long n) {
        this.auL.put(s, n);
    }
    
    public void putLongArray(final String s, final long[] array) {
        this.auL.put(s, array);
    }
    
    public void putString(final String s, final String s2) {
        this.auL.put(s, s2);
    }
    
    public void putStringArray(final String s, final String[] array) {
        this.auL.put(s, array);
    }
    
    public void putStringArrayList(final String s, final ArrayList<String> list) {
        this.auL.put(s, list);
    }
    
    public Object remove(final String s) {
        return this.auL.remove(s);
    }
    
    public int size() {
        return this.auL.size();
    }
    
    public Bundle toBundle() {
        final Bundle bundle = new Bundle();
        for (final String s : this.auL.keySet()) {
            a(bundle, s, this.auL.get(s));
        }
        return bundle;
    }
    
    public byte[] toByteArray() {
        return pm.f(pb.a(this).avQ);
    }
    
    @Override
    public String toString() {
        return this.auL.toString();
    }
}
