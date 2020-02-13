package net.thumbtack.school.colors.v3;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("Неверный цвет"),
    NULL_COLOR("Пустой цвет");

    String errorString ;

    ColorErrorCode(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
