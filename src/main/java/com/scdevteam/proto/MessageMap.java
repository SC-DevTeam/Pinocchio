package com.scdevteam.proto;

import com.scdevteam.Utils;
import com.scdevteam.WriterUtils;

import java.nio.ByteBuffer;

public class MessageMap {
    public static final short CLIENT_HELLO = 10100;
    public static final short LOGIN = 10101;
    public static final short CONNECTION_INFO = 10107;
    public static final short PING = 10108;
    public static final short AUTHENTICATION_CHECK = 10112;
    public static final short SET_DEVICE_TOKEN = 10113;
    public static final short RESET_ACCOUNT = 10116;
    public static final short REPORT_USER = 10117;
    public static final short ACCOUNT_SWITCHED = 10118;
    public static final short ACCOUNT_UNLOCK = 10121;
    public static final short APPLE_BILLING_REQUEST = 10150;
    public static final short GOOGLE_BILLING_REQUEST = 10151;
    public static final short KUNLUN_BILLING_REQUEST = 10159;
    public static final short NAME_CHANGE = 10212;
    public static final short ASK_FOR_PLAYING_GAMECENTER_FRIENDS = 10512;
    public static final short ASK_FOR_PLAYING_FACEBOOK_FRIENDS = 10513;
    public static final short INBOX_ASK = 10905;
    public static final short UNBIND_FACEBOOK_ACCOUNT = 12211;
    public static final short REQUEST_SECTOR_STATE = 12903;
    public static final short SECTOR_COMMAND = 12904;
    public static final short GET_CURRENT_BATTLE_REPLAY_DATA = 12905;
    public static final short SEND_BATTLE_EVENT = 12951;
    public static final short HOME_ASK_DATA_OWN = 14101;
    public static final short CLIENT_COMMANDS = 14102;
    public static final short MATCHMAKE_START = 14104;
    public static final short HOME_LOGIC_STOPPED = 14105;
    public static final short MATCHMAKE_CANCEL = 14107;
    public static final short CHANGE_HOME_NAME = 14108;
    public static final short HOME_ASK_DATA = 14113;
    public static final short HOME_BATTLE_REPLAY = 14114;
    public static final short HOME_BATTLE_REPLAY_VIEWED = 14117;
    public static final short ACCEPT_CHALLENGE = 14120;
    public static final short CANCEL_CHALLENGE_MESSAGE = 14123;
    public static final short BIND_FACEBOOK_ACCOUNT = 14201;
    public static final short BIND_GAMECENTER_ACCOUNT = 14212;
    public static final short BIND_GOOGLE_SERVICE_ACCOUNT = 14262;
    public static final short CLAN_CREATE = 14301;
    public static final short CLAN_ASK_DATA = 14302;
    public static final short CLAN_ASK_JOINABLE = 14303;
    public static final short ASK_FOR_ALLIANCE_STREAM = 14304;
    public static final short CLAN_JOIN = 14305;
    public static final short CHANGE_ALLIANCE_MEMBER_ROLE = 14306;
    public static final short KICK_ALLIANCE_MEMBER = 14307;
    public static final short CLAN_LEAVE = 14308;
    public static final short DONATE_ALLIANCE_UNIT = 14310;
    public static final short CLAN_CHAT_MESSAGE = 14315;
    public static final short CHANGE_ALLIANCE_SETTINGS = 14316;
    public static final short REQUEST_JOIN_ALLIANCE = 14317;
    public static final short SELECT_SPELLS_FROM_CO_OPEN = 14318;
    public static final short OFFER_CHEST_FOR_CO_OPEN = 14319;
    public static final short RESPOND_TO_ALLIANCE_JOIN_REQUEST = 14321;
    public static final short SEND_ALLIANCE_INVITATION = 14322;
    public static final short JOIN_ALLIANCE_USING_INVITATION = 14323;
    public static final short CLAN_SEARCH = 14324;
    public static final short SEND_ALLIANCE_MAIL = 14330;
    public static final short ASK_FOR_ALLIANCE_RANKING_LIST = 14401;
    public static final short ASK_FOR_TV_CONTENT = 14402;
    public static final short ASK_FOR_AVATAR_RANKING_LIST = 14403;
    public static final short ASK_FOR_AVATAR_LOCAL_RANKING = 14404;
    public static final short ASK_FOR_AVATAR_STREAM = 14405;
    public static final short ASK_FOR_BATTLE_REPLAY_STREAM = 14406;
    public static final short ASK_FOR_LAST_AVATAR_TOURNAMENT_RESULTS = 14408;
    public static final short REMOVE_AVATAR_STREAM_ENTRY = 14418;
    public static final short NAME_CHECK = 14600;
    public static final short LOGIC_DEVICE_LINK_CODE_STATUS = 16000;
    public static final short TOURNAMENT_ASK_JOINABLE = 16103;
    public static final short SEARCH_TOURNAMENTS = 16113;

    // Server messages
    public static final short SERVER_HELLO = 20100;
    public static final short LOGIN_FAILED = 20103;
    public static final short LOGIN_OK = 20104;
    public static final short FRIEND_LIST = 20105;
    public static final short PONG = 20108;
    public static final short CHAT_ACCOUNT_BAN_STATUS = 20118;
    public static final short BILLING_REQUEST_FAILED = 20121;
    public static final short UNLOCK_ACCOUNT_OK = 20132;
    public static final short ACCOUNT_UNLOCK_FAILED = 20133;
    public static final short APPLE_BILLING_PROCESSED_BY_SERVER = 20151;
    public static final short GOOGLE_BILLING_PROCESSED_BY_SERVER = 20152;
    public static final short KUNLUN_BILLING_PROCESSED_BY_SERVER = 20156;
    public static final short SHUTDOWN_STARTED = 20161;
    public static final short AVATAR_NAME_CHANGE_FAILED = 20205;
    public static final short AVATAR_IN_GAME_STATUS_UPDATED = 20206;
    public static final short CLAN_ONLINE_UPDATE = 20207;
    public static final short BATTLE_RESULT = 20225;
    public static final short NAME_CHECK_OK = 20300;
    public static final short OPPONENT_LEFT_MATCH_NOTIFICATION = 20801;
    public static final short OPPONENT_REJOINS_MATCH_NOTIFICATION = 20802;
    public static final short SECTOR_HEARBEAT = 21902;
    public static final short SECTOR_STATE = 21903;
    public static final short BATTLE_EVENT = 22952;
    public static final short PVP_MATCHMAKE_NOTIFICATION = 22957;
    public static final short HOME_DATA_OWN = 24101;
    public static final short OWN_AVATAR_DATA = 24102;
    public static final short OUT_OF_SYNC = 24104;
    public static final short STOP_HOME_LOGIC = 24106;
    public static final short MATCHMAKE_INFO = 24107;
    public static final short MATCHMAKE_FAILED = 24108;
    public static final short SERVER_COMMAND = 24111;
    public static final short UDP_CONNECTION_INFO = 24112;
    public static final short HOME_DATA_VISITED = 24113;
    public static final short HOME_BATTLE_REPLAY_DATA = 24114;
    public static final short SERVER_ERROR = 24115;
    public static final short HOME_BATTLE_REPLAY_FAILED = 24116;
    public static final short CHALLENGE_FAILED = 24121;
    public static final short CANCEL_CHALLENGE_DONE = 24124;
    public static final short MATCHMAKE_CANCEL_OK = 24125;
    public static final short FACEBOOK_ACCOUNT_BOUND = 24201;
    public static final short FACEBOOK_ACCOUNT_ALREADY_BOUND = 24202;
    public static final short GAMECENTER_ACCOUNT_ALREADY_BOUND = 24212;
    public static final short FACEBOOK_ACCOUNT_UNBOUND = 24213;
    public static final short GOOGLE_SERVICE_ACCOUNT_BOUND = 24261;
    public static final short GOOGLE_SERVICE_ACCOUNT_ALREADY_BOUND = 24262;
    public static final short CLAN_DATA = 24301;
    public static final short ALLIANCE_JOIN_FAILED = 24302;
    public static final short ALLIANCE_JOIN_OK = 24303;
    public static final short CLAN_JOINABLE_RESPONSE = 24304;
    public static final short ALLIANCE_LEAVE_OK = 24305;
    public static final short CHANGE_ALLIANCE_MEMBER_ROLE_OK = 24306;
    public static final short KICK_ALLIANCE_MEMBER_OK = 24307;
    public static final short CLAN_MEMBER_ADD = 24308;
    public static final short CLAN_MEMBER_REMOVE = 24309;
    public static final short ALLIANCE_LIST = 24310;
    public static final short ALLIANCE_STREAM = 24311;
    public static final short CLAN_EVENT = 24312;
    public static final short ALLIANCE_STREAM_ENTRY_REMOVED = 24318;
    public static final short ALLIANCE_JOIN_REQUEST_OK = 24319;
    public static final short ALLIANCE_JOIN_REQUEST_FAILED = 24320;
    public static final short ALLIANCE_INVITATION_SEND_FAILED = 24321;
    public static final short ALLIANCE_INVITATION_SENT_OK = 24322;
    public static final short ALLIANCE_FULL_ENTRY_UPDATE = 24324;
    public static final short ALLIANCE_CREATE_FAILED = 24332;
    public static final short ALLIANCE_CHANGE_FAILED = 24333;
    public static final short ALLIANCE_RANKING_LIST = 24401;
    public static final short ALLIANCE_LOCAL_RANKING_LIST = 24402;
    public static final short AVATAR_RANKING_LIST = 24403;
    public static final short AVATAR_LOCAL_RANKING_LIST = 24404;
    public static final short ROYAL_TV_CONTENT = 24405;
    public static final short LAST_AVATAR_TOURNAMENT_RESULTS = 24407;
    public static final short AVATAR_STREAM = 24411;
    public static final short AVATAR_STREAM_ENTRY = 24412;
    public static final short BATTLE_REPORT_STREAM = 24413;
    public static final short AVATAR_STREAM_ENTRY_REMOVED = 24418;
    public static final short INBOX_LIST = 24445;
    public static final short INBOX_GLOBAL = 24446;
    public static final short INBOX_COUNT = 24447;
    public static final short DISCONNECTED = 25892;
    public static final short LOGIC_DEVICE_LINK_CODE_RESPONSE = 26002;
    public static final short LOGIC_DEVICE_LINK_NEW_DEVICE_LINKED = 26003;
    public static final short LOGIC_DEVICE_LINK_CODE_DEACTIVATED = 26004;
    public static final short LOGIC_DEVICE_LINK_RESPONSE = 26005;
    public static final short LOGIC_DEVICE_LINK_DONE = 26007;
    public static final short LOGIC_DEVICE_LINK_ERROR = 26008;
    public static final short TOURNAMENT_LIST = 26101;
    public static final short TOURNAMENT_LIST_SEND = 26108;

    public static String getMessageType(int messageID) {
        switch (messageID) {
            // Client Messages
            case 10100: return "ClientHello";
            case 10101: return "Login";
            case 10107: return "ClientCapabilities";
            case 10108: return "KeepAlive";
            case 10112: return "AuthenticationCheck";
            case 10113: return "SetDeviceToken";
            case 10116: return "ResetAccount";
            case 10117: return "ReportUser";
            case 10118: return "AccountSwitched";
            case 10121: return "UnlockAccount";
            case 10150: return "AppleBillingRequest";
            case 10151: return "GoogleBillingRequest";
            case 10159: return "KunlunBillingRequest";
            case 10212: return "ChangeAvatarName";
            case 10512: return "AskForPlayingGamecenterFriends";
            case 10513: return "AskForPlayingFacebookFriends";
            case 10905: return "InboxOpened";
            case 12211: return "UnbindFacebookAccount";
            case 12903: return "RequestSectorState";
            case 12904: return "SectorCommand";
            case 12905: return "GetCurrentBattleReplayData";
            case 12951: return "SendBattleEvent";
            case 14101: return "GoHome";
            case 14102: return "EndClientTurn";
            case 14104: return "StartMission";
            case 14105: return "HomeLogicStopped";
            case 14107: return "CancelMatchmake";
            case 14108: return "ChangeHomeName";
            case 14113: return "VisitHome";
            case 14114: return "HomeBattleReplay";
            case 14117: return "HomeBattleReplayViewed";
            case 14120: return "AcceptChallenge";
            case 14123: return "CancelChallengeMessage";
            case 14201: return "BindFacebookAccount";
            case 14212: return "BindGamecenterAccount";
            case 14262: return "BindGoogleServiceAccount";
            case 14301: return "CreateAlliance";
            case 14302: return "AskForAllianceData";
            case 14303: return "AskForJoinableAlliancesList";
            case 14304: return "AskForAllianceStream";
            case 14305: return "JoinAlliance";
            case 14306: return "ChangeAllianceMemberRole";
            case 14307: return "KickAllianceMember";
            case 14308: return "LeaveAlliance";
            case 14310: return "DonateAllianceUnit";
            case 14315: return "ChatToAllianceStream";
            case 14316: return "ChangeAllianceSettings";
            case 14317: return "RequestJoinAlliance";
            case 14318: return "SelectSpellsFromCoOpen";
            case 14319: return "OfferChestForCoOpen";
            case 14321: return "RespondToAllianceJoinRequest";
            case 14322: return "SendAllianceInvitation";
            case 14323: return "JoinAllianceUsingInvitation";
            case 14324: return "SearchAlliances";
            case 14330: return "SendAllianceMail";
            case 14401: return "AskForAllianceRankingList";
            case 14402: return "AskForTVContent";
            case 14403: return "AskForAvatarRankingList";
            case 14404: return "AskForAvatarLocalRanking";
            case 14405: return "AskForAvatarStream";
            case 14406: return "AskForBattleReplayStream";
            case 14408: return "AskForLastAvatarTournamentResults";
            case 14418: return "RemoveAvatarStreamEntry";
            case 14600: return "AvatarNameCheckRequest";
            case 16000: return "LogicDeviceLinkCodeStatus";
            case 16103: return "AskForJoinableTournaments";
            case 16113: return "SearchTournaments";

            // Server Messages
            case 20100: return "ServerHello";
            case 20103: return "LoginFailed";
            case 20104: return "LoginOk";
            case 20105: return "FriendList";
            case 20108: return "KeepAliveServer";
            case 20118: return "ChatAccountBanStatus";
            case 20121: return "BillingRequestFailed";
            case 20132: return "UnlockAccountOk";
            case 20133: return "UnlockAccountFailed";
            case 20151: return "AppleBillingProcessedByServer";
            case 20152: return "GoogleBillingProcessedByServer";
            case 20156: return "KunlunBillingProcessedByServer";
            case 20161: return "ShutdownStarted";
            case 20205: return "AvatarNameChangeFailed";
            case 20206: return "AvatarInGameStatusUpdated";
            case 20207: return "AllianceOnlineStatusUpdated";
            case 20225: return "BattleResult";
            case 20300: return "AvatarNameCheckResponse";
            case 20801: return "OpponentLeftMatchNotification";
            case 20802: return "OpponentRejoinsMatchNotification";
            case 21902: return "SectorHearbeat";
            case 21903: return "SectorState";
            case 22952: return "BattleEvent";
            case 22957: return "PvpMatchmakeNotification";
            case 24101: return "OwnHomeData";
            case 24102: return "OwnAvatarData";
            case 24104: return "OutOfSync";
            case 24106: return "StopHomeLogic";
            case 24107: return "MatchmakeInfo";
            case 24108: return "MatchmakeFailed";
            case 24111: return "AvailableServerCommand";
            case 24112: return "UdpConnectionInfo";
            case 24113: return "VisitedHomeData";
            case 24114: return "HomeBattleReplay";
            case 24115: return "ServerError";
            case 24116: return "HomeBattleReplayFailed";
            case 24121: return "ChallengeFailed";
            case 24124: return "CancelChallengeDone";
            case 24125: return "CancelMatchmakeDone";
            case 24201: return "FacebookAccountBound";
            case 24202: return "FacebookAccountAlreadyBound";
            case 24212: return "GamecenterAccountAlreadyBound";
            case 24213: return "FacebookAccountUnbound";
            case 24261: return "GoogleServiceAccountBound";
            case 24262: return "GoogleServiceAccountAlreadyBound";
            case 24301: return "AllianceData";
            case 24302: return "AllianceJoinFailed";
            case 24303: return "AllianceJoinOk";
            case 24304: return "JoinableAllianceList";
            case 24305: return "AllianceLeaveOk";
            case 24306: return "ChangeAllianceMemberRoleOk";
            case 24307: return "KickAllianceMemberOk";
            case 24308: return "AllianceMember";
            case 24309: return "AllianceMemberRemoved";
            case 24310: return "AllianceList";
            case 24311: return "AllianceStream";
            case 24312: return "AllianceStreamEntry";
            case 24318: return "AllianceStreamEntryRemoved";
            case 24319: return "AllianceJoinRequestOk";
            case 24320: return "AllianceJoinRequestFailed";
            case 24321: return "AllianceInvitationSendFailed";
            case 24322: return "AllianceInvitationSentOk";
            case 24324: return "AllianceFullEntryUpdate";
            case 24332: return "AllianceCreateFailed";
            case 24333: return "AllianceChangeFailed";
            case 24401: return "AllianceRankingList";
            case 24402: return "AllianceLocalRankingList";
            case 24403: return "AvatarRankingList";
            case 24404: return "AvatarLocalRankingList";
            case 24405: return "RoyalTVContent";
            case 24407: return "LastAvatarTournamentResults";
            case 24411: return "AvatarStream";
            case 24412: return "AvatarStreamEntry";
            case 24413: return "BattleReportStream";
            case 24418: return "AvatarStreamEntryRemoved";
            case 24445: return "InboxList";
            case 24446: return "InboxGlobal";
            case 24447: return "InboxCount";
            case 25892: return "Disconnected";
            case 26002: return "LogicDeviceLinkCodeResponse";
            case 26003: return "LogicDeviceLinkNewDeviceLinked";
            case 26004: return "LogicDeviceLinkCodeDeactivated";
            case 26005: return "LogicDeviceLinkResponse";
            case 26007: return "LogicDeviceLinkDone";
            case 26008: return "LogicDeviceLinkError";
            case 26101: return "TournamentList";
            default:
                return "Unknown (" + messageID + ")";
        }
    }

    public static MapResult getMap(GameMapper gameMapper, int msgId, byte[] wht) {
        Mapper map = gameMapper.getMap(msgId);
        if (map != null) {
            BuffParser buffParser = new BuffParser(wht, 0);

            StringBuilder r = new StringBuilder();
            int i = 0;
            for (Mapper.MapPoint mapPoint : map.getMapPoints()) {
                populateMapPoint(mapPoint, r, buffParser, i, false);
                i++;
            }

            String remainings = "";
            if (buffParser.hasRemaining()) {
                byte[] rem = buffParser.remaining();

                remainings = Utils.hexDump(rem);
            }

            return new MapResult(r.toString(), remainings);
        }

        return null;
    }

    private static void populateMapPoint(Mapper.MapPoint mapPoint, StringBuilder r,
                                         BuffParser buffParser, int index,
                                         boolean sub) {
        String n = mapPoint.getName(index);
        if (mapPoint.getMapValue() != Mapper.MapValueType.COMPONENT) {
            if (!sub) {
                r.append(WriterUtils.buildGreenBold(n)).append("\n");
            } else {
                r.append("[")
                        .append(WriterUtils.buildCyanBold("*"))
                        .append("] ")
                        .append(WriterUtils.buildYellowBold(n)).append("\n");
            }
        }

        ByteBuffer clone = buffParser.cloneBuffer();
        byte[] d;

        try {
            switch (mapPoint.getMapValue()) {
                case BYTE:
                    d = new byte[1];
                    clone.get(d, 0, 1);

                    r.append(buffParser.readByte())
                            .append(" ")
                            .append(WriterUtils.buildRedBold(Utils.toHexString(d)))
                            .append(WriterUtils.buildCyanBold(" (BYTE)"));
                    break;
                case COMPONENT:
                    if (!sub) {
                        r.append("\n");
                    }
                    int len = 0;
                    switch (mapPoint.getComponentLength().getLengthType()) {
                        case LENGTH_FIXED:
                            len = mapPoint.getComponentLength().getLength();
                            break;
                        case LENGTH_RRSINT:
                            len = buffParser.readRssInt32().val;
                            break;
                        case LENGTH_INT:
                            len = buffParser.readInt();
                            break;
                    }

                    r.append(WriterUtils.buildPurpleBold(n))
                            .append(" (")
                            .append(len)
                            .append(")")
                            .append("\n\n");

                    for (int k = 0; k < len; k++) {
                        int mapLength = mapPoint.getComponent().getMapPoints().length;
                        for (int j=0;j<mapLength;j++) {
                            populateMapPoint(mapPoint.getComponent().getMapPoints()[j],
                                    r, buffParser, j + 1, true);

                            if (j == mapLength - 1) {
                                r.append("----------------")
                                        .append("\n\n");
                            }
                        }
                    }
                    break;
                case INT32:
                    d = new byte[4];
                    clone.get(d, 0, 4);

                    r.append(buffParser.readInt())
                            .append(" ")
                            .append(WriterUtils.buildRedBold(Utils.toHexString(d)))
                            .append(WriterUtils.buildCyanBold(" (INT)"));
                    break;
                case LONG:
                    d = new byte[8];
                    clone.get(d, 0, 8);
                    BuffParser.SLong sLong = buffParser.readLong();
                    r.append(sLong.hi)
                            .append(" ")
                            .append(sLong.lo)
                            .append(" ")
                            .append(WriterUtils.buildRedBold(Utils.toHexString(d)))
                            .append(WriterUtils.buildCyanBold(" (LONG)"));
                    break;
                case RRSINT32:
                    BuffParser.RrsInt rrsInt = buffParser.readRssInt32();

                    d = new byte[rrsInt.len];
                    clone.get(d, 0, rrsInt.len);

                    r.append(rrsInt.val)
                            .append(" ")
                            .append(WriterUtils.buildRedBold(Utils.toHexString(d)))
                            .append(WriterUtils.buildCyanBold(" (RRSINT32)"));
                    break;
                case STRING:
                    BuffParser.SString sString = buffParser.readString();
                    r.append(sString.s)
                            .append(" ")
                            .append(WriterUtils.buildRedBold(Utils.toHexString(sString.toBuff())))
                            .append(WriterUtils.buildCyanBold(" (STRING)"));
                    break;
                case BOOLEAN:
                    d = new byte[1];
                    clone.get(d, 0, 1);
                    r.append(buffParser.readBoolean())
                            .append(" ")
                            .append(WriterUtils.buildRedBold(Utils.toHexString(d)))
                            .append(WriterUtils.buildCyanBold(" (BOOLEAN)"));
                    break;
                case TAG:
                    d = new byte[8];
                    clone.get(d, 0, 8);
                    BuffParser.SLong l = buffParser.readLong();
                    r.append(l.hi)
                            .append(" ")
                            .append(l.lo)
                            .append(" ")
                            .append(l.v)
                            .append(" ")
                            .append(Utils.tagFromId(l))
                            .append(" ")
                            .append(WriterUtils.buildRedBold(Utils.toHexString(d)))
                            .append(WriterUtils.buildCyanBold(" (TAG)"));
                    break;

            }
        } catch (Exception e) {
            r.append("Failed to parse");
        }

        if (mapPoint.getMapValue() != Mapper.MapValueType.COMPONENT) {
            r.append("\n\n");
        }
    }

    public static class MapResult {
        private final String mResult;
        private final String mRemainings;
        public MapResult(String result, String remainings) {
            mResult = result;
            mRemainings = remainings;
        }

        public String getResult() {
            return mResult;
        }

        public String getRemainings() {
            return mRemainings;
        }
    }
}
