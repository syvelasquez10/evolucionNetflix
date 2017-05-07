// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import java.util.Map;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;

public class HttpHeaderParser
{
    public static Cache.Entry parseCacheHeaders(final NetworkResponse p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    java/lang/System.currentTimeMillis:()J
        //     3: lstore          14
        //     5: aload_0        
        //     6: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //     9: astore          16
        //    11: lconst_0       
        //    12: lstore          6
        //    14: lconst_0       
        //    15: lstore          10
        //    17: lconst_0       
        //    18: lstore          12
        //    20: lconst_0       
        //    21: lstore          4
        //    23: iconst_0       
        //    24: istore_2       
        //    25: aload           16
        //    27: ldc             "Date"
        //    29: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    34: checkcast       Ljava/lang/String;
        //    37: astore          17
        //    39: aload           17
        //    41: ifnull          51
        //    44: aload           17
        //    46: invokestatic    com/android/volley/toolbox/HttpHeaderParser.parseDateAsEpoch:(Ljava/lang/String;)J
        //    49: lstore          6
        //    51: aload           16
        //    53: ldc             "Cache-Control"
        //    55: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    60: checkcast       Ljava/lang/String;
        //    63: astore          17
        //    65: lload           4
        //    67: lstore          8
        //    69: aload           17
        //    71: ifnull          190
        //    74: iconst_1       
        //    75: istore_3       
        //    76: aload           17
        //    78: ldc             ","
        //    80: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    83: astore          17
        //    85: iconst_0       
        //    86: istore_1       
        //    87: iload_3        
        //    88: istore_2       
        //    89: lload           4
        //    91: lstore          8
        //    93: iload_1        
        //    94: aload           17
        //    96: arraylength    
        //    97: if_icmpge       190
        //   100: aload           17
        //   102: iload_1        
        //   103: aaload         
        //   104: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   107: astore          18
        //   109: aload           18
        //   111: ldc             "no-cache"
        //   113: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   116: ifne            129
        //   119: aload           18
        //   121: ldc             "no-store"
        //   123: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   126: ifeq            131
        //   129: aconst_null    
        //   130: areturn        
        //   131: aload           18
        //   133: ldc             "max-age="
        //   135: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   138: ifeq            164
        //   141: aload           18
        //   143: bipush          8
        //   145: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   148: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   151: lstore          8
        //   153: lload           8
        //   155: lstore          4
        //   157: iload_1        
        //   158: iconst_1       
        //   159: iadd           
        //   160: istore_1       
        //   161: goto            87
        //   164: aload           18
        //   166: ldc             "must-revalidate"
        //   168: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   171: ifne            184
        //   174: aload           18
        //   176: ldc             "proxy-revalidate"
        //   178: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   181: ifeq            157
        //   184: lconst_0       
        //   185: lstore          4
        //   187: goto            157
        //   190: aload           16
        //   192: ldc             "Expires"
        //   194: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   199: checkcast       Ljava/lang/String;
        //   202: astore          17
        //   204: aload           17
        //   206: ifnull          216
        //   209: aload           17
        //   211: invokestatic    com/android/volley/toolbox/HttpHeaderParser.parseDateAsEpoch:(Ljava/lang/String;)J
        //   214: lstore          10
        //   216: aload           16
        //   218: ldc             "ETag"
        //   220: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   225: checkcast       Ljava/lang/String;
        //   228: astore          17
        //   230: iload_2        
        //   231: ifeq            304
        //   234: lload           14
        //   236: ldc2_w          1000
        //   239: lload           8
        //   241: lmul           
        //   242: ladd           
        //   243: lstore          4
        //   245: new             Lcom/android/volley/Cache$Entry;
        //   248: dup            
        //   249: invokespecial   com/android/volley/Cache$Entry.<init>:()V
        //   252: astore          18
        //   254: aload           18
        //   256: aload_0        
        //   257: getfield        com/android/volley/NetworkResponse.data:[B
        //   260: putfield        com/android/volley/Cache$Entry.data:[B
        //   263: aload           18
        //   265: aload           17
        //   267: putfield        com/android/volley/Cache$Entry.etag:Ljava/lang/String;
        //   270: aload           18
        //   272: lload           4
        //   274: putfield        com/android/volley/Cache$Entry.softTtl:J
        //   277: aload           18
        //   279: aload           18
        //   281: getfield        com/android/volley/Cache$Entry.softTtl:J
        //   284: putfield        com/android/volley/Cache$Entry.ttl:J
        //   287: aload           18
        //   289: lload           6
        //   291: putfield        com/android/volley/Cache$Entry.serverDate:J
        //   294: aload           18
        //   296: aload           16
        //   298: putfield        com/android/volley/Cache$Entry.responseHeaders:Ljava/util/Map;
        //   301: aload           18
        //   303: areturn        
        //   304: lload           12
        //   306: lstore          4
        //   308: lload           6
        //   310: lconst_0       
        //   311: lcmp           
        //   312: ifle            245
        //   315: lload           12
        //   317: lstore          4
        //   319: lload           10
        //   321: lload           6
        //   323: lcmp           
        //   324: iflt            245
        //   327: lload           14
        //   329: lload           10
        //   331: lload           6
        //   333: lsub           
        //   334: ladd           
        //   335: lstore          4
        //   337: goto            245
        //   340: astore          18
        //   342: goto            157
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  141    153    340    345    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    public static String parseCharset(final Map<String, String> map) {
        final String s = map.get("Content-Type");
        if (s != null) {
            final String[] split = s.split(";");
            for (int i = 1; i < split.length; ++i) {
                final String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return "ISO-8859-1";
    }
    
    public static long parseDateAsEpoch(final String s) {
        try {
            return DateUtils.parseDate(s).getTime();
        }
        catch (DateParseException ex) {
            return 0L;
        }
    }
}
