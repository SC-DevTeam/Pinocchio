package com.scdevteam.messages.coc;

import com.scdevteam.messages.coc.server.LoginOk;
import com.scdevteam.proto.GameMapper;
import com.scdevteam.proto.Mapper;
import com.scdevteam.proto.MessageMap;

public class COCMapper extends GameMapper {
    @Override
    public Mapper getMap(int msgId) {
        switch (msgId) {
            case MessageMap.LOGIN_OK:
                return new LoginOk();
        }
        return null;
    }
}
