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
import com.netflix.mediaclient.util.ViewUtils;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.iris.notifications.NotificationsFrag$NotificationsListStatusListener;
import com.netflix.mediaclient.Log;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View$OnClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.view.ViewStub;
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import java.util.List;
import android.widget.BaseAdapter;

class StandardSlidingMenu$GenresListAdapter extends BaseAdapter
{
    private final List<GenreList> genres;
    final /* synthetic */ StandardSlidingMenu this$0;
    
    public StandardSlidingMenu$GenresListAdapter(final StandardSlidingMenu this$0, final List<GenreList> list) {
        this.this$0 = this$0;
        (this.genres = new ArrayList<GenreList>()).addAll(list);
    }
    
    public int getCount() {
        return this.genres.size();
    }
    
    public GenreList getItem(final int n) {
        return this.genres.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.this$0.activity.getLayoutInflater().inflate(2130903301, (ViewGroup)null);
            inflate.setTag((Object)new StandardSlidingMenu$GenreRowHolder((TextView)inflate.findViewById(2131690073), inflate.findViewById(2131690074)));
        }
        final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder = (StandardSlidingMenu$GenreRowHolder)inflate.getTag();
        final GenreList item = this.getItem(n);
        standardSlidingMenu$GenreRowHolder.tv.setText((CharSequence)item.getTitle());
        this.this$0.updateAdapterViews(standardSlidingMenu$GenreRowHolder, StringUtils.isNotEmpty(item.getId()) && item.getId().equals(this.this$0.selectedGenre.getId()));
        return inflate;
    }
}
