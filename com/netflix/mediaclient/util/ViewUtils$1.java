// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.Context;
import android.widget.Toast;
import android.view.View;
import android.app.Activity;
import android.view.View$OnClickListener;

final class ViewUtils$1 implements View$OnClickListener
{
    final /* synthetic */ Activity val$activity;
    
    ViewUtils$1(final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    public void onClick(final View view) {
        final StringBuilder sb = new StringBuilder("Clicked on: ");
        if (StringUtils.isNotEmpty(view.getContentDescription())) {
            sb.append(view.getContentDescription());
        }
        else {
            sb.append(view.getClass().getSimpleName());
        }
        Toast.makeText((Context)this.val$activity, (CharSequence)sb.toString(), 0).show();
    }
}
