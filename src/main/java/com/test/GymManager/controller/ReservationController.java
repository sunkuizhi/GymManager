package com.test.GymManager.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.test.GymManager.entity.Member;
import com.test.GymManager.entity.Pages;
import com.test.GymManager.entity.Reservation;
import com.test.GymManager.entity.Result;
import com.test.GymManager.service.IReservationService;
import com.test.GymManager.service.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    // 调用业务层
    @Autowired
    private ReservationServiceImpl reservationService;
    //创建预约
    @PostMapping("/create")
    public Result createReservation(@RequestBody Reservation reservation) {
        boolean success = reservationService.createReservation(reservation);
        return success ? Result.success() : Result.error("预约失败");
    }
    // 查询会员预约列表
    @GetMapping("/list/{memberId}")
    public Result listByMemberId(@PathVariable Long memberId) {
        return Result.success(reservationService.lambdaQuery()
                .eq(Reservation::getMemberId, memberId)
                .list());
    }

    //查看预约列表
    @GetMapping("/showreservations")
    public String showReservationList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            Model model) {
        // 实际项目中应从Session获取会员ID
        PageInfo<Reservation> pageInfo = reservationService.getData(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("reservations", pageInfo.getList()); // 关键：将分页数据绑定到 reservations
        return "reservationList";
    }
    @PostMapping("/checkIn")
    public Result checkIn(@RequestParam Long reservationId) {
        boolean success = reservationService.update(
                new UpdateWrapper<Reservation>()
                        .eq("reservation_id", reservationId)
                        .set("status", 1) // 1表示已签到
        );
        return success ? Result.success() : Result.error("签到失败");

    }
    //多条件查询
    @GetMapping("/multyFind")
    public String multyFind(Reservation reservation,@RequestParam(defaultValue = "1") int currentPage,Model m){
        System.out.println("reservation: "+reservation);
        //int currentPage, int size
        PageInfo<Reservation> data = reservationService.getDataBy(currentPage, 10,reservation);
        Pages pageInfo= new Pages();
        pageInfo.setTotalPages((int) data.getTotal());
        pageInfo.setContent(data.getList());
        pageInfo.setPageNumber(data.getPageNum());
        pageInfo.setPageSize(data.getPages());
        // m.addAttribute("pages",pageInfo);
        m.addAttribute("pageInfo",pageInfo);
        // m.addAttribute("reservations", pages.getList()); // 关键：将分页数据绑定到 reservations
        return "reservationList";
    }
}