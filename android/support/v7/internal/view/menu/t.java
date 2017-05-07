// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import android.view.ActionProvider;
import android.support.v4.internal.view.SupportMenuItem;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(16)
class t extends o
{
    t(final Context context, final SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }
    
    @Override
    p a(final ActionProvider actionProvider) {
        return new u(this, this.a, actionProvider);
    }
}
