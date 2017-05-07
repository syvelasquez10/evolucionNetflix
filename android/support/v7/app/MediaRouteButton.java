// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.util.Log;
import android.widget.Toast;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View$MeasureSpec;
import android.graphics.Canvas;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.drawable.Drawable$Callback;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.ContextWrapper;
import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.mediarouter.R;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.graphics.drawable.Drawable;
import android.view.View;

public class MediaRouteButton extends View
{
    private static final int[] CHECKABLE_STATE_SET;
    private static final int[] CHECKED_STATE_SET;
    private static final String CHOOSER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteChooserDialogFragment";
    private static final String CONTROLLER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteControllerDialogFragment";
    private static final String TAG = "MediaRouteButton";
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private boolean mCheatSheetEnabled;
    private MediaRouteDialogFactory mDialogFactory;
    private boolean mIsConnecting;
    private int mMinHeight;
    private int mMinWidth;
    private boolean mRemoteActive;
    private Drawable mRemoteIndicator;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;
    
    static {
        CHECKED_STATE_SET = new int[] { 16842912 };
        CHECKABLE_STATE_SET = new int[] { 16842911 };
    }
    
    public MediaRouteButton(final Context context) {
        this(context, null);
    }
    
    public MediaRouteButton(final Context context, final AttributeSet set) {
        this(context, set, R.attr.mediaRouteButtonStyle);
    }
    
    public MediaRouteButton(Context context, final AttributeSet set, final int n) {
        super(MediaRouterThemeHelper.createThemedContext(context, false), set, n);
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mDialogFactory = MediaRouteDialogFactory.getDefault();
        context = this.getContext();
        this.mRouter = MediaRouter.getInstance(context);
        this.mCallback = new MediaRouterCallback();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.MediaRouteButton, n, 0);
        this.setRemoteIndicatorDrawable(obtainStyledAttributes.getDrawable(2));
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.mMinHeight = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        this.setClickable(true);
        this.setLongClickable(true);
    }
    
    private Activity getActivity() {
        for (Context context = this.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
        }
        return null;
    }
    
    private FragmentManager getFragmentManager() {
        final Activity activity = this.getActivity();
        if (activity instanceof FragmentActivity) {
            return ((FragmentActivity)activity).getSupportFragmentManager();
        }
        return null;
    }
    
    private void refreshRoute() {
        final boolean b = false;
        if (this.mAttachedToWindow) {
            final MediaRouter.RouteInfo selectedRoute = this.mRouter.getSelectedRoute();
            final boolean mRemoteActive = !selectedRoute.isDefault() && selectedRoute.matchesSelector(this.mSelector);
            boolean mIsConnecting = b;
            if (mRemoteActive) {
                mIsConnecting = b;
                if (selectedRoute.isConnecting()) {
                    mIsConnecting = true;
                }
            }
            boolean b2 = false;
            if (this.mRemoteActive != mRemoteActive) {
                this.mRemoteActive = mRemoteActive;
                b2 = true;
            }
            if (this.mIsConnecting != mIsConnecting) {
                this.mIsConnecting = mIsConnecting;
                b2 = true;
            }
            if (b2) {
                this.refreshDrawableState();
            }
            this.setEnabled(this.mRouter.isRouteAvailable(this.mSelector, 1));
        }
    }
    
    private void setRemoteIndicatorDrawable(final Drawable mRemoteIndicator) {
        if (this.mRemoteIndicator != null) {
            this.mRemoteIndicator.setCallback((Drawable$Callback)null);
            this.unscheduleDrawable(this.mRemoteIndicator);
        }
        if ((this.mRemoteIndicator = mRemoteIndicator) != null) {
            mRemoteIndicator.setCallback((Drawable$Callback)this);
            mRemoteIndicator.setState(this.getDrawableState());
            mRemoteIndicator.setVisible(this.getVisibility() == 0, false);
        }
        this.refreshDrawableState();
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mRemoteIndicator != null) {
            this.mRemoteIndicator.setState(this.getDrawableState());
            this.invalidate();
        }
    }
    
    public MediaRouteDialogFactory getDialogFactory() {
        return this.mDialogFactory;
    }
    
    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }
    
    public void jumpDrawablesToCurrentState() {
        if (this.getBackground() != null) {
            DrawableCompat.jumpToCurrentState(this.getBackground());
        }
        if (this.mRemoteIndicator != null) {
            DrawableCompat.jumpToCurrentState(this.mRemoteIndicator);
        }
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        if (!this.mSelector.isEmpty()) {
            this.mRouter.addCallback(this.mSelector, (MediaRouter.Callback)this.mCallback);
        }
        this.refreshRoute();
    }
    
    protected int[] onCreateDrawableState(final int n) {
        final int[] onCreateDrawableState = super.onCreateDrawableState(n + 1);
        if (this.mIsConnecting) {
            mergeDrawableStates(onCreateDrawableState, MediaRouteButton.CHECKABLE_STATE_SET);
        }
        else if (this.mRemoteActive) {
            mergeDrawableStates(onCreateDrawableState, MediaRouteButton.CHECKED_STATE_SET);
            return onCreateDrawableState;
        }
        return onCreateDrawableState;
    }
    
    public void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        if (!this.mSelector.isEmpty()) {
            this.mRouter.removeCallback((MediaRouter.Callback)this.mCallback);
        }
        super.onDetachedFromWindow();
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mRemoteIndicator != null) {
            final int paddingLeft = this.getPaddingLeft();
            final int width = this.getWidth();
            final int paddingRight = this.getPaddingRight();
            final int paddingTop = this.getPaddingTop();
            final int height = this.getHeight();
            final int paddingBottom = this.getPaddingBottom();
            final int intrinsicWidth = this.mRemoteIndicator.getIntrinsicWidth();
            final int intrinsicHeight = this.mRemoteIndicator.getIntrinsicHeight();
            final int n = paddingLeft + (width - paddingRight - paddingLeft - intrinsicWidth) / 2;
            final int n2 = paddingTop + (height - paddingBottom - paddingTop - intrinsicHeight) / 2;
            this.mRemoteIndicator.setBounds(n, n2, n + intrinsicWidth, n2 + intrinsicHeight);
            this.mRemoteIndicator.draw(canvas);
        }
    }
    
    protected void onMeasure(int n, int n2) {
        final int n3 = 0;
        final int size = View$MeasureSpec.getSize(n);
        final int size2 = View$MeasureSpec.getSize(n2);
        final int mode = View$MeasureSpec.getMode(n);
        n2 = View$MeasureSpec.getMode(n2);
        final int mMinWidth = this.mMinWidth;
        if (this.mRemoteIndicator != null) {
            n = this.mRemoteIndicator.getIntrinsicWidth();
        }
        else {
            n = 0;
        }
        final int max = Math.max(mMinWidth, n);
        final int mMinHeight = this.mMinHeight;
        n = n3;
        if (this.mRemoteIndicator != null) {
            n = this.mRemoteIndicator.getIntrinsicHeight();
        }
        final int max2 = Math.max(mMinHeight, n);
        switch (mode) {
            default: {
                n = this.getPaddingLeft() + max + this.getPaddingRight();
                break;
            }
            case 1073741824: {
                n = size;
                break;
            }
            case Integer.MIN_VALUE: {
                n = Math.min(size, this.getPaddingLeft() + max + this.getPaddingRight());
                break;
            }
        }
        switch (n2) {
            default: {
                n2 = this.getPaddingTop() + max2 + this.getPaddingBottom();
                break;
            }
            case 1073741824: {
                n2 = size2;
                break;
            }
            case Integer.MIN_VALUE: {
                n2 = Math.min(size2, this.getPaddingTop() + max2 + this.getPaddingBottom());
                break;
            }
        }
        this.setMeasuredDimension(n, n2);
    }
    
    public boolean performClick() {
        boolean b = false;
        final boolean performClick = super.performClick();
        if (!performClick) {
            this.playSoundEffect(0);
        }
        if (this.showDialog() || performClick) {
            b = true;
        }
        return b;
    }
    
    public boolean performLongClick() {
        if (super.performLongClick()) {
            return true;
        }
        if (!this.mCheatSheetEnabled) {
            return false;
        }
        final CharSequence contentDescription = this.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            return false;
        }
        final int[] array = new int[2];
        final Rect rect = new Rect();
        this.getLocationOnScreen(array);
        this.getWindowVisibleDisplayFrame(rect);
        final Context context = this.getContext();
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int n = array[1];
        final int n2 = height / 2;
        final int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        final Toast text = Toast.makeText(context, contentDescription, 0);
        if (n + n2 < rect.height()) {
            text.setGravity(8388661, widthPixels - array[0] - width / 2, height);
        }
        else {
            text.setGravity(81, 0, height);
        }
        text.show();
        this.performHapticFeedback(0);
        return true;
    }
    
    void setCheatSheetEnabled(final boolean mCheatSheetEnabled) {
        this.mCheatSheetEnabled = mCheatSheetEnabled;
    }
    
    public void setDialogFactory(final MediaRouteDialogFactory mDialogFactory) {
        if (mDialogFactory == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        this.mDialogFactory = mDialogFactory;
    }
    
    public void setRouteSelector(final MediaRouteSelector mSelector) {
        if (mSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (!this.mSelector.equals(mSelector)) {
            if (this.mAttachedToWindow) {
                if (!this.mSelector.isEmpty()) {
                    this.mRouter.removeCallback((MediaRouter.Callback)this.mCallback);
                }
                if (!mSelector.isEmpty()) {
                    this.mRouter.addCallback(mSelector, (MediaRouter.Callback)this.mCallback);
                }
            }
            this.mSelector = mSelector;
            this.refreshRoute();
        }
    }
    
    public void setVisibility(final int visibility) {
        super.setVisibility(visibility);
        if (this.mRemoteIndicator != null) {
            this.mRemoteIndicator.setVisible(this.getVisibility() == 0, false);
        }
    }
    
    public boolean showDialog() {
        if (!this.mAttachedToWindow) {
            return false;
        }
        final FragmentManager fragmentManager = this.getFragmentManager();
        if (fragmentManager == null) {
            throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
        }
        final MediaRouter.RouteInfo selectedRoute = this.mRouter.getSelectedRoute();
        if (selectedRoute.isDefault() || !selectedRoute.matchesSelector(this.mSelector)) {
            if (fragmentManager.findFragmentByTag("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
                Log.w("MediaRouteButton", "showDialog(): Route chooser dialog already showing!");
                return false;
            }
            final MediaRouteChooserDialogFragment onCreateChooserDialogFragment = this.mDialogFactory.onCreateChooserDialogFragment();
            onCreateChooserDialogFragment.setRouteSelector(this.mSelector);
            onCreateChooserDialogFragment.show(fragmentManager, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment");
        }
        else {
            if (fragmentManager.findFragmentByTag("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") != null) {
                Log.w("MediaRouteButton", "showDialog(): Route controller dialog already showing!");
                return false;
            }
            this.mDialogFactory.onCreateControllerDialogFragment().show(fragmentManager, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment");
        }
        return true;
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mRemoteIndicator;
    }
    
    private final class MediaRouterCallback extends Callback
    {
        @Override
        public void onProviderAdded(final MediaRouter mediaRouter, final ProviderInfo providerInfo) {
            MediaRouteButton.this.refreshRoute();
        }
        
        @Override
        public void onProviderChanged(final MediaRouter mediaRouter, final ProviderInfo providerInfo) {
            MediaRouteButton.this.refreshRoute();
        }
        
        @Override
        public void onProviderRemoved(final MediaRouter mediaRouter, final ProviderInfo providerInfo) {
            MediaRouteButton.this.refreshRoute();
        }
        
        @Override
        public void onRouteAdded(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }
        
        @Override
        public void onRouteChanged(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }
        
        @Override
        public void onRouteRemoved(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }
        
        @Override
        public void onRouteSelected(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }
        
        @Override
        public void onRouteUnselected(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }
    }
}
