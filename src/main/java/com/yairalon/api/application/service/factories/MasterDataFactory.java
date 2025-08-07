package com.yairalon.api.application.service.factories;

import com.yairalon.api.application.service.MasterDataService;
import com.yairalon.api.application.service.MasterDataServiceImpl;
import com.yairalon.api.interfaces.rest.MasterData;

public abstract class MasterDataFactory   {
    public static MasterDataService createMasterData() {
       return new MasterDataServiceImpl();
    }
}
