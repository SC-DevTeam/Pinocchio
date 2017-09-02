package com.scdevteam.messages.cr.components;

import com.scdevteam.proto.Mapper;

public class CardComponent extends Mapper {
    @Override
    public MapPoint[] getMapPoints() {
        return new Mapper.MapPoint[] {
                new MapPoint("card", MapValueType.RRSINT32)
        };
    }
}
