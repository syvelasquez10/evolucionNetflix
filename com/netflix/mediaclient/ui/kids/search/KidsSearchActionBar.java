// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.search;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.netflix.mediaclient.android.widget.AccessibilityRunnable;
import android.app.ActionBar;
import android.app.Activity;
import com.netflix.mediaclient.android.widget.SearchActionBar;

public class KidsSearchActionBar extends SearchActionBar
{
    public KidsSearchActionBar(final Activity activity, final ActionBar actionBar, final AccessibilityRunnable accessibilityRunnable) {
        super(activity, actionBar, accessibilityRunnable);
        actionBar.setBackgroundDrawable((Drawable)new ColorDrawable(activity.getResources().getColor(2131296356)));
        this.searchView.setQueryHint((CharSequence)this.getActivity().getString(2131492953));
    }
}
