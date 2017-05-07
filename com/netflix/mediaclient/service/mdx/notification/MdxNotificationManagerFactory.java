// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.mdx.MediaSessionController;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.content.Context;

public class MdxNotificationManagerFactory
{
    public static IMdxNotificationManager create(final Context context, final boolean b, final MdxAgent mdxAgent, final MediaSessionController mediaSessionController) {
        IMdxNotificationManager mdxNotificationManager;
        if (AndroidUtils.getAndroidVersion() < 21) {
            mdxNotificationManager = new MdxNotificationManager(context, b, mdxAgent);
        }
        else {
            mdxNotificationManager = new MdxNotificationManagerLollipop(context, b, mdxAgent, mediaSessionController);
        }
        return new MdxNotificationManagerWrapper(mdxNotificationManager, mdxAgent.getConfigurationAgent().getMdxConfiguration());
    }
}
