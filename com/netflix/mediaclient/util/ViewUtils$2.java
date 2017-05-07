// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.Context;
import android.widget.Toast;
import android.view.MenuItem;
import android.app.Activity;
import android.view.MenuItem$OnMenuItemClickListener;

final class ViewUtils$2 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ Activity val$activity;
    
    ViewUtils$2(final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        final StringBuilder sb = new StringBuilder("Clicked on: ");
        if (StringUtils.isNotEmpty(menuItem.getTitle())) {
            sb.append(menuItem.getTitle());
        }
        else {
            sb.append(menuItem.getClass().getSimpleName());
        }
        Toast.makeText((Context)this.val$activity, (CharSequence)sb.toString(), 0).show();
        return true;
    }
}
