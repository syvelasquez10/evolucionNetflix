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

@zzgr
public class zzfe extends zzfh
{
    private final Context mContext;
    private final Map<String, String> zzvS;
    
    public zzfe(final zziz zziz, final Map<String, String> zzvS) {
        super(zziz, "storePicture");
        this.zzvS = zzvS;
        this.mContext = (Context)zziz.zzgZ();
    }
    
    public void execute() {
        if (this.mContext == null) {
            this.zzak("Activity context is not available");
            return;
        }
        if (!zzp.zzbv().zzL(this.mContext).zzcY()) {
            this.zzak("Feature is not supported by the device.");
            return;
        }
        final String s = this.zzvS.get("iurl");
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.zzak("Image url cannot be empty.");
            return;
        }
        if (!URLUtil.isValidUrl(s)) {
            this.zzak("Invalid image url: " + s);
            return;
        }
        final String zzaj = this.zzaj(s);
        if (!zzp.zzbv().zzaB(zzaj)) {
            this.zzak("Image type not recognized: " + zzaj);
            return;
        }
        final AlertDialog$Builder zzK = zzp.zzbv().zzK(this.mContext);
        zzK.setTitle((CharSequence)zzp.zzby().zzd(R$string.store_picture_title, "Save image"));
        zzK.setMessage((CharSequence)zzp.zzby().zzd(R$string.store_picture_message, "Allow Ad to store image in Picture gallery?"));
        zzK.setPositiveButton((CharSequence)zzp.zzby().zzd(R$string.accept, "Accept"), (DialogInterface$OnClickListener)new zzfe$1(this, s, zzaj));
        zzK.setNegativeButton((CharSequence)zzp.zzby().zzd(R$string.decline, "Decline"), (DialogInterface$OnClickListener)new zzfe$2(this));
        zzK.create().show();
    }
    
    String zzaj(final String s) {
        return Uri.parse(s).getLastPathSegment();
    }
    
    DownloadManager$Request zzg(final String s, final String s2) {
        final DownloadManager$Request downloadManager$Request = new DownloadManager$Request(Uri.parse(s));
        downloadManager$Request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, s2);
        zzp.zzbx().zza(downloadManager$Request);
        return downloadManager$Request;
    }
}
