// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids;

import java.io.Serializable;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration$ActionBarNavType;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration$ScrollBehavior;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration;
import android.view.MenuItem;
import android.view.Menu;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import android.view.ViewConfiguration;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration$LolomoImageType;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ListView;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import android.view.View;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.app.Activity;
import android.view.View$OnClickListener;

public class KidsUtils$OnSwitchToKidsClickListener implements View$OnClickListener
{
    private final Activity activity;
    private final UIViewLogging$UIViewCommandName entryName;
    
    public KidsUtils$OnSwitchToKidsClickListener(final Activity activity, final UIViewLogging$UIViewCommandName entryName) {
        this.activity = activity;
        this.entryName = entryName;
    }
    
    public void onClick(final View view) {
        this.activity.startActivity(createSwitchToKidsIntent(this.activity, this.entryName));
    }
}
