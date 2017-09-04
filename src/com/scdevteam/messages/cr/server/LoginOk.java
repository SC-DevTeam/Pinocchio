package com.scdevteam.messages.cr.server;

import com.scdevteam.proto.Mapper;

public class LoginOk extends Mapper {

    @Override
    public MapPoint[] getMapPoints() {
        return new MapPoint[] {
                new MapPoint("User ID", MapValueType.TAG),
                new MapPoint("Home ID", MapValueType.TAG),
                new MapPoint("User token", MapValueType.STRING),
                new MapPoint("GameCenter ID", MapValueType.STRING),
                new MapPoint("Facebook ID", MapValueType.STRING),
                new MapPoint("Major version", MapValueType.RRSINT32),
                new MapPoint("Build version", MapValueType.RRSINT32),
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