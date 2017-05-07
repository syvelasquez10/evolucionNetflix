// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.zzl;
import android.app.Activity;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONException;
import android.webkit.WebViewClient;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.util.DisplayMetrics;
import android.graphics.Canvas;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.webkit.ValueCallback;
import java.util.Iterator;
import android.view.View;
import android.webkit.WebSettings;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import com.google.android.gms.ads.internal.zzp;
import android.os.Build$VERSION;
import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import java.util.Map;
import android.webkit.DownloadListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.webkit.WebView;

class zzjd$1 implements Runnable
{
    final /* synthetic */ zzjd zzKB;
    
    zzjd$1(final zzjd zzKB) {
        this.zzKB = zzKB;
    }
    
    @Override
    public void run() {
        zzjd.zza(this.zzKB);
    }
}
