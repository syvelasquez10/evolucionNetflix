// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.wearable.d;
import java.util.Map;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.c;
import com.google.android.gms.common.data.b;

public final class kg extends b implements c
{
    private final int LE;
    
    public kg(final DataHolder dataHolder, final int n, final int le) {
        super(dataHolder, n);
        this.LE = le;
    }
    
    @Override
    public byte[] getData() {
        return this.getByteArray("data");
    }
    
    @Override
    public Uri getUri() {
        return Uri.parse(this.getString("path"));
    }
    
    @Override
    public Map<String, d> ma() {
        final HashMap<String, ke> hashMap = (HashMap<String, ke>)new HashMap<String, d>(this.LE);
        for (int i = 0; i < this.LE; ++i) {
            final ke ke = new ke(this.BB, this.BD + i);
            if (ke.mc() != null) {
                hashMap.put(ke.mc(), ke);
            }
        }
        return (Map<String, d>)hashMap;
    }
    
    @Deprecated
    @Override
    public Set<String> mb() {
        final HashSet<String> set = new HashSet<String>();
        final String string = this.getString("tags");
        if (string != null) {
            final String[] split = string.split("\\|");
            for (int length = split.length, i = 0; i < length; ++i) {
                set.add(split[i]);
            }
        }
        return set;
    }
    
    public c mg() {
        return new kf(this);
    }
}
