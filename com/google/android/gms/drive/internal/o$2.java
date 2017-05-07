// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveApi$ContentsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;

class o$2 extends o$b
{
    final /* synthetic */ o Ol;
    
    o$2(final o ol) {
        this.Ol = ol;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new CreateContentsRequest(536870912), new o$j((BaseImplementation$b<DriveApi$ContentsResult>)this));
    }
}
