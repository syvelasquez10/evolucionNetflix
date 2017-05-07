// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import java.util.Iterator;
import android.content.ComponentCallbacks;
import android.os.Build$VERSION;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import android.content.Context;
import java.util.Map;

class TagManager$1 implements DataLayer$zzb
{
    final /* synthetic */ TagManager zzaSw;
    
    TagManager$1(final TagManager zzaSw) {
        this.zzaSw = zzaSw;
    }
    
    @Override
    public void zzH(final Map<String, Object> map) {
        final Object value = map.get("event");
        if (value != null) {
            this.zzaSw.zzeU(value.toString());
        }
    }
}
