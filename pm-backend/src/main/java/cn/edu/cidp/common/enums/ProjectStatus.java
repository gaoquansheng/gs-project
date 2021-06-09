package cn.edu.cidp.common.enums;

public enum ProjectStatus {

    FILLING("0","填报中"),SUBMITTED("1","已提交"),RECOMMENDED("2","推荐中"),
    QUALIFICATION("3","资格审查中"),WAREHOUSING("4","入库"),NOTWAREHOUSING("5","未入库"),
    APPROVED("6","已立项"),EXPIRED("7","过期")
    ;

    private final String code;
    private final String info;

    ProjectStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }

    @Override
    public String toString() {
        return "ProjectStatus{" +
                "code='" + code + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
