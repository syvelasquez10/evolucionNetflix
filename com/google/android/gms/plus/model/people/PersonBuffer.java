// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.people;

import com.google.android.gms.internal.ir;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.ig;
import com.google.android.gms.common.data.c;
import com.google.android.gms.common.data.DataBuffer;

public final class PersonBuffer extends DataBuffer<Person>
{
    private final c<ig> FZ;
    
    public PersonBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        if (dataHolder.getMetadata() != null && dataHolder.getMetadata().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)) {
            this.FZ = new c<ig>(dataHolder, (android.os.Parcelable$Creator<ig>)ig.CREATOR);
            return;
        }
        this.FZ = null;
    }
    
    @Override
    public Person get(final int n) {
        if (this.FZ != null) {
            return this.FZ.B(n);
        }
        return new ir(this.nE, n);
    }
}
