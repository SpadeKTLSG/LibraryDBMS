> 这个单应用版本, 相比一般的若依来说更加简练

---

> 项目结构Note

- common
    - 通用的工具类
        - uuid是负责管理唯一标识的地方, 有自己做的有若依提供的解决方案
- framework
    - 框架核心内容 无须在意
- project !
    - 业务核心内容,内部层级都是C-S-M; 对应着前端接口的左侧目录层级
        - common是通用的Controller 可以自定义通用Controller
        - monitor是系统监控合集 无须在意
        - system是系统管理合集 无须在意
        - tool是系统工具合集 无须在意
        - server是我们的业务合集, 代码生成器对应路由目录
            - 对应六张表的基础CRUD, 但是对于部分表无法前端新增(借阅关系), 需要Service实现手动赋值新增
            - 流程: 根据业务找方法 -> 查对应实体(默认实体包为domain) -> 提取需要信息 -> DTO封装(对应包为dto)/可选 -> 返回
            - 注意:IService -> ServiceImpl对应关系, 名字Service前面有一个I...可以自己改

- resources
    - 已经调试好, 无须在意
        - mybatis
            - server
                - 里面是对应的六张表的Mapper.xml

---

