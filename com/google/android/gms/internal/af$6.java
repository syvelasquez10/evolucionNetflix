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
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import java.util.Map;

class af$6 implements by
{
    final /* synthetic */ af mT;
    
    af$6(final af mt) {
        this.mT = mt;
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        if (!this.mT.a(map)) {
            return;
        }
        gs.S("Received request to untrack: " + this.mT.mC.aC());
        this.mT.destroy();
    }
}
