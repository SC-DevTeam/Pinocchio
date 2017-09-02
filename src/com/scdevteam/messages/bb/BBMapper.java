package com.scdevteam.messages.bb;

import com.scdevteam.proto.GameMapper;
import com.scdevteam.proto.Mapper;
import com.scdevteam.proto.MessageMap;

public class BBMapper extends GameMapper {
    @Override
    public Mapper getMap(int msgId) {
        switch (msgId) {
            case MessageMap.LOGIN_OK:
        }
        return null;
    }
}
