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
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import com.crittercism.app.Transaction;
import java.util.Iterator;
import java.util.List;

final class be$1 extends di
{
    final /* synthetic */ List a;
    final /* synthetic */ ax b;
    
    be$1(final List a, final ax b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void a() {
        for (final be be : this.a) {
            synchronized (be) {
                if (be.b != be$a.b) {
                    continue;
                }
                this.b.l.b(be);
                continue;
            }
            break;
        }
    }
}
