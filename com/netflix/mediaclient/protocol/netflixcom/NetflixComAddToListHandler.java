// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.ui.details.DetailsActivity$Action;
import java.util.List;

public class NetflixComAddToListHandler extends NetflixComVideoDetailsHandler
{
    @Override
    public boolean canHandle(final List<String> list) {
        return list.size() > 1;
    }
    
    @Override
    protected DetailsActivity$Action getAction() {
        return DetailsActivity$Action.AddToMyList;
    }
}
