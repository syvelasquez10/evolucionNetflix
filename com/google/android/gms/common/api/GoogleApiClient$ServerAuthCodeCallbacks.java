// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.Set;

public interface GoogleApiClient$ServerAuthCodeCallbacks
{
    GoogleApiClient$ServerAuthCodeCallbacks$CheckResult onCheckServerAuthorization(final String p0, final Set<Scope> p1);
    
    boolean onUploadServerAuthCode(final String p0, final String p1);
}
