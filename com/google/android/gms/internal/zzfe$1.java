// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Environment;
import android.app.DownloadManager$Request;
import android.net.Uri;
import android.app.AlertDialog$Builder;
import com.google.android.gms.R$string;
import android.webkit.URLUtil;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;
import android.content.Context;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class zzfe$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ String zzAs;
    final /* synthetic */ String zzAt;
    final /* synthetic */ zzfe zzAu;
    
    zzfe$1(final zzfe zzAu, final String zzAs, final String zzAt) {
        this.zzAu = zzAu;
        this.zzAs = zzAs;
        this.zzAt = zzAt;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final DownloadManager downloadManager = (DownloadManager)this.zzAu.mContext.getSystemService("download");
        try {
            downloadManager.enqueue(this.zzAu.zzg(this.zzAs, this.zzAt));
        }
        catch (IllegalStateException ex) {
            this.zzAu.zzak("Could not store picture.");
        }
    }
}
