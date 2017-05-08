// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import com.google.android.exoplayer.util.Assertions;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public final class MediaPresentationDescriptionParser$ContentProtectionsBuilder implements Comparator<ContentProtection>
{
    private ArrayList<ContentProtection> adaptationSetProtections;
    private ArrayList<ContentProtection> currentRepresentationProtections;
    private ArrayList<ContentProtection> representationProtections;
    private boolean representationProtectionsSet;
    
    private void maybeAddContentProtection(final List<ContentProtection> list, final ContentProtection contentProtection) {
        if (!list.contains(contentProtection)) {
            for (int i = 0; i < list.size(); ++i) {
                Assertions.checkState(!list.get(i).schemeUriId.equals(contentProtection.schemeUriId));
            }
            list.add(contentProtection);
        }
    }
    
    public void addAdaptationSetProtection(final ContentProtection contentProtection) {
        if (this.adaptationSetProtections == null) {
            this.adaptationSetProtections = new ArrayList<ContentProtection>();
        }
        this.maybeAddContentProtection(this.adaptationSetProtections, contentProtection);
    }
    
    public ArrayList<ContentProtection> build() {
        if (this.adaptationSetProtections == null) {
            return this.representationProtections;
        }
        if (this.representationProtections == null) {
            return this.adaptationSetProtections;
        }
        for (int i = 0; i < this.representationProtections.size(); ++i) {
            this.maybeAddContentProtection(this.adaptationSetProtections, this.representationProtections.get(i));
        }
        return this.adaptationSetProtections;
    }
    
    @Override
    public int compare(final ContentProtection contentProtection, final ContentProtection contentProtection2) {
        return contentProtection.schemeUriId.compareTo(contentProtection2.schemeUriId);
    }
    
    public void endRepresentation() {
        boolean b = true;
        if (!this.representationProtectionsSet) {
            if (this.currentRepresentationProtections != null) {
                Collections.sort(this.currentRepresentationProtections, this);
            }
            this.representationProtections = this.currentRepresentationProtections;
            this.representationProtectionsSet = true;
        }
        else if (this.currentRepresentationProtections == null) {
            if (this.representationProtections != null) {
                b = false;
            }
            Assertions.checkState(b);
        }
        else {
            Collections.sort(this.currentRepresentationProtections, this);
            Assertions.checkState(this.currentRepresentationProtections.equals(this.representationProtections));
        }
        this.currentRepresentationProtections = null;
    }
}
