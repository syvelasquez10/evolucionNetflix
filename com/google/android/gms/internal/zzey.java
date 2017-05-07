// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Environment;
import android.app.DownloadManager$Request;
import android.net.Uri;
import android.app.AlertDialog$Builder;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.R$string;
import android.webkit.URLUtil;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;
import android.content.Context;

@zzgk
public class zzey extends zzfb
{
    private final Context mContext;
    private final Map<String, String> zzvs;
    
    public zzey(final zzip zzip, final Map<String, String> zzvs) {
        super(zzip, "storePicture");
        this.zzvs = zzvs;
        this.mContext = (Context)zzip.zzgN();
    }
    
    public void execute() {
        if (this.mContext == null) {
            this.zzah("Activity context is not available");
            return;
        }
        if (!zzp.zzbx().zzM(this.mContext).zzcX()) {
            this.zzah("Feature is not supported by the device.");
            return;
        }
        final String s = this.zzvs.get("iurl");
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.zzah("Image url cannot be empty.");
            return;
        }
        if (!URLUtil.isValidUrl(s)) {
            this.zzah("Invalid image url: " + s);
            return;
        }
        final String zzag = this.zzag(s);
        if (!zzp.zzbx().zzay(zzag)) {
            this.zzah("Image type not recognized: " + zzag);
            return;
        }
        final AlertDialog$Builder zzL = zzp.zzbx().zzL(this.mContext);
        zzL.setTitle((CharSequence)zzp.zzbA().zzc(R$string.store_picture_title, "Save image"));
        zzL.setMessage((CharSequence)zzp.zzbA().zzc(R$string.store_picture_message, "Allow Ad to store image in Picture gallery?"));
        zzL.setPositiveButton((CharSequence)zzp.zzbA().zzc(R$string.accept, "Accept"), (DialogInterface$OnClickListener)new zzey$1(this, s, zzag));
        zzL.setNegativeButton((CharSequence)zzp.zzbA().zzc(R$string.decline, "Decline"), (DialogInterface$OnClickListener)new zzey$2(this));
        zzL.create().show();
    }
    
    String zzag(final String s) {
        return Uri.parse(s).getLastPathSegment();
    }
    
    DownloadManager$Request zzg(final String s, final String s2) {
        final DownloadManager$Request downloadManager$Request = new DownloadManager$Request(Uri.parse(s));
        downloadManager$Request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, s2);
        zzp.zzbz().zza(downloadManager$Request);
        return downloadManager$Request;
    }
}
