// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveApi$MetadataBufferResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.drive.query.Query;

class o$1 extends o$i
{
    final /* synthetic */ Query Ok;
    final /* synthetic */ o Ol;
    
    o$1(final o ol, final Query ok) {
        this.Ol = ol;
        this.Ok = ok;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new QueryRequest(this.Ok), new o$l((BaseImplementation$b<DriveApi$MetadataBufferResult>)this));
    }
}
