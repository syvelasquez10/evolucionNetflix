// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Point;
import android.graphics.Rect;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import org.json.JSONException;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import org.json.JSONArray;
import java.util.Map;
import android.util.DisplayMetrics;
import org.json.JSONObject;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import java.util.concurrent.BlockingQueue;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.view.WindowManager;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@ez
public final class af implements ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    private static final long mK;
    private WeakReference<ViewTreeObserver> mA;
    private final WeakReference<View> mB;
    private final ad mC;
    private final Context mD;
    private final ah mE;
    private boolean mF;
    private final WindowManager mG;
    private final PowerManager mH;
    private final KeyguardManager mI;
    private ag mJ;
    private boolean mL;
    private final BlockingQueue<Runnable> mM;
    private long mN;
    private boolean mO;
    private boolean mP;
    private BroadcastReceiver mQ;
    private final HashSet<ac> mR;
    private boolean mn;
    private final Object mw;
    private final WeakReference<fz> mz;
    
    static {
        mK = TimeUnit.MILLISECONDS.toNanos(100L);
    }
    
    public af(final Context context, final ay ay, final fz fz, final View view, final gt gt) {
        this(ay, fz, gt, view, new aj(context, gt));
    }
    
    public af(final ay ay, final fz fz, final gt gt, final View view, final ah me) {
        this.mw = new Object();
        this.mn = false;
        this.mL = false;
        this.mM = new ArrayBlockingQueue<Runnable>(2);
        this.mN = Long.MIN_VALUE;
        this.mR = new HashSet<ac>();
        this.mz = new WeakReference<fz>(fz);
        this.mB = new WeakReference<View>(view);
        this.mA = new WeakReference<ViewTreeObserver>(null);
        this.mO = true;
        this.mC = new ad(UUID.randomUUID().toString(), gt, ay.of, fz.vp);
        this.mE = me;
        this.mG = (WindowManager)view.getContext().getSystemService("window");
        this.mH = (PowerManager)view.getContext().getApplicationContext().getSystemService("power");
        this.mI = (KeyguardManager)view.getContext().getSystemService("keyguard");
        this.mD = view.getContext().getApplicationContext();
        this.a(me);
        this.mE.a((ah.a)new ah.a() {
            @Override
            public void aM() {
                af.this.mF = true;
                af.this.d(view);
                af.this.aD();
            }
        });
        this.b(this.mE);
        while (true) {
            try {
                this.mM.add(new Runnable() {
                    final /* synthetic */ JSONObject mU = af.this.e(view);
                    
                    @Override
                    public void run() {
                        af.this.a(this.mU);
                    }
                });
                this.mM.add(new Runnable() {
                    @Override
                    public void run() {
                        af.this.e(false);
                    }
                });
                gs.S("Tracking ad unit: " + this.mC.aC());
            }
            catch (Throwable t) {
                continue;
            }
            break;
        }
    }
    
    protected int a(final int n, final DisplayMetrics displayMetrics) {
        return (int)(n / displayMetrics.density);
    }
    
    protected void a(final View view, final Map<String, String> map) {
        this.e(false);
    }
    
    public void a(final ac ac) {
        this.mR.add(ac);
    }
    
    public void a(final ag mj) {
        synchronized (this.mw) {
            this.mJ = mj;
        }
    }
    
    protected void a(final ah ah) {
        ah.f("https://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.html");
    }
    
    protected void a(final JSONObject jsonObject) {
        try {
            final JSONArray jsonArray = new JSONArray();
            final JSONObject jsonObject2 = new JSONObject();
            jsonArray.put((Object)jsonObject);
            jsonObject2.put("units", (Object)jsonArray);
            this.mE.a("AFMA_updateActiveView", jsonObject2);
        }
        catch (Throwable t) {
            gs.b("Skipping active view message.", t);
        }
    }
    
    protected boolean a(final Map<String, String> map) {
        if (map == null) {
            return false;
        }
        final String s = map.get("hashCode");
        return !TextUtils.isEmpty((CharSequence)s) && s.equals(this.mC.aC());
    }
    
    protected void aD() {
        synchronized (this.mw) {
            if (this.mQ != null) {
                return;
            }
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.mQ = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    af.this.e(false);
                }
            };
            this.mD.registerReceiver(this.mQ, intentFilter);
        }
    }
    
    protected void aE() {
        synchronized (this.mw) {
            if (this.mQ != null) {
                this.mD.unregisterReceiver(this.mQ);
                this.mQ = null;
            }
        }
    }
    
    public void aF() {
        synchronized (this.mw) {
            if (!this.mO) {
                return;
            }
            this.mP = true;
            try {
                this.a(this.aL());
                gs.S("Untracking ad unit: " + this.mC.aC());
            }
            catch (JSONException ex) {
                gs.b("JSON Failure while processing active view data.", (Throwable)ex);
            }
        }
    }
    
    protected void aG() {
        if (this.mJ != null) {
            this.mJ.a(this);
        }
    }
    
    public boolean aH() {
        synchronized (this.mw) {
            return this.mO;
        }
    }
    
    protected void aI() {
        final View view = this.mB.get();
        if (view != null) {
            final ViewTreeObserver viewTreeObserver = this.mA.get();
            final ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2 != viewTreeObserver) {
                this.mA = new WeakReference<ViewTreeObserver>(viewTreeObserver2);
                viewTreeObserver2.addOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
                viewTreeObserver2.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
            }
        }
    }
    
    protected void aJ() {
        final ViewTreeObserver viewTreeObserver = this.mA.get();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.removeOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
        viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
    }
    
    protected JSONObject aK() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("afmaVersion", (Object)this.mC.aA()).put("activeViewJSON", (Object)this.mC.aB()).put("timestamp", TimeUnit.NANOSECONDS.toMillis(System.nanoTime())).put("adFormat", (Object)this.mC.az()).put("hashCode", (Object)this.mC.aC());
        return jsonObject;
    }
    
    protected JSONObject aL() throws JSONException {
        final JSONObject ak = this.aK();
        ak.put("doneReasonCode", (Object)"u");
        return ak;
    }
    
    protected void b(final ah ah) {
        ah.a("/updateActiveView", new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                if (!af.this.a(map)) {
                    return;
                }
                af.this.a((View)gv, map);
            }
        });
        ah.a("/untrackActiveViewUnit", new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                if (!af.this.a(map)) {
                    return;
                }
                gs.S("Received request to untrack: " + af.this.mC.aC());
                af.this.destroy();
            }
        });
        ah.a("/visibilityChanged", new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                if (af.this.a(map) && map.containsKey("isVisible")) {
                    af.this.d(Boolean.valueOf("1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"))));
                }
            }
        });
        ah.a("/viewabilityChanged", bx.pA);
    }
    
    protected void d(final View view) {
        final ArrayList<Runnable> list = new ArrayList<Runnable>();
        this.mM.drainTo(list);
        final Iterator<Runnable> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().run();
        }
    }
    
    protected void d(final boolean b) {
        final Iterator<ac> iterator = this.mR.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(this, b);
        }
    }
    
    protected void destroy() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/af.mw:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: invokevirtual   com/google/android/gms/internal/af.aJ:()V
        //    11: aload_0        
        //    12: invokevirtual   com/google/android/gms/internal/af.aE:()V
        //    15: aload_0        
        //    16: iconst_0       
        //    17: putfield        com/google/android/gms/internal/af.mO:Z
        //    20: aload_0        
        //    21: getfield        com/google/android/gms/internal/af.mE:Lcom/google/android/gms/internal/ah;
        //    24: invokeinterface com/google/android/gms/internal/ah.destroy:()V
        //    29: aload_0        
        //    30: invokevirtual   com/google/android/gms/internal/af.aG:()V
        //    33: aload_1        
        //    34: monitorexit    
        //    35: return         
        //    36: astore_2       
        //    37: aload_1        
        //    38: monitorexit    
        //    39: aload_2        
        //    40: athrow         
        //    41: astore_2       
        //    42: goto            29
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  7      20     36     41     Any
        //  20     29     41     45     Ljava/lang/Throwable;
        //  20     29     36     41     Any
        //  29     35     36     41     Any
        //  37     39     36     41     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected JSONObject e(final View view) throws JSONException {
        final int[] array = new int[2];
        final int[] array2 = new int[2];
        view.getLocationOnScreen(array);
        view.getLocationInWindow(array2);
        final JSONObject ak = this.aK();
        final DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        final Rect rect = new Rect();
        rect.left = array[0];
        rect.top = array[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        final Rect rect2 = new Rect();
        rect2.right = this.mG.getDefaultDisplay().getWidth();
        rect2.bottom = this.mG.getDefaultDisplay().getHeight();
        final Rect rect3 = new Rect();
        final boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, (Point)null);
        final Rect rect4 = new Rect();
        ak.put("viewBox", (Object)new JSONObject().put("top", this.a(rect2.top, displayMetrics)).put("bottom", this.a(rect2.bottom, displayMetrics)).put("left", this.a(rect2.left, displayMetrics)).put("right", this.a(rect2.right, displayMetrics))).put("adBox", (Object)new JSONObject().put("top", this.a(rect.top, displayMetrics)).put("bottom", this.a(rect.bottom, displayMetrics)).put("left", this.a(rect.left, displayMetrics)).put("right", this.a(rect.right, displayMetrics))).put("globalVisibleBox", (Object)new JSONObject().put("top", this.a(rect3.top, displayMetrics)).put("bottom", this.a(rect3.bottom, displayMetrics)).put("left", this.a(rect3.left, displayMetrics)).put("right", this.a(rect3.right, displayMetrics))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", (Object)new JSONObject().put("top", this.a(rect4.top, displayMetrics)).put("bottom", this.a(rect4.bottom, displayMetrics)).put("left", this.a(rect4.left, displayMetrics)).put("right", this.a(rect4.right, displayMetrics))).put("localVisibleBoxVisible", view.getLocalVisibleRect(rect4)).put("screenDensity", (double)displayMetrics.density).put("isVisible", this.f(view)).put("isStopped", this.mL).put("isPaused", this.mn);
        return ak;
    }
    
    protected void e(final boolean b) {
        final long nanoTime;
        synchronized (this.mw) {
            if (!this.mF || !this.mO) {
                return;
            }
            nanoTime = System.nanoTime();
            if (b && this.mN + af.mK > nanoTime) {
                return;
            }
        }
        this.mN = nanoTime;
        final fz fz = this.mz.get();
        final View view = this.mB.get();
        while (true) {
            Label_0154: {
                if (view == null) {
                    break Label_0154;
                }
                if (fz == null) {
                    break Label_0154;
                }
                final int n = 0;
                if (n != 0) {
                    this.aF();
                    // monitorexit(o)
                    return;
                }
                while (true) {
                    try {
                        this.a(this.e(view));
                        this.aI();
                        this.aG();
                        // monitorexit(o)
                        return;
                    }
                    catch (JSONException ex) {
                        gs.a("Active view update failed.", (Throwable)ex);
                        continue;
                    }
                    break;
                }
            }
            final int n = 1;
            continue;
        }
    }
    
    protected boolean f(final View view) {
        return view.getVisibility() == 0 && view.isShown() && this.mH.isScreenOn() && !this.mI.inKeyguardRestrictedInputMode();
    }
    
    public void onGlobalLayout() {
        this.e(false);
    }
    
    public void onScrollChanged() {
        this.e(true);
    }
    
    public void pause() {
        synchronized (this.mw) {
            this.mn = true;
            this.e(false);
            this.mE.pause();
        }
    }
    
    public void resume() {
        synchronized (this.mw) {
            this.mE.resume();
            this.e(this.mn = false);
        }
    }
    
    public void stop() {
        synchronized (this.mw) {
            this.mL = true;
            this.e(false);
            this.mE.pause();
        }
    }
}
