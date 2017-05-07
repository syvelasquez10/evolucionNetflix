// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.falkor.Func;

final class Falkor$Creator$21 implements Func<SocialNotificationsListSummary>
{
    @Override
    public SocialNotificationsListSummary call() {
        return new SocialNotificationsListSummary();
    }
}
