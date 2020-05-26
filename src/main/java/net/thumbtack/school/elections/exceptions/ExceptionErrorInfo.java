package net.thumbtack.school.elections.exceptions;

public enum ExceptionErrorInfo {

    EMPTY_VOTER_FIRSTNAME(100, "Нельзя использовать пустое имя"),
    WRONG_VOTER_FIRSTNAME(101, "Неверное имя"),
    EMPTY_VOTER_LASTNAME(102, "Нельзя использовать пустую фамилию"),
    WRONG_VOTER_LASTNAME(103, "Неверная фамилия"),
    EMPTY_VOTER_STREET(104, "Нельзя использовать пустую улицу"),
    WRONG_VOTER_STREET(105, "Неверная улица"),
    EMPTY_VOTER_HOUSE(106, "Нельзя использовать пустой дом"),
    WRONG_VOTER_HOUSE(107, "Неверный дом"),
    EMPTY_VOTER_LOGIN(108, "Нельзя использовать пустой логин"),
    NULL_VOTER_LOGIN(109, "Пользователя с таким логином не существует"),
    WRONG_VOTER_LOGIN(110, "Неверый логин"),
    WRONG_VOTER_LOGIN_LENGTH(111, "Логин должен быть длиннее 2 символов"),
    EMPTY_VOTER_PASSWORD(112, "Нельзя использовать пустой логин"),
    WRONG_VOTER_PASSWORD_LENGTH(113, "Пароль должен быть длиннее 6 символов"),
    WRONG_VOTER_PASSWORD(114, "Неверный пароль"),
    WRONG_VOTER_TOKEN(115, "Неверный токен"),
    DUPLICATE_VOTER(116, "Такой избиратель уже зарегистрирован"),
    EMPTY_VOTER_PROPOSAL(117, "Предложение не может быть пустым"),
    EMPTY_CANDIDATE_LIST(118, "Такой кандидат не выдвинут"),
    DUPLICATE_CANDIDATE(119, "Такой кандидат уже выдвинут"),
    SAME_PROPOSAL_AUTHOR(120, "Нельзя менять собственного предложения"),
    WRONG_PROPOSAL_INFO(121, "Такое предложение еще не было выдвинуто"),
    WRONG_CANDIDATE_PROPOSAL(122, "У данного кандидата нет такого предложения"),
    EMPTY_CANDIDATE_PROGRAM(123, "У данного кандидата нет кандидатской программы"),
    WRONG_PROPOSAL_RATING(124, "Рейтинг должен быть от 1 до 5"),
    ELECTIONS_HAVE_BEEN_STARTED(125, "Выборы уже начались"),
    ELECTIONS_NOT_STARTED(126, "Выборы еще не начались"),
    MAJOR_NOT_SELECTED(127, "Мэр не выбран"),
    NOT_ENOUGH_ROOT(128, "Недостаточно прав"),
    SOMETHING_WRONG(129, "Что-то не так");

    private String errorString;
    private int errorCode;

    ExceptionErrorInfo(int errorCode, String errorString) {
        this.errorCode = errorCode;
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
