package com.devops.cicd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyTest {
    @Test
    void testValidPassword() {
        String password = "StrongP@ssw0rd!";
        assertTrue(PasswordPolicy.isStrong(password));
    }
}
