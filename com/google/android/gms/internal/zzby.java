// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import java.util.List;

@zzgk
public final class zzby
{
    public static final zzbu<String> zztV;
    public static final zzbu<String> zztW;
    public static final zzbu<Boolean> zztX;
    public static final zzbu<String> zztY;
    public static final zzbu<Boolean> zztZ;
    public static final zzbu<String> zzuA;
    public static final zzbu<Boolean> zzuB;
    public static final zzbu<String> zzuC;
    public static final zzbu<Boolean> zzuD;
    public static final zzbu<Boolean> zzuE;
    public static final zzbu<Integer> zzuF;
    public static final zzbu<Integer> zzuG;
    public static final zzbu<Integer> zzuH;
    public static final zzbu<Integer> zzuI;
    public static final zzbu<Integer> zzuJ;
    public static final zzbu<Boolean> zzuK;
    public static final zzbu<String> zzuL;
    public static final zzbu<Boolean> zzuM;
    public static final zzbu<Boolean> zzuN;
    public static final zzbu<Boolean> zzuO;
    public static final zzbu<String> zzuP;
    public static final zzbu<Boolean> zzuQ;
    public static final zzbu<Boolean> zzuR;
    public static final zzbu<Integer> zzuS;
    public static final zzbu<String> zzuT;
    public static final zzbu<String> zzuU;
    public static final zzbu<Boolean> zzuV;
    public static final zzbu<Boolean> zzuW;
    public static final zzbu<Boolean> zzuX;
    public static final zzbu<Long> zzuY;
    public static final zzbu<Boolean> zzuZ;
    public static final zzbu<String> zzua;
    public static final zzbu<Boolean> zzub;
    public static final zzbu<Boolean> zzuc;
    public static final zzbu<Boolean> zzud;
    public static final zzbu<String> zzue;
    public static final zzbu<String> zzuf;
    public static final zzbu<String> zzug;
    public static final zzbu<Boolean> zzuh;
    public static final zzbu<String> zzui;
    public static final zzbu<Integer> zzuj;
    public static final zzbu<Integer> zzuk;
    public static final zzbu<Integer> zzul;
    public static final zzbu<Long> zzum;
    public static final zzbu<Long> zzun;
    public static final zzbu<Integer> zzuo;
    public static final zzbu<Boolean> zzup;
    public static final zzbu<String> zzuq;
    public static final zzbu<Long> zzur;
    public static final zzbu<String> zzus;
    public static final zzbu<Boolean> zzut;
    public static final zzbu<String> zzuu;
    public static final zzbu<Boolean> zzuv;
    public static final zzbu<Boolean> zzuw;
    public static final zzbu<Boolean> zzux;
    public static final zzbu<String> zzuy;
    public static final zzbu<String> zzuz;
    public static final zzbu<Boolean> zzva;
    public static final zzbu<Boolean> zzvb;
    public static final zzbu<Boolean> zzvc;
    public static final zzbu<Boolean> zzvd;
    public static final zzbu<Long> zzve;
    
    static {
        zztV = zzbu.zzP("gads:sdk_core_experiment_id");
        zztW = zzbu.zzc("gads:sdk_core_location", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html");
        zztX = zzbu.zza("gads:request_builder:singleton_webview", Boolean.valueOf(false));
        zztY = zzbu.zzP("gads:request_builder:singleton_webview_experiment_id");
        zztZ = zzbu.zza("gads:sdk_use_dynamic_module", Boolean.valueOf(false));
        zzua = zzbu.zzP("gads:sdk_use_dynamic_module_experiment_id");
        zzub = zzbu.zza("gads:sdk_crash_report_enabled", Boolean.valueOf(false));
        zzuc = zzbu.zza("gads:sdk_crash_report_full_stacktrace", Boolean.valueOf(false));
        zzud = zzbu.zza("gads:block_autoclicks", Boolean.valueOf(false));
        zzue = zzbu.zzP("gads:block_autoclicks_experiment_id");
        zzuf = zzbu.zzQ("gads:prefetch:experiment_id");
        zzug = zzbu.zzP("gads:spam_app_context:experiment_id");
        zzuh = zzbu.zza("gads:spam_app_context:enabled", Boolean.valueOf(false));
        zzui = zzbu.zzP("gads:video_stream_cache:experiment_id");
        zzuj = zzbu.zza("gads:video_stream_cache:limit_count", 5);
        zzuk = zzbu.zza("gads:video_stream_cache:limit_space", 8388608);
        zzul = zzbu.zza("gads:video_stream_exo_cache:buffer_size", 8388608);
        zzum = zzbu.zzb("gads:video_stream_cache:limit_time_sec", 300L);
        zzun = zzbu.zzb("gads:video_stream_cache:notify_interval_millis", 1000L);
        zzuo = zzbu.zza("gads:video_stream_cache:connect_timeout_millis", 10000);
        zzup = zzbu.zza("gads:video:metric_reporting_enabled", Boolean.valueOf(false));
        zzuq = zzbu.zzc("gads:video:metric_frame_hash_times", "");
        zzur = zzbu.zzb("gads:video:metric_frame_hash_time_leniency", 500L);
        zzus = zzbu.zzQ("gads:spam_ad_id_decorator:experiment_id");
        zzut = zzbu.zza("gads:spam_ad_id_decorator:enabled", Boolean.valueOf(false));
        zzuu = zzbu.zzQ("gads:looper_for_gms_client:experiment_id");
        zzuv = zzbu.zza("gads:looper_for_gms_client:enabled", Boolean.valueOf(true));
        zzuw = zzbu.zza("gads:sw_ad_request_service:enabled", Boolean.valueOf(true));
        zzux = zzbu.zza("gads:sw_dynamite:enabled", Boolean.valueOf(true));
        zzuy = zzbu.zzc("gad:mraid:url_banner", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js");
        zzuz = zzbu.zzc("gad:mraid:url_expanded_banner", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js");
        zzuA = zzbu.zzc("gad:mraid:url_interstitial", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js");
        zzuB = zzbu.zza("gads:enabled_sdk_csi", Boolean.valueOf(false));
        zzuC = zzbu.zzc("gads:sdk_csi_server", "https://csi.gstatic.com/csi");
        zzuD = zzbu.zza("gads:sdk_csi_write_to_file", Boolean.valueOf(false));
        zzuE = zzbu.zza("gads:enable_content_fetching", Boolean.valueOf(true));
        zzuF = zzbu.zza("gads:content_length_weight", 1);
        zzuG = zzbu.zza("gads:content_age_weight", 1);
        zzuH = zzbu.zza("gads:min_content_len", 11);
        zzuI = zzbu.zza("gads:fingerprint_number", 10);
        zzuJ = zzbu.zza("gads:sleep_sec", 10);
        zzuK = zzbu.zza("gad:app_index_enabled", Boolean.valueOf(true));
        zzuL = zzbu.zzP("gads:kitkat_interstitial_workaround:experiment_id");
        zzuM = zzbu.zza("gads:kitkat_interstitial_workaround:enabled", Boolean.valueOf(true));
        zzuN = zzbu.zza("gads:interstitial_follow_url", Boolean.valueOf(true));
        zzuO = zzbu.zza("gads:interstitial_follow_url:register_click", Boolean.valueOf(true));
        zzuP = zzbu.zzP("gads:interstitial_follow_url:experiment_id");
        zzuQ = zzbu.zza("gads:analytics_enabled", Boolean.valueOf(true));
        zzuR = zzbu.zza("gads:ad_key_enabled", Boolean.valueOf(false));
        zzuS = zzbu.zza("gads:webview_cache_version", 0);
        zzuT = zzbu.zzQ("gads:pan:experiment_id");
        zzuU = zzbu.zzc("gads:native:engine_url", "//googleads.g.doubleclick.net/mads/static/mad/sdk/native/native_ads.html");
        zzuV = zzbu.zza("gads:ad_manager_creator:enabled", Boolean.valueOf(true));
        zzuW = zzbu.zza("gads:log:verbose_enabled", Boolean.valueOf(false));
        zzuX = zzbu.zza("gads:device_info_caching:enabled", Boolean.valueOf(true));
        zzuY = zzbu.zzb("gads:device_info_caching_expiry_ms:expiry", 300000L);
        zzuZ = zzbu.zza("gads:gen204_signals:enabled", Boolean.valueOf(false));
        zzva = zzbu.zza("gads:webview:error_reporting_enabled", Boolean.valueOf(false));
        zzvb = zzbu.zza("gads:adid_reporting:enabled", Boolean.valueOf(false));
        zzvc = zzbu.zza("gads:request_pkg:enabled", Boolean.valueOf(true));
        zzvd = zzbu.zza("gads:gmsg:disable_back_button:enabled", Boolean.valueOf(false));
        zzve = zzbu.zzb("gads:network:cache_prediction_duration_s", 300L);
    }
    
    public static List<String> zzde() {
        return zzp.zzbF().zzde();
    }
}
