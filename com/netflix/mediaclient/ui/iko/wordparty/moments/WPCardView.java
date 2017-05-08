// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.util.Property;
import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.animation.TimeInterpolator;
import android.animation.AnimatorSet;
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.view.animation.Interpolator;
import android.graphics.drawable.BitmapDrawable;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import android.widget.ImageView;

public class WPCardView extends ImageView
{
    public static final String TAG = "WPCardView";
    private final int CAMERA_DISTANCE;
    private WPInteractiveMomentsModel$WPAudio audio;
    private CardClickListener cardClickListener;
    private int cardHeight;
    private float cardRotationValue;
    private int cardWidth;
    private BitmapDrawable closedDrawable;
    private BitmapDrawable currentBitmapDrawable;
    private int deviceHeight;
    private int deviceWidth;
    private boolean isCardClosed;
    private BitmapDrawable openDrawable;
    private int padding;
    private int preferredHeight;
    private int preferredWidth;
    private Interpolator quintOutInterpolator;
    
    public WPCardView(final Context context) {
        super(context);
        this.CAMERA_DISTANCE = 8000;
        this.isCardClosed = true;
        this.init(context);
    }
    
    public WPCardView(final Context context, final AttributeSet set) {
        super(context, set);
        this.CAMERA_DISTANCE = 8000;
        this.isCardClosed = true;
        this.init(context);
    }
    
    private float getCardRotationValue(final int n, int n2) {
        if (n2 % 2 != 0 && n == n2 - 1) {
            return 0.0f;
        }
        float cardRotationValue;
        final float n3 = cardRotationValue = this.cardRotationValue;
        if (n2 == 4) {
            cardRotationValue = n3;
            if (n >= n2 / 2) {
                cardRotationValue = n3 / 2.0f;
            }
        }
        if (n / 2 <= 0) {
            n2 = -1;
        }
        else {
            n2 = 1;
        }
        final float n4 = n2;
        if (n % 2 > 0) {
            cardRotationValue = -cardRotationValue;
        }
        return cardRotationValue * n4;
    }
    
    private float getCardSpacingMultiplier(final int n) {
        return (float)Math.pow(1.2000000476837158, 4 - n + 1);
    }
    
    private float getCardXPosition(final int n, final int n2) {
        float n3;
        if (n2 % 2 != 0 && n == n2 - 1) {
            n3 = 0.0f;
        }
        else {
            n3 = this.getCardWidth() / 2 + this.padding;
            if (n % 2 == 0) {
                return -n3;
            }
        }
        return n3;
    }
    
    private float getCardYPosition(final int n, final int n2) {
        float n3;
        if (n2 <= 2) {
            n3 = 0.0f;
        }
        else {
            n3 = this.getCardHeight() / 2 + this.padding;
            if (n / 2 <= 0) {
                return -n3;
            }
        }
        return n3;
    }
    
    private int getMeasurement(final int n, final int n2) {
        final int size = View$MeasureSpec.getSize(n);
        switch (View$MeasureSpec.getMode(n)) {
            default: {
                return n2;
            }
            case 1073741824: {
                return size;
            }
            case Integer.MIN_VALUE: {
                return Math.min(n2, size);
            }
        }
    }
    
    private float getRecapCardXPosition(final int n, final int n2) {
        if (n == 0) {
            return 0.0f;
        }
        return this.getRecapExitCardXPosition(n - 1, n2 - 1);
    }
    
    private float getRecapCardYPosition(final int n, int n2) {
        n2 = this.getCardHeight() / 2;
        if (n == 0) {
            return -(n2 - this.padding);
        }
        return n2 * 3.0f * (1.0f / DeviceUtils.getScreenAspectRatio(this.getContext()));
    }
    
    private float getRecapExitCardXPosition(final int n, final int n2) {
        float n3 = n - n2 / 2;
        if (n2 % 2 == 0) {
            n3 += 0.5f;
        }
        return n3 * (this.getCardWidth() / 2) * this.getCardSpacingMultiplier(n2);
    }
    
    private float getRecapExitCardYPosition(final int n, final int n2) {
        return 0.0f;
    }
    
    private void init(final Context context) {
        this.setAdjustViewBounds(true);
        this.setCameraDistance(8000.0f);
        this.setScaleType(ImageView$ScaleType.CENTER_CROP);
        this.initLayoutConfigurations();
        this.quintOutInterpolator = WPConstants.getQuintOutInterpolator();
    }
    
    private void initLayoutConfigurations() {
        this.deviceWidth = DeviceUtils.getScreenWidthInPixels(this.getContext());
        this.deviceHeight = DeviceUtils.getScreenHeightInPixels(this.getContext());
        float n = 0.36f;
        if (DeviceUtils.getScreenAspectRatio(this.getContext()) <= 1.5f) {
            n = 0.4f;
        }
        this.preferredWidth = (int)(n * this.deviceWidth);
        this.preferredHeight = (int)(this.preferredWidth * 0.5625f);
        this.cardRotationValue = 3.0f;
        this.padding = this.getContext().getResources().getDimensionPixelSize(2131362310);
    }
    
    private int measureWidth(final int n) {
        return this.getMeasurement(n, this.preferredWidth);
    }
    
    public AnimatorSet calculateRecapAnimation(final int n, final int n2) {
        float n3;
        if (n == 0) {
            n3 = 1.5f;
        }
        else {
            n3 = 0.5f;
        }
        final AnimatorSet cardAnimation = this.getCardAnimation(this.getRecapCardXPosition(n, n2), this.getRecapCardYPosition(n, n2), 0.0f, n3);
        cardAnimation.setDuration(800L);
        cardAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return cardAnimation;
    }
    
    public AnimatorSet calculateRecapExitAnimation(final int n, final int n2) {
        final AnimatorSet cardAnimation = this.getCardAnimation(this.getRecapExitCardXPosition(n, n2), this.getRecapExitCardYPosition(n, n2), 0.0f, 0.5f);
        cardAnimation.setDuration(800L);
        cardAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return cardAnimation;
    }
    
    public AnimatorSet calculateRecapInitAnimation(final int n, final int n2) {
        this.setScaleX(0.5f);
        this.setScaleY(0.5f);
        final AnimatorSet cardAnimation = this.getCardAnimation(this.getRecapExitCardXPosition(n, n2), this.getRecapExitCardYPosition(n, n2), 0.0f, 0.5f);
        cardAnimation.setDuration(800L);
        cardAnimation.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return cardAnimation;
    }
    
    public AnimatorSet calculateRevealAnimation(final int n, final int n2) {
        final float n3 = 1.0f;
        final float cardXPosition = this.getCardXPosition(n, n2);
        final float cardYPosition = this.getCardYPosition(n, n2);
        final float cardRotationValue = this.getCardRotationValue(n, n2);
        float n4 = n3;
        if (n2 == 1) {
            n4 = n3;
            if (this.isCardClosed) {
                n4 = 1.5f;
            }
        }
        return this.getCardAnimation(cardXPosition, cardYPosition, cardRotationValue, n4);
    }
    
    public void flip() {
        this.setPivotX((float)(this.getWidth() / 2));
        final Property rotation_Y = View.ROTATION_Y;
        float n;
        if (this.isCardClosed) {
            n = -180.0f;
        }
        else {
            n = 0.0f;
        }
        final ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder((Object)this, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(rotation_Y, new float[] { n }), PropertyValuesHolder.ofFloat(View.ROTATION, new float[] { -1.0f * this.getRotation() }), PropertyValuesHolder.ofFloat(View.ALPHA, WPConstants.CARD_FLIP_ALPHA_VALUE_LIST) });
        ofPropertyValuesHolder.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new WPCardView$1(this, ofPropertyValuesHolder));
        final AnimatorSet set = new AnimatorSet();
        set.playSequentially(new Animator[] { ofPropertyValuesHolder });
        set.setDuration(500L);
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        set.addListener((Animator$AnimatorListener)new WPCardView$2(this));
        if (this.cardClickListener != null) {
            this.cardClickListener.onCardClickStart(this);
        }
        set.start();
    }
    
    public WPInteractiveMomentsModel$WPAudio getAudio() {
        return this.audio;
    }
    
    public AnimatorSet getCardAnimation(final float n, final float n2, final float n3, final float n4) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, View.TRANSLATION_X, new float[] { n });
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)this, View.TRANSLATION_Y, new float[] { n2 });
        final ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat((Object)this, View.ROTATION, new float[] { n3 });
        final ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat((Object)this, View.SCALE_X, new float[] { n4 });
        final ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat((Object)this, View.SCALE_Y, new float[] { n4 });
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(new Animator[] { ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5 });
        set.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return set;
    }
    
    public int getCardHeight() {
        int n = this.preferredHeight;
        if (this.cardHeight > 0) {
            n = this.cardHeight;
        }
        return n;
    }
    
    public int getCardWidth() {
        int n = this.preferredWidth;
        if (this.cardWidth > 0) {
            n = this.cardWidth;
        }
        return n;
    }
    
    public ObjectAnimator getWiggleAnimation(final int n) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, View.ROTATION, new float[] { this.getRotation(), -1.0f, 1.0f, this.getRotation() });
        ofFloat.setStartDelay((long)(n % 2 * 100));
        ofFloat.setRepeatCount(3);
        ofFloat.setRepeatMode(2);
        ofFloat.setInterpolator((TimeInterpolator)this.quintOutInterpolator);
        return ofFloat;
    }
    
    public boolean isOpen() {
        return !this.isCardClosed;
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.cardWidth = this.measureWidth(n);
        this.cardHeight = (int)(this.cardWidth * 0.5625f);
        this.setMeasuredDimension(this.cardWidth, this.cardHeight);
    }
    
    public void recycleDrawables(final BitmapDrawable... array) {
        if (array != null && array.length >= 1) {
            for (int length = array.length, i = 0; i < length; ++i) {
                final BitmapDrawable bitmapDrawable = array[i];
                if (bitmapDrawable == null) {
                    break;
                }
                final Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
    }
    
    public void releaseResources() {
        this.recycleDrawables(this.closedDrawable, this.openDrawable, this.currentBitmapDrawable);
        this.closedDrawable = null;
        this.openDrawable = null;
        this.currentBitmapDrawable = null;
    }
    
    public void reset(final boolean isCardClosed) {
        this.isCardClosed = isCardClosed;
        this.updateDrawableBitmap();
        this.setScaleX(1.0f);
        this.setScaleY(1.0f);
        this.setRotationY(0.0f);
        this.setRotation(0.0f);
    }
    
    public void setAudio(final WPInteractiveMomentsModel$WPAudio audio) {
        this.audio = audio;
    }
    
    public void setCardClickListener(final CardClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;
    }
    
    public void setDrawables(final BitmapDrawable closedDrawable, final BitmapDrawable openDrawable) {
        this.isCardClosed = true;
        this.closedDrawable = closedDrawable;
        this.openDrawable = openDrawable;
        this.updateDrawableBitmap();
    }
    
    public String toString() {
        String string;
        if (this.audio != null) {
            string = " " + this.audio.getUrl();
        }
        else {
            string = " null";
        }
        return "WPCardView{isCardClosed=" + this.isCardClosed + ", audio=" + string + '}';
    }
    
    public void toggleCardClosed() {
        this.isCardClosed = !this.isCardClosed;
    }
    
    public void updateDrawableBitmap() {
        BitmapDrawable currentBitmapDrawable;
        if (this.isCardClosed) {
            currentBitmapDrawable = this.closedDrawable;
        }
        else {
            currentBitmapDrawable = this.openDrawable;
        }
        this.setImageDrawable((Drawable)(this.currentBitmapDrawable = currentBitmapDrawable));
    }
}
