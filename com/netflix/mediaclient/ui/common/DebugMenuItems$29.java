// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.SubMenu;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.util.net.CronetHttpURLConnectionFactory;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.advisory.ContentAdvisory;
import com.netflix.model.leafs.advisory.ExpiringContentAdvisory;
import com.netflix.model.leafs.advisory.Advisory;
import com.netflix.mediaclient.android.widget.advisor.Advisor;
import com.netflix.model.leafs.advisory.ProductPlacementAdvisory;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$29 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    
    DebugMenuItems$29(final DebugMenuItems this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        Advisor.make(this.this$0.activity, (Advisory)new ProductPlacementAdvisory()).withMessage("This is the main message!").withSecondaryMessage("This is the secondary message!!!").withDelay(0.0f).forDuration(3.0f).withAnimation(2130968595).show();
        Advisor.make(this.this$0.activity, (Advisory)new ExpiringContentAdvisory()).withMessage("This is the main message2! This is the main message2!").withSecondaryMessage("This is the secondary message2!!! This is the secondary message2!!! ").withDelay(0.0f).forDuration(3.0f).withAnimation(2130968595).show();
        Advisor.make(this.this$0.activity, (Advisory)new ContentAdvisory()).withMessage("This is the main message3!").withSecondaryMessage("This is the secondary message3!!!").withDelay(0.0f).forDuration(3.0f).withAnimation(2130968595).show();
        return true;
    }
}
