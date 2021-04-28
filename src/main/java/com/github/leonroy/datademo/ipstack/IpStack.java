package com.github.leonroy.datademo.ipstack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpStack {
    private boolean success = true;

    @NotNull(message = "IP required")
    private String ip;

    private String type;

    @JsonProperty("continent_code")
    private String continentCode;

    @JsonProperty("continent_name")
    private String continentName;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("region_code")
    private String regionCode;

    @JsonProperty("region_name")
    private String regionName;

    private String city;

    private String zip;

    private double latitude;

    private double longitude;

    private Location location;

    @JsonProperty("time_zone")
    private Timezone timezone;

    private Currency currency;

    private Connection connection;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        @JsonProperty("geoname_id")
        private String geonameId;

        private String capital;

        private Set<Language> languages;

        @JsonProperty("country_flag")
        private String countryFlag;

        @JsonProperty("country_flag_emoji")
        private String countryFlagEmoji;

        @JsonProperty("country_flag_emoji_unicode")
        private String countryFlagEmojiUnicode;

        @JsonProperty("calling_code")
        private int callingCode;

        @JsonProperty("is_eu")
        private boolean isEu;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Language {
            private String code;

            private String name;

            @JsonProperty("native")
            private String nativeLanguage;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Timezone {
        private String id;

        @JsonProperty("current_time")
        private Date currentTime;

        @JsonProperty("gmt_offset")
        private int gmtOffset;

        private String code;

        @JsonProperty("is_daylight_saving")
        private boolean daylightSaving;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Currency {
        private String code;

        private String name;

        private String plural;

        private String symbol;

        @JsonProperty("symbol_native")
        private String symbolNative;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Connection {
        private int asn;

        private String isp;
    }
}
