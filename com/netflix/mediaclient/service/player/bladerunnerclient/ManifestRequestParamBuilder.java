// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import com.netflix.mediaclient.service.player.exoplayback.ExoVideoCodecSelector;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public class ManifestRequestParamBuilder
{
    private static final String TAG = "nf_msl_volley_FetchManifestsRequest";
    private static boolean allowAVC;
    private static boolean forceHEVC;
    private static boolean forceVP9;
    private boolean allowHEVC;
    private boolean allowVP9;
    private ServiceAgent$ConfigurationAgentInterface mConfig;
    private String mDxid;
    private IBladeRunnerClient$ManifestRequestFlavor mFalvor;
    private String mOxid;
    private String[] mPlayableIds;
    private IBladeRunnerClient$ManifestType mType;
    private String mUiversion;
    private ServiceAgent$UserAgentInterface mUser;
    private int mVersion;
    private DownloadVideoQuality mVideoQuality;
    
    static {
        ManifestRequestParamBuilder.forceVP9 = false;
        ManifestRequestParamBuilder.forceHEVC = false;
        ManifestRequestParamBuilder.allowAVC = true;
    }
    
    ManifestRequestParamBuilder(final ServiceAgent$ConfigurationAgentInterface mConfig, final ServiceAgent$UserAgentInterface mUser) {
        this.mConfig = mConfig;
        this.mUser = mUser;
        this.mType = IBladeRunnerClient$ManifestType.STANDARD;
        this.mVersion = 2;
        this.allowVP9 = this.mConfig.isAllowVp9Mobile();
        this.allowHEVC = this.mConfig.isAllowHevcMobile();
    }
    
    private String buildManifestUrl() {
        return "/" + this.getManifestMethodName();
    }
    
    private String getManifestMethodName() {
        return "manifest";
    }
    
    public static String getPresetFormat() {
        if (ManifestRequestParamBuilder.forceHEVC) {
            return "video/hevc";
        }
        if (ManifestRequestParamBuilder.forceVP9) {
            return "video/x-vnd.on2.vp9";
        }
        return "video/avc";
    }
    
    public static void presetVideoFormat(final String s) {
        ManifestRequestParamBuilder.forceVP9 = false;
        ManifestRequestParamBuilder.forceHEVC = false;
        if ("video/hevc".equals(s)) {
            ManifestRequestParamBuilder.forceHEVC = true;
            ManifestRequestParamBuilder.allowAVC = false;
        }
        else if ("video/x-vnd.on2.vp9".equals(s)) {
            ManifestRequestParamBuilder.forceVP9 = true;
            ManifestRequestParamBuilder.allowAVC = false;
        }
        else {
            ManifestRequestParamBuilder.allowAVC = true;
        }
        Log.d("nf_msl_volley_FetchManifestsRequest", "presetVideoFormat " + s);
    }
    
    private boolean shouldUseVP9(final boolean b) {
        if (b) {
            return ExoVideoCodecSelector.isHasSecureVP9Decoder();
        }
        return ExoVideoCodecSelector.isSupportVP9();
    }
    
    final String build() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_3       
        //     2: new             Lorg/json/JSONObject;
        //     5: dup            
        //     6: invokespecial   org/json/JSONObject.<init>:()V
        //     9: astore          4
        //    11: aload_0        
        //    12: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mConfig:Lcom/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface;
        //    15: invokeinterface com/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface.isDeviceHd:()Z
        //    20: ifeq            804
        //    23: aload_0        
        //    24: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mConfig:Lcom/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface;
        //    27: invokeinterface com/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface.isWidevineL1Enabled:()Z
        //    32: ifeq            804
        //    35: iconst_1       
        //    36: istore_1       
        //    37: aload_0        
        //    38: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mConfig:Lcom/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface;
        //    41: invokeinterface com/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface.getVideoResolutionRange:()Lcom/netflix/mediaclient/media/VideoResolutionRange;
        //    46: astore          5
        //    48: new             Lorg/json/JSONArray;
        //    51: dup            
        //    52: invokespecial   org/json/JSONArray.<init>:()V
        //    55: astore          6
        //    57: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.allowAVC:Z
        //    60: ifeq            133
        //    63: aload           6
        //    65: ldc             "playready-h264mpl30-dash"
        //    67: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    70: pop            
        //    71: aload           5
        //    73: invokevirtual   com/netflix/mediaclient/media/VideoResolutionRange.getMaxHeight:()I
        //    76: sipush          720
        //    79: if_icmplt       102
        //    82: iload_1        
        //    83: ifeq            102
        //    86: aload           6
        //    88: ldc             "playready-h264mpl31-dash"
        //    90: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    93: pop            
        //    94: ldc             "nf_msl_volley_FetchManifestsRequest"
        //    96: ldc             "device supports 720P"
        //    98: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   101: pop            
        //   102: aload           5
        //   104: invokevirtual   com/netflix/mediaclient/media/VideoResolutionRange.getMaxHeight:()I
        //   107: sipush          1080
        //   110: if_icmplt       133
        //   113: iload_1        
        //   114: ifeq            133
        //   117: aload           6
        //   119: ldc             "playready-h264mpl40-dash"
        //   121: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   124: pop            
        //   125: ldc             "nf_msl_volley_FetchManifestsRequest"
        //   127: ldc             "device supports 1080P"
        //   129: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   132: pop            
        //   133: iload_1        
        //   134: ifeq            809
        //   137: invokestatic    com/netflix/mediaclient/service/player/exoplayback/ExoVideoCodecSelector.isHasSecureHEVCDecoder:()Z
        //   140: istore_2       
        //   141: aload_0        
        //   142: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.allowHEVC:Z
        //   145: ifeq            152
        //   148: iload_2        
        //   149: ifne            158
        //   152: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.forceHEVC:Z
        //   155: ifeq            211
        //   158: aload           6
        //   160: ldc             "hevc-main-L21-dash-cenc-mobile"
        //   162: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   165: pop            
        //   166: aload           6
        //   168: ldc             "hevc-main-L30-dash-cenc-mobile"
        //   170: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   173: pop            
        //   174: aload           5
        //   176: invokevirtual   com/netflix/mediaclient/media/VideoResolutionRange.getMaxHeight:()I
        //   179: sipush          720
        //   182: if_icmplt       203
        //   185: iload_1        
        //   186: ifeq            203
        //   189: invokestatic    com/netflix/mediaclient/service/player/exoplayback/ExoVideoCodecSelector.isHasSecureHEVCDecoder:()Z
        //   192: ifeq            203
        //   195: aload           6
        //   197: ldc             "hevc-main-L31-dash-cenc-mobile"
        //   199: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   202: pop            
        //   203: ldc             "nf_msl_volley_FetchManifestsRequest"
        //   205: ldc             "device supports HEVC"
        //   207: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   210: pop            
        //   211: iload_1        
        //   212: ifeq            816
        //   215: invokestatic    com/netflix/mediaclient/service/player/exoplayback/ExoVideoCodecSelector.isHasSecureVP9Decoder:()Z
        //   218: istore_2       
        //   219: aload_0        
        //   220: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.allowVP9:Z
        //   223: ifeq            230
        //   226: iload_2        
        //   227: ifne            236
        //   230: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.forceVP9:Z
        //   233: ifeq            289
        //   236: aload           6
        //   238: ldc             "vp9-profile0-L21-dash-cenc"
        //   240: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   243: pop            
        //   244: aload           6
        //   246: ldc             "vp9-profile0-L30-dash-cenc"
        //   248: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   251: pop            
        //   252: aload           5
        //   254: invokevirtual   com/netflix/mediaclient/media/VideoResolutionRange.getMaxHeight:()I
        //   257: sipush          720
        //   260: if_icmplt       281
        //   263: iload_1        
        //   264: ifeq            281
        //   267: invokestatic    com/netflix/mediaclient/service/player/exoplayback/ExoVideoCodecSelector.isHasSecureVP9Decoder:()Z
        //   270: ifeq            281
        //   273: aload           6
        //   275: ldc             "vp9-profile0-L31-dash-cenc"
        //   277: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   280: pop            
        //   281: ldc             "nf_msl_volley_FetchManifestsRequest"
        //   283: ldc             "device supports VP9"
        //   285: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   288: pop            
        //   289: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   292: ifeq            333
        //   295: ldc             "nf_msl_volley_FetchManifestsRequest"
        //   297: new             Ljava/lang/StringBuilder;
        //   300: dup            
        //   301: invokespecial   java/lang/StringBuilder.<init>:()V
        //   304: ldc             "quality "
        //   306: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: aload_0        
        //   310: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mVideoQuality:Lcom/netflix/mediaclient/servicemgr/interface_/offline/DownloadVideoQuality;
        //   313: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   316: ldc             ", format "
        //   318: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   321: aload           6
        //   323: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   326: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   329: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   332: pop            
        //   333: aload_0        
        //   334: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mConfig:Lcom/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface;
        //   337: invokeinterface com/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface.isDolbyDigitalPlus51Supported:()Z
        //   342: istore_2       
        //   343: aload_0        
        //   344: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mConfig:Lcom/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface;
        //   347: invokeinterface com/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface.isDolbyDigitalPlus20Supported:()Z
        //   352: istore_3       
        //   353: aload           6
        //   355: ldc             "heaac-2-dash"
        //   357: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   360: pop            
        //   361: iload_3        
        //   362: ifeq            373
        //   365: aload           6
        //   367: ldc             "ddplus-2.0-dash"
        //   369: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   372: pop            
        //   373: iload_2        
        //   374: ifeq            385
        //   377: aload           6
        //   379: ldc             "ddplus-5.1-dash"
        //   381: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   384: pop            
        //   385: aload           6
        //   387: ldc             "simplesdh"
        //   389: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   392: ldc             "dfxp-ls-sdh"
        //   394: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   397: ldc             "nflx-cmisc"
        //   399: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   402: pop            
        //   403: aload           6
        //   405: ldc             "BIF320"
        //   407: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   410: pop            
        //   411: aload           4
        //   413: ldc             "profiles"
        //   415: aload           6
        //   417: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   420: pop            
        //   421: aload           4
        //   423: ldc             "method"
        //   425: aload_0        
        //   426: invokespecial   com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.getManifestMethodName:()Ljava/lang/String;
        //   429: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   432: pop            
        //   433: aload_0        
        //   434: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mFalvor:Lcom/netflix/mediaclient/service/player/bladerunnerclient/IBladeRunnerClient$ManifestRequestFlavor;
        //   437: ifnull          455
        //   440: aload           4
        //   442: ldc             "flavor"
        //   444: aload_0        
        //   445: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mFalvor:Lcom/netflix/mediaclient/service/player/bladerunnerclient/IBladeRunnerClient$ManifestRequestFlavor;
        //   448: invokevirtual   com/netflix/mediaclient/service/player/bladerunnerclient/IBladeRunnerClient$ManifestRequestFlavor.getValue:()Ljava/lang/String;
        //   451: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   454: pop            
        //   455: getstatic       com/netflix/mediaclient/service/player/bladerunnerclient/IBladeRunnerClient$ManifestType.OFFLINE:Lcom/netflix/mediaclient/service/player/bladerunnerclient/IBladeRunnerClient$ManifestType;
        //   458: aload_0        
        //   459: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mType:Lcom/netflix/mediaclient/service/player/bladerunnerclient/IBladeRunnerClient$ManifestType;
        //   462: if_acmpne       547
        //   465: aload           4
        //   467: ldc             "type"
        //   469: aload_0        
        //   470: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mType:Lcom/netflix/mediaclient/service/player/bladerunnerclient/IBladeRunnerClient$ManifestType;
        //   473: invokevirtual   com/netflix/mediaclient/service/player/bladerunnerclient/IBladeRunnerClient$ManifestType.getValue:()Ljava/lang/String;
        //   476: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   479: pop            
        //   480: aload           4
        //   482: ldc             "downloadQuality"
        //   484: aload_0        
        //   485: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mVideoQuality:Lcom/netflix/mediaclient/servicemgr/interface_/offline/DownloadVideoQuality;
        //   488: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   491: pop            
        //   492: aload_0        
        //   493: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mOxid:Ljava/lang/String;
        //   496: invokestatic    junit/framework/Assert.assertNotNull:(Ljava/lang/Object;)V
        //   499: aload_0        
        //   500: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mDxid:Ljava/lang/String;
        //   503: invokestatic    junit/framework/Assert.assertNotNull:(Ljava/lang/Object;)V
        //   506: aload           4
        //   508: ldc             "oxid"
        //   510: aload_0        
        //   511: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mOxid:Ljava/lang/String;
        //   514: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   517: pop            
        //   518: aload           4
        //   520: ldc             "dxid"
        //   522: aload_0        
        //   523: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mDxid:Ljava/lang/String;
        //   526: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   529: pop            
        //   530: aload           4
        //   532: ldc             "preferAssistiveAudio"
        //   534: aload_0        
        //   535: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mConfig:Lcom/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface;
        //   538: invokeinterface com/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface.isAssistiveAudioEnabled:()Z
        //   543: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   546: pop            
        //   547: aload           4
        //   549: ldc_w           "useHttpsStreams"
        //   552: iconst_0       
        //   553: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   556: pop            
        //   557: aload           4
        //   559: ldc_w           "drmType"
        //   562: ldc_w           "widevine"
        //   565: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   568: pop            
        //   569: aload           4
        //   571: ldc_w           "uiversion"
        //   574: aload_0        
        //   575: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mUiversion:Ljava/lang/String;
        //   578: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   581: pop            
        //   582: aload           4
        //   584: ldc_w           "sdk"
        //   587: ldc_w           "4.1"
        //   590: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   593: pop            
        //   594: aload           4
        //   596: ldc_w           "platform"
        //   599: ldc_w           "android"
        //   602: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   605: pop            
        //   606: aload           4
        //   608: ldc_w           "application"
        //   611: ldc_w           "android"
        //   614: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   617: pop            
        //   618: aload           4
        //   620: ldc_w           "viewableIds"
        //   623: new             Lorg/json/JSONArray;
        //   626: dup            
        //   627: aload_0        
        //   628: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mPlayableIds:[Ljava/lang/String;
        //   631: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   634: invokespecial   org/json/JSONArray.<init>:(Ljava/util/Collection;)V
        //   637: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   640: pop            
        //   641: new             Lorg/json/JSONObject;
        //   644: dup            
        //   645: invokespecial   org/json/JSONObject.<init>:()V
        //   648: astore          6
        //   650: aload_0        
        //   651: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mUser:Lcom/netflix/mediaclient/service/ServiceAgent$UserAgentInterface;
        //   654: invokeinterface com/netflix/mediaclient/service/ServiceAgent$UserAgentInterface.getCurrentProfile:()Lcom/netflix/mediaclient/servicemgr/interface_/user/UserProfile;
        //   659: invokeinterface com/netflix/mediaclient/servicemgr/interface_/user/UserProfile.getLanguages:()[Ljava/lang/String;
        //   664: astore          5
        //   666: aload_0        
        //   667: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mUser:Lcom/netflix/mediaclient/service/ServiceAgent$UserAgentInterface;
        //   670: invokeinterface com/netflix/mediaclient/service/ServiceAgent$UserAgentInterface.getCurrentAppLocale:()Lcom/netflix/mediaclient/util/l10n/UserLocale;
        //   675: invokevirtual   com/netflix/mediaclient/util/l10n/UserLocale.getRaw:()Ljava/lang/String;
        //   678: astore          7
        //   680: aload           6
        //   682: ldc_w           "appselectedlanguages"
        //   685: new             Lorg/json/JSONArray;
        //   688: dup            
        //   689: aload           5
        //   691: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   694: invokespecial   org/json/JSONArray.<init>:(Ljava/util/Collection;)V
        //   697: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   700: pop            
        //   701: aload           6
        //   703: ldc_w           "platformselectedlanguages"
        //   706: new             Lorg/json/JSONArray;
        //   709: dup            
        //   710: iconst_1       
        //   711: anewarray       Ljava/lang/String;
        //   714: dup            
        //   715: iconst_0       
        //   716: aload           7
        //   718: aastore        
        //   719: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   722: invokespecial   org/json/JSONArray.<init>:(Ljava/util/Collection;)V
        //   725: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   728: pop            
        //   729: aload_0        
        //   730: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mVersion:I
        //   733: iconst_2       
        //   734: if_icmplt       848
        //   737: new             Lorg/json/JSONObject;
        //   740: dup            
        //   741: invokespecial   org/json/JSONObject.<init>:()V
        //   744: astore          5
        //   746: aload           5
        //   748: ldc_w           "version"
        //   751: aload_0        
        //   752: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.mVersion:I
        //   755: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   758: pop            
        //   759: aload           5
        //   761: ldc_w           "params"
        //   764: aload           4
        //   766: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   769: pop            
        //   770: aload           5
        //   772: ldc_w           "url"
        //   775: aload_0        
        //   776: invokespecial   com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.buildManifestUrl:()Ljava/lang/String;
        //   779: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   782: pop            
        //   783: aload           5
        //   785: ldc_w           "preferredlanguages"
        //   788: aload           6
        //   790: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   793: pop            
        //   794: aload           5
        //   796: astore          4
        //   798: aload           4
        //   800: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   803: areturn        
        //   804: iconst_0       
        //   805: istore_1       
        //   806: goto            37
        //   809: invokestatic    com/netflix/mediaclient/service/player/exoplayback/ExoVideoCodecSelector.isHasHEVCHardwareDecoder:()Z
        //   812: istore_2       
        //   813: goto            141
        //   816: aload_0        
        //   817: getfield        com/netflix/mediaclient/service/player/bladerunnerclient/ManifestRequestParamBuilder.allowHEVC:Z
        //   820: ifeq            827
        //   823: iload_2        
        //   824: ifne            835
        //   827: iload_3        
        //   828: istore_2       
        //   829: invokestatic    com/netflix/mediaclient/service/player/exoplayback/ExoVideoCodecSelector.isSupportVP9:()Z
        //   832: ifne            219
        //   835: iload_3        
        //   836: istore_2       
        //   837: invokestatic    com/netflix/mediaclient/service/player/exoplayback/ExoVideoCodecSelector.isHasVP9HardwareDecoder:()Z
        //   840: ifne            219
        //   843: iconst_0       
        //   844: istore_2       
        //   845: goto            219
        //   848: aload           4
        //   850: ldc_w           "preferredlanguages"
        //   853: aload           6
        //   855: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   858: pop            
        //   859: goto            798
        //   862: astore          5
        //   864: ldc             "nf_msl_volley_FetchManifestsRequest"
        //   866: aload           5
        //   868: ldc_w           "error creating manifest params"
        //   871: iconst_0       
        //   872: anewarray       Ljava/lang/Object;
        //   875: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)I
        //   878: pop            
        //   879: goto            798
        //   882: astore          6
        //   884: aload           5
        //   886: astore          4
        //   888: aload           6
        //   890: astore          5
        //   892: goto            864
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  11     35     862    864    Ljava/lang/Exception;
        //  37     82     862    864    Ljava/lang/Exception;
        //  86     102    862    864    Ljava/lang/Exception;
        //  102    113    862    864    Ljava/lang/Exception;
        //  117    133    862    864    Ljava/lang/Exception;
        //  137    141    862    864    Ljava/lang/Exception;
        //  141    148    862    864    Ljava/lang/Exception;
        //  152    158    862    864    Ljava/lang/Exception;
        //  158    185    862    864    Ljava/lang/Exception;
        //  189    203    862    864    Ljava/lang/Exception;
        //  203    211    862    864    Ljava/lang/Exception;
        //  215    219    862    864    Ljava/lang/Exception;
        //  219    226    862    864    Ljava/lang/Exception;
        //  230    236    862    864    Ljava/lang/Exception;
        //  236    263    862    864    Ljava/lang/Exception;
        //  267    281    862    864    Ljava/lang/Exception;
        //  281    289    862    864    Ljava/lang/Exception;
        //  289    333    862    864    Ljava/lang/Exception;
        //  333    361    862    864    Ljava/lang/Exception;
        //  365    373    862    864    Ljava/lang/Exception;
        //  377    385    862    864    Ljava/lang/Exception;
        //  385    455    862    864    Ljava/lang/Exception;
        //  455    547    862    864    Ljava/lang/Exception;
        //  547    746    862    864    Ljava/lang/Exception;
        //  746    794    882    895    Ljava/lang/Exception;
        //  809    813    862    864    Ljava/lang/Exception;
        //  816    823    862    864    Ljava/lang/Exception;
        //  829    835    862    864    Ljava/lang/Exception;
        //  837    843    862    864    Ljava/lang/Exception;
        //  848    859    862    864    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0798:
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
    
    ManifestRequestParamBuilder downaloadVideoQuality(final DownloadVideoQuality mVideoQuality) {
        this.mVideoQuality = mVideoQuality;
        return this;
    }
    
    ManifestRequestParamBuilder flavor(final IBladeRunnerClient$ManifestRequestFlavor mFalvor) {
        this.mFalvor = mFalvor;
        return this;
    }
    
    ManifestRequestParamBuilder playableIds(final String[] mPlayableIds) {
        this.mPlayableIds = mPlayableIds;
        return this;
    }
    
    ManifestRequestParamBuilder setBladerunnerVersion(final int mVersion) {
        this.mVersion = mVersion;
        return this;
    }
    
    ManifestRequestParamBuilder setOfflineIds(final String mOxid, final String mDxid) {
        this.mOxid = mOxid;
        this.mDxid = mDxid;
        return this;
    }
    
    ManifestRequestParamBuilder type(final IBladeRunnerClient$ManifestType mType) {
        this.mType = mType;
        return this;
    }
    
    ManifestRequestParamBuilder uiversion(final String mUiversion) {
        this.mUiversion = mUiversion;
        return this;
    }
}
