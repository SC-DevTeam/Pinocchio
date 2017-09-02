package com.scdevteam.messages.cr.server;

import com.scdevteam.proto.Mapper;
import com.scdevteam.messages.cr.components.DeckComponent;

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
