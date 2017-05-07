// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.Collections;
import java.util.Collection;
import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.fh;
import java.util.HashMap;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Map;

public final class c
{
    private static Map<String, MetadataField<?>> rE;
    
    static {
        c.rE = new HashMap<String, MetadataField<?>>();
        b(fh.rG);
        b(fh.TITLE);
        b(fh.MIME_TYPE);
        b(fh.STARRED);
        b(fh.TRASHED);
        b(fh.rH);
        b(fh.rI);
        b(fh.PARENTS);
        b(fi.rL);
        b(fi.rJ);
        b(fi.rK);
        b(fi.rM);
    }
    
    public static MetadataField<?> ac(final String s) {
        return c.rE.get(s);
    }
    
    private static void b(final MetadataField<?> metadataField) {
        if (c.rE.containsKey(metadataField.getName())) {
            throw new IllegalArgumentException("Duplicate field name registered: " + metadataField.getName());
        }
        c.rE.put(metadataField.getName(), metadataField);
    }
    
    public static Collection<MetadataField<?>> cW() {
        return Collections.unmodifiableCollection((Collection<? extends MetadataField<?>>)c.rE.values());
    }
}
