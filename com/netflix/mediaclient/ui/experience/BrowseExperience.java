// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import java.util.HashMap;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import java.io.Serializable;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.kubrick.details.BarkerHelper;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.ui.search.SearchUtils$SearchExperience;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.ui.lomo.BillboardView;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$KubrickHighDensityRowAdapterProvider;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickHighDensityLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickHighDensityGenreLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick_kids.details.BarkerKidsDetailsActivity;
import com.netflix.mediaclient.ui.kids.lolomo.KidsSlidingMenu;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$KidsTabletRowAdapterProvider;
import com.netflix.mediaclient.ui.kubrick_kids.lolomo.KubrickKidsLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick_kids.lolomo.KubrickKidsGenreLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickLolomoUtils;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$KubrickRowAdapterProvider;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickGenreLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick.details.BarkerShowDetailsActivity;
import com.netflix.mediaclient.ui.kubrick.details.BarkerMovieDetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickHeroView;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickVideoView;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickCwGalleryView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.lomo.CwTestVTwoView;
import com.netflix.mediaclient.ui.lomo.CwTestView;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickHighDensityCwView;
import com.netflix.mediaclient.ui.lomo.CwView;
import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import com.netflix.mediaclient.ui.kubrick_kids.lolomo.KubrickKidsCharacterView;
import com.netflix.mediaclient.ui.lomo.CwDiscoveryView;
import com.netflix.mediaclient.util.CWTestUtil;
import com.netflix.mediaclient.android.widget.VideoView;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.ui.details.ShowDetailsActivity;
import com.netflix.mediaclient.ui.details.MovieDetailsActivity;
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
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.Set;

public enum BrowseExperience implements IExperience
{
    DISPLAY_PAGE_REFRESH("DISPLAY_PAGE_REFRESH", 1, (BrowseExperience$ExperienceMap)new BrowseExperience$3()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW));
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
            return BrowseExperience$4.STANDARD.createSlidingMenuAdapter(netflixActivity, drawerLayout);
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
    };
    
    protected static final String IMAGE_LOADER_CONFIG_ENUM = "IMAGE_LOADER_CONFIG_ENUM";
    protected static final String IMAGE_LOADER_CONFIG_NO_PLACEHOLDER_ENUM = "IMAGE_LOADER_CONFIG_NO_PLACEHOLDER_ENUM";
    
    KIDS_TABLET_STANDARD("KIDS_TABLET_STANDARD", 3, (BrowseExperience$ExperienceMap)new BrowseExperience$7()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW, VideoType.CHARACTERS));
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new KubrickKidsGenreLoLoMoAdapter(loLoMoFrag, s, false);
            }
            return new KubrickKidsLoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$KidsTabletRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return new KidsSlidingMenu(netflixActivity, drawerLayout);
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
            switch (BrowseExperience$21.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[basicLoMo.getType().ordinal()]) {
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
    }, 
    KUBRICK_AB_TEST_HERO_IMAGES("KUBRICK_AB_TEST_HERO_IMAGES", 4, (BrowseExperience$ExperienceMap)new BrowseExperience$9()) {
        BrowseExperience$10(final String s, final int n, final BrowseExperience$ExperienceMap browseExperience$ExperienceMap) {
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            return BrowseExperience$10.KUBRICK_PRODUCTIZED.createLolomoAdapter(loLoMoFrag, b, s);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return BrowseExperience$10.KUBRICK_PRODUCTIZED.createRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return BrowseExperience$10.STANDARD.createSlidingMenuAdapter(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            return BrowseExperience$10.KUBRICK_PRODUCTIZED.getDetailsClassTypeForVideo(videoType);
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            return BrowseExperience$10.KUBRICK_PRODUCTIZED.getLomoRowTitleVisibility(netflixActivity, basicLoMo);
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            return BrowseExperience$10.STANDARD.getPrefetchLolomoImageUrlList(context, video, loMoType);
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            return BrowseExperience$10.STANDARD.getViewImageUrl(context, video, clazz, n);
        }
    }, 
    KUBRICK_AB_TEST_HIGH_DENSITY("KUBRICK_AB_TEST_HIGH_DENSITY", 5, (BrowseExperience$ExperienceMap)new BrowseExperience$11()) {
        BrowseExperience$12(final String s, final int n, final BrowseExperience$ExperienceMap browseExperience$ExperienceMap) {
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new KubrickHighDensityGenreLoLoMoAdapter(loLoMoFrag, s);
            }
            return new KubrickHighDensityLoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$KubrickHighDensityRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return BrowseExperience$12.STANDARD.createSlidingMenuAdapter(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            return BrowseExperience$12.KUBRICK_PRODUCTIZED.getDetailsClassTypeForVideo(videoType);
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            return BrowseExperience$12.STANDARD.getLomoRowTitleVisibility(netflixActivity, basicLoMo);
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            return BrowseExperience$12.STANDARD.getPrefetchLolomoImageUrlList(context, video, loMoType);
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            return BrowseExperience$12.STANDARD.getViewImageUrl(context, video, clazz, n);
        }
    }, 
    KUBRICK_PRODUCTIZED("KUBRICK_PRODUCTIZED", 2, (BrowseExperience$ExperienceMap)new BrowseExperience$5()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW));
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new KubrickGenreLoLoMoAdapter(loLoMoFrag, s, true);
            }
            return new KubrickLoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$KubrickRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
            if (equals) {
                return BarkerMovieDetailsActivity.class;
            }
            return BarkerShowDetailsActivity.class;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            if (KubrickLolomoUtils.isDuplicateRow(basicLoMo)) {
                return 8;
            }
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
    
    protected static final String LOMO_FRAG_OFFSET_LEFT_DIMEN_INT = "LOMO_FRAG_OFFSET_LEFT_DIMEN_INT";
    protected static final String SEARCH_EXPERIENCE_ENUM = "SEARCH_EXPERIENCE_ENUM";
    protected static final String SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL = "SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL";
    protected static final String SHOULD_LOAD_KUBRICK_LEAVES_IN_DETAILS_BOOL = "SHOULD_LOAD_KUBRICK_LEAVES_IN_DETAILS_BOOL";
    protected static final String SHOULD_LOAD_KUBRICK_LEAVES_IN_LOLOMO_BOOL = "SHOULD_LOAD_KUBRICK_LEAVES_IN_LOLOMO_BOOL";
    protected static final String SHOULD_SHOW_LARGE_PEAK_FOR_KIDS_TOP_TEN = "SHOULD_SHOW_LARGE_PEAK_FOR_KIDS_TOP_TEN";
    
    STANDARD("STANDARD", 0, (BrowseExperience$ExperienceMap)new BrowseExperience$1()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW));
        }
        
        private boolean isFlagEnabled(final int n, final int n2) {
            return (n & n2) == n2;
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new GenreLoLoMoAdapter(loLoMoFrag, s);
            }
            return new LoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
            if (equals) {
                return MovieDetailsActivity.class;
            }
            return ShowDetailsActivity.class;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            if (basicLoMo.getType() != LoMoType.BILLBOARD) {
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
                switch (BrowseExperience$21.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[loMoType.ordinal()]) {
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
                        list2.add(this.getViewImageUrl(context, video, KubrickKidsCharacterView.class, 0));
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
            if (clazz == CwView.class || clazz == KubrickHighDensityCwView.class) {
                if (video instanceof CWVideo) {
                    s = ((CWVideo)video).createModifiedStillUrl();
                }
            }
            else {
                if (clazz == CwTestView.class || clazz == KubrickKidsCharacterView.class) {
                    return video.getBoxshotUrl();
                }
                if (clazz == CwTestVTwoView.class) {
                    if (video instanceof CWVideo && StringUtils.isEmpty(s = ((CWVideo)video).getCleanBoxshotUrl())) {
                        return video.getBoxshotUrl();
                    }
                }
                else {
                    if (clazz == CwDiscoveryView.class) {
                        return video.getHorzDispUrl();
                    }
                    if (clazz == KubrickCwGalleryView.class) {
                        if (video instanceof CWVideo) {
                            final CWVideo cwVideo = (CWVideo)video;
                            if (this.isFlagEnabled(n, 2)) {
                                return cwVideo.createModifiedBigStillUrl();
                            }
                            return cwVideo.createModifiedStillUrl();
                        }
                    }
                    else if (clazz == KubrickVideoView.class) {
                        if (this.isFlagEnabled(n, 2)) {
                            return video.getHorzDispUrl();
                        }
                        return video.getHorzDispSmallUrl();
                    }
                    else if (clazz == KubrickHeroView.class) {
                        if (video instanceof KubrickVideo) {
                            return ((KubrickVideo)video).getKubrickStoryImgUrl();
                        }
                    }
                    else if (clazz == VideoView.class) {
                        if (this.isFlagEnabled(n, 1)) {
                            return video.getHorzDispUrl();
                        }
                        return video.getBoxshotUrl();
                    }
                }
            }
            return s;
        }
    };
    
    private static final String TAG = "BrowseExperience";
    
    TEST_LOLOMO_TITLE_ART_6725_CELL_2("TEST_LOLOMO_TITLE_ART_6725_CELL_2", 6, (BrowseExperience$ExperienceMap)new BrowseExperience$13()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW));
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new GenreLoLoMoAdapter(loLoMoFrag, s);
            }
            return new LoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
            if (equals) {
                return MovieDetailsActivity.class;
            }
            return ShowDetailsActivity.class;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            if (basicLoMo.getType() != LoMoType.BILLBOARD || BillboardView.shouldShowArtworkOnly(netflixActivity)) {
                return 0;
            }
            return 8;
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            return BrowseExperience$14.STANDARD.getPrefetchLolomoImageUrlList(context, video, loMoType);
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            return BrowseExperience$14.STANDARD.getViewImageUrl(context, video, clazz, n);
        }
    }, 
    TEST_LOLOMO_TITLE_ART_6725_CELL_3("TEST_LOLOMO_TITLE_ART_6725_CELL_3", 7, (BrowseExperience$ExperienceMap)new BrowseExperience$15()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW));
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new GenreLoLoMoAdapter(loLoMoFrag, s);
            }
            return new LoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
            if (equals) {
                return MovieDetailsActivity.class;
            }
            return ShowDetailsActivity.class;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            if (basicLoMo.getType() != LoMoType.BILLBOARD || BillboardView.shouldShowArtworkOnly(netflixActivity)) {
                return 0;
            }
            return 8;
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            return BrowseExperience$16.STANDARD.getPrefetchLolomoImageUrlList(context, video, loMoType);
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            return BrowseExperience$16.STANDARD.getViewImageUrl(context, video, clazz, n);
        }
    }, 
    TEST_LOLOMO_TITLE_ART_6725_CELL_4("TEST_LOLOMO_TITLE_ART_6725_CELL_4", 8, (BrowseExperience$ExperienceMap)new BrowseExperience$17()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW));
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new GenreLoLoMoAdapter(loLoMoFrag, s);
            }
            return new LoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
            if (equals) {
                return MovieDetailsActivity.class;
            }
            return ShowDetailsActivity.class;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            if (basicLoMo.getType() != LoMoType.BILLBOARD || BillboardView.shouldShowArtworkOnly(netflixActivity)) {
                return 0;
            }
            return 8;
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            return BrowseExperience$18.STANDARD.getPrefetchLolomoImageUrlList(context, video, loMoType);
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            return BrowseExperience$18.STANDARD.getViewImageUrl(context, video, clazz, n);
        }
    }, 
    TEST_LOLOMO_TITLE_ART_6725_CELL_5("TEST_LOLOMO_TITLE_ART_6725_CELL_5", 9, (BrowseExperience$ExperienceMap)new BrowseExperience$19()) {
        private final Set<VideoType> VALID_DETAIL_TYPES;
        
        {
            this.VALID_DETAIL_TYPES = new HashSet<VideoType>(Arrays.asList(VideoType.MOVIE, VideoType.SHOW));
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new GenreLoLoMoAdapter(loLoMoFrag, s);
            }
            return new LoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity netflixActivity, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(netflixActivity, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
            if (equals) {
                return MovieDetailsActivity.class;
            }
            return ShowDetailsActivity.class;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            if (basicLoMo.getType() != LoMoType.BILLBOARD || BillboardView.shouldShowArtworkOnly(netflixActivity)) {
                return 0;
            }
            return 8;
        }
        
        @Override
        public List<String> getPrefetchLolomoImageUrlList(final Context context, final Video video, final LoMoType loMoType) {
            return BrowseExperience$20.STANDARD.getPrefetchLolomoImageUrlList(context, video, loMoType);
        }
        
        @Override
        public String getViewImageUrl(final Context context, final Video video, final Class clazz, final int n) {
            return BrowseExperience$20.STANDARD.getViewImageUrl(context, video, clazz, n);
        }
    };
    
    protected static final String USE_KIDS_GENRES_LOMO = "USE_KIDS_GENRES_LOMO";
    protected static final String USE_LOLOMO_BOXART = "USE_LOLOMO_BOXART";
    private static final boolean USE_PRODUCTION_KUBRICK = false;
    private static int currCoppola1TestCell;
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
    
    public static int getLomoFragOffsetLeftDimenId() {
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
    
    public static boolean isKubrick() {
        return BrowseExperience.currExperience == BrowseExperience.KUBRICK_PRODUCTIZED || BrowseExperience.currExperience == BrowseExperience.KUBRICK_AB_TEST_HERO_IMAGES || BrowseExperience.currExperience == BrowseExperience.KUBRICK_AB_TEST_HIGH_DENSITY;
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
        if (b && DeviceUtils.isTabletByContext(context)) {
            BrowseExperience.currExperience = BrowseExperience.KIDS_TABLET_STANDARD;
        }
        else {
            switch (BrowseExperience$21.$SwitchMap$com$netflix$mediaclient$ui$experience$PersistentExperience[value.ordinal()]) {
                default: {
                    BrowseExperience currExperience;
                    if (BarkerHelper.isInTest(context)) {
                        currExperience = BrowseExperience.DISPLAY_PAGE_REFRESH;
                    }
                    else {
                        currExperience = BrowseExperience.STANDARD;
                    }
                    BrowseExperience.currExperience = currExperience;
                    break;
                }
                case 1: {
                    BrowseExperience.currExperience = BrowseExperience.KUBRICK_AB_TEST_HERO_IMAGES;
                    break;
                }
                case 2: {
                    BrowseExperience.currExperience = BrowseExperience.KUBRICK_AB_TEST_HIGH_DENSITY;
                    break;
                }
                case 3: {
                    BrowseExperience.currExperience = BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_2;
                    break;
                }
                case 4: {
                    BrowseExperience.currExperience = BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_5;
                    break;
                }
                case 5: {
                    BrowseExperience.currExperience = BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_3;
                    break;
                }
                case 6: {
                    BrowseExperience.currExperience = BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_4;
                    break;
                }
            }
        }
        if (Log.isLoggable()) {
            final BrowseExperience currExperience2 = BrowseExperience.currExperience;
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
            Log.v("BrowseExperience", String.format("Experience updated to: %s, profile name: %s, is kids profile: %s, USE_PRODUCTION_KUBRICK: %s", currExperience2, firstName, value2, false));
        }
        if (Log.isLoggable()) {
            Log.i("BrowseExperience", "Setting Crittercism username: " + String.valueOf(BrowseExperience.currExperience));
        }
        ErrorLoggingManager.setUsername(String.valueOf(BrowseExperience.currExperience));
    }
    
    public static boolean shouldForcePortraitInMemento(final Context context) {
        return DeviceUtils.isNotTabletByContext(context) && PersistentConfig.inMementoTest(context);
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
    
    public static boolean shouldShowLargePeakForKidsTopTen() {
        return BrowseExperience.currExperience.data.containsKey("SHOULD_SHOW_LARGE_PEAK_FOR_KIDS_TOP_TEN") && ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("SHOULD_SHOW_LARGE_PEAK_FOR_KIDS_TOP_TEN");
    }
    
    public static boolean shouldShowMemento(final Context context) {
        return PersistentConfig.getMemento(context).ordinal() == ABTestConfig$Cell.CELL_THREE.ordinal();
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
