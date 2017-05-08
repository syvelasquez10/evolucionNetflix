// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.util.ViewUtils;
import android.app.FragmentTransaction;
import com.netflix.mediaclient.ui.details.RoleDetailsFrag;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.falkor.CachedModelProxy$FetchTurboCollectionVideosTask;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.app.Fragment;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.FragmentManager;
import android.app.DialogFragment;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import java.util.Collection;
import android.view.animation.Animation;
import android.view.View$OnClickListener;
import android.view.animation.AlphaAnimation;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class MiniPlayerControlsFrag$3 extends LoggingManagerCallback
{
    final /* synthetic */ MiniPlayerControlsFrag this$0;
    
    MiniPlayerControlsFrag$3(final MiniPlayerControlsFrag this$0, final String s) {
        this.this$0 = this$0;
        super(s);
    }
    
    @Override
    public void onVideosFetched(final List<Video> items, final Status status) {
        super.onVideosFetched(items, status);
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        ((Animation)alphaAnimation).setDuration(600L);
        this.this$0.relatedGridGroup.setVisibility(0);
        this.this$0.views.rotateCaretTo(90);
        this.this$0.getNetflixActivity().setSlidingEnabled(false);
        this.this$0.getSlidingPanelDragView().setOnClickListener((View$OnClickListener)new MiniPlayerControlsFrag$3$1(this));
        this.this$0.relatedGridGroup.startAnimation((Animation)alphaAnimation);
        this.this$0.relatedAdapter.setItems(items);
    }
}
