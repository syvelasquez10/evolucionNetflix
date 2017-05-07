// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.Status;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.app.Dialog;
import com.netflix.mediaclient.util.SocialUtils;
import android.content.DialogInterface;
import android.app.Activity;
import android.text.Html;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
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
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.HashSet;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.ArrayList;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class SendAsFacebookMessageDialog extends NetflixDialogFrag
{
    private static final String FRIENDS_SET = "friends_set";
    private static final String FULL_FRIENDS_LIST = "full_friends_list";
    private static final String GUID = "guid";
    private static final int MAX_AVATARS_ICONS = 3;
    private static final String MESSAGE = "message";
    private static final String TAG;
    private static final String VIDEO_ID = "video_id";
    private ArrayList<FriendForRecommendation> mAllFriends;
    private HashSet<FriendForRecommendation> mCheckedFriends;
    private TextView mDlgBodyTextView;
    private CheckBox mDontaskCheckBox;
    private ViewGroup mFriendsAvatarLayout;
    private String mGUID;
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
    
    public static SendAsFacebookMessageDialog newInstance(final Set<FriendForRecommendation> set, final String s, final String s2, final String s3, final ArrayList<FriendForRecommendation> list) {
        final SendAsFacebookMessageDialog sendAsFacebookMessageDialog = new SendAsFacebookMessageDialog();
        final Bundle arguments = new Bundle();
        arguments.putParcelableArray("friends_set", (Parcelable[])set.toArray((Parcelable[])new FriendForRecommendation[set.size()]));
        arguments.putString("video_id", s);
        arguments.putString("message", s2);
        arguments.putString("guid", s3);
        arguments.putParcelableArrayList("full_friends_list", (ArrayList)list);
        sendAsFacebookMessageDialog.setArguments(arguments);
        return sendAsFacebookMessageDialog;
    }
    
    private void sendMsgAndDismiss() {
        this.mServiceManager.sendRecommendationsToFriends(this.mVideoID, this.mCheckedFriends, this.mMessage, this.mGUID);
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
        final Iterator<FriendForRecommendation> iterator = firstFacebookProfile.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final FriendForRecommendation friendForRecommendation = iterator.next();
            final ViewGroup viewGroup = (ViewGroup)this.mLayoutInflater.inflate(2130903189, (ViewGroup)null);
            this.mFriendsAvatarLayout.addView((View)viewGroup, 0);
            NetflixActivity.getImageLoader((Context)this.getActivity()).showImg((AdvancedImageView)viewGroup.findViewById(2131165641), friendForRecommendation.getFriendProfile().getImageUrl(), IClientLogging$AssetType.profileAvatar, friendForRecommendation.getFriendProfile().getFullName(), true, true);
            ++n;
            if (n == 3) {
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
            RecommendToFriendsFrag.newInstance(this.mVideoID, this.mCheckedFriends, this.mMessage, this.mGUID, this.mAllFriends).show(this.getFragmentManager(), "frag_dialog");
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mVideoID = this.getArguments().getString("video_id");
        this.mMessage = this.getArguments().getString("message");
        this.mGUID = this.getArguments().getString("guid");
        SocialUtils.castArrayToSet(this.getArguments().getParcelableArray("friends_set"), this.mCheckedFriends);
        this.mAllFriends = (ArrayList<FriendForRecommendation>)this.getArguments().getParcelableArrayList("full_friends_list");
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        this.mLayoutInflater = this.getActivity().getLayoutInflater();
        final View inflate = this.mLayoutInflater.inflate(2130903188, (ViewGroup)null);
        this.mFriendsAvatarLayout = (ViewGroup)inflate.findViewById(2131165663);
        this.mDlgBodyTextView = (TextView)inflate.findViewById(2131165664);
        (this.mDontaskCheckBox = (CheckBox)inflate.findViewById(2131165665)).setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new SendAsFacebookMessageDialog$1(this));
        this.tryUpdateUI();
        return (Dialog)new AlertDialog$Builder((Context)this.getActivity()).setPositiveButton(2131492988, (DialogInterface$OnClickListener)new SendAsFacebookMessageDialog$3(this)).setNegativeButton(2131493351, (DialogInterface$OnClickListener)new SendAsFacebookMessageDialog$2(this)).setView(inflate).setCancelable(false).create();
    }
    
    @Override
    public void onManagerReady(final ServiceManager mServiceManager, final Status status) {
        super.onManagerReady(mServiceManager, status);
        this.mServiceManager = mServiceManager;
        this.tryUpdateUI();
    }
}
