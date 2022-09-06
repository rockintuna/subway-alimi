package kr.co.e8ight.subwayalimi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Address {
    @JsonProperty("address_name")
    String addressName;
    @JsonProperty("category_group_code")
    String categoryGroupCode;
    @JsonProperty("category_group_name")
    String categoryGroupName;
    @JsonProperty("category_name")
    String categoryName;
    String distance;
    String id;
    String phone;
    @JsonProperty("place_name")
    String placeName;
    @JsonProperty("place_url")
    String placeUrl;
    @JsonProperty("road_address_name")
    String roadAddressName;
    String x;
    String y;
}
