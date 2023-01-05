package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author tuuli
 * @time Created in 2022/11/3 15:05
 * @description
 */
public class BrandServiceImpl implements BrandService {

    //1.创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        List<Brand> brands = mapper.selectAll();

        //5.释放资源
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.add(brand);
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.deleteByIds(ids);
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteById(Integer id) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.deleteById(id);
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void update(Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.update(brand);
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.计算开始索引
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        //5.查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, size);

        //6.查询总记录数
        int totalCount = mapper.selectTotalCount();

        //7.封装
        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(rows);
        brandPageBean.setTotalCount(totalCount);

        sqlSession.close();

        return brandPageBean;

    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.计算开始索引
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        //处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }

        //5.查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);

        //6.查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //7.封装
        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(rows);
        brandPageBean.setTotalCount(totalCount);

        sqlSession.close();

        return brandPageBean;
    }
}
