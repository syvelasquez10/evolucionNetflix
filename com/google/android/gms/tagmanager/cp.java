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
import com.google.android.gms.internal.kt;
import java.io.FileOutputStream;
import com.google.android.gms.internal.ks;
import com.google.android.gms.internal.c;
import org.json.JSONException;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Executors;
import android.content.Context;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.internal.it;

class cp implements f
{
    private final String WJ;
    private bg<it.a> Zf;
    private final ExecutorService Zm;
    private final Context mContext;
    
    cp(final Context mContext, final String wj) {
        this.mContext = mContext;
        this.WJ = wj;
        this.Zm = Executors.newSingleThreadExecutor();
    }
    
    private cq.c a(final ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return ba.bG(byteArrayOutputStream.toString("UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {
            bh.v("Tried to convert binary resource to string for JSON parsing; not UTF-8 format");
            return null;
        }
        catch (JSONException ex2) {
            bh.z("Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }
    
    private cq.c k(final byte[] array) {
        try {
            return cq.b(com.google.android.gms.internal.c.f.a(array));
        }
        catch (ks ks) {
            bh.z("Resource doesn't contain a binary container");
            return null;
        }
        catch (cq.g g) {
            bh.z("Resource doesn't contain a binary container");
            return null;
        }
    }
    
    @Override
    public void a(final bg<it.a> zf) {
        this.Zf = zf;
    }
    
    @Override
    public void b(final it.a a) {
        this.Zm.execute(new Runnable() {
            @Override
            public void run() {
                cp.this.c(a);
            }
        });
    }
    
    boolean c(final it.a a) {
        final File lc = this.lc();
        FileOutputStream fileOutputStream;
        try {
            final FileOutputStream fileOutputStream2;
            fileOutputStream = (fileOutputStream2 = new FileOutputStream(lc));
            final it.a a2 = a;
            final byte[] array = kt.d(a2);
            fileOutputStream2.write(array);
            final FileOutputStream fileOutputStream3 = fileOutputStream;
            fileOutputStream3.close();
            return true;
        }
        catch (FileNotFoundException ex) {
            bh.w("Error opening resource file for writing");
            return false;
        }
        try {
            final FileOutputStream fileOutputStream2 = fileOutputStream;
            final it.a a2 = a;
            final byte[] array = kt.d(a2);
            fileOutputStream2.write(array);
            try {
                final FileOutputStream fileOutputStream3 = fileOutputStream;
                fileOutputStream3.close();
                return true;
            }
            catch (IOException ex2) {
                bh.z("error closing stream for writing resource to disk");
                return true;
            }
        }
        catch (IOException ex3) {
            bh.z("Error writing resource to disk. Removing resource from disk.");
            lc.delete();
            try {
                fileOutputStream.close();
                return false;
            }
            catch (IOException ex4) {
                bh.z("error closing stream for writing resource to disk");
                return false;
            }
        }
        finally {
            try {
                fileOutputStream.close();
            }
            catch (IOException ex5) {
                bh.z("error closing stream for writing resource to disk");
            }
        }
    }
    
    @Override
    public cq.c ca(final int n) {
        bh.y("Atttempting to load container from resource ID " + n);
        try {
            final InputStream openRawResource = this.mContext.getResources().openRawResource(n);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            cq.b(openRawResource, byteArrayOutputStream);
            final cq.c a = this.a(byteArrayOutputStream);
            if (a != null) {
                return a;
            }
            return this.k(byteArrayOutputStream.toByteArray());
        }
        catch (IOException ex) {
            bh.z("Error reading default container resource with ID " + n);
            return null;
        }
        catch (Resources$NotFoundException ex2) {
            bh.z("No default container resource found.");
            return null;
        }
    }
    
    @Override
    public void km() {
        this.Zm.execute(new Runnable() {
            @Override
            public void run() {
                cp.this.lb();
            }
        });
    }
    
    void lb() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/tagmanager/cp.Zf:Lcom/google/android/gms/tagmanager/bg;
        //     4: ifnonnull       17
        //     7: new             Ljava/lang/IllegalStateException;
        //    10: dup            
        //    11: ldc             "callback must be set before execute"
        //    13: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    16: athrow         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/tagmanager/cp.Zf:Lcom/google/android/gms/tagmanager/bg;
        //    21: invokeinterface com/google/android/gms/tagmanager/bg.kl:()V
        //    26: ldc             "Start loading resource from disk ..."
        //    28: invokestatic    com/google/android/gms/tagmanager/bh.y:(Ljava/lang/String;)V
        //    31: invokestatic    com/google/android/gms/tagmanager/cd.kT:()Lcom/google/android/gms/tagmanager/cd;
        //    34: invokevirtual   com/google/android/gms/tagmanager/cd.kU:()Lcom/google/android/gms/tagmanager/cd$a;
        //    37: getstatic       com/google/android/gms/tagmanager/cd$a.YU:Lcom/google/android/gms/tagmanager/cd$a;
        //    40: if_acmpeq       55
        //    43: invokestatic    com/google/android/gms/tagmanager/cd.kT:()Lcom/google/android/gms/tagmanager/cd;
        //    46: invokevirtual   com/google/android/gms/tagmanager/cd.kU:()Lcom/google/android/gms/tagmanager/cd$a;
        //    49: getstatic       com/google/android/gms/tagmanager/cd$a.YV:Lcom/google/android/gms/tagmanager/cd$a;
        //    52: if_acmpne       84
        //    55: aload_0        
        //    56: getfield        com/google/android/gms/tagmanager/cp.WJ:Ljava/lang/String;
        //    59: invokestatic    com/google/android/gms/tagmanager/cd.kT:()Lcom/google/android/gms/tagmanager/cd;
        //    62: invokevirtual   com/google/android/gms/tagmanager/cd.getContainerId:()Ljava/lang/String;
        //    65: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    68: ifeq            84
        //    71: aload_0        
        //    72: getfield        com/google/android/gms/tagmanager/cp.Zf:Lcom/google/android/gms/tagmanager/bg;
        //    75: getstatic       com/google/android/gms/tagmanager/bg$a.Yy:Lcom/google/android/gms/tagmanager/bg$a;
        //    78: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //    83: return         
        //    84: new             Ljava/io/FileInputStream;
        //    87: dup            
        //    88: aload_0        
        //    89: invokevirtual   com/google/android/gms/tagmanager/cp.lc:()Ljava/io/File;
        //    92: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    95: astore_1       
        //    96: new             Ljava/io/ByteArrayOutputStream;
        //    99: dup            
        //   100: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   103: astore_2       
        //   104: aload_1        
        //   105: aload_2        
        //   106: invokestatic    com/google/android/gms/tagmanager/cq.b:(Ljava/io/InputStream;Ljava/io/OutputStream;)V
        //   109: aload_0        
        //   110: getfield        com/google/android/gms/tagmanager/cp.Zf:Lcom/google/android/gms/tagmanager/bg;
        //   113: aload_2        
        //   114: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   117: invokestatic    com/google/android/gms/internal/it$a.l:([B)Lcom/google/android/gms/internal/it$a;
        //   120: invokeinterface com/google/android/gms/tagmanager/bg.i:(Ljava/lang/Object;)V
        //   125: aload_1        
        //   126: invokevirtual   java/io/FileInputStream.close:()V
        //   129: ldc_w           "Load resource from disk finished."
        //   132: invokestatic    com/google/android/gms/tagmanager/bh.y:(Ljava/lang/String;)V
        //   135: return         
        //   136: astore_1       
        //   137: ldc_w           "resource not on disk"
        //   140: invokestatic    com/google/android/gms/tagmanager/bh.v:(Ljava/lang/String;)V
        //   143: aload_0        
        //   144: getfield        com/google/android/gms/tagmanager/cp.Zf:Lcom/google/android/gms/tagmanager/bg;
        //   147: getstatic       com/google/android/gms/tagmanager/bg$a.Yy:Lcom/google/android/gms/tagmanager/bg$a;
        //   150: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //   155: return         
        //   156: astore_1       
        //   157: ldc_w           "error closing stream for reading resource from disk"
        //   160: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //   163: goto            129
        //   166: astore_2       
        //   167: ldc_w           "error reading resource from disk"
        //   170: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //   173: aload_0        
        //   174: getfield        com/google/android/gms/tagmanager/cp.Zf:Lcom/google/android/gms/tagmanager/bg;
        //   177: getstatic       com/google/android/gms/tagmanager/bg$a.Yz:Lcom/google/android/gms/tagmanager/bg$a;
        //   180: invokeinterface com/google/android/gms/tagmanager/bg.a:(Lcom/google/android/gms/tagmanager/bg$a;)V
        //   185: aload_1        
        //   186: invokevirtual   java/io/FileInputStream.close:()V
        //   189: goto            129
        //   192: astore_1       
        //   193: ldc_w           "error closing stream for reading resource from disk"
        //   196: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //   199: goto            129
        //   202: astore_2       
        //   203: aload_1        
        //   204: invokevirtual   java/io/FileInputStream.close:()V
        //   207: aload_2        
        //   208: athrow         
        //   209: astore_1       
        //   210: ldc_w           "error closing stream for reading resource from disk"
        //   213: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //   216: goto            207
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  84     96     136    156    Ljava/io/FileNotFoundException;
        //  96     125    166    202    Ljava/io/IOException;
        //  96     125    202    219    Any
        //  125    129    156    166    Ljava/io/IOException;
        //  167    185    202    219    Any
        //  185    189    192    202    Ljava/io/IOException;
        //  203    207    209    219    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0129:
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
    
    File lc() {
        return new File(this.mContext.getDir("google_tagmanager", 0), "resource_" + this.WJ);
    }
    
    @Override
    public void release() {
        synchronized (this) {
            this.Zm.shutdown();
        }
    }
}
