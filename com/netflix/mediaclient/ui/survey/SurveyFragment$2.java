// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.survey;

import com.netflix.model.survey.SurveyQuestion;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.model.survey.Survey;
import android.support.v4.app.Fragment;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.view.View$OnTouchListener;

class SurveyFragment$2 implements View$OnTouchListener
{
    private boolean isTracking;
    final /* synthetic */ SurveyFragment this$0;
    final /* synthetic */ ImageView val$s1;
    final /* synthetic */ ImageView val$s2;
    final /* synthetic */ ImageView val$s3;
    final /* synthetic */ ImageView val$s4;
    final /* synthetic */ ImageView val$s5;
    
    SurveyFragment$2(final SurveyFragment this$0, final ImageView val$s1, final ImageView val$s2, final ImageView val$s3, final ImageView val$s4, final ImageView val$s5) {
        this.this$0 = this$0;
        this.val$s1 = val$s1;
        this.val$s2 = val$s2;
        this.val$s3 = val$s3;
        this.val$s4 = val$s4;
        this.val$s5 = val$s5;
        this.isTracking = true;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (this.isTracking || motionEvent.getAction() == 1) {
            if (action == 0) {
                this.this$0.startTouchAnimation(view, true);
                return true;
            }
            if (action == 2 && !this.this$0.isEventInsideView(view, motionEvent)) {
                this.isTracking = false;
                this.this$0.startTouchAnimation(view, false);
                return true;
            }
            if (action == 1) {
                this.isTracking = true;
                if (this.this$0.isEventInsideView(view, motionEvent)) {
                    ViewUtils.setOnTouchListeners(null, this.val$s1, this.val$s2, this.val$s3, this.val$s4, this.val$s5);
                    this.this$0.makeSelection(view);
                    return true;
                }
            }
        }
        return true;
    }
}
