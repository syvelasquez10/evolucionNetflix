// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.mediarouter.R$string;
import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff$Mode;
import android.support.v4.content.ContextCompat;
import android.support.v7.mediarouter.R$drawable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View$OnClickListener;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageButton;

class MediaRouteExpandCollapseButton extends ImageButton
{
    final AnimationDrawable mCollapseAnimationDrawable;
    final String mCollapseGroupDescription;
    final AnimationDrawable mExpandAnimationDrawable;
    final String mExpandGroupDescription;
    boolean mIsGroupExpanded;
    View$OnClickListener mListener;
    
    public MediaRouteExpandCollapseButton(final Context context) {
        this(context, null);
    }
    
    public MediaRouteExpandCollapseButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public MediaRouteExpandCollapseButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mExpandAnimationDrawable = (AnimationDrawable)ContextCompat.getDrawable(context, R$drawable.mr_group_expand);
        this.mCollapseAnimationDrawable = (AnimationDrawable)ContextCompat.getDrawable(context, R$drawable.mr_group_collapse);
        final PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(MediaRouterThemeHelper.getControllerColor(context, n), PorterDuff$Mode.SRC_IN);
        this.mExpandAnimationDrawable.setColorFilter((ColorFilter)porterDuffColorFilter);
        this.mCollapseAnimationDrawable.setColorFilter((ColorFilter)porterDuffColorFilter);
        this.mExpandGroupDescription = context.getString(R$string.mr_controller_expand_group);
        this.mCollapseGroupDescription = context.getString(R$string.mr_controller_collapse_group);
        this.setImageDrawable(this.mExpandAnimationDrawable.getFrame(0));
        this.setContentDescription((CharSequence)this.mExpandGroupDescription);
        super.setOnClickListener((View$OnClickListener)new MediaRouteExpandCollapseButton$1(this));
    }
    
    public void setOnClickListener(final View$OnClickListener mListener) {
        this.mListener = mListener;
    }
}
