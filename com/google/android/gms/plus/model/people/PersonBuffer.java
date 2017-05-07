// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.people;

import com.google.android.gms.internal.oj;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.ny;
import com.google.android.gms.common.data.e;
import com.google.android.gms.common.data.DataBuffer;

public final class PersonBuffer extends DataBuffer<Person>
{
    private final e<ny> any;
    
    public PersonBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        if (dataHolder.gz() != null && dataHolder.gz().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)) {
            this.any = new e<ny>(dataHolder, (android.os.Parcelable$Creator<ny>)ny.CREATOR);
            return;
        }
        this.any = null;
    }
    
    @Override
    public Person get(final int n) {
        if (this.any != null) {
            return this.any.aq(n);
        }
        return new oj(this.IC, n);
    }
}
