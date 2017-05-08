// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.moments;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;
import java.util.Comparator;
import java.util.Collections;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.util.AudioUtils;
import android.content.Context;
import android.animation.ObjectAnimator;
import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import java.io.IOException;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import java.util.Map;
import android.media.SoundPool;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import java.util.List;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractiveMomentsModel;
import com.netflix.mediaclient.ui.iko.InteractiveMomentsManager;
import java.util.Iterator;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractiveMomentsModel$KongInteractiveMoment;

class KongInteractiveMomentsManager$3 implements Runnable
{
    final /* synthetic */ KongInteractiveMomentsManager this$0;
    
    KongInteractiveMomentsManager$3(final KongInteractiveMomentsManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        for (final KongInteractiveMomentsModel$KongInteractiveMoment kongInteractiveMomentsModel$KongInteractiveMoment : this.this$0.interactiveMoments) {
            kongInteractiveMomentsModel$KongInteractiveMoment.setImageBitmap(this.this$0.fetchImageFromCache(kongInteractiveMomentsModel$KongInteractiveMoment.getMomentImageUrl()));
            kongInteractiveMomentsModel$KongInteractiveMoment.setImageBackgroundBitmap(this.this$0.fetchImageFromCache(kongInteractiveMomentsModel$KongInteractiveMoment.getMomentAnimationImageUrl()));
            kongInteractiveMomentsModel$KongInteractiveMoment.setSfxSoundPoolId(this.this$0.loadSoundPoolVo(kongInteractiveMomentsModel$KongInteractiveMoment.getUnlockSfxSoundUrl()));
        }
    }
}
