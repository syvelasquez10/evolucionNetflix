// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import android.content.DialogInterface;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewPropertyAnimator;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.TextView;
import android.text.TextWatcher;
import android.view.View$OnClickListener;
import android.content.Intent;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import android.widget.Toast;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.EditText;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.CheckBox;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import android.view.View;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class ProfileDetailsActivity extends NetflixActivity implements DialogInterface$OnClickListener
{
    private static final float ALPHA_CONTENT_FADE = 0.4f;
    private static final String EXTRA_PROFILE_ID = "extra_profile_id";
    public static final String EXTRA_SELECTED_AVATAR = "avatar_name";
    private static final String SAVE_BUNDLE_CURRENT_AVATAR = "bundle_current_avatar";
    private static final String SAVE_BUNDLE_DEFAULT_AVATAR = "bundle_default_avatar";
    private static final String SAVE_BUNDLE_KIDS = "bundle_kids";
    private static final String SAVE_BUNDLE_NAME = "bundle_name";
    private static final int SELECT_AVATAR_ACTIVITY_RESULT = 1;
    private static final String TAG = "ProfileDetailsActivity";
    private final ErrorWrapper$Callback errorCallback;
    private View mCancelButton;
    private View mContentView;
    private AvatarInfo mCurrentAvatar;
    private boolean mDataWasInitialized;
    private AvatarInfo mDefaultAvatar;
    private View mDeleteButton;
    private View mDeleteSection;
    private final ManagerCallback mErrorHandlerCallback;
    private UserProfile mInputProfile;
    private String mInputProfileId;
    private CheckBox mKidsCheckBox;
    private View mKidsSection;
    private LoadingAndErrorWrapper mLoadingWrapper;
    private EditText mName;
    private boolean mNewProfileCreation;
    private View mPictureSelectorHint;
    private boolean mProfileChangeRequestWasSent;
    private boolean mProfileDeletionWasTriggered;
    private View mProfilePictureSection;
    private AdvancedImageView mProfilePictureView;
    private View mSaveButton;
    private View mSaveButtonText;
    private ServiceManager mServiceManager;
    
    public ProfileDetailsActivity() {
        this.errorCallback = new ProfileDetailsActivity$2(this);
        this.mErrorHandlerCallback = new ProfileDetailsActivity$3(this);
    }
    
    private void cancel() {
        if (this.mNewProfileCreation) {
            UserActionLogUtils.reportAddProfileActionEnded((Context)this, IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.profilesGate, null, this.getProfileForLogging());
        }
        else {
            UserActionLogUtils.reportEditProfileActionEnded((Context)this, IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.profilesGate, null, this.getProfileForLogging());
        }
        if (!this.mProfileChangeRequestWasSent) {
            int n;
            if (this.mNewProfileCreation) {
                n = 2131165648;
            }
            else {
                n = 2131165659;
            }
            Toast.makeText((Context)this, n, 1).show();
        }
        this.finish();
    }
    
    private boolean findErrorsBeforeSubmit() {
        this.mName.setError((CharSequence)null);
        if (this.mServiceManager == null) {
            Log.e("ProfileDetailsActivity", "Manager isn't available!");
            return true;
        }
        if (this.mCurrentAvatar == null) {
            Log.e("ProfileDetailsActivity", "Current avatar isn't ready yet, but Save button was clicked!");
            return true;
        }
        final String string = this.mName.getText().toString();
        if (string.contains("\"") || string.contains("<") || string.contains(">")) {
            this.mName.setError((CharSequence)this.getString(2131165667));
            return true;
        }
        for (final UserProfile userProfile : this.mServiceManager.getAllProfiles()) {
            if (string.equalsIgnoreCase(userProfile.getProfileName()) && !userProfile.getProfileGuid().equals(this.mInputProfileId)) {
                this.mName.setError((CharSequence)this.getString(2131165656));
                return true;
            }
        }
        return false;
    }
    
    private UserActionLogging$Profile getProfileForLogging() {
        return new UserActionLogging$Profile(this.mInputProfileId, this.mName.getText().toString(), null, this.mKidsCheckBox.isChecked());
    }
    
    public static Intent getStartIntent(final Context context, final String s) {
        final Intent intent = new Intent(context, (Class)ProfileDetailsActivity.class);
        if (s != null) {
            intent.putExtra("extra_profile_id", s);
        }
        return intent;
    }
    
    private void initUI() {
        this.setContentView(2130903193);
        this.getSupportActionBar().hide();
        this.mContentView = this.findViewById(2131624449);
        this.mLoadingWrapper = new LoadingAndErrorWrapper(this.findViewById(2131624448), this.errorCallback);
        (this.mCancelButton = this.findViewById(2131624457)).setOnClickListener((View$OnClickListener)new ProfileDetailsActivity$4(this));
        this.mDeleteSection = this.findViewById(2131624455);
        (this.mDeleteButton = this.findViewById(2131624456)).setOnClickListener((View$OnClickListener)new ProfileDetailsActivity$5(this));
        (this.mKidsSection = this.findViewById(2131624451)).setOnClickListener((View$OnClickListener)new ProfileDetailsActivity$6(this));
        this.mKidsCheckBox = (CheckBox)this.findViewById(2131624452);
        this.mSaveButtonText = this.findViewById(2131624459);
        (this.mSaveButton = this.findViewById(2131624458)).setOnClickListener((View$OnClickListener)new ProfileDetailsActivity$7(this));
        (this.mName = (EditText)this.findViewById(2131624450)).addTextChangedListener((TextWatcher)new ProfileDetailsActivity$8(this));
        this.mProfilePictureView = (AdvancedImageView)this.findViewById(2131624066);
        final ProfileDetailsActivity$9 profileDetailsActivity$9 = new ProfileDetailsActivity$9(this);
        this.mProfilePictureView.setOnClickListener((View$OnClickListener)profileDetailsActivity$9);
        (this.mProfilePictureSection = this.findViewById(2131624453)).setOnClickListener((View$OnClickListener)profileDetailsActivity$9);
        this.mPictureSelectorHint = this.findViewById(2131624454);
        if (this.mNewProfileCreation) {
            this.mName.requestFocus();
        }
        final TextView textView = (TextView)this.findViewById(2131624460);
        if (textView != null) {
            int text;
            if (this.mNewProfileCreation) {
                text = 2131165647;
            }
            else {
                text = 2131165660;
            }
            textView.setText(text);
        }
    }
    
    private boolean isCurrentAvatarDataValid() {
        return this.mCurrentAvatar != null && StringUtils.isNotEmpty(this.mCurrentAvatar.getUrl()) && StringUtils.isNotEmpty(this.mCurrentAvatar.getName());
    }
    
    private boolean isNameFieldValid() {
        return this.mName.getText().length() > 0;
    }
    
    private boolean isUserDataValid() {
        return this.isNameFieldValid() && this.mServiceManager != null && this.mCurrentAvatar != null;
    }
    
    private void removeFocusAndHideKeyboard() {
        this.mName.clearFocus();
        DeviceUtils.forceHideKeyboard(this, this.mName);
    }
    
    private void showDeleteProfileSection(final boolean b) {
        final View mDeleteSection = this.mDeleteSection;
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        mDeleteSection.setVisibility(visibility);
    }
    
    private void showLoading(final boolean b, final boolean b2) {
        float alpha = 0.4f;
        final boolean b3 = true;
        if (b) {
            this.mLoadingWrapper.showLoadingView(true);
        }
        else {
            this.mLoadingWrapper.hide(true);
        }
        this.mContentView.setEnabled(!b);
        this.mName.setEnabled(!b);
        this.updateSaveButton(!b);
        this.mCancelButton.setEnabled(!b);
        final View mCancelButton = this.mCancelButton;
        float alpha2;
        if (b) {
            alpha2 = 0.4f;
        }
        else {
            alpha2 = 1.0f;
        }
        mCancelButton.setAlpha(alpha2);
        this.mDeleteButton.setEnabled(!b);
        this.mKidsCheckBox.setEnabled(!b);
        this.mKidsSection.setEnabled(!b);
        final boolean b4 = this.isCurrentAvatarDataValid() && !b && b3;
        this.mProfilePictureView.setEnabled(b4);
        this.mProfilePictureSection.setEnabled(b4);
        if (b2) {
            final ViewPropertyAnimator animate = this.mContentView.animate();
            if (!b) {
                alpha = 1.0f;
            }
            animate.alpha(alpha).setDuration(400L).start();
            return;
        }
        final View mContentView = this.mContentView;
        if (!b) {
            alpha = 1.0f;
        }
        mContentView.setAlpha(alpha);
    }
    
    private void updateInputProfile() {
        this.mInputProfile = null;
        if (this.mInputProfileId != null) {
            for (final UserProfile mInputProfile : this.mServiceManager.getAllProfiles()) {
                if (mInputProfile.getProfileGuid().equals(this.mInputProfileId)) {
                    this.mInputProfile = mInputProfile;
                    break;
                }
            }
            if (this.mInputProfile == null) {
                this.mInputProfileId = null;
                this.mNewProfileCreation = true;
            }
        }
    }
    
    private void updateSaveButton(final boolean b) {
        final boolean b2 = this.isUserDataValid() && b;
        this.mSaveButton.setEnabled(b2);
        this.mSaveButtonText.setEnabled(b2);
    }
    
    private void updateUI() {
        this.showDeleteProfileSection(this.mInputProfileId != null && this.mInputProfile != null && !this.mInputProfile.isPrimaryProfile());
        if (!this.mNewProfileCreation && this.mInputProfile != null && !this.mDataWasInitialized) {
            this.mName.setText((CharSequence)this.mInputProfile.getProfileName());
            this.mKidsCheckBox.setChecked(this.mInputProfile.isKidsProfile());
            this.mDataWasInitialized = true;
        }
        int n;
        if (this.mServiceManager != null) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            this.showLoading(true, false);
        }
        else {
            this.showLoading(false, true);
            if (this.isCurrentAvatarDataValid()) {
                NetflixActivity.getImageLoader((Context)this).showImg(this.mProfilePictureView, this.mCurrentAvatar.getUrl(), IClientLogging$AssetType.profileAvatar, this.mCurrentAvatar.getName(), ImageLoader$StaticImgConfig.DARK, true);
            }
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ProfileDetailsActivity$1(this);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.profileDetails;
    }
    
    public boolean handleBackPressed() {
        this.cancel();
        return true;
    }
    
    @Override
    protected void handleProfilesListUpdated() {
        Log.d("ProfileDetailsActivity", "handleProfilesListUpdated");
        this.finish();
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1 && n2 == -1) {
            this.mCurrentAvatar = (AvatarInfo)intent.getParcelableExtra("avatar_name");
            Log.e("ProfileDetailsActivity", "Got url: " + this.mCurrentAvatar);
            this.updateUI();
        }
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (n == -1) {
            if (this.mInputProfile != null) {
                this.showLoading(true, true);
                this.mServiceManager.removeProfile(this.mInputProfile.getProfileGuid(), this.mErrorHandlerCallback);
                this.mProfileChangeRequestWasSent = true;
                this.mProfileDeletionWasTriggered = true;
                return;
            }
            Log.e("ProfileDetailsActivity", "Weird use case: profile deletion needs to be started, but input profile is null");
            final NetflixStatus netflixStatus = new NetflixStatus(StatusCode.NETWORK_ERROR);
            final String handleUserAgentErrors = this.handleUserAgentErrors(netflixStatus);
            ActionOnUIError actionOnUIError = ActionOnUIError.displayedError;
            if (handleUserAgentErrors == null) {
                actionOnUIError = ActionOnUIError.handledSilently;
            }
            UserActionLogUtils.reportDeleteProfileActionEnded((Context)this, IClientLogging$CompletionReason.failed, IClientLogging$ModalView.profilesGate, ConsolidatedLoggingUtils.createUIError(netflixStatus, handleUserAgentErrors, actionOnUIError));
        }
        else {
            if (n == -2) {
                Log.i("ProfileDetailsActivity", "Negative dialog button was clicked");
                UserActionLogUtils.reportDeleteProfileActionEnded((Context)this, IClientLogging$CompletionReason.canceled, this.getUiScreen(), null);
                UserActionLogUtils.reportEditProfileActionStarted((Context)this, null, this.getUiScreen());
                return;
            }
            Log.e("ProfileDetailsActivity", "Unhandled dialog button was clicked");
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Intent intent = this.getIntent();
        if (intent != null && intent.hasExtra("extra_profile_id")) {
            this.mInputProfileId = intent.getStringExtra("extra_profile_id");
        }
        else {
            this.mNewProfileCreation = true;
        }
        this.initUI();
        if (bundle != null && bundle.containsKey("bundle_name") && bundle.containsKey("bundle_kids") && bundle.containsKey("bundle_default_avatar") && bundle.containsKey("bundle_current_avatar")) {
            this.mName.setText((CharSequence)bundle.getString("bundle_name"));
            this.mKidsCheckBox.setChecked(bundle.getBoolean("bundle_kids"));
            this.mDefaultAvatar = (AvatarInfo)bundle.getParcelable("bundle_default_avatar");
            this.mCurrentAvatar = (AvatarInfo)bundle.getParcelable("bundle_current_avatar");
            this.mDataWasInitialized = true;
        }
        this.updateUI();
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("bundle_name", this.mName.getText().toString());
        bundle.putBoolean("bundle_kids", this.mKidsCheckBox.isChecked());
        bundle.putParcelable("bundle_default_avatar", (Parcelable)this.mDefaultAvatar);
        bundle.putParcelable("bundle_current_avatar", (Parcelable)this.mCurrentAvatar);
    }
}
