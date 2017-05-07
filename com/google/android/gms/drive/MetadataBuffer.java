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
import com.google.android.gms.common.data.DataBuffer;

public final class MetadataBuffer extends DataBuffer<Metadata>
{
    private final String Ni;
    private a Nj;
    
    public MetadataBuffer(final DataHolder dataHolder, final String ni) {
        super(dataHolder);
        this.Ni = ni;
        dataHolder.gz().setClassLoader(MetadataBuffer.class.getClassLoader());
    }
    
    @Override
    public Metadata get(final int n) {
        final a nj = this.Nj;
        if (nj != null) {
            final a nj2 = nj;
            if (nj.Nk == n) {
                return nj2;
            }
        }
        final a nj2 = new a(this.IC, n);
        this.Nj = nj2;
        return nj2;
    }
    
    public String getNextPageToken() {
        return this.Ni;
    }
    
    private static class a extends Metadata
    {
        private final DataHolder IC;
        private final int JR;
        private final int Nk;
        
        public a(final DataHolder ic, final int nk) {
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
            for (final MetadataField<com.google.android.gms.common.data.a> metadataField : e.in()) {
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
}
