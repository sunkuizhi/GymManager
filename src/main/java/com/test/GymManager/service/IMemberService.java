package com.test.GymManager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.GymManager.entity.Member;

import java.util.List;

public interface IMemberService extends IService<Member> {
    int insert(Member member);
    public List<Member> getAllMembers();
    public int deleteByemberId(Long memberId);
    public Member getMember(Long memberId);
    public int updatemember(Member member);
}
