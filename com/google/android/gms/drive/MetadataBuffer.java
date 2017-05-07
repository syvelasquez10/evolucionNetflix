// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.data.DataHolder;
import java.util.Iterator;
import java.util.Collection;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.c;
import java.util.ArrayList;
import com.google.android.gms.common.data.DataBuffer;

public final class MetadataBuffer extends DataBuffer<Metadata>
{
    private static final String[] EL;
    private final String EM;
    private a EN;
    
    static {
        final ArrayList list = new ArrayList();
        final Iterator<MetadataField<?>> iterator = c.fS().iterator();
        while (iterator.hasNext()) {
            list.addAll(iterator.next().fR());
        }
        EL = (String[])list.toArray(new String[0]);
    }
    
    public MetadataBuffer(final DataHolder dataHolder, final String em) {
        super(dataHolder);
        this.EM = em;
    }
    
    @Override
    public Metadata get(final int n) {
        final a en = this.EN;
        if (en != null) {
            final a en2 = en;
            if (en.EO == n) {
                return en2;
            }
        }
        final a en2 = new a(this.BB, n);
        this.EN = en2;
        return en2;
    }
    
    public String getNextPageToken() {
        return this.EM;
    }
    
    private static class a extends Metadata
    {
        private final DataHolder BB;
        private final int BE;
        private final int EO;
        
        public a(final DataHolder bb, final int eo) {
            this.BB = bb;
            this.EO = eo;
            this.BE = bb.G(eo);
        }
        
        @Override
        protected <T> T a(final MetadataField<T> metadataField) {
            return metadataField.a(this.BB, this.EO, this.BE);
        }
        
        public Metadata fB() {
            final MetadataBundle ft = MetadataBundle.fT();
            final Iterator<MetadataField<?>> iterator = c.fS().iterator();
            while (iterator.hasNext()) {
                iterator.next().a(this.BB, ft, this.EO, this.BE);
            }
            return new b(ft);
        }
        
        @Override
        public boolean isDataValid() {
            return !this.BB.isClosed();
        }
    }
}
