// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.util.StringUtils;
import java.util.HashMap;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.ui.iko.model.InteractivePostplayModel;

public class KongInteractivePostPlayModel extends InteractivePostplayModel
{
    public static final String TYPE = "KONG_POST_PLAY";
    KongInteractivePostPlayModel$KongPostPlaySounds audio;
    int autoPlayInterval;
    KongInteractivePostPlayModel$KongAvatar avatar;
    KongInteractivePostPlayModel$KongBattle battle;
    List<KongInteractivePostPlayModel$KongCollectionItems> collectionItems;
    boolean gatePostPlayOnAssetLoadFailure;
    boolean hideAnimation;
    KongInteractivePostPlayModel$KongPostPlayImages images;
    String initialState;
    String interactiveOriginalsPrimerVersion;
    KongInteractivePostPlayModel$KongNextEpisode nextEpisode;
    KongInteractivePostPlayModel$KongOpponent opponent;
    Map<String, LocalCachedFileMetadata> resourceUrlToLocalCachedFileMetadataMap;
    KongInteractivePostPlayModel$KongResultData resultData;
    KongInteractivePostPlayModel$KongPostPlayStrings strings;
    int trackId;
    
    public KongInteractivePostPlayModel() {
        this.resourceUrlToLocalCachedFileMetadataMap = new HashMap<String, LocalCachedFileMetadata>();
    }
    
    public int getAutoPlayInterval() {
        return this.autoPlayInterval;
    }
    
    public String getAutoPlayString() {
        if (this.strings == null || StringUtils.isEmpty(this.strings.autoPlayString)) {
            return "";
        }
        final StringBuilder sb = new StringBuilder(this.strings.autoPlayString);
        final int index = sb.indexOf("${remainingTime}");
        sb.replace(index, "${remainingTime}".length() + index, "%s");
        return sb.toString();
    }
    
    public KongInteractivePostPlayModel$KongImage getAvatarImage() {
        if (this.avatar == null || this.avatar.images == null) {
            return null;
        }
        return this.avatar.images._default;
    }
    
    public String getAvatarImageUrl() {
        if (this.getAvatarImage() == null) {
            return null;
        }
        return this.avatar.images._default.url;
    }
    
    public KongInteractivePostPlayModel$KongSound getAvatarSound() {
        if (this.avatar == null || this.avatar.audio == null) {
            return null;
        }
        return this.avatar.audio.soundEffect;
    }
    
    public KongInteractivePostPlayModel$KongImage getBackBTNImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.backBTN;
    }
    
    public String getBackBTNImageUrl() {
        if (this.getBackBTNImage() == null) {
            return null;
        }
        return this.images.backBTN.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getBackBTNTappedImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.backBTNTapped;
    }
    
    public String getBackBTNTappedImageUrl() {
        if (this.getBackBTNTappedImage() == null) {
            return null;
        }
        return this.images.backBTNTapped.url;
    }
    
    public String getBackLabelString() {
        if (this.strings == null) {
            return null;
        }
        return this.strings.backLabel;
    }
    
    public KongInteractivePostPlayModel$KongSound getBackVOSound() {
        if (this.audio == null) {
            return null;
        }
        return this.audio.backVO;
    }
    
    public String getBackVOSoundUrl() {
        if (this.getBackVOSound() == null) {
            return null;
        }
        return this.audio.backVO.url;
    }
    
    public String getBackgroundImageUrl() {
        final KongInteractivePostPlayModel$KongImage battleBackgroundImage = this.getBattleBackgroundImage();
        if (battleBackgroundImage == null) {
            return null;
        }
        return battleBackgroundImage.url;
    }
    
    public KongInteractivePostPlayModel$KongSound getBackgroundSound() {
        if (this.audio == null) {
            return null;
        }
        return this.audio.background;
    }
    
    public String getBackgroundSoundUrl() {
        if (this.getBackgroundSound() == null) {
            return null;
        }
        return this.audio.background.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getBadgeTappedImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.badgeTapped;
    }
    
    public String getBadgeTappedImageUrl() {
        if (this.getBadgeTappedImage() == null) {
            return null;
        }
        return this.images.badgeTapped.url;
    }
    
    public String getBattleAgainString() {
        if (this.resultData != null && this.resultData.strings != null) {
            return this.resultData.strings.battleAgain;
        }
        return null;
    }
    
    public KongInteractivePostPlayModel$KongImage getBattleBackgroundImage() {
        if (this.battle == null || this.battle.images == null) {
            return null;
        }
        return this.battle.images.background;
    }
    
    public KongInteractivePostPlayModel$KongSound getBattleButtonSound() {
        if (this.audio == null) {
            return null;
        }
        return this.audio.battleButton;
    }
    
    public String getBattleButtonSoundUrl() {
        if (this.getBattleButtonSound() == null) {
            return null;
        }
        return this.audio.battleButton.url;
    }
    
    public String getBattleButtonString() {
        if (this.strings == null) {
            return null;
        }
        return this.strings.battleButton;
    }
    
    public String getBattleOptInHeaderString() {
        if (this.resultData != null && this.resultData.strings != null && this.getNextEpisodeVideoId() > 0) {
            return this.resultData.strings.battleOptInHeader;
        }
        if (this.strings == null) {
            return null;
        }
        return this.strings.battleOptInHeader;
    }
    
    public KongInteractivePostPlayModel$KongSound getBattleOptInVOSound() {
        if (this.audio == null) {
            return null;
        }
        return this.audio.battleOptInVO;
    }
    
    public String getBattleOptInVOSoundUrl() {
        if (this.getBattleOptInVOSound() == null) {
            return null;
        }
        return this.audio.battleOptInVO.url;
    }
    
    public KongInteractivePostPlayModel$KongSound getBattleTitleSound() {
        if (this.battle == null || this.battle.audio == null) {
            return null;
        }
        return this.battle.audio.vo;
    }
    
    public String getBattleTitleString() {
        if (this.battle == null || this.battle.strings == null) {
            return null;
        }
        return this.battle.strings.name;
    }
    
    public KongInteractivePostPlayModel$KongImage getCardDropshadowImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.cardDropshadow;
    }
    
    public String getCardDropshadowImageUrl() {
        if (this.getCardDropshadowImage() == null) {
            return null;
        }
        return this.images.cardDropshadow.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getCardTappedImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.cardTapped;
    }
    
    public String getCardTappedImageUrl() {
        if (this.getCardTappedImage() == null) {
            return null;
        }
        return this.images.cardTapped.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getCloseBTNImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.closeBTN;
    }
    
    public String getCloseBTNImageUrl() {
        if (this.getCloseBTNImage() == null) {
            return null;
        }
        return this.images.closeBTN.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getCloseBTNTappedImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.closeBTNTapped;
    }
    
    public String getCloseBTNTappedImageUrl() {
        if (this.getCloseBTNTappedImage() == null) {
            return null;
        }
        return this.images.closeBTNTapped.url;
    }
    
    public List<KongInteractivePostPlayModel$KongCollectionItems> getCollectionItems() {
        return this.collectionItems;
    }
    
    public String getCreditString() {
        if (this.strings == null) {
            return null;
        }
        return this.strings.credit;
    }
    
    public KongInteractivePostPlayModel$KongImage getDotBlurParallaxImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.dotBlurParallax;
    }
    
    public String getDotBlurParallaxImageUrl() {
        if (this.getDotBlurParallaxImage() == null) {
            return null;
        }
        return this.images.dotBlurParallax.url;
    }
    
    public String getExitString() {
        if (this.strings == null) {
            return null;
        }
        return this.strings.exit;
    }
    
    public KongInteractivePostPlayModel$KongImage getHexParallaxImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.hexParallax;
    }
    
    public String getHexParallaxImageUrl() {
        if (this.getHexParallaxImage() == null) {
            return null;
        }
        return this.images.hexParallax.url;
    }
    
    public String getInitialState() {
        return this.initialState;
    }
    
    public String getInteractiveOriginalsPrimerVersion() {
        return this.interactiveOriginalsPrimerVersion;
    }
    
    @Override
    public int getInterrupterCount() {
        if (this.interrupterCount > 0) {
            return this.interrupterCount;
        }
        return 8;
    }
    
    public String getItemSelectionHeaderString() {
        if (this.strings == null) {
            return null;
        }
        return this.strings.itemSelectionHeader;
    }
    
    public KongInteractivePostPlayModel$KongSound getItemSelectionSound() {
        if (this.audio == null) {
            return null;
        }
        return this.audio.itemSelection;
    }
    
    public String getItemSelectionSoundUrl() {
        if (this.getItemSelectionSound() == null) {
            return null;
        }
        return this.audio.itemSelection.url;
    }
    
    public KongInteractivePostPlayModel$KongSound getItemUnlockSfxSound() {
        if (this.audio == null) {
            return null;
        }
        return this.audio.itemUnlockSfx;
    }
    
    public String getItemUnlockSfxSoundUrl() {
        if (this.getItemUnlockSfxSound() == null) {
            return null;
        }
        return this.audio.itemUnlockSfx.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getLeftGateImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.leftGate;
    }
    
    public String getLeftGateImageUrl() {
        if (this.getLeftGateImage() == null) {
            return null;
        }
        return this.images.leftGate.url;
    }
    
    public String getLoadingHeaderString() {
        if (this.strings == null) {
            return null;
        }
        return this.strings.loadingHeader;
    }
    
    public KongInteractivePostPlayModel$KongImage getNextEpisodeImage() {
        if (this.nextEpisode == null || this.nextEpisode.images == null) {
            return null;
        }
        return this.nextEpisode.images.interestingMomentStill;
    }
    
    public String getNextEpisodeImageUrl() {
        if (this.getNextEpisodeImage() == null) {
            return null;
        }
        return this.nextEpisode.images.interestingMomentStill.url;
    }
    
    public KongInteractivePostPlayModel$KongSound getNextEpisodeSound() {
        if (this.nextEpisode == null || this.nextEpisode.audio == null) {
            return null;
        }
        return this.nextEpisode.audio.vo;
    }
    
    public String getNextEpisodeString() {
        if (this.strings == null) {
            return null;
        }
        return this.strings.nextEpisode;
    }
    
    public String getNextEpisodeTitleString() {
        if (this.nextEpisode == null) {
            return null;
        }
        return this.nextEpisode.title;
    }
    
    public int getNextEpisodeVideoId() {
        if (this.nextEpisode == null) {
            return -1;
        }
        return this.nextEpisode.videoId;
    }
    
    public KongInteractivePostPlayModel$KongImage getOpponentImage() {
        if (this.opponent == null || this.opponent.images == null) {
            return null;
        }
        return this.opponent.images._default;
    }
    
    public String getOpponentImageUrl() {
        if (this.getOpponentImage() == null) {
            return null;
        }
        return this.opponent.images._default.url;
    }
    
    public String getOpponentNameString() {
        if (this.opponent == null || this.opponent.name == null) {
            return null;
        }
        return this.opponent.name.name;
    }
    
    public KongInteractivePostPlayModel$KongImage getPlusParallaxImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.plusParallax;
    }
    
    public String getPlusParallaxImageUrl() {
        if (this.getPlusParallaxImage() == null) {
            return null;
        }
        return this.images.plusParallax.url;
    }
    
    public String getPostPlayType() {
        return this.type;
    }
    
    public List<String> getPreCacheableResourcesList() {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.battle != null && this.battle.images != null && this.battle.images.background != null) {
            list.add(this.battle.images.background.url);
        }
        if (this.audio != null) {
            if (this.audio.background != null) {
                list.add(this.audio.background.url);
            }
            if (this.audio.battleOptInVO != null) {
                list.add(this.audio.battleOptInVO.url);
            }
            if (this.audio.itemSelection != null) {
                list.add(this.audio.itemSelection.url);
            }
            if (this.audio.itemUnlockSfx != null) {
                list.add(this.audio.itemUnlockSfx.url);
            }
            if (this.audio.unlockVO != null) {
                list.add(this.audio.unlockVO.url);
            }
        }
        if (this.battle != null && this.battle.audio != null && this.battle.audio.vo != null) {
            list.add(this.battle.audio.vo.url);
        }
        if (this.avatar != null) {
            if (this.avatar.audio != null && this.avatar.audio.soundEffect != null) {
                list.add(this.avatar.audio.soundEffect.url);
            }
            if (this.avatar.images != null && this.avatar.images._default != null) {
                list.add(this.avatar.images._default.url);
            }
        }
        if (this.opponent != null && this.opponent.images != null && this.opponent.images._default != null) {
            list.add(this.opponent.images._default.url);
        }
        if (this.nextEpisode != null) {
            if (this.nextEpisode.audio != null && this.nextEpisode.audio.vo != null) {
                list.add(this.nextEpisode.audio.vo.url);
            }
            if (this.nextEpisode.images != null && this.nextEpisode.images.interestingMomentStill != null) {
                list.add(this.nextEpisode.images.interestingMomentStill.url);
            }
        }
        if (this.resultData != null) {
            if (this.resultData.audio != null && this.resultData.audio.vo != null) {
                list.add(this.resultData.audio.vo.url);
            }
            if (this.resultData.audio != null && this.resultData.audio.soundEffect != null) {
                list.add(this.resultData.audio.soundEffect.url);
            }
            if (this.resultData.avatar != null && this.resultData.avatar.images != null && this.resultData.avatar.images.result != null) {
                list.add(this.resultData.avatar.images.result.url);
            }
            if (this.resultData.battle != null && this.resultData.battle.audio != null && this.resultData.battle.audio.vo != null) {
                list.add(this.resultData.battle.audio.vo.url);
            }
            if (this.isResultBattleCardAvailable()) {
                list.add(this.resultData.battleCard.url);
            }
        }
        if (this.images != null) {
            if (this.images.leftGate != null) {
                list.add(this.images.leftGate.url);
            }
            if (this.images.rightGate != null) {
                list.add(this.images.rightGate.url);
            }
            if (this.images.closeBTN != null) {
                list.add(this.images.closeBTN.url);
            }
            if (this.images.backBTN != null) {
                list.add(this.images.backBTN.url);
            }
            if (this.images.redFlare != null) {
                list.add(this.images.redFlare.url);
            }
            if (this.images.dotBlurParallax != null) {
                list.add(this.images.dotBlurParallax.url);
            }
            if (this.images.hexParallax != null) {
                list.add(this.images.hexParallax.url);
            }
            if (this.images.plusParallax != null) {
                list.add(this.images.plusParallax.url);
            }
            if (this.images.sunburst != null) {
                list.add(this.images.sunburst.url);
            }
            if (this.images.versusHex != null) {
                list.add(this.images.versusHex.url);
            }
            if (this.images.versusSwords != null) {
                list.add(this.images.versusSwords.url);
            }
            if (this.images.versusLock != null) {
                list.add(this.images.versusLock.url);
            }
            if (this.images.cardDropshadow != null) {
                list.add(this.images.cardDropshadow.url);
            }
            if (this.images.smAvatar != null) {
                list.add(this.images.smAvatar.url);
            }
            if (this.images.unlockEmptyBadge != null) {
                list.add(this.images.unlockEmptyBadge.url);
            }
            if (this.images.whiteFlare != null) {
                list.add(this.images.whiteFlare.url);
            }
        }
        if (this.collectionItems != null) {
            for (final KongInteractivePostPlayModel$KongCollectionItems kongInteractivePostPlayModel$KongCollectionItems : this.collectionItems) {
                if (kongInteractivePostPlayModel$KongCollectionItems != null) {
                    if (kongInteractivePostPlayModel$KongCollectionItems.images != null) {
                        if (kongInteractivePostPlayModel$KongCollectionItems.images.badge != null) {
                            list.add(kongInteractivePostPlayModel$KongCollectionItems.images.badge.url);
                        }
                        if (kongInteractivePostPlayModel$KongCollectionItems.images.battleBadge != null) {
                            list.add(kongInteractivePostPlayModel$KongCollectionItems.images.battleBadge.url);
                        }
                        if (kongInteractivePostPlayModel$KongCollectionItems.images.itemOnAvatar != null) {
                            list.add(kongInteractivePostPlayModel$KongCollectionItems.images.itemOnAvatar.url);
                        }
                    }
                    if (kongInteractivePostPlayModel$KongCollectionItems.audio == null) {
                        continue;
                    }
                    if (kongInteractivePostPlayModel$KongCollectionItems.audio.getVo() != null) {
                        list.add(kongInteractivePostPlayModel$KongCollectionItems.audio.getVo().url);
                    }
                    if (kongInteractivePostPlayModel$KongCollectionItems.audio.battleAgainVO == null) {
                        continue;
                    }
                    list.add(kongInteractivePostPlayModel$KongCollectionItems.audio.battleAgainVO.url);
                }
            }
        }
        return list;
    }
    
    public KongInteractivePostPlayModel$KongImage getRedFlareImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.redFlare;
    }
    
    public String getRedFlareImageUrl() {
        if (this.getRedFlareImage() == null) {
            return null;
        }
        return this.images.redFlare.url;
    }
    
    public Map<String, LocalCachedFileMetadata> getResourceUrlToLocalCachedFileMetadataMap() {
        return this.resourceUrlToLocalCachedFileMetadataMap;
    }
    
    public KongInteractivePostPlayModel$KongImage getResultAvatarImage() {
        if (this.resultData == null || this.resultData.avatar == null || this.resultData.avatar.images == null) {
            return null;
        }
        return this.resultData.avatar.images.result;
    }
    
    public String getResultAvatarImageUrl() {
        if (this.getResultAvatarImage() == null) {
            return null;
        }
        return this.resultData.avatar.images.result.url;
    }
    
    public KongInteractivePostPlayModel$KongSound getResultAvatarSound() {
        if (this.resultData == null || this.resultData.audio == null) {
            return null;
        }
        return this.resultData.audio.soundEffect;
    }
    
    public String getResultBattleCardImageUrl() {
        if (this.isResultBattleCardAvailable()) {
            return this.resultData.battleCard.url;
        }
        return null;
    }
    
    public KongInteractivePostPlayModel$KongSound getResultBattleSound() {
        if (this.resultData == null || this.resultData.battle == null || this.resultData.battle.audio == null) {
            return null;
        }
        return this.resultData.battle.audio.vo;
    }
    
    public KongInteractivePostPlayModel$KongResultData getResultData() {
        return this.resultData;
    }
    
    public KongInteractivePostPlayModel$KongSound getResultDataSound() {
        if (this.resultData == null || this.resultData.audio == null) {
            return null;
        }
        return this.resultData.audio.vo;
    }
    
    public String getResultDataWatchNextBattleOptInHeaderString() {
        if (this.resultData != null && this.resultData.strings != null) {
            return this.resultData.strings.battleOptInHeader;
        }
        return null;
    }
    
    public String getResultTitleString() {
        if (this.resultData == null || this.resultData.strings == null) {
            return null;
        }
        return this.resultData.strings.resultHeader;
    }
    
    public String getResultType() {
        if (this.resultData == null) {
            return null;
        }
        return this.resultData.type;
    }
    
    public KongInteractivePostPlayModel$KongImage getRightGateImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.rightGate;
    }
    
    public String getRightGateImageUrl() {
        if (this.getRightGateImage() == null) {
            return null;
        }
        return this.images.rightGate.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getSmAvatarImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.smAvatar;
    }
    
    public String getSmAvatarImageUrl() {
        if (this.getSmAvatarImage() == null) {
            return null;
        }
        return this.images.smAvatar.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getSunburstImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.sunburst;
    }
    
    public String getSunburstImageUrl() {
        if (this.getSunburstImage() == null) {
            return null;
        }
        return this.images.sunburst.url;
    }
    
    public int getTrackId() {
        return this.trackId;
    }
    
    @Override
    public String getType() {
        return this.type;
    }
    
    public KongInteractivePostPlayModel$KongImage getUnlockEmptyBadgeImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.unlockEmptyBadge;
    }
    
    public String getUnlockEmptyBadgeImageUrl() {
        if (this.getUnlockEmptyBadgeImage() == null) {
            return null;
        }
        return this.images.unlockEmptyBadge.url;
    }
    
    public KongInteractivePostPlayModel$KongSound getUnlockVOSound() {
        if (this.audio == null) {
            return null;
        }
        return this.audio.unlockVO;
    }
    
    public String getUnlockVOSoundUrl() {
        if (this.getUnlockVOSound() == null) {
            return null;
        }
        return this.audio.unlockVO.url;
    }
    
    public String getUnlockedHeaderString() {
        if (this.strings == null) {
            return null;
        }
        return this.strings.unlockedHeader;
    }
    
    public KongInteractivePostPlayModel$KongImage getVersusHexImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.versusHex;
    }
    
    public String getVersusHexImageUrl() {
        if (this.getVersusHexImage() == null) {
            return null;
        }
        return this.images.versusHex.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getVersusLockImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.versusLock;
    }
    
    public String getVersusLockImageUrl() {
        if (this.getVersusLockImage() == null) {
            return null;
        }
        return this.images.versusLock.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getVersusSwordsImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.versusSwords;
    }
    
    public String getVersusSwordsImageUrl() {
        if (this.getVersusSwordsImage() == null) {
            return null;
        }
        return this.images.versusSwords.url;
    }
    
    public KongInteractivePostPlayModel$KongImage getWhiteFlareImage() {
        if (this.images == null) {
            return null;
        }
        return this.images.whiteFlare;
    }
    
    public String getWhiteFlareImageUrl() {
        if (this.getWhiteFlareImage() == null) {
            return null;
        }
        return this.images.whiteFlare.url;
    }
    
    public boolean hasGatePostPlayOnAssetLoadFailure() {
        return this.gatePostPlayOnAssetLoadFailure;
    }
    
    public boolean hasWatchedAllBattleVideosForEpisode() {
        return this.resultData != null && this.resultData.hasWatchedAllBattleVideosForEpisode;
    }
    
    public boolean isNextEpisodeFocused() {
        return this.nextEpisode != null && this.nextEpisode.focused;
    }
    
    public boolean isResultBattleCardAvailable() {
        return this.resultData != null && this.resultData.battleCard != null;
    }
    
    public boolean shouldHideAnimation() {
        return this.hideAnimation;
    }
    
    @Override
    public String toString() {
        return "KongInteractivePostPlayModel{type='" + this.type + '\'' + ", initialState='" + this.initialState + '\'' + ", interactiveOriginalsPrimerVersion='" + this.interactiveOriginalsPrimerVersion + '\'' + ", autoPlayInterval=" + this.autoPlayInterval + ", trackId=" + this.trackId + ", strings=" + this.strings + ", audio=" + this.audio + ", battle=" + this.battle + ", images=" + this.images + ", avatar=" + this.avatar + ", opponent=" + this.opponent + ", nextEpisode=" + this.nextEpisode + ", collectionItems=" + this.collectionItems + ", resultData=" + this.resultData + '}';
    }
}
