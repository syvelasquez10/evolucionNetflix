// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Point;
import android.graphics.Rect;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import org.json.JSONException;
import android.content.IntentFilter;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Map;
import android.util.DisplayMetrics;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import java.util.concurrent.BlockingQueue;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.view.WindowManager;
import android.content.Context;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View;

class af$1 implements ah$a
{
    final /* synthetic */ View mS;
    final /* synthetic */ af mT;
    
    af$1(final af mt, final View ms) {
        this.mT = mt;
        this.mS = ms;
    }
    
    @Override
    public void aM() {
        this.mT.mF = true;
        this.mT.d(this.mS);
        this.mT.aD();
    }
}
