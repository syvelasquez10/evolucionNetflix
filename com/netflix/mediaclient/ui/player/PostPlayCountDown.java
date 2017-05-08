// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.text.Html;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.model.leafs.PostPlayItem;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.TimeUtils$CountdownTimer;
import android.widget.TextView;
import android.widget.LinearLayout;

public class PostPlayCountDown extends LinearLayout
{
    private TextView action;
    private TimeUtils$CountdownTimer countdownTimer;
    protected NetflixActivity netflixActivity;
    PostPlayItem postPlayItem;
    private final Runnable refreshTextRunner;
    
    public PostPlayCountDown(final Context context) {
        super(context);
        this.refreshTextRunner = new PostPlayCountDown$1(this);
    }
    
    public PostPlayCountDown(final Context context, final AttributeSet set) {
        super(context, set);
        this.refreshTextRunner = new PostPlayCountDown$1(this);
    }
    
    public PostPlayCountDown(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.refreshTextRunner = new PostPlayCountDown$1(this);
    }
    
    private String getString(final int n) {
        return this.getString(n, null);
    }
    
    private String getString(final int n, final String s) {
        return this.netflixActivity.getResources().getString(n, new Object[] { s });
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.countdownTimer != null) {
            this.countdownTimer.stopTimer();
        }
    }
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.action = (TextView)this.findViewById(2131690115);
    }
    
    public void refreshTimerText() {
        int time = this.countdownTimer.getTime();
        if (time <= 1) {
            time = 1;
        }
        int n;
        if (this.postPlayItem.isNextEpisodeAutoPlay()) {
            if (time > 1) {
                n = 2131231150;
            }
            else {
                n = 2131231149;
            }
        }
        else if (time > 1) {
            n = 2131231155;
        }
        else {
            n = 2131231154;
        }
        if (this.action != null) {
            this.action.setText((CharSequence)Html.fromHtml(this.getString(n, String.valueOf(time))));
        }
    }
    
    public void startTimer() {
        if (this.countdownTimer != null) {
            this.countdownTimer.startTimer();
        }
    }
    
    public void stopTimer() {
        if (this.countdownTimer != null) {
            this.countdownTimer.stopTimer();
        }
    }
    
    public void updateViews(final PostPlayItem postPlayItem, final NetflixActivity netflixActivity) {
        this.netflixActivity = netflixActivity;
        this.postPlayItem = postPlayItem;
        (this.countdownTimer = new TimeUtils$CountdownTimer(netflixActivity)).setOnTick(this.refreshTextRunner);
        this.countdownTimer.setTime(postPlayItem.getAutoPlaySeconds());
        String text;
        if (postPlayItem.isNextEpisodeAutoPlay()) {
            text = this.getString(2131231149);
        }
        else {
            text = this.getString(2131231154);
        }
        this.action.setText((CharSequence)text);
        this.refreshTimerText();
    }
}
