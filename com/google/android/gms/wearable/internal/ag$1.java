// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.MessageApi$SendMessageResult;

class ag$1 extends d<MessageApi$SendMessageResult>
{
    final /* synthetic */ byte[] CY;
    final /* synthetic */ String avs;
    final /* synthetic */ String avt;
    final /* synthetic */ ag avu;
    
    ag$1(final ag avu, final String avs, final String avt, final byte[] cy) {
        this.avu = avu;
        this.avs = avs;
        this.avt = avt;
        this.CY = cy;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.a((BaseImplementation$b<MessageApi$SendMessageResult>)this, this.avs, this.avt, this.CY);
    }
    
    protected MessageApi$SendMessageResult aJ(final Status status) {
        return new ag$a(status, -1);
    }
}
