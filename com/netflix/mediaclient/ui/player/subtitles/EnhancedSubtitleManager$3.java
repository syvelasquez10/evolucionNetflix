// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import android.text.TextUtils$TruncateAt;
import com.netflix.mediaclient.android.widget.AutoResizeTextView;
import com.netflix.mediaclient.android.widget.StrokeTextView;
import android.view.ViewTreeObserver;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import com.netflix.mediaclient.service.player.subtitles.DoubleLength;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.service.player.subtitles.text.ColorMapping;
import com.netflix.mediaclient.service.player.subtitles.text.TextSubtitleParser;
import android.graphics.Rect;
import com.netflix.mediaclient.util.SubtitleUtils$Margins;
import java.util.ArrayList;
import java.util.Iterator;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.text.SubtitleTextNode;
import android.util.Pair;
import com.netflix.mediaclient.service.player.subtitles.text.HorizontalAlignment;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.text.VerticalAlignment;
import com.netflix.mediaclient.util.SubtitleUtils;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.subtitles.text.Region;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import com.netflix.mediaclient.service.player.subtitles.text.TextSubtitleBlock;
import java.util.HashMap;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.widget.TextView;
import java.util.List;
import android.widget.RelativeLayout;
import java.util.Map;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.LinearLayout;

class EnhancedSubtitleManager$3 implements Runnable
{
    final /* synthetic */ EnhancedSubtitleManager this$0;
    final /* synthetic */ boolean val$visibility;
    
    EnhancedSubtitleManager$3(final EnhancedSubtitleManager this$0, final boolean val$visibility) {
        this.this$0 = this$0;
        this.val$visibility = val$visibility;
    }
    
    @Override
    public void run() {
        this.this$0.setVisibilityForAllRegions(this.val$visibility);
    }
}
