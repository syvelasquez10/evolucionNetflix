// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.client.zzl;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.UUID;
import android.os.Build;
import java.util.Locale;
import java.io.FileInputStream;
import java.io.InputStream;
import android.graphics.BitmapFactory;
import com.google.android.gms.common.internal.zzx;
import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.ArrayList;
import android.content.Intent;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.pm.PackageManager;
import java.net.HttpURLConnection;
import java.util.List;
import android.net.Uri$Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import android.os.Build$VERSION;
import android.webkit.WebSettings;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.Window;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.app.Activity;
import android.net.Uri;
import android.widget.PopupWindow;
import android.view.View;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.app.AlertDialog$Builder;
import android.webkit.WebView;
import android.content.Context;
import java.util.Arrays;
import org.json.JSONObject;
import java.util.Map;
import android.os.Bundle;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.Collection;
import android.os.Looper;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import android.os.Handler;

@zzgr
public class zzid
{
    private static final String zzIA;
    private static final String zzIB;
    private static final String zzIC;
    private static final String zzID;
    public static final Handler zzIE;
    private static final String zzIy;
    private static final String zzIz;
    private boolean zzIF;
    private boolean zzIG;
    private String zzIa;
    private final Object zzpd;
    
    static {
        zzIy = AdView.class.getName();
        zzIz = InterstitialAd.class.getName();
        zzIA = PublisherAdView.class.getName();
        zzIB = PublisherInterstitialAd.class.getName();
        zzIC = SearchAdView.class.getName();
        zzID = AdLoader.class.getName();
        zzIE = new zzia(Looper.getMainLooper());
    }
    
    public zzid() {
        this.zzpd = new Object();
        this.zzIF = true;
        this.zzIG = false;
    }
    
    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return;
        }
        zzid.zzIE.post(runnable);
    }
    
    private JSONArray zza(final Collection<?> collection) {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.zza(jsonArray, iterator.next());
        }
        return jsonArray;
    }
    
    private void zza(final JSONArray jsonArray, final Object o) {
        if (o instanceof Bundle) {
            jsonArray.put((Object)this.zze((Bundle)o));
            return;
        }
        if (o instanceof Map) {
            jsonArray.put((Object)this.zzz((Map<String, ?>)o));
            return;
        }
        if (o instanceof Collection) {
            jsonArray.put((Object)this.zza((Collection<?>)o));
            return;
        }
        if (o instanceof Object[]) {
            jsonArray.put((Object)this.zza((Object[])o));
            return;
        }
        jsonArray.put(o);
    }
    
    private void zza(final JSONObject jsonObject, String s, final Object o) {
        if (o instanceof Bundle) {
            jsonObject.put(s, (Object)this.zze((Bundle)o));
            return;
        }
        if (o instanceof Map) {
            jsonObject.put(s, (Object)this.zzz((Map<String, ?>)o));
            return;
        }
        if (o instanceof Collection) {
            if (s == null) {
                s = "null";
            }
            jsonObject.put(s, (Object)this.zza((Collection<?>)o));
            return;
        }
        if (o instanceof Object[]) {
            jsonObject.put(s, (Object)this.zza(Arrays.asList((Object[])o)));
            return;
        }
        jsonObject.put(s, o);
    }
    
    private JSONObject zze(final Bundle bundle) {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            this.zza(jsonObject, s, bundle.get(s));
        }
        return jsonObject;
    }
    
    protected String zzJ(final Context context) {
        return new WebView(context).getSettings().getUserAgentString();
    }
    
    public AlertDialog$Builder zzK(final Context context) {
        return new AlertDialog$Builder(context);
    }
    
    public zzbq zzL(final Context context) {
        return new zzbq(context);
    }
    
    public DisplayMetrics zza(final WindowManager windowManager) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
    
    public PopupWindow zza(final View view, final int n, final int n2, final boolean b) {
        return new PopupWindow(view, n, n2, b);
    }
    
    public String zza(final Context context, final zzan zzan, final String s) {
        if (zzan == null) {
            return s;
        }
        try {
            Uri uri2;
            final Uri uri = uri2 = Uri.parse(s);
            if (zzan.zzc(uri)) {
                uri2 = zzan.zza(uri, context);
            }
            return uri2.toString();
        }
        catch (Exception ex) {
            return s;
        }
    }
    
    public String zza(final zziz zziz, final String s) {
        return this.zza(zziz.getContext(), zziz.zzhg(), s);
    }
    
    JSONArray zza(final Object[] array) {
        final JSONArray jsonArray = new JSONArray();
        for (int length = array.length, i = 0; i < length; ++i) {
            this.zza(jsonArray, array[i]);
        }
        return jsonArray;
    }
    
    public void zza(final Activity activity, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        final Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
        }
    }
    
    public void zza(final Activity activity, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
        final Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnScrollChangedListener(viewTreeObserver$OnScrollChangedListener);
        }
    }
    
    public void zza(final Context context, final String s, final WebSettings webSettings) {
        webSettings.setUserAgentString(this.zzf(context, s));
    }
    
    public void zza(final Context context, final String s, final String s2, final Bundle bundle, final boolean b) {
        if (b) {
            Context applicationContext;
            if ((applicationContext = context.getApplicationContext()) == null) {
                applicationContext = context;
            }
            bundle.putString("os", Build$VERSION.RELEASE);
            bundle.putString("api", String.valueOf(Build$VERSION.SDK_INT));
            bundle.putString("device", zzp.zzbv().zzgE());
            bundle.putString("js", s);
            bundle.putString("appid", applicationContext.getPackageName());
            bundle.putString("eids", TextUtils.join((CharSequence)",", (Iterable)zzby.zzdf()));
        }
        final Uri$Builder appendQueryParameter = new Uri$Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", s2);
        for (final String s3 : bundle.keySet()) {
            appendQueryParameter.appendQueryParameter(s3, bundle.getString(s3));
        }
        zzp.zzbv().zzc(context, s, appendQueryParameter.toString());
    }
    
    public void zza(final Context context, final String s, final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            new zzij(context, s, iterator.next()).zzgz();
        }
    }
    
    public void zza(final Context context, final String s, final List<String> list, final String s2) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            new zzij(context, s, iterator.next(), s2).zzgz();
        }
    }
    
    public void zza(final Context context, final String s, final boolean b, final HttpURLConnection httpURLConnection) {
        this.zza(context, s, b, httpURLConnection, false);
    }
    
    public void zza(final Context context, final String s, final boolean instanceFollowRedirects, final HttpURLConnection httpURLConnection, final String s2) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(instanceFollowRedirects);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", s2);
        httpURLConnection.setUseCaches(false);
    }
    
    public void zza(final Context context, final String s, final boolean instanceFollowRedirects, final HttpURLConnection httpURLConnection, final boolean useCaches) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(instanceFollowRedirects);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", this.zzf(context, s));
        httpURLConnection.setUseCaches(useCaches);
    }
    
    public boolean zza(final PackageManager packageManager, final String s, final String s2) {
        return packageManager.checkPermission(s2, s) == 0;
    }
    
    public int zzaA(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            zzb.zzaH("Could not parse value:" + ex);
            return 0;
        }
    }
    
    public boolean zzaB(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }
    
    public void zzb(final Activity activity, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
        final Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().removeOnScrollChangedListener(viewTreeObserver$OnScrollChangedListener);
        }
    }
    
    public void zzb(final Context context, final Intent intent) {
        try {
            context.startActivity(intent);
        }
        catch (Throwable t) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }
    
    public void zzc(final Context context, final String s, final String s2) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s2);
        this.zza(context, s, list);
    }
    
    public Map<String, String> zze(final Uri uri) {
        if (uri == null) {
            return null;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : zzp.zzbx().zzf(uri)) {
            hashMap.put(s, uri.getQueryParameter(s));
        }
        return hashMap;
    }
    
    public String zzf(final Context p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzid.zzpd:Ljava/lang/Object;
        //     4: astore_3       
        //     5: aload_3        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //    11: ifnull          23
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //    18: astore_1       
        //    19: aload_3        
        //    20: monitorexit    
        //    21: aload_1        
        //    22: areturn        
        //    23: aload_0        
        //    24: invokestatic    com/google/android/gms/ads/internal/zzp.zzbx:()Lcom/google/android/gms/internal/zzie;
        //    27: aload_1        
        //    28: invokevirtual   com/google/android/gms/internal/zzie.getDefaultUserAgent:(Landroid/content/Context;)Ljava/lang/String;
        //    31: putfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //    38: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    41: ifeq            145
        //    44: invokestatic    com/google/android/gms/ads/internal/client/zzl.zzcF:()Lcom/google/android/gms/ads/internal/util/client/zza;
        //    47: invokevirtual   com/google/android/gms/ads/internal/util/client/zza.zzgT:()Z
        //    50: ifne            136
        //    53: aload_0        
        //    54: aconst_null    
        //    55: putfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //    58: getstatic       com/google/android/gms/internal/zzid.zzIE:Landroid/os/Handler;
        //    61: new             Lcom/google/android/gms/internal/zzid$1;
        //    64: dup            
        //    65: aload_0        
        //    66: aload_1        
        //    67: invokespecial   com/google/android/gms/internal/zzid$1.<init>:(Lcom/google/android/gms/internal/zzid;Landroid/content/Context;)V
        //    70: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //    73: pop            
        //    74: aload_0        
        //    75: getfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //    78: astore_1       
        //    79: aload_1        
        //    80: ifnonnull       145
        //    83: aload_0        
        //    84: getfield        com/google/android/gms/internal/zzid.zzpd:Ljava/lang/Object;
        //    87: invokevirtual   java/lang/Object.wait:()V
        //    90: goto            74
        //    93: astore_1       
        //    94: aload_0        
        //    95: aload_0        
        //    96: invokevirtual   com/google/android/gms/internal/zzid.zzgC:()Ljava/lang/String;
        //    99: putfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //   102: new             Ljava/lang/StringBuilder;
        //   105: dup            
        //   106: invokespecial   java/lang/StringBuilder.<init>:()V
        //   109: ldc_w           "Interrupted, use default user agent: "
        //   112: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   115: aload_0        
        //   116: getfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   125: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaH:(Ljava/lang/String;)V
        //   128: goto            74
        //   131: astore_1       
        //   132: aload_3        
        //   133: monitorexit    
        //   134: aload_1        
        //   135: athrow         
        //   136: aload_0        
        //   137: aload_0        
        //   138: aload_1        
        //   139: invokevirtual   com/google/android/gms/internal/zzid.zzJ:(Landroid/content/Context;)Ljava/lang/String;
        //   142: putfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //   145: aload_0        
        //   146: new             Ljava/lang/StringBuilder;
        //   149: dup            
        //   150: invokespecial   java/lang/StringBuilder.<init>:()V
        //   153: aload_0        
        //   154: getfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: ldc_w           " (Mobile; "
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: aload_2        
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: ldc_w           ")"
        //   173: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   176: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   179: putfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //   182: aload_0        
        //   183: getfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //   186: astore_1       
        //   187: aload_3        
        //   188: monitorexit    
        //   189: aload_1        
        //   190: areturn        
        //   191: astore_1       
        //   192: aload_0        
        //   193: aload_0        
        //   194: invokevirtual   com/google/android/gms/internal/zzid.zzgC:()Ljava/lang/String;
        //   197: putfield        com/google/android/gms/internal/zzid.zzIa:Ljava/lang/String;
        //   200: goto            145
        //   203: astore          4
        //   205: goto            34
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  7      21     131    136    Any
        //  23     34     203    208    Ljava/lang/Exception;
        //  23     34     131    136    Any
        //  34     74     131    136    Any
        //  74     79     131    136    Any
        //  83     90     93     131    Ljava/lang/InterruptedException;
        //  83     90     131    136    Any
        //  94     128    131    136    Any
        //  132    134    131    136    Any
        //  136    145    191    203    Ljava/lang/Exception;
        //  136    145    131    136    Any
        //  145    189    131    136    Any
        //  192    200    131    136    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    
    public Bitmap zzg(final Context context, final String s) {
        zzx.zzcj("getBackgroundImage must not be called on the main UI thread.");
        try {
            final FileInputStream openFileInput = context.openFileInput(s);
            final Bitmap decodeStream = BitmapFactory.decodeStream((InputStream)openFileInput);
            openFileInput.close();
            return decodeStream;
        }
        catch (Exception ex) {
            zzb.e("Fail to get background image");
            return null;
        }
    }
    
    public int[] zzg(final Activity activity) {
        final Window window = activity.getWindow();
        if (window != null) {
            final View viewById = window.findViewById(16908290);
            if (viewById != null) {
                return new int[] { viewById.getWidth(), viewById.getHeight() };
            }
        }
        return this.zzgF();
    }
    
    String zzgC() {
        final StringBuffer sb = new StringBuffer(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build$VERSION.RELEASE != null) {
            sb.append(" ").append(Build$VERSION.RELEASE);
        }
        sb.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/").append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }
    
    public String zzgD() {
        final UUID randomUUID = UUID.randomUUID();
        final byte[] byteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        final byte[] byteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String s = new BigInteger(1, byteArray).toString();
        int n = 0;
    Label_0103_Outer:
        while (true) {
            if (n >= 2) {
                return s;
            }
            while (true) {
                try {
                    final MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(byteArray);
                    instance.update(byteArray2);
                    final byte[] array = new byte[8];
                    System.arraycopy(instance.digest(), 0, array, 0, 8);
                    s = new BigInteger(1, array).toString();
                    ++n;
                    continue Label_0103_Outer;
                }
                catch (NoSuchAlgorithmException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public String zzgE() {
        final String manufacturer = Build.MANUFACTURER;
        final String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        }
        return manufacturer + " " + model;
    }
    
    protected int[] zzgF() {
        return new int[] { 0, 0 };
    }
    
    public int[] zzh(final Activity activity) {
        final int[] zzg = this.zzg(activity);
        return new int[] { zzl.zzcF().zzc((Context)activity, zzg[0]), zzl.zzcF().zzc((Context)activity, zzg[1]) };
    }
    
    public int[] zzi(final Activity activity) {
        final Window window = activity.getWindow();
        if (window != null) {
            final View viewById = window.findViewById(16908290);
            if (viewById != null) {
                return new int[] { viewById.getTop(), viewById.getBottom() };
            }
        }
        return this.zzgF();
    }
    
    public int[] zzj(final Activity activity) {
        final int[] zzi = this.zzi(activity);
        return new int[] { zzl.zzcF().zzc((Context)activity, zzi[0]), zzl.zzcF().zzc((Context)activity, zzi[1]) };
    }
    
    public Bitmap zzk(final View view) {
        view.setDrawingCacheEnabled(true);
        final Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }
    
    public JSONObject zzz(final Map<String, ?> map) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            for (final String s : map.keySet()) {
                this.zza(jsonObject, s, map.get(s));
            }
        }
        catch (ClassCastException ex) {
            throw new JSONException("Could not convert map to JSON: " + ex.getMessage());
        }
        return jsonObject;
    }
}
