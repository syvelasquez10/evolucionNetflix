// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import com.google.android.gms.common.internal.zzx;
import java.util.HashSet;
import com.google.android.gms.tagmanager.TagManager;
import java.util.Set;
import android.content.Context;
import com.google.android.gms.tagmanager.Container;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.common.api.ResultCallback;

class zzqg$1 implements ResultCallback<ContainerHolder>
{
    final /* synthetic */ zzqg zzaTs;
    
    zzqg$1(final zzqg zzaTs) {
        this.zzaTs = zzaTs;
    }
    
    public void zza(final ContainerHolder containerHolder) {
        Container container;
        if (containerHolder.getStatus().isSuccess()) {
            container = containerHolder.getContainer();
        }
        else {
            container = null;
        }
        this.zzaTs.zzpo = new zzqe(this.zzaTs.mContext, container, this.zzaTs.zzBn()).zzBh();
        this.zzaTs.zzgK();
    }
}
