package com.devops.cicd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingIntegrationTest {
    @Test
    void fullPricingFlow_withRealConfigFile() {
        PricingConfigLoader loader = new PricingConfigLoader();
        PricingConfig config = loader.load();
        PricingService service = new PricingService(config);

        double amountExclVat = 100.0;
        boolean vip = true;

        double finalAmount = service.finalTotal(amountExclVat, vip);

        // Calcul attendu :
        // 1. Appliquer la TVA (20%) : 100 + 20% = 120
        // 2. Appliquer la remise VIP (10%) : 120 - 10% = 108
        // 3. Frais de livraison (5.0 car < seuil de 50.0) : 108 + 5 = 113
        double expectedFinalAmount = 113.0;

        assertEquals(expectedFinalAmount, finalAmount, 0.01);
    }
}
