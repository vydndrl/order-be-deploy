package beyond.ordersystem.member.controller;

import beyond.ordersystem.common.dto.CommonResDto;
import beyond.ordersystem.member.domain.Member;
import beyond.ordersystem.member.dto.MemberReqDto;
import beyond.ordersystem.member.dto.MemberResDto;
import beyond.ordersystem.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordersystem")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/create")
    public ResponseEntity<?> createMember(@RequestBody MemberReqDto dto){
        Member member = memberService.memberCreate(dto);
        return new ResponseEntity<>(new CommonResDto(HttpStatus.CREATED, "member is successfully created", member.getId()), HttpStatus.CREATED);

    }

    @GetMapping("/member/list")
    public ResponseEntity<Object> memberList(Pageable pageable){
        Page<MemberResDto> dtos = memberService.memberList(pageable);
        return new ResponseEntity<>(new CommonResDto(HttpStatus.OK, "members are found", dtos), HttpStatus.OK);
    }
}
