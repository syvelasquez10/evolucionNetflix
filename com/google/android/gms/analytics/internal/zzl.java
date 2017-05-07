// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import android.util.Pair;
import java.util.HashMap;
import android.text.TextUtils;
import com.google.android.gms.internal.zzof;
import android.database.sqlite.SQLiteException;
import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.AnalyticsService;
import com.google.android.gms.analytics.AnalyticsReceiver;
import java.util.Iterator;
import com.google.android.gms.internal.zzob;
import java.util.Map;
import com.google.android.gms.internal.zzok;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzod;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.internal.zzol;
import com.google.android.gms.common.internal.zzx;

class zzl extends zzd
{
    private boolean mStarted;
    private final zzj zzMn;
    private final zzah zzMo;
    private final zzag zzMp;
    private final zzi zzMq;
    private long zzMr;
    private final zzt zzMs;
    private final zzt zzMt;
    private final zzaj zzMu;
    private long zzMv;
    private boolean zzMw;
    
    protected zzl(final zzf zzf, final zzg zzg) {
        super(zzf);
        zzx.zzv(zzg);
        this.zzMr = Long.MIN_VALUE;
        this.zzMp = zzg.zzk(zzf);
        this.zzMn = zzg.zzm(zzf);
        this.zzMo = zzg.zzn(zzf);
        this.zzMq = zzg.zzo(zzf);
        this.zzMu = new zzaj(this.zzid());
        this.zzMs = new zzl$1(this, zzf);
        this.zzMt = new zzl$2(this, zzf);
    }
    
    private void zza(final zzh zzh, final zzol zzol) {
        zzx.zzv(zzh);
        zzx.zzv(zzol);
        final zza zza = new zza(this.zzia());
        zza.zzaN(zzh.zzix());
        zza.enableAdvertisingIdCollection(zzh.zziy());
        final zzob zzhq = zza.zzhq();
        final zzjb zzjb = zzhq.zze(zzjb.class);
        zzjb.zzaS("data");
        zzjb.zzH(true);
        zzhq.zzb(zzol);
        final zzja zzja = zzhq.zze(zzja.class);
        final zzok zzok = zzhq.zze(zzok.class);
        for (final Map.Entry<String, String> entry : zzh.zzn().entrySet()) {
            final String s = entry.getKey();
            final String userId = entry.getValue();
            if ("an".equals(s)) {
                zzok.setAppName(userId);
            }
            else if ("av".equals(s)) {
                zzok.setAppVersion(userId);
            }
            else if ("aid".equals(s)) {
                zzok.setAppId(userId);
            }
            else if ("aiid".equals(s)) {
                zzok.setAppInstallerId(userId);
            }
            else if ("uid".equals(s)) {
                zzjb.setUserId(userId);
            }
            else {
                zzja.set(s, userId);
            }
        }
        this.zzb("Sending installation campaign to", zzh.zzix(), zzol);
        zzhq.zzL(this.zzii().zzky());
        zzhq.zzxl();
    }
    
    private boolean zzbf(final String s) {
        return this.getContext().checkCallingOrSelfPermission(s) == 0;
    }
    
    private void zziM() {
        final Context context = this.zzia().getContext();
        if (!AnalyticsReceiver.zzV(context)) {
            this.zzbb("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        }
        else if (!AnalyticsService.zzW(context)) {
            this.zzbc("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzV(context)) {
            this.zzbb("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        else if (!CampaignTrackingService.zzW(context)) {
            this.zzbb("CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
    }
    
    private void zziO() {
        this.zzb(new zzl$4(this));
    }
    
    private void zziP() {
        while (true) {
            try {
                this.zzMn.zziG();
                this.zziU();
                this.zzMt.zzt(this.zzif().zzjM());
            }
            catch (SQLiteException ex) {
                this.zzd("Failed to delete stale hits", ex);
                continue;
            }
            break;
        }
    }
    
    private boolean zziV() {
        return !this.zzMw && (!this.zzif().zzjk() || this.zzif().zzjl()) && this.zzjb() > 0L;
    }
    
    private void zziW() {
        final zzv zzih = this.zzih();
        if (zzih.zzjU() && !zzih.zzbr()) {
            final long zziH = this.zziH();
            if (zziH != 0L && Math.abs(this.zzid().currentTimeMillis() - zziH) <= this.zzif().zzju()) {
                this.zza("Dispatch alarm scheduled (ms)", this.zzif().zzjt());
                zzih.zzjV();
            }
        }
    }
    
    private void zziX() {
        this.zziW();
        final long zzjb = this.zzjb();
        final long zzkA = this.zzii().zzkA();
        long n;
        if (zzkA != 0L) {
            n = zzjb - Math.abs(this.zzid().currentTimeMillis() - zzkA);
            if (n <= 0L) {
                n = Math.min(this.zzif().zzjr(), zzjb);
            }
        }
        else {
            n = Math.min(this.zzif().zzjr(), zzjb);
        }
        this.zza("Dispatch scheduled (ms)", n);
        if (this.zzMs.zzbr()) {
            this.zzMs.zzu(Math.max(1L, n + this.zzMs.zzjR()));
            return;
        }
        this.zzMs.zzt(n);
    }
    
    private void zziY() {
        this.zziZ();
        this.zzja();
    }
    
    private void zziZ() {
        if (this.zzMs.zzbr()) {
            this.zzaY("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzMs.cancel();
    }
    
    private void zzja() {
        final zzv zzih = this.zzih();
        if (zzih.zzbr()) {
            zzih.cancel();
        }
    }
    
    protected void onServiceConnected() {
        this.zzic();
        if (!this.zzif().zzjk()) {
            this.zziR();
        }
    }
    
    void start() {
        this.zzio();
        zzx.zza(!this.mStarted, "Analytics backend already started");
        this.mStarted = true;
        if (!this.zzif().zzjk()) {
            this.zziM();
        }
        this.zzig().zzf(new zzl$3(this));
    }
    
    public void zzI(final boolean b) {
        this.zziU();
    }
    
    public long zza(final zzh p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/google/android/gms/common/internal/zzx.zzv:(Ljava/lang/Object;)Ljava/lang/Object;
        //     4: pop            
        //     5: aload_0        
        //     6: invokevirtual   com/google/android/gms/analytics/internal/zzl.zzio:()V
        //     9: aload_0        
        //    10: invokevirtual   com/google/android/gms/analytics/internal/zzl.zzic:()V
        //    13: aload_0        
        //    14: getfield        com/google/android/gms/analytics/internal/zzl.zzMn:Lcom/google/android/gms/analytics/internal/zzj;
        //    17: invokevirtual   com/google/android/gms/analytics/internal/zzj.beginTransaction:()V
        //    20: aload_0        
        //    21: getfield        com/google/android/gms/analytics/internal/zzl.zzMn:Lcom/google/android/gms/analytics/internal/zzj;
        //    24: aload_1        
        //    25: invokevirtual   com/google/android/gms/analytics/internal/zzh.zziw:()J
        //    28: aload_1        
        //    29: invokevirtual   com/google/android/gms/analytics/internal/zzh.getClientId:()Ljava/lang/String;
        //    32: invokevirtual   com/google/android/gms/analytics/internal/zzj.zza:(JLjava/lang/String;)V
        //    35: aload_0        
        //    36: getfield        com/google/android/gms/analytics/internal/zzl.zzMn:Lcom/google/android/gms/analytics/internal/zzj;
        //    39: aload_1        
        //    40: invokevirtual   com/google/android/gms/analytics/internal/zzh.zziw:()J
        //    43: aload_1        
        //    44: invokevirtual   com/google/android/gms/analytics/internal/zzh.getClientId:()Ljava/lang/String;
        //    47: aload_1        
        //    48: invokevirtual   com/google/android/gms/analytics/internal/zzh.zzix:()Ljava/lang/String;
        //    51: invokevirtual   com/google/android/gms/analytics/internal/zzj.zza:(JLjava/lang/String;Ljava/lang/String;)J
        //    54: lstore_3       
        //    55: iload_2        
        //    56: ifne            88
        //    59: aload_1        
        //    60: lload_3        
        //    61: invokevirtual   com/google/android/gms/analytics/internal/zzh.zzn:(J)V
        //    64: aload_0        
        //    65: getfield        com/google/android/gms/analytics/internal/zzl.zzMn:Lcom/google/android/gms/analytics/internal/zzj;
        //    68: aload_1        
        //    69: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzb:(Lcom/google/android/gms/analytics/internal/zzh;)V
        //    72: aload_0        
        //    73: getfield        com/google/android/gms/analytics/internal/zzl.zzMn:Lcom/google/android/gms/analytics/internal/zzj;
        //    76: invokevirtual   com/google/android/gms/analytics/internal/zzj.setTransactionSuccessful:()V
        //    79: aload_0        
        //    80: getfield        com/google/android/gms/analytics/internal/zzl.zzMn:Lcom/google/android/gms/analytics/internal/zzj;
        //    83: invokevirtual   com/google/android/gms/analytics/internal/zzj.endTransaction:()V
        //    86: lload_3        
        //    87: lreturn        
        //    88: aload_1        
        //    89: lconst_1       
        //    90: lload_3        
        //    91: ladd           
        //    92: invokevirtual   com/google/android/gms/analytics/internal/zzh.zzn:(J)V
        //    95: goto            64
        //    98: astore_1       
        //    99: aload_0        
        //   100: ldc_w           "Failed to update Analytics property"
        //   103: aload_1        
        //   104: invokevirtual   com/google/android/gms/analytics/internal/zzl.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   107: aload_0        
        //   108: getfield        com/google/android/gms/analytics/internal/zzl.zzMn:Lcom/google/android/gms/analytics/internal/zzj;
        //   111: invokevirtual   com/google/android/gms/analytics/internal/zzj.endTransaction:()V
        //   114: ldc2_w          -1
        //   117: lreturn        
        //   118: astore_1       
        //   119: aload_0        
        //   120: ldc_w           "Failed to end transaction"
        //   123: aload_1        
        //   124: invokevirtual   com/google/android/gms/analytics/internal/zzl.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   127: ldc2_w          -1
        //   130: lreturn        
        //   131: astore_1       
        //   132: aload_0        
        //   133: ldc_w           "Failed to end transaction"
        //   136: aload_1        
        //   137: invokevirtual   com/google/android/gms/analytics/internal/zzl.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   140: lload_3        
        //   141: lreturn        
        //   142: astore_1       
        //   143: aload_0        
        //   144: getfield        com/google/android/gms/analytics/internal/zzl.zzMn:Lcom/google/android/gms/analytics/internal/zzj;
        //   147: invokevirtual   com/google/android/gms/analytics/internal/zzj.endTransaction:()V
        //   150: aload_1        
        //   151: athrow         
        //   152: astore          5
        //   154: aload_0        
        //   155: ldc_w           "Failed to end transaction"
        //   158: aload           5
        //   160: invokevirtual   com/google/android/gms/analytics/internal/zzl.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   163: goto            150
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  13     55     98     131    Landroid/database/sqlite/SQLiteException;
        //  13     55     142    166    Any
        //  59     64     98     131    Landroid/database/sqlite/SQLiteException;
        //  59     64     142    166    Any
        //  64     79     98     131    Landroid/database/sqlite/SQLiteException;
        //  64     79     142    166    Any
        //  79     86     131    142    Landroid/database/sqlite/SQLiteException;
        //  88     95     98     131    Landroid/database/sqlite/SQLiteException;
        //  88     95     142    166    Any
        //  99     107    142    166    Any
        //  107    114    118    131    Landroid/database/sqlite/SQLiteException;
        //  143    150    152    166    Landroid/database/sqlite/SQLiteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
    
    public void zza(zzab zzf) {
        zzx.zzv(zzf);
        zzof.zzic();
        this.zzio();
        if (this.zzMw) {
            this.zzaZ("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        }
        else {
            this.zza("Delivering hit", zzf);
        }
        zzf = this.zzf(zzf);
        this.zziQ();
        if (this.zzMq.zzb(zzf)) {
            this.zzaZ("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        if (this.zzif().zzjk()) {
            this.zzie().zza(zzf, "Service unavailable on package side");
            return;
        }
        try {
            this.zzMn.zzc(zzf);
            this.zziU();
        }
        catch (SQLiteException ex) {
            this.zze("Delivery failed to save hit to a database", ex);
            this.zzie().zza(zzf, "deliver: failed to insert hit to database");
        }
    }
    
    public void zza(final zzw zzw, final long n) {
        zzof.zzic();
        this.zzio();
        long abs = -1L;
        final long zzkA = this.zzii().zzkA();
        if (zzkA != 0L) {
            abs = Math.abs(this.zzid().currentTimeMillis() - zzkA);
        }
        this.zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", abs);
        if (!this.zzif().zzjk()) {
            this.zziQ();
        }
        try {
            if (this.zziS()) {
                this.zzig().zzf(new zzl$5(this, zzw, n));
                return;
            }
            this.zzii().zzkB();
            this.zziU();
            if (zzw != null) {
                zzw.zzc(null);
            }
            if (this.zzMv != n) {
                this.zzMp.zzkt();
            }
        }
        catch (Throwable t) {
            this.zze("Local dispatch failed", t);
            this.zzii().zzkB();
            this.zziU();
            if (zzw != null) {
                zzw.zzc(t);
            }
        }
    }
    
    public void zzb(final zzw zzw) {
        this.zza(zzw, this.zzMv);
    }
    
    public void zzbg(final String s) {
        zzx.zzcs(s);
        this.zzic();
        this.zzib();
        final zzol zza = zzam.zza(this.zzie(), s);
        if (zza == null) {
            this.zzd("Parsing failed. Ignoring invalid campaign data", s);
        }
        else {
            final String zzkC = this.zzii().zzkC();
            if (s.equals(zzkC)) {
                this.zzbb("Ignoring duplicate install campaign");
                return;
            }
            if (!TextUtils.isEmpty((CharSequence)zzkC)) {
                this.zzd("Ignoring multiple install campaigns. original, new", zzkC, s);
                return;
            }
            this.zzii().zzbk(s);
            if (this.zzii().zzkz().zzv(this.zzif().zzjP())) {
                this.zzd("Campaign received too late, ignoring", zza);
                return;
            }
            this.zzb("Received installation campaign", zza);
            final Iterator<zzh> iterator = this.zzMn.zzr(0L).iterator();
            while (iterator.hasNext()) {
                this.zza(iterator.next(), zza);
            }
        }
    }
    
    protected void zzc(final zzh zzh) {
        this.zzic();
        this.zzb("Sending first hit to property", zzh.zzix());
        if (!this.zzii().zzkz().zzv(this.zzif().zzjP())) {
            final String zzkC = this.zzii().zzkC();
            if (!TextUtils.isEmpty((CharSequence)zzkC)) {
                final zzol zza = zzam.zza(this.zzie(), zzkC);
                this.zzb("Found relevant installation campaign", zza);
                this.zza(zzh, zza);
            }
        }
    }
    
    zzab zzf(final zzab zzab) {
        if (TextUtils.isEmpty((CharSequence)zzab.zzko())) {
            final Pair<String, Long> zzkG = this.zzii().zzkD().zzkG();
            if (zzkG != null) {
                final String string = zzkG.second + ":" + (String)zzkG.first;
                final HashMap<String, String> hashMap = new HashMap<String, String>(zzab.zzn());
                hashMap.put("_m", string);
                return zzab.zza(this, zzab, hashMap);
            }
        }
        return zzab;
    }
    
    @Override
    protected void zzhB() {
        this.zzMn.zza();
        this.zzMo.zza();
        this.zzMq.zza();
    }
    
    public void zzhX() {
        zzof.zzic();
        this.zzio();
        this.zzaY("Service disconnected");
    }
    
    void zzhZ() {
        this.zzic();
        this.zzMv = this.zzid().currentTimeMillis();
    }
    
    public long zziH() {
        zzof.zzic();
        this.zzio();
        try {
            return this.zzMn.zziH();
        }
        catch (SQLiteException ex) {
            this.zze("Failed to get min/max hit times from local store", ex);
            return 0L;
        }
    }
    
    protected void zziN() {
        this.zzio();
        this.zzii().zzky();
        if (!this.zzbf("android.permission.ACCESS_NETWORK_STATE")) {
            this.zzbc("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            this.zzjc();
        }
        if (!this.zzbf("android.permission.INTERNET")) {
            this.zzbc("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            this.zzjc();
        }
        if (AnalyticsService.zzW(this.getContext())) {
            this.zzaY("AnalyticsService registered in the app manifest and enabled");
        }
        else if (this.zzif().zzjk()) {
            this.zzbc("Device AnalyticsService not registered! Hits will not be delivered reliably.");
        }
        else {
            this.zzbb("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzMw && !this.zzif().zzjk() && !this.zzMn.isEmpty()) {
            this.zziQ();
        }
        this.zziU();
    }
    
    protected void zziQ() {
        if (!this.zzMw && this.zzif().zzjm() && !this.zzMq.isConnected() && this.zzMu.zzv(this.zzif().zzjH())) {
            this.zzMu.start();
            this.zzaY("Connecting to service");
            if (this.zzMq.connect()) {
                this.zzaY("Connected to service");
                this.zzMu.clear();
                this.onServiceConnected();
            }
        }
    }
    
    public void zziR() {
        zzof.zzic();
        this.zzio();
        this.zzib();
        if (!this.zzif().zzjm()) {
            this.zzbb("Service client disabled. Can't dispatch local hits to device AnalyticsService");
        }
        if (!this.zzMq.isConnected()) {
            this.zzaY("Service not connected");
        }
        else if (!this.zzMn.isEmpty()) {
            this.zzaY("Dispatching local hits to device AnalyticsService");
            while (true) {
                Label_0126: {
                    List<zzab> zzp;
                    try {
                        zzp = this.zzMn.zzp(this.zzif().zzjv());
                        if (zzp.isEmpty()) {
                            this.zziU();
                            return;
                        }
                        break Label_0126;
                    }
                    catch (SQLiteException ex) {
                        this.zze("Failed to read hits from store", ex);
                        this.zziY();
                        return;
                    }
                    while (true) {
                        zzab zzab = null;
                        zzp.remove(zzab);
                        try {
                            this.zzMn.zzq(zzab.zzkj());
                            if (zzp.isEmpty()) {
                                break;
                            }
                            zzab = zzp.get(0);
                            if (!this.zzMq.zzb(zzab)) {
                                this.zziU();
                                return;
                            }
                            continue;
                        }
                        catch (SQLiteException ex2) {
                            this.zze("Failed to remove hit that was send for delivery", ex2);
                            this.zziY();
                        }
                    }
                }
            }
        }
    }
    
    protected boolean zziS() {
        boolean b = true;
        zzof.zzic();
        this.zzio();
        this.zzaY("Dispatching a batch of local hits");
        boolean b2;
        if (!this.zzMq.isConnected() && !this.zzif().zzjk()) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (this.zzMo.zzku()) {
            b = false;
        }
        if (b2 && b) {
            this.zzaY("No network or service available. Will retry later");
            return false;
        }
        final long n = Math.max(this.zzif().zzjv(), this.zzif().zzjw());
        final ArrayList<Long> list = new ArrayList<Long>();
        long n2 = 0L;
    Label_0416_Outer:
        while (true) {
            while (true) {
                long n3 = 0L;
                Label_0788: {
                    try {
                        while (true) {
                            this.zzMn.beginTransaction();
                            list.clear();
                            List<zzab> zzp = null;
                            Label_0345: {
                                try {
                                    zzp = this.zzMn.zzp(n);
                                    if (zzp.isEmpty()) {
                                        this.zzaY("Store is empty, nothing to dispatch");
                                        this.zziY();
                                        try {
                                            this.zzMn.setTransactionSuccessful();
                                            this.zzMn.endTransaction();
                                            return false;
                                        }
                                        catch (SQLiteException ex) {
                                            this.zze("Failed to commit local dispatch transaction", ex);
                                            this.zziY();
                                            return false;
                                        }
                                    }
                                    this.zza("Hits loaded from store. count", zzp.size());
                                    final Iterator<zzab> iterator = zzp.iterator();
                                    Block_19: {
                                        while (iterator.hasNext()) {
                                            if (iterator.next().zzkj() == n2) {
                                                break Block_19;
                                            }
                                        }
                                        break Label_0345;
                                    }
                                    this.zzd("Database contains successfully uploaded hit", n2, zzp.size());
                                    this.zziY();
                                    try {
                                        this.zzMn.setTransactionSuccessful();
                                        this.zzMn.endTransaction();
                                        return false;
                                    }
                                    catch (SQLiteException ex2) {
                                        this.zze("Failed to commit local dispatch transaction", ex2);
                                        this.zziY();
                                        return false;
                                    }
                                }
                                catch (SQLiteException ex3) {
                                    this.zzd("Failed to read hits from persisted store", ex3);
                                    this.zziY();
                                    try {
                                        this.zzMn.setTransactionSuccessful();
                                        this.zzMn.endTransaction();
                                        return false;
                                    }
                                    catch (SQLiteException ex4) {
                                        this.zze("Failed to commit local dispatch transaction", ex4);
                                        this.zziY();
                                        return false;
                                    }
                                }
                            }
                            n3 = n2;
                            if (!this.zzMq.isConnected()) {
                                break;
                            }
                            n3 = n2;
                            if (this.zzif().zzjk()) {
                                break;
                            }
                            this.zzaY("Service connected, sending hits to the service");
                            Label_0620: {
                                List<Long> zzf;
                                while (true) {
                                    n3 = n2;
                                    if (zzp.isEmpty()) {
                                        break Label_0788;
                                    }
                                    final zzab zzab = zzp.get(0);
                                    if (this.zzMq.zzb(zzab)) {
                                        n2 = Math.max(n2, zzab.zzkj());
                                        zzp.remove(zzab);
                                        this.zzb("Hit sent do device AnalyticsService for delivery", zzab);
                                        try {
                                            this.zzMn.zzq(zzab.zzkj());
                                            list.add(zzab.zzkj());
                                            continue Label_0416_Outer;
                                        }
                                        catch (SQLiteException ex5) {
                                            this.zze("Failed to remove hit that was send for delivery", ex5);
                                            this.zziY();
                                            try {
                                                this.zzMn.setTransactionSuccessful();
                                                this.zzMn.endTransaction();
                                                return false;
                                            }
                                            catch (SQLiteException ex6) {
                                                this.zze("Failed to commit local dispatch transaction", ex6);
                                                this.zziY();
                                                return false;
                                            }
                                        }
                                        break;
                                    }
                                    n3 = n2;
                                    if (this.zzMo.zzku()) {
                                        zzf = this.zzMo.zzf(zzp);
                                        final Iterator<Long> iterator2 = zzf.iterator();
                                        while (iterator2.hasNext()) {
                                            n2 = Math.max(n2, iterator2.next());
                                        }
                                        break;
                                    }
                                    break Label_0620;
                                }
                                zzp.removeAll(zzf);
                                try {
                                    this.zzMn.zzd(zzf);
                                    list.addAll((Collection<?>)zzf);
                                    n3 = n2;
                                    if (list.isEmpty()) {
                                        try {
                                            this.zzMn.setTransactionSuccessful();
                                            this.zzMn.endTransaction();
                                            return false;
                                        }
                                        catch (SQLiteException ex7) {
                                            this.zze("Failed to commit local dispatch transaction", ex7);
                                            this.zziY();
                                            return false;
                                        }
                                    }
                                }
                                catch (SQLiteException ex8) {
                                    this.zze("Failed to remove successfully uploaded hits", ex8);
                                    this.zziY();
                                    try {
                                        this.zzMn.setTransactionSuccessful();
                                        this.zzMn.endTransaction();
                                        return false;
                                    }
                                    catch (SQLiteException ex9) {
                                        this.zze("Failed to commit local dispatch transaction", ex9);
                                        this.zziY();
                                        return false;
                                    }
                                }
                            }
                            try {
                                this.zzMn.setTransactionSuccessful();
                                this.zzMn.endTransaction();
                                n2 = n3;
                            }
                            catch (SQLiteException ex10) {
                                this.zze("Failed to commit local dispatch transaction", ex10);
                                this.zziY();
                                return false;
                            }
                        }
                    }
                    finally {
                        try {
                            this.zzMn.setTransactionSuccessful();
                            this.zzMn.endTransaction();
                        }
                        catch (SQLiteException list) {
                            this.zze("Failed to commit local dispatch transaction", list);
                            this.zziY();
                            return false;
                        }
                    }
                }
                n2 = n3;
                continue;
            }
        }
    }
    
    public void zziU() {
        this.zzia().zzic();
        this.zzio();
        if (!this.zziV()) {
            this.zzMp.unregister();
            this.zziY();
            return;
        }
        if (this.zzMn.isEmpty()) {
            this.zzMp.unregister();
            this.zziY();
            return;
        }
        int connected;
        if (!zzy.zzNH.get()) {
            this.zzMp.zzkr();
            connected = (this.zzMp.isConnected() ? 1 : 0);
        }
        else {
            connected = 1;
        }
        if (connected != 0) {
            this.zziX();
            return;
        }
        this.zziY();
        this.zziW();
    }
    
    public long zzjb() {
        long n;
        if (this.zzMr != Long.MIN_VALUE) {
            n = this.zzMr;
        }
        else {
            n = this.zzif().zzjs();
            if (this.zzhA().zzke()) {
                return this.zzhA().zzkV() * 1000L;
            }
        }
        return n;
    }
    
    public void zzjc() {
        this.zzio();
        this.zzic();
        this.zzMw = true;
        this.zzMq.disconnect();
        this.zziU();
    }
}
