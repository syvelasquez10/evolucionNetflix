// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import android.os.Bundle;
import android.os.IInterface;

public interface r extends IInterface
{
    Bundle a(final String p0, final Bundle p1);
    
    Bundle a(final String p0, final String p1, final Bundle p2);
    
    AccountChangeEventsResponse a(final AccountChangeEventsRequest p0);
}
