> 这个单应用版本, 相比一般的若依来说更加简练

---

> 项目结构Note

- common
    - 通用的工具类等 无须在意
- framework
    - 框架核心内容 无须在意
- project !
    - 业务核心内容,内部层级都是C-S-M; 对应着前端接口的左侧目录层级
        - common是通用的Controller
        - monitor是系统监控合集
        - system是系统管理合集
        - tool是工具合集
        - server是我们的业务合集, 代码生成器对应路由目录
- resources
    - 已经调试好, 无须在意

---

