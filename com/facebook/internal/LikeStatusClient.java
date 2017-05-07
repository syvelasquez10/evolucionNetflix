// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.os.Bundle;
import android.content.Context;

final class LikeStatusClient extends PlatformServiceClient
{
    private String objectId;
    
    LikeStatusClient(final Context context, final String s, final String objectId) {
        super(context, 65542, 65543, 20141001, s);
        this.objectId = objectId;
    }
    
    @Override
    protected void populateRequestBundle(final Bundle bundle) {
        bundle.putString("com.facebook.platform.extra.OBJECT_ID", this.objectId);
    }
}
