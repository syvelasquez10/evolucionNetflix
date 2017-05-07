// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View$OnClickListener;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class StandardSlidingMenu$FetchGenresCallback extends LoggingManagerCallback
{
    final /* synthetic */ StandardSlidingMenu this$0;
    
    public StandardSlidingMenu$FetchGenresCallback(final StandardSlidingMenu this$0) {
        this.this$0 = this$0;
        super("StandardSlidingMenu");
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
        super.onGenreListsFetched(list, status);
        if (status.isError()) {
            Log.w("StandardSlidingMenu", "Invalid status code for genres fetch");
            this.this$0.showGenreErrorView();
            return;
        }
        if (list == null || list.size() < 1) {
            Log.v("StandardSlidingMenu", "No genres in response");
            this.this$0.showGenreErrorView();
            String value;
            if (list == null) {
                value = "null";
            }
            else {
                value = String.valueOf(list.size());
            }
            ErrorLoggingManager.logHandledException("SPY-7985 - GenresListAdapter got null or empty genres list: " + value);
            return;
        }
        this.this$0.updateGenres(list);
    }
}
