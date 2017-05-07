// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.Window;
import android.graphics.Rect;
import android.app.Activity;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.util.HashSet;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.ArrayList;
import android.net.Uri;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import java.util.List;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.dynamic.d;
import android.view.ViewGroup$LayoutParams;
import android.os.RemoteException;
import android.view.View;
import android.util.DisplayMetrics;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Bundle;
import android.os.Build$VERSION;
import android.content.Context;
import android.content.res.Configuration;
import android.content.ComponentCallbacks;

class u$1 implements ComponentCallbacks
{
    final /* synthetic */ u lw;
    
    u$1(final u lw) {
        this.lw = lw;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (this.lw.lr != null && this.lw.lr.lI != null && this.lw.lr.lI.rN != null) {
            this.lw.lr.lI.rN.bT();
        }
    }
    
    public void onLowMemory() {
    }
}
