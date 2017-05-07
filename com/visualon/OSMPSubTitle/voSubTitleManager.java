// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPSubTitle;

import org.apache.http.util.ByteArrayBuffer;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import com.visualon.OSMPUtils.voLog;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.Paint$Align;
import android.graphics.Typeface;
import android.widget.TextView;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.widget.LinearLayout;
import android.util.DisplayMetrics;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import android.os.Message;
import android.app.Activity;
import android.os.Handler;
import java.util.ArrayList;
import android.os.Parcel;
import android.widget.RelativeLayout;

public class voSubTitleManager
{
    private static final int KEY_DISPLAY_EFFECTDIRECTION = 9;
    private static final int KEY_DISPLAY_EFFECTSPEED = 10;
    private static final int KEY_DISPLAY_EFFECTTYPE = 8;
    private static final int KEY_DISPLAY_INFO = 102;
    private static final int KEY_DURATION = 2;
    private static final int KEY_FONT_EFFECT = 108;
    private static final int KEY_FONT_EFFECT_EDGECOLOR = 21;
    private static final int KEY_FONT_EFFECT_EDGETYPE = 19;
    private static final int KEY_FONT_EFFECT_ITALIC = 17;
    private static final int KEY_FONT_EFFECT_OFFSET = 20;
    private static final int KEY_FONT_EFFECT_TEXTTAG = 16;
    private static final int KEY_FONT_EFFECT_UNDERLINE = 18;
    private static final int KEY_FONT_INFO = 107;
    private static final int KEY_FONT_INFO_COLOR = 15;
    private static final int KEY_FONT_INFO_SIZE = 13;
    private static final int KEY_FONT_INFO_STYLE = 14;
    private static final int KEY_IMAGE_DATA = 28;
    private static final int KEY_IMAGE_DISPLAY_DESCRIPTOR = 111;
    private static final int KEY_IMAGE_INFO = 105;
    private static final int KEY_IMAGE_SIZE = 27;
    private static final int KEY_RECT_BORDER_COLOR = 5;
    private static final int KEY_RECT_BORDER_TYPE = 4;
    private static final int KEY_RECT_FILL_COLOR = 6;
    private static final int KEY_RECT_INFO = 101;
    private static final int KEY_RECT_Z_ORDER = 7;
    private static final int KEY_START_TIME = 1;
    private static final int KEY_STRING_INFO = 106;
    private static final int KEY_STRUCT_RECT = 3;
    private static final int KEY_SUBTITLE_INFO = 100;
    private static final int KEY_TEXT_DISPLAY_DESCRIPTOR = 110;
    private static final int KEY_TEXT_DISPLAY_DESCRIPTOR_SCROLL_DIRECTION = 26;
    private static final int KEY_TEXT_DISPLAY_DESCRIPTOR_WRAP = 25;
    private static final int KEY_TEXT_INFO = 104;
    private static final int KEY_TEXT_ROW_DESCRIPTOR = 109;
    private static final int KEY_TEXT_ROW_DESCRIPTOR_HORI = 22;
    private static final int KEY_TEXT_ROW_DESCRIPTOR_PRINT_DIRECTION = 24;
    private static final int KEY_TEXT_ROW_DESCRIPTOR_VERT = 23;
    private static final int KEY_TEXT_ROW_INFO = 103;
    private static final int KEY_TEXT_SIZE = 11;
    private static final int KEY_TEXT_STRING = 12;
    private static final String TAG = "@@@ClosedCaptionManager.java";
    private float density;
    private int height;
    private RelativeLayout llWindow1;
    private Parcel mParcel;
    private boolean mShow;
    private voSubtitleInfo mSubtitleInfo;
    private ArrayList<voSubtitleInfo> mSubtitleInfoArray;
    private Handler m_handlerEvent;
    private Activity mainActivity;
    private RelativeLayout rlMain;
    protected voSubTitleFormatSettingImpl settings;
    private TextOutLinesView textViewOfRows;
    private voThumbnailPlayList thumbnailPlayList;
    private int width;
    private float xyRate;
    
    public voSubTitleManager() {
        this.mParcel = null;
        this.mSubtitleInfo = null;
        this.mSubtitleInfoArray = new ArrayList<voSubtitleInfo>();
        this.mainActivity = null;
        this.rlMain = null;
        this.llWindow1 = null;
        this.height = 0;
        this.width = 0;
        this.xyRate = 1.33f;
        this.density = 1.0f;
        this.mShow = true;
        this.settings = null;
        this.textViewOfRows = null;
        this.thumbnailPlayList = null;
        this.m_handlerEvent = new Handler() {
            public void handleMessage(final Message message) {
                voSubTitleManager.this.checkViewShowStatus(0);
            }
        };
    }
    
    private void createAndShowChildViews() {
        if (this.mainActivity != null && this.mSubtitleInfo != null && this.rlMain != null) {
            this.show(true);
            if (this.llWindow1 == null) {
                this.llWindow1 = new RelativeLayout((Context)this.mainActivity);
                this.rlMain.addView((View)this.llWindow1, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-2, -1));
                this.textViewOfRows = new TextOutLinesView((Context)this.mainActivity);
                this.llWindow1.addView((View)this.textViewOfRows, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
                this.setXYRate(this.xyRate);
            }
            else {
                this.removeImageViews();
            }
            if (this.llWindow1.getVisibility() != 0) {
                this.llWindow1.setVisibility(0);
            }
            if (this.mSubtitleInfo.subtitleEntry != null) {
                this.textViewOfRows.invalidate();
                for (int i = 0; i < this.mSubtitleInfo.subtitleEntry.size(); ++i) {
                    final voSubtitleInfoEntry voSubtitleInfoEntry = this.mSubtitleInfo.subtitleEntry.get(i);
                    final voSubtitleDisplayInfo access$600 = voSubtitleInfoEntry.subtitleDispInfo;
                    if (access$600.textRowInfo != null) {
                        for (int j = 0; j < access$600.textRowInfo.size(); ++j) {
                            this.startAnimation((voSubtitleTextRowInfo)access$600.textRowInfo.get(j), this.llWindow1);
                        }
                    }
                    if (access$600.imageInfo != null) {
                        for (int k = 0; k < access$600.imageInfo.size(); ++k) {
                            final voSubtitleImageInfo voSubtitleImageInfo = access$600.imageInfo.get(k);
                            if (voSubtitleImageInfo.imageData != null && voSubtitleImageInfo.imageInfoDescriptor != null) {
                                final LinearLayoutShowBorder linearLayoutShowBorder = new LinearLayoutShowBorder((Context)this.mainActivity);
                                linearLayoutShowBorder.setTag((Object)this.llWindow1);
                                final int realX = this.toRealX(voSubtitleImageInfo.imageInfoDescriptor.imageRectInfo.rectDraw.left);
                                final int realX2 = this.toRealX(voSubtitleImageInfo.imageInfoDescriptor.imageRectInfo.rectDraw.right);
                                final int realY = this.toRealY(voSubtitleImageInfo.imageInfoDescriptor.imageRectInfo.rectDraw.top);
                                this.toRealY(voSubtitleImageInfo.imageInfoDescriptor.imageRectInfo.rectDraw.bottom);
                                if (realX > 0 || realX2 > 0) {
                                    final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
                                    relativeLayout$LayoutParams.leftMargin = realX;
                                    relativeLayout$LayoutParams.topMargin = realY;
                                    this.llWindow1.addView((View)linearLayoutShowBorder, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
                                    linearLayoutShowBorder.setVerticalGravity(16);
                                    linearLayoutShowBorder.setHorizontalGravity(1);
                                }
                                else {
                                    final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -1);
                                    linearLayoutShowBorder.setVerticalGravity(16);
                                    linearLayoutShowBorder.setHorizontalGravity(1);
                                    this.llWindow1.addView((View)linearLayoutShowBorder, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
                                }
                                linearLayoutShowBorder.setBackgroundColor(voSubtitleImageInfo.imageInfoDescriptor.imageRectInfo.rectFillColor);
                                if (voSubtitleImageInfo.imageInfoDescriptor.imageRectInfo.rectBorderType > 0) {
                                    linearLayoutShowBorder.setBorder(2, voSubtitleImageInfo.imageInfoDescriptor.imageRectInfo.rectBorderColor);
                                }
                                final ImageView imageView = new ImageView((Context)this.mainActivity);
                                imageView.setImageBitmap(BitmapFactory.decodeByteArray(voSubtitleImageInfo.imageData.picData, 0, voSubtitleImageInfo.imageData.picData.length));
                                linearLayoutShowBorder.addView((View)imageView);
                                voSubtitleInfoEntry.mChildViewArr.add(linearLayoutShowBorder);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private float getFontSize(final voSubtitleTextInfoEntry voSubtitleTextInfoEntry) {
        float n = 0.75f;
        if (voSubtitleTextInfoEntry.stringInfo.fontInfo.fontSize == 1) {
            n = 0.7f;
        }
        if (voSubtitleTextInfoEntry.stringInfo.fontInfo.fontSize == 2) {
            n = 0.8f;
        }
        return n;
    }
    
    private boolean parseParcel() {
        this.mParcel.setDataPosition(0);
        if (this.mParcel.dataAvail() == 0) {
            return false;
        }
        final voSubtitleInfo voSubtitleInfo = new voSubtitleInfo();
        voSubtitleInfo.parse(this.mParcel);
        this.mSubtitleInfoArray.add(voSubtitleInfo);
        return true;
    }
    
    private void removeImageViews() {
        if (this.llWindow1 != null) {
            if (this.textViewOfRows != null) {
                this.textViewOfRows.invalidate();
            }
            while (true) {
                final View viewWithTag = this.llWindow1.findViewWithTag((Object)this.llWindow1);
                if (viewWithTag == null) {
                    break;
                }
                this.llWindow1.removeView(viewWithTag);
            }
        }
    }
    
    private String reverseString(final CharSequence charSequence) {
        final int length = charSequence.length();
        String string = "";
        for (int i = length - 1; i >= 0; --i) {
            string += charSequence.charAt(i);
        }
        return string;
    }
    
    private void startAnimation(final voSubtitleTextRowInfo voSubtitleTextRowInfo, final RelativeLayout relativeLayout) {
        if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffct.effectType > 0) {
            if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffct.effectType == 1) {
                final AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration((long)(voSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffct.effectSpeed * 1000));
                relativeLayout.startAnimation((Animation)alphaAnimation);
            }
            if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffct.effectType == 2) {
                float n;
                if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffct.effectDirection == 0) {
                    n = -this.width;
                }
                else {
                    n = 0.0f;
                }
                float n2 = n;
                if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffct.effectDirection == 1) {
                    n2 = this.width;
                }
                float n3;
                if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffct.effectDirection == 2) {
                    n3 = -this.height;
                }
                else {
                    n3 = 0.0f;
                }
                if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffct.effectDirection == 3) {
                    n3 = this.height;
                }
                final TranslateAnimation translateAnimation = new TranslateAnimation(n2, 0.0f, n3, 0.0f);
                translateAnimation.setDuration((long)(voSubtitleTextRowInfo.textRowDes.dataBox.rectDisplayEffct.effectSpeed * 1000));
                relativeLayout.startAnimation((Animation)translateAnimation);
            }
        }
    }
    
    private int toRealX(final int n) {
        return this.width * n / 100;
    }
    
    private int toRealY(final int n) {
        return this.height * n / 100;
    }
    
    public void checkViewShowStatus(final int n) {
        if (this.mShow && this.rlMain != null && this.mainActivity != null) {
            if (this.thumbnailPlayList != null) {
                final voThumbnailInfo access$1000 = this.thumbnailPlayList.getCurrentInfo(n);
                if (access$1000 != null && access$1000.pictureData != null) {
                    final voSubtitleInfo mSubtitleInfo = new voSubtitleInfo();
                    mSubtitleInfo.maxDuration = access$1000.duration;
                    mSubtitleInfo.timeStamp = access$1000.startTime;
                    final voSubtitleInfoEntry voSubtitleInfoEntry = new voSubtitleInfoEntry();
                    mSubtitleInfo.subtitleEntry.add(voSubtitleInfoEntry);
                    final voSubtitleImageInfo voSubtitleImageInfo = new voSubtitleImageInfo();
                    voSubtitleImageInfo.imageData = new voSubtitleImageInfoData();
                    voSubtitleImageInfo.imageData.picData = access$1000.pictureData;
                    voSubtitleImageInfo.imageData.size = access$1000.pictureData.length;
                    voSubtitleInfoEntry.subtitleDispInfo.imageInfo.add(voSubtitleImageInfo);
                    this.mSubtitleInfo = mSubtitleInfo;
                }
            }
            if (this.mSubtitleInfo != null) {
                boolean b;
                if ((this.mSubtitleInfo.maxDuration == -1 || this.mSubtitleInfo.timeStamp + this.mSubtitleInfo.maxDuration > n) && this.mSubtitleInfo.timeStamp < n) {
                    b = true;
                }
                else {
                    b = false;
                }
                if (!b && this.mSubtitleInfo.timeStamp >= 0) {
                    this.mSubtitleInfo = null;
                }
                if (b && this.mSubtitleInfo.maxDuration == -1 && n - this.mSubtitleInfo.timeStamp > 15000) {
                    this.mSubtitleInfoArray.clear();
                    this.mSubtitleInfo = null;
                    if (this.llWindow1 != null) {
                        this.removeImageViews();
                    }
                }
            }
            if (this.mSubtitleInfo == null && this.mSubtitleInfoArray.size() > 0) {
                for (int i = 0; i < this.mSubtitleInfoArray.size(); ++i) {
                    final voSubtitleInfo mSubtitleInfo2 = this.mSubtitleInfoArray.get(i);
                    if (mSubtitleInfo2.timeStamp <= n) {
                        for (int n2 = 0; mSubtitleInfo2.subtitleEntry != null && n2 < mSubtitleInfo2.subtitleEntry.size(); ++n2) {
                            final voSubtitleInfoEntry voSubtitleInfoEntry2 = mSubtitleInfo2.subtitleEntry.get(n2);
                            if ((voSubtitleInfoEntry2.duration == -1 || voSubtitleInfoEntry2.duration + mSubtitleInfo2.timeStamp > n) && !mSubtitleInfo2.equals(this.mSubtitleInfo)) {
                                this.mSubtitleInfo = mSubtitleInfo2;
                                this.createAndShowChildViews();
                                return;
                            }
                        }
                    }
                }
            }
            if (this.mSubtitleInfo != null && this.mSubtitleInfo.subtitleEntry == null) {
                return;
            }
        }
    }
    
    public void clearWidget() {
        if (this.rlMain != null && this.llWindow1 != null) {
            this.llWindow1.removeAllViews();
            this.rlMain.removeView((View)this.llWindow1);
            this.llWindow1 = null;
        }
    }
    
    public voSubTitleFormatSettingImpl getSettings() {
        if (this.settings == null) {
            this.settings = new voSubTitleFormatSettingImpl();
        }
        return this.settings;
    }
    
    public boolean isEmptySubtitleInfo() {
        voSubtitleInfo mSubtitleInfo = this.mSubtitleInfo;
        if (mSubtitleInfo == null && this.mSubtitleInfoArray.size() > 0) {
            mSubtitleInfo = this.mSubtitleInfoArray.get(this.mSubtitleInfoArray.size() - 1);
        }
        if (mSubtitleInfo != null && mSubtitleInfo.subtitleEntry.size() == 1 && ((voSubtitleInfoEntry)mSubtitleInfo.subtitleEntry.get(0)).subtitleDispInfo.getTextRowInfo().size() == 1) {
            final voSubtitleTextRowInfo voSubtitleTextRowInfo = mSubtitleInfo.subtitleEntry.get(0).subtitleDispInfo.getTextRowInfo().get(0);
            if (voSubtitleTextRowInfo.textInfoEntry.size() == 1 && ((voSubtitleTextInfoEntry)voSubtitleTextRowInfo.textInfoEntry.get(0)).stringText.length() == 0) {
                return true;
            }
        }
        return false;
    }
    
    public voSubtitleInfo parcelToSubtitleInfo(final Parcel parcel) {
        if (parcel != null) {
            parcel.setDataPosition(0);
            if (parcel.dataAvail() != 0) {
                final voSubtitleInfo voSubtitleInfo = new voSubtitleInfo();
                voSubtitleInfo.parse(parcel);
                return voSubtitleInfo;
            }
        }
        return null;
    }
    
    public void setActivity(final Activity mainActivity) {
        this.mainActivity = mainActivity;
    }
    
    public boolean setData(final Parcel mParcel, final boolean b) {
        if (mParcel != null) {
            if (this.mParcel != null) {
                this.mParcel.recycle();
            }
            mParcel.setDataPosition(0);
            this.mParcel = mParcel;
            if (b) {
                this.mSubtitleInfoArray.clear();
            }
            if (this.parseParcel()) {
                if (b) {
                    this.mSubtitleInfo = null;
                }
                return true;
            }
            if (b) {
                this.mSubtitleInfo = null;
            }
            if (this.llWindow1 != null) {
                this.llWindow1.setVisibility(8);
                return false;
            }
        }
        return false;
    }
    
    public void setMainLayout(final RelativeLayout rlMain) {
        this.rlMain = rlMain;
    }
    
    public void setThumbnailPlayListURL(final String s) {
        this.thumbnailPlayList = new voThumbnailPlayList();
        new Thread(new Runnable() {
            @Override
            public void run() {
                voSubTitleManager.this.thumbnailPlayList.setURL(s);
            }
        }).start();
    }
    
    public void setXYRate(final float xyRate) {
        this.xyRate = xyRate;
        if (this.mainActivity == null || this.llWindow1 == null) {
            return;
        }
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -1);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.width = displayMetrics.widthPixels;
        this.height = displayMetrics.heightPixels;
        if (displayMetrics.density > 0.0f) {
            this.density = displayMetrics.density;
        }
        if (this.width > (int)(this.height * this.xyRate)) {
            this.width = (int)(this.height * this.xyRate);
            layoutParams.leftMargin = (displayMetrics.widthPixels - this.width) / 2;
            layoutParams.width = this.width;
            layoutParams.height = this.height;
            layoutParams.topMargin = 0;
        }
        else {
            this.height = (int)(this.width / this.xyRate);
            layoutParams.topMargin = (displayMetrics.heightPixels - this.height) / 2;
            layoutParams.width = this.width;
            layoutParams.height = this.height;
            layoutParams.leftMargin = 0;
        }
        this.llWindow1.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    public void show(final boolean mShow) {
        if (!(this.mShow = mShow)) {
            if (this.llWindow1 != null) {
                if (this.llWindow1.getVisibility() == 0) {
                    this.llWindow1.setVisibility(4);
                }
                this.removeImageViews();
            }
            if (this.mSubtitleInfoArray != null) {
                this.mSubtitleInfoArray.clear();
            }
            if (this.mSubtitleInfo != null && this.mSubtitleInfo.subtitleEntry != null) {
                for (int i = 0; i < this.mSubtitleInfo.subtitleEntry.size(); ++i) {
                    ((voSubtitleInfoEntry)this.mSubtitleInfo.subtitleEntry.get(i)).mChildViewArr.clear();
                }
                this.mSubtitleInfo = null;
            }
        }
        else if (this.llWindow1 != null && this.llWindow1.getVisibility() != 0) {
            this.llWindow1.setVisibility(0);
        }
    }
    
    private class LinearLayoutShowBorder extends LinearLayout
    {
        int m_nColor;
        int m_nWidth;
        Paint paintBorder;
        
        public LinearLayoutShowBorder(final Context context) {
            super(context);
            this.m_nWidth = 0;
            this.m_nColor = 0;
            this.paintBorder = new Paint();
        }
        
        public LinearLayoutShowBorder(final Context context, final AttributeSet set) {
            super(context, set);
            this.m_nWidth = 0;
            this.m_nColor = 0;
            this.paintBorder = new Paint();
        }
        
        protected void onDraw(final Canvas canvas) {
            super.onDraw(canvas);
            if (this.m_nWidth == 0) {
                return;
            }
            canvas.drawLine(0.0f, 0.0f, (float)this.getWidth(), 0.0f, this.paintBorder);
            canvas.drawLine(0.0f, 0.0f, 0.0f, (float)this.getHeight(), this.paintBorder);
            canvas.drawLine((float)this.getWidth(), 0.0f, (float)this.getWidth(), (float)this.getHeight(), this.paintBorder);
            canvas.drawLine(0.0f, (float)this.getHeight(), (float)this.getWidth(), (float)this.getHeight(), this.paintBorder);
        }
        
        public void setBorder(final int nWidth, final int n) {
            this.m_nWidth = nWidth;
            this.m_nColor = n;
            this.paintBorder.setStrokeWidth((float)nWidth);
            this.paintBorder.setColor(n);
        }
    }
    
    private class TextOutLinesView extends TextView
    {
        private boolean enableDraw;
        
        public TextOutLinesView(final Context context) {
            super(context);
            this.enableDraw = true;
        }
        
        public TextOutLinesView(final Context context, final AttributeSet set) {
            super(context, set);
            this.enableDraw = true;
        }
        
        private int setTextViewTextInfo(final voSubtitleTextRowInfo voSubtitleTextRowInfo, final voSubtitleTextInfoEntry voSubtitleTextInfoEntry, final Paint paint, final Paint paint2) {
            final boolean b = false;
            paint.setColor(voSubtitleTextInfoEntry.stringInfo.fontInfo.fontColor);
            int access$8800;
            if (voSubtitleTextInfoEntry.stringInfo.fontInfo.fontSize == 103) {
                access$8800 = 0;
            }
            else {
                access$8800 = 1;
            }
            if ((voSubTitleManager.this.settings != null && voSubTitleManager.this.settings.fontBoldEnable) || voSubtitleTextInfoEntry.stringInfo.fontInfo.fontSize == 102) {
                access$8800 = voSubTitleManager.this.settings.fontBold;
            }
            int n = access$8800;
            if (voSubtitleTextInfoEntry.stringInfo.charEffect.Italic != 0) {
                n = (access$8800 | 0x2);
            }
            final Typeface create = Typeface.create(Typeface.MONOSPACE, n);
            Typeface typeface;
            if (voSubtitleTextInfoEntry.stringInfo.fontInfo.fontStyle == 2) {
                typeface = Typeface.create(Typeface.SERIF, n);
            }
            else {
                typeface = create;
                if (voSubtitleTextInfoEntry.stringInfo.fontInfo.fontStyle != 0) {
                    typeface = create;
                    if (voSubtitleTextInfoEntry.stringInfo.fontInfo.fontStyle != 1) {
                        typeface = Typeface.create(Typeface.DEFAULT, n);
                    }
                }
            }
            if (voSubTitleManager.this.settings != null && voSubTitleManager.this.settings.fontName != null && voSubTitleManager.this.settings.fontName.compareTo("") != 0) {
                typeface = Typeface.create(voSubTitleManager.this.settings.fontName, n);
            }
            paint.setFakeBoldText(n == 1 || n == 3);
            if (n == 2 || n == 3) {
                paint.setTextSkewX(-0.5f);
            }
            paint.setTypeface(typeface);
            if (paint2 != null) {
                paint2.setTypeface(typeface);
                paint2.setFakeBoldText(n == 1 || n == 3);
                if (n == 2 || n == 3) {
                    paint2.setTextSkewX(-0.5f);
                }
            }
            boolean b2 = b;
            if (voSubtitleTextRowInfo.textRowDes.printDirection > 1) {
                b2 = true;
            }
            final float access$8801 = voSubTitleManager.this.getFontSize(voSubtitleTextInfoEntry);
            float n3;
            if (b2) {
                final float n2 = access$8801 * (voSubTitleManager.this.toRealX(voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.right) - voSubTitleManager.this.toRealX(voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.left));
                paint.setTextSize(n2);
                n3 = n2;
                if (paint2 != null) {
                    paint2.setTextSize(n2);
                    n3 = n2;
                }
            }
            else {
                final float n4 = access$8801 * (voSubTitleManager.this.toRealY(voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.bottom) - voSubTitleManager.this.toRealY(voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.top));
                paint.setTextSize(n4);
                n3 = n4;
                if (paint2 != null) {
                    paint2.setTextSize(n4);
                    n3 = n4;
                }
            }
            final int n5 = (int)n3;
            if (voSubtitleTextInfoEntry.stringInfo.charEffect.Underline != 0) {
                paint.setUnderlineText(true);
            }
            if (voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType != 0) {
                float n6 = 1.5f;
                float n7 = 0.0f;
                Label_0558: {
                    if (voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType != 1 && voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType != 2) {
                        n7 = n6;
                        if (voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType != 3) {
                            break Label_0558;
                        }
                    }
                    if (n5 / 10.0f > 1.5f) {
                        n6 = n5 / 10.0f;
                    }
                    if (paint2 != null) {
                        paint2.setColor(voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeColor);
                    }
                    paint.setShadowLayer(n6, 0.0f, 0.0f, voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeColor);
                    n7 = n6;
                }
                if (voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType == 4) {
                    paint.setShadowLayer(n7, -2.0f, 2.0f, voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeColor);
                }
                if (voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType == 5) {
                    paint.setShadowLayer(n7, 2.0f, 2.0f, voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeColor);
                }
            }
            return n5;
        }
        
        public void enableDraw(final boolean enableDraw) {
            this.enableDraw = enableDraw;
            this.invalidate();
        }
        
        public void onDraw(final Canvas canvas) {
            if (!this.enableDraw) {
                canvas.save();
                canvas.drawARGB(0, 0, 0, 0);
                canvas.restore();
            }
            else if (voSubTitleManager.this.mainActivity != null && voSubTitleManager.this.mSubtitleInfo != null && voSubTitleManager.this.mSubtitleInfo.subtitleEntry != null) {
                final voSubtitleInfo access$10100 = voSubTitleManager.this.mSubtitleInfo;
                canvas.save();
                int n2;
                final int n = n2 = 0;
                Label_1031: {
                    if (voSubTitleManager.this.settings != null) {
                        n2 = n;
                        if (voSubTitleManager.this.settings.fontSizeEnable) {
                            n2 = n;
                            if (access$10100.subtitleEntry.size() > 0) {
                                final voSubtitleDisplayInfo access$10101 = ((voSubtitleInfoEntry)access$10100.subtitleEntry.get(0)).subtitleDispInfo;
                                int n3 = 0;
                                int n4 = 0;
                                int n5 = -1;
                                int n6 = -1;
                                int n7 = -1;
                                int n8 = -1;
                                int n9 = 0;
                                int n10 = 0;
                                int n11;
                                int n12;
                                int n13;
                                int n14;
                                int n15;
                                int n16;
                                int n17;
                                int n18;
                                for (int i = 0; i < access$10101.textRowInfo.size(); ++i, n10 = n11, n9 = n12, n8 = n13, n7 = n14, n6 = n15, n5 = n16, n4 = n17, n3 = n18) {
                                    final voSubtitleTextRowInfo voSubtitleTextRowInfo = access$10101.textRowInfo.get(i);
                                    n11 = n10;
                                    n12 = n9;
                                    n13 = n8;
                                    n14 = n7;
                                    n15 = n6;
                                    n16 = n5;
                                    n17 = n4;
                                    n18 = n3;
                                    if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.top != -1) {
                                        if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.left == -1) {
                                            n18 = n3;
                                            n17 = n4;
                                            n16 = n5;
                                            n15 = n6;
                                            n14 = n7;
                                            n13 = n8;
                                            n12 = n9;
                                            n11 = n10;
                                        }
                                        else {
                                            if (voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.top + voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.bottom < 100) {
                                                n3 = 1;
                                            }
                                            else {
                                                n4 = 1;
                                            }
                                            int top;
                                            if (n6 == -1 || (top = n6) > voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.top) {
                                                top = voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.top;
                                            }
                                            int left;
                                            if (n5 == -1 || (left = n5) > voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.left) {
                                                left = voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.left;
                                            }
                                            int bottom;
                                            if (n7 == -1 || (bottom = n7) < voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.bottom) {
                                                bottom = voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.bottom;
                                            }
                                            int right;
                                            if (n8 == -1 || (right = n8) < voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.right) {
                                                right = voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw.right;
                                            }
                                            n11 = n10;
                                            n12 = n9;
                                            n13 = right;
                                            n14 = bottom;
                                            n15 = top;
                                            n16 = left;
                                            n17 = n4;
                                            n18 = n3;
                                            if (voSubtitleTextRowInfo.textInfoEntry != null) {
                                                n11 = n10;
                                                n12 = n9;
                                                n13 = right;
                                                n14 = bottom;
                                                n15 = top;
                                                n16 = left;
                                                n17 = n4;
                                                n18 = n3;
                                                if (voSubtitleTextRowInfo.textInfoEntry.size() == 1) {
                                                    n11 = n10;
                                                    n12 = n9;
                                                    n13 = right;
                                                    n14 = bottom;
                                                    n15 = top;
                                                    n16 = left;
                                                    n17 = n4;
                                                    n18 = n3;
                                                    if (voSubtitleTextRowInfo.textRowDes.horizontalJustification != 0) {
                                                        if (voSubtitleTextRowInfo.textRowDes.horizontalJustification == 1) {
                                                            n12 = 1;
                                                            n11 = n10;
                                                            n13 = right;
                                                            n14 = bottom;
                                                            n15 = top;
                                                            n16 = left;
                                                            n17 = n4;
                                                            n18 = n3;
                                                        }
                                                        else {
                                                            n11 = n10;
                                                            n12 = n9;
                                                            n13 = right;
                                                            n14 = bottom;
                                                            n15 = top;
                                                            n16 = left;
                                                            n17 = n4;
                                                            n18 = n3;
                                                            if (voSubtitleTextRowInfo.textRowDes.horizontalJustification == 2) {
                                                                n11 = 1;
                                                                n12 = n9;
                                                                n13 = right;
                                                                n14 = bottom;
                                                                n15 = top;
                                                                n16 = left;
                                                                n17 = n4;
                                                                n18 = n3;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                int n19 = (int)(voSubTitleManager.this.width * n5 / 100.0f);
                                if (voSubTitleManager.this.settings.fontSizeMedium > 1.01) {
                                    int n20;
                                    if ((n20 = (int)(n5 * voSubTitleManager.this.settings.fontSizeMedium)) > 50) {
                                        n20 = 50;
                                    }
                                    n19 = (int)(n20 * voSubTitleManager.this.width / 100.0f);
                                }
                                int n21 = (int)(voSubTitleManager.this.height * n6 / 100.0f);
                                int n22;
                                if (n10 != 0) {
                                    n22 = voSubTitleManager.this.width / 2;
                                }
                                else if (n9 != 0) {
                                    n22 = (int)(voSubTitleManager.this.width * n8 / 100.0f);
                                }
                                else {
                                    n22 = n19;
                                }
                                if (n4 != 0 && n3 != 0) {
                                    n21 = voSubTitleManager.this.height / 2;
                                }
                                int n23;
                                if (n4 != 0 && n3 == 0) {
                                    n23 = (int)(voSubTitleManager.this.height * (n7 / 100.0f));
                                }
                                else {
                                    n23 = n21;
                                }
                                if (n4 == 0) {
                                    n2 = n;
                                    if (n3 == 0) {
                                        break Label_1031;
                                    }
                                }
                                int n24;
                                if (voSubTitleManager.this.settings.fontSizeMedium > 1.01) {
                                    n24 = 1;
                                }
                                else {
                                    n24 = 0;
                                }
                                canvas.scale(voSubTitleManager.this.settings.fontSizeMedium, voSubTitleManager.this.settings.fontSizeMedium, (float)n22, (float)n23);
                                n2 = n24;
                            }
                        }
                    }
                }
                canvas.drawARGB(0, 0, 0, 0);
                int j = 0;
            Label_2043_Outer:
                while (j < access$10100.subtitleEntry.size()) {
                    final voSubtitleInfoEntry voSubtitleInfoEntry = access$10100.subtitleEntry.get(j);
                    final voSubtitleDisplayInfo access$10102 = voSubtitleInfoEntry.subtitleDispInfo;
                    final Paint paint = new Paint();
                    paint.setTextAlign(Paint$Align.LEFT);
                    int color = voSubtitleInfoEntry.subtitleRectInfo.rectFillColor;
                    if (voSubTitleManager.this.settings != null) {
                        color = voSubTitleManager.this.settings.converColorOpacityRate(voSubTitleManager.this.settings.converColor(color, voSubTitleManager.this.settings.windowBackgroundColor, voSubTitleManager.this.settings.windowBackgroundColorEnable), voSubTitleManager.this.settings.windowBackgroundColorOpacityRate, voSubTitleManager.this.settings.windowBackgroundColorOpacityRateEnable);
                    }
                    paint.setColor(color);
                    while (true) {
                        Label_3370: {
                            if (paint.getAlpha() == 0) {
                                break Label_3370;
                            }
                            int n25 = -1;
                            int n36;
                            int n37;
                            int n38;
                            int n39;
                            int n40;
                            if (access$10102.textRowInfo != null) {
                                int n26 = 0;
                                int n27 = -1;
                                int n28 = -1;
                                int n29 = -1;
                                int n30;
                                int left2;
                                int n31;
                                int n32;
                                int n33;
                                for (int k = 0; k < access$10102.textRowInfo.size(); ++k, n25 = n33, n29 = n32, n28 = n31, n27 = left2, n26 = n30) {
                                    final voSubtitleTextRowInfo voSubtitleTextRowInfo2 = access$10102.textRowInfo.get(k);
                                    if (voSubtitleTextRowInfo2 == null) {
                                        n30 = n26;
                                        left2 = n27;
                                        n31 = n28;
                                        n32 = n29;
                                        n33 = n25;
                                    }
                                    else {
                                        n33 = n25;
                                        n32 = n29;
                                        n31 = n28;
                                        left2 = n27;
                                        n30 = n26;
                                        if (voSubtitleTextRowInfo2.textInfoEntry != null) {
                                            if (voSubtitleTextRowInfo2.textRowDes.dataBox.rectDraw.left < n27 || (left2 = n27) == -1) {
                                                left2 = voSubtitleTextRowInfo2.textRowDes.dataBox.rectDraw.left;
                                            }
                                            int top2;
                                            if (voSubtitleTextRowInfo2.textRowDes.dataBox.rectDraw.top < n29 || (top2 = n29) == -1) {
                                                top2 = voSubtitleTextRowInfo2.textRowDes.dataBox.rectDraw.top;
                                            }
                                            int right2;
                                            if (voSubtitleTextRowInfo2.textRowDes.dataBox.rectDraw.right > n28 || (right2 = n28) == -1) {
                                                right2 = voSubtitleTextRowInfo2.textRowDes.dataBox.rectDraw.right;
                                            }
                                            int bottom2;
                                            if (voSubtitleTextRowInfo2.textRowDes.dataBox.rectDraw.bottom > n25 || n25 == -1) {
                                                bottom2 = voSubtitleTextRowInfo2.textRowDes.dataBox.rectDraw.bottom;
                                            }
                                            else {
                                                bottom2 = n25;
                                            }
                                            int n34;
                                            for (int l = 0; l < voSubtitleTextRowInfo2.textInfoEntry.size(); ++l, n26 = n34) {
                                                n34 = n26;
                                                if (voSubtitleTextRowInfo2.textInfoEntry.get(l) != null) {
                                                    n34 = n26;
                                                    if (((voSubtitleTextInfoEntry)voSubtitleTextRowInfo2.textInfoEntry.get(l)).stringText != null) {
                                                        n34 = n26;
                                                        if (((voSubtitleTextInfoEntry)voSubtitleTextRowInfo2.textInfoEntry.get(l)).stringText.length() > 0) {
                                                            n34 = 1;
                                                        }
                                                    }
                                                }
                                            }
                                            n33 = bottom2;
                                            n32 = top2;
                                            n31 = right2;
                                            n30 = n26;
                                        }
                                    }
                                }
                                final int n35 = n25;
                                n36 = n26;
                                n37 = n27;
                                n38 = n28;
                                n39 = n29;
                                n40 = n35;
                            }
                            else {
                                n40 = -1;
                                n36 = 0;
                                n39 = -1;
                                n38 = -1;
                                n37 = -1;
                            }
                            if (n36 == 0 || (voSubtitleInfoEntry.subtitleRectInfo.rectDraw.left == -1 && voSubtitleInfoEntry.subtitleRectInfo.rectDraw.right == -1 && voSubtitleInfoEntry.subtitleRectInfo.rectDraw.top == -1 && voSubtitleInfoEntry.subtitleRectInfo.rectDraw.bottom == -1)) {
                                break Label_3370;
                            }
                            int access$10103;
                            if ((access$10103 = n37) != -1) {
                                access$10103 = voSubTitleManager.this.toRealX(n37);
                            }
                            int access$10104;
                            if ((access$10104 = n38) != -1) {
                                access$10104 = voSubTitleManager.this.toRealX(n38);
                            }
                            int access$10105;
                            if ((access$10105 = n39) != -1) {
                                access$10105 = voSubTitleManager.this.toRealY(n39);
                            }
                            int access$10106;
                            if ((access$10106 = n40) != -1) {
                                access$10106 = voSubTitleManager.this.toRealY(n40);
                            }
                            final Rect rect = new Rect();
                            rect.left = voSubTitleManager.this.toRealX(voSubtitleInfoEntry.subtitleRectInfo.rectDraw.left) - 2;
                            if (rect.left < 0) {
                                rect.left = 0;
                            }
                            if (rect.left > access$10103 - 5 && access$10103 != -1) {
                                rect.left = access$10103 - 7;
                            }
                            rect.right = voSubTitleManager.this.toRealX(voSubtitleInfoEntry.subtitleRectInfo.rectDraw.right) + 2;
                            if (rect.right < access$10104 && access$10104 != -1) {
                                rect.right = access$10104 + 2;
                            }
                            rect.top = voSubTitleManager.this.toRealY(voSubtitleInfoEntry.subtitleRectInfo.rectDraw.top) - 2;
                            if (rect.top < 0) {
                                rect.top = 0;
                            }
                            if (rect.top > access$10105 && access$10105 != -1) {
                                rect.top = access$10105 - 2;
                            }
                            rect.bottom = voSubTitleManager.this.toRealY(voSubtitleInfoEntry.subtitleRectInfo.rectDraw.bottom) + 2;
                            if (rect.bottom < access$10106 && access$10106 != -1) {
                                rect.bottom = access$10106 + 2;
                            }
                            canvas.drawRect(rect, paint);
                            final int n41 = 1;
                            if (access$10102.textRowInfo != null) {
                                for (int n42 = 0; n42 < access$10102.textRowInfo.size(); ++n42) {
                                    final voSubtitleTextRowInfo voSubtitleTextRowInfo3 = access$10102.textRowInfo.get(n42);
                                    if (voSubtitleTextRowInfo3 != null && voSubtitleTextRowInfo3.textInfoEntry != null) {
                                        int access$10107 = voSubTitleManager.this.toRealX(voSubtitleTextRowInfo3.textRowDes.dataBox.rectDraw.left);
                                        final int access$10108 = voSubTitleManager.this.toRealX(voSubtitleTextRowInfo3.textRowDes.dataBox.rectDraw.right);
                                        int access$10109 = voSubTitleManager.this.toRealY(voSubtitleTextRowInfo3.textRowDes.dataBox.rectDraw.top);
                                        final int access$10110 = voSubTitleManager.this.toRealY(voSubtitleTextRowInfo3.textRowDes.dataBox.rectDraw.bottom);
                                        int n50;
                                        int n51;
                                        for (int n43 = 0; n43 < voSubtitleTextRowInfo3.textInfoEntry.size(); ++n43, access$10109 = n51, access$10107 = n50) {
                                            Paint paint2 = new Paint();
                                            paint2.setTextAlign(Paint$Align.LEFT);
                                            paint2.setColor(voSubtitleTextRowInfo3.textRowDes.dataBox.rectFillColor);
                                            if (paint2.getAlpha() == 0) {
                                                paint2 = null;
                                            }
                                            final Paint paint3 = new Paint();
                                            paint3.setAntiAlias(true);
                                            final voSubtitleTextInfoEntry voSubtitleTextInfoEntry = voSubtitleTextRowInfo3.textInfoEntry.get(n43);
                                            Paint paint4;
                                            if (voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType < 1 || voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType > 3) {
                                                paint4 = null;
                                            }
                                            else {
                                                paint4 = new Paint();
                                                paint4.setAntiAlias(true);
                                                paint4.setTextAlign(Paint$Align.LEFT);
                                            }
                                            final String access$10111 = voSubtitleTextInfoEntry.stringText;
                                            int n44;
                                            if (voSubtitleTextRowInfo3.textRowDes.printDirection > 1) {
                                                n44 = 1;
                                            }
                                            else {
                                                n44 = 0;
                                            }
                                            String access$10112 = null;
                                            Label_2379: {
                                                if (voSubtitleTextRowInfo3.textRowDes.printDirection != 1) {
                                                    access$10112 = access$10111;
                                                    if (voSubtitleTextRowInfo3.textRowDes.printDirection != 3) {
                                                        break Label_2379;
                                                    }
                                                }
                                                access$10112 = voSubTitleManager.this.reverseString(access$10111);
                                            }
                                            final int setTextViewTextInfo = this.setTextViewTextInfo(voSubtitleTextRowInfo3, voSubtitleTextInfoEntry, paint3, paint4);
                                            if (paint4 != null) {
                                                paint4.setTextAlign(Paint$Align.LEFT);
                                                paint4.setStyle(Paint$Style.STROKE);
                                                paint4.setStrokeWidth(4.0f);
                                            }
                                            paint3.setTextAlign(Paint$Align.LEFT);
                                            int n45 = (int)paint3.descent() + 1;
                                            final Rect rect2 = new Rect();
                                            if (n44 != 0) {
                                                final int length = access$10112.length();
                                                if (setTextViewTextInfo < access$10108 - access$10107 && access$10108 - access$10107 - setTextViewTextInfo > 0) {}
                                                rect2.left = access$10107 - 3;
                                                int n46;
                                                if (voSubtitleTextInfoEntry.stringInfo.charEffect.Italic != 0) {
                                                    n46 = setTextViewTextInfo / 2 + 6;
                                                }
                                                else {
                                                    n46 = 6;
                                                }
                                                final int n47 = access$10108 - access$10107;
                                                for (int n48 = 0; n48 < length; ++n48) {
                                                    final String string = "" + access$10112.charAt(n48);
                                                    if (paint2 != null) {
                                                        rect2.top = n47 * n48 + access$10109;
                                                        rect2.bottom = (n48 + 1) * n47 + access$10109;
                                                        rect2.right = rect2.left + (int)paint3.measureText(string) + n46;
                                                        canvas.drawRect(rect2, paint2);
                                                    }
                                                    if (paint4 != null) {
                                                        canvas.drawText(string, (float)(access$10107 + 2), (float)((n48 + 1) * n47 + access$10109 - n47 / 4), paint4);
                                                    }
                                                    canvas.drawText(string, (float)(access$10107 + 2), (float)((n48 + 1) * n47 + access$10109 - n47 / 4), paint3);
                                                }
                                                final int n49 = access$10109 + n47 * length;
                                                n50 = access$10107;
                                                n51 = n49;
                                            }
                                            else {
                                                if (setTextViewTextInfo < access$10110 - access$10109 && access$10110 - access$10109 - setTextViewTextInfo > 0) {
                                                    n45 += (access$10110 - access$10109 - setTextViewTextInfo) / 3;
                                                }
                                                final String[] split = access$10112.split("\n");
                                                final ArrayList<String> list = new ArrayList<String>();
                                                boolean b = false;
                                                for (int n52 = 0; n52 < split.length; ++n52) {
                                                    if (split[n52].length() > 32) {
                                                        b = true;
                                                        list.add(split[n52]);
                                                    }
                                                    else {
                                                        list.add(split[n52]);
                                                    }
                                                }
                                                int n53 = access$10107;
                                                if (b) {
                                                    n53 = access$10107;
                                                    if (n41 == 0 && (n53 = access$10107) > 6) {
                                                        n53 = access$10107 / 2;
                                                    }
                                                }
                                                int n54 = 0;
                                                final int n55 = n53;
                                                int n56 = n53;
                                                int n57 = n55;
                                                while (n54 < list.size()) {
                                                    final String s = list.get(n54);
                                                    int n58;
                                                    if (s.length() <= 0) {
                                                        n58 = n56;
                                                    }
                                                    else {
                                                        final int n59 = (int)paint3.measureText(s);
                                                        int left3 = n57;
                                                        if (voSubtitleTextRowInfo3.textInfoEntry.size() == 1) {
                                                            left3 = n57;
                                                            if (voSubtitleTextRowInfo3.textRowDes.horizontalJustification == 1) {
                                                                left3 = access$10108 - n59 - 5;
                                                            }
                                                            if (voSubtitleTextRowInfo3.textRowDes.horizontalJustification == 2) {
                                                                left3 = (access$10108 - n56 - n59) / 2 + n56 - 5;
                                                            }
                                                        }
                                                        rect2.left = left3;
                                                        int n60 = 5;
                                                        if (n43 == 0) {
                                                            rect2.left -= 5;
                                                            n60 = 10;
                                                        }
                                                        int n61 = n60;
                                                        if (voSubtitleTextInfoEntry.stringInfo.charEffect.Italic != 0) {
                                                            n61 = n60 + setTextViewTextInfo / 2;
                                                        }
                                                        if (paint2 != null) {
                                                            rect2.top = (access$10110 - access$10109) * n54 + access$10109;
                                                            rect2.bottom = (access$10110 - access$10109) * (n54 + 1) + access$10109;
                                                            rect2.right = rect2.left + n59 + n61;
                                                            if (n2 != 0) {
                                                                rect2.right += n61;
                                                            }
                                                            canvas.drawRect(rect2, paint2);
                                                        }
                                                        if (paint4 != null) {
                                                            int n62 = 0;
                                                            if (voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType == 1) {
                                                                n62 = 1;
                                                            }
                                                            if (voSubtitleTextInfoEntry.stringInfo.charEffect.EdgeType == 2) {
                                                                n62 = -1;
                                                            }
                                                            canvas.drawText(s, (float)left3, (float)(n62 + ((access$10110 - access$10109) * (n54 + 1) + access$10109 - n45)), paint4);
                                                        }
                                                        canvas.drawText(s, (float)left3, (float)((access$10110 - access$10109) * (n54 + 1) + access$10109 - n45), paint3);
                                                        n57 = left3;
                                                        n58 = n56;
                                                        if (voSubtitleTextRowInfo3.textInfoEntry.size() > 1) {
                                                            n57 = left3;
                                                            n58 = n56;
                                                            if (split.length == 1) {
                                                                n58 = n56 + n59;
                                                                n57 = left3;
                                                            }
                                                        }
                                                    }
                                                    ++n54;
                                                    n56 = n58;
                                                }
                                                n51 = access$10109;
                                                n50 = n56;
                                            }
                                        }
                                    }
                                }
                            }
                            ++j;
                            continue Label_2043_Outer;
                        }
                        final int n41 = 0;
                        continue;
                    }
                }
                canvas.restore();
            }
        }
    }
    
    private class voRect
    {
        private int rectBorderColor;
        private int rectBorderType;
        voSubtitleDisPlayEffect rectDisplayEffct;
        private Rect rectDraw;
        private int rectFillColor;
        private int rectZOrder;
        
        private voRect() {
            this.rectDraw = new Rect();
            this.rectBorderType = 0;
            this.rectBorderColor = 0;
            this.rectFillColor = 0;
            this.rectZOrder = 0;
            this.rectDisplayEffct = new voSubtitleDisPlayEffect();
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 101) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
            }
            else if (parcel.readInt() == 3) {
                this.rectDraw.top = parcel.readInt();
                this.rectDraw.left = parcel.readInt();
                this.rectDraw.bottom = parcel.readInt();
                this.rectDraw.right = parcel.readInt();
                parcel.readInt();
                this.rectBorderType = parcel.readInt();
                parcel.readInt();
                this.rectBorderColor = parcel.readInt();
                parcel.readInt();
                this.rectFillColor = parcel.readInt();
                parcel.readInt();
                this.rectZOrder = parcel.readInt();
                this.rectDisplayEffct.parse(parcel);
                if (this.rectDraw.top > 95) {
                    this.rectDraw.top = 95;
                }
                if (this.rectDraw.left > 95) {
                    this.rectDraw.left = 95;
                }
                if (this.rectDraw.bottom > 99) {
                    this.rectDraw.bottom = 99;
                }
                if (this.rectDraw.right > 99) {
                    this.rectDraw.right = 99;
                }
                return true;
            }
            return false;
        }
        
        private void setRectFillColor(final int rectFillColor) {
            this.rectFillColor = rectFillColor;
        }
        
        public int getRectBorderColor() {
            return this.rectBorderColor;
        }
        
        public int getRectBorderType() {
            return this.rectBorderType;
        }
        
        public voSubtitleDisPlayEffect getRectDisplayEffect() {
            return this.rectDisplayEffct;
        }
        
        public Rect getRectDraw() {
            return this.rectDraw;
        }
        
        public int getRectFillColor() {
            return this.rectFillColor;
        }
        
        public int getRectZOrder() {
            return this.rectZOrder;
        }
    }
    
    public class voSubTitleFormatSettingImpl implements voSubTitleFormatSetting
    {
        private int backgroundColor;
        private boolean backgroundColorEnable;
        private int backgroundColorOpacityRate;
        private boolean backgroundColorOpacityRateEnable;
        private int edgeColor;
        private boolean edgeColorEnable;
        private int edgeColorOpacityRate;
        private boolean edgeColorOpacityRateEnable;
        private int edgeType;
        private boolean edgeTypeEnable;
        private int fontBold;
        private boolean fontBoldEnable;
        private int fontColor;
        private boolean fontColorEnable;
        private int fontColorOpacityRate;
        private boolean fontColorOpacityRateEnable;
        private int fontItalic;
        private boolean fontItalicEnable;
        private String fontName;
        private boolean fontSizeEnable;
        private float fontSizeMedium;
        private int fontUnderline;
        private boolean fontUnderlineEnable;
        private ArrayList<Rect> rectList;
        private int windowBackgroundColor;
        private boolean windowBackgroundColorEnable;
        private int windowBackgroundColorOpacityRate;
        private boolean windowBackgroundColorOpacityRateEnable;
        
        public voSubTitleFormatSettingImpl() {
            this.rectList = null;
            this.edgeType = 0;
            this.edgeColor = 0;
            this.fontColor = 0;
            this.windowBackgroundColor = 0;
            this.backgroundColor = 0;
            this.edgeColorOpacityRate = 0;
            this.fontColorOpacityRate = 0;
            this.windowBackgroundColorOpacityRate = 0;
            this.backgroundColorOpacityRate = 0;
            this.fontSizeMedium = 0.75f;
            this.fontItalic = 0;
            this.fontUnderline = 0;
            this.fontBold = 1;
            this.edgeTypeEnable = false;
            this.fontColorEnable = false;
            this.edgeColorEnable = false;
            this.backgroundColorEnable = false;
            this.windowBackgroundColorEnable = false;
            this.fontColorOpacityRateEnable = false;
            this.edgeColorOpacityRateEnable = false;
            this.backgroundColorOpacityRateEnable = false;
            this.windowBackgroundColorOpacityRateEnable = false;
            this.fontSizeEnable = false;
            this.fontItalicEnable = false;
            this.fontUnderlineEnable = false;
            this.fontBoldEnable = false;
            this.fontName = "";
        }
        
        private int checkAlpha(int n) {
            final int n2 = 100;
            if (n < 0) {
                n = 0;
            }
            if (n > 100) {
                n = n2;
            }
            n *= (int)2.55;
            voLog.v("CloseCaption", "alpha = %d ", n);
            return n;
        }
        
        private int converColor(final int n, final int n2, final boolean b) {
            int n3 = n;
            if (b) {
                n3 = ((0xFFFFFF & n2) | (0xFF000000 & n));
            }
            return n3;
        }
        
        private int converColorOpacityRate(final int n, final int n2, final boolean b) {
            int n3 = n;
            if (b) {
                n3 = ((n2 & 0xFF) * 16777216 | (0xFFFFFF & n));
            }
            return n3;
        }
        
        @Override
        public void reset() {
            this.edgeTypeEnable = false;
            this.fontColorEnable = false;
            this.edgeColorEnable = false;
            this.backgroundColorEnable = false;
            this.windowBackgroundColorEnable = false;
            this.fontColorOpacityRateEnable = false;
            this.edgeColorOpacityRateEnable = false;
            this.backgroundColorOpacityRateEnable = false;
            this.windowBackgroundColorOpacityRateEnable = false;
            this.fontSizeEnable = false;
            this.fontItalicEnable = false;
            this.fontUnderlineEnable = false;
            this.fontBoldEnable = false;
            this.fontName = "";
        }
        
        @Override
        public void setBackgroundColor(final int backgroundColor) {
            this.backgroundColor = backgroundColor;
            this.backgroundColorEnable = true;
        }
        
        @Override
        public void setBackgroundOpacity(final int n) {
            this.backgroundColorOpacityRateEnable = true;
            this.backgroundColorOpacityRate = this.checkAlpha(n);
        }
        
        @Override
        public void setEdgeColor(final int edgeColor) {
            this.edgeColor = edgeColor;
            this.edgeColorEnable = true;
        }
        
        @Override
        public void setEdgeOpacity(final int n) {
            this.edgeColorOpacityRateEnable = true;
            this.edgeColorOpacityRate = this.checkAlpha(n);
        }
        
        @Override
        public void setEdgeType(final int edgeType) {
            this.edgeType = edgeType;
            this.edgeTypeEnable = true;
        }
        
        @Override
        public void setFontBold(final boolean b) {
            int fontBold;
            if (b) {
                fontBold = 1;
            }
            else {
                fontBold = 0;
            }
            this.fontBold = fontBold;
            this.fontBoldEnable = true;
        }
        
        @Override
        public void setFontColor(final int fontColor) {
            this.fontColor = fontColor;
            this.fontColorEnable = true;
        }
        
        @Override
        public void setFontItalic(final boolean b) {
            int fontItalic;
            if (b) {
                fontItalic = 1;
            }
            else {
                fontItalic = 0;
            }
            this.fontItalic = fontItalic;
            this.fontItalicEnable = true;
        }
        
        @Override
        public void setFontName(final String fontName) {
            this.fontName = fontName;
        }
        
        @Override
        public void setFontOpacity(final int n) {
            this.fontColorOpacityRateEnable = true;
            this.fontColorOpacityRate = this.checkAlpha(n);
        }
        
        public void setFontSize(final float n) {
            this.setFontSize(n, n, n);
        }
        
        public void setFontSize(final float n, final float fontSizeMedium, final float n2) {
            this.fontSizeMedium = fontSizeMedium;
            this.fontSizeEnable = true;
        }
        
        @Override
        public void setFontSizeScale(int n) {
            final int n2 = 200;
            final int n3 = 50;
            if (n > 200) {
                n = n2;
            }
            if (n < 50) {
                n = n3;
            }
            this.setFontSize(n / 100.0f);
        }
        
        @Override
        public void setFontStyle(final int n) {
            if (n == 1) {
                this.fontName = "Courier";
            }
            if (n == 2) {
                this.fontName = "Times New Roman";
            }
            if (n == 3) {
                this.fontName = "Helvetica";
            }
            if (n == 4) {
                this.fontName = "Arial";
            }
            if (n == 5) {
                this.fontName = "Dom";
            }
            if (n == 6) {
                this.fontName = "Coronet";
            }
            if (n == 7) {
                this.fontName = "Gothic";
            }
        }
        
        @Override
        public void setFontUnderline(final boolean b) {
            int fontUnderline;
            if (b) {
                fontUnderline = 1;
            }
            else {
                fontUnderline = 0;
            }
            this.fontUnderline = fontUnderline;
            this.fontUnderlineEnable = true;
        }
        
        @Override
        public void setWindowColor(final int windowBackgroundColor) {
            this.windowBackgroundColor = windowBackgroundColor;
            this.windowBackgroundColorEnable = true;
        }
        
        @Override
        public void setWindowOpacity(final int n) {
            this.windowBackgroundColorOpacityRateEnable = true;
            this.windowBackgroundColorOpacityRate = this.checkAlpha(n);
        }
    }
    
    public class voSubtitleDisPlayEffect
    {
        private int effectDirection;
        private int effectSpeed;
        private int effectType;
        
        private voSubtitleDisPlayEffect() {
            this.effectType = 0;
            this.effectDirection = 0;
            this.effectSpeed = 0;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 8) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
                return false;
            }
            this.effectType = parcel.readInt();
            parcel.readInt();
            this.effectDirection = parcel.readInt();
            parcel.readInt();
            this.effectSpeed = parcel.readInt();
            return true;
        }
        
        public int getEffectDirection() {
            return this.effectDirection;
        }
        
        public int getEffectSpeed() {
            return this.effectSpeed;
        }
        
        public int getEffectType() {
            return this.effectType;
        }
    }
    
    public class voSubtitleDisplayInfo
    {
        private voSubtitleTextDisplayDescriptor dispDescriptor;
        private ArrayList<voSubtitleImageInfo> imageInfo;
        private ArrayList<voSubtitleTextRowInfo> textRowInfo;
        
        private voSubtitleDisplayInfo() {
            this.textRowInfo = new ArrayList<voSubtitleTextRowInfo>();
            this.dispDescriptor = new voSubtitleTextDisplayDescriptor();
            this.imageInfo = new ArrayList<voSubtitleImageInfo>();
        }
        
        private ArrayList<voSubtitleImageInfo> getImageInfo() {
            return this.imageInfo;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 102) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
                return false;
            }
            if (parcel.readInt() != 103) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
            }
            else {
                this.textRowInfo = new ArrayList<voSubtitleTextRowInfo>();
                while (true) {
                    final voSubtitleTextRowInfo voSubtitleTextRowInfo = new voSubtitleTextRowInfo();
                    if (!voSubtitleTextRowInfo.parse(parcel)) {
                        break;
                    }
                    if (voSubTitleManager.this.settings != null && voSubTitleManager.this.settings.rectList != null && voSubTitleManager.this.settings.rectList.size() > this.textRowInfo.size()) {
                        final Rect rect = voSubTitleManager.this.settings.rectList.get(this.textRowInfo.size());
                        if (voSubtitleTextRowInfo.textRowDes != null && voSubtitleTextRowInfo.textRowDes.dataBox != null) {
                            voSubtitleTextRowInfo.textRowDes.dataBox.rectDraw = rect;
                        }
                    }
                    this.textRowInfo.add(voSubtitleTextRowInfo);
                }
            }
            this.dispDescriptor = new voSubtitleTextDisplayDescriptor();
            if (!this.dispDescriptor.parse(parcel)) {
                return false;
            }
            final int int1 = parcel.readInt();
            parcel.setDataPosition(parcel.dataPosition() - 4);
            if (int1 != 105) {
                return true;
            }
            this.imageInfo = new ArrayList<voSubtitleImageInfo>();
            while (parcel.dataAvail() > 0) {
                final voSubtitleImageInfo voSubtitleImageInfo = new voSubtitleImageInfo();
                if (!voSubtitleImageInfo.parse(parcel)) {
                    break;
                }
                this.imageInfo.add(voSubtitleImageInfo);
            }
            return true;
        }
        
        public voSubtitleTextDisplayDescriptor getDispDescriptor() {
            return this.dispDescriptor;
        }
        
        public ArrayList<voSubtitleTextRowInfo> getTextRowInfo() {
            return this.textRowInfo;
        }
    }
    
    public class voSubtitleFontEffect
    {
        private int EdgeColor;
        private int EdgeType;
        private int Italic;
        private int Offset;
        private int TextTag;
        private int Underline;
        
        private voSubtitleFontEffect() {
            this.TextTag = 0;
            this.Italic = 0;
            this.Underline = 0;
            this.EdgeType = 0;
            this.Offset = 0;
            this.EdgeColor = 0;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 108) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
                return false;
            }
            parcel.readInt();
            this.TextTag = parcel.readInt();
            parcel.readInt();
            this.Italic = parcel.readInt();
            parcel.readInt();
            this.Underline = parcel.readInt();
            parcel.readInt();
            this.EdgeType = parcel.readInt();
            parcel.readInt();
            this.Offset = parcel.readInt();
            parcel.readInt();
            this.EdgeColor = parcel.readInt();
            if (voSubTitleManager.this.settings != null) {
                if (voSubTitleManager.this.settings.fontUnderlineEnable) {
                    this.Underline = voSubTitleManager.this.settings.fontUnderline;
                }
                if (voSubTitleManager.this.settings.fontItalicEnable) {
                    this.Italic = voSubTitleManager.this.settings.fontItalic;
                }
                if (voSubTitleManager.this.settings.edgeTypeEnable) {
                    this.EdgeType = voSubTitleManager.this.settings.edgeType;
                }
                if (voSubTitleManager.this.settings.edgeColorEnable) {
                    this.EdgeColor = voSubTitleManager.this.settings.edgeColor;
                }
                this.EdgeColor = voSubTitleManager.this.settings.converColor(this.EdgeColor, voSubTitleManager.this.settings.edgeColor, voSubTitleManager.this.settings.edgeColorEnable);
                this.EdgeColor = voSubTitleManager.this.settings.converColorOpacityRate(this.EdgeColor, voSubTitleManager.this.settings.edgeColorOpacityRate, voSubTitleManager.this.settings.edgeColorOpacityRateEnable);
            }
            return true;
        }
        
        public int getEdgeColor() {
            return this.EdgeColor;
        }
        
        public int getEdgeType() {
            return this.EdgeType;
        }
        
        public int getItalic() {
            return this.Italic;
        }
        
        public int getOffset() {
            return this.Offset;
        }
        
        public int getTextTag() {
            return this.TextTag;
        }
        
        public int getUnderline() {
            return this.Underline;
        }
    }
    
    public class voSubtitleFontInfo
    {
        private int fontColor;
        private int fontSize;
        private int fontStyle;
        
        private voSubtitleFontInfo() {
            this.fontSize = 0;
            this.fontStyle = 0;
            this.fontColor = 0;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 107) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
            }
            else if (parcel.readInt() == 13) {
                this.fontSize = parcel.readInt();
                parcel.readInt();
                this.fontStyle = parcel.readInt();
                parcel.readInt();
                this.fontColor = parcel.readInt();
                if (voSubTitleManager.this.settings != null) {
                    this.fontColor = voSubTitleManager.this.settings.converColor(this.fontColor, voSubTitleManager.this.settings.fontColor, voSubTitleManager.this.settings.fontColorEnable);
                    this.fontColor = voSubTitleManager.this.settings.converColorOpacityRate(this.fontColor, voSubTitleManager.this.settings.fontColorOpacityRate, voSubTitleManager.this.settings.fontColorOpacityRateEnable);
                }
                return true;
            }
            return false;
        }
        
        public int getFontColor() {
            return this.fontColor;
        }
        
        public int getFontSize() {
            return this.fontSize;
        }
        
        public int getFontStyle() {
            return this.fontStyle;
        }
    }
    
    private class voSubtitleImageInfo
    {
        private voSubtitleImageInfoData imageData;
        private voSubtitleImageInfoDescriptor imageInfoDescriptor;
        
        private voSubtitleImageInfo() {
            this.imageData = null;
            this.imageInfoDescriptor = null;
        }
        
        private voSubtitleImageInfoData getImageData() {
            return this.imageData;
        }
        
        private voSubtitleImageInfoDescriptor getImageInfoDescriptor() {
            return this.imageInfoDescriptor;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 105) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
                return false;
            }
            (this.imageData = new voSubtitleImageInfoData()).parse(parcel);
            (this.imageInfoDescriptor = new voSubtitleImageInfoDescriptor()).parse(parcel);
            return true;
        }
    }
    
    private class voSubtitleImageInfoData
    {
        private byte[] picData;
        private int size;
        
        private voSubtitleImageInfoData() {
            this.size = 0;
            this.picData = null;
        }
        
        private byte[] getPicData() {
            return this.picData;
        }
        
        private int getSize() {
            return this.size;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 27) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
                return false;
            }
            this.size = parcel.readInt();
            parcel.readInt();
            parcel.readByteArray(this.picData = new byte[this.size]);
            return true;
        }
    }
    
    private class voSubtitleImageInfoDescriptor
    {
        private voRect imageRectInfo;
        
        private voSubtitleImageInfoDescriptor() {
            this.imageRectInfo = null;
        }
        
        private voRect getImageRectInfo() {
            return this.imageRectInfo;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 111) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
            }
            else {
                this.imageRectInfo = new voRect();
                if (this.imageRectInfo.parse(parcel)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public class voSubtitleInfo
    {
        private int maxDuration;
        private ArrayList<voSubtitleInfoEntry> subtitleEntry;
        private int timeStamp;
        
        private voSubtitleInfo() {
            this.timeStamp = 0;
            this.maxDuration = -1;
            this.subtitleEntry = new ArrayList<voSubtitleInfoEntry>();
        }
        
        private int getMaxDuration() {
            if (this.subtitleEntry == null) {
                return 0;
            }
            for (int i = 0; i < this.subtitleEntry.size(); ++i) {
                final voSubtitleInfoEntry voSubtitleInfoEntry = this.subtitleEntry.get(i);
                if (voSubtitleInfoEntry.duration == -1) {
                    this.maxDuration = -1;
                    break;
                }
                if (voSubtitleInfoEntry.duration > this.maxDuration) {
                    this.maxDuration = voSubtitleInfoEntry.duration;
                }
            }
            return this.maxDuration;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() == 1) {
                this.timeStamp = parcel.readInt();
                if (parcel.readInt() == 100) {
                    while (parcel.dataAvail() > 0) {
                        final voSubtitleInfoEntry voSubtitleInfoEntry = new voSubtitleInfoEntry();
                        if (!voSubtitleInfoEntry.parse(parcel)) {
                            break;
                        }
                        if (this.subtitleEntry == null) {
                            this.subtitleEntry = new ArrayList<voSubtitleInfoEntry>();
                        }
                        this.subtitleEntry.add(voSubtitleInfoEntry);
                    }
                    if (this.subtitleEntry == null) {
                        return true;
                    }
                    this.getMaxDuration();
                    return true;
                }
            }
            return false;
        }
        
        public ArrayList<voSubtitleInfoEntry> getSubtitleEntry() {
            return this.subtitleEntry;
        }
        
        public int getTimeStamp() {
            return this.timeStamp;
        }
    }
    
    public class voSubtitleInfoEntry
    {
        private int duration;
        private ArrayList<View> mChildViewArr;
        private voSubtitleDisplayInfo subtitleDispInfo;
        private voRect subtitleRectInfo;
        
        private voSubtitleInfoEntry() {
            this.duration = -1;
            this.subtitleRectInfo = new voRect();
            this.subtitleDispInfo = new voSubtitleDisplayInfo();
            this.mChildViewArr = new ArrayList<View>();
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 2) {
                if (parcel.dataAvail() > 0) {
                    parcel.setDataPosition(parcel.dataPosition() - 4);
                    return false;
                }
            }
            else {
                this.duration = parcel.readInt();
                this.subtitleRectInfo = new voRect();
                if (this.subtitleRectInfo.parse(parcel)) {
                    this.subtitleDispInfo = new voSubtitleDisplayInfo();
                    if (this.subtitleDispInfo.parse(parcel)) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        public int getDuration() {
            return this.duration;
        }
        
        public voSubtitleDisplayInfo getSubtitleDispInfo() {
            return this.subtitleDispInfo;
        }
        
        public voRect getSubtitleRectInfo() {
            return this.subtitleRectInfo;
        }
    }
    
    public class voSubtitleStringInfo
    {
        private voSubtitleFontEffect charEffect;
        private voSubtitleFontInfo fontInfo;
        
        private voSubtitleStringInfo() {
            this.fontInfo = new voSubtitleFontInfo();
            this.charEffect = new voSubtitleFontEffect();
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 106) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
            }
            else {
                this.fontInfo = new voSubtitleFontInfo();
                if (this.fontInfo.parse(parcel)) {
                    this.charEffect = new voSubtitleFontEffect();
                    if (this.charEffect.parse(parcel)) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        public voSubtitleFontEffect getCharEffect() {
            return this.charEffect;
        }
        
        public voSubtitleFontInfo getFontInfo() {
            return this.fontInfo;
        }
    }
    
    public class voSubtitleTextDisplayDescriptor
    {
        private int scrollDirection;
        private int wrap;
        
        private voSubtitleTextDisplayDescriptor() {
            this.wrap = 0;
            this.scrollDirection = 0;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 110) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
                return false;
            }
            parcel.readInt();
            this.wrap = parcel.readInt();
            parcel.readInt();
            this.scrollDirection = parcel.readInt();
            return true;
        }
        
        public int getScrollDirection() {
            return this.scrollDirection;
        }
        
        public int getWrap() {
            return this.wrap;
        }
    }
    
    public class voSubtitleTextInfoEntry
    {
        private voSubtitleStringInfo stringInfo;
        private String stringText;
        
        private voSubtitleTextInfoEntry() {
            this.stringText = "";
            this.stringInfo = new voSubtitleStringInfo();
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 11) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
            }
            else {
                final int int1 = parcel.readInt();
                parcel.readInt();
                while (true) {
                    if (int1 <= 0) {
                        break Label_0104;
                    }
                    final byte[] array = new byte[int1];
                    parcel.readByteArray(array);
                    for (int i = 0; i < int1; i += 2) {
                        final byte b = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = b;
                    }
                    try {
                        this.stringText = new String(array, 0, int1, "utf-16");
                        this.stringInfo = new voSubtitleStringInfo();
                        if (this.stringInfo.parse(parcel)) {
                            return true;
                        }
                    }
                    catch (UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                        continue;
                    }
                    break;
                }
            }
            return false;
        }
        
        public voSubtitleStringInfo getStringInfo() {
            return this.stringInfo;
        }
        
        public String getStringText() {
            return this.stringText;
        }
    }
    
    public class voSubtitleTextRowDescriptor
    {
        private voRect dataBox;
        private int horizontalJustification;
        private int printDirection;
        private int verticalJustification;
        
        private voSubtitleTextRowDescriptor() {
            this.dataBox = new voRect();
            this.horizontalJustification = 0;
            this.verticalJustification = 0;
            this.printDirection = 0;
        }
        
        private boolean parse(final Parcel parcel) {
            if (parcel.readInt() != 109) {
                parcel.setDataPosition(parcel.dataPosition() - 4);
            }
            else {
                this.dataBox = new voRect();
                if (this.dataBox.parse(parcel)) {
                    while (true) {
                        try {
                            this.setDataBox(this.dataBox);
                            if (parcel.readInt() != 22) {
                                parcel.setDataPosition(parcel.dataPosition() - 4);
                                return false;
                            }
                        }
                        catch (Exception ex) {
                            ex.toString();
                            continue;
                        }
                        break;
                    }
                    this.horizontalJustification = parcel.readInt();
                    parcel.readInt();
                    this.verticalJustification = parcel.readInt();
                    parcel.readInt();
                    this.printDirection = parcel.readInt();
                    return true;
                }
            }
            return false;
        }
        
        private void setDataBox(final voRect dataBox) {
            this.dataBox = dataBox;
            if (voSubTitleManager.this.settings != null) {
                this.dataBox.setRectFillColor(voSubTitleManager.this.settings.converColorOpacityRate(voSubTitleManager.this.settings.converColor(dataBox.rectFillColor, voSubTitleManager.this.settings.backgroundColor, voSubTitleManager.this.settings.backgroundColorEnable), voSubTitleManager.this.settings.backgroundColorOpacityRate, voSubTitleManager.this.settings.backgroundColorOpacityRateEnable));
            }
        }
        
        public voRect getDataBox() {
            return this.dataBox;
        }
        
        public int getHorizontalJustification() {
            return this.horizontalJustification;
        }
        
        public int getPrintDirection() {
            return this.printDirection;
        }
        
        public int getVerticalJustification() {
            return this.verticalJustification;
        }
    }
    
    public class voSubtitleTextRowInfo
    {
        private ArrayList<voSubtitleTextInfoEntry> textInfoEntry;
        private voSubtitleTextRowDescriptor textRowDes;
        
        private voSubtitleTextRowInfo() {
            this.textInfoEntry = new ArrayList<voSubtitleTextInfoEntry>();
            this.textRowDes = new voSubtitleTextRowDescriptor();
        }
        
        private boolean parse(final Parcel parcel) {
            this.textInfoEntry = new ArrayList<voSubtitleTextInfoEntry>();
            int n = 0;
            while (true) {
                final voSubtitleTextInfoEntry voSubtitleTextInfoEntry = new voSubtitleTextInfoEntry();
                if (!voSubtitleTextInfoEntry.parse(parcel)) {
                    break;
                }
                this.textInfoEntry.add(voSubtitleTextInfoEntry);
                n = 1;
            }
            if (n != 0) {
                this.textRowDes = new voSubtitleTextRowDescriptor();
                if (this.textRowDes.parse(parcel)) {
                    return true;
                }
            }
            return false;
        }
        
        public ArrayList<voSubtitleTextInfoEntry> getTextInfoEntry() {
            return this.textInfoEntry;
        }
        
        public voSubtitleTextRowDescriptor getTextRowDes() {
            return this.textRowDes;
        }
    }
    
    private class voThumbnailPlayList
    {
        private boolean callEnabled;
        private voThumbnailInfo oldInfo;
        private int oldInfoIndex;
        private ArrayList<voThumbnailInfo> thumbnailInfoArray;
        private String urlPath;
        
        private voThumbnailPlayList() {
            this.thumbnailInfoArray = new ArrayList<voThumbnailInfo>();
            this.callEnabled = true;
            this.oldInfo = null;
            this.oldInfoIndex = -1;
            this.urlPath = "";
        }
        
        private voThumbnailInfo getCurrentInfo(final int n) {
            if (this.oldInfo != null && this.oldInfo.startTime < n && this.oldInfo.startTime + this.oldInfo.duration > n) {
                return null;
            }
            final int size = this.thumbnailInfoArray.size();
            int i;
            if (this.oldInfo != null && this.oldInfo.startTime < n) {
                i = this.oldInfoIndex;
            }
            else {
                i = 0;
            }
            while (i < size) {
                final voThumbnailInfo oldInfo = this.thumbnailInfoArray.get(i);
                voThumbnailInfo voThumbnailInfo;
                if (oldInfo.startTime > n) {
                    voThumbnailInfo = null;
                }
                else {
                    if (oldInfo.startTime + oldInfo.duration < n) {
                        oldInfo.pictureData = null;
                        ++i;
                        continue;
                    }
                    if (oldInfo.pictureData == null) {
                        this.loadPicture(i);
                        voThumbnailInfo = null;
                    }
                    else {
                        this.oldInfo = oldInfo;
                        this.oldInfoIndex = i;
                        voThumbnailInfo = oldInfo;
                        if (i < size - 1) {
                            this.loadPicture(i + 1);
                            voThumbnailInfo = oldInfo;
                        }
                    }
                }
                return voThumbnailInfo;
            }
            return null;
        }
        
        private void loadPicture(final int n) {
            new Thread(new Runnable() {
                final /* synthetic */ voThumbnailInfo val$info3 = voThumbnailPlayList.this.thumbnailInfoArray.get(n);
                
                @Override
                public void run() {
                    this.val$info3.loadPicture();
                }
            }).start();
        }
        
        private void setURL(final String s) {
            while (true) {
                this.callEnabled = false;
                this.thumbnailInfoArray.clear();
                while (true) {
                    int n3 = 0;
                    int n4 = 0;
                    Label_0294: {
                        try {
                            final URL url = new URL(s);
                            voLog.i("@@@ClosedCaptionManager.java", "Thumbnail PlayList download begin", new Object[0]);
                            this.urlPath = url.getPath();
                            final BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
                            final ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(0);
                            if (bufferedInputStream != null) {
                                final byte[] array = new byte[1024];
                                while (bufferedInputStream.available() > 0) {
                                    final int read = bufferedInputStream.read(array);
                                    if (read <= 0) {
                                        break;
                                    }
                                    byteArrayBuffer.append(array, 0, read);
                                }
                            }
                            final byte[] buffer = byteArrayBuffer.buffer();
                            if (buffer != null) {
                                final String[] split = new String(buffer).split("\n");
                                if (split != null) {
                                    final int n = 0;
                                    final int n2 = 0;
                                    if (n < split.length) {
                                        n3 = n;
                                        n4 = n2;
                                        if (split[n].compareToIgnoreCase("#EXTINF:") != 0) {
                                            break Label_0294;
                                        }
                                        n3 = n;
                                        n4 = n2;
                                        if (n < split.length - 1) {
                                            final voThumbnailInfo voThumbnailInfo = new voThumbnailInfo();
                                            final String replace = split[n].substring(8).replace("\r", "");
                                            voThumbnailInfo.startTime = n2;
                                            voThumbnailInfo.duration = Integer.parseInt(replace);
                                            n4 = n2 + voThumbnailInfo.duration;
                                            voThumbnailInfo.pictureURL = split[n + 1];
                                            if (this.thumbnailInfoArray.size() <= 0) {
                                                voThumbnailInfo.loadPicture();
                                            }
                                            this.thumbnailInfoArray.add(voThumbnailInfo);
                                            n3 = n + 1;
                                        }
                                        break Label_0294;
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {
                            voLog.i("@@@ClosedCaptionManager.java", "Thumbnail PlayList download Exception", new Object[0]);
                            ex.printStackTrace();
                        }
                        break;
                    }
                    final int n = n3 + 1;
                    final int n2 = n4;
                    continue;
                }
            }
            this.callEnabled = true;
        }
        
        private class voThumbnailInfo
        {
            private int duration;
            private byte[] pictureData;
            private String pictureURL;
            private int startTime;
            
            private voThumbnailInfo() {
                this.startTime = 0;
                this.duration = -1;
                this.pictureURL = "";
                this.pictureData = null;
            }
            
            private boolean loadPicture() {
                if (this.pictureURL.equals("")) {
                    return false;
                }
                Label_0158: {
                    if (!this.pictureURL.startsWith("http://") && !this.pictureURL.startsWith("https://") && !this.pictureURL.startsWith("HTTP://") && !this.pictureURL.startsWith("HTTPS://")) {
                        break Label_0158;
                    }
                    String s = this.pictureURL;
                    while (true) {
                        Label_0225: {
                            try {
                                final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(s).openStream());
                                final ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(0);
                                if (bufferedInputStream != null) {
                                    final byte[] array = new byte[1024];
                                    while (bufferedInputStream.available() > 0) {
                                        final int read = bufferedInputStream.read(array);
                                        if (read <= 0) {
                                            break;
                                        }
                                        byteArrayBuffer.append(array, 0, read);
                                    }
                                }
                                this.pictureData = byteArrayBuffer.buffer();
                                if (this.pictureData != null) {
                                    voLog.i("@@@ClosedCaptionManager.java", "loadPicture (%s) ok", s);
                                    return true;
                                }
                                break Label_0225;
                                s = voThumbnailPlayList.this.urlPath + "/" + this.pictureURL;
                                continue;
                            }
                            catch (Exception ex) {
                                voLog.i("@@@ClosedCaptionManager.java", "loadPicture Exception", new Object[0]);
                                ex.printStackTrace();
                                return true;
                            }
                        }
                        voLog.i("@@@ClosedCaptionManager.java", "loadPicture fail", new Object[0]);
                        return true;
                    }
                }
            }
        }
    }
}
