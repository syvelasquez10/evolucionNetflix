// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.cast.TextTrackStyle;
import org.json.JSONArray;
import com.google.android.gms.cast.MediaInfo;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import java.util.List;
import com.google.android.gms.cast.MediaStatus;
import java.util.Iterator;
import android.os.SystemClock;

class iq$a implements Runnable
{
    final /* synthetic */ iq Hu;
    
    private iq$a(final iq hu) {
        this.Hu = hu;
    }
    
    @Override
    public void run() {
        boolean b = false;
        this.Hu.Ht = false;
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final Iterator<it> iterator = (Iterator<it>)this.Hu.Hr.iterator();
        while (iterator.hasNext()) {
            iterator.next().e(elapsedRealtime, 2102);
        }
        while (true) {
            while (true) {
                Label_0133: {
                    synchronized (it.Hz) {
                        final Iterator iterator2 = this.Hu.Hr.iterator();
                        if (!iterator2.hasNext()) {
                            // monitorexit(it.Hz)
                            this.Hu.H(b);
                            return;
                        }
                        if (iterator2.next().fW()) {
                            b = true;
                            break Label_0133;
                        }
                        break Label_0133;
                    }
                }
                continue;
            }
        }
    }
}
