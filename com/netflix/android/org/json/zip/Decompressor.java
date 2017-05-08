// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

import java.io.UnsupportedEncodingException;
import com.netflix.android.org.json.Kim;
import com.netflix.android.org.json.JSONObject;
import com.netflix.android.org.json.JSONArray;
import com.netflix.android.org.json.JSONException;

public class Decompressor extends JSONzip
{
    BitReader bitreader;
    
    public Decompressor(final BitReader bitreader) {
        this.bitreader = bitreader;
    }
    
    private boolean bit() {
        try {
            return this.bitreader.bit();
        }
        catch (Throwable t) {
            throw new JSONException(t);
        }
    }
    
    private Object getAndTick(final Keep keep, final BitReader bitReader) {
        int read;
        Object value;
        try {
            read = bitReader.read(keep.bitsize());
            value = keep.value(read);
            if (read >= keep.length) {
                throw new JSONException("Deep error.");
            }
        }
        catch (Throwable t) {
            throw new JSONException(t);
        }
        keep.tick(read);
        return value;
    }
    
    private int read(int read) {
        try {
            read = this.bitreader.read(read);
            return read;
        }
        catch (Throwable t) {
            throw new JSONException(t);
        }
    }
    
    private JSONArray readArray(final boolean b) {
        final JSONArray jsonArray = new JSONArray();
        Object o;
        if (b) {
            o = this.readString();
        }
        else {
            o = this.readValue();
        }
        jsonArray.put(o);
        while (true) {
            if (!this.bit()) {
                if (!this.bit()) {
                    break;
                }
                Object o2;
                if (b) {
                    o2 = this.readValue();
                }
                else {
                    o2 = this.readString();
                }
                jsonArray.put(o2);
            }
            else {
                Object o3;
                if (b) {
                    o3 = this.readString();
                }
                else {
                    o3 = this.readValue();
                }
                jsonArray.put(o3);
            }
        }
        return jsonArray;
    }
    
    private Object readJSON() {
        switch (this.read(3)) {
            default: {
                return JSONObject.NULL;
            }
            case 5: {
                return this.readObject();
            }
            case 6: {
                return this.readArray(true);
            }
            case 7: {
                return this.readArray(false);
            }
            case 0: {
                return new JSONObject();
            }
            case 1: {
                return new JSONArray();
            }
            case 2: {
                return Boolean.TRUE;
            }
            case 3: {
                return Boolean.FALSE;
            }
        }
    }
    
    private String readName() {
        final byte[] array = new byte[65536];
        int n = 0;
        if (this.bit()) {
            return this.getAndTick(this.namekeep, this.bitreader).toString();
        }
        while (true) {
            final int read = this.namehuff.read(this.bitreader);
            if (read == 256) {
                break;
            }
            array[n] = (byte)read;
            ++n;
        }
        if (n == 0) {
            return "";
        }
        final Kim kim = new Kim(array, n);
        this.namekeep.register(kim);
        return kim.toString();
    }
    
    private JSONObject readObject() {
        final JSONObject jsonObject = new JSONObject();
        do {
            final String name = this.readName();
            Object o;
            if (!this.bit()) {
                o = this.readString();
            }
            else {
                o = this.readValue();
            }
            jsonObject.put(name, o);
        } while (this.bit());
        return jsonObject;
    }
    
    private String readString() {
        if (this.bit()) {
            return this.getAndTick(this.stringkeep, this.bitreader).toString();
        }
        final byte[] array = new byte[65536];
        int n = this.bit() ? 1 : 0;
        this.substringkeep.reserve();
        int n2 = 0;
        int n3 = 0;
        int n4 = -1;
        int n6;
        while (true) {
            if (n != 0) {
                final int copy = ((Kim)this.getAndTick(this.substringkeep, this.bitreader)).copy(array, n3);
                if (n4 != -1) {
                    this.substringkeep.registerOne(new Kim(array, n4, n2 + 1));
                }
                n = (this.bit() ? 1 : 0);
                n2 = copy;
                n4 = n3;
                n3 = copy;
            }
            else {
                final int n5 = n4;
                n6 = n3;
                int n7 = n5;
                while (true) {
                    final int read = this.substringhuff.read(this.bitreader);
                    if (read == 256) {
                        break;
                    }
                    array[n6] = (byte)read;
                    final int n8 = ++n6;
                    if (n7 == -1) {
                        continue;
                    }
                    this.substringkeep.registerOne(new Kim(array, n7, n2 + 1));
                    n7 = -1;
                    n6 = n8;
                }
                if (!this.bit()) {
                    break;
                }
                final int n9 = n6;
                n = 1;
                n4 = n7;
                n3 = n9;
            }
        }
        if (n6 == 0) {
            return "";
        }
        final Kim kim = new Kim(array, n6);
        this.stringkeep.register(kim);
        this.substringkeep.registerMany(kim);
        return kim.toString();
    }
    
    private Object readValue() {
        int n = 4;
        final int n2 = 0;
        switch (this.read(2)) {
            default: {
                throw new JSONException("Impossible.");
            }
            case 0: {
                if (this.bit()) {
                    if (!this.bit()) {
                        n = 7;
                    }
                    else {
                        n = 14;
                    }
                }
                return new Integer(this.read(n));
            }
            case 1: {
                final byte[] array = new byte[256];
                int n3 = n2;
            Label_0136_Outer:
                while (true) {
                    final int read = this.read(4);
                    while (true) {
                        if (read == Decompressor.endOfNumber) {
                            try {
                                final Object stringToValue = JSONObject.stringToValue(new String(array, 0, n3, "US-ASCII"));
                                this.values.register(stringToValue);
                                return stringToValue;
                                array[n3] = Decompressor.bcd[read];
                                ++n3;
                                continue Label_0136_Outer;
                            }
                            catch (UnsupportedEncodingException ex) {
                                throw new JSONException(ex);
                            }
                            return this.getAndTick(this.values, this.bitreader);
                        }
                        continue;
                    }
                }
                break;
            }
            case 2: {
                return this.getAndTick(this.values, this.bitreader);
            }
            case 3: {
                return this.readJSON();
            }
        }
    }
    
    public boolean pad(final int n) {
        try {
            return this.bitreader.pad(n);
        }
        catch (Throwable t) {
            throw new JSONException(t);
        }
    }
    
    public Object unzip() {
        this.begin();
        return this.readJSON();
    }
}
