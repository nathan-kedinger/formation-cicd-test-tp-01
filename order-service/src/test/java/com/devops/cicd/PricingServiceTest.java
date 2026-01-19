package com.devops.cicd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest {

    private final PricingConfig fakeConfig = new PricingConfig(20.0, 50.0);
    private final PricingService service = new PricingService(fakeConfig);

    @Test
    void testApplyVat() {
        double amountExclVat = 100.0;
        double expected = 120.0; // 100 + 20% VAT
        double actual = service.applyVat(amountExclVat);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    void testApplyVipDiscount_VipCustomer() {
        double amount = 120.0;
        boolean vip = true;
        double expected = 108.0; // 120 - 10% discount
        double actual = service.applyVipDiscount(amount, vip);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    void testApplyVipDiscount_NonVipCustomer() {
        double amount = 120.0;
        boolean vip = false;
        double expected = 120.0; // no discount
        double actual = service.applyVipDiscount(amount, vip);
        assertEquals(expected, actual, 0.01);
    }
    @Test
    void testShippingCost_FreeShipping() {
        double amount = 60.0; // above free shipping threshold
        double expected = 0.0;
        double actual = service.shippingCost(amount);
        assertEquals(expected, actual, 0.01);
    }
}