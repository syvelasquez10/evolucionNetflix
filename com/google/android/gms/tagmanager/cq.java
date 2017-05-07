// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.InputStream;
import android.content.res.Resources$NotFoundException;
import java.io.OutputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.google.android.gms.internal.pm;
import java.io.FileOutputStream;
import com.google.android.gms.internal.pl;
import com.google.android.gms.internal.c$f;
import org.json.JSONException;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Executors;
import android.content.Context;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.internal.ok$a;

class cq implements o$f
{
    private final String anR;
    private bg<ok$a> aqi;
    private final ExecutorService aqp;
    private final Context mContext;
    
    cq(final Context mContext, final String anR) {
        this.mContext = mContext;
        this.anR = anR;
        this.aqp = Executors.newSingleThreadExecutor();
    }
    
    private cr$c a(final ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return ba.cD(byteArrayOutputStream.toString("UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {
            bh.S("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        }
        catch (JSONException ex2) {
            bh.W("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }
    
    private void d(final ok$a ok$a) {
        if (ok$a.gs == null && ok$a.ash == null) {
            throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
        }
    }
    
    private cr$c k(final byte[] array) {
        try {
            final cr$c b = cr.b(c$f.a(array));
            if (b != null) {
                bh.V("The container was successfully loaded from the resource (using binary file)");
            }
            return b;
        }
        catch (pl pl) {
            bh.T("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        }
        catch (cr$g cr$g) {
            bh.W("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }
    
    @Override
    public void a(final bg<ok$a> aqi) {
        this.aqi = aqi;
    }
    
    @Override
    public void b(final ok$a ok$a) {
        this.aqp.execute(new cq$2(this, ok$a));
    }
    
    boolean c(final ok$a ok$a) {
        final File oq = this.oQ();
        FileOutputStream fileOutputStream;
        try {
            final FileOutputStream fileOutputStream2;
            fileOutputStream = (fileOutputStream2 = new FileOutputStream(oq));
            final ok$a ok$a2 = ok$a;
            final byte[] array = pm.f(ok$a2);
            fileOutputStream2.write(array);
            final FileOutputStream fileOutputStream3 = fileOutputStream;
            fileOutputStream3.close();
            return true;
        }
        catch (FileNotFoundException ex) {
            bh.T("Error opening resource file for writing");
            return false;
        }
        try {
            final FileOutputStream fileOutputStream2 = fileOutputStream;
            final ok$a ok$a2 = ok$a;
            final byte[] array = pm.f(ok$a2);
            fileOutputStream2.write(array);
            try {
                final FileOutputStream fileOutputStream3 = fileOutputStream;
                fileOutputStream3.close();
                return true;
            }
            catch (IOException ex2) {
                bh.W("error closing stream for writing resource to disk");
                return true;
            }
        }
        catch (IOException ex3) {
            bh.W("Error writing resource to disk. Removing resource from disk.");
            oq.delete();
            try {
                fileOutputStream.close();
                return false;
            }
            catch (IOException ex4) {
                bh.W("error closing stream for writing resource to disk");
                return false;
            }
        }
        finally {
            try {
                fileOutputStream.close();
            }
            catch (IOException ex5) {
                bh.W("error closing stream for writing resource to disk");
            }
        }
    }
    
    @Override
    public cr$c fe(final int n) {
        Label_0113: {
            InputStream openRawResource;
            try {
                openRawResource = this.mContext.getResources().openRawResource(n);
                bh.V("Attempting to load a container from the resource ID " + n + " (" + this.mContext.getResources().getResourceName(n) + ")");
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final InputStream inputStream = openRawResource;
                final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                cr.b(inputStream, byteArrayOutputStream2);
                final cq cq = this;
                final ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream;
                final cr$c cr$c = cq.a(byteArrayOutputStream3);
                final cr$c cr$c3;
                final cr$c cr$c2 = cr$c3 = cr$c;
                if (cr$c3 != null) {
                    final String s = "The container was successfully loaded from the resource (using JSON file format)";
                    bh.V(s);
                    return cr$c2;
                }
                break Label_0113;
            }
            catch (Resources$NotFoundException ex) {
                bh.W("Failed to load the container. No default container resource found with the resource ID " + n);
                return null;
            }
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final InputStream inputStream = openRawResource;
                final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                cr.b(inputStream, byteArrayOutputStream2);
                final cq cq = this;
                final ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream;
                final cr$c cr$c = cq.a(byteArrayOutputStream3);
                final cr$c cr$c3;
                final cr$c cr$c2 = cr$c3 = cr$c;
                if (cr$c3 != null) {
                    final String s = "The container was successfully loaded from the resource (using JSON file format)";
                    bh.V(s);
                    return cr$c2;
                }
                return this.k(byteArrayOutputStream.toByteArray());
            }
            catch (IOException ex2) {
                bh.W("Error reading the default container with resource ID " + n + " (" + this.mContext.getResources().getResourceName(n) + ")");
                return null;
            }
        }
    }
    
    void oP() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/tagmanager/cq.aqi:Lcom/google/android/gms/tagmanager/bg;
        //     4: ifnonnull       17
        //     7: new             Ljava/lang/IllegalStateException;
        //    10: dup            
        //    11: ldc             "Callback must be set before execute"
        //    13: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    16: athrow         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/tagmanager/cq.aqi:Lcom/google/android/gms/tagmanager/bg;
        //    21: invokeinterface com/google/android/gms/tagmanager/bg.nZ:()V
        //    26: ldc             "Attempting to load resource from disk"
        //    28: invokestatic    com/google/android/gms/tagmanager/bh.V:(Ljava/lang/String;)V
        //    31: invokestatic    com/google/android/gms/tagmanager/ce.oH:()Lcom/google/android/gms/tagmanager/ce;
        //    34: invokevirtual   com/google/android/gms/tagmanager/ce.oI:()Lcom/google/android/gms/tagmanager/ce$a;
        //    37: getstatic       com/google/android/gms/tagmanager/ce$a.apX:Lcom/google/android/gms/tagmanager/ce$a;
        //    40: if_acmpeq       55
        //    43: invokestatic    com/google/android/gms/tagmanager/ce.oH:()Lcom/google/android/gms/tagmanager/ce;
        //    46: invokevirtual   com/google/android/gms/tagmanager/ce.oI:()Lcom/google/android/gms/tagmanager/ce$a;
        //    49: getstatic       com/google/android/gms/tagmanager/ce$a.apY:Lcom/google/android/gms/tagmanager/ce$a;
        //    52: if_acmpne       84
        //    55: aload_0        
        //    56: getfield        com/google/android/gms/tagmanager/cq.anR:Ljava/lang/String;
        //    59: invokestatic    com/google/android/gms/tagmanager/ce.oH:()Lcom/google/android/gms/tagmanager/ce;
        //    62: invokevirtual   com/google/android/gms/tagmanager/ce.getContainerId:()Ljava/lang/String;
        //    65: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    68: ifeq            84
        //    71: aload_0        
        //    72: getfield        com/google/android/gms/tagmanager/cq.aqi:Lcom/google/android/gms/tagmanager/bg;
        //    75: getstatic       com/google/android/gms/tagmanager/bg$a.apB:Lcom/google/android/gms/tagmanager/bg$a;
        //    78: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //    83: return         
        //    84: new             Ljava/io/FileInputStream;
        //    87: dup            
        //    88: aload_0        
        //    89: invokevirtual   com/google/android/gms/tagmanager/cq.oQ:()Ljava/io/File;
        //    92: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    95: astore_1       
        //    96: new             Ljava/io/ByteArrayOutputStream;
        //    99: dup            
        //   100: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   103: astore_2       
        //   104: aload_1        
        //   105: aload_2        
        //   106: invokestatic    com/google/android/gms/tagmanager/cr.b:(Ljava/io/InputStream;Ljava/io/OutputStream;)V
        //   109: aload_2        
        //   110: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   113: invokestatic    com/google/android/gms/internal/ok$a.l:([B)Lcom/google/android/gms/internal/ok$a;
        //   116: astore_2       
        //   117: aload_0        
        //   118: aload_2        
        //   119: invokespecial   com/google/android/gms/tagmanager/cq.d:(Lcom/google/android/gms/internal/ok$a;)V
        //   122: aload_0        
        //   123: getfield        com/google/android/gms/tagmanager/cq.aqi:Lcom/google/android/gms/tagmanager/bg;
        //   126: aload_2        
        //   127: invokeinterface com/google/android/gms/tagmanager/bg.l:(Ljava/lang/Object;)V
        //   132: aload_1        
        //   133: invokevirtual   java/io/FileInputStream.close:()V
        //   136: ldc_w           "The Disk resource was successfully read."
        //   139: invokestatic    com/google/android/gms/tagmanager/bh.V:(Ljava/lang/String;)V
        //   142: return         
        //   143: astore_1       
        //   144: ldc_w           "Failed to find the resource in the disk"
        //   147: invokestatic    com/google/android/gms/tagmanager/bh.S:(Ljava/lang/String;)V
        //   150: aload_0        
        //   151: getfield        com/google/android/gms/tagmanager/cq.aqi:Lcom/google/android/gms/tagmanager/bg;
        //   154: getstatic       com/google/android/gms/tagmanager/bg$a.apB:Lcom/google/android/gms/tagmanager/bg$a;
        //   157: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //   162: return         
        //   163: astore_1       
        //   164: ldc_w           "Error closing stream for reading resource from disk"
        //   167: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   170: goto            136
        //   173: astore_2       
        //   174: aload_0        
        //   175: getfield        com/google/android/gms/tagmanager/cq.aqi:Lcom/google/android/gms/tagmanager/bg;
        //   178: getstatic       com/google/android/gms/tagmanager/bg$a.apC:Lcom/google/android/gms/tagmanager/bg$a;
        //   181: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //   186: ldc_w           "Failed to read the resource from disk"
        //   189: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   192: aload_1        
        //   193: invokevirtual   java/io/FileInputStream.close:()V
        //   196: goto            136
        //   199: astore_1       
        //   200: ldc_w           "Error closing stream for reading resource from disk"
        //   203: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   206: goto            136
        //   209: astore_2       
        //   210: aload_0        
        //   211: getfield        com/google/android/gms/tagmanager/cq.aqi:Lcom/google/android/gms/tagmanager/bg;
        //   214: getstatic       com/google/android/gms/tagmanager/bg$a.apC:Lcom/google/android/gms/tagmanager/bg$a;
        //   217: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //   222: ldc_w           "Failed to read the resource from disk. The resource is inconsistent"
        //   225: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   228: aload_1        
        //   229: invokevirtual   java/io/FileInputStream.close:()V
        //   232: goto            136
        //   235: astore_1       
        //   236: ldc_w           "Error closing stream for reading resource from disk"
        //   239: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   242: goto            136
        //   245: astore_2       
        //   246: aload_1        
        //   247: invokevirtual   java/io/FileInputStream.close:()V
        //   250: aload_2        
        //   251: athrow         
        //   252: astore_1       
        //   253: ldc_w           "Error closing stream for reading resource from disk"
        //   256: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   259: goto            250
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  84     96     143    163    Ljava/io/FileNotFoundException;
        //  96     132    173    209    Ljava/io/IOException;
        //  96     132    209    245    Ljava/lang/IllegalArgumentException;
        //  96     132    245    262    Any
        //  132    136    163    173    Ljava/io/IOException;
        //  174    192    245    262    Any
        //  192    196    199    209    Ljava/io/IOException;
        //  210    228    245    262    Any
        //  228    232    235    245    Ljava/io/IOException;
        //  246    250    252    262    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0136:
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
    
    File oQ() {
        return new File(this.mContext.getDir("google_tagmanager", 0), "resource_" + this.anR);
    }
    
    @Override
    public void oa() {
        this.aqp.execute(new cq$1(this));
    }
    
    @Override
    public void release() {
        synchronized (this) {
            this.aqp.shutdown();
        }
    }
}
