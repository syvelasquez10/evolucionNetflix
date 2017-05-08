// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.service.error.ErrorDescriptor;
import android.content.Context;

public interface CryptoErrorHandler
{
    ErrorDescriptor handle(final Context p0, final Throwable p1);
}
