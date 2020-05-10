package net.thumbtack.school.elections.exceptions;

public enum ExceptionErrorCode {

    EMPTY_VOTER_FIRSTNAME("Нельзя использовать пустое имя"),
    WRONG_VOTER_FIRSTNAME("Неверное имя"),
    EMPTY_VOTER_LASTNAME("Нельзя использовать пустую фамилию"),
    WRONG_VOTER_LASTNAME("Неверная фамилия"),
    EMPTY_VOTER_STREET("Нельзя использовать пустую улицу"),
    WRONG_VOTER_STREET("Неверная улица"),
    EMPTY_VOTER_HOUSE("Нельзя использовать пустой дом"),
    WRONG_VOTER_HOUSE("Неверный дом"),
    EMPTY_VOTER_LOGIN("Нельзя использовать пустой логин"),
    NULL_VOTER_LOGIN("Пользователя с таким логином не существует"),
    WRONG_VOTER_LOGIN("Неверый логин"),
    WRONG_VOTER_LOGIN_LENGTH("Логин должен быть длиннее 2 символов"),
    EMPTY_VOTER_PASSWORD("Нельзя использовать пустой логин"),
    WRONG_VOTER_PASSWORD_LENGTH("Пароль должен быть длиннее 6 символов"),
    WRONG_VOTER_PASSWORD("Неверный пароль"),
    WRONG_VOTER_TOKEN("Неверный токен"),
    DUPLICATE_VOTER("Такой избиратель уже зарегестрирован"),
    EMPTY_VOTER_PROPOSAL("Предложение не может быть пустым"),
    EMPTY_CANDIDATE_LIST("Такой кандидат не выдвинут"),
    DUPLICATE_CANDIDATE("Такой кандидат уже выдвинут"),
    SAME_PROPOSAL_AUTHOR("Нельзя менять собственного предложения"),
    WRONG_PROPOSAL_INFO("Такое предложение еще не было выдвинуто"),
    WRONG_CANDIDATE_PROPOSAL("У данного кандидата нет такого предложения"),
    EMPTY_CANDIDATE_PROGRAM("У данного кандидата нет кандидатской программы"),
    WRONG_PROPOSAL_RATING("Рейтинг должен быть от 1 до 5"),
    ELECTIONS_HAVE_BEEN_STARTED("Выборы уже начались"),
    ELECTIONS_NOT_STARTED("Выборы еще не начались"),
    MAJOR_NOT_SELECTED("Мэр не выбран"),
    NOT_ENOUGH_ROOT("Недостаточно прав");

    private String errorString;

    ExceptionErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
