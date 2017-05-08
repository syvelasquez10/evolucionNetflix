// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.android.app.Status;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Build$VERSION;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.widget.ListAdapter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import java.util.ArrayList;
import com.netflix.mediaclient.util.ViewUtils;
import java.util.List;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.iris.notifications.NotificationsFrag$NotificationsListStatusListener;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View$OnClickListener;
import android.view.ViewStub;
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.BroadcastReceiver;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;

class StandardSlidingMenu$9 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ StandardSlidingMenu this$0;
    
    StandardSlidingMenu$9(final StandardSlidingMenu this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (!this.this$0.mBlockedByUmaAlert) {
            HomeActivity.showGenreList(this.this$0.activity, this.this$0.genresAdapter.getItem(n - this.this$0.genresList.getHeaderViewsCount()));
            this.this$0.closeDrawersWithDelay();
        }
    }
}
