// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;
import java.util.Comparator;
import java.util.Collections;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPImage;
import android.animation.Animator;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import org.json.JSONException;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import org.json.JSONObject;
import android.animation.TimeInterpolator;
import android.animation.Animator$AnimatorListener;
import android.animation.PropertyValuesHolder;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.ViewPropertyAnimator;
import android.app.Activity;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.iko.SoundPoolManager;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.graphics.Bitmap;
import com.netflix.mediaclient.ui.player.PlayPauseListener;
import android.view.View$OnTouchListener;
import android.widget.ProgressBar;
import android.view.animation.Interpolator;
import java.util.List;
import android.widget.RelativeLayout;
import android.os.Handler;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPMoment;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class WPInteractiveMomentsManager$10 implements View$OnClickListener
{
    final /* synthetic */ WPInteractiveMomentsManager this$0;
    
    WPInteractiveMomentsManager$10(final WPInteractiveMomentsManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "Close button onClick invoked ");
        }
        this.this$0.hide();
        this.this$0.reportCommandEvent(UIViewLogging$UIViewCommandName.backButton);
        this.this$0.reportMomentEnded(IClientLogging$CompletionReason.canceled, this.this$0.momentScreen.getCurrentState().name());
    }
}
