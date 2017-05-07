// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListAdapter;
import android.view.View$OnClickListener;
import android.text.TextWatcher;
import android.widget.AbsListView$OnScrollListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.os.Parcelable;
import android.os.Bundle;
import java.util.Set;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.content.Context;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.text.Html;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.EditText;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class RecommendToFriendsFrag extends NetflixDialogFrag
{
    private static final String BUNDLE_EXTRA_FRIENDS_LIST = "friends_list";
    private static final String BUNDLE_EXTRA_HAS_LOAD_MORE = "has_load_more_list";
    private static final String BUNDLE_EXTRA_SELECTED_FRIENDS_LIST = "selected_friends_list";
    private static final String FRIENDS_LIST_ARGS = "friends";
    private static final String GUID_ARGS = "guid";
    private static final String MESSAGE_TEXT_ARGS = "message";
    private static final String SELECTED_SET_ARGS = "selected_set";
    private static final String TAG;
    private static final int TOTAL_SELECTED_LAYOUT_ANIMATION = 300;
    private static final String VIDEO_ID_ARGS = "video_id";
    private LoadingAndErrorWrapper leWrapper;
    private RecommendToFriendsFrag$FriendsListAdapter mAdapter;
    private final HashSet<FriendForRecommendation> mCheckedFriends;
    private EditText mEditMessage;
    private boolean mErrorOccurred;
    private ArrayList<FriendForRecommendation> mFriends;
    protected ListView mFriendsList;
    private String mGUID;
    private String mInputMessage;
    private int mLastRequestId;
    private LayoutInflater mLayoutInflater;
    private boolean mLoadMoreAvailable;
    private View mSearchClearButton;
    private EditText mSearchEditText;
    private Animation mSearchEditTextAnim;
    private String mSearchTerm;
    private Button mSendButton;
    private ServiceManager mServiceManager;
    private View mTotalSelectedLayout;
    private Animation mTotalSelectedLayoutAnim;
    private boolean mTotalSelectedLayoutShown;
    private TextView mTotalSelectedStatus;
    private int mTrackId;
    private final ErrorWrapper$Callback retryFetchFriendsCallback;
    
    static {
        TAG = RecommendToFriendsFrag.class.getSimpleName();
    }
    
    public RecommendToFriendsFrag() {
        this.mCheckedFriends = new HashSet<FriendForRecommendation>();
        this.mLoadMoreAvailable = true;
        this.mTrackId = UIViewLogUtils.MISSING_TRACK_ID;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.retryFetchFriendsCallback = new RecommendToFriendsFrag$1(this);
    }
    
    private boolean checkForError(final Status status) {
        if (!this.isAdded() || this.getActivity() == null || this.getActivity().isFinishing()) {
            Log.w(RecommendToFriendsFrag.TAG, "Fragment is not attached already - no need to handle a request");
        }
        else {
            this.mErrorOccurred = false;
            if (this.leWrapper != null) {
                this.leWrapper.hide(false);
            }
            if (status.getStatusCode() == StatusCode.NETWORK_ERROR) {
                this.mErrorOccurred = true;
                if (this.leWrapper != null) {
                    this.leWrapper.showErrorView(this.getString(2131165670), 2131165585, this.retryFetchFriendsCallback);
                }
            }
            else if (status.getStatusCode() == StatusCode.USER_NOT_AUTHORIZED) {
                this.mErrorOccurred = true;
                if (this.leWrapper != null) {
                    this.leWrapper.showErrorView((CharSequence)Html.fromHtml(this.getString(2131165669)), 2131165421, new RecommendToFriendsFrag$RetryConnectFacebookCallback(this, this.getActivity()));
                }
            }
            if (!this.mErrorOccurred) {
                return false;
            }
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
                return true;
            }
        }
        return true;
    }
    
    private void fetchFriends() {
        if (this.mServiceManager != null) {
            if (this.leWrapper != null) {
                this.leWrapper.showLoadingView(false);
            }
            this.mLastRequestId = this.mServiceManager.fetchFriendsForRecommendationList(this.getArguments().getString("video_id"), 0, this.mSearchTerm, new RecommendToFriendsFrag$7(this));
        }
    }
    
    private String getGUID() {
        if (this.mGUID == UIViewLogUtils.MISSING_GUID) {
            this.mGUID = ConsolidatedLoggingUtils.createGUID();
        }
        return this.mGUID;
    }
    
    private int getTrackId(final Activity activity) {
        if (this.mTrackId == UIViewLogUtils.MISSING_TRACK_ID) {
            this.mTrackId = getTrackIdStatic(activity);
        }
        return this.mTrackId;
    }
    
    public static int getTrackIdStatic(final Activity activity) {
        final int missing_TRACK_ID = UIViewLogUtils.MISSING_TRACK_ID;
        if (activity instanceof DetailsActivity && ((DetailsActivity)activity).getPlayContext() != null) {
            return ((DetailsActivity)activity).getPlayContext().getTrackId();
        }
        if (Log.isLoggable()) {
            Log.e(RecommendToFriendsFrag.TAG, "Couldn't get track id from activity: " + activity);
        }
        return missing_TRACK_ID;
    }
    
    private boolean groupFriends() {
        if (this.mFriends != null && this.mCheckedFriends.size() > 0) {
            int i = 0;
            int n = 0;
            boolean b = false;
            while (i < this.mFriends.size()) {
                final FriendForRecommendation friendForRecommendation = this.mFriends.get(i);
                int n2 = n;
                boolean b2 = b;
                if (this.mCheckedFriends.contains(friendForRecommendation)) {
                    if (n < i) {
                        this.mFriends.remove(i);
                        this.mFriends.add(n, friendForRecommendation);
                        b = true;
                    }
                    n2 = n + 1;
                    b2 = b;
                }
                ++i;
                n = n2;
                b = b2;
            }
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
            return b;
        }
        return false;
    }
    
    private void handleManagerReady() {
        if (this.mFriends == null) {
            this.fetchFriends();
        }
        else {
            if (this.leWrapper != null) {
                this.leWrapper.hide(false);
            }
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }
    
    public static boolean isSocialRecommendationsFeatureSupported(final ServiceManager serviceManager) {
        return serviceManager != null && serviceManager.isReady() && serviceManager.getActivity() != null && isSocialRecommendationsFeatureSupported(serviceManager.getCurrentProfile(), (Context)serviceManager.getActivity());
    }
    
    public static boolean isSocialRecommendationsFeatureSupported(final UserProfile userProfile, final Context context) {
        return userProfile != null;
    }
    
    private void launchAnimation(final boolean b) {
        final int n = (int)this.getResources().getDimension(2131296619);
        if (this.mSearchEditTextAnim != null) {
            this.mSearchEditTextAnim.cancel();
        }
        if (this.mTotalSelectedLayoutAnim != null) {
            this.mTotalSelectedLayoutAnim.cancel();
        }
        float n2;
        if (b) {
            n2 = -n;
        }
        else {
            n2 = n;
        }
        this.mSearchEditTextAnim = (Animation)new TranslateAnimation(0.0f, n2, 0.0f, 0.0f);
        float n3;
        if (b) {
            n3 = 0.0f;
        }
        else {
            n3 = -n;
        }
        float n4;
        if (b) {
            n4 = -n;
        }
        else {
            n4 = 0.0f;
        }
        this.mTotalSelectedLayoutAnim = (Animation)new TranslateAnimation(n3, n4, 0.0f, 0.0f);
        this.mSearchEditTextAnim.setInterpolator((Interpolator)new AccelerateDecelerateInterpolator());
        this.mSearchEditTextAnim.setDuration(300L);
        this.mSearchEditTextAnim.setAnimationListener((Animation$AnimationListener)new RecommendToFriendsFrag$9(this, b, n));
        this.mTotalSelectedLayoutAnim.setInterpolator((Interpolator)new AccelerateDecelerateInterpolator());
        this.mTotalSelectedLayoutAnim.setDuration(300L);
        this.mSearchEditText.startAnimation(this.mSearchEditTextAnim);
        this.mTotalSelectedLayout.startAnimation(this.mTotalSelectedLayoutAnim);
    }
    
    private void loadMoreFriends() {
        if (this.mServiceManager != null && this.mFriends != null) {
            this.mServiceManager.fetchFriendsForRecommendationList(this.getArguments().getString("video_id"), this.mFriends.size(), this.mSearchTerm, new RecommendToFriendsFrag$8(this));
        }
    }
    
    public static RecommendToFriendsFrag newInstance(final String s, final Set<FriendForRecommendation> set, final String s2, final String s3, final ArrayList<FriendForRecommendation> list) {
        final RecommendToFriendsFrag recommendToFriendsFrag = new RecommendToFriendsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        if (set != null) {
            arguments.putParcelableArray("selected_set", (Parcelable[])set.toArray((Parcelable[])new FriendForRecommendation[set.size()]));
        }
        if (s2 != null) {
            arguments.putString("message", s2);
        }
        if (s3 != null) {
            arguments.putString("guid", s3);
        }
        if (list != null) {
            arguments.putParcelableArrayList("friends", (ArrayList)list);
        }
        recommendToFriendsFrag.setArguments(arguments);
        return recommendToFriendsFrag;
    }
    
    private void resetSearch() {
        if (this.mSearchEditText.length() > 0) {
            this.mSearchEditText.setText((CharSequence)"");
            this.mSearchEditText.clearFocus();
        }
        DeviceUtils.forceHideKeyboard(this.getActivity(), this.mSearchEditText);
        this.mFriendsList.smoothScrollToPosition(0);
    }
    
    private void updateTotalSelectedStatus() {
        boolean enabled = true;
        if (this.mCheckedFriends.size() == 0 && this.mTotalSelectedLayoutShown) {
            this.mTotalSelectedLayoutShown = false;
            this.launchAnimation(true);
        }
        else if (this.mCheckedFriends.size() > 0 && !this.mTotalSelectedLayoutShown) {
            this.mTotalSelectedLayoutShown = true;
            this.launchAnimation(false);
        }
        this.mTotalSelectedStatus.setText((CharSequence)String.valueOf(this.mCheckedFriends.size()));
        final Button mSendButton = this.mSendButton;
        if (this.mCheckedFriends.size() <= 0) {
            enabled = false;
        }
        mSendButton.setEnabled(enabled);
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        this.setStyle(1, 2131362158);
        super.onCreate(bundle);
        if (bundle != null && bundle.containsKey("friends_list")) {
            this.mLoadMoreAvailable = bundle.getBoolean("has_load_more_list");
            this.mFriends = (ArrayList<FriendForRecommendation>)bundle.getParcelableArrayList("friends_list");
            SocialUtils.castArrayToSet(bundle.getParcelableArray("selected_friends_list"), this.mCheckedFriends);
        }
        else if (this.getArguments().containsKey("selected_set") && this.getArguments().containsKey("message") && this.getArguments().containsKey("guid")) {
            SocialUtils.castArrayToSet(this.getArguments().getParcelableArray("selected_set"), this.mCheckedFriends);
            this.mInputMessage = this.getArguments().getString("message");
            this.mGUID = this.getArguments().getString("guid");
            this.mFriends = (ArrayList<FriendForRecommendation>)this.getArguments().getParcelableArrayList("friends");
        }
    }
    
    public View onCreateView(final LayoutInflater mLayoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v(RecommendToFriendsFrag.TAG, "Creating new frag view...");
        this.mLayoutInflater = mLayoutInflater;
        final View inflate = this.mLayoutInflater.inflate(2130903212, viewGroup, false);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.retryFetchFriendsCallback);
        (this.mFriendsList = (ListView)inflate.findViewById(2131624504)).setOnScrollListener((AbsListView$OnScrollListener)new RecommendToFriendsFrag$2(this));
        (this.mSearchEditText = (EditText)inflate.findViewById(2131624502)).addTextChangedListener((TextWatcher)new RecommendToFriendsFrag$3(this));
        this.mEditMessage = (EditText)inflate.findViewById(2131624505);
        if (this.mInputMessage != null) {
            this.mEditMessage.setText((CharSequence)this.mInputMessage);
        }
        (this.mSendButton = (Button)inflate.findViewById(2131624506)).setOnClickListener((View$OnClickListener)new RecommendToFriendsFrag$4(this));
        this.mTotalSelectedLayout = inflate.findViewById(2131624500);
        (this.mTotalSelectedStatus = (TextView)inflate.findViewById(2131624501)).setOnClickListener((View$OnClickListener)new RecommendToFriendsFrag$5(this));
        (this.mSearchClearButton = inflate.findViewById(2131624503)).setOnClickListener((View$OnClickListener)new RecommendToFriendsFrag$6(this));
        this.mAdapter = new RecommendToFriendsFrag$FriendsListAdapter(this, null);
        this.mFriendsList.setAdapter((ListAdapter)this.mAdapter);
        this.leWrapper.showLoadingView(false);
        this.updateTotalSelectedStatus();
        if (this.getActivity() != null && ((NetflixActivity)this.getActivity()).getServiceManager() != null) {
            this.mServiceManager = ((NetflixActivity)this.getActivity()).getServiceManager();
            this.handleManagerReady();
        }
        return inflate;
    }
    
    @Override
    public void onManagerReady(final ServiceManager mServiceManager, final Status status) {
        super.onManagerReady(mServiceManager, status);
        this.mServiceManager = mServiceManager;
        this.handleManagerReady();
    }
    
    public void onPause() {
        super.onPause();
        UIViewLogUtils.reportUIViewImpressionEnded((Context)this.getActivity(), true, null);
    }
    
    public void onResume() {
        super.onResume();
        if (this.mErrorOccurred) {
            this.fetchFriends();
        }
        UIViewLogUtils.reportUIViewImpressionStarted((Context)this.getActivity(), IClientLogging$ModalView.movieDetails, this.getGUID());
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mFriends != null) {
            bundle.putBoolean("has_load_more_list", this.mLoadMoreAvailable);
            bundle.putParcelableArrayList("friends_list", (ArrayList)this.mFriends);
            bundle.putParcelableArray("selected_friends_list", (Parcelable[])this.mCheckedFriends.toArray((Parcelable[])new FriendForRecommendation[this.mCheckedFriends.size()]));
        }
    }
}
