// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.Set;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.io.PrintWriter;
import java.io.FileDescriptor;

public interface Api$Client
{
    void connect(final GoogleApiClient$ConnectionProgressReportCallbacks p0);
    
    void disconnect();
    
    void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
    void getRemoteService(final IAccountAccessor p0, final Set<Scope> p1);
    
    boolean isConnected();
    
    boolean requiresSignIn();
    
    void validateAccount(final IAccountAccessor p0);
}
