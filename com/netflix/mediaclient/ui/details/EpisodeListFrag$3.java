// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import java.util.List;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.widget.AbsListView$OnScrollListener;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener;
import android.widget.TextView;
import com.netflix.mediaclient.android.app.Status;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.LinearLayout;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import android.app.Activity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.content.DialogInterface;
import android.widget.AbsListView;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.widget.AdapterView$OnItemSelectedListener;
import android.os.Build$VERSION;
import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.IntentFilter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class EpisodeListFrag$3 extends BroadcastReceiver
{
    final /* synthetic */ EpisodeListFrag this$0;
    
    EpisodeListFrag$3(final EpisodeListFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (!this.this$0.isDestroyed() && "com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH".equals(intent.getAction())) {
            final int n = intent.getIntExtra("curSeasonNumber", 1) - 1;
            final int selectedItemPosition = this.this$0.spinner.getSelectedItemPosition();
            if (n != selectedItemPosition) {
                if (Log.isLoggable("EpisodeListFrag", 2)) {
                    Log.v("EpisodeListFrag", "Notification is for season " + n + " but spinner set to season " + selectedItemPosition);
                }
            }
            else {
                final int n2 = intent.getIntExtra("curEpisodeNumber", 1) - 1;
                final int n3 = this.this$0.getHeaderViewsCount() + n2;
                if (Log.isLoggable("EpisodeListFrag", 2)) {
                    Log.v("EpisodeListFrag", "Episode index: " + n2 + ", setting current episode to: " + n3);
                }
                this.this$0.setItemChecked(n3, true);
                this.this$0.episodesAdapter.updateSeasonDetails((SeasonDetails)this.this$0.spinner.getItemAtPosition(selectedItemPosition));
                if (this.this$0.listView != null) {
                    this.this$0.listView.smoothScrollToPosition(n3);
                }
            }
        }
    }
}
