// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import java.net.URLConnection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.io.IOException;
import com.google.android.gms.common.internal.zzx;
import java.net.URLEncoder;
import java.net.URL;
import android.os.Build;
import java.util.Locale;
import android.os.Build$VERSION;

class zzah extends zzd
{
    private static final byte[] zzOs;
    private final String zzHj;
    private final zzaj zzOr;
    
    static {
        zzOs = "\n".getBytes();
    }
    
    zzah(final zzf zzf) {
        super(zzf);
        this.zzHj = zza("GoogleAnalytics", zze.VERSION, Build$VERSION.RELEASE, zzam.zza(Locale.getDefault()), Build.MODEL, Build.ID);
        this.zzOr = new zzaj(zzf.zzid());
    }
    
    private int zza(final URL p0, final byte[] p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          6
        //     3: aconst_null    
        //     4: astore          8
        //     6: aconst_null    
        //     7: astore          9
        //     9: aconst_null    
        //    10: astore          7
        //    12: aload_1        
        //    13: invokestatic    com/google/android/gms/common/internal/zzx.zzv:(Ljava/lang/Object;)Ljava/lang/Object;
        //    16: pop            
        //    17: aload_2        
        //    18: invokestatic    com/google/android/gms/common/internal/zzx.zzv:(Ljava/lang/Object;)Ljava/lang/Object;
        //    21: pop            
        //    22: aload_0        
        //    23: ldc             "POST bytes, url"
        //    25: aload_2        
        //    26: arraylength    
        //    27: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    30: aload_1        
        //    31: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //    34: aload_0        
        //    35: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzin:()Z
        //    38: ifeq            55
        //    41: aload_0        
        //    42: ldc             "Post payload\n"
        //    44: new             Ljava/lang/String;
        //    47: dup            
        //    48: aload_2        
        //    49: invokespecial   java/lang/String.<init>:([B)V
        //    52: invokevirtual   com/google/android/gms/analytics/internal/zzah.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //    55: aload_0        
        //    56: aload_1        
        //    57: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzc:(Ljava/net/URL;)Ljava/net/HttpURLConnection;
        //    60: astore_1       
        //    61: aload_1        
        //    62: astore          6
        //    64: aload           8
        //    66: astore_1       
        //    67: aload           6
        //    69: astore          5
        //    71: aload           9
        //    73: astore          7
        //    75: aload           6
        //    77: iconst_1       
        //    78: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //    81: aload           8
        //    83: astore_1       
        //    84: aload           6
        //    86: astore          5
        //    88: aload           9
        //    90: astore          7
        //    92: aload           6
        //    94: aload_2        
        //    95: arraylength    
        //    96: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //    99: aload           8
        //   101: astore_1       
        //   102: aload           6
        //   104: astore          5
        //   106: aload           9
        //   108: astore          7
        //   110: aload           6
        //   112: invokevirtual   java/net/HttpURLConnection.connect:()V
        //   115: aload           8
        //   117: astore_1       
        //   118: aload           6
        //   120: astore          5
        //   122: aload           9
        //   124: astore          7
        //   126: aload           6
        //   128: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   131: astore          8
        //   133: aload           8
        //   135: astore_1       
        //   136: aload           6
        //   138: astore          5
        //   140: aload           8
        //   142: astore          7
        //   144: aload           8
        //   146: aload_2        
        //   147: invokevirtual   java/io/OutputStream.write:([B)V
        //   150: aload           8
        //   152: astore_1       
        //   153: aload           6
        //   155: astore          5
        //   157: aload           8
        //   159: astore          7
        //   161: aload_0        
        //   162: aload           6
        //   164: invokespecial   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/net/HttpURLConnection;)V
        //   167: aload           8
        //   169: astore_1       
        //   170: aload           6
        //   172: astore          5
        //   174: aload           8
        //   176: astore          7
        //   178: aload           6
        //   180: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   183: istore          4
        //   185: iload           4
        //   187: sipush          200
        //   190: if_icmpne       211
        //   193: aload           8
        //   195: astore_1       
        //   196: aload           6
        //   198: astore          5
        //   200: aload           8
        //   202: astore          7
        //   204: aload_0        
        //   205: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzhz:()Lcom/google/android/gms/analytics/internal/zzb;
        //   208: invokevirtual   com/google/android/gms/analytics/internal/zzb.zzhZ:()V
        //   211: aload           8
        //   213: astore_1       
        //   214: aload           6
        //   216: astore          5
        //   218: aload           8
        //   220: astore          7
        //   222: aload_0        
        //   223: ldc             "POST status"
        //   225: iload           4
        //   227: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   230: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/lang/String;Ljava/lang/Object;)V
        //   233: aload           8
        //   235: ifnull          243
        //   238: aload           8
        //   240: invokevirtual   java/io/OutputStream.close:()V
        //   243: iload           4
        //   245: istore_3       
        //   246: aload           6
        //   248: ifnull          259
        //   251: aload           6
        //   253: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   256: iload           4
        //   258: istore_3       
        //   259: iload_3        
        //   260: ireturn        
        //   261: astore_1       
        //   262: aload_0        
        //   263: ldc             "Error closing http post connection output stream"
        //   265: aload_1        
        //   266: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   269: goto            243
        //   272: astore_2       
        //   273: aconst_null    
        //   274: astore          6
        //   276: aload           7
        //   278: astore_1       
        //   279: aload           6
        //   281: astore          5
        //   283: aload_0        
        //   284: ldc             "Network POST connection error"
        //   286: aload_2        
        //   287: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzd:(Ljava/lang/String;Ljava/lang/Object;)V
        //   290: iconst_0       
        //   291: istore_3       
        //   292: aload           7
        //   294: ifnull          302
        //   297: aload           7
        //   299: invokevirtual   java/io/OutputStream.close:()V
        //   302: aload           6
        //   304: ifnull          259
        //   307: aload           6
        //   309: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   312: iconst_0       
        //   313: ireturn        
        //   314: astore_1       
        //   315: aload_0        
        //   316: ldc             "Error closing http post connection output stream"
        //   318: aload_1        
        //   319: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   322: goto            302
        //   325: astore_2       
        //   326: aconst_null    
        //   327: astore          5
        //   329: aload           6
        //   331: astore_1       
        //   332: aload_1        
        //   333: ifnull          340
        //   336: aload_1        
        //   337: invokevirtual   java/io/OutputStream.close:()V
        //   340: aload           5
        //   342: ifnull          350
        //   345: aload           5
        //   347: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   350: aload_2        
        //   351: athrow         
        //   352: astore_1       
        //   353: aload_0        
        //   354: ldc             "Error closing http post connection output stream"
        //   356: aload_1        
        //   357: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   360: goto            340
        //   363: astore_2       
        //   364: goto            332
        //   367: astore_2       
        //   368: goto            276
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  55     61     272    276    Ljava/io/IOException;
        //  55     61     325    332    Any
        //  75     81     367    371    Ljava/io/IOException;
        //  75     81     363    367    Any
        //  92     99     367    371    Ljava/io/IOException;
        //  92     99     363    367    Any
        //  110    115    367    371    Ljava/io/IOException;
        //  110    115    363    367    Any
        //  126    133    367    371    Ljava/io/IOException;
        //  126    133    363    367    Any
        //  144    150    367    371    Ljava/io/IOException;
        //  144    150    363    367    Any
        //  161    167    367    371    Ljava/io/IOException;
        //  161    167    363    367    Any
        //  178    185    367    371    Ljava/io/IOException;
        //  178    185    363    367    Any
        //  204    211    367    371    Ljava/io/IOException;
        //  204    211    363    367    Any
        //  222    233    367    371    Ljava/io/IOException;
        //  222    233    363    367    Any
        //  238    243    261    272    Ljava/io/IOException;
        //  283    290    363    367    Any
        //  297    302    314    325    Ljava/io/IOException;
        //  336    340    352    363    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0211:
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
    
    private static String zza(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", s, s2, s3, s4, s5, s6);
    }
    
    private void zza(final StringBuilder sb, final String s, final String s2) {
        if (sb.length() != 0) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(s, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(s2, "UTF-8"));
    }
    
    private int zzb(final URL url) {
        zzx.zzv(url);
        this.zzb("GET request", url);
        HttpURLConnection httpURLConnection = null;
        HttpURLConnection zzc = null;
        try {
            final HttpURLConnection httpURLConnection2 = httpURLConnection = (zzc = this.zzc(url));
            httpURLConnection2.connect();
            zzc = httpURLConnection2;
            httpURLConnection = httpURLConnection2;
            this.zzb(httpURLConnection2);
            zzc = httpURLConnection2;
            httpURLConnection = httpURLConnection2;
            final int responseCode = httpURLConnection2.getResponseCode();
            if (responseCode == 200) {
                zzc = httpURLConnection2;
                httpURLConnection = httpURLConnection2;
                this.zzhz().zzhZ();
            }
            zzc = httpURLConnection2;
            httpURLConnection = httpURLConnection2;
            this.zzb("GET status", responseCode);
            int n = responseCode;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
                n = responseCode;
            }
            return n;
        }
        catch (IOException ex) {
            httpURLConnection = zzc;
            this.zzd("Network GET connection error", ex);
            final int n = 0;
            return 0;
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
    
    private int zzb(final URL p0, final byte[] p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          6
        //     3: aconst_null    
        //     4: astore          5
        //     6: aload_1        
        //     7: invokestatic    com/google/android/gms/common/internal/zzx.zzv:(Ljava/lang/Object;)Ljava/lang/Object;
        //    10: pop            
        //    11: aload_2        
        //    12: invokestatic    com/google/android/gms/common/internal/zzx.zzv:(Ljava/lang/Object;)Ljava/lang/Object;
        //    15: pop            
        //    16: aload_2        
        //    17: invokestatic    com/google/android/gms/analytics/internal/zzah.zzg:([B)[B
        //    20: astore          7
        //    22: aload_0        
        //    23: ldc             "POST compressed size, ratio %, url"
        //    25: aload           7
        //    27: arraylength    
        //    28: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    31: ldc2_w          100
        //    34: aload           7
        //    36: arraylength    
        //    37: i2l            
        //    38: lmul           
        //    39: aload_2        
        //    40: arraylength    
        //    41: i2l            
        //    42: ldiv           
        //    43: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    46: aload_1        
        //    47: invokevirtual   com/google/android/gms/analytics/internal/zzah.zza:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
        //    50: aload           7
        //    52: arraylength    
        //    53: aload_2        
        //    54: arraylength    
        //    55: if_icmple       75
        //    58: aload_0        
        //    59: ldc             "Compressed payload is larger then uncompressed. compressed, uncompressed"
        //    61: aload           7
        //    63: arraylength    
        //    64: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    67: aload_2        
        //    68: arraylength    
        //    69: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    72: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzc:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //    75: aload_0        
        //    76: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzin:()Z
        //    79: ifeq            114
        //    82: aload_0        
        //    83: ldc             "Post payload"
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: ldc             "\n"
        //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    97: new             Ljava/lang/String;
        //   100: dup            
        //   101: aload_2        
        //   102: invokespecial   java/lang/String.<init>:([B)V
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   111: invokevirtual   com/google/android/gms/analytics/internal/zzah.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //   114: aload_0        
        //   115: aload_1        
        //   116: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzc:(Ljava/net/URL;)Ljava/net/HttpURLConnection;
        //   119: astore_1       
        //   120: aload_1        
        //   121: iconst_1       
        //   122: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   125: aload_1        
        //   126: ldc             "Content-Encoding"
        //   128: ldc             "gzip"
        //   130: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   133: aload_1        
        //   134: aload           7
        //   136: arraylength    
        //   137: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   140: aload_1        
        //   141: invokevirtual   java/net/HttpURLConnection.connect:()V
        //   144: aload_1        
        //   145: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   148: astore_2       
        //   149: aload_2        
        //   150: aload           7
        //   152: invokevirtual   java/io/OutputStream.write:([B)V
        //   155: aload_2        
        //   156: invokevirtual   java/io/OutputStream.close:()V
        //   159: aload_0        
        //   160: aload_1        
        //   161: invokespecial   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/net/HttpURLConnection;)V
        //   164: aload_1        
        //   165: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   168: istore          4
        //   170: iload           4
        //   172: sipush          200
        //   175: if_icmpne       185
        //   178: aload_0        
        //   179: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzhz:()Lcom/google/android/gms/analytics/internal/zzb;
        //   182: invokevirtual   com/google/android/gms/analytics/internal/zzb.zzhZ:()V
        //   185: aload_0        
        //   186: ldc             "POST status"
        //   188: iload           4
        //   190: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   193: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/lang/String;Ljava/lang/Object;)V
        //   196: iconst_0       
        //   197: ifeq            208
        //   200: new             Ljava/lang/NullPointerException;
        //   203: dup            
        //   204: invokespecial   java/lang/NullPointerException.<init>:()V
        //   207: athrow         
        //   208: iload           4
        //   210: istore_3       
        //   211: aload_1        
        //   212: ifnull          222
        //   215: aload_1        
        //   216: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   219: iload           4
        //   221: istore_3       
        //   222: iload_3        
        //   223: ireturn        
        //   224: astore_2       
        //   225: aload_0        
        //   226: ldc             "Error closing http compressed post connection output stream"
        //   228: aload_2        
        //   229: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   232: goto            208
        //   235: astore_2       
        //   236: aconst_null    
        //   237: astore_1       
        //   238: aload_0        
        //   239: ldc             "Network compressed POST connection error"
        //   241: aload_2        
        //   242: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzd:(Ljava/lang/String;Ljava/lang/Object;)V
        //   245: iconst_0       
        //   246: istore_3       
        //   247: aload           5
        //   249: ifnull          257
        //   252: aload           5
        //   254: invokevirtual   java/io/OutputStream.close:()V
        //   257: aload_1        
        //   258: ifnull          222
        //   261: aload_1        
        //   262: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   265: iconst_0       
        //   266: ireturn        
        //   267: astore_2       
        //   268: aload_0        
        //   269: ldc             "Error closing http compressed post connection output stream"
        //   271: aload_2        
        //   272: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   275: goto            257
        //   278: astore_2       
        //   279: aconst_null    
        //   280: astore_1       
        //   281: aload           6
        //   283: astore          5
        //   285: aload           5
        //   287: ifnull          295
        //   290: aload           5
        //   292: invokevirtual   java/io/OutputStream.close:()V
        //   295: aload_1        
        //   296: ifnull          303
        //   299: aload_1        
        //   300: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   303: aload_2        
        //   304: athrow         
        //   305: astore          5
        //   307: aload_0        
        //   308: ldc             "Error closing http compressed post connection output stream"
        //   310: aload           5
        //   312: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   315: goto            295
        //   318: astore_2       
        //   319: aload           6
        //   321: astore          5
        //   323: goto            285
        //   326: astore          6
        //   328: aload_2        
        //   329: astore          5
        //   331: aload           6
        //   333: astore_2       
        //   334: goto            285
        //   337: astore_2       
        //   338: goto            285
        //   341: astore_2       
        //   342: goto            238
        //   345: astore          6
        //   347: aload_2        
        //   348: astore          5
        //   350: aload           6
        //   352: astore_2       
        //   353: goto            238
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  16     75     235    238    Ljava/io/IOException;
        //  16     75     278    285    Any
        //  75     114    235    238    Ljava/io/IOException;
        //  75     114    278    285    Any
        //  114    120    235    238    Ljava/io/IOException;
        //  114    120    278    285    Any
        //  120    149    341    345    Ljava/io/IOException;
        //  120    149    318    326    Any
        //  149    159    345    356    Ljava/io/IOException;
        //  149    159    326    337    Any
        //  159    170    341    345    Ljava/io/IOException;
        //  159    170    318    326    Any
        //  178    185    341    345    Ljava/io/IOException;
        //  178    185    318    326    Any
        //  185    196    341    345    Ljava/io/IOException;
        //  185    196    318    326    Any
        //  200    208    224    235    Ljava/io/IOException;
        //  238    245    337    341    Any
        //  252    257    267    278    Ljava/io/IOException;
        //  290    295    305    318    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 191, Size: 191
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    private URL zzb(final zzab zzab, final String s) {
        Label_0059: {
            if (!zzab.zzkm()) {
                break Label_0059;
            }
            String s2 = this.zzif().zzjy() + this.zzif().zzjA() + "?" + s;
            try {
                return new URL(s2);
                s2 = this.zzif().zzjz() + this.zzif().zzjA() + "?" + s;
                return new URL(s2);
            }
            catch (MalformedURLException ex) {
                this.zze("Error trying to parse the hardcoded host url", ex);
                return null;
            }
        }
    }
    
    private void zzb(final HttpURLConnection httpURLConnection) {
        InputStream inputStream = null;
        try {
            final InputStream inputStream2 = inputStream = httpURLConnection.getInputStream();
            do {
                inputStream = inputStream2;
            } while (inputStream2.read(new byte[1024]) > 0);
            if (inputStream2 == null) {
                return;
            }
            try {
                inputStream2.close();
            }
            catch (IOException ex) {
                this.zze("Error closing http connection input stream", ex);
            }
        }
        finally {
            Label_0057: {
                if (inputStream == null) {
                    break Label_0057;
                }
                try {
                    inputStream.close();
                }
                catch (IOException ex2) {
                    this.zze("Error closing http connection input stream", ex2);
                }
            }
        }
    }
    
    private boolean zzg(final zzab zzab) {
        zzx.zzv(zzab);
        final String zza = this.zza(zzab, !zzab.zzkm());
        if (zza == null) {
            this.zzie().zza(zzab, "Error formatting hit for upload");
        }
        else if (zza.length() <= this.zzif().zzjn()) {
            final URL zzb = this.zzb(zzab, zza);
            if (zzb == null) {
                this.zzbc("Failed to build collect GET endpoint url");
                return false;
            }
            if (this.zzb(zzb) != 200) {
                return false;
            }
        }
        else {
            final String zza2 = this.zza(zzab, false);
            if (zza2 == null) {
                this.zzie().zza(zzab, "Error formatting hit for POST upload");
                return true;
            }
            final byte[] bytes = zza2.getBytes();
            if (bytes.length > this.zzif().zzjp()) {
                this.zzie().zza(zzab, "Hit payload exceeds size limit");
                return true;
            }
            final URL zzh = this.zzh(zzab);
            if (zzh == null) {
                this.zzbc("Failed to build collect POST endpoint url");
                return false;
            }
            if (this.zza(zzh, bytes) != 200) {
                return false;
            }
        }
        return true;
    }
    
    private static byte[] zzg(final byte[] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(array);
        gzipOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    private URL zzh(final zzab zzab) {
        Label_0049: {
            if (!zzab.zzkm()) {
                break Label_0049;
            }
            String s = this.zzif().zzjy() + this.zzif().zzjA();
            try {
                return new URL(s);
                s = this.zzif().zzjz() + this.zzif().zzjA();
                return new URL(s);
            }
            catch (MalformedURLException ex) {
                this.zze("Error trying to parse the hardcoded host url", ex);
                return null;
            }
        }
    }
    
    private String zzi(final zzab zzab) {
        return String.valueOf(zzab.zzkj());
    }
    
    private URL zzkv() {
        final String string = this.zzif().zzjy() + this.zzif().zzjB();
        try {
            return new URL(string);
        }
        catch (MalformedURLException ex) {
            this.zze("Error trying to parse the hardcoded host url", ex);
            return null;
        }
    }
    
    String zza(final zzab zzab, final boolean b) {
        zzx.zzv(zzab);
        final StringBuilder sb = new StringBuilder();
        try {
            for (final Map.Entry<String, String> entry : zzab.zzn().entrySet()) {
                final String s = entry.getKey();
                if (!"ht".equals(s) && !"qt".equals(s) && !"AppUID".equals(s) && !"z".equals(s) && !"_gmsv".equals(s)) {
                    this.zza(sb, s, entry.getValue());
                }
            }
        }
        catch (UnsupportedEncodingException ex) {
            this.zze("Failed to encode name or value", ex);
            return null;
        }
        this.zza(sb, "ht", String.valueOf(zzab.zzkk()));
        this.zza(sb, "qt", String.valueOf(this.zzid().currentTimeMillis() - zzab.zzkk()));
        if (this.zzif().zzjk()) {
            this.zza(sb, "_gmsv", zze.VERSION);
        }
        if (b) {
            final long zzkn = zzab.zzkn();
            String s2;
            if (zzkn != 0L) {
                s2 = String.valueOf(zzkn);
            }
            else {
                s2 = this.zzi(zzab);
            }
            this.zza(sb, "z", s2);
        }
        return sb.toString();
    }
    
    List<Long> zza(final List<zzab> list, final boolean b) {
        zzx.zzZ(!list.isEmpty());
        this.zza("Uploading batched hits. compression, count", b, list.size());
        final zzah$zza zzah$zza = new zzah$zza(this);
        final ArrayList<Long> list2 = new ArrayList<Long>();
        for (final zzab zzab : list) {
            if (!zzah$zza.zzj(zzab)) {
                break;
            }
            list2.add(zzab.zzkj());
        }
        if (zzah$zza.zzkx() == 0) {
            return list2;
        }
        final URL zzkv = this.zzkv();
        if (zzkv == null) {
            this.zzbc("Failed to build batching endpoint url");
            return Collections.emptyList();
        }
        int n;
        if (b) {
            n = this.zzb(zzkv, zzah$zza.getPayload());
        }
        else {
            n = this.zza(zzkv, zzah$zza.getPayload());
        }
        if (200 == n) {
            this.zza("Batched upload completed. Hits batched", zzah$zza.zzkx());
            return list2;
        }
        this.zza("Network error uploading hits. status code", n);
        if (this.zzif().zzjE().contains(n)) {
            this.zzbb("Server instructed the client to stop batching");
            this.zzOr.start();
        }
        return Collections.emptyList();
    }
    
    HttpURLConnection zzc(final URL url) {
        final URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain http connection");
        }
        final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setConnectTimeout(this.zzif().zzjN());
        httpURLConnection.setReadTimeout(this.zzif().zzjO());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestProperty("User-Agent", this.zzHj);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }
    
    public List<Long> zzf(final List<zzab> list) {
        boolean b = true;
        this.zzic();
        this.zzio();
        zzx.zzv(list);
        int n;
        if (this.zzif().zzjE().isEmpty() || !this.zzOr.zzv(this.zzif().zzjx() * 1000L)) {
            b = false;
            n = 0;
        }
        else {
            boolean b2;
            if (this.zzif().zzjC() != zzm.zzMz) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            n = (b2 ? 1 : 0);
            if (this.zzif().zzjD() != zzo.zzMK) {
                b = false;
                n = (b2 ? 1 : 0);
            }
        }
        if (n != 0) {
            return this.zza(list, b);
        }
        return this.zzg(list);
    }
    
    List<Long> zzg(final List<zzab> list) {
        final ArrayList<Long> list2 = new ArrayList<Long>(list.size());
        for (final zzab zzab : list) {
            if (!this.zzg(zzab)) {
                break;
            }
            list2.add(zzab.zzkj());
            if (list2.size() >= this.zzif().zzjv()) {
                return list2;
            }
        }
        return list2;
    }
    
    @Override
    protected void zzhB() {
        this.zza("Network initialized. User agent", this.zzHj);
    }
    
    public boolean zzku() {
        this.zzic();
        this.zzio();
        final ConnectivityManager connectivityManager = (ConnectivityManager)this.getContext().getSystemService("connectivity");
        while (true) {
            try {
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    this.zzaY("No network connectivity");
                    return false;
                }
            }
            catch (SecurityException ex) {
                final NetworkInfo activeNetworkInfo = null;
                continue;
            }
            break;
        }
        return true;
    }
}
