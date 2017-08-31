package com.scdevteam.maps.mappers.cr.server;

import com.scdevteam.maps.mappers.Mapper;
import com.scdevteam.maps.mappers.cr.components.DeckComponent;

public class OwnHomeData extends Mapper {
    @Override
    public MapPoint[] getMapPoints() {
        return new MapPoint[] {
                new MapPoint("Account ID", MapValueType.TAG),
                new MapPoint("ECT Seed", MapValueType.RRSINT32),
                new MapPoint(MapValueType.RRSINT32),
                new MapPoint(MapValueType.RRSINT32),
                new MapPoint(MapValueType.RRSINT32),
                new MapPoint(MapValueType.RRSINT32),
                new MapPoint(MapValueType.RRSINT32),
                new MapPoint("Decks", new DeckComponent()),
        };
    }
}
