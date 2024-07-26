package beyond.ordersystem.member.dto;

import beyond.ordersystem.common.domain.Address;
import beyond.ordersystem.member.domain.Member;
import beyond.ordersystem.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberReqDto {

    private String name;
    @NotEmpty(message = "email is essential")
    private String email;

    @NotEmpty(message = "password is essential")
    @Size(min = 8, message = "password id minimum length is 8")
    private String password;
    private Address address;
    private Role role;

    public Member toEntity(String password){
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .password(password)
                .role(Role.USER)
                .address(address)
                .build();
    }
}
