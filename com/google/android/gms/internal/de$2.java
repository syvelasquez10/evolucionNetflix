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
import org.json.JSONObject;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class de$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ de ra;
    
    de$2(final de ra) {
        this.ra = ra;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.ra.md.b("onStorePictureCanceled", new JSONObject());
    }
}
