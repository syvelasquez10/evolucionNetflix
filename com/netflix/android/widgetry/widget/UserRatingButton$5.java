// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

class UserRatingButton$5 implements UserRatingButton$OnRateListener
{
    final /* synthetic */ UserRatingButton this$0;
    final /* synthetic */ UserRatingButton$OnRateListener val$onRateListener;
    
    UserRatingButton$5(final UserRatingButton this$0, final UserRatingButton$OnRateListener val$onRateListener) {
        this.this$0 = this$0;
        this.val$onRateListener = val$onRateListener;
    }
    
    @Override
    public void onAlphaAnimate(final float n) {
        this.val$onRateListener.onAlphaAnimate(n);
    }
    
    @Override
    public void onDismissed(final UserRatingButton userRatingButton) {
        this.this$0.getParent().requestDisallowInterceptTouchEvent(false);
        this.val$onRateListener.onDismissed(userRatingButton);
    }
    
    @Override
    public void onOpened(final UserRatingButton userRatingButton, final boolean b) {
        this.val$onRateListener.onOpened(userRatingButton, b);
    }
    
    @Override
    public void onRate(final UserRatingButton userRatingButton, final int n) {
        this.val$onRateListener.onRate(userRatingButton, n);
    }
}
