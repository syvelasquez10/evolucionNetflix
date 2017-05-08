// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class OfflineErrorDialog$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ OfflineErrorDialog this$0;
    
    OfflineErrorDialog$2(final OfflineErrorDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        dialogInterface.dismiss();
    }
}
