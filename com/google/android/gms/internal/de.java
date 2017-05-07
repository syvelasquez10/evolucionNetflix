// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.R$string;
import android.app.AlertDialog$Builder;
import android.webkit.URLUtil;
import android.text.TextUtils;
import android.os.Environment;
import android.app.DownloadManager$Request;
import android.net.Uri;
import java.util.Map;
import android.content.Context;

@ez
public class de
{
    private final Context mContext;
    private final gv md;
    private final Map<String, String> qM;
    
    public de(final gv md, final Map<String, String> qm) {
        this.md = md;
        this.qM = qm;
        this.mContext = md.dA();
    }
    
    String B(final String s) {
        return Uri.parse(s).getLastPathSegment();
    }
    
    DownloadManager$Request b(final String s, final String s2) {
        final DownloadManager$Request downloadManager$Request = new DownloadManager$Request(Uri.parse(s));
        downloadManager$Request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, s2);
        downloadManager$Request.allowScanningByMediaScanner();
        downloadManager$Request.setNotificationVisibility(1);
        return downloadManager$Request;
    }
    
    public void execute() {
        if (!new bl(this.mContext).bl()) {
            gs.W("Store picture feature is not supported on this device.");
            return;
        }
        if (TextUtils.isEmpty((CharSequence)this.qM.get("iurl"))) {
            gs.W("Image url cannot be empty.");
            return;
        }
        final String s = this.qM.get("iurl");
        if (!URLUtil.isValidUrl(s)) {
            gs.W("Invalid image url:" + s);
            return;
        }
        final String b = this.B(s);
        if (!gj.N(b)) {
            gs.W("Image type not recognized:");
            return;
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
        alertDialog$Builder.setTitle((CharSequence)gb.c(R$string.store_picture_title, "Save image"));
        alertDialog$Builder.setMessage((CharSequence)gb.c(R$string.store_picture_message, "Allow Ad to store image in Picture gallery?"));
        alertDialog$Builder.setPositiveButton((CharSequence)gb.c(R$string.accept, "Accept"), (DialogInterface$OnClickListener)new de$1(this, s, b));
        alertDialog$Builder.setNegativeButton((CharSequence)gb.c(R$string.decline, "Decline"), (DialogInterface$OnClickListener)new de$2(this));
        alertDialog$Builder.create().show();
    }
}
