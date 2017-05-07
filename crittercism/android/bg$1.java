// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.Build$VERSION;
import java.util.Date;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import android.os.Process;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.Collection;
import java.text.ParseException;
import org.json.JSONException;
import java.util.concurrent.TimeUnit;
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

final class bg$1 extends dj
{
    final /* synthetic */ List a;
    final /* synthetic */ az b;
    
    bg$1(final List a, final az b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void a() {
        for (final bg bg : this.a) {
            synchronized (bg) {
                if (bg.j != bg$a.b) {
                    continue;
                }
                this.b.n.b(bg);
                continue;
            }
            break;
        }
    }
}
