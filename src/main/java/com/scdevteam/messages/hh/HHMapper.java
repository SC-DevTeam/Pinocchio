package com.scdevteam.messages.hh;

import com.scdevteam.proto.GameMapper;
import com.scdevteam.proto.Mapper;
import com.scdevteam.proto.MessageMap;

public class HHMapper extends GameMapper {
    @Override
    public Mapper getMap(int msgId) {
        switch (msgId) {
            case MessageMap.LOGIN_OK:
        }
        return null;
    }
}
