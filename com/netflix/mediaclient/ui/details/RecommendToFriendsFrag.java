// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.text.Editable;
import android.text.TextWatcher;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.widget.AbsListView;
import android.widget.AbsListView$OnScrollListener;
import android.view.ViewGroup;
import android.os.Parcelable;
import android.os.Bundle;
import java.util.Collection;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import java.util.Iterator;
import java.util.List;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import com.netflix.mediaclient.StatusCode;
import android.app.Dialog;
import android.text.method.LinkMovementMethod;
import android.text.Html;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import com.netflix.mediaclient.servicemgr.SocialLogging;
import android.app.Activity;
import com.netflix.mediaclient.ui.social.FacebookLoginActivity;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.app.DialogFragment;
import java.util.Set;
import android.view.View$OnClickListener;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.EditText;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.HashSet;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class RecommendToFriendsFrag extends NetflixDialogFrag
{
    private static final String BUNDLE_EXTRA_FRIENDS_LIST = "friends_list";
    private static final String BUNDLE_EXTRA_HAS_LOAD_MORE = "has_load_more_list";
    private static final String BUNDLE_EXTRA_SELECTED_FRIENDS_LIST = "selected_friends_list";
    private static final String FRIENDS_LIST_ARGS = "friends";
    private static final String MESSAGE_TEXT_ARGS = "message";
    private static final String SELECTED_SET_ARGS = "selected_set";
    private static final String TAG;
    private static final int TOTAL_SELECTED_LAYOUT_ANIMATION = 300;
    private static final String VIDEO_ID_ARGS = "video_id";
    private LoadingAndErrorWrapper leWrapper;
    private FriendsListAdapter mAdapter;
    private HashSet<FriendForRecommendation> mCheckedFriends;
    private EditText mEditMessage;
    private boolean mErrorOccurred;
    private ArrayList<FriendForRecommendation> mFriends;
    protected ListView mFriendsList;
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
    private final ErrorWrapper.Callback retryFetchFriendsCallback;
    
    static {
        TAG = RecommendToFriendsFrag.class.getSimpleName();
    }
    
    public RecommendToFriendsFrag() {
        this.mCheckedFriends = new HashSet<FriendForRecommendation>();
        this.mLoadMoreAvailable = true;
        this.retryFetchFriendsCallback = new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                RecommendToFriendsFrag.this.fetchFriends();
            }
        };
    }
    
    public static void addRecommendButtonHandler(final NetflixActivity netflixActivity, final ServiceManager serviceManager, final TextView textView, final String s) {
        if (netflixActivity == null) {
            Log.w(RecommendToFriendsFrag.TAG, "Activity is null, bail out...");
            return;
        }
        if (textView == null) {
            Log.i(RecommendToFriendsFrag.TAG, "We don't have recommend button on tablets yet...");
            return;
        }
        if (!isSocialRecommendationsFeatureSupported(serviceManager)) {
            textView.setVisibility(8);
            return;
        }
        UIViewLogUtils.reportUIViewImpressionEvent((Context)netflixActivity, UIViewLogging.UIViewCommandName.socialRecommendButton, UIViewLogUtils.MISSING_TRACK_ID);
        textView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (serviceManager == null || !serviceManager.isReady() || serviceManager.getCurrentProfile() == null || netflixActivity == null) {
                    if (Log.isLoggable(RecommendToFriendsFrag.TAG, 6)) {
                        Log.e(RecommendToFriendsFrag.TAG, "Got problems trying to handle click on RecommendButton. Activity: " + netflixActivity + "; manager: " + serviceManager);
                    }
                    return;
                }
                if (serviceManager.getCurrentProfile().isSocialConnected()) {
                    netflixActivity.showDialog(RecommendToFriendsFrag.newInstance(s, null, null, null));
                    return;
                }
                final Dialog displayDialog = netflixActivity.displayDialog(new AlertDialog$Builder((Context)netflixActivity).setPositiveButton(2131492983, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        if (netflixActivity != null && !netflixActivity.isFinishing()) {
                            FacebookLoginActivity.show(netflixActivity);
                            SocialLoggingUtils.reportStartSocialConnectSession((Context)netflixActivity, SocialLogging.Channel.Facebook);
                        }
                    }
                }).setMessage((CharSequence)Html.fromHtml(netflixActivity.getString(2131493390))).setNegativeButton(2131493127, (DialogInterface$OnClickListener)null));
                SocialLoggingUtils.reportStartSocialConnectSession((Context)netflixActivity, SocialLogging.Channel.Facebook);
                ((TextView)displayDialog.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }
    
    private boolean checkForNetworkError(final Status status) {
        this.mErrorOccurred = false;
        if (this.leWrapper != null) {
            this.leWrapper.hide(false);
        }
        if (status.getStatusCode() == StatusCode.NETWORK_ERROR) {
            this.mErrorOccurred = true;
            if (this.leWrapper != null) {
                this.leWrapper.showErrorView(this.getString(2131493392), 2131492971, this.retryFetchFriendsCallback);
            }
        }
        else if (status.getStatusCode() == StatusCode.USER_NOT_AUTHORIZED) {
            this.mErrorOccurred = true;
            if (this.leWrapper != null) {
                this.leWrapper.showErrorView((CharSequence)Html.fromHtml(this.getString(2131493393)), 2131493132, (ErrorWrapper.Callback)new RetryConnectFacebookCallback(this.getActivity()));
            }
        }
        if (this.mErrorOccurred) {
            if (this.mAdapter != null) {
                this.mAdapter.notifyDataSetChanged();
            }
            return true;
        }
        return false;
    }
    
    private void fetchFriends() {
        if (this.mServiceManager != null) {
            if (this.leWrapper != null) {
                this.leWrapper.showLoadingView(false);
            }
            this.mLastRequestId = this.mServiceManager.fetchFriendsForRecommendationList(this.getArguments().getString("video_id"), 0, this.mSearchTerm, new SimpleManagerCallback() {
                @Override
                public void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> list, final Status status) {
                    if (status.getRequestId() != RecommendToFriendsFrag.this.mLastRequestId) {
                        if (Log.isLoggable(RecommendToFriendsFrag.TAG, 4)) {
                            Log.i(RecommendToFriendsFrag.TAG, "Skipping old result with ID: " + status.getRequestId() + "; last requestID: " + RecommendToFriendsFrag.this.mLastRequestId);
                        }
                    }
                    else if (!RecommendToFriendsFrag.this.checkForNetworkError(status)) {
                        RecommendToFriendsFrag.this.mLoadMoreAvailable = (list != null && list.size() == 20);
                        RecommendToFriendsFrag.this.mFriends = (ArrayList<FriendForRecommendation>)(ArrayList)list;
                        if (RecommendToFriendsFrag.this.mSearchTerm == null && RecommendToFriendsFrag.this.mFriends != null && RecommendToFriendsFrag.this.mCheckedFriends != null) {
                            for (final FriendForRecommendation friendForRecommendation : RecommendToFriendsFrag.this.mCheckedFriends) {
                                if (!RecommendToFriendsFrag.this.mFriends.contains(friendForRecommendation)) {
                                    RecommendToFriendsFrag.this.mFriends.add(0, friendForRecommendation);
                                }
                            }
                        }
                        if (!RecommendToFriendsFrag.this.groupFriends() && RecommendToFriendsFrag.this.mAdapter != null) {
                            RecommendToFriendsFrag.this.mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
    
    private boolean groupFriends() {
        final boolean b = false;
        boolean b2 = false;
        boolean b3 = b;
        if (this.mFriends != null) {
            b3 = b;
            if (this.mCheckedFriends.size() > 0) {
                int n = 0;
                int n2;
                boolean b4;
                for (int i = 0; i < this.mFriends.size(); ++i, n = n2, b2 = b4) {
                    final FriendForRecommendation friendForRecommendation = this.mFriends.get(i);
                    n2 = n;
                    b4 = b2;
                    if (this.mCheckedFriends.contains(friendForRecommendation)) {
                        if (n < i) {
                            this.mFriends.remove(i);
                            this.mFriends.add(n, friendForRecommendation);
                            b2 = true;
                        }
                        n2 = n + 1;
                        b4 = b2;
                    }
                }
                b3 = b2;
                if (this.mAdapter != null) {
                    this.mAdapter.notifyDataSetChanged();
                    b3 = b2;
                }
            }
        }
        return b3;
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
        return userProfile != null && !userProfile.isKidsProfile() && DeviceUtils.isNotTabletByContext(context);
    }
    
    private void launchAnimation(final boolean b) {
        final int n = (int)this.getResources().getDimension(2131361935);
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
        this.mSearchEditTextAnim.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
            public void onAnimationEnd(final Animation animation) {
                RecommendToFriendsFrag.this.mTotalSelectedLayout.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        final View access$2400 = RecommendToFriendsFrag.this.mTotalSelectedLayout;
                        int visibility;
                        if (b) {
                            visibility = 4;
                        }
                        else {
                            visibility = 0;
                        }
                        access$2400.setVisibility(visibility);
                        final EditText access$2401 = RecommendToFriendsFrag.this.mSearchEditText;
                        float translationX;
                        if (b) {
                            translationX = -n;
                        }
                        else {
                            translationX = 0.0f;
                        }
                        access$2401.setTranslationX(translationX);
                    }
                });
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        });
        this.mTotalSelectedLayoutAnim.setInterpolator((Interpolator)new AccelerateDecelerateInterpolator());
        this.mTotalSelectedLayoutAnim.setDuration(300L);
        this.mSearchEditText.startAnimation(this.mSearchEditTextAnim);
        this.mTotalSelectedLayout.startAnimation(this.mTotalSelectedLayoutAnim);
    }
    
    private void loadMoreFriends() {
        if (this.mServiceManager != null && this.mFriends != null) {
            this.mServiceManager.fetchFriendsForRecommendationList(this.getArguments().getString("video_id"), this.mFriends.size(), this.mSearchTerm, new SimpleManagerCallback() {
                @Override
                public void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> list, final Status status) {
                    if (!RecommendToFriendsFrag.this.checkForNetworkError(status)) {
                        RecommendToFriendsFrag.this.mLoadMoreAvailable = (list != null && list.size() == 20);
                        if (list != null) {
                            if (RecommendToFriendsFrag.this.mSearchTerm == null && RecommendToFriendsFrag.this.mCheckedFriends != null) {
                                for (final FriendForRecommendation friendForRecommendation : list) {
                                    if (!RecommendToFriendsFrag.this.mFriends.contains(friendForRecommendation)) {
                                        RecommendToFriendsFrag.this.mFriends.add(friendForRecommendation);
                                    }
                                }
                            }
                            else {
                                RecommendToFriendsFrag.this.mFriends.addAll(list);
                            }
                        }
                        if (RecommendToFriendsFrag.this.mAdapter != null) {
                            RecommendToFriendsFrag.this.mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
    
    public static RecommendToFriendsFrag newInstance(final String s, final Set<FriendForRecommendation> set, final String s2, final ArrayList<FriendForRecommendation> list) {
        final RecommendToFriendsFrag recommendToFriendsFrag = new RecommendToFriendsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        if (set != null) {
            arguments.putParcelableArray("selected_set", (Parcelable[])set.toArray((Parcelable[])new FriendForRecommendation[set.size()]));
        }
        if (s2 != null) {
            arguments.putString("message", s2);
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
    
    public static void restoreSetFromArray(final Parcelable[] array, final Set<FriendForRecommendation> set) {
        for (int length = array.length, i = 0; i < length; ++i) {
            set.add((FriendForRecommendation)array[i]);
        }
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
        this.setStyle(1, 2131558734);
        super.onCreate(bundle);
        if (bundle != null && bundle.containsKey("friends_list")) {
            this.mLoadMoreAvailable = bundle.getBoolean("has_load_more_list");
            this.mFriends = (ArrayList<FriendForRecommendation>)bundle.getParcelableArrayList("friends_list");
            restoreSetFromArray(bundle.getParcelableArray("selected_friends_list"), this.mCheckedFriends);
        }
        else if (this.getArguments().containsKey("selected_set") && this.getArguments().containsKey("message")) {
            restoreSetFromArray(this.getArguments().getParcelableArray("selected_set"), this.mCheckedFriends);
            this.mInputMessage = this.getArguments().getString("message");
            this.mFriends = (ArrayList<FriendForRecommendation>)this.getArguments().getParcelableArrayList("friends");
        }
    }
    
    public View onCreateView(final LayoutInflater mLayoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v(RecommendToFriendsFrag.TAG, "Creating new frag view...");
        this.mLayoutInflater = mLayoutInflater;
        final View inflate = this.mLayoutInflater.inflate(2130903167, viewGroup, false);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.retryFetchFriendsCallback);
        (this.mFriendsList = (ListView)inflate.findViewById(2131165622)).setOnScrollListener((AbsListView$OnScrollListener)new AbsListView$OnScrollListener() {
            public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
                SocialLoggingUtils.reportRecommendPanelScrolledEvent((Context)RecommendToFriendsFrag.this.getActivity(), IClientLogging.ModalView.movieDetails, UIViewLogUtils.MISSING_TRACK_ID);
            }
            
            public void onScrollStateChanged(final AbsListView absListView, final int n) {
            }
        });
        (this.mSearchEditText = (EditText)inflate.findViewById(2131165620)).addTextChangedListener((TextWatcher)new TextWatcher() {
            public void afterTextChanged(final Editable editable) {
            }
            
            public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }
            
            public void onTextChanged(final CharSequence charSequence, int visibility, final int n, final int n2) {
                if (charSequence.length() < 1) {
                    RecommendToFriendsFrag.this.mSearchTerm = null;
                }
                else {
                    RecommendToFriendsFrag.this.mSearchTerm = charSequence.toString();
                }
                final View access$300 = RecommendToFriendsFrag.this.mSearchClearButton;
                if (RecommendToFriendsFrag.this.mSearchTerm != null) {
                    visibility = 0;
                }
                else {
                    visibility = 8;
                }
                access$300.setVisibility(visibility);
                SocialLoggingUtils.reportRecommendPanelSearchedEvent((Context)RecommendToFriendsFrag.this.getActivity(), IClientLogging.ModalView.movieDetails, UIViewLogUtils.MISSING_TRACK_ID);
                RecommendToFriendsFrag.this.fetchFriends();
            }
        });
        this.mEditMessage = (EditText)inflate.findViewById(2131165623);
        if (this.mInputMessage != null) {
            this.mEditMessage.setText((CharSequence)this.mInputMessage);
        }
        (this.mSendButton = (Button)inflate.findViewById(2131165624)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                boolean b = false;
                final SocialLogging.FriendPosition[] array = new SocialLogging.FriendPosition[RecommendToFriendsFrag.this.mCheckedFriends.size()];
                int n = 0;
                for (final FriendForRecommendation friendForRecommendation : RecommendToFriendsFrag.this.mCheckedFriends) {
                    if (!friendForRecommendation.isNetlflixConnected()) {
                        b = true;
                    }
                    array[n] = new SocialLogging.FriendPosition(friendForRecommendation.getFriendProfile().getId(), n, false);
                    ++n;
                }
                final String string = RecommendToFriendsFrag.this.getArguments().getString("video_id");
                final String string2 = RecommendToFriendsFrag.this.mEditMessage.getText().toString();
                if (StringUtils.isNotEmpty(string2)) {
                    SocialLoggingUtils.reportRecommendMessageAddedEvent((Context)RecommendToFriendsFrag.this.getActivity(), IClientLogging.ModalView.movieDetails, UIViewLogUtils.MISSING_TRACK_ID);
                }
                SocialLoggingUtils.reportRecommendFriendSelectedEvent((Context)RecommendToFriendsFrag.this.getActivity(), IClientLogging.ModalView.movieDetails, array, UIViewLogUtils.MISSING_TRACK_ID);
                if (!b || PreferenceUtils.getBooleanPref((Context)RecommendToFriendsFrag.this.getActivity(), SendAsFacebookMessageDialog.getFacebookMsgOptInKey(RecommendToFriendsFrag.this.mServiceManager), false)) {
                    RecommendToFriendsFrag.this.mServiceManager.sendRecommendationsToFriends(string, RecommendToFriendsFrag.this.mCheckedFriends, string2);
                }
                else {
                    final SendAsFacebookMessageDialog instance = SendAsFacebookMessageDialog.newInstance(RecommendToFriendsFrag.this.mCheckedFriends, string, string2, RecommendToFriendsFrag.this.mFriends);
                    instance.show(RecommendToFriendsFrag.this.getFragmentManager(), "frag_dialog");
                    instance.onManagerReady(RecommendToFriendsFrag.this.mServiceManager, CommonStatus.OK);
                }
                RecommendToFriendsFrag.this.dismiss();
            }
        });
        this.mTotalSelectedLayout = inflate.findViewById(2131165618);
        (this.mTotalSelectedStatus = (TextView)inflate.findViewById(2131165619)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                RecommendToFriendsFrag.this.groupFriends();
                RecommendToFriendsFrag.this.resetSearch();
            }
        });
        (this.mSearchClearButton = inflate.findViewById(2131165621)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                RecommendToFriendsFrag.this.resetSearch();
            }
        });
        this.mAdapter = new FriendsListAdapter();
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
        UIViewLogUtils.reportUIViewImpressionStarted((Context)this.getActivity(), IClientLogging.ModalView.movieDetails);
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mFriends != null) {
            bundle.putBoolean("has_load_more_list", this.mLoadMoreAvailable);
            bundle.putParcelableArrayList("friends_list", (ArrayList)this.mFriends);
            bundle.putParcelableArray("selected_friends_list", (Parcelable[])this.mCheckedFriends.toArray((Parcelable[])new FriendForRecommendation[this.mCheckedFriends.size()]));
        }
    }
    
    private class FriendsListAdapter extends BaseAdapter
    {
        public int getCount() {
            if (RecommendToFriendsFrag.this.mErrorOccurred) {
                return 0;
            }
            if (RecommendToFriendsFrag.this.mFriends == null || RecommendToFriendsFrag.this.mFriends.size() == 0) {
                return 1;
            }
            if (RecommendToFriendsFrag.this.mLoadMoreAvailable) {
                return RecommendToFriendsFrag.this.mFriends.size() + 1;
            }
            return RecommendToFriendsFrag.this.mFriends.size();
        }
        
        public FriendForRecommendation getItem(final int n) {
            if (RecommendToFriendsFrag.this.mFriends == null || n > RecommendToFriendsFrag.this.mFriends.size() - 1) {
                return null;
            }
            return RecommendToFriendsFrag.this.mFriends.get(n);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(int imageResource, final View view, final ViewGroup viewGroup) {
            View inflate = view;
            if (view == null) {
                inflate = RecommendToFriendsFrag.this.mLayoutInflater.inflate(2130903168, viewGroup, false);
                inflate.setTag((Object)new Holder((AdvancedImageView)inflate.findViewById(2131165625), (TextView)inflate.findViewById(2131165626), (ImageView)inflate.findViewById(2131165629), inflate.findViewById(2131165628), (TextView)inflate.findViewById(2131165627)));
            }
            final Holder holder = (Holder)inflate.getTag();
            final FriendForRecommendation item = this.getItem(imageResource);
            holder.img.setVisibility(8);
            holder.checkMark.setVisibility(8);
            holder.fbIcon.setVisibility(8);
            holder.friendWatchedStatus.setVisibility(8);
            if (!RecommendToFriendsFrag.this.mLoadMoreAvailable && (RecommendToFriendsFrag.this.mFriends == null || RecommendToFriendsFrag.this.mFriends.size() == 0)) {
                holder.name.setText(2131493387);
                holder.name.setSingleLine(false);
                holder.name.setGravity(17);
            }
            else {
                if (RecommendToFriendsFrag.this.mLoadMoreAvailable && imageResource == this.getCount() - 1) {
                    holder.name.setText(2131493386);
                    holder.name.setSingleLine(false);
                    holder.name.setGravity(17);
                    RecommendToFriendsFrag.this.loadMoreFriends();
                    return inflate;
                }
                if (RecommendToFriendsFrag.this.getActivity() != null && NetflixActivity.getImageLoader((Context)RecommendToFriendsFrag.this.getActivity()) != null) {
                    holder.name.setGravity(19);
                    holder.name.setSingleLine(true);
                    holder.name.setText((CharSequence)item.getFriendProfile().getFullName());
                    holder.img.setVisibility(0);
                    NetflixActivity.getImageLoader((Context)RecommendToFriendsFrag.this.getActivity()).showImg(holder.img, item.getFriendProfile().getImageUrl(), IClientLogging.AssetType.profileAvatar, item.getFriendProfile().getFullName(), true, true);
                    holder.checkMark.setVisibility(0);
                    if (item.wasWatched()) {
                        holder.friendWatchedStatus.setVisibility(0);
                    }
                    else if (!item.isNetlflixConnected()) {
                        holder.fbIcon.setVisibility(0);
                    }
                    final ImageView access$1500 = holder.checkMark;
                    if (RecommendToFriendsFrag.this.mCheckedFriends.contains(item)) {
                        imageResource = 2130837832;
                    }
                    else {
                        imageResource = 2130837831;
                    }
                    access$1500.setImageResource(imageResource);
                    inflate.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            if (RecommendToFriendsFrag.this.mCheckedFriends.contains(item)) {
                                holder.checkMark.setImageResource(2130837831);
                                RecommendToFriendsFrag.this.mCheckedFriends.remove(item);
                            }
                            else {
                                holder.checkMark.setImageResource(2130837832);
                                RecommendToFriendsFrag.this.mCheckedFriends.add(item);
                            }
                            RecommendToFriendsFrag.this.updateTotalSelectedStatus();
                            if (RecommendToFriendsFrag.this.mSearchTerm != null) {
                                RecommendToFriendsFrag.this.resetSearch();
                            }
                        }
                    });
                    return inflate;
                }
            }
            return inflate;
        }
        
        private class Holder
        {
            private final ImageView checkMark;
            private final View fbIcon;
            private final TextView friendWatchedStatus;
            private final AdvancedImageView img;
            private final TextView name;
            
            public Holder(final AdvancedImageView img, final TextView name, final ImageView checkMark, final View fbIcon, final TextView friendWatchedStatus) {
                this.img = img;
                this.name = name;
                this.checkMark = checkMark;
                this.fbIcon = fbIcon;
                this.friendWatchedStatus = friendWatchedStatus;
            }
        }
    }
    
    private final class RetryConnectFacebookCallback implements Callback
    {
        private Activity mActivity;
        
        public RetryConnectFacebookCallback(final Activity mActivity) {
            this.mActivity = mActivity;
        }
        
        @Override
        public void onRetryRequested() {
            FacebookLoginActivity.show(this.mActivity);
        }
    }
}
