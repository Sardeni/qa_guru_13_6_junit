package guru.qa.enums;

public enum AvitoMenu {
    AUTO("Авто"),
    REAL_ESTATE("Недвижимость"),
    JOB("Работа"),
    SERVICES("Услуги");

    public final String desc;

    AvitoMenu(String desc) {
        this.desc = desc;
    }
}
