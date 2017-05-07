// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import org.json.JSONObject;
import com.google.android.gms.internal.is;

class RemoteMediaPlayer$b$1 implements is
{
    final /* synthetic */ RemoteMediaPlayer$b Gc;
    
    RemoteMediaPlayer$b$1(final RemoteMediaPlayer$b gc) {
        this.Gc = gc;
    }
    
    @Override
    public void a(final long n, final int n2, final JSONObject jsonObject) {
        this.Gc.b((R)new RemoteMediaPlayer$c(new Status(n2), jsonObject));
    }
    
    @Override
    public void n(final long n) {
        this.Gc.b((R)this.Gc.l(new Status(2103)));
    }
}
