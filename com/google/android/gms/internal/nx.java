// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.moments.ItemScope;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.data.d;

public final class nx extends d implements Moment
{
    private nv amO;
    
    public nx(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    private nv nr() {
        synchronized (this) {
            if (this.amO == null) {
                final byte[] byteArray = this.getByteArray("momentImpl");
                final Parcel obtain = Parcel.obtain();
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                this.amO = nv.CREATOR.dc(obtain);
                obtain.recycle();
            }
            return this.amO;
        }
    }
    
    @Override
    public String getId() {
        return this.nr().getId();
    }
    
    @Override
    public ItemScope getResult() {
        return this.nr().getResult();
    }
    
    @Override
    public String getStartDate() {
        return this.nr().getStartDate();
    }
    
    @Override
    public ItemScope getTarget() {
        return this.nr().getTarget();
    }
    
    @Override
    public String getType() {
        return this.nr().getType();
    }
    
    @Override
    public boolean hasId() {
        return this.nr().hasId();
    }
    
    @Override
    public boolean hasResult() {
        return this.nr().hasResult();
    }
    
    @Override
    public boolean hasStartDate() {
        return this.nr().hasStartDate();
    }
    
    @Override
    public boolean hasTarget() {
        return this.nr().hasTarget();
    }
    
    @Override
    public boolean hasType() {
        return this.nr().hasType();
    }
    
    public nv nq() {
        return this.nr();
    }
}
