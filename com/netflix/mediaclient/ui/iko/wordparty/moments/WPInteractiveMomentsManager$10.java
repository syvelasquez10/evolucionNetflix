// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class WPInteractiveMomentsManager$10 implements View$OnClickListener
{
    final /* synthetic */ WPInteractiveMomentsManager this$0;
    
    WPInteractiveMomentsManager$10(final WPInteractiveMomentsManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "Close button onClick invoked ");
        }
        this.this$0.hide();
        this.this$0.reportCommandEvent(UIViewLogging$UIViewCommandName.backButton);
        this.this$0.reportMomentEnded(IClientLogging$CompletionReason.canceled);
    }
}
