package com.test.GymManager.controller;


import com.github.pagehelper.PageInfo;
import com.test.GymManager.entity.Member;
import com.test.GymManager.entity.Pages;
import com.test.GymManager.service.impl.MemberServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@Controller
@RequestMapping("/member")
@Api(value = "会员控制类",tags = {"关于会员的MemberController"})
public class MemberController {
    //调用业务层
    @Autowired
    private MemberServiceImpl memberService;
    //添加会员,获取表单提交过来的数据
    @GetMapping("/addmember")
    @ResponseBody
    public String addmember(Member member){
        int n = memberService.insert(member);
        return n>0?"添加成功":"添加失败";
    }
    //显示所有的会员信息
    @GetMapping("/showmembers")
    @ApiOperation(value = "显示所有的会员信息",notes = "查询所有会员")
    public String showmembers(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            Model model) {
        PageInfo<Member> pageInfo = memberService.getData(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("members", pageInfo.getList()); // 关键：将分页数据绑定到 members
        return "memberList";
    }
    //删除会员
    @GetMapping("/deletemembers")
    @ResponseBody
    public String deletemember(Long memberId){
        int n = memberService.deleteByemberId(memberId);
        return n>0?"删除成功":"删除失败";
    }
    //修改会员
    @GetMapping("/memberupdate")
    public String showUpdate(Long memberId, Model m) {
        Member member = memberService.getMember(memberId);
        m.addAttribute("member", member);
        // 跳转到修改页面（需创建该HTML文件）
        return "memberEdit";
    }
    @PostMapping("/updatemember")
    public String updatemember(@ModelAttribute Member member) {
        int n = memberService.updatemember(member);
        return "redirect:/member/showmembers"; // 重定向到会员列表页面
    }
}