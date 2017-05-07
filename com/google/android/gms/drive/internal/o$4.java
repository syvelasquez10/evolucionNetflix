// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.drive.Contents;

class o$4 extends p$a
{
    final /* synthetic */ o Ol;
    final /* synthetic */ Contents On;
    
    o$4(final o ol, final Contents on) {
        this.Ol = ol;
        this.On = on;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new CloseContentsRequest(this.On, false), new bb((BaseImplementation$b<Status>)this));
    }
}
