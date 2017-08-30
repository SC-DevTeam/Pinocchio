package com.scdevteam.maps.mappers.cr.components;

import com.scdevteam.maps.mappers.Mapper;

public class CardComponent extends Mapper {
    @Override
    public MapPoint[] getMapPoints() {
        return new Mapper.MapPoint[] {
                new MapPoint("card", MapValueType.RRSINT32)
        };
    }
}
