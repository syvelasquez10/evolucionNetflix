// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import android.net.Uri;
import android.content.ComponentCallbacks;
import android.os.Build$VERSION;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Context;
import java.util.concurrent.ConcurrentMap;
import java.util.Map;

class TagManager$1 implements DataLayer$b
{
    final /* synthetic */ TagManager arD;
    
    TagManager$1(final TagManager arD) {
        this.arD = arD;
    }
    
    @Override
    public void D(final Map<String, Object> map) {
        final Object value = map.get("event");
        if (value != null) {
            this.arD.cQ(value.toString());
        }
    }
}
