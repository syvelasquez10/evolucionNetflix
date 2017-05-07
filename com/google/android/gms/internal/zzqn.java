// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzx;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class zzqn
{
    private final List<zzqi> zzaTH;
    
    public zzqn() {
        this.zzaTH = new ArrayList<zzqi>();
    }
    
    public String getId() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<zzqi> iterator = this.zzaTH.iterator();
        int n = 1;
        while (iterator.hasNext()) {
            final zzqi zzqi = iterator.next();
            if (n != 0) {
                n = 0;
            }
            else {
                sb.append("#");
            }
            sb.append(zzqi.getContainerId());
        }
        return sb.toString();
    }
    
    public List<zzqi> zzBv() {
        return this.zzaTH;
    }
    
    public zzqn zzb(final zzqi zzqi) {
        zzx.zzv(zzqi);
        final Iterator<zzqi> iterator = this.zzaTH.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getContainerId().equals(zzqi.getContainerId())) {
                throw new IllegalArgumentException("The container is already being requested. " + zzqi.getContainerId());
            }
        }
        this.zzaTH.add(zzqi);
        return this;
    }
}
