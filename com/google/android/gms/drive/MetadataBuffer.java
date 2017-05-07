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
    private MetadataBuffer$a Nj;
    
    public MetadataBuffer(final DataHolder dataHolder, final String ni) {
        super(dataHolder);
        this.Ni = ni;
        dataHolder.gz().setClassLoader(MetadataBuffer.class.getClassLoader());
    }
    
    @Override
    public Metadata get(final int n) {
        final MetadataBuffer$a nj = this.Nj;
        if (nj != null) {
            final MetadataBuffer$a nj2 = nj;
            if (nj.Nk == n) {
                return nj2;
            }
        }
        final MetadataBuffer$a nj2 = new MetadataBuffer$a(this.IC, n);
        this.Nj = nj2;
        return nj2;
    }
    
    public String getNextPageToken() {
        return this.Ni;
    }
}
