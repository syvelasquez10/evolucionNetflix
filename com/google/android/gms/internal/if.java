// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.moments.ItemScope;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.data.b;

public final class if extends b implements Moment
{
    private id Fo;
    
    public if(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    private id fu() {
        synchronized (this) {
            if (this.Fo == null) {
                final byte[] byteArray = this.getByteArray("momentImpl");
                final Parcel obtain = Parcel.obtain();
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                this.Fo = id.CREATOR.at(obtain);
                obtain.recycle();
            }
            return this.Fo;
        }
    }
    
    public id ft() {
        return this.fu();
    }
    
    @Override
    public String getId() {
        return this.fu().getId();
    }
    
    @Override
    public ItemScope getResult() {
        return this.fu().getResult();
    }
    
    @Override
    public String getStartDate() {
        return this.fu().getStartDate();
    }
    
    @Override
    public ItemScope getTarget() {
        return this.fu().getTarget();
    }
    
    @Override
    public String getType() {
        return this.fu().getType();
    }
    
    @Override
    public boolean hasId() {
        return this.fu().hasId();
    }
    
    @Override
    public boolean hasResult() {
        return this.fu().hasId();
    }
    
    @Override
    public boolean hasStartDate() {
        return this.fu().hasStartDate();
    }
    
    @Override
    public boolean hasTarget() {
        return this.fu().hasTarget();
    }
    
    @Override
    public boolean hasType() {
        return this.fu().hasType();
    }
}
