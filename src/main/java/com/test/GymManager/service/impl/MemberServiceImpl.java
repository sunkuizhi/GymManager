package com.test.GymManager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.GymManager.entity.*;
import com.test.GymManager.mapper.MemberMapper;
import com.test.GymManager.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Position;
import java.util.List;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper,Member> implements IMemberService {
    @Autowired
    MemberMapper memberMapper;

    @Override
    public int insert(Member member) {
        int n = memberMapper.insert(member);
        return n;
    }
    @Override
    public List<Member> getAllMembers() {
        return memberMapper.getAllMembers();
    }
    public PageInfo<Member> getData(int currentPage, int size) {
        //注意代码顺序,PageHelper分页插件,接管mybatisplus从数据库中查询到的数据
        //PageHelper要卸载userMapper.selAll()逻辑的前面,不然不生效
        PageHelper.startPage(currentPage,size);
        List<Member> emps = memberMapper.getAllMembers();
        for (Member Member:emps){
            Long memberId = Member.getMemberId();
        }
        //进行分页:含页码number,数据的总体条数total,分页后的数据List
        PageInfo<Member> pageInfo = new PageInfo<>(emps);
        return pageInfo;
    }
    @Override
    public int deleteByemberId(Long memberId) {
        return memberMapper.deleteByMemberId(memberId);
    }
    @Override
    public Member getMember(Long memberId) {
        Member member = memberMapper.getMember(memberId);
        return member;
    }
    @Override
    public int updatemember(Member member) {
        return memberMapper.updatemember(member);
    }
}

