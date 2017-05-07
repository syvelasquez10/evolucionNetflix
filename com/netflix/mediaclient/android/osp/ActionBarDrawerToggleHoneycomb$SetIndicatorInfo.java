// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.osp;

import android.view.View;
import android.view.ViewGroup;
import android.graphics.drawable.Drawable;
import android.app.ActionBar;
import android.app.Activity;
import android.widget.ImageView;
import java.lang.reflect.Method;

class ActionBarDrawerToggleHoneycomb$SetIndicatorInfo
{
    public Method setHomeActionContentDescription;
    public Method setHomeAsUpIndicator;
    public ImageView upIndicatorView;
    
    ActionBarDrawerToggleHoneycomb$SetIndicatorInfo(Activity activity) {
        try {
            this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
            this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
        }
        catch (NoSuchMethodException ex) {
            activity = (Activity)activity.findViewById(16908332);
            if (activity == null) {
                return;
            }
            final ViewGroup viewGroup = (ViewGroup)((View)activity).getParent();
            if (viewGroup.getChildCount() != 2) {
                return;
            }
            activity = (Activity)viewGroup.getChildAt(0);
            final Object child = viewGroup.getChildAt(1);
            if (((View)activity).getId() == 16908332) {
                activity = (Activity)child;
            }
            if (activity instanceof ImageView) {
                this.upIndicatorView = (ImageView)activity;
            }
        }
    }
}
