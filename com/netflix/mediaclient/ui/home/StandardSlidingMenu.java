// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.widget.ListAdapter;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.net.Uri;
import android.content.Intent;
import java.util.ArrayList;
import android.os.Build$VERSION;
import com.netflix.mediaclient.util.ViewUtils;
import java.util.List;
import com.netflix.mediaclient.ui.iris.notifications.NotificationsFrag$NotificationsListStatusListener;
import android.view.ViewStub;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View$OnClickListener;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.annotation.SuppressLint;

@SuppressLint({ "InflateParams" })
public class StandardSlidingMenu implements SlidingMenuAdapter
{
    private static final GenreList HOME_LOLOMO;
    private static final String TAG = "StandardSlidingMenu";
    protected final NetflixActivity activity;
    private StandardSlidingMenu$AppListAdapter appAdapter;
    private final StaticListView appList;
    private View appTitle;
    private final boolean canLoadNotifications;
    private final DrawerLayout drawerLayout;
    private StandardSlidingMenu$GenresListAdapter genresAdapter;
    private final ErrorWrapper$Callback genresErrorCallback;
    private final LoadingAndErrorWrapper genresLeWrapper;
    protected final StaticListView genresList;
    private final View homeGenreRow;
    protected TextView homeText;
    private ServiceManager manager;
    private SlidingMenuNotificationsFrag notificationsFrag;
    private final AdapterView$OnItemClickListener onAppRowClickListener;
    private final AdapterView$OnItemClickListener onGenreRowClickListener;
    private final View$OnClickListener onHomeClickListener;
    private final View$OnClickListener onSwitchProfileClickListener;
    private final AdvancedImageView profileImg;
    protected final TextView profileName;
    private final View profilesGroup;
    private GenreList selectedGenre;
    protected final ImageView switchProfilesIcon;
    
    static {
        HOME_LOLOMO = new StandardSlidingMenu$DummyGenreList();
    }
    
    public StandardSlidingMenu(final NetflixActivity activity, final DrawerLayout drawerLayout, final boolean canLoadNotifications) {
        this.genresErrorCallback = new StandardSlidingMenu$3(this);
        this.onHomeClickListener = (View$OnClickListener)new StandardSlidingMenu$4(this);
        this.onSwitchProfileClickListener = (View$OnClickListener)new StandardSlidingMenu$5(this);
        this.onGenreRowClickListener = (AdapterView$OnItemClickListener)new StandardSlidingMenu$6(this);
        this.onAppRowClickListener = (AdapterView$OnItemClickListener)new StandardSlidingMenu$12(this);
        this.activity = activity;
        this.drawerLayout = drawerLayout;
        this.canLoadNotifications = canLoadNotifications;
        final View inflate = activity.getLayoutInflater().inflate(2130903251, (ViewGroup)drawerLayout);
        final View viewById = drawerLayout.findViewById(2131624662);
        viewById.setOnClickListener((View$OnClickListener)null);
        this.genresLeWrapper = new LoadingAndErrorWrapper(drawerLayout.findViewById(2131624665), this.genresErrorCallback);
        if (this.canLoadNotifications) {
            Log.v("StandardSlidingMenu", "Inflating notifications into layout");
            final ViewStub viewStub = (ViewStub)inflate.findViewById(2131624663);
            viewStub.inflate();
            (this.notificationsFrag = (SlidingMenuNotificationsFrag)activity.getFragmentManager().findFragmentById(2131624313)).setNotificationsListStatusListener(new StandardSlidingMenu$1(this, viewStub));
            if (Log.isLoggable()) {
                Log.v("StandardSlidingMenu", "Notifications frag: " + this.notificationsFrag);
            }
            int visibility;
            if (this.notificationsFrag.areNotificationsPresent()) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            viewStub.setVisibility(visibility);
        }
        (this.profilesGroup = inflate.findViewById(2131624657)).setOnClickListener(this.onSwitchProfileClickListener);
        this.switchProfilesIcon = (ImageView)this.profilesGroup.findViewById(2131624658);
        this.profileName = (TextView)this.profilesGroup.findViewById(2131624660);
        (this.profileImg = (AdvancedImageView)this.profilesGroup.findViewById(2131624659)).setPressedStateHandlerEnabled(false);
        (this.genresList = (StaticListView)viewById.findViewById(2131624666)).setFocusable(false);
        this.homeGenreRow = this.createHomeRow();
        this.genresList.addHeaderView(this.homeGenreRow, (Object)null, false);
        this.setSelectedGenre(StandardSlidingMenu.HOME_LOLOMO);
        this.appTitle = viewById.findViewById(2131624667);
        (this.appList = (StaticListView)viewById.findViewById(2131624668)).setFocusable(false);
        this.setAppActions();
        this.fetchGenresDataIfReady();
    }
    
    private void applyGenreSelectionStyle(final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder) {
        ViewUtils.setTextViewToBold(standardSlidingMenu$GenreRowHolder.tv);
        standardSlidingMenu$GenreRowHolder.tv.setTextColor(this.activity.getResources().getColor(2131558575));
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
    
    private static List<StandardSlidingMenu$AppAction> createActions(final NetflixActivity netflixActivity) {
        final boolean b = false;
        final ArrayList<StandardSlidingMenu$AppAction> list = new ArrayList<StandardSlidingMenu$AppAction>();
        if (netflixActivity.getServiceManager() == null) {
            Log.w("StandardSlidingMenu", "Service manager is null, no app section");
            return list;
        }
        if (!netflixActivity.getServiceManager().isReady()) {
            Log.w("StandardSlidingMenu", "Service manager not ready, no app section");
            return list;
        }
        if (netflixActivity.getServiceManager().getCurrentProfile() == null) {
            Log.w("StandardSlidingMenu", "Current profile is null, no app section");
            return list;
        }
        if (netflixActivity.showSettingsInMenu()) {
            list.add(new StandardSlidingMenu$AppAction(netflixActivity.getString(2131165647), new StandardSlidingMenu$7(netflixActivity)));
        }
        if (netflixActivity.showAccountInMenu()) {
            list.add(new StandardSlidingMenu$AppAction(netflixActivity.getString(2131165646), new StandardSlidingMenu$8(netflixActivity)));
        }
        if (netflixActivity.showAboutInMenu()) {
            list.add(new StandardSlidingMenu$AppAction(netflixActivity.getString(2131165412), new StandardSlidingMenu$9(netflixActivity)));
        }
        if (netflixActivity.showContactUsInSlidingMenu() && netflixActivity.getServiceManager().getVoip() != null && netflixActivity.getServiceManager().getVoip().isEnabled()) {
            list.add(new StandardSlidingMenu$AppAction(netflixActivity.getString(2131165455), new StandardSlidingMenu$10(netflixActivity)));
        }
        else if (Log.isLoggable()) {
            Log.w("StandardSlidingMenu", "Show Contact Us In SlidingMenu: " + netflixActivity.showContactUsInSlidingMenu());
            Log.w("StandardSlidingMenu", "VOIP is null: " + (netflixActivity.getServiceManager().getVoip() == null));
            final StringBuilder append = new StringBuilder().append("VOIP is enabled: ");
            boolean enabled = b;
            if (netflixActivity.getServiceManager().getVoip() != null) {
                enabled = netflixActivity.getServiceManager().getVoip().isEnabled();
            }
            Log.w("StandardSlidingMenu", append.append(enabled).toString());
        }
        if (netflixActivity.showSignOutInMenu()) {
            list.add(new StandardSlidingMenu$AppAction(netflixActivity.getString(2131165640), new StandardSlidingMenu$11(netflixActivity)));
        }
        if (Log.isLoggable()) {
            Log.d("StandardSlidingMenu", "App section should exist " + list.size());
        }
        return list;
    }
    
    private View createHomeRow() {
        final View inflate = this.activity.getLayoutInflater().inflate(2130903252, (ViewGroup)null);
        (this.homeText = (TextView)inflate.findViewById(2131624438)).setText(2131165551);
        inflate.setBackgroundResource(2130837918);
        inflate.setOnClickListener(this.onHomeClickListener);
        inflate.setTag((Object)new StandardSlidingMenu$GenreRowHolder(this.homeText, inflate.findViewById(2131624439)));
        return inflate;
    }
    
    private static Intent createViewAccountIntent(final String s) {
        return new Intent("android.intent.action.VIEW").setData(Uri.parse("https://www.netflix.com/TermsOfUse"));
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
    
    private void removeGenreSelectionStyle(final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder) {
        ViewUtils.setTextViewToNormal(standardSlidingMenu$GenreRowHolder.tv);
        standardSlidingMenu$GenreRowHolder.tv.setTextColor(this.activity.getResources().getColor(2131558577));
        standardSlidingMenu$GenreRowHolder.selectionIndicator.setVisibility(8);
    }
    
    private void setAppActions() {
        final List<StandardSlidingMenu$AppAction> actions = createActions(this.activity);
        if (actions != null && actions.size() > 0) {
            this.appAdapter = new StandardSlidingMenu$AppListAdapter(actions);
            this.appList.setAdapter((ListAdapter)this.appAdapter);
            this.appList.setOnItemClickListener(this.onAppRowClickListener);
            this.appTitle.setVisibility(0);
            this.appList.setVisibility(0);
            return;
        }
        this.appTitle.setVisibility(8);
        this.appList.setVisibility(8);
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
        this.profileName.setText(LocalizationUtils.forceLayoutDirectionIfNeeded(currentProfile.getFirstName()));
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
    
    @Override
    public boolean canLoadNotifications() {
        return this.canLoadNotifications;
    }
    
    public void markNotificationsAsRead() {
        this.notificationsFrag.markNotificationsAsRead();
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
        this.setAppActions();
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
    
    public void reportNotificationsImpression(final boolean b) {
        if (this.notificationsFrag != null) {
            this.notificationsFrag.reportNotificationsImpression(b);
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
            this.updateAdapterViews((StandardSlidingMenu$GenreRowHolder)this.homeGenreRow.getTag(), true);
        }
        else {
            this.selectedGenre = selectedGenre;
            this.updateAdapterViews((StandardSlidingMenu$GenreRowHolder)this.homeGenreRow.getTag(), false);
        }
        if (this.genresAdapter != null) {
            this.genresAdapter.notifyDataSetChanged();
        }
    }
    
    protected boolean shouldShowChangeProfilesItem() {
        if (this.manager.getAllProfiles() == null) {
            Log.w("StandardSlidingMenu", "No profiles found for user!");
            return false;
        }
        return true;
    }
    
    protected void updateAdapterViews(final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder, final boolean b) {
        if (b) {
            this.applyGenreSelectionStyle(standardSlidingMenu$GenreRowHolder);
            return;
        }
        this.removeGenreSelectionStyle(standardSlidingMenu$GenreRowHolder);
    }
    
    protected void updateSwitchProfileButton() {
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
            imageResource = 2130837686;
        }
        switchProfilesIcon.setImageResource(imageResource);
    }
}
