// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveApi$DriveIdResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Api$a;

class o$5 extends o$g
{
    final /* synthetic */ o Ol;
    final /* synthetic */ String Oo;
    
    o$5(final o ol, final String oo) {
        this.Ol = ol;
        this.Oo = oo;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new GetMetadataRequest(DriveId.bg(this.Oo)), new o$e((BaseImplementation$b<DriveApi$DriveIdResult>)this));
    }
}
