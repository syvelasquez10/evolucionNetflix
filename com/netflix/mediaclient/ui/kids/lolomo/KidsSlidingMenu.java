// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import android.graphics.Typeface;
import android.widget.TextView;
import com.netflix.mediaclient.ui.home.StandardSlidingMenu$GenreRowHolder;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.StandardSlidingMenu;

public class KidsSlidingMenu extends StandardSlidingMenu
{
    private final int kidsItemTextColor;
    
    public KidsSlidingMenu(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
        super(netflixActivity, drawerLayout, false);
        this.notificationsDivider.setBackgroundColor(netflixActivity.getResources().getColor(2131689631));
        this.notificationsDivider.setVisibility(0);
        drawerLayout.findViewById(2131755930).setVisibility(8);
        drawerLayout.findViewById(2131755937).setVisibility(8);
        drawerLayout.findViewById(2131755924).getLayoutParams().height = netflixActivity.getResources().getDimensionPixelSize(2131427701);
        this.kidsItemTextColor = netflixActivity.getResources().getColor(2131689596);
        this.profileName.setTextColor(this.kidsItemTextColor);
        ViewUtils.setTextViewToBold(this.profileName);
        this.homeText.setTextColor(this.kidsItemTextColor);
        ViewUtils.setTextViewToBold(this.homeText);
        ((FrameLayout$LayoutParams)this.genresList.getLayoutParams()).bottomMargin = (int)netflixActivity.getResources().getDimension(2131427700);
        ViewUtils.removeShadow(this.homeText);
        this.homeText.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, netflixActivity.getResources().getDimensionPixelSize(2131427745)));
        drawerLayout.findViewById(2131755919).setBackgroundResource(2131689595);
    }
    
    @Override
    protected void updateAdapterViews(final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder, final boolean b) {
        super.updateAdapterViews(standardSlidingMenu$GenreRowHolder, b);
        standardSlidingMenu$GenreRowHolder.tv.setTextColor(this.kidsItemTextColor);
        final TextView tv = standardSlidingMenu$GenreRowHolder.tv;
        final Typeface typeface = standardSlidingMenu$GenreRowHolder.tv.getTypeface();
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        tv.setTypeface(typeface, n);
        standardSlidingMenu$GenreRowHolder.tv.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131427745)));
        ViewUtils.removeShadow(standardSlidingMenu$GenreRowHolder.tv);
    }
    
    @Override
    protected void updateSwitchProfileButton() {
        super.updateSwitchProfileButton();
        this.switchProfilesIcon.setImageResource(2130837729);
    }
}
