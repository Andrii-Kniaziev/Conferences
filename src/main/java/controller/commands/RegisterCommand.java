package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.impl.JDBCAccountDAO;
import dao.Constants;
import dao.MyException;
import model.entities.Account;
import model.entities.Role;

public class RegisterCommand implements Command {
    private static final String FORWARD = "/WEB-INF/views/registration-result.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws MyException {
        String result = "Вы успешно зарегестрированы, пожалуйста, авторизируйтесь";
        JDBCAccountDAO dao = JDBCAccountDAO.getInstance();

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Role role = Role.stringToEnum(request.getParameter("role"));

        if(!fieldsNotEmptyCheck(firstName, lastName, email, password, role)) {
            result = "Заполните пожалуйста все поля, в том числе роль";
            request.setAttribute("result", result);
            return FORWARD;
        }

        String fieldsValidation = validateFields(firstName, lastName, email, password);
        if(fieldsValidation.length() != 0) {
            request.setAttribute("result", fieldsValidation);
            return FORWARD;
        }

        Account newAcc = new Account(email, password, firstName, lastName, role);
        try{
            dao.insertAccount(newAcc);
        } catch (MyException ex) {
            result = "Пользователь с таким адрессом електронной почты уже зарегестрирован";
        }

        request.setAttribute("result", result);
        return FORWARD;
    }

    public boolean fieldsNotEmptyCheck(String fName, String lName, String email, String pass, Role role) {
        return (fName != null && lName != null && email != null && pass != null && role != Role.ERROR &&
                !fName.equals("") && !lName.equals("") && !email.equals("") && !pass.equals(""));
    }

    public String validateFields(String fName, String lName, String email, String pass) {
        StringBuilder result = new StringBuilder();

        result.append(validateName(fName));
        result.append(validateName(lName));
        result.append(validateEmail(email));
        result.append(validatePassword(pass));

        return result.toString();
    }

    public String validateName(String name) {
        String result = "";
        Pattern pattern = Pattern.compile("^[A-ZА-ЯЁЭЇ][a-zа-яёэї]{2,21}$");
        Matcher matcher = pattern.matcher(name);

        if(!matcher.find()) {
            result = "Имя и фамилия должны начинаться с большой буквы, и быть от 2 до 21 букв длинной."
                    + Constants.LN_SEP;
        }

        return result;
    }

    public String validateEmail(String email) {
        String result = "";
        Pattern pattern =
                Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]" +
                        "+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);

        if(!matcher.find()) {
            result = "Введите пожалуйста корректный имейл" + Constants.LN_SEP;;
        }

        return result;
    }

    public String validatePassword(String name) {
        String result = "";
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcher = pattern.matcher(name);

        if(!matcher.find()) {
            result = "Пароль должен содержать как минимум: одну заглавную букву," +
                    " одну маленькую букву, одну цифру, один спец символ." + Constants.LN_SEP +
                    " Пароль должен состоять как минимум из восьми символов и не содержать пробелов." + Constants.LN_SEP;
        }

        return result;
    }

}
