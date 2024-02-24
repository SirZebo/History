package com.AmbroseGoh.Bank.Application.Common.Interfaces.Authentication;

import java.util.UUID;

public interface IJwtTokenGenerator {
    String generateToken(UUID userId, String firstName, String lastName, long ttlMillis);
}
