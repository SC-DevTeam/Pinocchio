package com.scdevteam.maps.mappers;

public abstract class Mapper {
    public enum MapValueType {
        COMPONENT, INT32, LONG, RRSINT32, STRING, BOOLEAN, TAG
    }
    public class MapPoint {
        private final String mName;
        private MapValueType mMapValue;
        private Mapper mComponentMapPoint;

        public MapPoint(String name, MapValueType mapValue) {
            mName = name;
            mMapValue = mapValue;
        }

        public MapPoint(String name, Mapper componentMapPoint) {
            mName = name;
            mMapValue = MapValueType.COMPONENT;
            mComponentMapPoint = componentMapPoint;
        }

        public String getName(int index) {
            if (mName.isEmpty()) {
                return "Unknown" + index;
            }
            return mName;
        }

        public MapValueType getMapValue() {
            return mMapValue;
        }

        public Mapper getComponent() {
            return mComponentMapPoint;
        }
    }

    public abstract MapPoint[] getMapPoints();
}
