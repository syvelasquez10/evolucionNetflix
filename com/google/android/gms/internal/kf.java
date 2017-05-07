// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.Serializable;
import android.util.Log;
import java.util.Iterator;
import java.util.Collections;
import java.util.HashMap;
import android.net.Uri;
import java.util.Set;
import com.google.android.gms.wearable.d;
import java.util.Map;
import com.google.android.gms.wearable.c;

public class kf implements c
{
    private byte[] Nf;
    private Map<String, d> adD;
    private Set<String> adE;
    private Uri mUri;
    
    public kf(final c c) {
        this.mUri = c.getUri();
        this.Nf = c.getData();
        final HashMap<String, d> hashMap = new HashMap<String, d>();
        for (final Map.Entry<String, d> entry : c.ma().entrySet()) {
            if (entry.getKey() != null) {
                hashMap.put(entry.getKey(), entry.getValue().freeze());
            }
        }
        this.adD = (Map<String, d>)Collections.unmodifiableMap((Map<?, ?>)hashMap);
        this.adE = Collections.unmodifiableSet((Set<? extends String>)c.mb());
    }
    
    @Override
    public byte[] getData() {
        return this.Nf;
    }
    
    @Override
    public Uri getUri() {
        return this.mUri;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public Map<String, d> ma() {
        return this.adD;
    }
    
    @Deprecated
    @Override
    public Set<String> mb() {
        return this.adE;
    }
    
    public c mg() {
        return this;
    }
    
    @Override
    public String toString() {
        return this.toString(Log.isLoggable("DataItem", 3));
    }
    
    public String toString(final boolean b) {
        final StringBuilder sb = new StringBuilder("DataItemEntity[");
        sb.append("@");
        sb.append(Integer.toHexString(this.hashCode()));
        final StringBuilder append = new StringBuilder().append(",dataSz=");
        Serializable value;
        if (this.Nf == null) {
            value = "null";
        }
        else {
            value = this.Nf.length;
        }
        sb.append(append.append(value).toString());
        sb.append(", numAssets=" + this.adD.size());
        sb.append(", uri=" + this.mUri);
        if (!b) {
            sb.append("]");
            return sb.toString();
        }
        sb.append("\n  tags=[");
        final Iterator<String> iterator = this.adE.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (n != 0) {
                sb.append(", ");
            }
            else {
                n = 1;
            }
            sb.append(s);
        }
        sb.append("]\n  assets: ");
        for (final String s2 : this.adD.keySet()) {
            sb.append("\n    " + s2 + ": " + this.adD.get(s2));
        }
        sb.append("\n  ]");
        return sb.toString();
    }
}
