// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import com.facebook.device.yearclass.YearClass;
import com.netflix.mediaclient.ui.iko.InteractiveMomentsFactory;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.iko.InteractiveMomentsManager;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.InteractiveMoments;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class InteractiveMomentsDecorator$1 extends SimpleManagerCallback
{
    final /* synthetic */ InteractiveMomentsDecorator this$0;
    
    InteractiveMomentsDecorator$1(final InteractiveMomentsDecorator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onInteractiveMomentsFetched(final InteractiveMoments interactiveMoments, final Status status) {
        super.onInteractiveMomentsFetched(interactiveMoments, status);
        if (interactiveMoments != null) {
            this.this$0.onMomentsFetched(interactiveMoments.getInteractiveMoments());
        }
    }
}
