// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;

final class DownloadButtonDialogHelper$5 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Context val$context;
    
    DownloadButtonDialogHelper$5(final Context val$context) {
        this.val$context = val$context;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.val$context.startActivity(OfflineActivity.showAllDownloads((Activity)this.val$context));
        dialogInterface.dismiss();
    }
}
