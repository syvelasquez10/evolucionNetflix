// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.animation.LinearInterpolator;
import android.view.animation.AnimationUtils;
import android.graphics.drawable.Drawable$Callback;
import android.os.Build$VERSION;
import android.os.Parcelable;
import android.graphics.drawable.Animatable;
import android.os.SystemClock;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.Shader;
import android.graphics.BitmapShader;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.Context;
import android.view.animation.Transformation;
import android.graphics.Bitmap;
import android.view.animation.Interpolator;
import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.View;

class ProgressBarCompat$RefreshProgressRunnable implements Runnable
{
    private boolean mFromUser;
    private int mId;
    private int mProgress;
    final /* synthetic */ ProgressBarCompat this$0;
    
    ProgressBarCompat$RefreshProgressRunnable(final ProgressBarCompat this$0, final int mId, final int mProgress, final boolean mFromUser) {
        this.this$0 = this$0;
        this.mId = mId;
        this.mProgress = mProgress;
        this.mFromUser = mFromUser;
    }
    
    @Override
    public void run() {
        this.this$0.doRefreshProgress(this.mId, this.mProgress, this.mFromUser, true);
        this.this$0.mRefreshProgressRunnable = this;
    }
    
    public void setup(final int mId, final int mProgress, final boolean mFromUser) {
        this.mId = mId;
        this.mProgress = mProgress;
        this.mFromUser = mFromUser;
    }
}
