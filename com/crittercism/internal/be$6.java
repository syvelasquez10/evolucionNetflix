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
import com.crittercism.app.Transaction;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

final class be$6 extends di
{
    final /* synthetic */ be a;
    final /* synthetic */ be b;
    
    be$6(final be b, final be a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public final void a() {
        while (true) {
            if (this.a.b == be$a.c) {
                break Label_0055;
            }
            final be$6$1 be$6$1 = new be$6$1(this);
            final ExecutorService r = this.b.a.r;
            final FutureTask futureTask = new FutureTask<Object>(be$6$1, null);
            r.execute(futureTask);
            try {
                futureTask.get();
                this.b.a.o.b.block();
                if (be.r) {
                    dw.d("about to delete: " + this.b.l);
                    this.b.a.l.a(this.b.l);
                    if (this.a.b != be$a.h) {
                        this.b.a.m.a(this.a);
                    }
                    return;
                }
            }
            catch (InterruptedException ex) {
                dw.b(ex);
                continue;
            }
            catch (ExecutionException ex2) {
                dw.b(ex2);
                continue;
            }
            break;
        }
        synchronized (be.o) {
            be.o.clear();
        }
    }
}
