// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.search.SearchMenu;
import android.view.Menu;

public class DetailsMenu
{
    public DetailsMenu(final DetailsActivity detailsActivity, final Menu menu, final boolean b) {
        SearchMenu.addSearchNavigation(detailsActivity, menu, b);
    }
}
