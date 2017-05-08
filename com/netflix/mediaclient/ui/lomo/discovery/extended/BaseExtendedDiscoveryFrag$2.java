// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery.extended;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class BaseExtendedDiscoveryFrag$2 implements ErrorWrapper$Callback
{
    final /* synthetic */ BaseExtendedDiscoveryFrag this$0;
    
    BaseExtendedDiscoveryFrag$2(final BaseExtendedDiscoveryFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getActivity())) {
            if (!(this.this$0.getActivity() instanceof ErrorWrapper$Callback)) {
                Log.w("BaseExtendedDiscoveryFrag", "SPY-8068 - BaseExtendedDiscoveryFrag - getActivity() is not a valid type");
                ErrorLoggingManager.logHandledException("SPY-8068 - BaseExtendedDiscoveryFrag - getActivity() is not a valid type");
                return;
            }
            ((ErrorWrapper$Callback)this.this$0.getActivity()).onRetryRequested();
        }
    }
}
