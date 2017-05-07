// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjf;
import com.google.android.gms.auth.api.credentials.internal.zzc;
import com.google.android.gms.internal.zzjm;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzjn;
import com.google.android.gms.internal.zzje;
import com.google.android.gms.auth.api.proxy.zza;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjg;
import com.google.android.gms.internal.zzjj;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.credentials.internal.CredentialsClientImpl;
import com.google.android.gms.common.api.Api$ClientKey;

public final class Auth
{
    public static final Api$ClientKey<CredentialsClientImpl> CLIENT_KEY_CREDENTIALS_API;
    public static final Api<Api$ApiOptions$NoOptions> CREDENTIALS_API;
    public static final CredentialsApi CredentialsApi;
    public static final Api$ClientKey<zzjj> zzOE;
    public static final Api$ClientKey<zzjg> zzOF;
    public static final Api$ClientKey<zzjq> zzOG;
    private static final Api$zza<zzjj, Auth$zza> zzOH;
    private static final Api$zza<CredentialsClientImpl, Api$ApiOptions$NoOptions> zzOI;
    private static final Api$zza<zzjg, Api$ApiOptions$NoOptions> zzOJ;
    private static final Api$zza<zzjq, Api$ApiOptions$NoOptions> zzOK;
    public static final Api<Auth$zza> zzOL;
    public static final Api<Api$ApiOptions$NoOptions> zzOM;
    public static final Api<Api$ApiOptions$NoOptions> zzON;
    public static final zza zzOO;
    public static final zzje zzOP;
    public static final zzjn zzOQ;
    
    static {
        zzOE = new Api$ClientKey<zzjj>();
        CLIENT_KEY_CREDENTIALS_API = new Api$ClientKey<CredentialsClientImpl>();
        zzOF = new Api$ClientKey<zzjg>();
        zzOG = new Api$ClientKey<zzjq>();
        zzOH = new Auth$1();
        zzOI = new Auth$2();
        zzOJ = new Auth$3();
        zzOK = new Auth$4();
        zzOL = new Api<Auth$zza>("Auth.PROXY_API", (Api$zza<C, Auth$zza>)Auth.zzOH, (Api$ClientKey<C>)Auth.zzOE, new Scope[0]);
        CREDENTIALS_API = new Api<Api$ApiOptions$NoOptions>("Auth.CREDENTIALS_API", (Api$zza<C, Api$ApiOptions$NoOptions>)Auth.zzOI, (Api$ClientKey<C>)Auth.CLIENT_KEY_CREDENTIALS_API, new Scope[0]);
        zzOM = new Api<Api$ApiOptions$NoOptions>("Auth.SIGN_IN_API", (Api$zza<C, Api$ApiOptions$NoOptions>)Auth.zzOK, (Api$ClientKey<C>)Auth.zzOG, new Scope[0]);
        zzON = new Api<Api$ApiOptions$NoOptions>("Auth.ACCOUNT_STATUS_API", (Api$zza<C, Api$ApiOptions$NoOptions>)Auth.zzOJ, (Api$ClientKey<C>)Auth.zzOF, new Scope[0]);
        zzOO = new zzjm();
        CredentialsApi = new zzc();
        zzOP = new zzjf();
        zzOQ = new zzjp();
    }
}
