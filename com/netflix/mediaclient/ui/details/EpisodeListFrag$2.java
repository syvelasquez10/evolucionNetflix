// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.Status;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.LinearLayout;
import com.netflix.mediaclient.util.ViewUtils;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import android.app.Activity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.content.DialogInterface;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.FrameLayout;
import android.widget.AdapterView$OnItemSelectedListener;
import android.os.Build$VERSION;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.IntentFilter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Handler;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.Log;

class EpisodeListFrag$2 implements Runnable
{
    final /* synthetic */ EpisodeListFrag this$0;
    
    EpisodeListFrag$2(final EpisodeListFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        int selection;
        if ((selection = this.this$0.currSeasonIndex) == -1) {
            selection = this.this$0.spinner.getSeasonIndexBySeasonNumber(this.this$0.showDetails.getCurrentSeasonNumber());
        }
        if (selection < 0) {
            Log.v("EpisodeListFrag", "No valid season index found");
            return;
        }
        if (Log.isLoggable("EpisodeListFrag", 2)) {
            Log.v("EpisodeListFrag", "Setting current season to: " + selection);
        }
        this.this$0.spinner.setSelection(selection);
    }
}
