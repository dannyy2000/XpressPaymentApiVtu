package com.example.airtimevtuapi.enums;

import com.example.airtimevtuapi.exceptions.NetworkProviderException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum NetworkProvider {

        MTN(new String[]{"0803", "0813", "0703"}),
        AIRTEL(new String[]{"0802", "0902", "0701"}),
        ETISALAT(new String[]{"0909", "0908", "0817"}),
        GLO(new String[]{"0805", "0705", "0815"});

        private final String[] prefixes;

        NetworkProvider(String[] prefixes) {
            this.prefixes = prefixes;
        }

        public String[] getPrefixes() {
            return prefixes;
        }

        public static String getProvider(String phoneNumber) {
            if (phoneNumber.length() != 11) {
                throw new NetworkProviderException("Phone number must be 11 digits.");
            }

            String prefix = phoneNumber.substring(0, 4);

            for (NetworkProvider provider : NetworkProvider.values()) {
                if (Arrays.asList(provider.getPrefixes()).contains(prefix)) {
                    return provider.name();
                }
            }

            throw new NetworkProviderException("Network provider not recognized.");
        }
    }





