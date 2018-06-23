package com.scdevteam.messages.cr.server;

import com.scdevteam.messages.cr.components.CardListComponent;
import com.scdevteam.messages.cr.components.UnknownComponent;
import com.scdevteam.proto.Mapper;
import com.scdevteam.messages.cr.components.DeckComponent;

public class OwnHomeData extends Mapper {
    @Override
    public MapPoint[] getMapPoints() {
        return new MapPoint[] {
                new MapPoint("Account ID", MapValueType.TAG),
                new MapPoint("ECT Seed", MapValueType.RRSINT32),
                new MapPoint(MapValueType.RRSINT32),
                new MapPoint("Tick1", MapValueType.RRSINT32),
                new MapPoint("Tick2", MapValueType.RRSINT32),
                new MapPoint("UnknownTimestamp", MapValueType.RRSINT32),
                new MapPoint(MapValueType.RRSINT32),
                new MapPoint("Decks", new DeckComponent(), ComponentLength.RRSINT32()),
                new MapPoint("Current Deck", new CardListComponent(), ComponentLength.FIXED(8)),
                new MapPoint(MapValueType.BYTE),
                new MapPoint("Unused Cards", new CardListComponent(), ComponentLength.RRSINT32()),
                new MapPoint("Last Deck Update", MapValueType.RRSINT32),
                new MapPoint(MapValueType.BYTE),
                new MapPoint(MapValueType.BYTE),
                new MapPoint(MapValueType.BYTE),
                new MapPoint("Unknown Component", new UnknownComponent(), ComponentLength.FIXED(8)),
        };
    }
}
