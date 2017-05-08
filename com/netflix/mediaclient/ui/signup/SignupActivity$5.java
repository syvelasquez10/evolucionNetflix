// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class SignupActivity$5 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$5(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        this.this$0.reload(true);
        return true;
    }
}
