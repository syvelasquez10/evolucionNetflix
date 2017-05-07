// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class b extends d implements AppState
{
    b(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    @Override
    public boolean equals(final Object o) {
        return a.a(this, o);
    }
    
    public AppState fp() {
        return new a(this);
    }
    
    @Override
    public byte[] getConflictData() {
        return this.getByteArray("conflict_data");
    }
    
    @Override
    public String getConflictVersion() {
        return this.getString("conflict_version");
    }
    
    @Override
    public int getKey() {
        return this.getInteger("key");
    }
    
    @Override
    public byte[] getLocalData() {
        return this.getByteArray("local_data");
    }
    
    @Override
    public String getLocalVersion() {
        return this.getString("local_version");
    }
    
    @Override
    public boolean hasConflict() {
        return !this.aS("conflict_version");
    }
    
    @Override
    public int hashCode() {
        return a.a(this);
    }
    
    @Override
    public String toString() {
        return a.b(this);
    }
}
