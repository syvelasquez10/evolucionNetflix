// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.internal.ib;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class AppStateManager$e extends AppStateManager$a<AppStateManager$StateResult>
{
    public AppStateManager$StateResult i(final Status status) {
        return e(status);
    }
}
