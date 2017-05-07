// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.content.DialogInterface;
import android.app.Activity;
import android.text.Html;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.Log;
import android.os.Parcelable;
import android.os.Bundle;
import java.util.Set;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.HashSet;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.ArrayList;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class SendAsFacebookMessageDialog extends NetflixDialogFrag
{
    private static final String FRIENDS_SET = "friends_set";
    private static final String FULL_FRIENDS_LIST = "full_friends_list";
    private static final int MAX_AVATARS_ICONS = 3;
    private static final String MESSAGE = "message";
    private static final String TAG;
    private static final String VIDEO_ID = "video_id";
    private ArrayList<FriendForRecommendation> mAllFriends;
    private HashSet<FriendForRecommendation> mCheckedFriends;
    private TextView mDlgBodyTextView;
    private ViewGroup mFriendsAvatarLayout;
    private LayoutInflater mLayoutInflater;
    private String mMessage;
    private ServiceManager mServiceManager;
    private boolean mUIWasUpdated;
    private String mVideoID;
    
    static {
        TAG = SendAsFacebookMessageDialog.class.getSimpleName();
    }
    
    public SendAsFacebookMessageDialog() {
        this.mCheckedFriends = new HashSet<FriendForRecommendation>();
    }
    
    private ArrayList<FriendForRecommendation> findFirstFacebookProfile() {
        final ArrayList<FriendForRecommendation> list = new ArrayList<FriendForRecommendation>();
        for (final FriendForRecommendation friendForRecommendation : this.mCheckedFriends) {
            if (!friendForRecommendation.isNetlflixConnected()) {
                list.add(friendForRecommendation);
            }
        }
        return list;
    }
    
    public static String getFacebookMsgOptInKey(final ServiceManager serviceManager) {
        String profileGuid;
        if (serviceManager == null || serviceManager.getCurrentProfile() == null) {
            profileGuid = "";
        }
        else {
            profileGuid = serviceManager.getCurrentProfile().getProfileGuid();
        }
        return "user_send_recommendation_via_facebook_approved" + profileGuid;
    }
    
    public static SendAsFacebookMessageDialog newInstance(final Set<FriendForRecommendation> set, final String s, final String s2, final ArrayList<FriendForRecommendation> list) {
        final SendAsFacebookMessageDialog sendAsFacebookMessageDialog = new SendAsFacebookMessageDialog();
        final Bundle arguments = new Bundle();
        arguments.putParcelableArray("friends_set", (Parcelable[])set.toArray((Parcelable[])new FriendForRecommendation[set.size()]));
        arguments.putString("video_id", s);
        arguments.putString("message", s2);
        arguments.putParcelableArrayList("full_friends_list", (ArrayList)list);
        sendAsFacebookMessageDialog.setArguments(arguments);
        return sendAsFacebookMessageDialog;
    }
    
    private void sendMsgAndDismiss() {
        this.mServiceManager.sendRecommendationsToFriends(this.mVideoID, this.mCheckedFriends, this.mMessage);
        this.dismiss();
    }
    
    private void tryUpdateUI() {
        if (this.mUIWasUpdated) {
            return;
        }
        if (this.getActivity() == null || this.mServiceManager == null || this.mFriendsAvatarLayout == null || this.mDlgBodyTextView == null) {
            Log.i(SendAsFacebookMessageDialog.TAG, "updateUI() was called too early - will pass with it now...");
            return;
        }
        final ArrayList<FriendForRecommendation> firstFacebookProfile = this.findFirstFacebookProfile();
        if (firstFacebookProfile.size() == 0) {
            Log.e(SendAsFacebookMessageDialog.TAG, "Got 0 facebook profiles!");
            return;
        }
        int n = 0;
        for (final FriendForRecommendation friendForRecommendation : firstFacebookProfile) {
            final ViewGroup viewGroup = (ViewGroup)this.mLayoutInflater.inflate(2130903180, (ViewGroup)null);
            this.mFriendsAvatarLayout.addView((View)viewGroup, 0);
            NetflixActivity.getImageLoader((Context)this.getActivity()).showImg((AdvancedImageView)viewGroup.findViewById(2131165625), friendForRecommendation.getFriendProfile().getImageUrl(), IClientLogging.AssetType.profileAvatar, friendForRecommendation.getFriendProfile().getFullName(), true, true);
            if (++n == 3) {
                break;
            }
        }
        this.mDlgBodyTextView.setText((CharSequence)Html.fromHtml(this.getActivity().getResources().getQuantityString(2131623939, firstFacebookProfile.size(), new Object[] { firstFacebookProfile.size() - 1, firstFacebookProfile.get(0).getFriendProfile().getFirstName() })));
        this.mUIWasUpdated = true;
    }
    
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.tryUpdateUI();
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        if (this.getActivity() != null && !this.getActivity().isFinishing() && this.getFragmentManager() != null) {
            RecommendToFriendsFrag.newInstance(this.mVideoID, this.mCheckedFriends, this.mMessage, this.mAllFriends).show(this.getFragmentManager(), "frag_dialog");
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        this.setStyle(1, 2131558710);
        super.onCreate(bundle);
        this.mVideoID = this.getArguments().getString("video_id");
        this.mMessage = this.getArguments().getString("message");
        RecommendToFriendsFrag.restoreSetFromArray(this.getArguments().getParcelableArray("friends_set"), this.mCheckedFriends);
        this.mAllFriends = (ArrayList<FriendForRecommendation>)this.getArguments().getParcelableArrayList("full_friends_list");
    }
    
    public View onCreateView(final LayoutInflater mLayoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v(SendAsFacebookMessageDialog.TAG, "Creating new frag view...");
        this.mLayoutInflater = mLayoutInflater;
        final View inflate = this.mLayoutInflater.inflate(2130903179, viewGroup, false);
        this.mFriendsAvatarLayout = (ViewGroup)inflate.findViewById(2131165648);
        this.mDlgBodyTextView = (TextView)inflate.findViewById(2131165649);
        ((Button)inflate.findViewById(2131165650)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                PreferenceUtils.putBooleanPref((Context)SendAsFacebookMessageDialog.this.getActivity(), SendAsFacebookMessageDialog.getFacebookMsgOptInKey(SendAsFacebookMessageDialog.this.mServiceManager), true);
                SendAsFacebookMessageDialog.this.sendMsgAndDismiss();
            }
        });
        ((Button)inflate.findViewById(2131165651)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final HashSet<FriendForRecommendation> set = new HashSet<FriendForRecommendation>();
                for (final FriendForRecommendation friendForRecommendation : SendAsFacebookMessageDialog.this.mCheckedFriends) {
                    if (friendForRecommendation.isNetlflixConnected()) {
                        set.add(friendForRecommendation);
                    }
                }
                SendAsFacebookMessageDialog.this.mCheckedFriends = set;
                SendAsFacebookMessageDialog.this.sendMsgAndDismiss();
            }
        });
        ((Button)inflate.findViewById(2131165652)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                SendAsFacebookMessageDialog.this.sendMsgAndDismiss();
            }
        });
        this.tryUpdateUI();
        this.getDialog().setCanceledOnTouchOutside(false);
        return inflate;
    }
    
    @Override
    public void onManagerReady(final ServiceManager mServiceManager, final Status status) {
        super.onManagerReady(mServiceManager, status);
        this.mServiceManager = mServiceManager;
        this.tryUpdateUI();
    }
}
