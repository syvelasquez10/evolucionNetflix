// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.survey;

import com.netflix.model.survey.SurveyQuestion;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ImageView;
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
import android.view.MotionEvent;
import com.netflix.model.survey.Survey;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View$OnClickListener;

class SurveyFragment$1 implements View$OnClickListener
{
    final /* synthetic */ SurveyFragment this$0;
    
    SurveyFragment$1(final SurveyFragment this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.surveyListener != null) {
            this.this$0.surveyListener.onSkipped();
        }
    }
}
