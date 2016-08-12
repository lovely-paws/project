package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.beans.EndUserInfo;
import edu.johnshopkins.lovelypaws.entity.EndUser;

public interface EndUserBo {
    EndUser create(EndUserInfo endUserInfo);
}
