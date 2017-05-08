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

final class be$3 extends di
{
    final /* synthetic */ ax a;
    final /* synthetic */ be b;
    
    be$3(final ax a, final be b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void a() {
        this.a.o.b.block();
        if (be.r) {
            this.a.l.a(this.b);
            return;
        }
        synchronized (be.o) {
            be.o.clear();
        }
    }
}
