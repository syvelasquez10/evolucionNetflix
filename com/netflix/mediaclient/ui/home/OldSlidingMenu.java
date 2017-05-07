// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils$OnSwitchToKidsClickListener;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import java.util.List;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.annotation.SuppressLint;

@Deprecated
@SuppressLint({ "InflateParams" })
public class OldSlidingMenu implements SlidingMenuAdapter
{
    private static final boolean DISABLE_SWITCH_TO_KIDS_MENU_ITEM = true;
    private static final GenreList HOME_LOLOMO;
    private static final String TAG = "OldSlidingMenu";
    protected final NetflixActivity activity;
    private OldSlidingMenu$GenresListAdapter adapter;
    protected final View content;
    private final DrawerLayout drawerLayout;
    private final ErrorWrapper$Callback errorCallback;
    protected final TextView home;
    private final View homeRow;
    private final LoadingAndErrorWrapper leWrapper;
    private final ListView list;
    private ServiceManager manager;
    private final View$OnClickListener onHomeClickListener;
    private final AdapterView$OnItemClickListener onRowClickListener;
    private final View$OnClickListener onSwitchProfileClickListener;
    private final AdvancedImageView profileImg;
    private final TextView profileName;
    private final View profilesGroup;
    private GenreList selectedGenre;
    private final ImageView switchProfilesIcon;
    
    static {
        HOME_LOLOMO = new OldSlidingMenu$DummyGenreList();
    }
    
    public OldSlidingMenu(final NetflixActivity activity, final DrawerLayout drawerLayout) {
        this.errorCallback = new OldSlidingMenu$2(this);
        this.onHomeClickListener = (View$OnClickListener)new OldSlidingMenu$3(this);
        this.onSwitchProfileClickListener = (View$OnClickListener)new OldSlidingMenu$4(this);
        this.onRowClickListener = (AdapterView$OnItemClickListener)new OldSlidingMenu$5(this);
        this.activity = activity;
        activity.getLayoutInflater().inflate(2130903154, (ViewGroup)drawerLayout);
        (this.content = drawerLayout.findViewById(2131427682)).setOnClickListener((View$OnClickListener)null);
        ViewUtils.setPaddingTop(this.content, activity.getActionBarHeight());
        this.leWrapper = new LoadingAndErrorWrapper(this.content, this.errorCallback);
        (this.profilesGroup = this.content.findViewById(2131427683)).setOnClickListener(this.onSwitchProfileClickListener);
        this.switchProfilesIcon = (ImageView)this.profilesGroup.findViewById(2131427684);
        this.profileName = (TextView)this.content.findViewById(2131427686);
        (this.profileImg = (AdvancedImageView)this.content.findViewById(2131427685)).setPressedStateHandlerEnabled(false);
        this.homeRow = activity.getLayoutInflater().inflate(2130903155, (ViewGroup)null);
        (this.home = (TextView)this.homeRow.findViewById(2131427689)).setText(2131493170);
        this.homeRow.setBackgroundResource(2130837895);
        this.homeRow.setOnClickListener(this.onHomeClickListener);
        this.homeRow.setTag((Object)new OldSlidingMenu$Holder(this.home, this.homeRow.findViewById(2131427690)));
        this.setSelectedGenre(OldSlidingMenu.HOME_LOLOMO);
        (this.list = (ListView)this.content.findViewById(2131427688)).setFocusable(false);
        this.list.addHeaderView(this.homeRow, (Object)null, false);
        this.drawerLayout = drawerLayout;
        this.fetchGenresDataIfReady();
    }
    
    private void fetchGenresDataIfReady() {
        if (this.managerNotReady()) {
            return;
        }
        if (this.content == null) {
            Log.v("OldSlidingMenu", "Content is null - can't fetch data yet");
            return;
        }
        Log.v("OldSlidingMenu", "Fetching genres list...");
        this.manager.getBrowse().fetchGenreLists(new OldSlidingMenu$FetchGenresCallback(this));
    }
    
    private boolean managerNotReady() {
        if (this.manager == null || !this.manager.isReady()) {
            Log.v("OldSlidingMenu", "Manager is not ready - can't update");
            return true;
        }
        return false;
    }
    
    private void setupMenuStub() {
    }
    
    private void showErrorView() {
        this.leWrapper.showErrorView(true);
        AnimationUtils.hideView((View)this.list, true);
    }
    
    private void showLoadingView() {
        this.leWrapper.showLoadingView(true);
        AnimationUtils.hideView((View)this.list, true);
    }
    
    private void updateGenres(final List<GenreList> list) {
        this.leWrapper.hide(true);
        AnimationUtils.showView((View)this.list, true);
        this.adapter = new OldSlidingMenu$GenresListAdapter(list);
        this.list.setAdapter((ListAdapter)this.adapter);
        this.list.setOnItemClickListener(this.onRowClickListener);
    }
    
    private void updateMenuStubText(final TextView textView) {
        textView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131296481)));
        textView.setBackgroundResource(2130837878);
        if (KidsUtils.isKidsProfile(this.manager.getCurrentProfile())) {
            Log.v("OldSlidingMenu", "Showing 'exit kids' menu item in sliding menu");
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            textView.setCompoundDrawablePadding(0);
            textView.setText(2131492976);
            textView.setOnClickListener((View$OnClickListener)new OldSlidingMenu$1(this));
            return;
        }
        Log.v("OldSlidingMenu", "Showing 'switch to kids' menu item in sliding menu");
        textView.setCompoundDrawablesWithIntrinsicBounds(2130837739, 0, 0, 0);
        textView.setCompoundDrawablePadding(AndroidUtils.dipToPixels((Context)this.activity, 12));
        textView.setText(2131492977);
        textView.setOnClickListener((View$OnClickListener)new KidsUtils$OnSwitchToKidsClickListener(this.activity, UIViewLogging$UIViewCommandName.slidingMenuKidsEntry));
    }
    
    private void updateProfileInfo() {
        if (this.managerNotReady()) {
            return;
        }
        final UserProfile currentProfile = this.manager.getCurrentProfile();
        if (currentProfile == null) {
            Log.v("OldSlidingMenu", "Profile is null - can't update profile info");
            return;
        }
        this.profileName.setText((CharSequence)currentProfile.getFirstName());
        NetflixActivity.getImageLoader((Context)this.activity).showImg(this.profileImg, currentProfile.getAvatarUrl(), IClientLogging$AssetType.profileAvatar, currentProfile.getFirstName(), ImageLoader$StaticImgConfig.DARK_NO_PLACEHOLDER, true);
    }
    
    private void updateProfileViewGroupVisibility() {
        if (this.managerNotReady()) {
            Log.d("OldSlidingMenu", "Manager not ready - can't update profile view visibility yet");
            return;
        }
        final View profilesGroup = this.profilesGroup;
        int visibility;
        if (this.shouldShowChangeProfilesItem()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        profilesGroup.setVisibility(visibility);
    }
    
    private void updateProfileViews() {
        this.updateProfileViewGroupVisibility();
        this.updateProfileInfo();
        this.updateSwitchProfileButton();
    }
    
    private void updateSwitchProfileButton() {
        if (this.managerNotReady()) {
            return;
        }
        this.manager.refreshProfileSwitchingStatus();
        final boolean profileSwitchingDisabled = this.manager.isProfileSwitchingDisabled();
        this.profilesGroup.setEnabled(!profileSwitchingDisabled);
        final ImageView switchProfilesIcon = this.switchProfilesIcon;
        int imageResource;
        if (profileSwitchingDisabled) {
            imageResource = 17301535;
        }
        else {
            imageResource = 2130837687;
        }
        switchProfilesIcon.setImageResource(imageResource);
    }
    
    protected void applySelectionStyle(final View view) {
        final OldSlidingMenu$Holder oldSlidingMenu$Holder = (OldSlidingMenu$Holder)view.getTag();
        ViewUtils.setTextViewToBold(oldSlidingMenu$Holder.tv);
        oldSlidingMenu$Holder.tv.setTextColor(view.getResources().getColor(2131230904));
        oldSlidingMenu$Holder.selectionIndicator.setVisibility(0);
    }
    
    @Override
    public boolean canLoadNotifications() {
        return false;
    }
    
    @Override
    public void onActivityResume() {
        this.updateProfileViews();
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final Status status) {
        this.manager = manager;
        this.setupMenuStub();
        this.fetchGenresDataIfReady();
        this.updateProfileViews();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        this.manager = null;
    }
    
    @Override
    public void refresh() {
        this.showLoadingView();
        this.fetchGenresDataIfReady();
    }
    
    protected void removeSelectionStyle(final View view) {
        final OldSlidingMenu$Holder oldSlidingMenu$Holder = (OldSlidingMenu$Holder)view.getTag();
        ViewUtils.setTextViewToNormal(oldSlidingMenu$Holder.tv);
        oldSlidingMenu$Holder.tv.setTextColor(view.getResources().getColor(2131230903));
        oldSlidingMenu$Holder.selectionIndicator.setVisibility(8);
    }
    
    @Override
    public void setSelectedGenre(final GenreList selectedGenre) {
        if (Log.isLoggable()) {
            if (selectedGenre == null) {
                Log.v("OldSlidingMenu", "Selected genre is null so selecting home lolomo");
            }
            else if (selectedGenre == OldSlidingMenu.HOME_LOLOMO) {
                Log.v("OldSlidingMenu", "Setting selected genre to home lolomo");
            }
            else {
                Log.v("OldSlidingMenu", "Setting selected genre: " + selectedGenre.getTitle() + ", id: " + selectedGenre.getId());
            }
        }
        if (selectedGenre == null || selectedGenre == OldSlidingMenu.HOME_LOLOMO) {
            this.selectedGenre = OldSlidingMenu.HOME_LOLOMO;
            this.applySelectionStyle(this.homeRow);
        }
        else {
            this.selectedGenre = selectedGenre;
            this.removeSelectionStyle(this.homeRow);
        }
        if (this.adapter != null) {
            this.adapter.notifyDataSetChanged();
        }
    }
    
    protected boolean shouldShowChangeProfilesItem() {
        if (this.manager.getAllProfiles() == null) {
            Log.w("OldSlidingMenu", "No profiles found for user!");
            return false;
        }
        return true;
    }
    
    protected void updateAdapterViews(final OldSlidingMenu$Holder oldSlidingMenu$Holder, final GenreList list) {
        oldSlidingMenu$Holder.tv.setText((CharSequence)list.getTitle());
    }
}
