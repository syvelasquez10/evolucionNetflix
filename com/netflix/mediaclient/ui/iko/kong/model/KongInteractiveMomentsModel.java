// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;

public class KongInteractiveMomentsModel extends InteractiveMomentsModel
{
    public static final String TYPE = "kong";
    List<KongInteractiveMomentsModel$KongInteractiveMoment> moments;
    
    public List<KongInteractiveMomentsModel$KongInteractiveMoment> getMoments() {
        return this.moments;
    }
    
    public List<String> getPreCacheableResourcesList() {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.moments != null) {
            for (final KongInteractiveMomentsModel$KongInteractiveMoment kongInteractiveMomentsModel$KongInteractiveMoment : this.moments) {
                if (kongInteractiveMomentsModel$KongInteractiveMoment != null && kongInteractiveMomentsModel$KongInteractiveMoment.notification != null) {
                    final String momentImageUrl = kongInteractiveMomentsModel$KongInteractiveMoment.getMomentImageUrl();
                    final String momentAnimationImageUrl = kongInteractiveMomentsModel$KongInteractiveMoment.getMomentAnimationImageUrl();
                    final String unlockSfxSoundUrl = kongInteractiveMomentsModel$KongInteractiveMoment.getUnlockSfxSoundUrl();
                    if (StringUtils.isNotEmpty(momentImageUrl)) {
                        list.add(momentImageUrl);
                    }
                    if (StringUtils.isNotEmpty(momentAnimationImageUrl)) {
                        list.add(momentAnimationImageUrl);
                    }
                    if (!StringUtils.isNotEmpty(unlockSfxSoundUrl)) {
                        continue;
                    }
                    list.add(unlockSfxSoundUrl);
                }
            }
        }
        return list;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String toString() {
        return "KongInteractiveMomentsModel{type='" + this.type + '\'' + ", moments=" + this.moments + '}';
    }
}
