// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.VolleyLog;
import java.io.OutputStream;
import java.util.Iterator;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Collections;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.InputStream;
import com.android.volley.Cache$Entry;
import java.util.Map;

class DiskBasedCache$CacheHeader
{
    public String etag;
    public String key;
    public Map<String, String> responseHeaders;
    public long serverDate;
    public long size;
    public long softTtl;
    public long ttl;
    
    private DiskBasedCache$CacheHeader() {
    }
    
    public DiskBasedCache$CacheHeader(final String key, final Cache$Entry cache$Entry) {
        this.key = key;
        this.size = cache$Entry.data.length;
        this.etag = cache$Entry.etag;
        this.serverDate = cache$Entry.serverDate;
        this.ttl = cache$Entry.ttl;
        this.softTtl = cache$Entry.softTtl;
        this.responseHeaders = cache$Entry.responseHeaders;
    }
    
    public static DiskBasedCache$CacheHeader readHeader(final InputStream inputStream) {
        final DiskBasedCache$CacheHeader diskBasedCache$CacheHeader = new DiskBasedCache$CacheHeader();
        final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        if (objectInputStream.readByte() != 2) {
            throw new IOException();
        }
        diskBasedCache$CacheHeader.key = objectInputStream.readUTF();
        diskBasedCache$CacheHeader.etag = objectInputStream.readUTF();
        if (diskBasedCache$CacheHeader.etag.equals("")) {
            diskBasedCache$CacheHeader.etag = null;
        }
        diskBasedCache$CacheHeader.serverDate = objectInputStream.readLong();
        diskBasedCache$CacheHeader.ttl = objectInputStream.readLong();
        diskBasedCache$CacheHeader.softTtl = objectInputStream.readLong();
        diskBasedCache$CacheHeader.responseHeaders = readStringStringMap(objectInputStream);
        return diskBasedCache$CacheHeader;
    }
    
    private static Map<String, String> readStringStringMap(final ObjectInputStream objectInputStream) {
        final int int1 = objectInputStream.readInt();
        Map<String, String> emptyMap;
        if (int1 == 0) {
            emptyMap = Collections.emptyMap();
        }
        else {
            emptyMap = new HashMap<String, String>(int1);
        }
        for (int i = 0; i < int1; ++i) {
            emptyMap.put(objectInputStream.readUTF().intern(), objectInputStream.readUTF().intern());
        }
        return emptyMap;
    }
    
    private static void writeStringStringMap(final Map<String, String> map, final ObjectOutputStream objectOutputStream) {
        if (map != null) {
            objectOutputStream.writeInt(map.size());
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                objectOutputStream.writeUTF(entry.getKey());
                objectOutputStream.writeUTF(entry.getValue());
            }
        }
        else {
            objectOutputStream.writeInt(0);
        }
    }
    
    public Cache$Entry toCacheEntry(final byte[] data) {
        final Cache$Entry cache$Entry = new Cache$Entry();
        cache$Entry.data = data;
        cache$Entry.etag = this.etag;
        cache$Entry.serverDate = this.serverDate;
        cache$Entry.ttl = this.ttl;
        cache$Entry.softTtl = this.softTtl;
        cache$Entry.responseHeaders = this.responseHeaders;
        return cache$Entry;
    }
    
    public boolean writeHeader(final OutputStream outputStream) {
        try {
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeByte(2);
            objectOutputStream.writeUTF(this.key);
            String etag;
            if (this.etag == null) {
                etag = "";
            }
            else {
                etag = this.etag;
            }
            objectOutputStream.writeUTF(etag);
            objectOutputStream.writeLong(this.serverDate);
            objectOutputStream.writeLong(this.ttl);
            objectOutputStream.writeLong(this.softTtl);
            writeStringStringMap(this.responseHeaders, objectOutputStream);
            objectOutputStream.flush();
            return true;
        }
        catch (IOException ex) {
            VolleyLog.d("%s", ex.toString());
            return false;
        }
    }
}
