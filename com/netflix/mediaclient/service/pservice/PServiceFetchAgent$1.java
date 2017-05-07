// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import java.util.HashMap;

class PServiceFetchAgent$1 extends HashMap<PDiskData$ListName, Integer>
{
    final /* synthetic */ PServiceFetchAgent this$0;
    
    PServiceFetchAgent$1(final PServiceFetchAgent this$0) {
        this.this$0 = this$0;
        this.put(PDiskData$ListName.BILLBOARD, 1);
        this.put(PDiskData$ListName.CW, 1);
        this.put(PDiskData$ListName.IQ, 3);
        this.put(PDiskData$ListName.STANDARD_FIRST, 3);
    }
}
