// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.service.falkor.Falkor$Creator;
import java.util.List;
import java.util.Set;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.netflix.falkor.Func;
import com.netflix.model.leafs.ListOfMoviesSummary;
import com.netflix.falkor.Ref;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.falkor.BranchMap;

public class FalkorEvidenceList<T> extends BranchMap<T> implements LoMo, FalkorObject
{
    private static final String TAG = "FalkorEvidenceList";
    private UnsummarizedList<FalkorBillboardData> billboardData;
    private UnsummarizedList<UnsummarizedList<Ref>> discoveryData;
    private ListOfMoviesSummary summary;
    private UnsummarizedList<Ref> videoEvidence;
    
    public FalkorEvidenceList(final Func<T> func) {
        super(func);
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("FalkorEvidenceList", "Creating FalkorList");
        }
    }
    
    public int describeContents() {
        throw new IllegalStateException("unsupported method");
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                    Log.d("FalkorEvidenceList", "FalkorEvidenceLists:get: key:" + s);
                }
                return super.get(s);
            }
            case "summary": {
                return this.summary;
            }
            case "discoveryEvidence": {
                return this.discoveryData;
            }
            case "videoEvidence": {
                return this.videoEvidence;
            }
            case "billboardData": {
                return this.billboardData;
            }
        }
    }
    
    public int getHeroTrackId() {
        return this.summary.getHeroTrackId();
    }
    
    public String getId() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getId();
    }
    
    @Override
    public Set<String> getKeys() {
        final Set<String> keys = super.getKeys();
        if (this.summary != null) {
            keys.add("summary");
        }
        if (this.videoEvidence != null) {
            keys.add("videoEvidence");
        }
        if (this.billboardData != null) {
            keys.add("billboardData");
        }
        if (this.discoveryData != null) {
            keys.add("discoveryEvidence");
        }
        return keys;
    }
    
    public int getListPos() {
        if (this.summary == null) {
            return 0;
        }
        return this.summary.getListPos();
    }
    
    @Override
    public List<String> getMoreImages() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getMoreImages();
    }
    
    @Override
    public int getNumVideos() {
        if (this.summary == null) {
            return 0;
        }
        return this.summary.getNumVideos();
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("FalkorEvidenceList", "Creating leaf for key: " + s);
        }
        switch (s) {
            default: {
                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                    Log.d("FalkorEvidenceList", "FalkorEvidenceLists:getOrCreate: key:" + s);
                }
                return super.getOrCreate(s);
            }
            case "summary": {
                return this.summary = new ListOfMoviesSummary();
            }
            case "discoveryEvidence": {
                return this.discoveryData = new UnsummarizedList<UnsummarizedList<Ref>>(Falkor$Creator.UnsummarizedListOfRef);
            }
            case "videoEvidence": {
                return this.videoEvidence = new UnsummarizedList<Ref>(Falkor$Creator.Ref);
            }
            case "billboardData": {
                return this.billboardData = new UnsummarizedList<FalkorBillboardData>(Falkor$Creator.FalkorBillboardData());
            }
        }
    }
    
    public String getRequestId() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getRequestId();
    }
    
    public String getTitle() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getTitle();
    }
    
    public int getTrackId() {
        if (this.summary == null) {
            return -1;
        }
        return this.summary.getTrackId();
    }
    
    public LoMoType getType() {
        if (this.summary == null) {
            return LoMoType.STANDARD;
        }
        return this.summary.getType();
    }
    
    @Override
    public boolean isBillboard() {
        return this.summary != null && this.summary.isBillboard();
    }
    
    public boolean isHero() {
        return this.summary.isHero();
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {
                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                    Log.d("FalkorEvidenceList", String.format("FalkorEvidenceList:set key: %s, value: %s", s, o));
                }
                super.set(s, o);
            }
            case "summary": {
                this.summary = (ListOfMoviesSummary)o;
            }
            case "videoEvidence": {
                this.videoEvidence = (UnsummarizedList<Ref>)o;
            }
            case "billboardData": {
                this.billboardData = (UnsummarizedList<FalkorBillboardData>)o;
            }
            case "discoveryEvidence": {
                this.discoveryData = (UnsummarizedList<UnsummarizedList<Ref>>)o;
            }
        }
    }
    
    @Override
    public void setId(final String id) {
        if (this.summary != null) {
            this.summary.setId(id);
        }
    }
    
    @Override
    public void setListPos(final int listPos) {
        if (this.summary != null) {
            this.summary.setListPos(listPos);
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        throw new IllegalStateException("unsupported method");
    }
}
