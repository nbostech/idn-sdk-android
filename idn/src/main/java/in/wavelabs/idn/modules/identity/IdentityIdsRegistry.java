package in.wavelabs.idn.modules.identity;

import in.wavelabs.ids.IDS;

/**
 * Created by vivekkiran on 6/16/16.
 */

public class IdentityIdsRegistry {
    static {
        IDS.register("identity",IdentityApi.class);
    }
}
