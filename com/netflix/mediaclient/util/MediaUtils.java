// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.media.MediaCodecInfo$CodecCapabilities;
import java.util.Arrays;
import org.json.JSONArray;
import android.media.MediaCodecInfo$CodecProfileLevel;
import android.annotation.TargetApi;
import android.media.MediaCodecList;
import android.media.MediaCodecInfo;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Map;

public final class MediaUtils
{
    private static final String TAG = "MediaUtils";
    private static final Map<String, MediaUtils$VideoDecoderClassfier> decoderClassifier;
    
    static {
        decoderClassifier = new MediaUtils$1();
    }
    
    public static String getDecoderCapbilityForFormatIfUpdated() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("video/hevc");
        list.add("video/x-vnd.on2.vp9");
        final JSONObject jsonObject = new JSONObject();
        for (final String s : list) {
            try {
                jsonObject.putOpt(MediaUtils.decoderClassifier.get(s).getName(), (Object)getQualifiedDecoders(s));
            }
            catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return jsonObject.toString();
    }
    
    @TargetApi(21)
    private static MediaCodecInfo[] getDecoders() {
        return new MediaCodecList(1).getCodecInfos();
    }
    
    private static MediaCodecInfo[] getDecodersForK() {
        final int codecCount = MediaCodecList.getCodecCount();
        final MediaCodecInfo[] array = new MediaCodecInfo[codecCount];
        for (int i = 0; i < codecCount; ++i) {
            array[i] = MediaCodecList.getCodecInfoAt(i);
        }
        return array;
    }
    
    private static int getMaxLevelForProfile(final MediaCodecInfo$CodecProfileLevel[] array, final int n) {
        int n2 = 0;
        int max;
        for (int length = array.length, i = 0; i < length; ++i, n2 = max) {
            final MediaCodecInfo$CodecProfileLevel mediaCodecInfo$CodecProfileLevel = array[i];
            max = n2;
            if (mediaCodecInfo$CodecProfileLevel.profile == n) {
                max = Math.max(n2, mediaCodecInfo$CodecProfileLevel.level);
            }
        }
        return n2;
    }
    
    private static JSONArray getQualifiedDecoders(final String s) {
        final JSONArray jsonArray = new JSONArray();
        MediaCodecInfo[] array;
        if (AndroidUtils.getAndroidVersion() >= 21) {
            array = getDecoders();
        }
        else {
            array = getDecodersForK();
        }
        final int length = array.length;
        int i = 0;
        MediaCodecInfo mediaCodecInfo = null;
        while (true) {
            while (i < length) {
                final MediaCodecInfo mediaCodecInfo2 = array[i];
                MediaCodecInfo mediaCodecInfo3 = mediaCodecInfo;
                if (mediaCodecInfo2 != null) {
                    if (mediaCodecInfo2.isEncoder()) {
                        mediaCodecInfo3 = mediaCodecInfo;
                    }
                    else {
                        mediaCodecInfo3 = mediaCodecInfo;
                        if (Arrays.asList(mediaCodecInfo2.getSupportedTypes()).indexOf(s) >= 0) {
                            final MediaCodecInfo$CodecCapabilities capabilitiesForType = mediaCodecInfo2.getCapabilitiesForType(s);
                            mediaCodecInfo3 = mediaCodecInfo;
                            if (capabilitiesForType != null) {
                                mediaCodecInfo3 = mediaCodecInfo;
                                if (capabilitiesForType.isFeatureSupported("adaptive-playback")) {
                                    if (capabilitiesForType.isFeatureSupported("secure-playback")) {
                                        if (mediaCodecInfo2 != null) {
                                            final JSONObject logDecoder = logDecoder(mediaCodecInfo2, s);
                                            if (logDecoder != null) {
                                                jsonArray.put((Object)logDecoder);
                                            }
                                        }
                                        return jsonArray;
                                    }
                                    if ((mediaCodecInfo3 = mediaCodecInfo) == null) {
                                        mediaCodecInfo3 = mediaCodecInfo2;
                                    }
                                }
                            }
                        }
                    }
                }
                ++i;
                mediaCodecInfo = mediaCodecInfo3;
            }
            final MediaCodecInfo mediaCodecInfo2 = mediaCodecInfo;
            continue;
        }
    }
    
    private static JSONObject logDecoder(final MediaCodecInfo p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokevirtual   android/media/MediaCodecInfo.getCapabilitiesForType:(Ljava/lang/String;)Landroid/media/MediaCodecInfo$CodecCapabilities;
        //     5: astore          4
        //     7: aload           4
        //     9: ldc             "secure-playback"
        //    11: invokevirtual   android/media/MediaCodecInfo$CodecCapabilities.isFeatureSupported:(Ljava/lang/String;)Z
        //    14: istore_3       
        //    15: getstatic       com/netflix/mediaclient/util/MediaUtils.decoderClassifier:Ljava/util/Map;
        //    18: aload_1        
        //    19: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    24: checkcast       Lcom/netflix/mediaclient/util/MediaUtils$VideoDecoderClassfier;
        //    27: astore_1       
        //    28: aload_1        
        //    29: ifnonnull       34
        //    32: aconst_null    
        //    33: areturn        
        //    34: aload           4
        //    36: getfield        android/media/MediaCodecInfo$CodecCapabilities.profileLevels:[Landroid/media/MediaCodecInfo$CodecProfileLevel;
        //    39: aload_1        
        //    40: invokevirtual   com/netflix/mediaclient/util/MediaUtils$VideoDecoderClassfier.getInterestedProfile:()I
        //    43: invokestatic    com/netflix/mediaclient/util/MediaUtils.getMaxLevelForProfile:([Landroid/media/MediaCodecInfo$CodecProfileLevel;I)I
        //    46: istore_2       
        //    47: iload_2        
        //    48: aload_1        
        //    49: invokevirtual   com/netflix/mediaclient/util/MediaUtils$VideoDecoderClassfier.getFhdLevel:()I
        //    52: if_icmplt       173
        //    55: iconst_3       
        //    56: istore_2       
        //    57: new             Lorg/json/JSONObject;
        //    60: dup            
        //    61: invokespecial   org/json/JSONObject.<init>:()V
        //    64: astore_1       
        //    65: aload_1        
        //    66: ldc             "name"
        //    68: aload_0        
        //    69: invokevirtual   android/media/MediaCodecInfo.getName:()Ljava/lang/String;
        //    72: invokevirtual   org/json/JSONObject.putOpt:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    75: pop            
        //    76: aload_1        
        //    77: ldc             "securePlayback"
        //    79: iload_3        
        //    80: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    83: invokevirtual   org/json/JSONObject.putOpt:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    86: pop            
        //    87: aload_1        
        //    88: ldc             "hdPlayback"
        //    90: iload_2        
        //    91: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    94: invokevirtual   org/json/JSONObject.putOpt:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    97: pop            
        //    98: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   101: bipush          23
        //   103: if_icmplt       171
        //   106: aload           4
        //   108: invokevirtual   android/media/MediaCodecInfo$CodecCapabilities.getVideoCapabilities:()Landroid/media/MediaCodecInfo$VideoCapabilities;
        //   111: astore_0       
        //   112: aload_0        
        //   113: ifnull          171
        //   116: aload_0        
        //   117: sipush          1920
        //   120: sipush          1080
        //   123: invokevirtual   android/media/MediaCodecInfo$VideoCapabilities.getAchievableFrameRatesFor:(II)Landroid/util/Range;
        //   126: astore          4
        //   128: aload           4
        //   130: ifnull          145
        //   133: aload_1        
        //   134: ldc             "hdPerf"
        //   136: aload           4
        //   138: invokevirtual   android/util/Range.getUpper:()Ljava/lang/Comparable;
        //   141: invokevirtual   org/json/JSONObject.putOpt:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   144: pop            
        //   145: aload_0        
        //   146: sipush          720
        //   149: sipush          480
        //   152: invokevirtual   android/media/MediaCodecInfo$VideoCapabilities.getAchievableFrameRatesFor:(II)Landroid/util/Range;
        //   155: astore_0       
        //   156: aload_0        
        //   157: ifnull          171
        //   160: aload_1        
        //   161: ldc             "sdPerf"
        //   163: aload_0        
        //   164: invokevirtual   android/util/Range.getUpper:()Ljava/lang/Comparable;
        //   167: invokevirtual   org/json/JSONObject.putOpt:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   170: pop            
        //   171: aload_1        
        //   172: areturn        
        //   173: iload_2        
        //   174: aload_1        
        //   175: invokevirtual   com/netflix/mediaclient/util/MediaUtils$VideoDecoderClassfier.getHdLevel:()I
        //   178: if_icmplt       186
        //   181: iconst_2       
        //   182: istore_2       
        //   183: goto            57
        //   186: iload_2        
        //   187: aload_1        
        //   188: invokevirtual   com/netflix/mediaclient/util/MediaUtils$VideoDecoderClassfier.getLowestLevel:()I
        //   191: if_icmplt       237
        //   194: iconst_1       
        //   195: istore_2       
        //   196: goto            57
        //   199: astore          4
        //   201: aload_1        
        //   202: ldc             "hdPerf"
        //   204: iconst_0       
        //   205: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   208: invokevirtual   org/json/JSONObject.putOpt:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   211: pop            
        //   212: goto            145
        //   215: astore_0       
        //   216: aload_0        
        //   217: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   220: aconst_null    
        //   221: areturn        
        //   222: astore_0       
        //   223: aload_1        
        //   224: ldc             "sdPerf"
        //   226: iconst_0       
        //   227: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   230: invokevirtual   org/json/JSONObject.putOpt:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   233: pop            
        //   234: goto            171
        //   237: iconst_0       
        //   238: istore_2       
        //   239: goto            57
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  65     112    215    222    Lorg/json/JSONException;
        //  116    128    199    215    Ljava/lang/Throwable;
        //  116    128    215    222    Lorg/json/JSONException;
        //  133    145    199    215    Ljava/lang/Throwable;
        //  133    145    215    222    Lorg/json/JSONException;
        //  145    156    222    237    Ljava/lang/Throwable;
        //  145    156    215    222    Lorg/json/JSONException;
        //  160    171    222    237    Ljava/lang/Throwable;
        //  160    171    215    222    Lorg/json/JSONException;
        //  201    212    215    222    Lorg/json/JSONException;
        //  223    234    215    222    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0145:
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
}
