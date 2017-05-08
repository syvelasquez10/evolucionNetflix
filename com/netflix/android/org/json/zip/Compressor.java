// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import com.netflix.android.org.json.JSONObject;
import com.netflix.android.org.json.JSONArray;
import com.netflix.android.org.json.Kim;
import com.netflix.android.org.json.JSONException;

public class Compressor extends JSONzip
{
    final BitWriter bitwriter;
    
    public Compressor(final BitWriter bitwriter) {
        this.bitwriter = bitwriter;
    }
    
    private static int bcd(final char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        switch (c) {
            default: {
                return 13;
            }
            case '.': {
                return 10;
            }
            case '-': {
                return 11;
            }
            case '+': {
                return 12;
            }
        }
    }
    
    private void one() {
        this.write(1, 1);
    }
    
    private void write(final int n, final int n2) {
        try {
            this.bitwriter.write(n, n2);
        }
        catch (Throwable t) {
            throw new JSONException(t);
        }
    }
    
    private void write(final int n, final Huff huff) {
        huff.write(n, this.bitwriter);
    }
    
    private void write(final Kim kim, int i, final int n, final Huff huff) {
        while (i < n) {
            this.write(kim.get(i), huff);
            ++i;
        }
    }
    
    private void write(final Kim kim, final Huff huff) {
        this.write(kim, 0, kim.length, huff);
    }
    
    private void writeAndTick(final int n, final Keep keep) {
        final int bitsize = keep.bitsize();
        keep.tick(n);
        this.write(n, bitsize);
    }
    
    private void writeArray(final JSONArray jsonArray) {
        boolean b = false;
        int i = 1;
        final int length = jsonArray.length();
        if (length == 0) {
            this.write(1, 3);
            return;
        }
        Object o;
        if ((o = jsonArray.get(0)) == null) {
            o = JSONObject.NULL;
        }
        if (o instanceof String) {
            this.write(6, 3);
            this.writeString((String)o);
            b = true;
        }
        else {
            this.write(7, 3);
            this.writeValue(o);
        }
        while (i < length) {
            Object o2;
            if ((o2 = jsonArray.get(i)) == null) {
                o2 = JSONObject.NULL;
            }
            if (o2 instanceof String != b) {
                this.zero();
            }
            this.one();
            if (o2 instanceof String) {
                this.writeString((String)o2);
            }
            else {
                this.writeValue(o2);
            }
            ++i;
        }
        this.zero();
        this.zero();
    }
    
    private void writeJSON(Object o) {
        if (JSONObject.NULL.equals(o)) {
            this.write(4, 3);
            return;
        }
        if (Boolean.FALSE.equals(o)) {
            this.write(3, 3);
            return;
        }
        if (Boolean.TRUE.equals(o)) {
            this.write(2, 3);
            return;
        }
        if (o instanceof Map) {
            o = new JSONObject((Map)o);
        }
        else if (o instanceof Collection) {
            o = new JSONArray((Collection)o);
        }
        else if (o.getClass().isArray()) {
            o = new JSONArray(o);
        }
        if (o instanceof JSONObject) {
            this.writeObject((JSONObject)o);
            return;
        }
        if (o instanceof JSONArray) {
            this.writeArray((JSONArray)o);
            return;
        }
        throw new JSONException("Unrecognized object");
    }
    
    private void writeName(final String s) {
        final Kim kim = new Kim(s);
        final int find = this.namekeep.find(kim);
        if (find != -1) {
            this.one();
            this.writeAndTick(find, this.namekeep);
            return;
        }
        this.zero();
        this.write(kim, this.namehuff);
        this.write(256, this.namehuff);
        this.namekeep.register(kim);
    }
    
    private void writeObject(final JSONObject jsonObject) {
        int n = 1;
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String next = keys.next();
            if (next instanceof String) {
                if (n != 0) {
                    this.write(5, 3);
                    n = 0;
                }
                else {
                    this.one();
                }
                this.writeName(next);
                final Object value = jsonObject.get(next);
                if (value instanceof String) {
                    this.zero();
                    this.writeString((String)value);
                }
                else {
                    this.one();
                    this.writeValue(value);
                }
            }
        }
        if (n != 0) {
            this.write(0, 3);
            return;
        }
        this.zero();
    }
    
    private void writeString(final String s) {
        if (s.length() == 0) {
            this.zero();
            this.zero();
            this.write(256, this.substringhuff);
            this.zero();
            return;
        }
        final Kim kim = new Kim(s);
        final int find = this.stringkeep.find(kim);
        if (find != -1) {
            this.one();
            this.writeAndTick(find, this.stringkeep);
            return;
        }
        this.writeSubstring(kim);
        this.stringkeep.register(kim);
    }
    
    private void writeSubstring(final Kim kim) {
        int n = 0;
        this.substringkeep.reserve();
        this.zero();
        final int length = kim.length;
        int n2 = -1;
        int n3 = 0;
    Label_0188_Outer:
        while (true) {
            int match = -1;
            int i;
            for (i = n3; i <= length - 3; ++i) {
                match = this.substringkeep.match(kim, i, length);
                if (match != -1) {
                    break;
                }
            }
            if (match == -1) {
                break;
            }
            if (n3 != i) {
                this.zero();
                this.write(kim, n3, i, this.substringhuff);
                this.write(256, this.substringhuff);
                if (n2 != -1) {
                    this.substringkeep.registerOne(kim, n2, n);
                    n2 = -1;
                }
            }
            while (true) {
                this.one();
                this.writeAndTick(match, this.substringkeep);
                n3 = this.substringkeep.length(match) + i;
                if (n2 != -1) {
                    this.substringkeep.registerOne(kim, n2, n);
                }
                n = n3 + 1;
                n2 = i;
                continue Label_0188_Outer;
                continue;
            }
        }
        this.zero();
        if (n3 < length) {
            this.write(kim, n3, length, this.substringhuff);
            if (n2 != -1) {
                this.substringkeep.registerOne(kim, n2, n);
            }
        }
        this.write(256, this.substringhuff);
        this.zero();
        this.substringkeep.registerMany(kim);
    }
    
    private void writeValue(final Object o) {
        if (!(o instanceof Number)) {
            this.write(3, 2);
            this.writeJSON(o);
            return;
        }
        final String numberToString = JSONObject.numberToString((Number)o);
        final int find = this.values.find(numberToString);
        if (find != -1) {
            this.write(2, 2);
            this.writeAndTick(find, this.values);
            return;
        }
        if (o instanceof Integer || o instanceof Long) {
            final long longValue = ((Number)o).longValue();
            if (longValue >= 0L && longValue < 16384L) {
                this.write(0, 2);
                if (longValue < 16L) {
                    this.zero();
                    this.write((int)longValue, 4);
                    return;
                }
                this.one();
                if (longValue < 128L) {
                    this.zero();
                    this.write((int)longValue, 7);
                    return;
                }
                this.one();
                this.write((int)longValue, 14);
                return;
            }
        }
        this.write(1, 2);
        for (int i = 0; i < numberToString.length(); ++i) {
            this.write(bcd(numberToString.charAt(i)), 4);
        }
        this.write(Compressor.endOfNumber, 4);
        this.values.register(numberToString);
    }
    
    private void zero() {
        this.write(0, 1);
    }
    
    public void flush() {
        this.pad(8);
    }
    
    public void pad(final int n) {
        try {
            this.bitwriter.pad(n);
        }
        catch (Throwable t) {
            throw new JSONException(t);
        }
    }
    
    public void zip(final JSONArray jsonArray) {
        this.begin();
        this.writeJSON(jsonArray);
    }
    
    public void zip(final JSONObject jsonObject) {
        this.begin();
        this.writeJSON(jsonObject);
    }
}
