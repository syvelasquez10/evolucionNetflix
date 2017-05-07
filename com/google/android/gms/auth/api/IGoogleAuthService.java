// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.IInterface;

public interface IGoogleAuthService extends IInterface
{
    void sendConnection(final IGoogleAuthApiCallbacks p0, final GoogleAuthApiRequest p1);
}
