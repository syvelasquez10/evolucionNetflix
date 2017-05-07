// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Build$VERSION;
import com.netflix.mediaclient.util.ViewUtils;
import java.util.List;
import com.netflix.mediaclient.ui.social.notifications.NotificationsFrag$NotificationsListStatusListener;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View$OnClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.view.ViewStub;
import com.netflix.mediaclient.ui.social.notifications.KubrickSlidingMenuNotificationsFrag;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.annotation.SuppressLint;

@SuppressLint({ "InflateParams" })
public class StandardSlidingMenu implements SlidingMenuAdapter
{
    private static final GenreList HOME_LOLOMO;
    private static final String TAG = "StandardSlidingMenu";
    private final NetflixActivity activity;
    private final boolean canLoadNotifications;
    private final DrawerLayout drawerLayout;
    private StandardSlidingMenu$GenresListAdapter genresAdapter;
    private final ErrorWrapper$Callback genresErrorCallback;
    private final LoadingAndErrorWrapper genresLeWrapper;
    private final StaticListView genresList;
    private final View homeGenreRow;
    private ServiceManager manager;
    private KubrickSlidingMenuNotificationsFrag notificationsFrag;
    private ViewStub notificationsStub;
    private final AdapterView$OnItemClickListener onGenreRowClickListener;
    private final View$OnClickListener onHomeClickListener;
    private final View$OnClickListener onSwitchProfileClickListener;
    private final AdvancedImageView profileImg;
    private final TextView profileName;
    private final View profilesGroup;
    private GenreList selectedGenre;
    private final ImageView switchProfilesIcon;
    
    static {
        HOME_LOLOMO = new StandardSlidingMenu$DummyGenreList();
    }
    
    public StandardSlidingMenu(final NetflixActivity activity, final DrawerLayout drawerLayout, final boolean canLoadNotifications) {
        this.genresErrorCallback = new StandardSlidingMenu$3(this);
        this.onHomeClickListener = (View$OnClickListener)new StandardSlidingMenu$4(this);
        this.onSwitchProfileClickListener = (View$OnClickListener)new StandardSlidingMenu$5(this);
        this.onGenreRowClickListener = (AdapterView$OnItemClickListener)new StandardSlidingMenu$6(this);
        this.activity = activity;
        this.drawerLayout = drawerLayout;
        this.canLoadNotifications = canLoadNotifications;
        final View inflate = activity.getLayoutInflater().inflate(2130903204, (ViewGroup)drawerLayout);
        final View viewById = drawerLayout.findViewById(2131427838);
        viewById.setOnClickListener((View$OnClickListener)null);
        this.genresLeWrapper = new LoadingAndErrorWrapper(drawerLayout.findViewById(2131427841), this.genresErrorCallback);
        this.notificationsStub = (ViewStub)inflate.findViewById(2131427839);
        if (this.canLoadNotifications) {
            Log.v("StandardSlidingMenu", "Inflating notifications into layout");
            this.notificationsStub.inflate();
            (this.notificationsFrag = (KubrickSlidingMenuNotificationsFrag)activity.getFragmentManager().findFragmentById(2131427580)).setNotificationsListStatusListener(new StandardSlidingMenu$1(this));
            if (Log.isLoggable()) {
                Log.v("StandardSlidingMenu", "Notifications frag: " + this.notificationsFrag);
            }
            final ViewStub notificationsStub = this.notificationsStub;
            int visibility;
            if (this.notificationsFrag.areNotificationsPresent()) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            notificationsStub.setVisibility(visibility);
        }
        (this.profilesGroup = inflate.findViewById(2131427834)).setOnClickListener(this.onSwitchProfileClickListener);
        this.switchProfilesIcon = (ImageView)this.profilesGroup.findViewById(2131427835);
        this.profileName = (TextView)this.profilesGroup.findViewById(2131427837);
        (this.profileImg = (AdvancedImageView)this.profilesGroup.findViewById(2131427836)).setPressedStateHandlerEnabled(false);
        (this.genresList = (StaticListView)viewById.findViewById(2131427842)).setFocusable(false);
        this.homeGenreRow = this.createHomeRow();
        this.genresList.addHeaderView(this.homeGenreRow, (Object)null, false);
        this.setSelectedGenre(StandardSlidingMenu.HOME_LOLOMO);
        this.fetchGenresDataIfReady();
    }
    
    private void applyGenreSelectionStyle(final View view) {
        final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder = (StandardSlidingMenu$GenreRowHolder)view.getTag();
        ViewUtils.setTextViewToBold(standardSlidingMenu$GenreRowHolder.tv);
        standardSlidingMenu$GenreRowHolder.tv.setTextColor(view.getResources().getColor(2131230902));
        standardSlidingMenu$GenreRowHolder.selectionIndicator.setVisibility(0);
    }
    
    private void closeDrawersWithDelay() {
        final DrawerLayout drawerLayout = this.drawerLayout;
        final StandardSlidingMenu$2 standardSlidingMenu$2 = new StandardSlidingMenu$2(this);
        long n;
        if (Build$VERSION.SDK_INT >= 21) {
            n = 300L;
        }
        else {
            n = 0L;
        }
        drawerLayout.postDelayed((Runnable)standardSlidingMenu$2, n);
    }
    
    private View createHomeRow() {
        final View inflate = this.activity.getLayoutInflater().inflate(2130903205, (ViewGroup)null);
        final TextView textView = (TextView)inflate.findViewById(2131427690);
        textView.setText(2131493170);
        inflate.setBackgroundResource(2130837891);
        inflate.setOnClickListener(this.onHomeClickListener);
        inflate.setTag((Object)new StandardSlidingMenu$GenreRowHolder(textView, inflate.findViewById(2131427691)));
        return inflate;
    }
    
    private void fetchGenresDataIfReady() {
        if (this.managerNotReady()) {
            return;
        }
        Log.v("StandardSlidingMenu", "Fetching genres list...");
        this.manager.getBrowse().fetchGenreLists(new StandardSlidingMenu$FetchGenresCallback(this));
    }
    
    private boolean managerNotReady() {
        if (this.manager == null || !this.manager.isReady()) {
            Log.v("StandardSlidingMenu", "Manager is not ready - can't update");
            return true;
        }
        return false;
    }
    
    private void removeGenreSelectionStyle(final View view) {
        final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder = (StandardSlidingMenu$GenreRowHolder)view.getTag();
        ViewUtils.setTextViewToNormal(standardSlidingMenu$GenreRowHolder.tv);
        standardSlidingMenu$GenreRowHolder.tv.setTextColor(view.getResources().getColor(2131230903));
        standardSlidingMenu$GenreRowHolder.selectionIndicator.setVisibility(8);
    }
    
    private boolean shouldShowChangeProfilesItem() {
        if (this.manager.getAllProfiles() == null) {
            Log.w("StandardSlidingMenu", "No profiles found for user!");
            return false;
        }
        return true;
    }
    
    private void showGenreErrorView() {
        this.genresLeWrapper.showErrorView(true);
        AnimationUtils.hideView((View)this.genresList, true);
    }
    
    private void showGenreLoadingView() {
        this.genresLeWrapper.showLoadingView(true);
        AnimationUtils.hideView((View)this.genresList, true);
    }
    
    private void updateGenres(final List<GenreList> list) {
        this.genresLeWrapper.hide(true);
        AnimationUtils.showView((View)this.genresList, true);
        this.genresAdapter = new StandardSlidingMenu$GenresListAdapter(list);
        this.genresList.setAdapter((ListAdapter)this.genresAdapter);
        this.genresList.setOnItemClickListener(this.onGenreRowClickListener);
    }
    
    private void updateProfileInfo() {
        if (this.managerNotReady()) {
            return;
        }
        final UserProfile currentProfile = this.manager.getCurrentProfile();
        if (currentProfile == null) {
            Log.v("StandardSlidingMenu", "Profile is null - can't update profile info");
            return;
        }
        this.profileName.setText((CharSequence)currentProfile.getFirstName());
        NetflixActivity.getImageLoader((Context)this.activity).showImg(this.profileImg, currentProfile.getAvatarUrl(), IClientLogging$AssetType.profileAvatar, currentProfile.getFirstName(), ImageLoader$StaticImgConfig.DARK_NO_PLACEHOLDER, true);
    }
    
    private void updateProfileViewGroupVisibility() {
        if (this.managerNotReady()) {
            Log.d("StandardSlidingMenu", "Manager not ready - can't update profile view visibility yet");
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
    
    @Override
    public boolean canLoadNotifications() {
        return this.canLoadNotifications;
    }
    
    @Override
    public void onActivityResume() {
        this.updateProfileViews();
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final Status status) {
        this.manager = manager;
        if (this.notificationsFrag != null) {
            this.notificationsFrag.onManagerReady(manager, status);
        }
        this.fetchGenresDataIfReady();
        this.updateProfileViews();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        if (this.notificationsFrag != null) {
            this.notificationsFrag.onManagerUnavailable(serviceManager, status);
        }
        this.manager = null;
    }
    
    @Override
    public void refresh() {
        this.showGenreLoadingView();
        this.fetchGenresDataIfReady();
        if (this.notificationsFrag != null) {
            this.notificationsFrag.refresh();
        }
    }
    
    @Override
    public void setSelectedGenre(final GenreList selectedGenre) {
        if (Log.isLoggable()) {
            if (selectedGenre == null) {
                Log.v("StandardSlidingMenu", "Selected genre is null so selecting home lolomo");
            }
            else if (selectedGenre == StandardSlidingMenu.HOME_LOLOMO) {
                Log.v("StandardSlidingMenu", "Setting selected genre to home lolomo");
            }
            else {
                Log.v("StandardSlidingMenu", "Setting selected genre: " + selectedGenre.getTitle() + ", id: " + selectedGenre.getId());
            }
        }
        if (selectedGenre == null || selectedGenre == StandardSlidingMenu.HOME_LOLOMO) {
            this.selectedGenre = StandardSlidingMenu.HOME_LOLOMO;
            this.applyGenreSelectionStyle(this.homeGenreRow);
        }
        else {
            this.selectedGenre = selectedGenre;
            this.removeGenreSelectionStyle(this.homeGenreRow);
        }
        if (this.genresAdapter != null) {
            this.genresAdapter.notifyDataSetChanged();
        }
    }
}
