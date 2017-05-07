// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import android.text.TextUtils$TruncateAt;
import com.netflix.mediaclient.android.widget.AutoResizeTextView;
import com.netflix.mediaclient.android.widget.StrokeTextView;
import android.view.ViewTreeObserver;
import com.netflix.mediaclient.service.player.subtitles.DoubleLength;
import com.netflix.mediaclient.service.player.subtitles.TextStyle;
import com.netflix.mediaclient.service.player.subtitles.ColorMapping;
import android.graphics.Rect;
import com.netflix.mediaclient.util.SubtitleUtils$Margins;
import java.util.Comparator;
import android.app.Activity;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.Iterator;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.SubtitleTextNode;
import android.util.Pair;
import com.netflix.mediaclient.service.player.subtitles.HorizontalAlignment;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.VerticalAlignment;
import com.netflix.mediaclient.util.SubtitleUtils;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.Region;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.TextView;
import android.view.View;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import android.os.Handler;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.widget.RelativeLayout;
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
