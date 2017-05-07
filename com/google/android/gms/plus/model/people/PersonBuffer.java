// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.people;

import com.google.android.gms.internal.is;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.ih;
import com.google.android.gms.common.data.c;
import com.google.android.gms.common.data.DataBuffer;

public final class PersonBuffer extends DataBuffer<Person>
{
    private final c<ih> Wr;
    
    public PersonBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        if (dataHolder.getMetadata() != null && dataHolder.getMetadata().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)) {
            this.Wr = new c<ih>(dataHolder, (android.os.Parcelable$Creator<ih>)ih.CREATOR);
            return;
        }
        this.Wr = null;
    }
    
    @Override
    public Person get(final int n) {
        if (this.Wr != null) {
            return this.Wr.F(n);
        }
        return new is(this.BB, n);
    }
}
