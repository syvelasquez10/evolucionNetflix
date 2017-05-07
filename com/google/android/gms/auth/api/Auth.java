// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.auth.api.credentials.internal.zzc;
import com.google.android.gms.internal.zzkm;
import com.google.android.gms.auth.api.consent.zza;
import com.google.android.gms.auth.api.signin.zzd;
import com.google.android.gms.auth.api.signin.zzf;
import com.google.android.gms.internal.zzjz;
import com.google.android.gms.auth.api.signin.GoogleSignInConfig;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.auth.api.signin.internal.zzb;
import com.google.android.gms.auth.api.signin.internal.zzg;
import com.google.android.gms.internal.zzkb;
import com.google.android.gms.auth.api.credentials.internal.zze;
import com.google.android.gms.internal.zzki;
import com.google.android.gms.common.api.Api$zzc;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.api.Api;

public final class Auth
{
    public static final Api<Auth$AuthCredentialsOptions> CREDENTIALS_API;
    public static final CredentialsApi CredentialsApi;
    public static final Api<Auth$zza> PROXY_API;
    public static final ProxyApi ProxyApi;
    public static final Api$zzc<zzki> zzRE;
    public static final Api$zzc<zze> zzRF;
    public static final Api$zzc<zzkb> zzRG;
    public static final Api$zzc<zzg> zzRH;
    public static final Api$zzc<zzb> zzRI;
    public static final Api$zzc<zzkf> zzRJ;
    private static final Api$zza<zzki, Auth$zza> zzRK;
    private static final Api$zza<zze, Auth$AuthCredentialsOptions> zzRL;
    private static final Api$zza<zzkb, Api$ApiOptions$NoOptions> zzRM;
    private static final Api$zza<zzkf, Api$ApiOptions$NoOptions> zzRN;
    private static final Api$zza<zzg, com.google.android.gms.auth.api.signin.zzg> zzRO;
    private static final Api$zza<zzb, GoogleSignInConfig> zzRP;
    public static final Api<com.google.android.gms.auth.api.signin.zzg> zzRQ;
    public static final Api<GoogleSignInConfig> zzRR;
    public static final Api<Api$ApiOptions$NoOptions> zzRS;
    public static final Api<Api$ApiOptions$NoOptions> zzRT;
    public static final zzjz zzRU;
    public static final zzf zzRV;
    public static final zzd zzRW;
    public static final zza zzRX;
    
    static {
        zzRE = new Api$zzc<zzki>();
        zzRF = new Api$zzc<zze>();
        zzRG = new Api$zzc<zzkb>();
        zzRH = new Api$zzc<zzg>();
        zzRI = new Api$zzc<zzb>();
        zzRJ = new Api$zzc<zzkf>();
        zzRK = new Auth$1();
        zzRL = new Auth$2();
        zzRM = new Auth$3();
        zzRN = new Auth$4();
        zzRO = new Auth$5();
        zzRP = new Auth$6();
        PROXY_API = new Api<Auth$zza>("Auth.PROXY_API", (Api$zza<C, Auth$zza>)Auth.zzRK, (Api$zzc<C>)Auth.zzRE);
        CREDENTIALS_API = new Api<Auth$AuthCredentialsOptions>("Auth.CREDENTIALS_API", (Api$zza<C, Auth$AuthCredentialsOptions>)Auth.zzRL, (Api$zzc<C>)Auth.zzRF);
        zzRQ = new Api<com.google.android.gms.auth.api.signin.zzg>("Auth.SIGN_IN_API", (Api$zza<C, com.google.android.gms.auth.api.signin.zzg>)Auth.zzRO, (Api$zzc<C>)Auth.zzRH);
        zzRR = new Api<GoogleSignInConfig>("Auth.GOOGLE_SIGN_IN_API", (Api$zza<C, GoogleSignInConfig>)Auth.zzRP, (Api$zzc<C>)Auth.zzRI);
        zzRS = new Api<Api$ApiOptions$NoOptions>("Auth.ACCOUNT_STATUS_API", (Api$zza<C, Api$ApiOptions$NoOptions>)Auth.zzRM, (Api$zzc<C>)Auth.zzRG);
        zzRT = new Api<Api$ApiOptions$NoOptions>("Auth.CONSENT_API", (Api$zza<C, Api$ApiOptions$NoOptions>)Auth.zzRN, (Api$zzc<C>)Auth.zzRJ);
        ProxyApi = new zzkm();
        CredentialsApi = new zzc();
        zzRU = new zzka();
        zzRV = new com.google.android.gms.auth.api.signin.internal.zzf();
        zzRW = new com.google.android.gms.auth.api.signin.internal.zza();
        zzRX = new zzke();
    }
}
