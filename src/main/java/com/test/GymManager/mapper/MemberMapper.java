package com.test.GymManager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.GymManager.entity.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface MemberMapper extends BaseMapper<Member> {
    @Override
    int insert(Member member);

    @Select("select * from member")
    public List<Member> getAllMembers();

    @Delete("DELETE FROM member WHERE member_id = #{memberId}")
    int deleteByMemberId(@Param("memberId") Long memberId);

    //根据Id查询
    @Select("select * from member where member_id=#{param1}")
    public Member getMember(@Param("memberId")Long memberId);
    // public List<Member> getMember(Member member);
    @Update({
            "UPDATE member SET name = #{name} ,age = #{age},gender = #{gender},phone = #{phone},register_date = #{registerDate},balance = #{balance} WHERE member_id = #{memberId}"
    })
    public int updatemember(Member member);
}
