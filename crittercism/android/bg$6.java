// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.Build$VERSION;
import java.util.Date;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import android.os.Process;
import java.util.Collection;
import java.text.ParseException;
import org.json.JSONException;
import java.util.concurrent.TimeUnit;
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

final class bg$6 extends dj
{
    final /* synthetic */ bg a;
    final /* synthetic */ bg b;
    
    bg$6(final bg b, final bg a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public final void a() {
        while (true) {
            if (this.a.j == bg$a.c) {
                break Label_0055;
            }
            final bg$6$1 bg$6$1 = new bg$6$1(this);
            final ExecutorService s = this.b.a.s;
            final FutureTask futureTask = new FutureTask(bg$6$1, null);
            s.execute(futureTask);
            try {
                futureTask.get();
                this.b.a.q.a.block();
                this.b.a.n.a(this.b.l);
                this.b.a.o.a(this.a);
            }
            catch (InterruptedException ex) {
                dy.a(ex);
                continue;
            }
            catch (ExecutionException ex2) {
                dy.a(ex2);
                continue;
            }
            break;
        }
    }
}
