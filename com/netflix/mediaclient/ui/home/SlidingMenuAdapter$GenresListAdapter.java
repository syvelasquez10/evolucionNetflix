// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils$OnSwitchToKidsClickListener;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.ViewStub;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import java.util.List;
import android.widget.BaseAdapter;

class SlidingMenuAdapter$GenresListAdapter extends BaseAdapter
{
    private final List<GenreList> genres;
    final /* synthetic */ SlidingMenuAdapter this$0;
    
    public SlidingMenuAdapter$GenresListAdapter(final SlidingMenuAdapter this$0, final List<GenreList> genres) {
        this.this$0 = this$0;
        this.genres = genres;
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
            inflate = this.this$0.activity.getLayoutInflater().inflate(2130903098, (ViewGroup)null);
            inflate.setTag((Object)new SlidingMenuAdapter$Holder((TextView)inflate.findViewById(2131165399), inflate.findViewById(2131165400)));
        }
        final SlidingMenuAdapter$Holder slidingMenuAdapter$Holder = (SlidingMenuAdapter$Holder)inflate.getTag();
        final GenreList item = this.getItem(n);
        this.this$0.updateAdapterViews(slidingMenuAdapter$Holder, item);
        if (StringUtils.isNotEmpty(item.getId()) && item.getId().equals(this.this$0.selectedGenre.getId())) {
            this.this$0.applySelectionStyle(inflate);
            return inflate;
        }
        this.this$0.removeSelectionStyle(inflate);
        return inflate;
    }
}
