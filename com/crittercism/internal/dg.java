// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.ConditionVariable;

public final class dg extends di
{
    public ConditionVariable a;
    public ConditionVariable b;
    private az c;
    private Context d;
    private au e;
    private av f;
    private ar g;
    private List h;
    private boolean i;
    private boolean j;
    private ap k;
    private Exception l;
    
    public dg(final az c, final Context d, final au e, final av f, final ar g, final ap k) {
        this.a = new ConditionVariable();
        this.b = new ConditionVariable();
        this.h = new ArrayList();
        this.i = false;
        this.l = null;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.k = k;
        this.j = false;
    }
    
    private void b() {
        synchronized (this) {
            this.i = true;
        }
    }
    
    private boolean c() {
        synchronized (this) {
            return this.i;
        }
    }
    
    private File d() {
        int i = 0;
        final File file = new File(this.d.getFilesDir().getAbsolutePath() + "/" + this.c.b());
        if (file.exists() && file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length == 1) {
                    final File file2 = listFiles[0];
                    file2.isFile();
                    if (file2.isFile()) {
                        return file2;
                    }
                }
                else if (listFiles.length > 1) {
                    while (i < listFiles.length) {
                        final File file3 = listFiles[i];
                        file3.isFile();
                        file3.delete();
                        ++i;
                    }
                }
            }
        }
        return null;
    }
    
    private void e() {
        if (!this.j) {
            if (!this.j) {
                final bq o = this.e.o();
                final bq p = this.e.p();
                final bq q = this.e.q();
                final bq r = this.e.r();
                final bq s = this.e.s();
                final du z = this.e.z();
                final String b = this.c.c.b;
                final df df = new df(this.d);
                df.a(o, new ct$a(), this.c.c.d, "/v0/appload", this.c.c.b, this.g, new cs$b());
                df.a(p, new da$a(), this.c.c.b, "/android_v2/handle_exceptions", null, this.g, new cu$a());
                df.a(r, new da$a(), this.c.c.b, "/android_v2/handle_ndk_crashes", null, this.g, new cu$a());
                df.a(s, new da$a(), this.c.c.b, "/android_v2/handle_crashes", null, this.g, new cu$a());
                df.a(q, new da$a(), this.c.c.b, "/android_v2/handle_exceptions", null, new ay(this.g, this.c), new cu$a());
                df.a();
                if (z.b()) {
                    ax.C().G();
                }
            }
            this.k.a = new bk(this.g);
            if (!this.k.b && ap.a(this.d)) {
                dw.d("Discovered App Load during init task");
                this.k.b();
            }
            if (this.k.d()) {
                dw.d("about to send App Load from init task");
                final ap k = this.k;
                final au e = this.e;
                final az c = this.c;
                final Context d = this.d;
                final ar g = this.g;
                if (!k.d.a()) {
                    k.c();
                    final bq o2 = e.o();
                    final String b2 = c.c.b;
                    final bk a = k.a;
                    if (a != null) {
                        o2.a(a);
                    }
                    final df df2 = new df(d);
                    df2.a(o2, new ct$a(), c.c.d, "/v0/appload", c.c.b, g, new cs$b());
                    df2.a();
                }
            }
        }
    }
    
    @Override
    public final void a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "Running critter init task"
        //     2: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //     5: new             Ljava/io/File;
        //     8: dup            
        //     9: new             Ljava/lang/StringBuilder;
        //    12: dup            
        //    13: invokespecial   java/lang/StringBuilder.<init>:()V
        //    16: aload_0        
        //    17: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //    20: invokevirtual   android/content/Context.getFilesDir:()Ljava/io/File;
        //    23: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: ldc             "/com.crittercism/pending"
        //    31: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    34: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    37: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    40: astore_2       
        //    41: aload_2        
        //    42: invokevirtual   java/io/File.exists:()Z
        //    45: ifeq            506
        //    48: aload_2        
        //    49: invokevirtual   java/io/File.isDirectory:()Z
        //    52: ifne            506
        //    55: ldc             "Someone is tampering!!!!"
        //    57: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //    60: invokestatic    com/crittercism/internal/ax.C:()Lcom/crittercism/internal/ax;
        //    63: getfield        com/crittercism/internal/ax.w:Lcom/crittercism/internal/dq;
        //    66: invokevirtual   com/crittercism/internal/dq.a:()Ljava/lang/String;
        //    69: pop            
        //    70: aload_0        
        //    71: getfield        com/crittercism/internal/dg.g:Lcom/crittercism/internal/ar;
        //    74: invokeinterface com/crittercism/internal/ar.l:()Lcom/crittercism/internal/dv;
        //    79: astore_2       
        //    80: aload_2        
        //    81: aload_0        
        //    82: getfield        com/crittercism/internal/dg.f:Lcom/crittercism/internal/av;
        //    85: invokevirtual   com/crittercism/internal/dv.a:(Lcom/crittercism/internal/av;)V
        //    88: aload_0        
        //    89: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //    92: invokestatic    com/crittercism/internal/dp.a:(Landroid/content/Context;)Ljava/lang/Boolean;
        //    95: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    98: putstatic       com/crittercism/internal/dp.a:Z
        //   101: aload_0        
        //   102: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   105: iconst_0       
        //   106: invokestatic    com/crittercism/internal/dp.a:(Landroid/content/Context;Z)V
        //   109: aload_0        
        //   110: aload_0        
        //   111: getfield        com/crittercism/internal/dg.g:Lcom/crittercism/internal/ar;
        //   114: invokeinterface com/crittercism/internal/ar.m:()Lcom/crittercism/internal/dr;
        //   119: invokevirtual   com/crittercism/internal/dr.a:()Z
        //   122: putfield        com/crittercism/internal/dg.j:Z
        //   125: aload_0        
        //   126: getfield        com/crittercism/internal/dg.j:Z
        //   129: ifne            205
        //   132: new             Lcom/crittercism/internal/ds;
        //   135: dup            
        //   136: aload_0        
        //   137: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   140: invokespecial   com/crittercism/internal/ds.<init>:(Landroid/content/Context;)V
        //   143: astore_3       
        //   144: aload_3        
        //   145: invokevirtual   com/crittercism/internal/ds.a:()I
        //   148: istore_1       
        //   149: aload_3        
        //   150: getfield        com/crittercism/internal/ds.a:Landroid/content/SharedPreferences;
        //   153: invokeinterface android/content/SharedPreferences.edit:()Landroid/content/SharedPreferences$Editor;
        //   158: ldc_w           "numAppLoads"
        //   161: iload_1        
        //   162: iconst_1       
        //   163: iadd           
        //   164: invokeinterface android/content/SharedPreferences$Editor.putInt:(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;
        //   169: invokeinterface android/content/SharedPreferences$Editor.commit:()Z
        //   174: pop            
        //   175: invokestatic    com/crittercism/internal/ax.C:()Lcom/crittercism/internal/ax;
        //   178: aload_3        
        //   179: putfield        com/crittercism/internal/ax.A:Lcom/crittercism/internal/ds;
        //   182: aload_2        
        //   183: invokevirtual   com/crittercism/internal/dv.a:()Lcom/crittercism/internal/dt;
        //   186: aload_0        
        //   187: getfield        com/crittercism/internal/dg.f:Lcom/crittercism/internal/av;
        //   190: getstatic       com/crittercism/internal/cq.j:Lcom/crittercism/internal/cq;
        //   193: getfield        com/crittercism/internal/cq.m:Ljava/lang/String;
        //   196: getstatic       com/crittercism/internal/cq.j:Lcom/crittercism/internal/cq;
        //   199: getfield        com/crittercism/internal/cq.n:Ljava/lang/String;
        //   202: invokevirtual   com/crittercism/internal/dt.a:(Lcom/crittercism/internal/av;Ljava/lang/String;Ljava/lang/String;)V
        //   205: aload_0        
        //   206: invokespecial   com/crittercism/internal/dg.d:()Ljava/io/File;
        //   209: astore_3       
        //   210: aload_0        
        //   211: getfield        com/crittercism/internal/dg.j:Z
        //   214: ifeq            530
        //   217: aload_0        
        //   218: getfield        com/crittercism/internal/dg.b:Landroid/os/ConditionVariable;
        //   221: invokevirtual   android/os/ConditionVariable.open:()V
        //   224: invokestatic    com/crittercism/internal/ax.C:()Lcom/crittercism/internal/ax;
        //   227: getfield        com/crittercism/internal/ax.s:Z
        //   230: ifne            411
        //   233: aload_3        
        //   234: ifnull          249
        //   237: aload_3        
        //   238: invokevirtual   java/io/File.exists:()Z
        //   241: ifeq            249
        //   244: aload_3        
        //   245: invokevirtual   java/io/File.isFile:()Z
        //   248: pop            
        //   249: new             Lcom/crittercism/internal/bq;
        //   252: dup            
        //   253: aload_0        
        //   254: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   257: getstatic       com/crittercism/internal/bp.a:Lcom/crittercism/internal/bp;
        //   260: invokespecial   com/crittercism/internal/bq.<init>:(Landroid/content/Context;Lcom/crittercism/internal/bp;)V
        //   263: invokevirtual   com/crittercism/internal/bq.a:()V
        //   266: new             Lcom/crittercism/internal/bq;
        //   269: dup            
        //   270: aload_0        
        //   271: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   274: getstatic       com/crittercism/internal/bp.b:Lcom/crittercism/internal/bp;
        //   277: invokespecial   com/crittercism/internal/bq.<init>:(Landroid/content/Context;Lcom/crittercism/internal/bp;)V
        //   280: invokevirtual   com/crittercism/internal/bq.a:()V
        //   283: new             Lcom/crittercism/internal/bq;
        //   286: dup            
        //   287: aload_0        
        //   288: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   291: getstatic       com/crittercism/internal/bp.c:Lcom/crittercism/internal/bp;
        //   294: invokespecial   com/crittercism/internal/bq.<init>:(Landroid/content/Context;Lcom/crittercism/internal/bp;)V
        //   297: invokevirtual   com/crittercism/internal/bq.a:()V
        //   300: new             Lcom/crittercism/internal/bq;
        //   303: dup            
        //   304: aload_0        
        //   305: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   308: getstatic       com/crittercism/internal/bp.d:Lcom/crittercism/internal/bp;
        //   311: invokespecial   com/crittercism/internal/bq.<init>:(Landroid/content/Context;Lcom/crittercism/internal/bp;)V
        //   314: invokevirtual   com/crittercism/internal/bq.a:()V
        //   317: new             Lcom/crittercism/internal/bq;
        //   320: dup            
        //   321: aload_0        
        //   322: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   325: getstatic       com/crittercism/internal/bp.e:Lcom/crittercism/internal/bp;
        //   328: invokespecial   com/crittercism/internal/bq.<init>:(Landroid/content/Context;Lcom/crittercism/internal/bp;)V
        //   331: invokevirtual   com/crittercism/internal/bq.a:()V
        //   334: new             Lcom/crittercism/internal/bq;
        //   337: dup            
        //   338: aload_0        
        //   339: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   342: getstatic       com/crittercism/internal/bp.f:Lcom/crittercism/internal/bp;
        //   345: invokespecial   com/crittercism/internal/bq.<init>:(Landroid/content/Context;Lcom/crittercism/internal/bp;)V
        //   348: invokevirtual   com/crittercism/internal/bq.a:()V
        //   351: new             Lcom/crittercism/internal/bq;
        //   354: dup            
        //   355: aload_0        
        //   356: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   359: getstatic       com/crittercism/internal/bp.h:Lcom/crittercism/internal/bp;
        //   362: invokespecial   com/crittercism/internal/bq.<init>:(Landroid/content/Context;Lcom/crittercism/internal/bp;)V
        //   365: invokevirtual   com/crittercism/internal/bq.a:()V
        //   368: new             Lcom/crittercism/internal/bq;
        //   371: dup            
        //   372: aload_0        
        //   373: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   376: getstatic       com/crittercism/internal/bp.g:Lcom/crittercism/internal/bp;
        //   379: invokespecial   com/crittercism/internal/bq.<init>:(Landroid/content/Context;Lcom/crittercism/internal/bp;)V
        //   382: invokevirtual   com/crittercism/internal/bq.a:()V
        //   385: new             Lcom/crittercism/internal/bq;
        //   388: dup            
        //   389: aload_0        
        //   390: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   393: getstatic       com/crittercism/internal/bp.k:Lcom/crittercism/internal/bp;
        //   396: invokespecial   com/crittercism/internal/bq.<init>:(Landroid/content/Context;Lcom/crittercism/internal/bp;)V
        //   399: invokevirtual   com/crittercism/internal/bq.a:()V
        //   402: aload_3        
        //   403: ifnull          411
        //   406: aload_3        
        //   407: invokevirtual   java/io/File.delete:()Z
        //   410: pop            
        //   411: aload_0        
        //   412: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   415: invokestatic    com/crittercism/internal/h.b:(Landroid/content/Context;)V
        //   418: aload_0        
        //   419: invokespecial   com/crittercism/internal/dg.b:()V
        //   422: aload_0        
        //   423: getfield        com/crittercism/internal/dg.h:Ljava/util/List;
        //   426: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   431: astore_2       
        //   432: aload_2        
        //   433: invokeinterface java/util/Iterator.hasNext:()Z
        //   438: ifeq            1171
        //   441: aload_2        
        //   442: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   447: checkcast       Ljava/lang/Runnable;
        //   450: invokeinterface java/lang/Runnable.run:()V
        //   455: goto            432
        //   458: astore_2       
        //   459: new             Ljava/lang/StringBuilder;
        //   462: dup            
        //   463: ldc_w           "Exception in run(): "
        //   466: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   469: aload_2        
        //   470: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   473: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   476: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   479: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //   482: aload_2        
        //   483: invokestatic    com/crittercism/internal/dw.a:(Ljava/lang/Throwable;)V
        //   486: aload_0        
        //   487: aload_2        
        //   488: putfield        com/crittercism/internal/dg.l:Ljava/lang/Exception;
        //   491: aload_0        
        //   492: getfield        com/crittercism/internal/dg.b:Landroid/os/ConditionVariable;
        //   495: invokevirtual   android/os/ConditionVariable.open:()V
        //   498: aload_0        
        //   499: getfield        com/crittercism/internal/dg.a:Landroid/os/ConditionVariable;
        //   502: invokevirtual   android/os/ConditionVariable.open:()V
        //   505: return         
        //   506: aload_2        
        //   507: invokestatic    com/crittercism/internal/dz.a:(Ljava/io/File;)V
        //   510: goto            60
        //   513: astore_2       
        //   514: aload_0        
        //   515: getfield        com/crittercism/internal/dg.b:Landroid/os/ConditionVariable;
        //   518: invokevirtual   android/os/ConditionVariable.open:()V
        //   521: aload_0        
        //   522: getfield        com/crittercism/internal/dg.a:Landroid/os/ConditionVariable;
        //   525: invokevirtual   android/os/ConditionVariable.open:()V
        //   528: aload_2        
        //   529: athrow         
        //   530: aload_0        
        //   531: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   534: astore          4
        //   536: new             Lcom/crittercism/internal/h;
        //   539: dup            
        //   540: aload           4
        //   542: invokespecial   com/crittercism/internal/h.<init>:(Landroid/content/Context;)V
        //   545: astore_2       
        //   546: aload           4
        //   548: ldc_w           "com.crittercism.optmz.config"
        //   551: iconst_0       
        //   552: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   555: astore          4
        //   557: aload           4
        //   559: ldc_w           "interval"
        //   562: invokeinterface android/content/SharedPreferences.contains:(Ljava/lang/String;)Z
        //   567: ifeq            1186
        //   570: aload_2        
        //   571: aload           4
        //   573: ldc_w           "interval"
        //   576: bipush          10
        //   578: invokeinterface android/content/SharedPreferences.getInt:(Ljava/lang/String;I)I
        //   583: putfield        com/crittercism/internal/h.d:I
        //   586: aload           4
        //   588: ldc_w           "kill"
        //   591: invokeinterface android/content/SharedPreferences.contains:(Ljava/lang/String;)Z
        //   596: ifeq            1191
        //   599: aload_2        
        //   600: aload           4
        //   602: ldc_w           "kill"
        //   605: iconst_0       
        //   606: invokeinterface android/content/SharedPreferences.getBoolean:(Ljava/lang/String;Z)Z
        //   611: putfield        com/crittercism/internal/h.c:Z
        //   614: aload           4
        //   616: ldc_w           "persist"
        //   619: invokeinterface android/content/SharedPreferences.contains:(Ljava/lang/String;)Z
        //   624: ifeq            1196
        //   627: aload_2        
        //   628: aload           4
        //   630: ldc_w           "persist"
        //   633: iconst_0       
        //   634: invokeinterface android/content/SharedPreferences.getBoolean:(Ljava/lang/String;Z)Z
        //   639: putfield        com/crittercism/internal/h.b:Z
        //   642: aload           4
        //   644: ldc_w           "enabled"
        //   647: invokeinterface android/content/SharedPreferences.contains:(Ljava/lang/String;)Z
        //   652: ifeq            1201
        //   655: aload_2        
        //   656: aload           4
        //   658: ldc_w           "enabled"
        //   661: iconst_0       
        //   662: invokeinterface android/content/SharedPreferences.getBoolean:(Ljava/lang/String;Z)Z
        //   667: putfield        com/crittercism/internal/h.a:Z
        //   670: aload_2        
        //   671: ifnull          681
        //   674: invokestatic    com/crittercism/internal/ax.C:()Lcom/crittercism/internal/ax;
        //   677: aload_2        
        //   678: invokevirtual   com/crittercism/internal/ax.a:(Lcom/crittercism/internal/h;)V
        //   681: aload_0        
        //   682: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   685: invokeinterface com/crittercism/internal/au.A:()V
        //   690: invokestatic    com/crittercism/internal/ax.C:()Lcom/crittercism/internal/ax;
        //   693: getfield        com/crittercism/internal/ax.s:Z
        //   696: ifne            849
        //   699: aload_0        
        //   700: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   703: invokestatic    com/crittercism/internal/bf.a:(Landroid/content/Context;)Lcom/crittercism/internal/bf;
        //   706: astore_2       
        //   707: aload_0        
        //   708: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   711: invokeinterface com/crittercism/internal/au.y:()Lcom/crittercism/internal/bq;
        //   716: astore          4
        //   718: aload_0        
        //   719: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   722: invokeinterface com/crittercism/internal/au.t:()Lcom/crittercism/internal/bq;
        //   727: astore          5
        //   729: aload_0        
        //   730: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   733: invokeinterface com/crittercism/internal/au.u:()Lcom/crittercism/internal/bq;
        //   738: astore          6
        //   740: aload_0        
        //   741: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   744: invokeinterface com/crittercism/internal/au.w:()Lcom/crittercism/internal/bq;
        //   749: astore          7
        //   751: new             Ljava/net/URL;
        //   754: dup            
        //   755: new             Ljava/lang/StringBuilder;
        //   758: dup            
        //   759: invokespecial   java/lang/StringBuilder.<init>:()V
        //   762: aload_0        
        //   763: getfield        com/crittercism/internal/dg.c:Lcom/crittercism/internal/az;
        //   766: getfield        com/crittercism/internal/az.c:Lcom/crittercism/internal/bl;
        //   769: getfield        com/crittercism/internal/bl.c:Ljava/lang/String;
        //   772: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   775: ldc_w           "/api/v1/transactions"
        //   778: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   781: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   784: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   787: astore          8
        //   789: new             Lcom/crittercism/internal/bg;
        //   792: dup            
        //   793: aload_0        
        //   794: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //   797: aload_0        
        //   798: getfield        com/crittercism/internal/dg.g:Lcom/crittercism/internal/ar;
        //   801: aload           4
        //   803: aload           5
        //   805: aload           6
        //   807: aload           7
        //   809: aload           8
        //   811: invokespecial   com/crittercism/internal/bg.<init>:(Landroid/content/Context;Lcom/crittercism/internal/ar;Lcom/crittercism/internal/bq;Lcom/crittercism/internal/bq;Lcom/crittercism/internal/bq;Lcom/crittercism/internal/bq;Ljava/net/URL;)V
        //   814: astore          4
        //   816: invokestatic    com/crittercism/internal/ax.C:()Lcom/crittercism/internal/ax;
        //   819: astore          5
        //   821: aload           5
        //   823: aload           4
        //   825: putfield        com/crittercism/internal/ax.y:Lcom/crittercism/internal/bg;
        //   828: new             Lcom/crittercism/internal/dx;
        //   831: dup            
        //   832: aload           4
        //   834: ldc_w           "TXN Thread"
        //   837: invokespecial   com/crittercism/internal/dx.<init>:(Ljava/lang/Runnable;Ljava/lang/String;)V
        //   840: invokevirtual   java/lang/Thread.start:()V
        //   843: aload           5
        //   845: aload_2        
        //   846: invokevirtual   com/crittercism/internal/ax.a:(Lcom/crittercism/internal/bf;)V
        //   849: ldc_w           "generateCrashFromDump: begin"
        //   852: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //   855: invokestatic    com/crittercism/internal/ax.C:()Lcom/crittercism/internal/ax;
        //   858: astore          7
        //   860: aload           7
        //   862: getfield        com/crittercism/internal/ax.s:Z
        //   865: ifne            1040
        //   868: aload_3        
        //   869: ifnull          884
        //   872: aload_3        
        //   873: invokevirtual   java/io/File.exists:()Z
        //   876: ifeq            884
        //   879: aload_3        
        //   880: invokevirtual   java/io/File.isFile:()Z
        //   883: pop            
        //   884: aload_0        
        //   885: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   888: invokeinterface com/crittercism/internal/au.t:()Lcom/crittercism/internal/bq;
        //   893: astore_2       
        //   894: aload_0        
        //   895: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   898: invokeinterface com/crittercism/internal/au.u:()Lcom/crittercism/internal/bq;
        //   903: astore          4
        //   905: aload_0        
        //   906: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   909: invokeinterface com/crittercism/internal/au.v:()Lcom/crittercism/internal/bq;
        //   914: astore          5
        //   916: aload_0        
        //   917: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   920: invokeinterface com/crittercism/internal/au.w:()Lcom/crittercism/internal/bq;
        //   925: astore          6
        //   927: aload_0        
        //   928: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //   931: invokeinterface com/crittercism/internal/au.r:()Lcom/crittercism/internal/bq;
        //   936: astore          8
        //   938: aload_3        
        //   939: ifnull          1123
        //   942: iconst_1       
        //   943: putstatic       com/crittercism/internal/dp.a:Z
        //   946: aload           7
        //   948: getfield        com/crittercism/internal/ax.f:Landroid/os/ConditionVariable;
        //   951: invokevirtual   android/os/ConditionVariable.open:()V
        //   954: aload           8
        //   956: new             Lcom/crittercism/internal/cb;
        //   959: dup            
        //   960: aload_3        
        //   961: aload_2        
        //   962: aload           5
        //   964: aload           4
        //   966: aload           6
        //   968: invokespecial   com/crittercism/internal/cb.<init>:(Ljava/io/File;Lcom/crittercism/internal/bq;Lcom/crittercism/internal/bq;Lcom/crittercism/internal/bq;Lcom/crittercism/internal/bq;)V
        //   971: invokevirtual   com/crittercism/internal/bq.a:(Lcom/crittercism/internal/cf;)Z
        //   974: pop            
        //   975: aload_3        
        //   976: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   979: astore          7
        //   981: new             Ljava/lang/StringBuilder;
        //   984: dup            
        //   985: ldc_w           "generateCrashFromDump: deleting ndk dump: "
        //   988: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   991: aload           7
        //   993: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   996: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   999: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //  1002: aload_3        
        //  1003: invokevirtual   java/io/File.delete:()Z
        //  1006: pop            
        //  1007: aload_0        
        //  1008: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //  1011: invokeinterface com/crittercism/internal/au.x:()Lcom/crittercism/internal/bq;
        //  1016: invokevirtual   com/crittercism/internal/bq.a:()V
        //  1019: aload           5
        //  1021: invokevirtual   com/crittercism/internal/bq.a:()V
        //  1024: aload           4
        //  1026: invokevirtual   com/crittercism/internal/bq.a:()V
        //  1029: aload           6
        //  1031: invokevirtual   com/crittercism/internal/bq.a:()V
        //  1034: aload_2        
        //  1035: aload           5
        //  1037: invokevirtual   com/crittercism/internal/bq.a:(Lcom/crittercism/internal/bq;)V
        //  1040: invokestatic    com/crittercism/internal/be.f:()V
        //  1043: aload_0        
        //  1044: getfield        com/crittercism/internal/dg.b:Landroid/os/ConditionVariable;
        //  1047: invokevirtual   android/os/ConditionVariable.open:()V
        //  1050: aload_0        
        //  1051: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //  1054: invokeinterface com/crittercism/internal/au.t:()Lcom/crittercism/internal/bq;
        //  1059: getstatic       com/crittercism/internal/cd.a:Lcom/crittercism/internal/cd;
        //  1062: invokevirtual   com/crittercism/internal/bq.a:(Lcom/crittercism/internal/cf;)Z
        //  1065: pop            
        //  1066: invokestatic    com/crittercism/internal/ax.C:()Lcom/crittercism/internal/ax;
        //  1069: getfield        com/crittercism/internal/ax.s:Z
        //  1072: ifne            1105
        //  1075: aload_0        
        //  1076: getfield        com/crittercism/internal/dg.c:Lcom/crittercism/internal/az;
        //  1079: invokevirtual   com/crittercism/internal/az.isNdkCrashReportingEnabled:()Z
        //  1082: ifeq            1105
        //  1085: ldc_w           "Installing NDK Library"
        //  1088: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //  1091: aload_0        
        //  1092: getfield        com/crittercism/internal/dg.d:Landroid/content/Context;
        //  1095: aload_0        
        //  1096: getfield        com/crittercism/internal/dg.c:Lcom/crittercism/internal/az;
        //  1099: invokevirtual   com/crittercism/internal/az.b:()Ljava/lang/String;
        //  1102: invokestatic    com/crittercism/app/CrittercismNDK.installNdkLib:(Landroid/content/Context;Ljava/lang/String;)V
        //  1105: aload_0        
        //  1106: invokespecial   com/crittercism/internal/dg.e:()V
        //  1109: goto            418
        //  1112: astore_2       
        //  1113: ldc_w           "Bad transactions URL. Transactions will be disabled"
        //  1116: aload_2        
        //  1117: invokestatic    com/crittercism/internal/dw.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1120: goto            849
        //  1123: aload           7
        //  1125: getfield        com/crittercism/internal/ax.f:Landroid/os/ConditionVariable;
        //  1128: invokevirtual   android/os/ConditionVariable.open:()V
        //  1131: aload_0        
        //  1132: getfield        com/crittercism/internal/dg.e:Lcom/crittercism/internal/au;
        //  1135: invokestatic    com/crittercism/internal/be.a:(Lcom/crittercism/internal/au;)V
        //  1138: goto            1019
        //  1141: astore_2       
        //  1142: new             Ljava/lang/StringBuilder;
        //  1145: dup            
        //  1146: ldc_w           "Exception installing ndk library: "
        //  1149: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //  1152: aload_2        
        //  1153: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //  1156: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //  1159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1162: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1165: invokestatic    com/crittercism/internal/dw.d:(Ljava/lang/String;)V
        //  1168: goto            1105
        //  1171: aload_0        
        //  1172: getfield        com/crittercism/internal/dg.b:Landroid/os/ConditionVariable;
        //  1175: invokevirtual   android/os/ConditionVariable.open:()V
        //  1178: aload_0        
        //  1179: getfield        com/crittercism/internal/dg.a:Landroid/os/ConditionVariable;
        //  1182: invokevirtual   android/os/ConditionVariable.open:()V
        //  1185: return         
        //  1186: aconst_null    
        //  1187: astore_2       
        //  1188: goto            670
        //  1191: aconst_null    
        //  1192: astore_2       
        //  1193: goto            670
        //  1196: aconst_null    
        //  1197: astore_2       
        //  1198: goto            670
        //  1201: aconst_null    
        //  1202: astore_2       
        //  1203: goto            670
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      60     458    506    Ljava/lang/Exception;
        //  0      60     513    530    Any
        //  60     205    458    506    Ljava/lang/Exception;
        //  60     205    513    530    Any
        //  205    233    458    506    Ljava/lang/Exception;
        //  205    233    513    530    Any
        //  237    249    458    506    Ljava/lang/Exception;
        //  237    249    513    530    Any
        //  249    402    458    506    Ljava/lang/Exception;
        //  249    402    513    530    Any
        //  406    411    458    506    Ljava/lang/Exception;
        //  406    411    513    530    Any
        //  411    418    458    506    Ljava/lang/Exception;
        //  411    418    513    530    Any
        //  418    432    458    506    Ljava/lang/Exception;
        //  418    432    513    530    Any
        //  432    455    458    506    Ljava/lang/Exception;
        //  432    455    513    530    Any
        //  459    491    513    530    Any
        //  506    510    458    506    Ljava/lang/Exception;
        //  506    510    513    530    Any
        //  530    670    458    506    Ljava/lang/Exception;
        //  530    670    513    530    Any
        //  674    681    458    506    Ljava/lang/Exception;
        //  674    681    513    530    Any
        //  681    751    458    506    Ljava/lang/Exception;
        //  681    751    513    530    Any
        //  751    789    1112   1123   Ljava/net/MalformedURLException;
        //  751    789    458    506    Ljava/lang/Exception;
        //  751    789    513    530    Any
        //  789    849    458    506    Ljava/lang/Exception;
        //  789    849    513    530    Any
        //  849    868    458    506    Ljava/lang/Exception;
        //  849    868    513    530    Any
        //  872    884    458    506    Ljava/lang/Exception;
        //  872    884    513    530    Any
        //  884    938    458    506    Ljava/lang/Exception;
        //  884    938    513    530    Any
        //  942    1019   458    506    Ljava/lang/Exception;
        //  942    1019   513    530    Any
        //  1019   1040   458    506    Ljava/lang/Exception;
        //  1019   1040   513    530    Any
        //  1040   1091   458    506    Ljava/lang/Exception;
        //  1040   1091   513    530    Any
        //  1091   1105   1141   1171   Ljava/lang/Throwable;
        //  1091   1105   458    506    Ljava/lang/Exception;
        //  1091   1105   513    530    Any
        //  1105   1109   458    506    Ljava/lang/Exception;
        //  1105   1109   513    530    Any
        //  1113   1120   458    506    Ljava/lang/Exception;
        //  1113   1120   513    530    Any
        //  1123   1138   458    506    Ljava/lang/Exception;
        //  1123   1138   513    530    Any
        //  1142   1168   458    506    Ljava/lang/Exception;
        //  1142   1168   513    530    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_1105:
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
    
    public final boolean a(final Runnable runnable) {
        synchronized (this) {
            boolean b;
            if (!this.c()) {
                this.h.add(runnable);
                b = true;
            }
            else {
                b = false;
            }
            return b;
        }
    }
}
