package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.*;
import io.smallrye.mutiny.Uni;

public interface UserService {

    Uni<UserOnboardResponseDTO> userOnboard(UserOnboardRequestDTO req);

    Uni<UserBirthChartRegistrationResponse> birthChartRegister(
            BirthChartResponseDTO req, String UUID);

    Uni<UserDataResponseDTO> getUserData(String id);

    Uni<UserDataResponseDTO> getUserDatabyAuthId(String userAuthUUID);
}
