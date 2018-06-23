package com.scdevteam.messages.cr.components;

import com.scdevteam.proto.Mapper;

public class DeckComponent extends Mapper {
    @Override
    public MapPoint[] getMapPoints() {
        return new MapPoint[] {
                new MapPoint("deck", new CardComponent(), ComponentLength.RRSINT32())
        };
    }
}
