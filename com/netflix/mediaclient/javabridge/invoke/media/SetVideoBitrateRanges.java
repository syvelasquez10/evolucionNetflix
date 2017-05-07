// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.media.bitrate.VideoBitrateRange;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetVideoBitrateRanges extends BaseInvoke
{
    private static final String METHOD = "setVideoBitrateRanges";
    private static final String PROPERTY_maxBitrate = "max";
    private static final String PROPERTY_minBitrate = "min";
    private static final String PROPERTY_profile = "profile";
    private static final String PROPERTY_ranges = "ranges";
    private static final String TARGET = "media";
    
    public SetVideoBitrateRanges(final int p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ldc             "media"
        //     3: ldc             "setVideoBitrateRanges"
        //     5: invokespecial   com/netflix/mediaclient/javabridge/invoke/BaseInvoke.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //     8: invokestatic    com/netflix/mediaclient/javabridge/transport/NativeTransport.getSupportedVideoProfiles:()[Ljava/lang/String;
        //    11: astore          7
        //    13: ldc             "nf_invoke"
        //    15: iconst_3       
        //    16: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    19: ifeq            56
        //    22: ldc             "nf_invoke"
        //    24: new             Ljava/lang/StringBuilder;
        //    27: dup            
        //    28: invokespecial   java/lang/StringBuilder.<init>:()V
        //    31: ldc             "minBitrate: "
        //    33: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    36: iload_1        
        //    37: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    40: ldc             ", maxBitrate: "
        //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    45: iload_2        
        //    46: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    49: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    52: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    55: pop            
        //    56: new             Lorg/json/JSONArray;
        //    59: dup            
        //    60: invokespecial   org/json/JSONArray.<init>:()V
        //    63: astore          6
        //    65: aload           7
        //    67: arraylength    
        //    68: istore          4
        //    70: iconst_0       
        //    71: istore_3       
        //    72: aconst_null    
        //    73: astore          5
        //    75: iload_3        
        //    76: iload           4
        //    78: if_icmpge       139
        //    81: aload           7
        //    83: iload_3        
        //    84: aaload         
        //    85: astore          8
        //    87: new             Lorg/json/JSONObject;
        //    90: dup            
        //    91: invokespecial   org/json/JSONObject.<init>:()V
        //    94: astore          5
        //    96: aload           5
        //    98: ldc             "min"
        //   100: iload_1        
        //   101: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   104: pop            
        //   105: aload           5
        //   107: ldc             "max"
        //   109: iload_2        
        //   110: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   113: pop            
        //   114: aload           5
        //   116: ldc             "profile"
        //   118: aload           8
        //   120: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   123: pop            
        //   124: aload           6
        //   126: aload           5
        //   128: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   131: pop            
        //   132: iload_3        
        //   133: iconst_1       
        //   134: iadd           
        //   135: istore_3       
        //   136: goto            75
        //   139: new             Lorg/json/JSONObject;
        //   142: dup            
        //   143: invokespecial   org/json/JSONObject.<init>:()V
        //   146: astore          5
        //   148: aload           5
        //   150: ldc             "ranges"
        //   152: aload           6
        //   154: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   157: pop            
        //   158: aload_0        
        //   159: aload           5
        //   161: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   164: putfield        com/netflix/mediaclient/javabridge/invoke/media/SetVideoBitrateRanges.arguments:Ljava/lang/String;
        //   167: return         
        //   168: astore          5
        //   170: ldc             "nf_invoke"
        //   172: ldc             "Failed to create JSON object"
        //   174: aload           5
        //   176: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   179: pop            
        //   180: return         
        //   181: astore          5
        //   183: goto            170
        //   186: astore          5
        //   188: goto            170
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  65     70     168    170    Lorg/json/JSONException;
        //  87     96     181    186    Lorg/json/JSONException;
        //  96     132    168    170    Lorg/json/JSONException;
        //  139    148    181    186    Lorg/json/JSONException;
        //  148    167    186    191    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 96, Size: 96
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public SetVideoBitrateRanges(final VideoBitrateRange[] arguments) {
        super("media", "setVideoBitrateRanges");
        if (arguments == null) {
            throw new IllegalArgumentException("Range can not be null!");
        }
        this.setArguments(arguments);
    }
    
    private void setArguments(final VideoBitrateRange[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONArray;
        //     3: dup            
        //     4: invokespecial   org/json/JSONArray.<init>:()V
        //     7: astore          5
        //     9: aload_1        
        //    10: arraylength    
        //    11: istore_3       
        //    12: iconst_0       
        //    13: istore_2       
        //    14: aconst_null    
        //    15: astore          4
        //    17: iload_2        
        //    18: iload_3        
        //    19: if_icmpge       90
        //    22: aload_1        
        //    23: iload_2        
        //    24: aaload         
        //    25: astore          6
        //    27: new             Lorg/json/JSONObject;
        //    30: dup            
        //    31: invokespecial   org/json/JSONObject.<init>:()V
        //    34: astore          4
        //    36: aload           4
        //    38: ldc             "min"
        //    40: aload           6
        //    42: invokevirtual   com/netflix/mediaclient/media/bitrate/VideoBitrateRange.getMinimal:()I
        //    45: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    48: pop            
        //    49: aload           4
        //    51: ldc             "max"
        //    53: aload           6
        //    55: invokevirtual   com/netflix/mediaclient/media/bitrate/VideoBitrateRange.getMaximal:()I
        //    58: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    61: pop            
        //    62: aload           4
        //    64: ldc             "profile"
        //    66: aload           6
        //    68: invokevirtual   com/netflix/mediaclient/media/bitrate/VideoBitrateRange.getProfile:()Ljava/lang/String;
        //    71: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    74: pop            
        //    75: aload           5
        //    77: aload           4
        //    79: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    82: pop            
        //    83: iload_2        
        //    84: iconst_1       
        //    85: iadd           
        //    86: istore_2       
        //    87: goto            17
        //    90: new             Lorg/json/JSONObject;
        //    93: dup            
        //    94: invokespecial   org/json/JSONObject.<init>:()V
        //    97: astore_1       
        //    98: aload_1        
        //    99: ldc             "ranges"
        //   101: aload           5
        //   103: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   106: pop            
        //   107: aload_0        
        //   108: aload_1        
        //   109: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   112: putfield        com/netflix/mediaclient/javabridge/invoke/media/SetVideoBitrateRanges.arguments:Ljava/lang/String;
        //   115: return         
        //   116: astore_1       
        //   117: ldc             "nf_invoke"
        //   119: ldc             "Failed to create JSON object"
        //   121: aload_1        
        //   122: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   125: pop            
        //   126: return         
        //   127: astore_1       
        //   128: goto            117
        //   131: astore_1       
        //   132: goto            117
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  9      12     116    117    Lorg/json/JSONException;
        //  27     36     127    131    Lorg/json/JSONException;
        //  36     83     116    117    Lorg/json/JSONException;
        //  90     98     127    131    Lorg/json/JSONException;
        //  98     115    131    135    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 74, Size: 74
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
}
