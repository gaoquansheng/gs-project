package cn.edu.cidp.common.enums;

public enum ProjectTaskStatus {

    FILLING("0","填报中"),RECOMMENDED("1","推荐中"),
    REVIEW("2","复查中"),FINALIZED("3","定稿")
    ;

    private final String code;
    private final String info;

    ProjectTaskStatus(String code, String info) {
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
