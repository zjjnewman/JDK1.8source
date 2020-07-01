package javabase;

public class EnumTest {
    public static void main(String[] args) {
        SeasonC s = SeasonC.AUTUMN;
        SeasonE spring = SeasonE.SPRING;
        System.out.println(spring);
    }
}


class SeasonC{
    public static final SeasonC SPRING = new SeasonC("spring", "春天")

    {
        public String show(){
            return "da";
        }
    };
    public static final SeasonC SUMMER = new SeasonC("summer", "夏天");
    public static final SeasonC AUTUMN = new SeasonC("autumn", "秋天");
    public static final SeasonC WINTER = new SeasonC("winter", "冬天");


    private final String seasonName;
    private final String seasonDesc;


    private SeasonC(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "SeasonC{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}



enum SeasonE{

    SPRING("spring", "春天"){
        //可以针对自身重写方法
        public String show(){
            return "dasfs";
        }
    },
    SUMMER("summer", "夏天"),
    AUTUMN("autumn", "秋天"),
    WINTER("winter", "冬天");

    private final String seasonName;
    private final String seasonDesc;

    private SeasonE(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "SeasonC{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }



}