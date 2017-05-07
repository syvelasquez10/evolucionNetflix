// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveApi$DriveContentsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;

class o$3 extends o$d
{
    final /* synthetic */ o Ol;
    final /* synthetic */ int Om;
    
    o$3(final o ol, final int om) {
        this.Ol = ol;
        this.Om = om;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new CreateContentsRequest(this.Om), new o$k((BaseImplementation$b<DriveApi$DriveContentsResult>)this));
    }
}
