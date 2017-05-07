// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.zzk;
import android.app.Activity;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.util.DisplayMetrics;
import android.graphics.Canvas;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import java.util.Iterator;
import android.view.View;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzp;
import android.os.Build$VERSION;
import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.Map;
import com.google.android.gms.ads.internal.overlay.zzd;
import android.webkit.DownloadListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.webkit.WebView;

class zzis$1 implements Runnable
{
    final /* synthetic */ zzis zzJv;
    
    zzis$1(final zzis zzJv) {
        this.zzJv = zzJv;
    }
    
    @Override
    public void run() {
        zzis.zza(this.zzJv);
    }
}
