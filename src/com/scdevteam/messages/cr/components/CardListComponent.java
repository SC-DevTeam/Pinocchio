package com.scdevteam.messages.cr.components;

import com.scdevteam.proto.Mapper;

public class CardListComponent extends Mapper {
    @Override
    public MapPoint[] getMapPoints() {
        return new Mapper.MapPoint[] {
                new MapPoint(MapValueType.BYTE),
                new MapPoint("ID", MapValueType.RRSINT32),
                new MapPoint("Level", MapValueType.BYTE),
                new MapPoint("Rarity", MapValueType.RRSINT32),
                new MapPoint("Number Available", MapValueType.RRSINT32),
                new MapPoint(MapValueType.RRSINT32),
                new MapPoint(MapValueType.RRSINT32)
        };
    }
}