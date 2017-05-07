// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi$SendMessageResult;

public class ag$a implements MessageApi$SendMessageResult
{
    private final Status CM;
    private final int uQ;
    
    public ag$a(final Status cm, final int uq) {
        this.CM = cm;
        this.uQ = uq;
    }
    
    @Override
    public int getRequestId() {
        return this.uQ;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
