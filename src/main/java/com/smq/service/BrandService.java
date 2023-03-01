package com.smq.service;

import com.smq.pojo.Brand;
import com.smq.pojo.PageBean;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有
     *
     * @return
     */
    public List<Brand> selectAll();


    void add(int[] brand);

    Brand selectById(int parseInt);

    void deleteById(int parseInt);

    void update(Brand brand);

    void deleteByIds(int[] ids);

    PageBean<Brand> selectByPage(int currentPage, int PageSize);

    PageBean<Brand> selectByPageAndCondition(int currentPage, int PageSize, Brand brand);
}
