// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.MenuItem;

class ShareCompat$ShareCompatImplBase implements ShareCompat$ShareCompatImpl
{
    private static void withinStyle(final StringBuilder sb, final CharSequence charSequence, int i, final int n) {
        while (i < n) {
            final char char1 = charSequence.charAt(i);
            if (char1 == '<') {
                sb.append("&lt;");
            }
            else if (char1 == '>') {
                sb.append("&gt;");
            }
            else if (char1 == '&') {
                sb.append("&amp;");
            }
            else if (char1 > '~' || char1 < ' ') {
                sb.append("&#" + (int)char1 + ";");
            }
            else if (char1 == ' ') {
                while (i + 1 < n && charSequence.charAt(i + 1) == ' ') {
                    sb.append("&nbsp;");
                    ++i;
                }
                sb.append(' ');
            }
            else {
                sb.append(char1);
            }
            ++i;
        }
    }
    
    @Override
    public void configureMenuItem(final MenuItem menuItem, final ShareCompat$IntentBuilder shareCompat$IntentBuilder) {
        menuItem.setIntent(shareCompat$IntentBuilder.createChooserIntent());
    }
    
    @Override
    public String escapeHtml(final CharSequence charSequence) {
        final StringBuilder sb = new StringBuilder();
        withinStyle(sb, charSequence, 0, charSequence.length());
        return sb.toString();
    }
}
