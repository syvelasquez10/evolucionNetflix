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

class zzey$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ String zzzF;
    final /* synthetic */ String zzzG;
    final /* synthetic */ zzey zzzH;
    
    zzey$1(final zzey zzzH, final String zzzF, final String zzzG) {
        this.zzzH = zzzH;
        this.zzzF = zzzF;
        this.zzzG = zzzG;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final DownloadManager downloadManager = (DownloadManager)this.zzzH.mContext.getSystemService("download");
        try {
            downloadManager.enqueue(this.zzzH.zzg(this.zzzF, this.zzzG));
        }
        catch (IllegalStateException ex) {
            this.zzzH.zzah("Could not store picture.");
        }
    }
}
