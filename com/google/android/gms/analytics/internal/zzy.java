// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

public final class zzy
{
    public static zzy$zza<Boolean> zzMY;
    public static zzy$zza<Boolean> zzMZ;
    public static zzy$zza<Integer> zzNA;
    public static zzy$zza<Long> zzNB;
    public static zzy$zza<Integer> zzNC;
    public static zzy$zza<Integer> zzND;
    public static zzy$zza<Long> zzNE;
    public static zzy$zza<String> zzNF;
    public static zzy$zza<Integer> zzNG;
    public static zzy$zza<Boolean> zzNH;
    public static zzy$zza<Long> zzNI;
    public static zzy$zza<Long> zzNJ;
    public static zzy$zza<Long> zzNK;
    public static zzy$zza<Long> zzNL;
    public static zzy$zza<Long> zzNM;
    public static zzy$zza<Long> zzNN;
    public static zzy$zza<Long> zzNO;
    public static zzy$zza<String> zzNa;
    public static zzy$zza<Long> zzNb;
    public static zzy$zza<Float> zzNc;
    public static zzy$zza<Integer> zzNd;
    public static zzy$zza<Integer> zzNe;
    public static zzy$zza<Integer> zzNf;
    public static zzy$zza<Long> zzNg;
    public static zzy$zza<Long> zzNh;
    public static zzy$zza<Long> zzNi;
    public static zzy$zza<Long> zzNj;
    public static zzy$zza<Long> zzNk;
    public static zzy$zza<Long> zzNl;
    public static zzy$zza<Integer> zzNm;
    public static zzy$zza<Integer> zzNn;
    public static zzy$zza<String> zzNo;
    public static zzy$zza<String> zzNp;
    public static zzy$zza<String> zzNq;
    public static zzy$zza<String> zzNr;
    public static zzy$zza<Integer> zzNs;
    public static zzy$zza<String> zzNt;
    public static zzy$zza<String> zzNu;
    public static zzy$zza<Integer> zzNv;
    public static zzy$zza<Integer> zzNw;
    public static zzy$zza<Integer> zzNx;
    public static zzy$zza<Integer> zzNy;
    public static zzy$zza<String> zzNz;
    
    static {
        zzy.zzMY = zzy$zza.zzd("analytics.service_enabled", false);
        zzy.zzMZ = zzy$zza.zzd("analytics.service_client_enabled", true);
        zzy.zzNa = zzy$zza.zzd("analytics.log_tag", "GAv4", "GAv4-SVC");
        zzy.zzNb = zzy$zza.zzc("analytics.max_tokens", 60L);
        zzy.zzNc = zzy$zza.zza("analytics.tokens_per_sec", 0.5f);
        zzy.zzNd = zzy$zza.zza("analytics.max_stored_hits", 2000, 20000);
        zzy.zzNe = zzy$zza.zze("analytics.max_stored_hits_per_app", 2000);
        zzy.zzNf = zzy$zza.zze("analytics.max_stored_properties_per_app", 100);
        zzy.zzNg = zzy$zza.zza("analytics.local_dispatch_millis", 1800000L, 120000L);
        zzy.zzNh = zzy$zza.zza("analytics.initial_local_dispatch_millis", 5000L, 5000L);
        zzy.zzNi = zzy$zza.zzc("analytics.min_local_dispatch_millis", 120000L);
        zzy.zzNj = zzy$zza.zzc("analytics.max_local_dispatch_millis", 7200000L);
        zzy.zzNk = zzy$zza.zzc("analytics.dispatch_alarm_millis", 7200000L);
        zzy.zzNl = zzy$zza.zzc("analytics.max_dispatch_alarm_millis", 32400000L);
        zzy.zzNm = zzy$zza.zze("analytics.max_hits_per_dispatch", 20);
        zzy.zzNn = zzy$zza.zze("analytics.max_hits_per_batch", 20);
        zzy.zzNo = zzy$zza.zzn("analytics.insecure_host", "http://www.google-analytics.com");
        zzy.zzNp = zzy$zza.zzn("analytics.secure_host", "https://ssl.google-analytics.com");
        zzy.zzNq = zzy$zza.zzn("analytics.simple_endpoint", "/collect");
        zzy.zzNr = zzy$zza.zzn("analytics.batching_endpoint", "/batch");
        zzy.zzNs = zzy$zza.zze("analytics.max_get_length", 2036);
        zzy.zzNt = zzy$zza.zzd("analytics.batching_strategy.k", zzm.zzMD.name(), zzm.zzMD.name());
        zzy.zzNu = zzy$zza.zzn("analytics.compression_strategy.k", zzo.zzMK.name());
        zzy.zzNv = zzy$zza.zze("analytics.max_hits_per_request.k", 20);
        zzy.zzNw = zzy$zza.zze("analytics.max_hit_length.k", 8192);
        zzy.zzNx = zzy$zza.zze("analytics.max_post_length.k", 8192);
        zzy.zzNy = zzy$zza.zze("analytics.max_batch_post_length", 8192);
        zzy.zzNz = zzy$zza.zzn("analytics.fallback_responses.k", "404,502");
        zzy.zzNA = zzy$zza.zze("analytics.batch_retry_interval.seconds.k", 3600);
        zzy.zzNB = zzy$zza.zzc("analytics.service_monitor_interval", 86400000L);
        zzy.zzNC = zzy$zza.zze("analytics.http_connection.connect_timeout_millis", 60000);
        zzy.zzND = zzy$zza.zze("analytics.http_connection.read_timeout_millis", 61000);
        zzy.zzNE = zzy$zza.zzc("analytics.campaigns.time_limit", 86400000L);
        zzy.zzNF = zzy$zza.zzn("analytics.first_party_experiment_id", "");
        zzy.zzNG = zzy$zza.zze("analytics.first_party_experiment_variant", 0);
        zzy.zzNH = zzy$zza.zzd("analytics.test.disable_receiver", false);
        zzy.zzNI = zzy$zza.zza("analytics.service_client.idle_disconnect_millis", 10000L, 10000L);
        zzy.zzNJ = zzy$zza.zzc("analytics.service_client.connect_timeout_millis", 5000L);
        zzy.zzNK = zzy$zza.zzc("analytics.service_client.second_connect_delay_millis", 5000L);
        zzy.zzNL = zzy$zza.zzc("analytics.service_client.unexpected_reconnect_millis", 60000L);
        zzy.zzNM = zzy$zza.zzc("analytics.service_client.reconnect_throttle_millis", 1800000L);
        zzy.zzNN = zzy$zza.zzc("analytics.monitoring.sample_period_millis", 86400000L);
        zzy.zzNO = zzy$zza.zzc("analytics.initialization_warning_threshold", 5000L);
    }
}
