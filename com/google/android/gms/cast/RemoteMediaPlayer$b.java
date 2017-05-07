// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.is;

abstract class RemoteMediaPlayer$b extends Cast$a<RemoteMediaPlayer$MediaChannelResult>
{
    is Gb;
    
    RemoteMediaPlayer$b() {
        this.Gb = new RemoteMediaPlayer$b$1(this);
    }
    
    public RemoteMediaPlayer$MediaChannelResult l(final Status status) {
        return new RemoteMediaPlayer$b$2(this, status);
    }
}
