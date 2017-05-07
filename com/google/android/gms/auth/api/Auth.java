// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import com.google.android.gms.internal.zzjr;
import com.google.android.gms.auth.api.credentials.internal.zzc;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.auth.api.signin.internal.zzd;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.auth.api.credentials.internal.zze;
import com.google.android.gms.internal.zzjw;
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
    public static final Api$zzc<zzjw> zzQL;
    public static final Api$zzc<zze> zzQM;
    public static final Api$zzc<zzjs> zzQN;
    public static final Api$zzc<zzd> zzQO;
    private static final Api$zza<zzjw, Auth$zza> zzQP;
    private static final Api$zza<zze, Auth$AuthCredentialsOptions> zzQQ;
    private static final Api$zza<zzjs, Api$ApiOptions$NoOptions> zzQR;
    private static final Api$zza<zzd, com.google.android.gms.auth.api.signin.zze> zzQS;
    public static final Api<com.google.android.gms.auth.api.signin.zze> zzQT;
    public static final Api<Api$ApiOptions$NoOptions> zzQU;
    public static final zzjq zzQV;
    public static final com.google.android.gms.auth.api.signin.zzd zzQW;
    
    static {
        zzQL = new Api$zzc<zzjw>();
        zzQM = new Api$zzc<zze>();
        zzQN = new Api$zzc<zzjs>();
        zzQO = new Api$zzc<zzd>();
        zzQP = new Auth$1();
        zzQQ = new Auth$2();
        zzQR = new Auth$3();
        zzQS = new Auth$4();
        PROXY_API = new Api<Auth$zza>("Auth.PROXY_API", (Api$zza<C, Auth$zza>)Auth.zzQP, (Api$zzc<C>)Auth.zzQL);
        CREDENTIALS_API = new Api<Auth$AuthCredentialsOptions>("Auth.CREDENTIALS_API", (Api$zza<C, Auth$AuthCredentialsOptions>)Auth.zzQQ, (Api$zzc<C>)Auth.zzQM);
        zzQT = new Api<com.google.android.gms.auth.api.signin.zze>("Auth.SIGN_IN_API", (Api$zza<C, com.google.android.gms.auth.api.signin.zze>)Auth.zzQS, (Api$zzc<C>)Auth.zzQO);
        zzQU = new Api<Api$ApiOptions$NoOptions>("Auth.ACCOUNT_STATUS_API", (Api$zza<C, Api$ApiOptions$NoOptions>)Auth.zzQR, (Api$zzc<C>)Auth.zzQN);
        ProxyApi = new zzka();
        CredentialsApi = new zzc();
        zzQV = new zzjr();
        zzQW = new com.google.android.gms.auth.api.signin.internal.zzc();
    }
}
