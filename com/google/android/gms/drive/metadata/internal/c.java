// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.Collections;
import java.util.Collection;
import com.google.android.gms.internal.gv;
import com.google.android.gms.internal.gt;
import com.google.android.gms.internal.gs;
import java.util.HashMap;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Map;

public final class c
{
    private static Map<String, MetadataField<?>> FP;
    
    static {
        c.FP = new HashMap<String, MetadataField<?>>();
        b(gs.FR);
        b(gs.Go);
        b(gs.Gh);
        b(gs.Gm);
        b(gs.Gp);
        b(gs.Gb);
        b(gs.Gc);
        b(gs.FZ);
        b(gs.Ge);
        b(gs.Gk);
        b(gs.FS);
        b(gs.Gj);
        b(gs.FT);
        b(gs.Ga);
        b(gs.FU);
        b(gs.FV);
        b(gs.FW);
        b(gs.Gg);
        b(gs.Gd);
        b(gs.Gi);
        b(gs.Gl);
        b(gs.Gq);
        b(gs.Gr);
        b(gs.FY);
        b(gs.FX);
        b(gs.Gn);
        b(gs.Gf);
        b(gt.Gs);
        b(gt.Gu);
        b(gt.Gv);
        b(gt.Gw);
        b(gt.Gt);
        b(gv.Gy);
        b(gv.Gz);
    }
    
    public static MetadataField<?> ax(final String s) {
        return c.FP.get(s);
    }
    
    private static void b(final MetadataField<?> metadataField) {
        if (c.FP.containsKey(metadataField.getName())) {
            throw new IllegalArgumentException("Duplicate field name registered: " + metadataField.getName());
        }
        c.FP.put(metadataField.getName(), metadataField);
    }
    
    public static Collection<MetadataField<?>> fS() {
        return Collections.unmodifiableCollection((Collection<? extends MetadataField<?>>)c.FP.values());
    }
}
