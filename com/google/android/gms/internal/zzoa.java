// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import android.text.TextUtils;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import android.net.Uri$Builder;
import android.util.LogPrinter;
import android.net.Uri;

public final class zzoa implements zzoh
{
    private static final Uri zzaHN;
    private final LogPrinter zzaHO;
    
    static {
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("uri");
        uri$Builder.authority("local");
        zzaHN = uri$Builder.build();
    }
    
    public zzoa() {
        this.zzaHO = new LogPrinter(4, "GA/LogCatTransport");
    }
    
    @Override
    public void zzb(final zzob zzob) {
        final ArrayList<Object> list = new ArrayList<Object>(zzob.zzxi());
        Collections.sort(list, (Comparator<? super Object>)new zzoa$1(this));
        final StringBuilder sb = new StringBuilder();
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String string = ((zzod<?>)iterator.next()).toString();
            if (!TextUtils.isEmpty((CharSequence)string)) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(string);
            }
        }
        this.zzaHO.println(sb.toString());
    }
    
    @Override
    public Uri zzhs() {
        return zzoa.zzaHN;
    }
}
