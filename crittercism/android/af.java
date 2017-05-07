// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import org.json.JSONObject;

public final class af
{
    s a;
    private boolean b;
    
    public af(final s a) {
        this.a = null;
        this.b = false;
        this.a = a;
    }
    
    public af(final s a, final boolean b) {
        this.a = null;
        this.b = false;
        this.a = a;
        this.b = b;
    }
    
    final JSONObject a(final int n) {
        JSONObject jsonObject = new JSONObject();
        final FutureTask<JSONObject> futureTask = new FutureTask<JSONObject>(new Callable() {});
        final ExecutorService l = crittercism.android.l.i().l;
        try {
            l.execute(futureTask);
            while (!futureTask.isDone()) {
                jsonObject = futureTask.get(n, TimeUnit.MILLISECONDS);
            }
            goto Label_0094;
        }
        catch (TimeoutException ex2) {
            final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
            hashMap.put("success", 1);
            jsonObject = new JSONObject((Map)hashMap);
        }
        catch (Exception ex) {
            new StringBuilder("Exception in sendToNetwork!: ").append(ex.getClass().getName());
            final HashMap<String, Integer> hashMap2 = new HashMap<String, Integer>();
            hashMap2.put("success", 0);
            return new JSONObject((Map)hashMap2);
        }
    }
    
    public final void a() {
        final Thread thread = new Thread(new a(this));
        thread.start();
        if (!this.b) {
            return;
        }
        try {
            thread.join();
        }
        catch (Exception ex) {
            new StringBuilder("Exception in NetworkIO$send(): ").append(ex.getClass().getName());
        }
        catch (InterruptedException ex2) {}
    }
    
    static final class a implements Runnable
    {
        private af a;
        private int b;
        
        public a(final af a) {
            this.a = null;
            this.a = a;
            this.b = 2500;
        }
        
        @Override
        public final void run() {
            this.a.a.a(this.a.a(this.b));
        }
    }
}
