// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import java.util.Iterator;
import com.google.android.gms.drive.internal.l;
import com.google.android.gms.internal.kd;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.common.data.a;
import com.google.android.gms.drive.metadata.internal.e;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.common.data.DataHolder;

class MetadataBuffer$a extends Metadata
{
    private final DataHolder IC;
    private final int JR;
    private final int Nk;
    
    public MetadataBuffer$a(final DataHolder ic, final int nk) {
        this.IC = ic;
        this.Nk = nk;
        this.JR = ic.ar(nk);
    }
    
    @Override
    protected <T> T a(final MetadataField<T> metadataField) {
        return metadataField.a(this.IC, this.Nk, this.JR);
    }
    
    public Metadata hR() {
        final MetadataBundle io = MetadataBundle.io();
        for (final MetadataField<a> metadataField : e.in()) {
            if (!(metadataField instanceof b) && metadataField != kd.Qd) {
                metadataField.a(this.IC, io, this.Nk, this.JR);
            }
        }
        return new l(io);
    }
    
    @Override
    public boolean isDataValid() {
        return !this.IC.isClosed();
    }
}
