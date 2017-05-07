// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Build$VERSION;
import com.netflix.mediaclient.util.ViewUtils;
import java.util.List;
import android.view.ViewStub;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.ui.social.notifications.KubrickSlidingMenuNotificationsFrag;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.annotation.SuppressLint;
import android.content.Context;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import android.view.View;
import android.view.View$OnClickListener;

class StandardSlidingMenu$4 implements View$OnClickListener
{
    final /* synthetic */ StandardSlidingMenu this$0;
    
    StandardSlidingMenu$4(final StandardSlidingMenu this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.activity.startActivity(ProfileSelectionActivity.createStartIntent((Context)this.this$0.activity));
    }
}
