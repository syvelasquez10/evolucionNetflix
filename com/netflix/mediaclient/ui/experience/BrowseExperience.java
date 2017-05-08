// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import java.util.HashMap;
import java.io.Serializable;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.barker.details.BarkerHelper;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.ui.search.SearchUtils$SearchExperience;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.ui.kids.lolomo.KidsSlidingMenu;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$KidsTabletRowAdapterProvider;
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsActivity;
import com.netflix.mediaclient.ui.barker.details.BarkerMovieDetailsActivity;
import com.netflix.mediaclient.ui.kids.lolomo.KidsParityLightSlidingMenu;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.lomo.CwTestVTwoView;
import com.netflix.mediaclient.ui.lomo.CwTestView;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.ui.lomo.CwView;
import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import com.netflix.mediaclient.ui.barker_kids.lolomo.BarkerKidsCharacterView;
import com.netflix.mediaclient.ui.lomo.CwDiscoveryView;
import com.netflix.mediaclient.util.CWTestUtil;
import com.netflix.mediaclient.android.widget.VideoView;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.ui.details.ShowDetailsActivity;
import com.netflix.mediaclient.ui.details.MovieDetailsActivity;
import com.netflix.mediaclient.ui.barker_kids.details.BarkerKidsDetailsActivity;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.ui.home.StandardSlidingMenu;
import com.netflix.mediaclient.ui.home.SlidingMenuAdapter;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$StandardRowAdapterProvider;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$IRowAdapterProvider;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.RowAdapterCallbacks;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoAdapter;
import com.netflix.mediaclient.ui.lolomo.GenreLoLoMoAdapter;
import com.netflix.mediaclient.ui.barker_kids.lolomo.BarkerKidsLoLoMoAdapter;
import com.netflix.mediaclient.ui.barker_kids.lolomo.BarkerKidsGenreLoLoMoAdapter;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.Set;

public enum BrowseExperience implements IExperience
{
    DISPLAY_PAGE_REFRESH("DISPLAY_PAGE_REFRESH", 2, (BrowseExperience$ExperienceMap)new BrowseExperience$5()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW));
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            return BrowseExperience$6.STANDARD.createLolomoAdapter(loLoMoFrag, b, s);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return BrowseExperience$6.STANDARD.createRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return BrowseExperience$6.STANDARD.createSlidingMenuAdapter(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            final boolean equals = VideoType.MOVIE.equals(videoType);
            if (!this.VALID_DETAIL_TYPES.contains(videoType)) {
                return null;
            }
            if (equals) {
                return BarkerMovieDetailsActivity.class;
            }
            return BarkerShowDetailsActivity.class;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            return BrowseExperience$6.STANDARD.getLomoRowTitleVisibility(netflixActivity, basicLoMo);
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            return BrowseExperience$6.STANDARD.getPrefetchLolomoImageUrlList(context, video, loMoType);
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            return BrowseExperience$6.STANDARD.getViewImageUrl(context, video, clazz, n);
        }
    };
    
    protected static final String IMAGE_LOADER_CONFIG_ENUM = "IMAGE_LOADER_CONFIG_ENUM";
    protected static final String IMAGE_LOADER_CONFIG_NO_PLACEHOLDER_ENUM = "IMAGE_LOADER_CONFIG_NO_PLACEHOLDER_ENUM";
    
    KIDS_PARITY_LIGHT("KIDS_PARITY_LIGHT", 1, (BrowseExperience$ExperienceMap)new BrowseExperience$3()) {
        BrowseExperience$4(final String s, final int n, final BrowseExperience$ExperienceMap browseExperience$ExperienceMap) {
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            return BrowseExperience$4.STANDARD.createLolomoAdapter(loLoMoFrag, b, s);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return BrowseExperience$4.STANDARD.createRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return (SlidingMenuAdapter)new KidsParityLightSlidingMenu(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            return BrowseExperience$4.STANDARD.getDetailsClassTypeForVideo(videoType);
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            return BrowseExperience$4.STANDARD.getLomoRowTitleVisibility(netflixActivity, basicLoMo);
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            return BrowseExperience$4.STANDARD.getPrefetchLolomoImageUrlList(context, video, loMoType);
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            return BrowseExperience$4.STANDARD.getViewImageUrl(context, video, clazz, n);
        }
    }, 
    KIDS_TABLET_STANDARD("KIDS_TABLET_STANDARD", 3, (BrowseExperience$ExperienceMap)new BrowseExperience$7()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW, VideoType.CHARACTERS));
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return (LoLoMoFrag$ILoLoMoAdapter)new BarkerKidsGenreLoLoMoAdapter(loLoMoFrag, s, false);
            }
            return (LoLoMoFrag$ILoLoMoAdapter)new BarkerKidsLoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return (RowAdapterProvider$IRowAdapterProvider)new RowAdapterProvider$KidsTabletRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return (SlidingMenuAdapter)new KidsSlidingMenu(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            if (this.VALID_DETAIL_TYPES.contains(videoType)) {
                return BarkerKidsDetailsActivity.class;
            }
            return null;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            switch (BrowseExperience$9.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[basicLoMo.getType().ordinal()]) {
                default: {
                    return 0;
                }
                case 3: {
                    return 8;
                }
            }
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            return BrowseExperience$8.STANDARD.getPrefetchLolomoImageUrlList(context, video, loMoType);
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            return BrowseExperience$8.STANDARD.getViewImageUrl(context, video, clazz, n);
        }
    };
    
    protected static final String LOMO_FRAG_OFFSET_LEFT_DIMEN_INT = "LOMO_FRAG_OFFSET_LEFT_DIMEN_INT";
    protected static final String SEARCH_EXPERIENCE_ENUM = "SEARCH_EXPERIENCE_ENUM";
    protected static final String SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL = "SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL";
    protected static final String SHOULD_LOAD_KUBRICK_LEAVES_IN_DETAILS_BOOL = "SHOULD_LOAD_KUBRICK_LEAVES_IN_DETAILS_BOOL";
    protected static final String SHOULD_LOAD_KUBRICK_LEAVES_IN_LOLOMO_BOOL = "SHOULD_LOAD_KUBRICK_LEAVES_IN_LOLOMO_BOOL";
    
    STANDARD("STANDARD", 0, (BrowseExperience$ExperienceMap)new BrowseExperience$1()) {
        public final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW, VideoType.CHARACTERS));
        }
        
        private String getHorzDispUrl(final Video video) {
            if (video instanceof FalkorVideo) {
                return ((FalkorVideo)video).getHorzDispUrl();
            }
            return null;
        }
        
        private boolean isFlagEnabled(final int n, final int n2) {
            return (n & n2) == n2;
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (KidsUtils.isKidsParity((Context)loLoMoFrag.getActivity())) {
                if (b) {
                    return (LoLoMoFrag$ILoLoMoAdapter)new BarkerKidsGenreLoLoMoAdapter(loLoMoFrag, s, false);
                }
                return (LoLoMoFrag$ILoLoMoAdapter)new BarkerKidsLoLoMoAdapter(loLoMoFrag);
            }
            else {
                if (b) {
                    return (LoLoMoFrag$ILoLoMoAdapter)new GenreLoLoMoAdapter(loLoMoFrag, s);
                }
                return (LoLoMoFrag$ILoLoMoAdapter)new LoLoMoAdapter(loLoMoFrag);
            }
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return (RowAdapterProvider$IRowAdapterProvider)new RowAdapterProvider$StandardRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return new StandardSlidingMenu(netflixActivity, drawerLayout, true);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            final boolean equals = VideoType.MOVIE.equals(videoType);
            if (!this.VALID_DETAIL_TYPES.contains(videoType)) {
                return null;
            }
            if (videoType == VideoType.CHARACTERS) {
                return BarkerKidsDetailsActivity.class;
            }
            if (equals) {
                return MovieDetailsActivity.class;
            }
            return ShowDetailsActivity.class;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            if (basicLoMo.getType() != LoMoType.BILLBOARD && basicLoMo.getType() != LoMoType.CHARACTERS) {
                return 0;
            }
            return 8;
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            List<String> list = null;
            if (loMoType == null || video == null || context == null) {
                list = null;
            }
            else {
                final ArrayList<String> list2 = new ArrayList<String>();
                switch (BrowseExperience$9.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[loMoType.ordinal()]) {
                    default: {
                        list2.add(this.getViewImageUrl(context, video, VideoView.class, 0));
                        return list2;
                    }
                    case 1: {
                        list2.add(this.getViewImageUrl(context, video, CWTestUtil.getCWViewClass(context), 0));
                        return list2;
                    }
                    case 2: {
                        list2.add(this.getViewImageUrl(context, video, CwDiscoveryView.class, 0));
                        return list2;
                    }
                    case 3: {
                        list2.add(this.getViewImageUrl(context, video, BarkerKidsCharacterView.class, 0));
                        return list2;
                    }
                    case 4: {
                        list = list2;
                        if (!(video instanceof Billboard)) {
                            break;
                        }
                        final BillboardSummary billboardSummary = ((Billboard)video).getBillboardSummary();
                        if (billboardSummary != null && billboardSummary.getLogo() != null && billboardSummary.getBackground() != null && billboardSummary.getBackgroundPortrait() != null) {
                            final String url = billboardSummary.getBackgroundPortrait().getUrl();
                            final String url2 = billboardSummary.getBackground().getUrl();
                            final String url3 = billboardSummary.getLogo().getUrl();
                            list2.add(url);
                            list2.add(url2);
                            list2.add(url3);
                            return list2;
                        }
                        list = list2;
                        if (Log.isLoggable()) {
                            Log.d("BrowseExperience", "getPrefetchLolomoImageUrlList: Billboard summary is null");
                            return list2;
                        }
                        break;
                    }
                }
            }
            return list;
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            String s = null;
            if (clazz == CwView.class) {
                if (video instanceof CWVideo) {
                    s = ((CWVideo)video).createModifiedStillUrl();
                }
            }
            else {
                if (clazz == CwTestView.class || clazz == BarkerKidsCharacterView.class) {
                    return video.getBoxshotUrl();
                }
                if (clazz == CwTestVTwoView.class) {
                    if (video instanceof CWVideo && StringUtils.isEmpty(s = ((CWVideo)video).getCleanBoxshotUrl())) {
                        return video.getBoxshotUrl();
                    }
                }
                else {
                    if (clazz == CwDiscoveryView.class) {
                        return this.getHorzDispUrl(video);
                    }
                    if (clazz == VideoView.class) {
                        if (this.isFlagEnabled(n, 1)) {
                            return this.getHorzDispUrl(video);
                        }
                        return video.getBoxshotUrl();
                    }
                }
            }
            return s;
        }
    };
    
    private static final String TAG = "BrowseExperience";
    protected static final String USE_KIDS_GENRES_LOMO = "USE_KIDS_GENRES_LOMO";
    protected static final String USE_LOLOMO_BOXART = "USE_LOLOMO_BOXART";
    private static final boolean USE_PRODUCTION_KUBRICK = false;
    private static BrowseExperience currExperience;
    private final BrowseExperience$ExperienceMap data;
    
    static {
        BrowseExperience.currExperience = BrowseExperience.STANDARD;
    }
    
    private BrowseExperience(final BrowseExperience$ExperienceMap data) {
        this.data = data;
    }
    
    public static BrowseExperience get() {
        return BrowseExperience.currExperience;
    }
    
    public static ImageLoader$StaticImgConfig getImageLoaderConfig() {
        return ((HashMap<K, ImageLoader$StaticImgConfig>)BrowseExperience.currExperience.data).get("IMAGE_LOADER_CONFIG_ENUM");
    }
    
    public static ImageLoader$StaticImgConfig getImageLoaderConfigNoPlaceholder() {
        return ((HashMap<K, ImageLoader$StaticImgConfig>)BrowseExperience.currExperience.data).get("IMAGE_LOADER_CONFIG_NO_PLACEHOLDER_ENUM");
    }
    
    public static int getLomoFragOffsetStartDimenId() {
        return ((HashMap<K, Integer>)BrowseExperience.currExperience.data).get("LOMO_FRAG_OFFSET_LEFT_DIMEN_INT");
    }
    
    public static String getLomoVideoViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
        final BrowseExperience currExperience = BrowseExperience.currExperience;
        return get().getViewImageUrl(context, video, clazz, n);
    }
    
    public static SearchUtils$SearchExperience getSearchExperience() {
        return ((HashMap<K, SearchUtils$SearchExperience>)BrowseExperience.currExperience.data).get("SEARCH_EXPERIENCE_ENUM");
    }
    
    public static boolean isDisplayPageRefresh() {
        return BrowseExperience.currExperience == BrowseExperience.DISPLAY_PAGE_REFRESH;
    }
    
    public static boolean isLightTheme() {
        return BrowseExperience.currExperience == BrowseExperience.KIDS_PARITY_LIGHT;
    }
    
    public static void refresh(final Context context, final UserProfile userProfile) {
        final PersistentExperience value = PersistentExperience.get(context);
        boolean b;
        if (userProfile != null && userProfile.isKidsProfile()) {
            b = true;
        }
        else {
            b = false;
        }
        if (b && DeviceUtils.isTabletByContext(context) && !KidsUtils.isKidsParity(context)) {
            BrowseExperience.currExperience = BrowseExperience.KIDS_TABLET_STANDARD;
        }
        else {
            final int n = BrowseExperience$9.$SwitchMap$com$netflix$mediaclient$ui$experience$PersistentExperience[value.ordinal()];
            if (BarkerHelper.isInTest(context)) {
                BrowseExperience.currExperience = BrowseExperience.DISPLAY_PAGE_REFRESH;
            }
            else if (KidsUtils.isKidsParity(context) && b) {
                BrowseExperience.currExperience = BrowseExperience.KIDS_PARITY_LIGHT;
            }
            else {
                BrowseExperience.currExperience = BrowseExperience.STANDARD;
            }
        }
        if (Log.isLoggable()) {
            final BrowseExperience currExperience = BrowseExperience.currExperience;
            String firstName;
            if (userProfile == null) {
                firstName = "null profile";
            }
            else {
                firstName = userProfile.getFirstName();
            }
            Serializable value2;
            if (userProfile == null) {
                value2 = "null profile";
            }
            else {
                value2 = userProfile.isKidsProfile();
            }
            Log.v("BrowseExperience", String.format("Experience updated to: %s, profile name: %s, is kids profile: %s, USE_PRODUCTION_KUBRICK: %s", currExperience, firstName, value2, false));
        }
        if (Log.isLoggable()) {
            Log.i("BrowseExperience", "Setting Crittercism username: " + String.valueOf(BrowseExperience.currExperience));
        }
        ErrorLoggingManager.setUsername(String.valueOf(BrowseExperience.currExperience));
    }
    
    public static boolean shouldLoadExtraCharacterLeaves() {
        return ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL");
    }
    
    public static boolean shouldLoadKubrickLeavesInDetails() {
        return ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("SHOULD_LOAD_KUBRICK_LEAVES_IN_DETAILS_BOOL");
    }
    
    public static boolean shouldLoadKubrickLeavesInLolomo() {
        return ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("SHOULD_LOAD_KUBRICK_LEAVES_IN_LOLOMO_BOOL");
    }
    
    public static boolean showKidsExperience() {
        return BrowseExperience.currExperience == BrowseExperience.KIDS_TABLET_STANDARD;
    }
    
    public static boolean useKidsGenresLoMo() {
        return ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("USE_KIDS_GENRES_LOMO");
    }
    
    public static boolean useLolomoBoxArt() {
        return ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("USE_LOLOMO_BOXART");
    }
}
