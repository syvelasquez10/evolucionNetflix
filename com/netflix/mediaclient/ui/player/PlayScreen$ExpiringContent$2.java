// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.Asset;
import java.util.Date;
import junit.framework.Assert;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentAction;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.IExpiringContentWarning;
import android.widget.TextView;
import android.view.View;
import android.view.View$OnClickListener;

class PlayScreen$ExpiringContent$2 implements View$OnClickListener
{
    final /* synthetic */ PlayScreen$ExpiringContent this$1;
    
    PlayScreen$ExpiringContent$2(final PlayScreen$ExpiringContent this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        this.this$1.dismiss();
    }
}
