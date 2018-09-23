package com.example.fakegram.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
public class NewUserDataDTO {

    @NonNull private String username;
    @NonNull private String email;
    @NonNull private String password;
    private AddressDTO address;

}
