// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.Asset;
import java.util.Date;
import junit.framework.Assert;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentAction;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.IExpiringContentWarning;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PlayScreen$ExpiringContent$1 extends LoggingManagerCallback
{
    final /* synthetic */ PlayScreen$ExpiringContent this$1;
    
    PlayScreen$ExpiringContent$1(final PlayScreen$ExpiringContent this$1, final String s) {
        this.this$1 = this$1;
        super(s);
    }
    
    @Override
    public void onExpiringContentWarning(final IExpiringContentWarning expiringContentWarning, final Status status, final ExpiringContentAction expiringContentAction) {
        if (status.isSucces() && expiringContentAction == ExpiringContentAction.SHOULD_SHOW_NOTICE && expiringContentWarning != null && expiringContentWarning.willExpire()) {
            this.this$1.showWarning(expiringContentWarning);
        }
    }
}
