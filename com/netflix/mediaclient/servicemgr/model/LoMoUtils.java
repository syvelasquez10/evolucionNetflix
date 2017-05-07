// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialPlaceholder;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialConnectPlaceholder;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialFriendPlaceholder;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialGroupPlaceholder;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class LoMoUtils
{
    private static int INJECTED_PAGE_INDEX = 0;
    private static final String TAG = "LoMoUtils";
    private static int VIDEO_INJECT_COUNT;
    
    static {
        LoMoUtils.VIDEO_INJECT_COUNT = 1;
        LoMoUtils.INJECTED_PAGE_INDEX = 0;
    }
    
    public static void applyContentOverlapPadding(final NetflixActivity netflixActivity, final View view, final LoMoUtils$LoMoWidthType loMoUtils$LoMoWidthType) {
        view.setPadding(getLomoFragOffsetLeftPx(netflixActivity), 0, getLomoFragOffsetRightPx(netflixActivity, loMoUtils$LoMoWidthType), 0);
    }
    
    public static int getClientInjectedVideoCount(final BasicLoMo basicLoMo, final boolean b, final boolean b2, final int n) {
        if (isClientModifiedRow(basicLoMo, b, b2) && n == LoMoUtils.INJECTED_PAGE_INDEX) {
            return LoMoUtils.VIDEO_INJECT_COUNT;
        }
        return 0;
    }
    
    public static int getLomoFragImageOffsetLeftPx(final NetflixActivity netflixActivity) {
        return getLomoFragOffsetLeftPx(netflixActivity) + netflixActivity.getResources().getDimensionPixelOffset(2131361913);
    }
    
    public static int getLomoFragOffsetLeftPx(final NetflixActivity netflixActivity) {
        if (netflixActivity.isKubrick()) {
            return netflixActivity.getResources().getDimensionPixelOffset(2131361991);
        }
        if (netflixActivity.isForKids()) {
            return netflixActivity.getResources().getDimensionPixelOffset(2131361974);
        }
        return netflixActivity.getResources().getDimensionPixelOffset(2131361912);
    }
    
    public static int getLomoFragOffsetRightPx(final NetflixActivity netflixActivity, final LoMoUtils$LoMoWidthType loMoUtils$LoMoWidthType) {
        if (Log.isLoggable("LoMoUtils", 2)) {
            Log.v("LoMoUtils", "getLomoFragOffsetRightPx, activity: " + netflixActivity.getClass().getSimpleName() + ", widthType: " + loMoUtils$LoMoWidthType);
        }
        switch (LoMoUtils$1.$SwitchMap$com$netflix$mediaclient$servicemgr$model$LoMoUtils$LoMoWidthType[loMoUtils$LoMoWidthType.ordinal()]) {
            default: {
                if (netflixActivity.isKubrick()) {
                    return netflixActivity.getResources().getDimensionPixelOffset(2131361992);
                }
                if (netflixActivity.isForKids()) {
                    return netflixActivity.getResources().getDimensionPixelOffset(2131361975);
                }
                return netflixActivity.getResources().getDimensionPixelOffset(2131361912);
            }
            case 1: {
                return netflixActivity.getResources().getDimensionPixelOffset(2131362009);
            }
        }
    }
    
    public static void injectSocialData(final LoMo loMo, final List<Video> list) {
        list.remove(0);
        final SocialGroupPlaceholder socialGroupPlaceholder = null;
        final LoMoType type = loMo.getType();
        Log.d("LoMoUtils", "Injecting social data for type: " + type);
        SocialPlaceholder socialPlaceholder;
        if (type == LoMoType.SOCIAL_GROUP) {
            socialPlaceholder = new SocialGroupPlaceholder(loMo);
        }
        else if (type == LoMoType.SOCIAL_FRIEND) {
            socialPlaceholder = new SocialFriendPlaceholder(loMo);
        }
        else if (type == LoMoType.SOCIAL_POPULAR) {
            socialPlaceholder = new SocialConnectPlaceholder(loMo);
        }
        else {
            Log.w("LoMoUtils", "Unrecognized type for social data injection: " + type);
            socialPlaceholder = socialGroupPlaceholder;
        }
        list.add(0, socialPlaceholder);
    }
    
    private static boolean isClientModifiedRow(final BasicLoMo basicLoMo, final boolean b, final boolean b2) {
        return shouldInjectSocialData(basicLoMo, b, b2);
    }
    
    private static boolean shouldInjectConnectToFacebook(final LoMoType loMoType, final boolean b) {
        return !b && loMoType == LoMoType.SOCIAL_POPULAR;
    }
    
    public static boolean shouldInjectSocialData(final BasicLoMo basicLoMo, final boolean b, final boolean b2) {
        if (!b2) {
            final LoMoType type = basicLoMo.getType();
            if (shouldInjectConnectToFacebook(type, b) || type == LoMoType.SOCIAL_FRIEND || type == LoMoType.SOCIAL_GROUP) {
                return true;
            }
        }
        return false;
    }
}
