// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.DialogInterface;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;

final class DownloadButtonDialogHelper$15 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Context val$context;
    
    DownloadButtonDialogHelper$15(final Context val$context) {
        this.val$context = val$context;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        OfflineUiHelper.showAvailableDownloadsGenreList((NetflixActivity)this.val$context);
        dialogInterface.dismiss();
    }
}
