package com.smq.service.impl;

import com.smq.mapper.BrandMapper;
import com.smq.pojo.Brand;
import com.smq.pojo.PageBean;
import com.smq.service.BrandService;
import com.smq.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Brand> selectAll() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brandMappers = brandMapper.selectAll();
        sqlSession.close();
        return brandMappers;

    }
    public void add(int[] brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.add(brand);
        sqlSession.commit();
        sqlSession.close();

    }

    public Brand selectById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = brandMapper.selectById(id);

        sqlSession.close();
        return brand;
    }

    public void update(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.update(brand);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int PageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * PageSize;
        List<Brand> rows = brandMapper.selectByPage(begin, PageSize);
        int count = brandMapper.selectTotalCount();
        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(rows);
        brandPageBean.setTotal(count);
        sqlSession.close();
        return brandPageBean;

    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int PageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * PageSize;

        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }
        List<Brand> rows = brandMapper.selectByPageAndCondition(begin, PageSize,brand);
        int count = brandMapper.selectTotalCountByCondition(brand);
        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(rows);
        brandPageBean.setTotal(count);
        sqlSession.close();
        return brandPageBean;

    }

    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteById(id);
        sqlSession.commit();

        sqlSession.close();

    }
}
