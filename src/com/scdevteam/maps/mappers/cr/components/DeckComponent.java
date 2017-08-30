package com.scdevteam.maps.mappers.cr.components;

import com.scdevteam.maps.mappers.Mapper;

public class DeckComponent extends Mapper {
    @Override
    public Mapper.MapPoint[] getMapPoints() {
        return new MapPoint[] {
                new MapPoint("deck", new CardComponent())
        };
    }
}
