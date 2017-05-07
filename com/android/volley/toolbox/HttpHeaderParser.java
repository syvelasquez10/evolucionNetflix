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
        //     0: iconst_0       
        //     1: istore_1       
        //     2: lconst_0       
        //     3: lstore          8
        //     5: invokestatic    java/lang/System.currentTimeMillis:()J
        //     8: lstore          10
        //    10: aload_0        
        //    11: getfield        com/android/volley/NetworkResponse.headers:Ljava/util/Map;
        //    14: astore          12
        //    16: aload           12
        //    18: ldc             "Date"
        //    20: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    25: checkcast       Ljava/lang/String;
        //    28: astore          13
        //    30: aload           13
        //    32: ifnull          331
        //    35: aload           13
        //    37: invokestatic    com/android/volley/toolbox/HttpHeaderParser.parseDateAsEpoch:(Ljava/lang/String;)J
        //    40: lstore          4
        //    42: aload           12
        //    44: ldc             "Cache-Control"
        //    46: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    51: checkcast       Ljava/lang/String;
        //    54: astore          13
        //    56: aload           13
        //    58: ifnull          326
        //    61: aload           13
        //    63: ldc             ","
        //    65: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    68: astore          13
        //    70: iconst_0       
        //    71: istore_1       
        //    72: lconst_0       
        //    73: lstore_2       
        //    74: iload_1        
        //    75: aload           13
        //    77: arraylength    
        //    78: if_icmpge       169
        //    81: aload           13
        //    83: iload_1        
        //    84: aaload         
        //    85: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    88: astore          14
        //    90: aload           14
        //    92: ldc             "no-cache"
        //    94: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    97: ifne            110
        //   100: aload           14
        //   102: ldc             "no-store"
        //   104: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   107: ifeq            112
        //   110: aconst_null    
        //   111: areturn        
        //   112: aload           14
        //   114: ldc             "max-age="
        //   116: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   119: ifeq            144
        //   122: aload           14
        //   124: bipush          8
        //   126: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   129: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   132: lstore          6
        //   134: lload           6
        //   136: lstore_2       
        //   137: iload_1        
        //   138: iconst_1       
        //   139: iadd           
        //   140: istore_1       
        //   141: goto            74
        //   144: aload           14
        //   146: ldc             "must-revalidate"
        //   148: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   151: ifne            164
        //   154: aload           14
        //   156: ldc             "proxy-revalidate"
        //   158: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   161: ifeq            137
        //   164: lconst_0       
        //   165: lstore_2       
        //   166: goto            137
        //   169: iconst_1       
        //   170: istore_1       
        //   171: aload           12
        //   173: ldc             "Expires"
        //   175: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   180: checkcast       Ljava/lang/String;
        //   183: astore          13
        //   185: aload           13
        //   187: ifnull          320
        //   190: aload           13
        //   192: invokestatic    com/android/volley/toolbox/HttpHeaderParser.parseDateAsEpoch:(Ljava/lang/String;)J
        //   195: lstore          6
        //   197: aload           12
        //   199: ldc             "ETag"
        //   201: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   206: checkcast       Ljava/lang/String;
        //   209: astore          13
        //   211: iload_1        
        //   212: ifeq            282
        //   215: ldc2_w          1000
        //   218: lload_2        
        //   219: lmul           
        //   220: lload           10
        //   222: ladd           
        //   223: lstore_2       
        //   224: new             Lcom/android/volley/Cache$Entry;
        //   227: dup            
        //   228: invokespecial   com/android/volley/Cache$Entry.<init>:()V
        //   231: astore          14
        //   233: aload           14
        //   235: aload_0        
        //   236: getfield        com/android/volley/NetworkResponse.data:[B
        //   239: putfield        com/android/volley/Cache$Entry.data:[B
        //   242: aload           14
        //   244: aload           13
        //   246: putfield        com/android/volley/Cache$Entry.etag:Ljava/lang/String;
        //   249: aload           14
        //   251: lload_2        
        //   252: putfield        com/android/volley/Cache$Entry.softTtl:J
        //   255: aload           14
        //   257: aload           14
        //   259: getfield        com/android/volley/Cache$Entry.softTtl:J
        //   262: putfield        com/android/volley/Cache$Entry.ttl:J
        //   265: aload           14
        //   267: lload           4
        //   269: putfield        com/android/volley/Cache$Entry.serverDate:J
        //   272: aload           14
        //   274: aload           12
        //   276: putfield        com/android/volley/Cache$Entry.responseHeaders:Ljava/util/Map;
        //   279: aload           14
        //   281: areturn        
        //   282: lload           8
        //   284: lstore_2       
        //   285: lload           4
        //   287: lconst_0       
        //   288: lcmp           
        //   289: ifle            224
        //   292: lload           8
        //   294: lstore_2       
        //   295: lload           6
        //   297: lload           4
        //   299: lcmp           
        //   300: iflt            224
        //   303: lload           6
        //   305: lload           4
        //   307: lsub           
        //   308: lload           10
        //   310: ladd           
        //   311: lstore_2       
        //   312: goto            224
        //   315: astore          14
        //   317: goto            137
        //   320: lconst_0       
        //   321: lstore          6
        //   323: goto            197
        //   326: lconst_0       
        //   327: lstore_2       
        //   328: goto            171
        //   331: lconst_0       
        //   332: lstore          4
        //   334: goto            42
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  122    134    315    320    Ljava/lang/Exception;
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
