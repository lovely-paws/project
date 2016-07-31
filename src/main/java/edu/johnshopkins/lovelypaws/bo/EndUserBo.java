package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.beans.CreateUserRequest;
import edu.johnshopkins.lovelypaws.entity.EndUser;

public interface EndUserBo {
    EndUser create(CreateUserRequest createUserRequest);
}
