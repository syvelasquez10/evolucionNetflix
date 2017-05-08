// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class DownloadButtonDialogHelper$14 implements DialogInterface$OnClickListener
{
    final /* synthetic */ DialogInterface$OnClickListener val$deleteClick;
    
    DownloadButtonDialogHelper$14(final DialogInterface$OnClickListener val$deleteClick) {
        this.val$deleteClick = val$deleteClick;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.val$deleteClick.onClick(dialogInterface, n);
        dialogInterface.dismiss();
    }
}
