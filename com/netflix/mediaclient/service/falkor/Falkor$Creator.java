// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.branches.FalkorVideo;
import com.netflix.model.branches.FalkorSuggestion;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.model.branches.FalkorIrisNotification;
import com.netflix.model.branches.FalkorSeason;
import com.netflix.model.branches.FalkorScene;
import com.netflix.model.branches.FalkorPerson;
import com.netflix.model.branches.FalkorKidsCharacter;
import com.netflix.model.branches.FalkorExpiringContent;
import com.netflix.model.branches.FalkorEvidenceList;
import com.netflix.model.branches.FalkorEpisode;
import com.netflix.model.leafs.DiscoverySummary;
import com.netflix.model.branches.FalkorBillboardData;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.UnsummarizedList;
import com.netflix.model.leafs.TrackableListSummary;
import com.netflix.model.branches.SummarizedList;
import com.netflix.model.leafs.SearchTrackableListSummary;
import com.netflix.falkor.Ref;
import com.netflix.model.branches.PostPlayMap;
import com.netflix.model.branches.MementoVideoSwatch;
import com.netflix.model.leafs.LoLoMoSummary;
import com.netflix.model.leafs.ListOfMoviesSummary;
import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.falkor.Func;

public class Falkor$Creator
{
    public static final Func<BillboardSummary> BillboardSummary;
    public static final Func<ListOfMoviesSummary> ListOfMoviesSummary;
    public static final Func<LoLoMoSummary> LoLoMoSummary;
    public static final Func<MementoVideoSwatch> MementoVideoSwatch;
    public static final Func<PostPlayMap> PostPlayMap;
    public static final Func<Ref> Ref;
    public static Func<SearchTrackableListSummary> SearchTrackableListSummary;
    public static final Func<SummarizedList<Ref, LoLoMoSummary>> SummarizedListOfLoLoMoSummaryRefs;
    public static final Func<SummarizedList<Ref, ListOfMoviesSummary>> SummarizedListOfMovieRefs;
    public static final Func<SummarizedList<Ref, SearchTrackableListSummary>> SummarizedListOfSearchResults;
    public static final Func<TrackableListSummary> TrackableListSummary;
    public static final Func<UnsummarizedList<Ref>> UnsummarizedListOfRef;
    
    static {
        Ref = new Falkor$Creator$1();
        TrackableListSummary = new Falkor$Creator$2();
        UnsummarizedListOfRef = new Falkor$Creator$3();
        ListOfMoviesSummary = new Falkor$Creator$4();
        SummarizedListOfMovieRefs = new Falkor$Creator$5();
        LoLoMoSummary = new Falkor$Creator$6();
        SummarizedListOfLoLoMoSummaryRefs = new Falkor$Creator$7();
        SummarizedListOfSearchResults = new Falkor$Creator$8();
        PostPlayMap = new Falkor$Creator$9();
        MementoVideoSwatch = new Falkor$Creator$10();
        Falkor$Creator.SearchTrackableListSummary = new Falkor$Creator$11();
        BillboardSummary = new Falkor$Creator$23();
    }
    
    public static Func<FalkorActorStill> FalkorActorStill(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$17(modelProxy);
    }
    
    public static Func<FalkorBillboardData> FalkorBillboardData() {
        return new Falkor$Creator$24();
    }
    
    public static Func<SummarizedList<Ref, DiscoverySummary>> FalkorDiscoveryList(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$27(modelProxy);
    }
    
    public static Func<DiscoverySummary> FalkorDiscoverySummary(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$26();
    }
    
    public static Func<FalkorEpisode> FalkorEpisode(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$14(modelProxy);
    }
    
    public static Func<FalkorEvidenceList<Ref>> FalkorEvidenceList(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$13();
    }
    
    public static Func<FalkorExpiringContent> FalkorExpiringContent(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$18(modelProxy);
    }
    
    public static Func<FalkorKidsCharacter> FalkorKidsCharacter(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$20(modelProxy);
    }
    
    public static Func<FalkorPerson> FalkorPerson(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$16(modelProxy);
    }
    
    public static Func<FalkorScene> FalkorScene(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$25(modelProxy);
    }
    
    public static Func<FalkorSeason> FalkorSeason(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$15(modelProxy);
    }
    
    public static Func<FalkorIrisNotification> FalkorSocialNotifications(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$21(modelProxy);
    }
    
    public static SummarizedList<Ref, IrisNotificationsListSummary> FalkorSocialNotificationsList(final ModelProxy<? extends BranchNode> modelProxy) {
        return new SummarizedList<Ref, IrisNotificationsListSummary>(Falkor$Creator.Ref, FalkorSocialNotificationsListSummary(modelProxy));
    }
    
    public static Func<IrisNotificationsListSummary> FalkorSocialNotificationsListSummary(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$22();
    }
    
    public static Func<FalkorSuggestion> FalkorSuggestion(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$19(modelProxy);
    }
    
    public static Func<FalkorVideo> FalkorVideo(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$12(modelProxy);
    }
}
