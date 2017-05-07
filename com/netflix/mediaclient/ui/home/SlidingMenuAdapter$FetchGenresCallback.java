// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils$OnSwitchToKidsClickListener;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class SlidingMenuAdapter$FetchGenresCallback extends LoggingManagerCallback
{
    final /* synthetic */ SlidingMenuAdapter this$0;
    
    public SlidingMenuAdapter$FetchGenresCallback(final SlidingMenuAdapter this$0) {
        this.this$0 = this$0;
        super("SlidingMenuAdapter");
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
        super.onGenreListsFetched(list, status);
        if (status.isError()) {
            Log.w("SlidingMenuAdapter", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        if (list == null) {
            Log.v("SlidingMenuAdapter", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.updateGenres(list);
    }
}
