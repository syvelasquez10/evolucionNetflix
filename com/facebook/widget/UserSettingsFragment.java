// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.SessionState;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Intent;
import java.util.List;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionDefaultAudience;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.net.MalformedURLException;
import android.content.Context;
import com.facebook.android.R;
import android.os.Bundle;
import com.facebook.Response;
import com.facebook.Request;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import com.facebook.model.GraphUser;
import com.facebook.Session;
import android.widget.TextView;

public class UserSettingsFragment extends FacebookFragment
{
    private static final String FIELDS = "fields";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PICTURE = "picture";
    private static final String REQUEST_FIELDS;
    private TextView connectedStateLabel;
    private LoginButton loginButton;
    private LoginButton.LoginButtonProperties loginButtonProperties;
    private Session.StatusCallback sessionStatusCallback;
    private GraphUser user;
    private Session userInfoSession;
    private Drawable userProfilePic;
    private String userProfilePicID;
    
    static {
        REQUEST_FIELDS = TextUtils.join((CharSequence)",", (Object[])new String[] { "id", "name", "picture" });
    }
    
    public UserSettingsFragment() {
        this.loginButtonProperties = new LoginButton.LoginButtonProperties();
    }
    
    private void fetchUserInfo() {
        final Session session = this.getSession();
        if (session != null && session.isOpened()) {
            if (session != this.userInfoSession) {
                final Request meRequest = Request.newMeRequest(session, (Request.GraphUserCallback)new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(final GraphUser graphUser, final Response response) {
                        if (session == UserSettingsFragment.this.getSession()) {
                            UserSettingsFragment.this.user = graphUser;
                            UserSettingsFragment.this.updateUI();
                        }
                        if (response.getError() != null) {
                            UserSettingsFragment.this.loginButton.handleError(response.getError().getException());
                        }
                    }
                });
                final Bundle parameters = new Bundle();
                parameters.putString("fields", UserSettingsFragment.REQUEST_FIELDS);
                meRequest.setParameters(parameters);
                Request.executeBatchAsync(meRequest);
                this.userInfoSession = session;
            }
            return;
        }
        this.user = null;
    }
    
    private ImageRequest getImageRequest() {
        try {
            return new ImageRequest.Builder((Context)this.getActivity(), ImageRequest.getProfilePictureUrl(this.user.getId(), this.getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_width), this.getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_height))).setCallerTag(this).setCallback(new ImageRequest.Callback() {
                @Override
                public void onCompleted(final ImageResponse imageResponse) {
                    UserSettingsFragment.this.processImageResponse(UserSettingsFragment.this.user.getId(), imageResponse);
                }
            }).build();
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    private void processImageResponse(final String userProfilePicID, final ImageResponse imageResponse) {
        if (imageResponse != null) {
            final Bitmap bitmap = imageResponse.getBitmap();
            if (bitmap != null) {
                final BitmapDrawable userProfilePic = new BitmapDrawable(this.getResources(), bitmap);
                userProfilePic.setBounds(0, 0, this.getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_width), this.getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_height));
                this.userProfilePic = (Drawable)userProfilePic;
                this.userProfilePicID = userProfilePicID;
                this.connectedStateLabel.setCompoundDrawables((Drawable)null, (Drawable)userProfilePic, (Drawable)null, (Drawable)null);
                this.connectedStateLabel.setTag((Object)imageResponse.getRequest().getImageUrl());
            }
        }
    }
    
    private void updateUI() {
        if (!this.isAdded()) {
            return;
        }
        if (!this.isSessionOpen()) {
            final int color = this.getResources().getColor(R.color.com_facebook_usersettingsfragment_not_connected_text_color);
            this.connectedStateLabel.setTextColor(color);
            this.connectedStateLabel.setShadowLayer(0.0f, 0.0f, 0.0f, color);
            this.connectedStateLabel.setText((CharSequence)this.getResources().getString(R.string.com_facebook_usersettingsfragment_not_logged_in));
            this.connectedStateLabel.setCompoundDrawables((Drawable)null, (Drawable)null, (Drawable)null, (Drawable)null);
            this.connectedStateLabel.setTag((Object)null);
            return;
        }
        this.connectedStateLabel.setTextColor(this.getResources().getColor(R.color.com_facebook_usersettingsfragment_connected_text_color));
        this.connectedStateLabel.setShadowLayer(1.0f, 0.0f, -1.0f, this.getResources().getColor(R.color.com_facebook_usersettingsfragment_connected_shadow_color));
        if (this.user != null) {
            final ImageRequest imageRequest = this.getImageRequest();
            if (imageRequest != null) {
                final URL imageUrl = imageRequest.getImageUrl();
                if (!imageUrl.equals(this.connectedStateLabel.getTag())) {
                    if (this.user.getId().equals(this.userProfilePicID)) {
                        this.connectedStateLabel.setCompoundDrawables((Drawable)null, this.userProfilePic, (Drawable)null, (Drawable)null);
                        this.connectedStateLabel.setTag((Object)imageUrl);
                    }
                    else {
                        ImageDownloader.downloadAsync(imageRequest);
                    }
                }
            }
            this.connectedStateLabel.setText((CharSequence)this.user.getName());
            return;
        }
        this.connectedStateLabel.setText((CharSequence)this.getResources().getString(R.string.com_facebook_usersettingsfragment_logged_in));
        final Drawable drawable = this.getResources().getDrawable(R.drawable.com_facebook_profile_default_icon);
        drawable.setBounds(0, 0, this.getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_width), this.getResources().getDimensionPixelSize(R.dimen.com_facebook_usersettingsfragment_profile_picture_height));
        this.connectedStateLabel.setCompoundDrawables((Drawable)null, drawable, (Drawable)null, (Drawable)null);
    }
    
    public void clearPermissions() {
        this.loginButtonProperties.clearPermissions();
    }
    
    public SessionDefaultAudience getDefaultAudience() {
        return this.loginButtonProperties.getDefaultAudience();
    }
    
    public SessionLoginBehavior getLoginBehavior() {
        return this.loginButtonProperties.getLoginBehavior();
    }
    
    public LoginButton.OnErrorListener getOnErrorListener() {
        return this.loginButtonProperties.getOnErrorListener();
    }
    
    List<String> getPermissions() {
        return this.loginButtonProperties.getPermissions();
    }
    
    public Session.StatusCallback getSessionStatusCallback() {
        return this.sessionStatusCallback;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setRetainInstance(true);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R.layout.com_facebook_usersettingsfragment, viewGroup, false);
        (this.loginButton = (LoginButton)inflate.findViewById(R.id.com_facebook_usersettingsfragment_login_button)).setProperties(this.loginButtonProperties);
        this.loginButton.setFragment(this);
        final Session session = this.getSession();
        if (session != null && !session.equals(Session.getActiveSession())) {
            this.loginButton.setSession(session);
        }
        this.connectedStateLabel = (TextView)inflate.findViewById(R.id.com_facebook_usersettingsfragment_profile_name);
        if (inflate.getBackground() == null) {
            inflate.setBackgroundColor(this.getResources().getColor(R.color.com_facebook_blue));
            return inflate;
        }
        inflate.getBackground().setDither(true);
        return inflate;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.fetchUserInfo();
        this.updateUI();
    }
    
    @Override
    protected void onSessionStateChange(final SessionState sessionState, final Exception ex) {
        this.fetchUserInfo();
        this.updateUI();
        if (this.sessionStatusCallback != null) {
            this.sessionStatusCallback.call(this.getSession(), sessionState, ex);
        }
    }
    
    public void setDefaultAudience(final SessionDefaultAudience defaultAudience) {
        this.loginButtonProperties.setDefaultAudience(defaultAudience);
    }
    
    public void setLoginBehavior(final SessionLoginBehavior loginBehavior) {
        this.loginButtonProperties.setLoginBehavior(loginBehavior);
    }
    
    public void setOnErrorListener(final LoginButton.OnErrorListener onErrorListener) {
        this.loginButtonProperties.setOnErrorListener(onErrorListener);
    }
    
    public void setPublishPermissions(final List<String> list) {
        this.loginButtonProperties.setPublishPermissions(list, this.getSession());
    }
    
    public void setReadPermissions(final List<String> list) {
        this.loginButtonProperties.setReadPermissions(list, this.getSession());
    }
    
    @Override
    public void setSession(final Session session) {
        super.setSession(session);
        if (this.loginButton != null) {
            this.loginButton.setSession(session);
        }
        this.fetchUserInfo();
        this.updateUI();
    }
    
    public void setSessionStatusCallback(final Session.StatusCallback sessionStatusCallback) {
        this.sessionStatusCallback = sessionStatusCallback;
    }
}
