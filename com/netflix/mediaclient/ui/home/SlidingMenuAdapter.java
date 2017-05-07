// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils$OnSwitchToKidsClickListener;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.ViewStub;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import java.util.List;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.ViewUtils;
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
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

@SuppressLint({ "InflateParams" })
public class SlidingMenuAdapter implements ManagerStatusListener
{
    private static final GenreList HOME_LOLOMO;
    private static final String TAG = "SlidingMenuAdapter";
    protected final NetflixActivity activity;
    private SlidingMenuAdapter$GenresListAdapter adapter;
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
    protected final View profilesGroup;
    private GenreList selectedGenre;
    private final ImageView switchProfilesIcon;
    
    static {
        HOME_LOLOMO = new SlidingMenuAdapter$DummyGenreList();
    }
    
    public SlidingMenuAdapter(final NetflixActivity activity, final DrawerLayout drawerLayout) {
        this.errorCallback = new SlidingMenuAdapter$2(this);
        this.onHomeClickListener = (View$OnClickListener)new SlidingMenuAdapter$3(this);
        this.onSwitchProfileClickListener = (View$OnClickListener)new SlidingMenuAdapter$4(this);
        this.onRowClickListener = (AdapterView$OnItemClickListener)new SlidingMenuAdapter$5(this);
        this.activity = activity;
        (this.content = drawerLayout.findViewById(2131165392)).setOnClickListener((View$OnClickListener)null);
        ViewUtils.setPaddingTop(this.content, activity.getActionBarHeight());
        this.leWrapper = new LoadingAndErrorWrapper(this.content, this.errorCallback);
        (this.profilesGroup = this.content.findViewById(2131165393)).setOnClickListener(this.onSwitchProfileClickListener);
        this.switchProfilesIcon = (ImageView)this.profilesGroup.findViewById(2131165394);
        this.profileName = (TextView)this.content.findViewById(2131165396);
        (this.profileImg = (AdvancedImageView)this.content.findViewById(2131165395)).setPressedStateHandlerEnabled(false);
        this.homeRow = activity.getLayoutInflater().inflate(2130903098, (ViewGroup)null);
        (this.home = (TextView)this.homeRow.findViewById(2131165399)).setText(2131493155);
        this.homeRow.setOnClickListener(this.onHomeClickListener);
        this.homeRow.setTag((Object)new SlidingMenuAdapter$Holder(this.home, this.homeRow.findViewById(2131165400)));
        this.setSelectedGenre(SlidingMenuAdapter.HOME_LOLOMO);
        (this.list = (ListView)this.content.findViewById(2131165398)).setFocusable(false);
        this.list.addHeaderView(this.homeRow, (Object)null, false);
        this.drawerLayout = drawerLayout;
        this.fetchGenresDataIfReady();
    }
    
    private void fetchGenresDataIfReady() {
        if (this.managerNotReady()) {
            return;
        }
        if (this.content == null) {
            Log.v("SlidingMenuAdapter", "Content is null - can't fetch data yet");
            return;
        }
        Log.v("SlidingMenuAdapter", "Fetching genres list...");
        this.manager.getBrowse().fetchGenreLists(new SlidingMenuAdapter$FetchGenresCallback(this));
    }
    
    private boolean managerNotReady() {
        if (this.manager == null || !this.manager.isReady()) {
            Log.v("SlidingMenuAdapter", "Manager is not ready - can't update");
            return true;
        }
        return false;
    }
    
    private void setupMenuStub() {
        if (this.activity.isForKids() || !KidsUtils.shouldShowKidsEntryInMenu(this.activity)) {
            return;
        }
        this.updateMenuStubText((TextView)((ViewStub)this.content.findViewById(2131165397)).inflate().findViewById(2131165399));
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
        this.adapter = new SlidingMenuAdapter$GenresListAdapter(list);
        this.list.setAdapter((ListAdapter)this.adapter);
        this.list.setOnItemClickListener(this.onRowClickListener);
    }
    
    private void updateMenuStubText(final TextView textView) {
        textView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131361914)));
        textView.setBackgroundResource(2130837827);
        if (KidsUtils.isKidsProfile(this.manager.getCurrentProfile())) {
            Log.v("SlidingMenuAdapter", "Showing 'exit kids' menu item in sliding menu");
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            textView.setCompoundDrawablePadding(0);
            textView.setText(2131492952);
            textView.setOnClickListener((View$OnClickListener)new SlidingMenuAdapter$1(this));
            return;
        }
        Log.v("SlidingMenuAdapter", "Showing 'switch to kids' menu item in sliding menu");
        textView.setCompoundDrawablesWithIntrinsicBounds(2130837696, 0, 0, 0);
        textView.setCompoundDrawablePadding(AndroidUtils.dipToPixels((Context)this.activity, 12));
        textView.setText(2131492953);
        textView.setOnClickListener((View$OnClickListener)new KidsUtils$OnSwitchToKidsClickListener(this.activity, UIViewLogging$UIViewCommandName.slidingMenuKidsEntry));
    }
    
    private void updateProfileInfo() {
        if (this.managerNotReady()) {
            return;
        }
        final UserProfile currentProfile = this.manager.getCurrentProfile();
        if (currentProfile == null) {
            Log.v("SlidingMenuAdapter", "Profile is null - can't update profile info");
            return;
        }
        this.profileName.setText((CharSequence)currentProfile.getFirstName());
        NetflixActivity.getImageLoader((Context)this.activity).showImg(this.profileImg, currentProfile.getAvatarUrl(), IClientLogging$AssetType.profileAvatar, currentProfile.getFirstName(), false, true);
    }
    
    private void updateProfileViewGroupVisibility() {
        if (this.managerNotReady()) {
            Log.d("SlidingMenuAdapter", "Manager not ready - can't update profile view visibility yet");
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
            imageResource = 2130837653;
        }
        switchProfilesIcon.setImageResource(imageResource);
    }
    
    protected void applySelectionStyle(final View view) {
        final SlidingMenuAdapter$Holder slidingMenuAdapter$Holder = (SlidingMenuAdapter$Holder)view.getTag();
        ViewUtils.setTextViewToBold(slidingMenuAdapter$Holder.tv);
        slidingMenuAdapter$Holder.tv.setBackgroundResource(2130837846);
        slidingMenuAdapter$Holder.selectionIndicator.setVisibility(0);
    }
    
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
    
    public void refresh() {
        this.showLoadingView();
        this.fetchGenresDataIfReady();
    }
    
    protected void removeSelectionStyle(final View view) {
        final SlidingMenuAdapter$Holder slidingMenuAdapter$Holder = (SlidingMenuAdapter$Holder)view.getTag();
        ViewUtils.setTextViewToNormal(slidingMenuAdapter$Holder.tv);
        slidingMenuAdapter$Holder.tv.setBackgroundResource(2130837845);
        slidingMenuAdapter$Holder.selectionIndicator.setVisibility(8);
    }
    
    public void setSelectedGenre(final GenreList selectedGenre) {
        if (Log.isLoggable("SlidingMenuAdapter", 2)) {
            if (selectedGenre == null) {
                Log.v("SlidingMenuAdapter", "Selected genre is null so selecting home lolomo");
            }
            else if (selectedGenre == SlidingMenuAdapter.HOME_LOLOMO) {
                Log.v("SlidingMenuAdapter", "Setting selected genre to home lolomo");
            }
            else {
                Log.v("SlidingMenuAdapter", "Setting selected genre: " + selectedGenre.getTitle() + ", id: " + selectedGenre.getId());
            }
        }
        if (selectedGenre == null || selectedGenre == SlidingMenuAdapter.HOME_LOLOMO) {
            this.selectedGenre = SlidingMenuAdapter.HOME_LOLOMO;
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
            Log.w("SlidingMenuAdapter", "No profiles found for user!");
            return false;
        }
        return true;
    }
    
    protected void updateAdapterViews(final SlidingMenuAdapter$Holder slidingMenuAdapter$Holder, final GenreList list) {
        slidingMenuAdapter$Holder.tv.setText((CharSequence)list.getTitle());
    }
}
