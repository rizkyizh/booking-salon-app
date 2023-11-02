package com.booking.models;

import com.booking.helper.MembershipNameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Membership{
    private String memberId;

    private MembershipNameEnum membershipNameEnum;
}

