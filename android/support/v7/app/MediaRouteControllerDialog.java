// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.os.Bundle;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.mediarouter.R;
import android.content.Context;
import android.widget.SeekBar;
import android.widget.LinearLayout;
import android.support.v7.media.MediaRouter;
import android.widget.Button;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.app.Dialog;

public class MediaRouteControllerDialog extends Dialog
{
    private static final String TAG = "MediaRouteControllerDialog";
    private static final int VOLUME_UPDATE_DELAY_MILLIS = 250;
    private final MediaRouterCallback mCallback;
    private View mControlView;
    private boolean mCreated;
    private Drawable mCurrentIconDrawable;
    private Button mDisconnectButton;
    private Drawable mMediaRouteConnectingDrawable;
    private Drawable mMediaRouteOnDrawable;
    private final MediaRouter.RouteInfo mRoute;
    private final MediaRouter mRouter;
    private boolean mVolumeControlEnabled;
    private LinearLayout mVolumeLayout;
    private SeekBar mVolumeSlider;
    private boolean mVolumeSliderTouched;
    
    public MediaRouteControllerDialog(final Context context) {
        this(context, 0);
    }
    
    public MediaRouteControllerDialog(final Context context, final int n) {
        super(MediaRouterThemeHelper.createThemedContext(context, true), n);
        this.mVolumeControlEnabled = true;
        this.mRouter = MediaRouter.getInstance(this.getContext());
        this.mCallback = new MediaRouterCallback();
        this.mRoute = this.mRouter.getSelectedRoute();
    }
    
    private Drawable getIconDrawable() {
        if (this.mRoute.isConnecting()) {
            if (this.mMediaRouteConnectingDrawable == null) {
                this.mMediaRouteConnectingDrawable = MediaRouterThemeHelper.getThemeDrawable(this.getContext(), R.attr.mediaRouteConnectingDrawable);
            }
            return this.mMediaRouteConnectingDrawable;
        }
        if (this.mMediaRouteOnDrawable == null) {
            this.mMediaRouteOnDrawable = MediaRouterThemeHelper.getThemeDrawable(this.getContext(), R.attr.mediaRouteOnDrawable);
        }
        return this.mMediaRouteOnDrawable;
    }
    
    private boolean isVolumeControlAvailable() {
        return this.mVolumeControlEnabled && this.mRoute.getVolumeHandling() == 1;
    }
    
    private boolean update() {
        boolean b = true;
        if (!this.mRoute.isSelected() || this.mRoute.isDefault()) {
            this.dismiss();
            b = false;
        }
        else {
            this.setTitle((CharSequence)this.mRoute.getName());
            this.updateVolume();
            final Drawable iconDrawable = this.getIconDrawable();
            if (iconDrawable != this.mCurrentIconDrawable) {
                (this.mCurrentIconDrawable = iconDrawable).setVisible(false, true);
                this.getWindow().setFeatureDrawable(3, iconDrawable);
                return true;
            }
        }
        return b;
    }
    
    private void updateVolume() {
        if (!this.mVolumeSliderTouched) {
            if (!this.isVolumeControlAvailable()) {
                this.mVolumeLayout.setVisibility(8);
                return;
            }
            this.mVolumeLayout.setVisibility(0);
            this.mVolumeSlider.setMax(this.mRoute.getVolumeMax());
            this.mVolumeSlider.setProgress(this.mRoute.getVolume());
        }
    }
    
    public View getMediaControlView() {
        return this.mControlView;
    }
    
    public MediaRouter.RouteInfo getRoute() {
        return this.mRoute;
    }
    
    public boolean isVolumeControlEnabled() {
        return this.mVolumeControlEnabled;
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mRouter.addCallback(MediaRouteSelector.EMPTY, (MediaRouter.Callback)this.mCallback, 2);
        this.update();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getWindow().requestFeature(3);
        this.setContentView(R.layout.mr_media_route_controller_dialog);
        this.mVolumeLayout = (LinearLayout)this.findViewById(R.id.media_route_volume_layout);
        (this.mVolumeSlider = (SeekBar)this.findViewById(R.id.media_route_volume_slider)).setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)new SeekBar$OnSeekBarChangeListener() {
            private final Runnable mStopTrackingTouch = new Runnable() {
                @Override
                public void run() {
                    if (MediaRouteControllerDialog.this.mVolumeSliderTouched) {
                        MediaRouteControllerDialog.this.mVolumeSliderTouched = false;
                        MediaRouteControllerDialog.this.updateVolume();
                    }
                }
            };
            
            public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
                if (b) {
                    MediaRouteControllerDialog.this.mRoute.requestSetVolume(n);
                }
            }
            
            public void onStartTrackingTouch(final SeekBar seekBar) {
                if (MediaRouteControllerDialog.this.mVolumeSliderTouched) {
                    MediaRouteControllerDialog.this.mVolumeSlider.removeCallbacks(this.mStopTrackingTouch);
                    return;
                }
                MediaRouteControllerDialog.this.mVolumeSliderTouched = true;
            }
            
            public void onStopTrackingTouch(final SeekBar seekBar) {
                MediaRouteControllerDialog.this.mVolumeSlider.postDelayed(this.mStopTrackingTouch, 250L);
            }
        });
        (this.mDisconnectButton = (Button)this.findViewById(R.id.media_route_disconnect_button)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (MediaRouteControllerDialog.this.mRoute.isSelected()) {
                    MediaRouteControllerDialog.this.mRouter.getDefaultRoute().select();
                }
                MediaRouteControllerDialog.this.dismiss();
            }
        });
        this.mCreated = true;
        if (this.update()) {
            this.mControlView = this.onCreateMediaControlView(bundle);
            final FrameLayout frameLayout = (FrameLayout)this.findViewById(R.id.media_route_control_frame);
            if (this.mControlView == null) {
                frameLayout.setVisibility(8);
                return;
            }
            frameLayout.addView(this.mControlView);
            frameLayout.setVisibility(0);
        }
    }
    
    public View onCreateMediaControlView(final Bundle bundle) {
        return null;
    }
    
    public void onDetachedFromWindow() {
        this.mRouter.removeCallback((MediaRouter.Callback)this.mCallback);
        super.onDetachedFromWindow();
    }
    
    public boolean onKeyDown(int n, final KeyEvent keyEvent) {
        if (n == 25 || n == 24) {
            final MediaRouter.RouteInfo mRoute = this.mRoute;
            if (n == 25) {
                n = -1;
            }
            else {
                n = 1;
            }
            mRoute.requestUpdateVolume(n);
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        return n == 25 || n == 24 || super.onKeyUp(n, keyEvent);
    }
    
    public void setVolumeControlEnabled(final boolean mVolumeControlEnabled) {
        if (this.mVolumeControlEnabled != mVolumeControlEnabled) {
            this.mVolumeControlEnabled = mVolumeControlEnabled;
            if (this.mCreated) {
                this.updateVolume();
            }
        }
    }
    
    private final class MediaRouterCallback extends Callback
    {
        @Override
        public void onRouteChanged(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteControllerDialog.this.update();
        }
        
        @Override
        public void onRouteUnselected(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            MediaRouteControllerDialog.this.update();
        }
        
        @Override
        public void onRouteVolumeChanged(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
            if (routeInfo == MediaRouteControllerDialog.this.mRoute) {
                MediaRouteControllerDialog.this.updateVolume();
            }
        }
    }
}
