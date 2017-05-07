// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

public final class GamesContract
{
    public interface AccountMetadataColumns
    {
        public static final String[] XK = { "account_name", "external_player_id", "match_sync_token", "last_package_scan_timestamp", "quest_sync_token", "quest_sync_metadata_token", "request_sync_token", "xp_sync_token", "cover_photo_image_id", "cover_photo_image_uri", "cover_photo_image_url" };
    }
    
    public interface AchievementDefinitionsColumns
    {
        public static final String[] XK = { "game_id", "external_achievement_id", "type", "name", "description", "unlocked_icon_image_id", "unlocked_icon_image_uri", "unlocked_icon_image_url", "revealed_icon_image_id", "revealed_icon_image_uri", "revealed_icon_image_url", "total_steps", "formatted_total_steps", "initial_state", "sorting_rank", "definition_xp_value" };
    }
    
    public interface AchievementInstancesColumns
    {
        public static final String[] XK = { "definition_id", "player_id", "state", "current_steps", "formatted_current_steps", "last_updated_timestamp", "instance_xp_value" };
    }
    
    public interface AchievementPendingOpsColumns
    {
        public static final String[] XK = { "client_context_id", "external_achievement_id", "achievement_type", "new_state", "steps_to_increment", "min_steps_to_set", "external_game_id", "external_player_id" };
    }
    
    public interface AclsColumns
    {
        public static final String[] XK = { "specified_by_user", "pacl" };
    }
    
    public interface ApplicationSessionColumns
    {
        public static final String[] XK = { "client_context_id", "end_time", "external_game_id", "session_id", "start_time" };
    }
    
    public interface ClientContextsColumns
    {
        public static final String[] XK = { "package_name", "package_uid", "account_name" };
    }
    
    public interface ContactSettingsColumns
    {
        public static final String[] XK = { "mobile_notifications_enabled", "match_notifications_enabled", "quest_notifications_enabled", "request_notifications_enabled", "level_notifications_enabled" };
    }
    
    public interface EventDefinitionColumns
    {
        public static final String[] XK = { "event_definitions_game_id", "external_event_id", "name", "description", "visibility", "icon_image_id", "icon_image_uri", "icon_image_url", "sorting_rank" };
    }
    
    public interface EventInstancesColumns
    {
        public static final String[] XK = { "definition_id", "player_id", "formatted_value", "last_updated_timestamp" };
    }
    
    public interface EventPendingOpsColumns
    {
        public static final String[] XK = { "client_context_id", "instance_id", "window_start_time", "window_end_time", "increment", "request_id" };
    }
    
    public interface ExperienceEventColumns
    {
        public static final String[] XK = { "external_experience_id", "game_id", "created_timestamp", "current_xp", "xp_earned", "display_title", "display_description", "display_string", "type", "icon_id", "icon_url", "icon_uri", "newLevel" };
    }
    
    public interface GameBadgesColumns
    {
        public static final String[] XK = { "badge_game_id", "badge_type", "badge_title", "badge_description", "badge_icon_image_id", "badge_icon_image_uri" };
    }
    
    public interface GameInstancesColumns
    {
        public static final String[] XK = { "instance_game_id", "real_time_support", "turn_based_support", "platform_type", "instance_display_name", "package_name", "piracy_check", "installed", "preferred" };
        public static final String[] XL = { "installed", "package_name", "real_time_support", "turn_based_support" };
    }
    
    public interface GameSearchSuggestionsColumns
    {
        public static final String[] XK = { "suggestion" };
    }
    
    public interface GamesColumns
    {
        public static final String[] XK = { "external_game_id", "display_name", "primary_category", "secondary_category", "developer_name", "game_description", "game_icon_image_id", "game_icon_image_uri", "game_icon_image_url", "game_hi_res_image_id", "game_hi_res_image_uri", "game_hi_res_image_url", "featured_image_id", "featured_image_uri", "featured_image_url", "play_enabled_game", "last_played_server_time", "last_connection_local_time", "last_synced_local_time", "metadata_version", "target_instance", "gameplay_acl_status", "achievement_total_count", "leaderboard_count", "muted", "identity_sharing_confirmed", "snapshots_enabled", "theme_color" };
    }
    
    public interface ImagesColumns
    {
        public static final String[] XK = { "url", "local", "filesize", "download_timestamp" };
    }
    
    public interface InvitationsColumns
    {
        public static final String[] XK = { "game_id", "external_invitation_id", "external_inviter_id", "creation_timestamp", "last_modified_timestamp", "description", "type", "variant", "has_automatch_criteria", "automatch_min_players", "automatch_max_players", "inviter_in_circles" };
    }
    
    public interface LeaderboardInstancesColumns
    {
        public static final String[] XK = { "leaderboard_id", "timespan", "collection", "player_raw_score", "player_display_score", "player_rank", "player_display_rank", "player_score_tag", "total_scores", "top_page_token_next", "window_page_token_prev", "window_page_token_next" };
    }
    
    public interface LeaderboardScoresColumns
    {
        public static final String[] XK = { "instance_id", "page_type", "player_id", "default_display_name", "default_display_image_id", "default_display_image_uri", "default_display_image_url", "rank", "display_rank", "raw_score", "display_score", "achieved_timestamp", "score_tag" };
    }
    
    public interface LeaderboardsColumns
    {
        public static final String[] XK = { "game_id", "external_leaderboard_id", "name", "board_icon_image_id", "board_icon_image_uri", "board_icon_image_url", "sorting_rank", "score_order" };
    }
    
    public interface MatchesColumns
    {
        public static final String[] XK = { "game_id", "external_match_id", "creator_external", "creation_timestamp", "last_updater_external", "last_updated_timestamp", "pending_participant_external", "data", "status", "description", "version", "variant", "notification_text", "user_match_status", "has_automatch_criteria", "automatch_min_players", "automatch_max_players", "automatch_bit_mask", "automatch_wait_estimate_sec", "rematch_id", "match_number", "previous_match_data", "upsync_required", "description_participant_id" };
    }
    
    public interface MatchesPendingOpsColumns
    {
        public static final String[] XK = { "client_context_id", "type", "external_game_id", "external_match_id", "pending_participant_id", "version", "is_turn", "results" };
    }
    
    public interface MilestoneColumns
    {
        public static final String[] XK = { "completion_reward_data", "event_instance_id", "external_milestone_id", "initial_value", "quest_id", "milestones_sorting_rank", "milestone_state", "target_value" };
    }
    
    public interface NotificationsColumns
    {
        public static final String[] XK = { "notification_id", "game_id", "external_sub_id", "type", "image_id", "ticker", "title", "text", "coalesced_text", "timestamp", "acknowledged", "alert_level" };
    }
    
    public interface PageType
    {
    }
    
    public interface ParticipantsColumns
    {
        public static final String[] XK = { "match_id", "invitation_id", "external_participant_id", "player_id", "default_display_image_id", "default_display_image_url", "default_display_hi_res_image_id", "default_display_hi_res_image_url", "default_display_name", "player_status", "client_address", "result_type", "placing", "connected", "capabilities" };
    }
    
    public interface PlayerLevelColumns
    {
        public static final String[] XK = { "level_value", "level_min_xp", "level_max_xp", "version" };
    }
    
    public interface PlayersColumns
    {
        public static final String[] XK = { "external_player_id", "profile_icon_image_id", "profile_hi_res_image_id", "profile_icon_image_uri", "profile_icon_image_url", "profile_hi_res_image_uri", "profile_hi_res_image_url", "profile_name", "last_updated", "is_in_circles", "has_all_public_acls", "is_profile_visible", "current_xp_total", "current_level", "current_level_min_xp", "current_level_max_xp", "next_level", "next_level_max_xp", "last_level_up_timestamp", "player_title", "most_recent_external_game_id", "most_recent_game_name", "most_recent_activity_timestamp", "most_recent_game_icon_id", "most_recent_game_icon_uri", "most_recent_game_hi_res_id", "most_recent_game_hi_res_uri", "most_recent_game_featured_id", "most_recent_game_featured_uri" };
    }
    
    public interface ProfileSettingsColumns
    {
        public static final String[] XK = { "profile_visible", "profile_visibility_explicitly_set" };
    }
    
    public interface QuestsColumns
    {
        public static final String[] XK = { "accepted_ts", "quest_banner_image_id", "quest_banner_image_uri", "quest_banner_image_url", "quest_description", "quest_end_ts", "external_quest_id", "game_id", "quest_icon_image_id", "quest_icon_image_uri", "quest_icon_image_url", "quest_last_updated_ts", "quest_name", "notified", "notification_ts", "quest_start_ts", "quest_state", "quest_type" };
    }
    
    public interface RequestPendingOpsColumns
    {
        public static final String[] XK = { "client_context_id", "external_request_id", "external_game_id" };
    }
    
    public interface RequestRecipientsColumns
    {
        public static final String[] XK = { "request_id", "player_id", "recipient_status" };
    }
    
    public interface RequestSummaryColumns
    {
        public static final String[] XK = { "wish_count", "gift_count", "player_count" };
    }
    
    public interface RequestsColumns
    {
        public static final String[] XK = { "external_request_id", "game_id", "sender_id", "data", "type", "creation_timestamp", "expiration_timestamp", "status" };
    }
    
    public interface SnapshotColumns
    {
        public static final String[] XK = { "game_id", "owner_id", "external_snapshot_id", "drive_resolved_id_string", "drive_resource_id_string", "cover_icon_image_id", "cover_icon_image_uri", "cover_icon_image_url", "cover_icon_image_width", "cover_icon_image_height", "title", "description", "last_modified_timestamp", "duration", "unique_name", "visible" };
    }
}
