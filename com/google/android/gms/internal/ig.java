// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.moments.ItemScope;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.data.b;

public final class ig extends b implements Moment
{
    private ie VG;
    
    public ig(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    private ie ju() {
        synchronized (this) {
            if (this.VG == null) {
                final byte[] byteArray = this.getByteArray("momentImpl");
                final Parcel obtain = Parcel.obtain();
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                this.VG = ie.CREATOR.aM(obtain);
                obtain.recycle();
            }
            return this.VG;
        }
    }
    
    @Override
    public String getId() {
        return this.ju().getId();
    }
    
    @Override
    public ItemScope getResult() {
        return this.ju().getResult();
    }
    
    @Override
    public String getStartDate() {
        return this.ju().getStartDate();
    }
    
    @Override
    public ItemScope getTarget() {
        return this.ju().getTarget();
    }
    
    @Override
    public String getType() {
        return this.ju().getType();
    }
    
    @Override
    public boolean hasId() {
        return this.ju().hasId();
    }
    
    @Override
    public boolean hasResult() {
        return this.ju().hasId();
    }
    
    @Override
    public boolean hasStartDate() {
        return this.ju().hasStartDate();
    }
    
    @Override
    public boolean hasTarget() {
        return this.ju().hasTarget();
    }
    
    @Override
    public boolean hasType() {
        return this.ju().hasType();
    }
    
    public ie jt() {
        return this.ju();
    }
}
