package kr.co.e8ight.subwayalimi.dto;

import kr.co.e8ight.subwayalimi.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AddressSearchResponse {
    List<Address> documents;
}
