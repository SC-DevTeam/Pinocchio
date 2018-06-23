package com.scdevteam.proto;

public abstract class Mapper {
    public enum MapValueType {
        COMPONENT, BYTE, INT32, LONG, RRSINT32, STRING, BOOLEAN, TAG
    }

    public enum ComponentLengthType {
        LENGTH_FIXED, LENGTH_RRSINT, LENGTH_INT
    }

    public static class ComponentLength {
        private final ComponentLengthType mLengthType;
        private final int mLength;

        public static ComponentLength RRSINT32() {
            return new ComponentLength(ComponentLengthType.LENGTH_RRSINT, 0);
        }

        public static ComponentLength INT() {
            return new ComponentLength(ComponentLengthType.LENGTH_RRSINT, 0);
        }

        public static ComponentLength FIXED(int length) {
            return new ComponentLength(ComponentLengthType.LENGTH_FIXED, length);
        }

        private ComponentLength(ComponentLengthType componentLengthType, int length) {
            mLengthType = componentLengthType;
            mLength = length;
        }

        ComponentLengthType getLengthType() {
            return mLengthType;
        }

        public int getLength() {
            return mLength;
        }
    }

    public class MapPoint {
        private final String mName;
        private MapValueType mMapValue;
        private Mapper mComponentMapPoint;
        private ComponentLength mComponentLength;

        public MapPoint(MapValueType mapValue) {
            mName = "";
            mMapValue = mapValue;
        }

        public MapPoint(String name, MapValueType mapValue) {
            mName = name;
            mMapValue = mapValue;
        }

        public MapPoint(String name, Mapper componentMapPoint, ComponentLength componentLength) {
            mName = name;
            mMapValue = MapValueType.COMPONENT;
            mComponentMapPoint = componentMapPoint;
            mComponentLength = componentLength;
        }

        String getName(int index) {
            if (mName.isEmpty()) {
                return "Unknown" + index;
            }
            return mName;
        }

        MapValueType getMapValue() {
            return mMapValue;
        }

        Mapper getComponent() {
            return mComponentMapPoint;
        }

        public ComponentLength getComponentLength() {
            return mComponentLength;
        }
    }

    public abstract MapPoint[] getMapPoints();
}
