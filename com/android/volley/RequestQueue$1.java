// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

class RequestQueue$1 implements RequestQueue$RequestFilter
{
    final /* synthetic */ RequestQueue this$0;
    final /* synthetic */ Object val$tag;
    
    RequestQueue$1(final RequestQueue this$0, final Object val$tag) {
        this.this$0 = this$0;
        this.val$tag = val$tag;
    }
    
    @Override
    public boolean apply(final Request<?> request) {
        return request.getTag() == this.val$tag;
    }
}
