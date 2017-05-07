// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;

class UserAgent$FetchAvailableAvatarsListTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$FetchAvailableAvatarsListTask this$1;
    
    UserAgent$FetchAvailableAvatarsListTask$1(final UserAgent$FetchAvailableAvatarsListTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
        this.this$1.mCallback.onAvailableAvatarsListFetched(list, status);
    }
}
