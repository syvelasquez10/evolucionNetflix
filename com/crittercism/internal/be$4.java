// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.os.Build$VERSION;
import java.util.Date;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import android.os.Process;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.Collection;
import java.text.ParseException;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import com.crittercism.app.Transaction;

final class be$4 extends di
{
    final /* synthetic */ be a;
    final /* synthetic */ be b;
    
    be$4(final be b, final be a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public final void a() {
        this.b.a.o.b.block();
        if (be.r) {
            this.b.a.l.a(this.a);
            return;
        }
        synchronized (be.o) {
            be.o.clear();
        }
    }
}
