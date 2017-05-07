// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import bolts.Task;
import bolts.AppLink;
import android.net.Uri;
import java.util.Map;
import bolts.Continuation;

class FacebookAppLinkResolver$1 implements Continuation<Map<Uri, AppLink>, AppLink>
{
    final /* synthetic */ FacebookAppLinkResolver this$0;
    final /* synthetic */ Uri val$uri;
    
    FacebookAppLinkResolver$1(final FacebookAppLinkResolver this$0, final Uri val$uri) {
        this.this$0 = this$0;
        this.val$uri = val$uri;
    }
    
    @Override
    public AppLink then(final Task<Map<Uri, AppLink>> task) {
        return task.getResult().get(this.val$uri);
    }
}
