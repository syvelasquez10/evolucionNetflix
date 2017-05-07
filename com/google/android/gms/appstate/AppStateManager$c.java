// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class AppStateManager$c extends AppStateManager$a<AppStateManager$StateListResult>
{
    public AppStateManager$StateListResult h(final Status status) {
        return new AppStateManager$c$1(this, status);
    }
}
