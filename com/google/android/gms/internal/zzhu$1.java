// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.client.zzk;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.UUID;
import android.os.Build;
import java.util.Locale;
import java.util.ArrayList;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.pm.PackageManager;
import java.net.HttpURLConnection;
import java.util.List;
import com.google.android.gms.ads.internal.zzp;
import android.os.Build$VERSION;
import android.webkit.WebSettings;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.Window;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.app.Activity;
import android.net.Uri$Builder;
import java.util.HashMap;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzm;
import android.net.Uri;
import android.widget.PopupWindow;
import android.view.View;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.app.AlertDialog$Builder;
import android.webkit.WebView;
import java.util.Arrays;
import org.json.JSONObject;
import java.util.Map;
import android.os.Bundle;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.Collection;
import android.os.Looper;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import android.os.Handler;
import android.content.Context;

class zzhu$1 implements Runnable
{
    final /* synthetic */ zzhu zzHN;
    final /* synthetic */ Context zzrn;
    
    zzhu$1(final zzhu zzHN, final Context zzrn) {
        this.zzHN = zzHN;
        this.zzrn = zzrn;
    }
    
    @Override
    public void run() {
        synchronized (this.zzHN.zzpc) {
            this.zzHN.zzHj = this.zzHN.zzK(this.zzrn);
            this.zzHN.zzpc.notifyAll();
        }
    }
}
