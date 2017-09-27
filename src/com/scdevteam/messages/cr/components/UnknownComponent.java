package com.scdevteam.messages.cr.components;

import com.scdevteam.proto.Mapper;

public class UnknownComponent extends Mapper {
    @Override
    public MapPoint[] getMapPoints() {
        return new MapPoint[] {
                new MapPoint(MapValueType.BYTE),
                new MapPoint(MapValueType.BYTE),
                new MapPoint(MapValueType.INT32),
                new MapPoint(MapValueType.RRSINT32),
        };
    }
}