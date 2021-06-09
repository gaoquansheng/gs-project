package cn.edu.cidp.common.enums;

public enum projectConclusionReportStatus {

    FILLING("0","填报中"),RECOMMENDED("1","推荐中"), FINALIZED("2","定稿")
    ;

    private final String code;
    private final String info;

    projectConclusionReportStatus(String code, String info) {
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
