// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.javabridge.invoke.media.Unpause;
import com.netflix.mediaclient.javabridge.invoke.media.Stop;
import com.netflix.mediaclient.javabridge.invoke.android.SetWifiApsInfo;
import android.content.Context;
import com.netflix.mediaclient.media.bitrate.VideoBitrateRange;
import com.netflix.mediaclient.javabridge.invoke.media.SetVideoBitrateRanges;
import com.netflix.mediaclient.javabridge.invoke.android.InitVisualOn;
import com.netflix.mediaclient.javabridge.invoke.android.SetVideoSurface;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.invoke.SetConfigData;
import com.netflix.mediaclient.javabridge.invoke.media.SetStreamingBuffer;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.javabridge.invoke.media.SelectTracks;
import com.netflix.mediaclient.javabridge.invoke.media.Swim;
import com.netflix.mediaclient.javabridge.invoke.media.Play;
import com.netflix.mediaclient.javabridge.invoke.media.Pause;
import com.netflix.mediaclient.javabridge.invoke.media.Open;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.javabridge.invoke.media.Close;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.invoke.android.ChangePlayer;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.event.nrdp.media.BaseMediaEvent;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import com.netflix.mediaclient.event.nrdp.media.GenericMediaEvent;
import com.netflix.mediaclient.event.nrdp.media.Warning;
import com.netflix.mediaclient.event.nrdp.media.UpdateVideoBitrate;
import com.netflix.mediaclient.event.nrdp.media.UpdatePts;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.event.nrdp.media.StreamSelected;
import com.netflix.mediaclient.event.nrdp.media.Statechanged;
import com.netflix.mediaclient.event.nrdp.media.ShowSubtitle;
import com.netflix.mediaclient.event.nrdp.media.RemoveSubtitle;
import com.netflix.mediaclient.event.nrdp.media.NewStream;
import com.netflix.mediaclient.event.nrdp.media.Exception;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.BufferRange;
import com.netflix.mediaclient.event.nrdp.media.Buffering;
import com.netflix.mediaclient.event.nrdp.media.AudioTrackChanged;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import org.json.JSONObject;
import com.netflix.mediaclient.ui.player.NccpSubtitle;
import java.util.Arrays;
import java.util.ArrayList;
import org.json.JSONException;
import com.netflix.mediaclient.ui.player.NccpAudioSource;
import org.json.JSONArray;
import android.view.Display;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.media.TrickplayUrl;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.proxy.nrdp.media.StreamInfo;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.javabridge.ui.IMedia;

public class NativeMedia extends NativeNrdObject implements IMedia
{
    private static final String NAME_STREAMING = "streaming";
    private static final String TAG = "nf-bridge";
    private static final String TAG1 = "nf-media";
    private AudioSource[] mAudioTrackList;
    private AudioSource mCurrentAudioTrack;
    private int mCurrentAudioTrackIndex;
    private Subtitle mCurrentSubtitleTrack;
    private int mCurrentSubtitleTrackIndex;
    private int mCurrentVideoBitrate;
    private StreamInfo mCurrentVideoStream;
    private AudioSubtitleDefaultOrderInfo[] mDefaultOrderInfo;
    private int mDisplayAspectRatioX;
    private int mDisplayAspectRatioY;
    private int mDuration;
    private int mFrameX;
    private int mFrameY;
    private int mPosition;
    private int mState;
    private SubtitleOutputMode mSubtitleOutputMode;
    private SubtitleProfile mSubtitleProfile;
    private Subtitle[] mSubtitleTrackList;
    private StreamInfo mTargetVideoStream;
    private TrickplayUrl[] mTrickplayUrlList;
    
    public NativeMedia(final Bridge bridge) {
        super(bridge);
    }
    
    private void calculateVideoSize() {
        if (Log.isLoggable("nf-media", 3)) {
            Log.d("nf-media", "handlePropertyUpdate:: displayAspectRatio x: " + this.mDisplayAspectRatioX + ", y: " + this.mDisplayAspectRatioY);
        }
        final Display displaySize = this.getDisplaySize();
        if (displaySize == null) {
            Log.w("nf-media", "Screen size null, use default calculation");
            if (this.mDisplayAspectRatioY == 0) {
                this.mFrameY = 0;
                this.mFrameX = 0;
            }
            else {
                this.mFrameY = 1080;
                this.mFrameX = this.mFrameY * this.mDisplayAspectRatioX / this.mDisplayAspectRatioY;
            }
        }
        else {
            if (Log.isLoggable("nf-media", 3)) {
                Log.d("nf-media", "handlePropertyUpdate:: screen size x: " + displaySize.getWidth() + ", y: " + displaySize.getHeight());
            }
            if (this.mDisplayAspectRatioY == 0) {
                this.mFrameY = 0;
                this.mFrameX = 0;
            }
            else {
                this.mFrameY = displaySize.getHeight();
                this.mFrameX = this.mFrameY * this.mDisplayAspectRatioX / this.mDisplayAspectRatioY;
            }
        }
        if (Log.isLoggable("nf-media", 3)) {
            Log.d("nf-media", "handlePropertyUpdate:: frame x: " + this.mFrameX + ", y: " + this.mFrameY);
        }
    }
    
    private AudioSource[] getAudioSources(final JSONArray jsonArray) throws JSONException {
        AudioSource[] array;
        if (jsonArray == null || jsonArray.length() < 1) {
            Log.w("nf-bridge", "Empty audio source list");
            array = new AudioSource[0];
        }
        else {
            final AudioSource[] array2 = new AudioSource[jsonArray.length()];
            int n = 0;
            while (true) {
                array = array2;
                if (n >= array2.length) {
                    break;
                }
                array2[n] = NccpAudioSource.newInstance(jsonArray.getJSONObject(n), n);
                ++n;
            }
        }
        return array;
    }
    
    private AudioSubtitleDefaultOrderInfo[] getDefaultOrderInfo(final JSONArray jsonArray) {
        if (this.mAudioTrackList == null || this.mAudioTrackList.length < 1) {
            Log.d("nf-bridge", "Restrictions not found! Audio track list is empty!");
            return new AudioSubtitleDefaultOrderInfo[0];
        }
        if (jsonArray == null || jsonArray.length() < 1) {
            Log.d("nf-bridge", "No defaults found!");
            return new AudioSubtitleDefaultOrderInfo[0];
        }
        final ArrayList<AudioSubtitleDefaultOrderInfo> list = new ArrayList<AudioSubtitleDefaultOrderInfo>(jsonArray.length());
        int i = 0;
    Label_0143_Outer:
        while (i < jsonArray.length()) {
            while (true) {
                try {
                    final AudioSubtitleDefaultOrderInfo audioSubtitleDefaultOrderInfo = new AudioSubtitleDefaultOrderInfo(jsonArray.getJSONObject(i));
                    if (Log.isLoggable("nf-bridge", 3)) {
                        Log.d("nf-bridge", "Default found " + audioSubtitleDefaultOrderInfo);
                    }
                    if (this.isValid(audioSubtitleDefaultOrderInfo)) {
                        list.add(audioSubtitleDefaultOrderInfo);
                    }
                    ++i;
                    continue Label_0143_Outer;
                }
                catch (JSONException ex) {
                    Log.e("nf-bridge", "Failed to parse default ", (Throwable)ex);
                    continue;
                }
                break;
            }
            break;
        }
        final AudioSubtitleDefaultOrderInfo[] array = list.toArray(new AudioSubtitleDefaultOrderInfo[list.size()]);
        Arrays.sort(array);
        return array;
    }
    
    private Display getDisplaySize() {
        return this.bridge.getDisplaySize();
    }
    
    private Subtitle getSubtitle(final String s) {
        if (this.mSubtitleTrackList != null) {
            int i = 0;
            while (i < this.mSubtitleTrackList.length) {
                final Subtitle subtitle = this.mSubtitleTrackList[i];
                if (subtitle.getId() != null && subtitle.getId().equals(s)) {
                    final Subtitle subtitle2 = subtitle;
                    if (Log.isLoggable("nf-bridge", 3)) {
                        Log.d("nf-bridge", "Subtitle found " + subtitle + " for id " + s);
                        return subtitle;
                    }
                    return subtitle2;
                }
                else {
                    ++i;
                }
            }
            Log.e("nf-bridge", "Subtitle not found for id " + s);
            return null;
        }
        return null;
    }
    
    private Subtitle[] getSubtitle(final JSONArray jsonArray) throws JSONException {
        Subtitle[] array;
        if (jsonArray == null || jsonArray.length() < 1) {
            Log.w("nf-bridge", "Empty subtitle list");
            array = new Subtitle[0];
        }
        else {
            final Subtitle[] array2 = new Subtitle[jsonArray.length()];
            int n = 0;
            while (true) {
                array = array2;
                if (n >= array2.length) {
                    break;
                }
                array2[n] = NccpSubtitle.newInstance(jsonArray.getJSONObject(n), n);
                ++n;
            }
        }
        return array;
    }
    
    private int handleEvent(JSONObject jsonObject) throws java.lang.Exception {
        jsonObject = this.getJSONObject(jsonObject, "data", null);
        if (jsonObject != null) {
            final String string = this.getString(jsonObject, "type", null);
            BaseMediaEvent baseMediaEvent;
            if ("Nccp".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: NCCP Type!!!");
                baseMediaEvent = NccpError.toNccpError(jsonObject);
                if (baseMediaEvent == null) {
                    Log.w("nf-bridge", "Uknown event for NCCP type!");
                    return 0;
                }
                Log.d("nf-bridge", "Media::processUpdate: Event found NccpError");
            }
            else if ("background".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: background NCCP error");
                baseMediaEvent = NccpError.toNccpError(jsonObject);
                if (baseMediaEvent == null) {
                    Log.w("nf-bridge", "Uknown event for background type!");
                    return 0;
                }
                Log.d("nf-bridge", "Media::processUpdate: Event found NccpError");
            }
            else if ("audioTrackChanged".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: Event found AudioTrackChanged");
                baseMediaEvent = new AudioTrackChanged(jsonObject);
                this.mCurrentAudioTrackIndex = ((AudioTrackChanged)baseMediaEvent).getTrackIndex();
            }
            else if ("buffering".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: Event found Buffering");
                baseMediaEvent = new Buffering(jsonObject);
            }
            else if ("bufferrange".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: Event found BufferRange");
                baseMediaEvent = new BufferRange(jsonObject);
            }
            else if ("error".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: Event found Error");
                baseMediaEvent = new Error(jsonObject);
            }
            else if ("exception".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: Event found Exception");
                baseMediaEvent = new Exception(jsonObject);
            }
            else if ("nccperror".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: Event found NccpError");
                baseMediaEvent = NccpError.toNccpError(jsonObject);
            }
            else if ("newStream".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: Event found NewStream, process");
                baseMediaEvent = new NewStream(jsonObject);
                final StreamInfo streamInfo = ((NewStream)baseMediaEvent).getStreamInfo();
                if (streamInfo == null) {
                    Log.e("nf-bridge", "Stream is null!");
                    return 0;
                }
                if (streamInfo.getStreamType() != 1) {
                    Log.d("nf-bridge", "Not vdeo stream, ignore");
                    return 0;
                }
                Log.d("nf-bridge", "Video stream");
                this.mCurrentVideoStream = streamInfo;
            }
            else if ("removeSubtitle".equalsIgnoreCase(string)) {
                Log.d("nf-bridge", "Media::processUpdate: Event found RemoveSubtitle");
                baseMediaEvent = new RemoveSubtitle(jsonObject);
            }
            else {
                if ("setvideobitraterange".equalsIgnoreCase(string)) {
                    Log.d("nf-bridge", "Media::processUpdate: Event found SetVideoBitrateRange");
                    return -1;
                }
                if ("setvideoresolutionrange".equalsIgnoreCase(string)) {
                    Log.d("nf-bridge", "Media::processUpdate: Event found SetVideoResolutionRange");
                    return -1;
                }
                if ("showSubtitle".equalsIgnoreCase(string)) {
                    Log.d("nf-bridge", "Media::processUpdate: Event found ShowSubtitle");
                    baseMediaEvent = new ShowSubtitle(jsonObject);
                }
                else {
                    if ("skip".equalsIgnoreCase(string)) {
                        Log.d("nf-bridge", "Media::processUpdate: Event found Skip. NOOP");
                        return -1;
                    }
                    if ("statechanged".equalsIgnoreCase(string)) {
                        Log.d("nf-bridge", "Media::processUpdate: Event found Statechanged");
                        baseMediaEvent = new Statechanged(jsonObject);
                    }
                    else if ("streamSelected".equalsIgnoreCase(string)) {
                        Log.d("nf-bridge", "Media::processUpdate: Event found StreamSelected");
                        baseMediaEvent = new StreamSelected(jsonObject);
                        final StreamInfo streamInfo2 = ((StreamSelected)baseMediaEvent).getStreamInfo();
                        if (streamInfo2 == null) {
                            Log.e("nf-bridge", "Stream is null!");
                            return 0;
                        }
                        if (streamInfo2.getStreamType() != 1) {
                            Log.d("nf-bridge", "Not vdeo stream, ignore");
                            return 0;
                        }
                        Log.d("nf-bridge", "Video stream");
                        this.mTargetVideoStream = streamInfo2;
                    }
                    else if ("subtitledata".equalsIgnoreCase(string)) {
                        Log.d("nf-bridge", "Media::processUpdate: Event found SubtitleData");
                        baseMediaEvent = new SubtitleData(jsonObject);
                    }
                    else {
                        if ("subtitleTrackChanged".equalsIgnoreCase(string)) {
                            Log.d("nf-bridge", "Media::processUpdate: Event found SubtitleTrackChanged");
                            return -1;
                        }
                        if ("updatePts".equalsIgnoreCase(string)) {
                            Log.d("nf-bridge", "Media::processUpdate: Event found UpdatePts. Consuming.");
                            baseMediaEvent = new UpdatePts(jsonObject);
                            this.mPosition = ((UpdatePts)baseMediaEvent).getPts();
                        }
                        else {
                            if ("updateVideoBitrate".equalsIgnoreCase(string)) {
                                final UpdateVideoBitrate updateVideoBitrate = new UpdateVideoBitrate(jsonObject);
                                this.mCurrentVideoBitrate = updateVideoBitrate.getBitsPerSecond();
                                if (Log.isLoggable("nf-bridge", 3)) {
                                    Log.d("nf-bridge", "Media::processUpdate: Event found UpdateVideoBitrate " + updateVideoBitrate.getBitsPerSecond());
                                }
                                return -1;
                            }
                            if ("videoWindowChanged".equalsIgnoreCase(string)) {
                                Log.d("nf-bridge", "Media::processUpdate: Event found VideoWindowChanged");
                                return -1;
                            }
                            if ("warning".equalsIgnoreCase(string)) {
                                Log.d("nf-bridge", "Media::processUpdate: Event found Warning");
                                baseMediaEvent = new Warning(jsonObject);
                            }
                            else if ("bufferingComplete".equalsIgnoreCase(string)) {
                                Log.d("nf-bridge", "Media::processUpdate: Event found bufferingComplete");
                                baseMediaEvent = new GenericMediaEvent("bufferingComplete");
                            }
                            else if ("openComplete".equalsIgnoreCase(string)) {
                                Log.d("nf-bridge", "Media::processUpdate: Event found openComplete");
                                baseMediaEvent = new GenericMediaEvent("openComplete");
                            }
                            else if ("endOfStream".equalsIgnoreCase(string)) {
                                Log.d("nf-bridge", "Media::processUpdate: Event found endOfStream");
                                baseMediaEvent = new GenericMediaEvent("endOfStream");
                            }
                            else if ("underflow".equalsIgnoreCase(string)) {
                                Log.d("nf-bridge", "Media::processUpdate: Event found underflow");
                                baseMediaEvent = new GenericMediaEvent("underflow");
                            }
                            else {
                                if (Log.isLoggable("nf-bridge", 3)) {
                                    Log.d("nf-bridge", "Media::processUpdate: uknown type " + string);
                                    return 0;
                                }
                                return 0;
                            }
                        }
                    }
                }
            }
            if (Log.isLoggable("nf-bridge", 3)) {
                Log.d("nf-bridge", "Passing event to MP " + baseMediaEvent);
            }
            if (baseMediaEvent != null && baseMediaEvent instanceof MediaEvent) {
                this.handleListener(baseMediaEvent.getName(), baseMediaEvent);
            }
            return -1;
        }
        Log.e("nf-bridge", "Media::processUpdate: Data not found!");
        return 0;
    }
    
    private int handlePropertyUpdate(JSONObject jsonObject) throws JSONException {
        jsonObject = this.getJSONObject(jsonObject, "properties", null);
        if (jsonObject == null) {
            Log.w("nf-bridge", "handlePropertyUpdate:: properties does not exist");
            return 0;
        }
        if (jsonObject.has("currentAudioTrack")) {
            this.mCurrentAudioTrackIndex = this.getInt(jsonObject, "currentAudioTrack", -1);
            if (this.mAudioTrackList != null && this.mAudioTrackList.length > this.mCurrentAudioTrackIndex && this.mCurrentAudioTrackIndex > -1) {
                this.mCurrentAudioTrack = this.mAudioTrackList[this.mCurrentAudioTrackIndex];
            }
            else {
                this.mCurrentAudioTrack = null;
            }
        }
        if (jsonObject.has("currentSubtitleTrack")) {
            this.mCurrentSubtitleTrackIndex = this.getInt(jsonObject, "currentSubtitleTrack", -1);
            if (this.mSubtitleTrackList != null && this.mSubtitleTrackList.length > this.mCurrentSubtitleTrackIndex && this.mCurrentSubtitleTrackIndex > -1) {
                this.mCurrentSubtitleTrack = this.mSubtitleTrackList[this.mCurrentSubtitleTrackIndex];
            }
            else {
                this.mCurrentSubtitleTrack = null;
            }
        }
        if (jsonObject.has("state")) {
            this.mState = this.getInt(jsonObject, "state", -1);
        }
        if (jsonObject.has("duration")) {
            this.mDuration = this.getInt(jsonObject, "duration", Integer.MIN_VALUE);
        }
        if (jsonObject.has("subtitleTrackList")) {
            this.mSubtitleTrackList = this.getSubtitle(this.getJSONArray(jsonObject, "subtitleTrackList"));
        }
        if (jsonObject.has("displayAspectRatio")) {
            final JSONObject jsonObject2 = this.getJSONObject(jsonObject, "displayAspectRatio", null);
            if (jsonObject2 != null) {
                Log.d("nf-media", "handlePropertyUpdate:: displayAspectRatio found");
                if (jsonObject2.has("x")) {
                    this.mDisplayAspectRatioX = this.getInt(jsonObject2, "x", 0);
                }
                if (jsonObject2.has("y")) {
                    this.mDisplayAspectRatioY = this.getInt(jsonObject2, "y", 0);
                }
                this.calculateVideoSize();
            }
            else {
                Log.w("nf-media", "handlePropertyUpdate:: displayAspectRatio found, but it is null!!!");
            }
        }
        else {
            Log.w("nf-media", "handlePropertyUpdate:: displayAspectRatio does not exist");
        }
        if (jsonObject.has("audioTrackList")) {
            this.mAudioTrackList = this.getAudioSources(this.getJSONArray(jsonObject, "audioTrackList"));
        }
        if (jsonObject.has("defaultTrackOrderList")) {
            this.mDefaultOrderInfo = this.getDefaultOrderInfo(this.getJSONArray(jsonObject, "defaultTrackOrderList"));
        }
        if (jsonObject.has("trickplayList")) {
            this.mTrickplayUrlList = this.toTrickplayUrlList(this.getJSONArray(jsonObject, "trickplayList"));
        }
        return -1;
    }
    
    private boolean isValid(final AudioSubtitleDefaultOrderInfo audioSubtitleDefaultOrderInfo) {
        Label_0025: {
            if (this.mAudioTrackList == null || this.mAudioTrackList.length < 1) {
                Log.e("nf-bridge", "Audio track list is empty!");
            }
            else {
                int i = 0;
                while (i < this.mAudioTrackList.length) {
                    final AudioSource audioSource = this.mAudioTrackList[i];
                    if (audioSource.getId() != null && audioSource.getId().equals(audioSubtitleDefaultOrderInfo.getAudioTrackId())) {
                        if (Log.isLoggable("nf-bridge", 3)) {
                            Log.d("nf-bridge", "Audio track found " + audioSource + " for default audio track id " + audioSubtitleDefaultOrderInfo.getAudioTrackId());
                        }
                        final String[] disallowedSubtitles = this.mAudioTrackList[i].getDisallowedSubtitles();
                        if (disallowedSubtitles != null) {
                            int j = 0;
                            while (j < disallowedSubtitles.length) {
                                if (disallowedSubtitles[j] != null && disallowedSubtitles[j].equals(audioSubtitleDefaultOrderInfo.getSubtitleTrackId())) {
                                    if (Log.isLoggable("nf-bridge", 6)) {
                                        Log.e("nf-bridge", "Default subtitle track id " + audioSubtitleDefaultOrderInfo.getSubtitleTrackId() + " is not allowed! Error on NCCP side!");
                                        return false;
                                    }
                                    break Label_0025;
                                }
                                else {
                                    ++j;
                                }
                            }
                        }
                        if (audioSubtitleDefaultOrderInfo.getSubtitleTrackId() == null || "none".equalsIgnoreCase(audioSubtitleDefaultOrderInfo.getSubtitleTrackId()) || "".equals(audioSubtitleDefaultOrderInfo.getSubtitleTrackId())) {
                            if (Log.isLoggable("nf-bridge", 3)) {
                                Log.d("nf-bridge", "Subtitle track id is NULL for default, no subtitles: " + audioSubtitleDefaultOrderInfo);
                            }
                            return true;
                        }
                        if (this.getSubtitle(audioSubtitleDefaultOrderInfo.getSubtitleTrackId()) != null) {
                            if (Log.isLoggable("nf-bridge", 3)) {
                                Log.d("nf-bridge", "Default is valid, no restrictions  " + audioSubtitleDefaultOrderInfo);
                            }
                            return true;
                        }
                        break;
                    }
                    else {
                        ++i;
                    }
                }
            }
        }
        return false;
    }
    
    private TrickplayUrl[] toTrickplayUrlList(final JSONArray jsonArray) throws JSONException {
        TrickplayUrl[] array;
        if (jsonArray == null || jsonArray.length() < 1) {
            Log.w("nf-bridge", "Empty trickplayUrlList");
            array = new TrickplayUrl[0];
        }
        else {
            final TrickplayUrl[] array2 = new TrickplayUrl[jsonArray.length()];
            int n = 0;
            while (true) {
                array = array2;
                if (n >= array2.length) {
                    break;
                }
                array2[n] = new TrickplayUrl(jsonArray.getJSONObject(n));
                Log.d("nf-bridge", array2[n].toString());
                ++n;
            }
        }
        return array;
    }
    
    @Override
    public void changePlayer(final PlayerType playerType, final int n) {
        this.bridge.getNrdProxy().invokeMethod(new ChangePlayer(playerType, n));
    }
    
    @Override
    public void close() {
        this.bridge.getNrdProxy().invokeMethod(new Close());
    }
    
    @Override
    public AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo() {
        return this.mDefaultOrderInfo;
    }
    
    @Override
    public AudioSource[] getAudioTrackList() {
        return this.mAudioTrackList;
    }
    
    @Override
    public AudioSource getCurrentAudioTrack() {
        return this.mCurrentAudioTrack;
    }
    
    @Override
    public int getCurrentPosition() {
        return this.mPosition;
    }
    
    @Override
    public Subtitle getCurrentSubtitleTrack() {
        return this.mCurrentSubtitleTrack;
    }
    
    public int getCurrentVideoBitrate() {
        return this.mCurrentVideoBitrate;
    }
    
    public StreamInfo getCurrentVideoStream() {
        return this.mCurrentVideoStream;
    }
    
    @Override
    public float getDisplayAspectRatio() {
        if (this.mDisplayAspectRatioY == 0) {
            return 0.0f;
        }
        return this.mDisplayAspectRatioX * 1.0f / this.mDisplayAspectRatioY;
    }
    
    @Override
    public int getDuration() {
        return this.mDuration;
    }
    
    @Override
    public int getMediaPosition() {
        return this.mPosition;
    }
    
    @Override
    public String getName() {
        return "media";
    }
    
    @Override
    public String getPath() {
        return "nrdp.media";
    }
    
    @Override
    public PlayoutMetadata getPlayoutMetadata() {
        Log.d("nf-bridge", "Media:: METADATA");
        boolean highDefinition = false;
        boolean superHighDefinition = false;
        int n = 0;
        int n2 = 0;
        if (this.mCurrentVideoStream == null) {
            Log.w("nf-bridge", "Media:: Current video stream info uknown");
        }
        else {
            n2 = this.mCurrentVideoStream.getBitsPerSecond() / 1024;
            highDefinition = this.mCurrentVideoStream.isHighDefinition();
            superHighDefinition = this.mCurrentVideoStream.isSuperHighDefinition();
        }
        if (this.mTargetVideoStream != null) {
            n = this.mTargetVideoStream.getBitsPerSecond() / 1024;
        }
        else {
            Log.w("nf-bridge", "Media:: Target video stream info uknown");
        }
        int numChannels = 0;
        int trackType = 0;
        String languageDescription = "";
        if (this.mAudioTrackList != null && this.mAudioTrackList.length > this.mCurrentAudioTrackIndex && this.mCurrentAudioTrackIndex > -1) {
            final AudioSource audioSource = this.mAudioTrackList[this.mCurrentAudioTrackIndex];
            if (audioSource != null) {
                numChannels = audioSource.getNumChannels();
                trackType = audioSource.getTrackType();
                languageDescription = audioSource.getLanguageDescription();
            }
            else {
                Log.e("nf-bridge", "Audio source is null for  " + this.mCurrentAudioTrackIndex);
            }
        }
        else if (this.mAudioTrackList == null) {
            Log.e("nf-bridge", "audio list is null ");
        }
        else {
            Log.e("nf-bridge", "audio list has less elements " + this.mAudioTrackList.length + " than current index " + this.mCurrentAudioTrackIndex);
        }
        final PlayoutMetadata playoutMetadata = new PlayoutMetadata(this.mPosition, this.mDuration, n2, n, highDefinition, superHighDefinition, languageDescription, numChannels, trackType);
        if (Log.isLoggable("nf-bridge", 3)) {
            Log.d("nf-bridge", "Media:: getPlayoutMetadata:: " + playoutMetadata);
        }
        return playoutMetadata;
    }
    
    @Override
    public int getState() {
        return this.mState;
    }
    
    @Override
    public SubtitleOutputMode getSubtitleOutputMode() {
        return this.mSubtitleOutputMode;
    }
    
    @Override
    public SubtitleProfile getSubtitleProfile() {
        return this.mSubtitleProfile;
    }
    
    @Override
    public Subtitle[] getSubtitleTrackList() {
        return this.mSubtitleTrackList;
    }
    
    @Override
    public TrickplayUrl[] getTrickplayUrlList() {
        return this.mTrickplayUrlList;
    }
    
    @Override
    public int getVideoHeight() {
        if (this.mCurrentVideoStream == null) {
            if (Log.isLoggable("nf-bridge", 3)) {
                Log.d("nf-media", "getVideoHeight:: Current video stream info unknown, use display aspect ratio " + this.mDisplayAspectRatioY + " and height " + this.mFrameY);
            }
            return this.mFrameY;
        }
        if (Log.isLoggable("nf-bridge", 3)) {
            Log.d("nf-media", "Current video stream " + this.mCurrentVideoStream);
        }
        return this.mCurrentVideoStream.getFrameHeight();
    }
    
    @Override
    public int getVideoWidth() {
        if (this.mCurrentVideoStream == null) {
            if (Log.isLoggable("nf-bridge", 3)) {
                Log.d("nf-media", "getVideoHeight:: Current video stream info unknown, use display aspect ratio " + this.mDisplayAspectRatioX + " and width " + this.mFrameX);
            }
            return this.mFrameX;
        }
        if (Log.isLoggable("nf-bridge", 3)) {
            Log.d("nf-media", "Current video stream " + this.mCurrentVideoStream);
        }
        return this.mCurrentVideoStream.getFrameWidth();
    }
    
    @Override
    public void open(final long n, final PlayContext playContext, final Open.NetType netType) {
        this.bridge.getNrdProxy().invokeMethod(new Open(n, playContext, netType));
        Log.d("nf-bridge", "invokeMethod just called...");
    }
    
    @Override
    public void pause() {
        this.bridge.getNrdProxy().invokeMethod(new Pause());
    }
    
    @Override
    public void play(final long n) {
        this.bridge.getNrdProxy().invokeMethod(new Play(n));
    }
    
    @Override
    public int processUpdate(final JSONObject jsonObject) {
        try {
            final String string = this.getString(jsonObject, "type", null);
            if (Log.isLoggable("nf-bridge", 3)) {
                Log.d("nf-bridge", "processUpdate: handle type " + string);
            }
            if ("PropertyUpdate".equalsIgnoreCase(string)) {
                if (jsonObject != null && Log.isLoggable("nf-bridge", 3)) {
                    Log.d("nf-bridge", "processUpdate: handle prop update " + jsonObject.toString());
                }
                return this.handlePropertyUpdate(jsonObject);
            }
            Log.d("nf-bridge", "processUpdate: handle event");
            return this.handleEvent(jsonObject);
        }
        catch (java.lang.Exception ex) {
            Log.e("nf-bridge", "Failed with JSON", ex);
            return 0;
        }
    }
    
    @Override
    public void reset() {
        this.mAudioTrackList = null;
        this.mSubtitleTrackList = null;
        this.mDefaultOrderInfo = null;
        this.mCurrentSubtitleTrackIndex = 0;
        this.mCurrentAudioTrackIndex = 0;
        this.mCurrentSubtitleTrack = null;
        this.mCurrentAudioTrack = null;
        this.mDuration = 0;
        this.mPosition = 0;
        this.mState = 0;
        this.mCurrentVideoBitrate = 0;
        this.mCurrentVideoStream = null;
        this.mDisplayAspectRatioX = 0;
        this.mDisplayAspectRatioX = 0;
        this.mFrameX = 0;
        this.mFrameY = 0;
    }
    
    @Override
    public void seekTo(final int n, final boolean b) {
        this.bridge.getNrdProxy().invokeMethod(new Swim(n, b));
    }
    
    @Override
    public boolean selectTracks(final AudioSource audioSource, final Subtitle subtitle) {
        this.bridge.getNrdProxy().invokeMethod(new SelectTracks(audioSource, subtitle));
        return true;
    }
    
    @Override
    public void setAudioBitrateRange(final AudioBitrateRange audioBitrateRange) {
    }
    
    @Override
    public void setMaxVideoBufferSize(final int n) {
        this.bridge.getNrdProxy().invokeMethod(new SetStreamingBuffer(false, n, 0));
    }
    
    @Override
    public void setNetworkProfile(final int n) {
        this.bridge.getNrdProxy().setProperty("nrdp.media", "networkProfile", String.valueOf(n));
    }
    
    public boolean setProperty(final String s, final String s2) {
        if (Log.isLoggable("nf-bridge", 3)) {
            Log.d("nf-bridge", "Sets property " + s + " to " + s2);
        }
        return false;
    }
    
    @Override
    public void setStreamingQoe(final String s) {
        if (s == null) {
            return;
        }
        try {
            this.bridge.getNrdProxy().invokeMethod(new SetConfigData(new JSONObject(s), "streaming"));
        }
        catch (JSONException ex) {
            Log.e("nf-bridge", "Failed to create JSON object, unable to setConfigData", (Throwable)ex);
        }
    }
    
    @Override
    public void setSubtitleOutputMode(final SubtitleOutputMode mSubtitleOutputMode) {
        if (mSubtitleOutputMode == null) {
            throw new IllegalArgumentException("Output mode can not be null!");
        }
        this.bridge.getNrdProxy().setProperty("media", "subtitleOutputMode", String.valueOf(mSubtitleOutputMode.getValue()));
        this.mSubtitleOutputMode = mSubtitleOutputMode;
    }
    
    @Override
    public void setSubtitleProfile(final SubtitleProfile mSubtitleProfile) {
        if (mSubtitleProfile == null) {
            throw new IllegalArgumentException("Subtitle profile can not be null!");
        }
        this.bridge.getNrdProxy().setProperty("media", "subtitleProfile", String.valueOf(mSubtitleProfile.getValue()));
        this.mSubtitleProfile = mSubtitleProfile;
    }
    
    @Override
    public void setSurface(final Surface surface) {
        this.bridge.getNrdProxy().invokeMethod(new SetVideoSurface(surface));
        Log.d("nf-bridge", "invokeMethod just called setSurface...");
    }
    
    @Override
    public void setThrotteled(final boolean b) {
        this.bridge.getNrdProxy().setProperty("nrdp.media", "throttled", "false");
    }
    
    @Override
    public void setVOapi(final long n, final long n2) {
        this.bridge.getNrdProxy().invokeMethod(new InitVisualOn(n, n2));
    }
    
    @Override
    public void setVideoBitrateRange(final int n, final int n2) {
        this.bridge.getNrdProxy().invokeMethod(new SetVideoBitrateRanges(n, n2));
    }
    
    @Override
    public void setVideoBitrateRanges(final VideoBitrateRange[] array) {
        this.bridge.getNrdProxy().invokeMethod(new SetVideoBitrateRanges(array));
    }
    
    @Override
    public void setWifiApsInfo(final Context context, final String s, final boolean b) {
        this.bridge.getNrdProxy().invokeMethod(new SetWifiApsInfo(context, s, b));
        Log.d("nf-bridge", "invokeMethod setWifiApsInfo just called...");
    }
    
    @Override
    public void stop() {
        this.bridge.getNrdProxy().invokeMethod(new Stop());
    }
    
    @Override
    public void unpause() {
        this.bridge.getNrdProxy().invokeMethod(new Unpause());
    }
}
