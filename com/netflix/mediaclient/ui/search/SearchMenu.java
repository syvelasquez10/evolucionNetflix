// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SearchMenu
{
    public static void addSearchNavigation(final NetflixActivity netflixActivity, final Menu menu) {
        int icon;
        if (netflixActivity.isKubrick() && netflixActivity.isForKids()) {
            icon = 2130837700;
        }
        else {
            icon = 2130837699;
        }
        menu.add(0, 2131165250, 0, 2131493184).setIcon(icon).setIntent(SearchActivity.create(netflixActivity)).setShowAsAction(1);
    }
}
