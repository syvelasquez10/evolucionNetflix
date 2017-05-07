// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;
import android.view.View;
import android.graphics.Rect;
import android.widget.TextView;
import android.widget.LinearLayout;

public class LastTime extends PlayerSection
{
    private static final int CURRENT_TIME_BOTTOM_MARGIN_REGULAR_PHONE = 10;
    private static final int CURRENT_TIME_BOTTOM_MARGIN_TABLET_PRESSED = 40;
    protected static final String TAG = "screen";
    private static final int TIME_TEXT_PADDING = 18;
    protected LinearLayout lastTime;
    protected TextView lastTimeLabel;
    
    public LastTime(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.lastTime = (LinearLayout)playerActivity.findViewById(2131165517);
        this.lastTimeLabel = (TextView)playerActivity.findViewById(2131165518);
    }
    
    private float calculateAlpha(final Rect rect, final Rect rect2) {
        final Rect intersect = intersect(rect, rect2);
        float n;
        if (intersect == null) {
            n = 1.0f;
        }
        else {
            final float n2 = intersect.width();
            final float n3 = rect.width();
            final boolean seekForward = this.context.getState().isSeekForward();
            final boolean intersectWithMiddle = this.intersectWithMiddle(rect, rect2, seekForward);
            final boolean intersectWithEdge = this.intersectWithEdge(rect, rect2, seekForward);
            float n5;
            final float n4 = n5 = 1.0f - 2.0f * n2 / n3;
            if (intersectWithMiddle) {
                if (intersectWithEdge) {
                    n5 = n4;
                }
                else {
                    n5 = 0.0f;
                }
            }
            float n6 = n5;
            if (n5 > 1.0f) {
                n6 = 1.0f;
            }
            n = n6;
            if (n6 < 0.0f) {
                return 0.0f;
            }
        }
        return n;
    }
    
    private float getAlphaForLastTime(final View view) {
        float calculateAlpha = 1.0f;
        final Rect rect = getRect((Context)this.context, (View)this.lastTimeLabel);
        final Rect rect2 = getRect((Context)this.context, view);
        if (Rect.intersects(rect, rect2)) {
            calculateAlpha = this.calculateAlpha(rect, rect2);
        }
        return calculateAlpha;
    }
    
    private int getLastTimeBottomMargin() {
        final PlayerActivity context = this.context;
        if (context != null && context.isTablet()) {
            return 40;
        }
        return 10;
    }
    
    private static Rect getRect(final Context context, final View view) {
        if (view == null) {
            return null;
        }
        final int dipToPixels = AndroidUtils.dipToPixels(context, 18);
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        return new Rect(array[0] + dipToPixels, array[1], array[0] + view.getWidth() - dipToPixels, array[1] + view.getHeight());
    }
    
    private static Rect intersect(final Rect rect, final Rect rect2) {
        if (!Rect.intersects(rect, rect2)) {
            return null;
        }
        return new Rect(Math.max(rect2.left, rect.left), Math.max(rect2.top, rect.top), Math.min(rect2.right, rect.right), Math.min(rect2.bottom, rect.bottom));
    }
    
    private boolean intersectWithEdge(final Rect rect, final Rect rect2, final boolean b) {
        if (b) {
            return rect2.right >= rect.right;
        }
        return rect2.left <= rect.left;
    }
    
    private boolean intersectWithMiddle(final Rect rect, final Rect rect2, final boolean b) {
        final int centerX = rect.centerX();
        if (b) {
            return rect2.right >= centerX;
        }
        return rect2.left <= centerX;
    }
    
    @Override
    public void changeActionState(final boolean b) {
    }
    
    @Override
    public void hide() {
    }
    
    public void setLastTimeState(final boolean b) {
        if (!b) {
            Log.d("screen", "setLastTimeState:: Last time gone!");
            this.lastTime.setVisibility(4);
            return;
        }
        Log.d("screen", "setLastTimeState:: Last time visible");
        final PlayerActivity context = this.context;
        if (context == null) {
            return;
        }
        final BottomPanel bottomPanel = this.context.getScreen().getBottomPanel();
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.lastTime.getLayoutParams();
        layoutParams.setMargins(bottomPanel.getTimeX((View)this.lastTime), 0, 0, AndroidUtils.dipToPixels((Context)context, this.getLastTimeBottomMargin()));
        Log.d("screen", "setLastTimeState::Last time update!");
        if (this.lastTimeLabel != null) {
            final String stringForMs = bottomPanel.getFormatter().getStringForMs(bottomPanel.getTimeline().getProgress());
            if (Log.isLoggable("screen", 3)) {
                Log.d("screen", "setLastTimeState::Sets Last time update to: " + stringForMs);
            }
            this.lastTimeLabel.setText((CharSequence)stringForMs);
        }
        Log.d("screen", "setLastTimeState:: Last time move overlay");
        this.lastTime.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.lastTime.setAlpha(0.0f);
        this.lastTime.setVisibility(0);
    }
    
    @Override
    public void show() {
    }
    
    void validateTimeOverlap(final View view) {
        if (this.lastTime != null) {
            if (this.lastTime.getVisibility() != 8) {
                final float alphaForLastTime = this.getAlphaForLastTime(view);
                if (alphaForLastTime != this.lastTime.getAlpha()) {
                    this.lastTime.setAlpha(alphaForLastTime);
                }
            }
            return;
        }
        Log.e("screen", "lastTime  null!!!");
    }
}
