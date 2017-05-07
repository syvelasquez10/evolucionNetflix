// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Bundle;
import android.content.Context;
import com.facebook.internal.PlatformServiceClient;

final class GetTokenClient extends PlatformServiceClient
{
    GetTokenClient(final Context context, final String s) {
        super(context, 65536, 65537, 20121101, s);
    }
    
    @Override
    protected void populateRequestBundle(final Bundle bundle) {
    }
}
