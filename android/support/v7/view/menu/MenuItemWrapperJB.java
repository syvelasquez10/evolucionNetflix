// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.ActionProvider;
import android.support.v4.internal.view.SupportMenuItem;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(16)
class MenuItemWrapperJB extends MenuItemWrapperICS
{
    MenuItemWrapperJB(final Context context, final SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }
    
    @Override
    MenuItemWrapperICS$ActionProviderWrapper createActionProviderWrapper(final ActionProvider actionProvider) {
        return new MenuItemWrapperJB$ActionProviderWrapperJB(this, this.mContext, actionProvider);
    }
}
