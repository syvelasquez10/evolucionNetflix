// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.webclient.model.client.SocialPlaceholder;
import com.netflix.mediaclient.service.webclient.model.client.SocialConnectPlaceholder;
import com.netflix.mediaclient.service.webclient.model.client.SocialFriendPlaceholder;
import com.netflix.mediaclient.service.webclient.model.client.SocialGroupPlaceholder;
import com.netflix.mediaclient.Log;
import java.util.List;

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
    
    public static void injectSocialData(final LoMo loMo, final List<Video> list) {
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
