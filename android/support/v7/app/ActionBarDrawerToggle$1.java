// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.MenuItem;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.support.v7.widget.Toolbar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import android.view.View;
import android.view.View$OnClickListener;

class ActionBarDrawerToggle$1 implements View$OnClickListener
{
    final /* synthetic */ ActionBarDrawerToggle this$0;
    
    ActionBarDrawerToggle$1(final ActionBarDrawerToggle this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.mDrawerIndicatorEnabled) {
            this.this$0.toggle();
        }
        else if (this.this$0.mToolbarNavigationClickListener != null) {
            this.this$0.mToolbarNavigationClickListener.onClick(view);
        }
    }
}
