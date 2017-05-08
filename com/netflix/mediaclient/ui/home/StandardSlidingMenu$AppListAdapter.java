// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.widget.ListAdapter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.net.Uri;
import android.content.Intent;
import java.util.ArrayList;
import android.os.Build$VERSION;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.iris.notifications.NotificationsFrag$NotificationsListStatusListener;
import android.view.ViewStub;
import com.netflix.mediaclient.Log;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View$OnClickListener;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.annotation.SuppressLint;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import java.util.List;
import android.widget.BaseAdapter;

class StandardSlidingMenu$AppListAdapter extends BaseAdapter
{
    private final List<StandardSlidingMenu$AppAction> actions;
    final /* synthetic */ StandardSlidingMenu this$0;
    
    public StandardSlidingMenu$AppListAdapter(final StandardSlidingMenu this$0, final List<StandardSlidingMenu$AppAction> actions) {
        this.this$0 = this$0;
        this.actions = actions;
    }
    
    public int getCount() {
        return this.actions.size();
    }
    
    public StandardSlidingMenu$AppAction getItem(final int n) {
        return this.actions.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.this$0.activity.getLayoutInflater().inflate(2130903279, (ViewGroup)null);
            inflate.setTag((Object)new StandardSlidingMenu$GenreRowHolder((TextView)inflate.findViewById(2131690036), inflate.findViewById(2131690037)));
        }
        final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder = (StandardSlidingMenu$GenreRowHolder)inflate.getTag();
        standardSlidingMenu$GenreRowHolder.tv.setText((CharSequence)this.getItem(n).text);
        this.this$0.removeGenreSelectionStyle(standardSlidingMenu$GenreRowHolder);
        return inflate;
    }
}
