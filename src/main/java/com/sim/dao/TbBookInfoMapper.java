package com.sim.dao;

import com.sim.criteria.TbBookInfoCriteria;
import com.sim.model.TbBookInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbBookInfoMapper {
    long countByExample(TbBookInfoCriteria example);

    int deleteByExample(TbBookInfoCriteria example);

    int deleteByPrimaryKey(Integer bid);

    int insert(TbBookInfo record);

    int insertSelective(TbBookInfo record);

    List<TbBookInfo> selectByExample(TbBookInfoCriteria example);

    TbBookInfo selectByPrimaryKey(Integer bid);

    int updateByExampleSelective(@Param("record") TbBookInfo record, @Param("example") TbBookInfoCriteria example);

    int updateByExample(@Param("record") TbBookInfo record, @Param("example") TbBookInfoCriteria example);

    int updateByPrimaryKeySelective(TbBookInfo record);

    int updateByPrimaryKey(TbBookInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_book_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    TbBookInfo selectOneByExample(TbBookInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_book_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<TbBookInfo> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_book_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<TbBookInfo> list, @Param("selective") TbBookInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_book_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsert(TbBookInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_book_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertSelective(TbBookInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_book_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertByExample(@Param("record") TbBookInfo record, @Param("example") TbBookInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_book_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertByExampleSelective(@Param("record") TbBookInfo record, @Param("example") TbBookInfoCriteria example);
}