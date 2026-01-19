package com.devops.cicd;

    import java.io.IOException;
    import java.util.Properties;

    public class PricingConfigLoader {

        public PricingConfig load() {
            var props = new Properties();
            try (var input = getClass().getClassLoader().getResourceAsStream("app.properties")) {
                props.load(input);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load pricing properties", e);
            }

            double vatRate = Double.parseDouble(required(props, "vat.rate"));
            double freeShippingThreshold = Double.parseDouble(required(props, "free.shipping.threshold"));

            return new PricingConfig(vatRate, freeShippingThreshold);
        }

        private String required(Properties props, String key) {
            String value = props.getProperty(key);
            if (value == null) {
                throw new IllegalArgumentException("Missing required property: " + key);
            }
            return value;
        }
    }