// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import java.util.HashMap;
import java.io.Serializable;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.content.Context;
import com.netflix.mediaclient.ui.search.SearchUtils$SearchExperience;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$KubrickHighDensityRowAdapterProvider;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickHighDensityLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickHighDensityGenreLoLoMoAdapter;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$KubrickKidsRowAdapterProvider;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$KubrickKidsHighDensityVerticalRowAdapterProvider;
import com.netflix.mediaclient.ui.kubrick_kids.details.KubrickKidsDetailsActivity;
import com.netflix.mediaclient.ui.kids.lolomo.KidsSlidingMenu;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$KubrickKidsHighDensityRowAdapterProvider;
import com.netflix.mediaclient.ui.kubrick_kids.lolomo.KubrickKidsLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick_kids.lolomo.KubrickKidsGenreLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickLolomoUtils;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsActivity;
import com.netflix.mediaclient.ui.kubrick.details.KubrickMovieDetailsActivity;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$KubrickRowAdapterProvider;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickGenreLoLoMoAdapter;
import com.netflix.mediaclient.ui.lomo.BillboardView;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.ui.details.ShowDetailsActivity;
import com.netflix.mediaclient.ui.details.MovieDetailsActivity;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.ui.home.StandardSlidingMenu;
import com.netflix.mediaclient.ui.home.SlidingMenuAdapter;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$StandardRowAdapterProvider;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$IRowAdapterProvider;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.RowAdapterCallbacks;
import com.netflix.mediaclient.servicemgr.ServiceManager;
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
    protected static final String IMAGE_LOADER_CONFIG_ENUM = "IMAGE_LOADER_CONFIG_ENUM";
    protected static final String IMAGE_LOADER_CONFIG_NO_PLACEHOLDER_ENUM = "IMAGE_LOADER_CONFIG_NO_PLACEHOLDER_ENUM";
    
    KUBRICK_AB_TEST_HERO_IMAGES("KUBRICK_AB_TEST_HERO_IMAGES", 6, (BrowseExperience$ExperienceMap)new BrowseExperience$13()) {
        BrowseExperience$14(final String s, final int n, final BrowseExperience$ExperienceMap browseExperience$ExperienceMap) {
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            return BrowseExperience$14.KUBRICK_PRODUCTIZED.createLolomoAdapter(loLoMoFrag, b, s);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return BrowseExperience$14.KUBRICK_PRODUCTIZED.createRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return BrowseExperience$14.STANDARD.createSlidingMenuAdapter(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            return BrowseExperience$14.KUBRICK_PRODUCTIZED.getDetailsClassTypeForVideo(videoType);
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            return BrowseExperience$14.KUBRICK_PRODUCTIZED.getLomoRowTitleVisibility(netflixActivity, basicLoMo);
        }
    }, 
    KUBRICK_AB_TEST_HIGH_DENSITY("KUBRICK_AB_TEST_HIGH_DENSITY", 7, (BrowseExperience$ExperienceMap)new BrowseExperience$15()) {
        BrowseExperience$16(final String s, final int n, final BrowseExperience$ExperienceMap browseExperience$ExperienceMap) {
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new KubrickHighDensityGenreLoLoMoAdapter(loLoMoFrag, s);
            }
            return new KubrickHighDensityLoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$KubrickHighDensityRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return BrowseExperience$16.STANDARD.createSlidingMenuAdapter(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            return BrowseExperience$16.KUBRICK_PRODUCTIZED.getDetailsClassTypeForVideo(videoType);
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            return BrowseExperience$16.STANDARD.getLomoRowTitleVisibility(netflixActivity, basicLoMo);
        }
    }, 
    KUBRICK_PRODUCTIZED("KUBRICK_PRODUCTIZED", 1, (BrowseExperience$ExperienceMap)new BrowseExperience$3()) {
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
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$KubrickRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
                return KubrickMovieDetailsActivity.class;
            }
            return KubrickShowDetailsActivity.class;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            if (KubrickLolomoUtils.isDuplicateRow(basicLoMo)) {
                return 8;
            }
            return BrowseExperience$4.STANDARD.getLomoRowTitleVisibility(netflixActivity, basicLoMo);
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
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new GenreLoLoMoAdapter(loLoMoFrag, s);
            }
            return new LoLoMoAdapter(loLoMoFrag);
        }
        
        @Override
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
    };
    
    private static final String TAG = "BrowseExperience";
    
    TEST_KoT_6538_CELL_2("TEST_KoT_6538_CELL_2", 2, (BrowseExperience$ExperienceMap)new BrowseExperience$5()) {
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
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
    }, 
    TEST_KoT_6538_CELL_3("TEST_KoT_6538_CELL_3", 5, (BrowseExperience$ExperienceMap)new BrowseExperience$11()) {
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
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$KubrickKidsRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return new KidsSlidingMenu(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            if (this.VALID_DETAIL_TYPES.contains(videoType)) {
                return KubrickKidsDetailsActivity.class;
            }
            return null;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            switch (BrowseExperience$25.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[basicLoMo.getType().ordinal()]) {
                default: {
                    return 0;
                }
                case 1: {
                    return 8;
                }
                case 2: {
                    return 4;
                }
            }
        }
    }, 
    TEST_KoT_6538_CELL_4("TEST_KoT_6538_CELL_4", 3, (BrowseExperience$ExperienceMap)new BrowseExperience$7()) {
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
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$KubrickKidsHighDensityRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return new KidsSlidingMenu(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            if (this.VALID_DETAIL_TYPES.contains(videoType)) {
                return KubrickKidsDetailsActivity.class;
            }
            return null;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            switch (BrowseExperience$25.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[basicLoMo.getType().ordinal()]) {
                default: {
                    return 0;
                }
                case 1: {
                    return 8;
                }
            }
        }
    }, 
    TEST_KoT_6538_CELL_5("TEST_KoT_6538_CELL_5", 4, (BrowseExperience$ExperienceMap)new BrowseExperience$9()) {
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
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$KubrickKidsHighDensityVerticalRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        
        @Override
        public SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
            return new KidsSlidingMenu(netflixActivity, drawerLayout);
        }
        
        @Override
        public Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType videoType) {
            if (this.VALID_DETAIL_TYPES.contains(videoType)) {
                return KubrickKidsDetailsActivity.class;
            }
            return null;
        }
        
        @Override
        public int getLomoRowTitleVisibility(final NetflixActivity netflixActivity, final BasicLoMo basicLoMo) {
            switch (BrowseExperience$25.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[basicLoMo.getType().ordinal()]) {
                default: {
                    return 0;
                }
                case 1: {
                    return 8;
                }
            }
        }
    }, 
    TEST_LOLOMO_TITLE_ART_6725_CELL_2("TEST_LOLOMO_TITLE_ART_6725_CELL_2", 8, (BrowseExperience$ExperienceMap)new BrowseExperience$17()) {
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
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
    }, 
    TEST_LOLOMO_TITLE_ART_6725_CELL_3("TEST_LOLOMO_TITLE_ART_6725_CELL_3", 9, (BrowseExperience$ExperienceMap)new BrowseExperience$19()) {
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
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
    }, 
    TEST_LOLOMO_TITLE_ART_6725_CELL_4("TEST_LOLOMO_TITLE_ART_6725_CELL_4", 10, (BrowseExperience$ExperienceMap)new BrowseExperience$21()) {
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
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
    }, 
    TEST_LOLOMO_TITLE_ART_6725_CELL_5("TEST_LOLOMO_TITLE_ART_6725_CELL_5", 11, (BrowseExperience$ExperienceMap)new BrowseExperience$23()) {
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
        public RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
            return new RowAdapterProvider$StandardRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
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
    };
    
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
    
    public static int getLomoFragOffsetLeftDimenId() {
        return ((HashMap<K, Integer>)BrowseExperience.currExperience.data).get("LOMO_FRAG_OFFSET_LEFT_DIMEN_INT");
    }
    
    public static SearchUtils$SearchExperience getSearchExperience() {
        return ((HashMap<K, SearchUtils$SearchExperience>)BrowseExperience.currExperience.data).get("SEARCH_EXPERIENCE_ENUM");
    }
    
    public static boolean isKubrick() {
        return BrowseExperience.currExperience == BrowseExperience.KUBRICK_PRODUCTIZED || BrowseExperience.currExperience == BrowseExperience.KUBRICK_AB_TEST_HERO_IMAGES || BrowseExperience.currExperience == BrowseExperience.KUBRICK_AB_TEST_HIGH_DENSITY;
    }
    
    public static boolean isKubrickKids() {
        return BrowseExperience.currExperience == BrowseExperience.TEST_KoT_6538_CELL_3 || BrowseExperience.currExperience == BrowseExperience.TEST_KoT_6538_CELL_4 || BrowseExperience.currExperience == BrowseExperience.TEST_KoT_6538_CELL_5;
    }
    
    public static void refresh(final Context context, final UserProfile userProfile) {
        final PersistentExperience value = PersistentExperience.get(context);
        final boolean b = userProfile != null && userProfile.isKidsProfile();
        switch (BrowseExperience$25.$SwitchMap$com$netflix$mediaclient$ui$experience$PersistentExperience[value.ordinal()]) {
            default: {
                BrowseExperience.currExperience = BrowseExperience.STANDARD;
                break;
            }
            case 1: {
                BrowseExperience currExperience;
                if (b) {
                    currExperience = BrowseExperience.STANDARD;
                }
                else {
                    currExperience = BrowseExperience.KUBRICK_AB_TEST_HERO_IMAGES;
                }
                BrowseExperience.currExperience = currExperience;
                break;
            }
            case 2: {
                BrowseExperience currExperience2;
                if (b) {
                    currExperience2 = BrowseExperience.STANDARD;
                }
                else {
                    currExperience2 = BrowseExperience.KUBRICK_AB_TEST_HIGH_DENSITY;
                }
                BrowseExperience.currExperience = currExperience2;
                break;
            }
            case 3: {
                BrowseExperience currExperience3;
                if (b) {
                    currExperience3 = BrowseExperience.TEST_KoT_6538_CELL_2;
                }
                else {
                    currExperience3 = BrowseExperience.STANDARD;
                }
                BrowseExperience.currExperience = currExperience3;
                break;
            }
            case 4: {
                BrowseExperience currExperience4;
                if (b) {
                    currExperience4 = BrowseExperience.TEST_KoT_6538_CELL_3;
                }
                else {
                    currExperience4 = BrowseExperience.STANDARD;
                }
                BrowseExperience.currExperience = currExperience4;
                break;
            }
            case 5: {
                BrowseExperience currExperience5;
                if (b) {
                    currExperience5 = BrowseExperience.TEST_KoT_6538_CELL_4;
                }
                else {
                    currExperience5 = BrowseExperience.STANDARD;
                }
                BrowseExperience.currExperience = currExperience5;
                break;
            }
            case 6: {
                BrowseExperience currExperience6;
                if (b) {
                    currExperience6 = BrowseExperience.TEST_KoT_6538_CELL_5;
                }
                else {
                    currExperience6 = BrowseExperience.STANDARD;
                }
                BrowseExperience.currExperience = currExperience6;
                break;
            }
            case 7: {
                BrowseExperience.currExperience = BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_2;
                break;
            }
            case 8: {
                BrowseExperience.currExperience = BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_5;
                break;
            }
            case 9: {
                BrowseExperience.currExperience = BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_3;
                break;
            }
            case 10: {
                BrowseExperience.currExperience = BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_4;
                break;
            }
        }
        if (Log.isLoggable()) {
            final BrowseExperience currExperience7 = BrowseExperience.currExperience;
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
            Log.v("BrowseExperience", String.format("Experience updated to: %s, profile name: %s, is kids profile: %s, USE_PRODUCTION_KUBRICK: %s", currExperience7, firstName, value2, false));
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
    
    public static boolean shouldShowLargePeakForKidsTopTen() {
        return BrowseExperience.currExperience.data.containsKey("SHOULD_SHOW_LARGE_PEAK_FOR_KIDS_TOP_TEN") && ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("SHOULD_SHOW_LARGE_PEAK_FOR_KIDS_TOP_TEN");
    }
    
    public static boolean useKidsGenresLoMo() {
        return ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("USE_KIDS_GENRES_LOMO");
    }
    
    public static boolean useLolomoBoxArt() {
        return ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("USE_LOLOMO_BOXART");
    }
}
