package com.ldb.project.server.domain;


import java.util.List;
import java.util.Map;

/**
 * 全局变量&信息定义
 */
public abstract class GlobalField {

    /**
     * 读者类型对应最大借阅数量
     *
     * @Author SpadeK
     */
    public static final Map<String, Integer> MAX_BORROW_NUM = Map.of(
            "青铜会员", 5,
            "白银会员", 10,
            "黄金会员", 20,
            "铂金会员", 30,
            "钻石会员", 50,
            "星耀会员", 100
    );

    /**
     * 读者类型列表
     * <p>手动定义</p>
     * 星耀会员
     * 白银会员
     * 铂金会员
     * 青铜会员
     * 黄金会员
     * 钻石会员
     *
     * @Author SpadeK
     */
    public static final List<String> READER_TYPE_LIST = List.of(
            "青铜会员",
            "白银会员",
            "黄金会员",
            "铂金会员",
            "钻石会员",
            "星耀会员");


    /**
     * 书籍类型列表
     * <p>手动定义</p>
     * 马列毛邓
     * 哲学、宗教
     * 社会科学总论
     * 政治、法律
     * 军事
     * 经济
     * 文化科教
     * 语言、文字
     * 文学
     * 艺术
     * 历史、地理
     * 自然科学总论
     * 数理化学
     * 天文地球科学
     * 生物科学
     * 医药、卫生
     * 农业科学
     * 工业技术
     * 交通运输
     * 航空、航天
     * 环境安全科学
     * 综合性图书
     *
     * @Author SpadeK
     */
    public static final List<String> BOOK_TYPE_LIST = List.of(
            "马列毛邓",
            "哲学、宗教",
            "社会科学总论",
            "政治、法律",
            "军事",
            "经济",
            "文化科教",
            "语言、文字",
            "文学",
            "艺术",
            "历史、地理",
            "自然科学总论",
            "数理化学",
            "天文地球科学",
            "生物科学",
            "医药、卫生",
            "农业科学",
            "工业技术",
            "交通运输",
            "航空、航天",
            "环境安全科学",
            "综合性图书"
    );
}
