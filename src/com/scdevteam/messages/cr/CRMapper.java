package com.scdevteam.messages.cr;

import com.scdevteam.messages.cr.server.LoginOk;
import com.scdevteam.messages.cr.server.OwnHomeData;
import com.scdevteam.proto.GameMapper;
import com.scdevteam.proto.Mapper;
import com.scdevteam.proto.MessageMap;

public class CRMapper extends GameMapper {
    @Override
    public Mapper getMap(int msgId) {
        switch (msgId) {
            case MessageMap.HOME_DATA_OWN:
                return new OwnHomeData();
            case MessageMap.LOGIN_OK:
                return new LoginOk();
        }
        return null;
    }
}
