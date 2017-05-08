// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.view;

import com.facebook.common.internal.Objects$ToStringHelper;
import com.facebook.common.internal.Objects;
import android.net.Uri;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.interfaces.DraweeController;
import android.content.res.ColorStateList;
import android.os.Build$VERSION;
import android.content.Context;
import android.widget.ImageView;
import com.facebook.drawee.interfaces.DraweeHierarchy;

public class DraweeView<DH extends DraweeHierarchy> extends ImageView
{
    private float mAspectRatio;
    private DraweeHolder<DH> mDraweeHolder;
    private boolean mInitialised;
    private final AspectRatioMeasure$Spec mMeasureSpec;
    
    public DraweeView(final Context context) {
        super(context);
        this.mMeasureSpec = new AspectRatioMeasure$Spec();
        this.mAspectRatio = 0.0f;
        this.mInitialised = false;
        this.init(context);
    }
    
    private void init(final Context context) {
        if (!this.mInitialised) {
            this.mInitialised = true;
            this.mDraweeHolder = DraweeHolder.create((DH)null, context);
            if (Build$VERSION.SDK_INT >= 21) {
                final ColorStateList imageTintList = this.getImageTintList();
                if (imageTintList != null) {
                    this.setColorFilter(imageTintList.getDefaultColor());
                }
            }
        }
    }
    
    protected void doAttach() {
        this.mDraweeHolder.onAttach();
    }
    
    protected void doDetach() {
        this.mDraweeHolder.onDetach();
    }
    
    public float getAspectRatio() {
        return this.mAspectRatio;
    }
    
    public DraweeController getController() {
        return this.mDraweeHolder.getController();
    }
    
    public DH getHierarchy() {
        return this.mDraweeHolder.getHierarchy();
    }
    
    public Drawable getTopLevelDrawable() {
        return this.mDraweeHolder.getTopLevelDrawable();
    }
    
    protected void onAttach() {
        this.doAttach();
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.onAttach();
    }
    
    protected void onDetach() {
        this.doDetach();
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.onDetach();
    }
    
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        this.onAttach();
    }
    
    protected void onMeasure(final int width, final int height) {
        this.mMeasureSpec.width = width;
        this.mMeasureSpec.height = height;
        AspectRatioMeasure.updateMeasureSpec(this.mMeasureSpec, this.mAspectRatio, this.getLayoutParams(), this.getPaddingLeft() + this.getPaddingRight(), this.getPaddingTop() + this.getPaddingBottom());
        super.onMeasure(this.mMeasureSpec.width, this.mMeasureSpec.height);
    }
    
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        this.onDetach();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.mDraweeHolder.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
    }
    
    public void setAspectRatio(final float mAspectRatio) {
        if (mAspectRatio == this.mAspectRatio) {
            return;
        }
        this.mAspectRatio = mAspectRatio;
        this.requestLayout();
    }
    
    public void setController(final DraweeController controller) {
        this.mDraweeHolder.setController(controller);
        super.setImageDrawable(this.mDraweeHolder.getTopLevelDrawable());
    }
    
    public void setHierarchy(final DH hierarchy) {
        this.mDraweeHolder.setHierarchy(hierarchy);
        super.setImageDrawable(this.mDraweeHolder.getTopLevelDrawable());
    }
    
    @Deprecated
    public void setImageBitmap(final Bitmap imageBitmap) {
        this.init(this.getContext());
        this.mDraweeHolder.setController(null);
        super.setImageBitmap(imageBitmap);
    }
    
    @Deprecated
    public void setImageDrawable(final Drawable imageDrawable) {
        this.init(this.getContext());
        this.mDraweeHolder.setController(null);
        super.setImageDrawable(imageDrawable);
    }
    
    @Deprecated
    public void setImageResource(final int imageResource) {
        this.init(this.getContext());
        this.mDraweeHolder.setController(null);
        super.setImageResource(imageResource);
    }
    
    @Deprecated
    public void setImageURI(final Uri imageURI) {
        this.init(this.getContext());
        this.mDraweeHolder.setController(null);
        super.setImageURI(imageURI);
    }
    
    public String toString() {
        final Objects$ToStringHelper stringHelper = Objects.toStringHelper(this);
        String string;
        if (this.mDraweeHolder != null) {
            string = this.mDraweeHolder.toString();
        }
        else {
            string = "<no holder set>";
        }
        return stringHelper.add("holder", string).toString();
    }
}
