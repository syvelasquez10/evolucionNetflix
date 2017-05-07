// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.panorama.PanoramaApi$PanoramaResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import android.content.Context;
import android.net.Uri;

class nb$2 extends nb$b
{
    final /* synthetic */ Uri akn;
    final /* synthetic */ nb akp;
    
    nb$2(final nb akp, final Uri akn) {
        this.akp = akp;
        this.akn = akn;
        super((nb$1)null);
    }
    
    @Override
    protected void a(final Context context, final na na) {
        na.a(new nb$c((BaseImplementation$b<PanoramaApi$PanoramaResult>)this), this.akn, null, false);
    }
}
