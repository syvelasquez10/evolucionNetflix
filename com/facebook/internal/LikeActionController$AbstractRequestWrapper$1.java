// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.Response;
import com.facebook.Request$Callback;

class LikeActionController$AbstractRequestWrapper$1 implements Request$Callback
{
    final /* synthetic */ LikeActionController$AbstractRequestWrapper this$1;
    
    LikeActionController$AbstractRequestWrapper$1(final LikeActionController$AbstractRequestWrapper this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onCompleted(final Response response) {
        this.this$1.error = response.getError();
        if (this.this$1.error != null) {
            this.this$1.processError(this.this$1.error);
            return;
        }
        this.this$1.processSuccess(response);
    }
}
