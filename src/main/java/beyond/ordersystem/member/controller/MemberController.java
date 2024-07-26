package beyond.ordersystem.member.controller;

import beyond.ordersystem.common.auth.JwtTokenProvider;
import beyond.ordersystem.common.dto.CommonResDto;
import beyond.ordersystem.member.domain.Member;
import beyond.ordersystem.member.dto.MemberLoginDto;
import beyond.ordersystem.member.dto.MemberReqDto;
import beyond.ordersystem.member.dto.MemberResDto;
import beyond.ordersystem.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/member/create")
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberReqDto dto){
        Member member = memberService.memberCreate(dto);
        return new ResponseEntity<>(new CommonResDto(HttpStatus.CREATED, "member is successfully created", member.getId()), HttpStatus.CREATED);

    }

    @GetMapping("/member/list")
    public ResponseEntity<Object> memberList(Pageable pageable){
        Page<MemberResDto> dtos = memberService.memberList(pageable);
        return new ResponseEntity<>(new CommonResDto(HttpStatus.OK, "members are found", dtos), HttpStatus.OK);
    }

    @PostMapping("/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody MemberLoginDto dto) {
        // 1. email, password가 일치하는 지 검증
        Member member = memberService.login(dto);

        // 2. 일치할 경우 accessToken 생성
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());

        // 3. 생성된 토큰을 CommonResDto에 담아 사용자에게 return
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put ("id", member.getId());
        loginInfo.put ("token", jwtToken);
        return new ResponseEntity<>(new CommonResDto(HttpStatus.OK, "login is successful", loginInfo), HttpStatus.OK);
    }
}
