// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.R$string;
import android.app.AlertDialog$Builder;
import android.webkit.URLUtil;
import android.text.TextUtils;
import android.os.Environment;
import android.app.DownloadManager$Request;
import android.net.Uri;
import java.util.Map;
import android.content.Context;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class de$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ String qY;
    final /* synthetic */ String qZ;
    final /* synthetic */ de ra;
    
    de$1(final de ra, final String qy, final String qz) {
        this.ra = ra;
        this.qY = qy;
        this.qZ = qz;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final DownloadManager downloadManager = (DownloadManager)this.ra.mContext.getSystemService("download");
        try {
            downloadManager.enqueue(this.ra.b(this.qY, this.qZ));
        }
        catch (IllegalStateException ex) {
            gs.U("Could not store picture.");
        }
    }
}
