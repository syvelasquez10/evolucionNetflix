// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.survey;

import com.netflix.model.survey.SurveyQuestion;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnClickListener;
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
import android.view.View;
import com.netflix.model.survey.Survey;
import android.support.v4.app.Fragment;

public class SurveyFragment extends Fragment
{
    private static final int ANIM_DURATION_MS = 200;
    private static final String EXTRA_SURVEY = "extra_survey";
    private static final int LINE_BREAK_CHAR_THRESHOLD = 6;
    private static final int LINE_BREAK_DP_THRESHOLD = 699;
    private static final float PUSH_SQUISH_RATIO = 1.2f;
    private Survey survey;
    private SurveyListener surveyListener;
    
    public static SurveyFragment getInstance(final Survey survey) {
        final SurveyFragment surveyFragment = new SurveyFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("extra_survey", (Parcelable)survey);
        surveyFragment.setArguments(arguments);
        return surveyFragment;
    }
    
    private boolean isEventInsideView(final View view, final MotionEvent motionEvent) {
        return motionEvent.getX() <= view.getWidth() && motionEvent.getY() <= view.getHeight() && motionEvent.getX() >= 0.0f && motionEvent.getY() >= 0.0f;
    }
    
    private void makeSelection(final View view) {
        int n = 0;
        switch (view.getId()) {
            case 2131690298: {
                n = 1;
                break;
            }
            case 2131690299: {
                n = 2;
                break;
            }
            case 2131690300: {
                n = 3;
                break;
            }
            case 2131690301: {
                n = 4;
                break;
            }
            case 2131690302: {
                n = 5;
                break;
            }
        }
        if (this.surveyListener != null) {
            this.surveyListener.onCompleted(n);
        }
    }
    
    private void startTouchAnimation(final View view, final boolean b) {
        float n;
        if (b) {
            n = 1.2f;
        }
        else {
            n = 1.0f;
        }
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)view, View.SCALE_X, new float[] { n });
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)view, View.SCALE_Y, new float[] { n });
        final AnimatorSet set = new AnimatorSet();
        set.play((Animator)ofFloat).with((Animator)ofFloat2);
        set.setDuration(200L);
        set.start();
        final TransitionDrawable transitionDrawable = (TransitionDrawable)view.getBackground();
        if (b) {
            transitionDrawable.startTransition(200);
            return;
        }
        transitionDrawable.reverseTransition(200);
    }
    
    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        if (context instanceof SurveyListener) {
            this.surveyListener = (SurveyListener)context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement SurveyListener");
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130903129, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131689834);
        final TextView textView2 = (TextView)inflate.findViewById(2131689566);
        final TextView textView3 = (TextView)inflate.findViewById(2131689835);
        final TextView textView4 = (TextView)inflate.findViewById(2131690303);
        final TextView textView5 = (TextView)inflate.findViewById(2131690304);
        final Button button = (Button)inflate.findViewById(2131689836);
        final ImageView imageView = (ImageView)inflate.findViewById(2131690298);
        final ImageView imageView2 = (ImageView)inflate.findViewById(2131690299);
        final ImageView imageView3 = (ImageView)inflate.findViewById(2131690300);
        final ImageView imageView4 = (ImageView)inflate.findViewById(2131690301);
        final ImageView imageView5 = (ImageView)inflate.findViewById(2131690302);
        this.survey = (Survey)this.getArguments().getParcelable("extra_survey");
        if (this.survey == null || this.survey.isEmpty()) {
            this.getActivity().finish();
            return inflate;
        }
        final SurveyQuestion firstQuestion = this.survey.getFirstQuestion();
        textView.setText((CharSequence)firstQuestion.getHeader());
        textView2.setText((CharSequence)firstQuestion.getTitle());
        textView3.setText((CharSequence)firstQuestion.getBody());
        textView4.setText((CharSequence)firstQuestion.getDisagreementLabel());
        textView5.setText((CharSequence)firstQuestion.getAgreementLabel());
        button.setText((CharSequence)firstQuestion.getSkipLabel());
        button.setOnClickListener((View$OnClickListener)new SurveyFragment$1(this));
        ViewUtils.setOnTouchListeners((View$OnTouchListener)new SurveyFragment$2(this, imageView, imageView2, imageView3, imageView4, imageView5), imageView, imageView2, imageView3, imageView4, imageView5);
        return inflate;
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        this.surveyListener = null;
    }
}
