package com.scdevteam.messages.cr.server;

import com.scdevteam.proto.Mapper;

public class LoginOk extends Mapper {
    /*
        {"name": "userId", "type": "LONG"},
    {"name": "homeId", "type": "LONG"},
    {"name": "userToken", "type": "STRING"},
    {"name": "gameCenterId", "type": "STRING"},
    {"name": "facebookId", "type": "STRING"},
    {"name": "serverMajorVersion", "type": "RRSINT32"},
    {"name": "serverBuild", "type": "RRSINT32"},
    {"name": "serverBuild", "type": "RRSINT32"},
    {"name": "contentVersion", "type": "RRSINT32"},
    {"name": "environment", "type": "STRING"},
    {"name": "sessionCount", "type": "RRSINT32"},
    {"name": "playTimeSeconds", "type": "RRSINT32"},
    {"name": "daysSinceStartedPlaying", "type": "RRSINT32"},
    {"name": "facebookAppId", "type": "STRING"},
    {"name": "serverTime", "type": "STRING"},
    {"name": "accountCreatedDate", "type": "STRING"},
    {"type": "RRSINT32"},
    {"name": "googleServiceId", "type": "STRING"},
    {"type": "STRING"},
    {"type": "STRING"},
    {"name": "region", "type": "STRING"},
    {"name": "contentURL", "type": "STRING"},
    {"name": "eventAssetsURL", "type": "STRING"},
    {"type": "BYTE"}

     */
    @Override
    public MapPoint[] getMapPoints() {
        return new MapPoint[] {
                new MapPoint("User ID", MapValueType.TAG),
                new MapPoint("Home ID", MapValueType.RRSINT32),
                new MapPoint("User token", MapValueType.STRING),
                new MapPoint("GameCenter ID", MapValueType.STRING),
                new MapPoint("Facebook ID", MapValueType.STRING),
                new MapPoint("Major version", MapValueType.RRSINT32),
                new MapPoint("Minor version", MapValueType.RRSINT32),
                new MapPoint("Build version", MapValueType.RRSINT32),
                new MapPoint("Content version", MapValueType.RRSINT32),
                new MapPoint("Environment", MapValueType.STRING),
                new MapPoint("Session count", MapValueType.RRSINT32),
                new MapPoint("Play time (seconds)", MapValueType.RRSINT32),
                new MapPoint("Days since start playing", MapValueType.RRSINT32),
                new MapPoint("Facebook AppID", MapValueType.STRING),
                new MapPoint("Server time", MapValueType.STRING),
                new MapPoint("Account creation date", MapValueType.STRING),
                new MapPoint(MapValueType.RRSINT32),
                new MapPoint("Google service ID", MapValueType.STRING),
                new MapPoint(MapValueType.STRING),
                new MapPoint(MapValueType.STRING),
                new MapPoint("Region", MapValueType.STRING),
                new MapPoint("Content URL", MapValueType.STRING),
                new MapPoint("Event Assets URL", MapValueType.STRING),
                new MapPoint(MapValueType.BYTE),
        };
    }
}