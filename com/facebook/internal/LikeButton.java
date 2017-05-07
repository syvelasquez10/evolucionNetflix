// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.android.R$string;
import com.facebook.android.R$drawable;
import android.graphics.Typeface;
import com.facebook.android.R$dimen;
import com.facebook.android.R$color;
import android.content.Context;
import android.widget.Button;

public class LikeButton extends Button
{
    private boolean isLiked;
    
    public LikeButton(final Context context, final boolean isLiked) {
        super(context);
        this.isLiked = isLiked;
        this.initialize();
    }
    
    private void initialize() {
        this.setGravity(16);
        this.setTextColor(this.getResources().getColor(R$color.com_facebook_likebutton_text_color));
        this.setTextSize(0, this.getResources().getDimension(R$dimen.com_facebook_likebutton_text_size));
        this.setTypeface(Typeface.DEFAULT_BOLD);
        this.setCompoundDrawablePadding(this.getResources().getDimensionPixelSize(R$dimen.com_facebook_likebutton_compound_drawable_padding));
        this.setPadding(this.getResources().getDimensionPixelSize(R$dimen.com_facebook_likebutton_padding_left), this.getResources().getDimensionPixelSize(R$dimen.com_facebook_likebutton_padding_top), this.getResources().getDimensionPixelSize(R$dimen.com_facebook_likebutton_padding_right), this.getResources().getDimensionPixelSize(R$dimen.com_facebook_likebutton_padding_bottom));
        this.updateForLikeStatus();
    }
    
    private void updateForLikeStatus() {
        if (this.isLiked) {
            this.setBackgroundResource(R$drawable.com_facebook_button_like_selected);
            this.setCompoundDrawablesWithIntrinsicBounds(R$drawable.com_facebook_button_like_icon_selected, 0, 0, 0);
            this.setText((CharSequence)this.getResources().getString(R$string.com_facebook_like_button_liked));
            return;
        }
        this.setBackgroundResource(R$drawable.com_facebook_button_like);
        this.setCompoundDrawablesWithIntrinsicBounds(R$drawable.com_facebook_button_like_icon, 0, 0, 0);
        this.setText((CharSequence)this.getResources().getString(R$string.com_facebook_like_button_not_liked));
    }
    
    public void setLikeState(final boolean isLiked) {
        if (isLiked != this.isLiked) {
            this.isLiked = isLiked;
            this.updateForLikeStatus();
        }
    }
}
