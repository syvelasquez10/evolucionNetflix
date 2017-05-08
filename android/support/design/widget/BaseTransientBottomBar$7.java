// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewGroup$LayoutParams;
import android.view.ViewParent;
import java.util.ArrayList;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.support.design.R$anim;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.os.Build$VERSION;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.os.Handler$Callback;
import android.os.Looper;
import android.view.ViewGroup;
import android.content.Context;
import java.util.List;
import android.view.accessibility.AccessibilityManager;
import android.os.Handler;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class BaseTransientBottomBar$7 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ BaseTransientBottomBar this$0;
    
    BaseTransientBottomBar$7(final BaseTransientBottomBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.this$0.onViewShown();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.mContentViewCallback.animateContentIn(70, 180);
    }
}
