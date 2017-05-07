// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import android.text.TextUtils;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$a;

class Cast$CastApi$a$9 extends Cast$b
{
    final /* synthetic */ Cast$CastApi$a EG;
    final /* synthetic */ String EJ;
    
    Cast$CastApi$a$9(final Cast$CastApi$a eg, final String ej) {
        this.EG = eg;
        this.EJ = ej;
        super((Cast$1)null);
    }
    
    @Override
    protected void a(final ij ij) {
        if (TextUtils.isEmpty((CharSequence)this.EJ)) {
            this.e(2001, "IllegalArgument: sessionId cannot be null or empty");
            return;
        }
        try {
            ij.a(this.EJ, (BaseImplementation$b<Status>)this);
        }
        catch (IllegalStateException ex) {
            this.V(2001);
        }
    }
}
