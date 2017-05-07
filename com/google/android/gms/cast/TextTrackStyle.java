// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.m;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.jz;
import org.json.JSONException;
import android.view.accessibility.CaptioningManager$CaptionStyle;
import android.graphics.Typeface;
import android.view.accessibility.CaptioningManager;
import com.google.android.gms.internal.kc;
import android.content.Context;
import android.graphics.Color;
import org.json.JSONObject;

public final class TextTrackStyle
{
    public static final int COLOR_UNSPECIFIED = 0;
    public static final float DEFAULT_FONT_SCALE = 1.0f;
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int EDGE_TYPE_UNSPECIFIED = -1;
    public static final int FONT_FAMILY_CASUAL = 4;
    public static final int FONT_FAMILY_CURSIVE = 5;
    public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
    public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
    public static final int FONT_FAMILY_SANS_SERIF = 0;
    public static final int FONT_FAMILY_SERIF = 2;
    public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
    public static final int FONT_FAMILY_UNSPECIFIED = -1;
    public static final int FONT_STYLE_BOLD = 1;
    public static final int FONT_STYLE_BOLD_ITALIC = 3;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_NORMAL = 0;
    public static final int FONT_STYLE_UNSPECIFIED = -1;
    public static final int WINDOW_TYPE_NONE = 0;
    public static final int WINDOW_TYPE_NORMAL = 1;
    public static final int WINDOW_TYPE_ROUNDED = 2;
    public static final int WINDOW_TYPE_UNSPECIFIED = -1;
    private JSONObject Fl;
    private float Gd;
    private int Ge;
    private int Gf;
    private int Gg;
    private int Gh;
    private int Gi;
    private int Gj;
    private String Gk;
    private int Gl;
    private int Gm;
    private int xm;
    
    public TextTrackStyle() {
        this.clear();
    }
    
    private int aC(final String s) {
        int argb;
        final int n = argb = 0;
        if (s == null) {
            return argb;
        }
        argb = n;
        if (s.length() != 9) {
            return argb;
        }
        argb = n;
        if (s.charAt(0) != '#') {
            return argb;
        }
        try {
            argb = Color.argb(Integer.parseInt(s.substring(7, 9), 16), Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
            return argb;
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    private void clear() {
        this.Gd = 1.0f;
        this.Ge = 0;
        this.xm = 0;
        this.Gf = -1;
        this.Gg = 0;
        this.Gh = -1;
        this.Gi = 0;
        this.Gj = 0;
        this.Gk = null;
        this.Gl = -1;
        this.Gm = -1;
        this.Fl = null;
    }
    
    public static TextTrackStyle fromSystemSettings(final Context context) {
        final TextTrackStyle textTrackStyle = new TextTrackStyle();
        if (!kc.hH()) {
            return textTrackStyle;
        }
        final CaptioningManager captioningManager = (CaptioningManager)context.getSystemService("captioning");
        textTrackStyle.setFontScale(captioningManager.getFontScale());
        final CaptioningManager$CaptionStyle userStyle = captioningManager.getUserStyle();
        textTrackStyle.setBackgroundColor(userStyle.backgroundColor);
        textTrackStyle.setForegroundColor(userStyle.foregroundColor);
        switch (userStyle.edgeType) {
            default: {
                textTrackStyle.setEdgeType(0);
                break;
            }
            case 1: {
                textTrackStyle.setEdgeType(1);
                break;
            }
            case 2: {
                textTrackStyle.setEdgeType(2);
                break;
            }
        }
        textTrackStyle.setEdgeColor(userStyle.edgeColor);
        final Typeface typeface = userStyle.getTypeface();
        if (typeface != null) {
            if (Typeface.MONOSPACE.equals((Object)typeface)) {
                textTrackStyle.setFontGenericFamily(1);
            }
            else if (Typeface.SANS_SERIF.equals((Object)typeface)) {
                textTrackStyle.setFontGenericFamily(0);
            }
            else if (Typeface.SERIF.equals((Object)typeface)) {
                textTrackStyle.setFontGenericFamily(2);
            }
            else {
                textTrackStyle.setFontGenericFamily(0);
            }
            final boolean bold = typeface.isBold();
            final boolean italic = typeface.isItalic();
            if (bold && italic) {
                textTrackStyle.setFontStyle(3);
            }
            else if (bold) {
                textTrackStyle.setFontStyle(1);
            }
            else if (italic) {
                textTrackStyle.setFontStyle(2);
            }
            else {
                textTrackStyle.setFontStyle(0);
            }
        }
        return textTrackStyle;
    }
    
    private String t(final int n) {
        return String.format("#%02X%02X%02X%02X", Color.red(n), Color.green(n), Color.blue(n), Color.alpha(n));
    }
    
    public JSONObject bL() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fontScale", (double)this.Gd);
            if (this.Ge != 0) {
                jsonObject.put("foregroundColor", (Object)this.t(this.Ge));
            }
            if (this.xm != 0) {
                jsonObject.put("backgroundColor", (Object)this.t(this.xm));
            }
            switch (this.Gf) {
                case 0: {
                    jsonObject.put("edgeType", (Object)"NONE");
                    break;
                }
                case 1: {
                    jsonObject.put("edgeType", (Object)"OUTLINE");
                    break;
                }
                case 2: {
                    jsonObject.put("edgeType", (Object)"DROP_SHADOW");
                    break;
                }
                case 3: {
                    jsonObject.put("edgeType", (Object)"RAISED");
                    break;
                }
                case 4: {
                    jsonObject.put("edgeType", (Object)"DEPRESSED");
                    break;
                }
            }
            if (this.Gg != 0) {
                jsonObject.put("edgeColor", (Object)this.t(this.Gg));
            }
            switch (this.Gh) {
                case 0: {
                    jsonObject.put("windowType", (Object)"NONE");
                    break;
                }
                case 1: {
                    jsonObject.put("windowType", (Object)"NORMAL");
                    break;
                }
                case 2: {
                    jsonObject.put("windowType", (Object)"ROUNDED_CORNERS");
                    break;
                }
            }
            if (this.Gi != 0) {
                jsonObject.put("windowColor", (Object)this.t(this.Gi));
            }
            if (this.Gh == 2) {
                jsonObject.put("windowRoundedCornerRadius", this.Gj);
            }
            if (this.Gk != null) {
                jsonObject.put("fontFamily", (Object)this.Gk);
            }
            switch (this.Gl) {
                case 0: {
                    jsonObject.put("fontGenericFamily", (Object)"SANS_SERIF");
                    break;
                }
                case 1: {
                    jsonObject.put("fontGenericFamily", (Object)"MONOSPACED_SANS_SERIF");
                    break;
                }
                case 2: {
                    jsonObject.put("fontGenericFamily", (Object)"SERIF");
                    break;
                }
                case 3: {
                    jsonObject.put("fontGenericFamily", (Object)"MONOSPACED_SERIF");
                    break;
                }
                case 4: {
                    jsonObject.put("fontGenericFamily", (Object)"CASUAL");
                    break;
                }
                case 5: {
                    jsonObject.put("fontGenericFamily", (Object)"CURSIVE");
                    break;
                }
                case 6: {
                    jsonObject.put("fontGenericFamily", (Object)"SMALL_CAPITALS");
                    break;
                }
            }
            switch (this.Gm) {
                case 0: {
                    jsonObject.put("fontStyle", (Object)"NORMAL");
                    break;
                }
                case 1: {
                    jsonObject.put("fontStyle", (Object)"BOLD");
                    break;
                }
                case 2: {
                    jsonObject.put("fontStyle", (Object)"ITALIC");
                    break;
                }
                case 3: {
                    jsonObject.put("fontStyle", (Object)"BOLD_ITALIC");
                    break;
                }
            }
            if (this.Fl != null) {
                jsonObject.put("customData", (Object)this.Fl);
                return jsonObject;
            }
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    public void c(final JSONObject jsonObject) throws JSONException {
        this.clear();
        this.Gd = (float)jsonObject.optDouble("fontScale", 1.0);
        this.Ge = this.aC(jsonObject.optString("foregroundColor"));
        this.xm = this.aC(jsonObject.optString("backgroundColor"));
        if (jsonObject.has("edgeType")) {
            final String string = jsonObject.getString("edgeType");
            if ("NONE".equals(string)) {
                this.Gf = 0;
            }
            else if ("OUTLINE".equals(string)) {
                this.Gf = 1;
            }
            else if ("DROP_SHADOW".equals(string)) {
                this.Gf = 2;
            }
            else if ("RAISED".equals(string)) {
                this.Gf = 3;
            }
            else if ("DEPRESSED".equals(string)) {
                this.Gf = 4;
            }
        }
        this.Gg = this.aC(jsonObject.optString("edgeColor"));
        if (jsonObject.has("windowType")) {
            final String string2 = jsonObject.getString("windowType");
            if ("NONE".equals(string2)) {
                this.Gh = 0;
            }
            else if ("NORMAL".equals(string2)) {
                this.Gh = 1;
            }
            else if ("ROUNDED_CORNERS".equals(string2)) {
                this.Gh = 2;
            }
        }
        this.Gi = this.aC(jsonObject.optString("windowColor"));
        if (this.Gh == 2) {
            this.Gj = jsonObject.optInt("windowRoundedCornerRadius", 0);
        }
        this.Gk = jsonObject.optString("fontFamily", (String)null);
        if (jsonObject.has("fontGenericFamily")) {
            final String string3 = jsonObject.getString("fontGenericFamily");
            if ("SANS_SERIF".equals(string3)) {
                this.Gl = 0;
            }
            else if ("MONOSPACED_SANS_SERIF".equals(string3)) {
                this.Gl = 1;
            }
            else if ("SERIF".equals(string3)) {
                this.Gl = 2;
            }
            else if ("MONOSPACED_SERIF".equals(string3)) {
                this.Gl = 3;
            }
            else if ("CASUAL".equals(string3)) {
                this.Gl = 4;
            }
            else if ("CURSIVE".equals(string3)) {
                this.Gl = 5;
            }
            else if ("SMALL_CAPITALS".equals(string3)) {
                this.Gl = 6;
            }
        }
        if (jsonObject.has("fontStyle")) {
            final String string4 = jsonObject.getString("fontStyle");
            if ("NORMAL".equals(string4)) {
                this.Gm = 0;
            }
            else if ("BOLD".equals(string4)) {
                this.Gm = 1;
            }
            else if ("ITALIC".equals(string4)) {
                this.Gm = 2;
            }
            else if ("BOLD_ITALIC".equals(string4)) {
                this.Gm = 3;
            }
        }
        this.Fl = jsonObject.optJSONObject("customData");
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o instanceof TextTrackStyle) {
                final TextTrackStyle textTrackStyle = (TextTrackStyle)o;
                int n;
                if (this.Fl == null) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                int n2;
                if (textTrackStyle.Fl == null) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                b3 = b2;
                if (n == n2) {
                    if (this.Fl != null && textTrackStyle.Fl != null) {
                        b3 = b2;
                        if (!jz.d(this.Fl, textTrackStyle.Fl)) {
                            return b3;
                        }
                    }
                    return this.Gd == textTrackStyle.Gd && this.Ge == textTrackStyle.Ge && this.xm == textTrackStyle.xm && this.Gf == textTrackStyle.Gf && this.Gg == textTrackStyle.Gg && this.Gh == textTrackStyle.Gh && this.Gj == textTrackStyle.Gj && ik.a(this.Gk, textTrackStyle.Gk) && this.Gl == textTrackStyle.Gl && this.Gm == textTrackStyle.Gm && b;
                }
            }
        }
        return b3;
    }
    
    public int getBackgroundColor() {
        return this.xm;
    }
    
    public JSONObject getCustomData() {
        return this.Fl;
    }
    
    public int getEdgeColor() {
        return this.Gg;
    }
    
    public int getEdgeType() {
        return this.Gf;
    }
    
    public String getFontFamily() {
        return this.Gk;
    }
    
    public int getFontGenericFamily() {
        return this.Gl;
    }
    
    public float getFontScale() {
        return this.Gd;
    }
    
    public int getFontStyle() {
        return this.Gm;
    }
    
    public int getForegroundColor() {
        return this.Ge;
    }
    
    public int getWindowColor() {
        return this.Gi;
    }
    
    public int getWindowCornerRadius() {
        return this.Gj;
    }
    
    public int getWindowType() {
        return this.Gh;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Gd, this.Ge, this.xm, this.Gf, this.Gg, this.Gh, this.Gi, this.Gj, this.Gk, this.Gl, this.Gm, this.Fl);
    }
    
    public void setBackgroundColor(final int xm) {
        this.xm = xm;
    }
    
    public void setCustomData(final JSONObject fl) {
        this.Fl = fl;
    }
    
    public void setEdgeColor(final int gg) {
        this.Gg = gg;
    }
    
    public void setEdgeType(final int gf) {
        if (gf < 0 || gf > 4) {
            throw new IllegalArgumentException("invalid edgeType");
        }
        this.Gf = gf;
    }
    
    public void setFontFamily(final String gk) {
        this.Gk = gk;
    }
    
    public void setFontGenericFamily(final int gl) {
        if (gl < 0 || gl > 6) {
            throw new IllegalArgumentException("invalid fontGenericFamily");
        }
        this.Gl = gl;
    }
    
    public void setFontScale(final float gd) {
        this.Gd = gd;
    }
    
    public void setFontStyle(final int gm) {
        if (gm < 0 || gm > 3) {
            throw new IllegalArgumentException("invalid fontStyle");
        }
        this.Gm = gm;
    }
    
    public void setForegroundColor(final int ge) {
        this.Ge = ge;
    }
    
    public void setWindowColor(final int gi) {
        this.Gi = gi;
    }
    
    public void setWindowCornerRadius(final int gj) {
        if (gj < 0) {
            throw new IllegalArgumentException("invalid windowCornerRadius");
        }
        this.Gj = gj;
    }
    
    public void setWindowType(final int gh) {
        if (gh < 0 || gh > 2) {
            throw new IllegalArgumentException("invalid windowType");
        }
        this.Gh = gh;
    }
}
