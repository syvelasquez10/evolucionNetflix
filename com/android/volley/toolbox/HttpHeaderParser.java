// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import java.util.Map;
import com.android.volley.Cache$Entry;
import com.android.volley.NetworkResponse;

public class HttpHeaderParser
{
    public static Cache$Entry parseCacheHeaders(final NetworkResponse p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    java/lang/System.currentTimeMillis:()J
        //     3: lstore          12
        //     5: aload_0        
        //     6: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //     9: astore          14
        //    11: lconst_0       
        //    12: lstore          6
        //    14: lconst_0       
        //    15: lstore          10
        //    17: lconst_0       
        //    18: lstore          4
        //    20: iconst_0       
        //    21: istore_2       
        //    22: aload           14
        //    24: ldc             "Date"
        //    26: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    31: checkcast       Ljava/lang/String;
        //    34: astore          15
        //    36: aload           15
        //    38: ifnull          48
        //    41: aload           15
        //    43: invokestatic    com/android/volley/toolbox/HttpHeaderParser.parseDateAsEpoch:(Ljava/lang/String;)J
        //    46: lstore          6
        //    48: aload           14
        //    50: ldc             "Cache-Control"
        //    52: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    57: checkcast       Ljava/lang/String;
        //    60: astore          15
        //    62: lload           4
        //    64: lstore          8
        //    66: aload           15
        //    68: ifnull          187
        //    71: iconst_1       
        //    72: istore_3       
        //    73: aload           15
        //    75: ldc             ","
        //    77: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    80: astore          15
        //    82: iconst_0       
        //    83: istore_1       
        //    84: lload           4
        //    86: lstore          8
        //    88: iload_3        
        //    89: istore_2       
        //    90: iload_1        
        //    91: aload           15
        //    93: arraylength    
        //    94: if_icmpge       187
        //    97: aload           15
        //    99: iload_1        
        //   100: aaload         
        //   101: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   104: astore          16
        //   106: aload           16
        //   108: ldc             "no-cache"
        //   110: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   113: ifne            126
        //   116: aload           16
        //   118: ldc             "no-store"
        //   120: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   123: ifeq            128
        //   126: aconst_null    
        //   127: areturn        
        //   128: aload           16
        //   130: ldc             "max-age="
        //   132: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   135: ifeq            161
        //   138: aload           16
        //   140: bipush          8
        //   142: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   145: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   148: lstore          8
        //   150: lload           8
        //   152: lstore          4
        //   154: iload_1        
        //   155: iconst_1       
        //   156: iadd           
        //   157: istore_1       
        //   158: goto            84
        //   161: aload           16
        //   163: ldc             "must-revalidate"
        //   165: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   168: ifne            181
        //   171: aload           16
        //   173: ldc             "proxy-revalidate"
        //   175: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   178: ifeq            154
        //   181: lconst_0       
        //   182: lstore          4
        //   184: goto            154
        //   187: aload           14
        //   189: ldc             "Expires"
        //   191: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   196: checkcast       Ljava/lang/String;
        //   199: astore          15
        //   201: lload           10
        //   203: lstore          4
        //   205: aload           15
        //   207: ifnull          217
        //   210: aload           15
        //   212: invokestatic    com/android/volley/toolbox/HttpHeaderParser.parseDateAsEpoch:(Ljava/lang/String;)J
        //   215: lstore          4
        //   217: aload           14
        //   219: ldc             "ETag"
        //   221: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   226: checkcast       Ljava/lang/String;
        //   229: astore          15
        //   231: iload_2        
        //   232: ifeq            305
        //   235: lload           8
        //   237: ldc2_w          1000
        //   240: lmul           
        //   241: lload           12
        //   243: ladd           
        //   244: lstore          4
        //   246: new             Lcom/android/volley/Cache$Entry;
        //   249: dup            
        //   250: invokespecial   com/android/volley/Cache$Entry.<init>:()V
        //   253: astore          16
        //   255: aload           16
        //   257: aload_0        
        //   258: getfield        com/android/volley/NetworkResponse.data:[B
        //   261: putfield        com/android/volley/Cache$Entry.data:[B
        //   264: aload           16
        //   266: aload           15
        //   268: putfield        com/android/volley/Cache$Entry.etag:Ljava/lang/String;
        //   271: aload           16
        //   273: lload           4
        //   275: putfield        com/android/volley/Cache$Entry.softTtl:J
        //   278: aload           16
        //   280: aload           16
        //   282: getfield        com/android/volley/Cache$Entry.softTtl:J
        //   285: putfield        com/android/volley/Cache$Entry.ttl:J
        //   288: aload           16
        //   290: lload           6
        //   292: putfield        com/android/volley/Cache$Entry.serverDate:J
        //   295: aload           16
        //   297: aload           14
        //   299: putfield        com/android/volley/Cache$Entry.responseHeaders:Ljava/util/Map;
        //   302: aload           16
        //   304: areturn        
        //   305: lload           6
        //   307: lconst_0       
        //   308: lcmp           
        //   309: ifle            338
        //   312: lload           4
        //   314: lload           6
        //   316: lcmp           
        //   317: iflt            338
        //   320: lload           4
        //   322: lload           6
        //   324: lsub           
        //   325: lload           12
        //   327: ladd           
        //   328: lstore          4
        //   330: goto            246
        //   333: astore          16
        //   335: goto            154
        //   338: lconst_0       
        //   339: lstore          4
        //   341: goto            246
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  138    150    333    338    Ljava/lang/Exception;
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
