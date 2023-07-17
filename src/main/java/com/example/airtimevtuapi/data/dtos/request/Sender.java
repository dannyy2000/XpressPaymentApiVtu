package com.example.airtimevtuapi.data.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sender {

    private String name;
    private String email;

}
