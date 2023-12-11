## Header

[后端手册](https://doc.ruoyi.vip/ruoyi-vue/document/htsc.html#%E5%88%86%E9%A1%B5%E5%AE%9E%E7%8E%B0)

[集成MP](https://doc.ruoyi.vip/ruoyi-vue/document/cjjc.html#%E9%9B%86%E6%88%90mybatisplus%E5%AE%9E%E7%8E%B0mybatis%E5%A2%9E%E5%BC%BA) -> Mybatis

[集成knife4j](https://doc.ruoyi.vip/ruoyi-vue/document/cjjc.html#%E9%9B%86%E6%88%90knife4j%E5%AE%9E%E7%8E%B0swagger%E6%96%87%E6%A1%A3%E5%A2%9E%E5%BC%BA) -> Swagger

[问题综合](https://doc.ruoyi.vip/ruoyi-vue/other/faq.html)

## 数据库

### 表

自己的数据库, 对应数据库项目和对应含义(和注释)

按照层级排列, 加粗为主键(+索引)

6张表

* book书籍

    * **book_id序号**
    * book_name名称
    * author作者
    * book_type类型
    * book_price价格
    * publishing_house出版商
    * summary概要
    * bookshelf_number书架序号
    * collection_number收集总数
    * borrowed_number借出数量
    * in_libraries_number持有数量
* bookshelf书架

    * **bookshelf_number书架序号**
    * bookshelf_type书架类型
* borrow借阅

    * **card_number人卡号**
    * **book_number书序号**
    * book_borrow_time借出时间
    * book_return_time归还时间
    * is_return是否还书
* reader读者

    * **card_number卡号**
    * reader_name读者姓名
    * reader_type读者类型
    * sex性别
    * borrowing_number借阅中数量
    * borrowed_number已归还数量
* staff员工

    * **staff_id员工号**
    * staff_name员工姓名
    * staff_office员工科室
    * staff_wages员工薪资
* user_type用户类型

    * **user_type用户类型**
    * can_borrowing_time最长借阅时间
    * can_borrow_number同时借阅数量

### 数据

BookId 根据书籍数量 `0.89 * 50000` 为10万量级, 因此推算图书馆需要的索引量为至少1000万量级(UUID长度), 否则很容易导致撞, 也就是例如 99999999 ~ 00000001 恰好是一个16进制数

* Book

    * **中国图书馆图书分类法**
      Chinese Library Classification
      简称《中图法》，是建国后编制出版的一部具有代表性的大型综合性分类法，是当今国内图书馆使用最广泛的分类法体系。
    * 根据下面的表, 默认相同书籍放到相同位置(书架), 例如洗头佬的我的奋斗属于文学, 放到9号书架;

      默认图书馆书架只有下面规定的22个
    * 根据基本图书分类创建不同分类的图书22本作为例子(由于没有图书类型表, 只能这样了)

    ==1	A==	马列毛邓
    ==2	B==	哲学、宗教
    ==3	C==	社会科学总论
    ==4	D==	政治、法律
    ==5	E==	军事
    ==6	F==	经济
    ==7	G==	文化科教
    ==8	H==	语言、文字
    ==9	I==	文学
    ==10	J==	艺术
    ==11	K==	历史、地理
    ==12	N==	自然科学总论
    ==13	O==	数理化学
    ==14	P==	天文地球科学
    ==15	Q==	生物科学
    ==16	R==	医药、卫生
    ==17	S==	农业科学
    ==18	T==	工业技术
    ==19	U==	交通运输
    ==20	V==	航空、航天
    ==21	X==	环境安全科学
    ==22	Z==	综合性图书

书架需要有和书本自洽的逻辑关系, 因为没有多对多的中间表, 因此采用主动约束控制
因此默认情况下不能生成书架, 书架数量固定, 因此不用UUID生成也可

这里为了满足项目要求也设置了新增书架的方法, 不会影响到原本的书架

对应关系如上, 数据库输入

## Ways

### 获取用户登录信息

1. 第一种方法

```java
// 获取当前的用户名称
String username = SecurityUtils.getUsername();
```

2、缓存获取当前用户信息

```java
@Autowired
private TokenService tokenService;

LoginUser loginUser = tokenService.getLoginUser();
// 获取当前的用户名称
String username = loginUser.getUsername();
```
