package com.awbeci.core.abs;

import com.awbeci.core.IBaseDao;
import com.awbeci.core.bean.Page;
import com.awbeci.core.util.ClassUtil;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 抽象泛型Dao实现
 * Created by [Sun Wang] on 2015/10/31.
 */
public abstract class BaseDao<T, ID extends Serializable> extends SqlSessionDaoSupport implements IBaseDao<T, ID> {

    public static final String SQLNAME_SEPARATOR = ".";
    public static final String SQL_INSERT = "insert";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_DETAIL = "detail";
    public static final String SQL_DELETE = "delete";
    public static final String SQL_FINDPAGE = "findPage";
    public static final String SQL_FINDLIST = "findList";

    /**
     * sql命名空间
     */
    private String sqlNamespace = "";

    /**
     * 构造初始化
     */
    public BaseDao() {
        //使用泛型参数中业务实体类型的全限定名作为默认的命名空间。
        if(sqlNamespace == null) sqlNamespace = ClassUtil.getGenericType(this.getClass()).getName();
    }

    /**
     * 设置sessionfactory
     * @param sqlSessionFactory
     */
    @Override
    @Autowired(required = false)
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 设置SqlMapping命名空间。
     * 此方法只用于注入SqlMapping命名空间，以改变默认的SqlMapping命名空间，
     * 不能滥用此方法随意改变SqlMapping命名空间。
     *
     * @param sqlNamespace SqlMapping命名空间
     */
    public void setSqlNamespace(String sqlNamespace) {
        this.sqlNamespace = sqlNamespace;
    }

    /**
     * 获取SqlMapping命名空间
     * 使用泛型参数中业务实体类型的全限定名作为默认的命名空间。
     * 如果实际应用中需要特殊的命名空间，可由子类重写该方法实现自己的命名空间规则。
     *
     * @return SqlMapping命名空间
     */
    public String getSqlNamespace() {
        return sqlNamespace;
    }

    /**
     * 将SqlMapping命名空间与给定的SqlMapping名组合在一起。
     *
     * @param sqlName SqlMapping名
     * @return 组合了SqlMapping命名空间后的完整SqlMapping名
     */
    protected String getSqlName(String sqlName) {
        if(getSqlNamespace() != null && !"".equals(getSqlNamespace().trim())) {
            return getSqlNamespace() + SQLNAME_SEPARATOR + sqlName;
        } else {
            return sqlName;
        }
    }

    /**
     * 生成主键值。
     * 默认情况下什么也不做；
     * 如果需要生成主键，需要由子类重写此方法根据需要的方式生成主键值。
     *
     * @param obj 要持久化的对象
     */
    protected void generateId(T obj) {

    }

    /**
     * 新增
     * @param object 要添加持久化对象
     * @return
     */
    public Integer insert(T object) {
        generateId(object);
        return insert(SQL_INSERT, object);
    }

    /**
     * 修改
     * @param object 要更新的持久化对象
     * @return
     */
    public Integer update(T object) {
        return update(SQL_UPDATE, object);
    }

    /**
     * 删除
     * @param object 要删除的持久化对象
     * @return
     */
    public Integer delete(T object) {
        return delete(SQL_DELETE, object);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public T detail(ID id) {
        return selectOne(SQL_DETAIL, id);
    }

    /**
     * 分页查询
     * @param page
     * @param parameters
     * @param orders
     * @return
     */
    public Page<T> findPage(Page<T> page, Object parameters, Order... orders) {
        return findPage(SQL_FINDPAGE, page, parameters, orders);
    }

    /**
     * 分页查询
     * @param page
     * @param parameters
     * @return
     */
    public Page<T> findPage(Page<T> page, Object parameters) {
        return findPage(SQL_FINDPAGE, page, parameters, (Order[]) null);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    public Page<T> findPage(Page<T> page) {
        return findPage(SQL_FINDPAGE, page, null, (Order[]) null);
    }

    /**
     * 查询列表
     * @param parameters
     * @param offset
     * @param limit
     * @return
     */
    public List<T> findList(Object parameters, int offset, int limit) {
        return findList(SQL_FINDLIST, parameters, offset, limit);
    }

    /**
     * 查询列表
     * @param parameters
     * @return
     */
    public List<T> findList(Object parameters) {
        return findList(SQL_FINDLIST, parameters);
    }

    /**
     * 查询列表
     * @return
     */
    public List<T> findList() {
        return findList(SQL_FINDLIST);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#insert(String, Object)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @param parameters 参数
     * @return 执行结果——插入成功的记录数
     * @see org.apache.ibatis.session.SqlSession#insert(String, Object)
     */
    public Integer insert(String statement, Object parameters) {
        if(parameters == null) {
            return getSqlSession().insert(getSqlName(statement));
        } else {
            return getSqlSession().insert(getSqlName(statement), parameters);
        }
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#insert(String, Object)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @return 执行结果——插入成功的记录数
     * @see org.apache.ibatis.session.SqlSession#insert(String, Object)
     */
    public Integer insert(String statement) {
        return insert(statement, null);
    }


    /**
     * 对{@link org.apache.ibatis.session.SqlSession#update(String, Object)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @param parameters 参数
     * @return 执行结果——更新成功的记录数
     * @see org.apache.ibatis.session.SqlSession#update(String, Object)
     */
    public Integer update(String statement, Object parameters) {
        if(parameters == null) {
            return getSqlSession().update(getSqlName(statement));
        } else {
            return getSqlSession().update(getSqlName(statement), parameters);
        }
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#update(String, Object)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @return 执行结果——更新成功的记录数
     * @see org.apache.ibatis.session.SqlSession#update(String, Object)
     */
    public Integer update(String statement) {
        return update(statement, null);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#delete(String, Object)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @param parameters 参数
     * @return 执行结果——删除成功的记录数
     * @see org.apache.ibatis.session.SqlSession#delete(String, Object)
     */
    public Integer delete(String statement, Object parameters) {
        if(parameters == null) {
            return getSqlSession().delete(getSqlName(statement));
        } else {
            return getSqlSession().delete(getSqlName(statement), parameters);
        }
    }


    /**
     * 对{@link org.apache.ibatis.session.SqlSession#delete(String)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @return 执行结果——删除成功的记录数
     * @see org.apache.ibatis.session.SqlSession#delete(String)
     */
    public Integer delete(String statement) {
        return delete(statement, null);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectOne(String, Object)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @param parameters 参数
     * @return 查询结果对象
     * @see org.apache.ibatis.session.SqlSession#selectOne(String, Object)
     */
    public <M> M selectOne(String statement, Object parameters) {
        if(parameters == null) {
            return getSqlSession().selectOne(getSqlName(statement));
        } else {
            return getSqlSession().selectOne(getSqlName(statement), parameters);
        }
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectOne(String, Object)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @return 查询结果对象
     * @see org.apache.ibatis.session.SqlSession#selectOne(String, Object)
     */
    public <M> M selectOne(String statement) {
        return selectOne(statement, null);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectList(String, Object, RowBounds)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * 分页查询
     *
     * @param statement 映射的语句ID
     * @param page 分页参数
     * @param parameters 查询参数
     * @param orders 排序条件
     * @param <M>
     * @return
     */
    public <M> Page<M> findPage(String statement, Page<M> page, Object parameters, Order... orders) {
        //分页对象初始化
        page = page == null ? new Page<M>() : page;
        PageBounds pageBounds = null;
        if(orders != null) {
            pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), orders);
        } else {
            pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
        }
        //是否返回总数(设置为true会执count语句)
        pageBounds.setContainsTotalCount(true);
        PageList<M> pageList = (PageList<M>) getSqlSession().selectList(getSqlName(statement), parameters, pageBounds);
        int count = pageList.getPaginator().getTotalCount();
        return page.setTotalCount(count).setPageData(pageList);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectList(String, Object, RowBounds)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * 分页查询
     *
     * @param statement 映射的语句ID
     * @param page 分页参数
     * @param orders 排序条件
     * @param <M>
     * @return
     */
    public <M> Page<M> findPage(String statement, Page<M> page, Order... orders) {
        return findPage(statement, page, null, orders);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectList(String, Object, RowBounds)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * 分页查询
     *
     * @param statement 映射的语句ID
     * @param page 分页参数
     * @param parameters 查询参数
     * @param <M>
     * @return
     */
    public <M> Page<M> findPage(String statement, Page<M> page, Object parameters) {
        return findPage(statement, page, parameters, (Order[]) null);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectList(String, Object, RowBounds)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * 列表查询
     *
     * @param statement 映射的语句ID
     * @param parameters 查询参数
     * @param offset 起始行号
     * @param limit 查询行数
     * @param <M>
     * @return
     */
    public <M> List<M> findList(String statement, Object parameters, int offset, int limit) {
        RowBounds rowBounds = null;
        if(offset >= 0 && limit >= 0) {
            rowBounds = new RowBounds(offset, limit);
        }
        if(rowBounds == null && parameters == null) {
            return getSqlSession().selectList(getSqlName(statement));
        } else if(rowBounds != null && parameters != null) {
            return getSqlSession().selectList(getSqlName(statement), parameters, rowBounds);
        } else {
            return getSqlSession().selectList(getSqlName(statement), parameters);
        }
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectList(String, Object, RowBounds)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * 列表查询
     *
     * @param statement 映射的语句ID
     * @param parameters 查询参数
     * @param <M>
     * @return
     */
    public <M> List<M> findList(String statement, Object parameters) {
        return findList(statement, parameters, -1, -1);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectList(String, Object, RowBounds)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * 列表查询
     *
     * @param statement 映射的语句ID
     * @param <M>
     * @return
     */
    public <M> List<M> findList(String statement) {
        return findList(statement, null, -1, -1);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectMap(String, Object, String, RowBounds)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @param parameters 参数
     * @param mapKey    数据mapKey
     * @param rowBounds 用于分页查询的记录范围
     * @return 查询结果Map
     * @see org.apache.ibatis.session.SqlSession#selectMap(String, Object, String, RowBounds)
     */
    public <K, M> Map<K, M> findMap(String statement, Object parameters, String mapKey, RowBounds rowBounds) {
        if(parameters != null && rowBounds != null) {
            return getSqlSession().selectMap(statement, parameters, mapKey, rowBounds);
        } else if(parameters != null) {
            return getSqlSession().selectMap(statement, parameters, mapKey);
        } else {
            return getSqlSession().selectMap(statement, mapKey);
        }
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectMap(String, Object, String, RowBounds)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @param parameters 参数
     * @param mapKey    数据mapKey
     * @return 查询结果Map
     * @see org.apache.ibatis.session.SqlSession#selectMap(String, Object, String, RowBounds)
     */
    public <K, M> Map<K, M> findMap(String statement, Object parameters, String mapKey) {
        return findMap(statement, parameters, mapKey, null);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectMap(String, Object, String, RowBounds)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * @param statement 映射的语句ID
     * @param mapKey    数据mapKey
     * @return 查询结果Map
     * @see org.apache.ibatis.session.SqlSession#selectMap(String, Object, String, RowBounds)
     */
    public <K, M> Map<K, M> findMap(String statement, String mapKey) {
        return findMap(statement, mapKey, null, null);
    }

    /**
     * 对{@link org.apache.ibatis.session.SqlSession#selectOne(String, Object)}的代理。
     * 将statement包装了命名空间，方便DAO子类调用。
     *
     * 查询一个数字
     *
     * @param statement 映射的语句ID
     * @param parameters 参数
     * @return 查询结果对象
     * @see org.apache.ibatis.session.SqlSession#selectOne(String, Object)
     */
    public Integer getCount(String statement, Object parameters) {
        return selectOne(statement, parameters);
    }

}
