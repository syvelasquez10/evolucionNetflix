// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialPlaceholder;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialConnectPlaceholder;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialFriendPlaceholder;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialGroupPlaceholder;
import com.netflix.mediaclient.Log;
import java.util.List;
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
    
    public static int getClientInjectedVideoCount(final BasicLoMo basicLoMo, final boolean b, final int n) {
        if (isClientModifiedRow(basicLoMo, b) && n == LoMoUtils.INJECTED_PAGE_INDEX) {
            return LoMoUtils.VIDEO_INJECT_COUNT;
        }
        return 0;
    }
    
    public static int getLomoFragOffsetLeftPx(final NetflixActivity netflixActivity) {
        if (netflixActivity.isForKids()) {
            return (int)(0.667f * netflixActivity.getResources().getDimensionPixelOffset(2131361968));
        }
        return netflixActivity.getResources().getDimensionPixelOffset(2131361896);
    }
    
    public static int getLomoFragOffsetRightPx(final NetflixActivity netflixActivity) {
        if (netflixActivity.isForKids()) {
            return (int)(1.333f * netflixActivity.getResources().getDimensionPixelOffset(2131361968));
        }
        return netflixActivity.getResources().getDimensionPixelOffset(2131361896);
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
    
    private static boolean isClientModifiedRow(final BasicLoMo basicLoMo, final boolean b) {
        return shouldInjectSocialData(basicLoMo, b);
    }
    
    private static boolean shouldInjectConnectToFacebook(final LoMoType loMoType, final boolean b) {
        return !b && loMoType == LoMoType.SOCIAL_POPULAR;
    }
    
    public static boolean shouldInjectSocialData(final BasicLoMo basicLoMo, final boolean b) {
        final LoMoType type = basicLoMo.getType();
        return shouldInjectConnectToFacebook(type, b) || type == LoMoType.SOCIAL_FRIEND || type == LoMoType.SOCIAL_GROUP;
    }
}
