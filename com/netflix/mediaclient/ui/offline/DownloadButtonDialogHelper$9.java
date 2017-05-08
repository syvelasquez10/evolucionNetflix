// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.settings.SettingsActivity;
import android.app.Activity;
import android.content.DialogInterface;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;

final class DownloadButtonDialogHelper$9 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Context val$context;
    final /* synthetic */ String val$playableId;
    final /* synthetic */ VideoType val$videoType;
    
    DownloadButtonDialogHelper$9(final Context val$context, final String val$playableId, final VideoType val$videoType) {
        this.val$context = val$context;
        this.val$playableId = val$playableId;
        this.val$videoType = val$videoType;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final Intent startIntent = SettingsActivity.createStartIntent((Activity)this.val$context);
        startIntent.putExtra("playableId", this.val$playableId);
        startIntent.putExtra("videoTYpe", this.val$videoType.getValue());
        ((Activity)this.val$context).startActivityForResult(startIntent, NetflixActivity.DL_REQUEST_CODE);
        dialogInterface.dismiss();
    }
}
