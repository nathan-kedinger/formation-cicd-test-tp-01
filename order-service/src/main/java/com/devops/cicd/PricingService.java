package com.devops.cicd;

public final class PricingService {

    private final PricingConfig config;

    public PricingService(PricingConfig config) {
        this.config = config;
    }

    public double applyVat(double amountExclVat) {
        var vatRate = config.getVatRate();
        var totalWithVat = amountExclVat * (1 + vatRate / 100);
        return totalWithVat;
    }

    public double applyVipDiscount(double amount, boolean vip) {
        if (vip) {
            var discountRate = 10.0; // remise de 10% pour les clients VIP
            var discountedAmount = amount * (1 - discountRate / 100);
            return discountedAmount;
        } else {
            return amount;
        }
    }

    public double shippingCost(double amount) {
        var freeShippingThreshold = config.getFreeShippingThreshold();
        if (amount >= freeShippingThreshold) {
            return 0.0; // livraison gratuite si le montant dépasse le seuil
        } else {
            return 5.0;
        }
    }

    /**
     * - TVA appliquée d'abord : HT -> TTC
     * - remise VIP appliquée sur TTC
     * - frais de livraison ajoutés ensuite (calculés sur TTC)
     */
    public double finalTotal(double amountExclVat, boolean vip) {
        var totalWithVat = applyVat(amountExclVat);
        var totalAfterDiscount = applyVipDiscount(totalWithVat, vip);
        var shipping = shippingCost(totalAfterDiscount);
        var finalAmount = totalAfterDiscount + shipping;
        return finalAmount;
    }
}