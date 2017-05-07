// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataHolder;

public final class b extends com.google.android.gms.common.data.b implements AppState
{
    b(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public AppState aK() {
        return new a(this);
    }
    
    @Override
    public boolean equals(final Object o) {
        return a.a(this, o);
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
        return !this.M("conflict_version");
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
