// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.x;
import com.google.android.gms.drive.internal.t;
import com.google.android.gms.drive.internal.o;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.internal.q;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public final class Drive
{
    public static final Api<Api$ApiOptions$NoOptions> API;
    public static final Api$c<q> CU;
    public static final DriveApi DriveApi;
    public static final Scope MU;
    public static final Scope MV;
    public static final Api<Drive$b> MW;
    public static final b MX;
    public static final e MY;
    public static final Scope SCOPE_APPFOLDER;
    public static final Scope SCOPE_FILE;
    
    static {
        CU = new Api$c<q>();
        SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
        SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
        MU = new Scope("https://www.googleapis.com/auth/drive");
        MV = new Scope("https://www.googleapis.com/auth/drive.apps");
        API = new Api<Api$ApiOptions$NoOptions>((Api$b<C, Api$ApiOptions$NoOptions>)new Drive$1(), (Api$c<C>)Drive.CU, new Scope[0]);
        MW = new Api<Drive$b>((Api$b<C, Drive$b>)new Drive$2(), (Api$c<C>)Drive.CU, new Scope[0]);
        DriveApi = new o();
        MX = new t();
        MY = new x();
    }
}
