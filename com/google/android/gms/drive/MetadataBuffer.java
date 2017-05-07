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
    private static final String[] qS;
    private final String qT;
    
    static {
        final ArrayList list = new ArrayList();
        final Iterator<MetadataField<?>> iterator = c.cW().iterator();
        while (iterator.hasNext()) {
            list.addAll(iterator.next().cV());
        }
        qS = (String[])list.toArray(new String[0]);
    }
    
    public MetadataBuffer(final DataHolder dataHolder, final String qt) {
        super(dataHolder);
        this.qT = qt;
    }
    
    @Override
    public Metadata get(final int n) {
        return new a(this.nE, n);
    }
    
    public String getNextPageToken() {
        return this.qT;
    }
    
    private static class a extends Metadata
    {
        private final DataHolder nE;
        private final int nH;
        private final int qU;
        
        public a(final DataHolder ne, final int qu) {
            this.nE = ne;
            this.qU = qu;
            this.nH = ne.C(qu);
        }
        
        @Override
        protected <T> T a(final MetadataField<T> metadataField) {
            return metadataField.c(this.nE, this.qU, this.nH);
        }
        
        public Metadata cK() {
            final MetadataBundle cx = MetadataBundle.cX();
            final Iterator<MetadataField<?>> iterator = c.cW().iterator();
            while (iterator.hasNext()) {
                iterator.next().a(this.nE, cx, this.qU, this.nH);
            }
            return new b(cx);
        }
        
        @Override
        public boolean isDataValid() {
            return !this.nE.isClosed();
        }
    }
}
