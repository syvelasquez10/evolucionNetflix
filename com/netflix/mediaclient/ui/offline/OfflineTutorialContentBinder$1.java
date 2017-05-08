// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.View;
import android.view.View$OnClickListener;

class OfflineTutorialContentBinder$1 implements View$OnClickListener
{
    final /* synthetic */ OfflineTutorialContentBinder this$0;
    final /* synthetic */ OfflineTutorialDialogFrag val$dialog;
    
    OfflineTutorialContentBinder$1(final OfflineTutorialContentBinder this$0, final OfflineTutorialDialogFrag val$dialog) {
        this.this$0 = this$0;
        this.val$dialog = val$dialog;
    }
    
    public void onClick(final View view) {
        this.val$dialog.showAvailableToDownload();
        this.val$dialog.dismiss();
    }
}
