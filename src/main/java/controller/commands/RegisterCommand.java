package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.Constants;
import dao.MyException;
import model.entities.Account;
import model.entities.Role;
import model.entities.builders.AccountBuilderImpl;
import model.service.AccountService;

public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        Properties pr = getProperties(request);

        String result = pr.getProperty("registerSuccess");
        AccountService service = new AccountService();

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Role role = Role.stringToEnum(request.getParameter("role"));

        if(!fieldsNotEmptyCheck(firstName, lastName, email, password, role)) {
            result = pr.getProperty("fillAllFields");
            request.setAttribute("result", result);
            return getNextPage(request);
        }

        String fieldsValidation = validateFields(firstName, lastName, email, password, pr);
        if(fieldsValidation.length() != 0) {
            request.setAttribute("result", fieldsValidation);
            return getNextPage(request);
        }

        Account newAcc = new AccountBuilderImpl()
                .setEmail(email)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(role)
                .build();


        try{
            service.createNewAccount(newAcc);
        } catch (MyException ex) {
            result = pr.getProperty("emailAlreadyExists");
        }

        request.setAttribute("result", result);
        return getNextPage(request);
    }

    public boolean fieldsNotEmptyCheck(String fName, String lName, String email, String pass, Role role) {
        return (fName != null && lName != null && email != null && pass != null && role != Role.ERROR &&
                !fName.equals("") && !lName.equals("") && !email.equals("") && !pass.equals(""));
    }

    public String validateFields(String fName, String lName, String email, String pass, Properties pr) {
        StringBuilder result = new StringBuilder();

        result.append(validateName(fName, pr));
        result.append(validateName(lName, pr));
        result.append(validateEmail(email, pr));
        result.append(validatePassword(pass, pr));

        return result.toString();
    }

    public String validateName(String name, Properties pr) {
        String result = "";
        Pattern pattern = Pattern.compile("^[A-ZА-ЯЁЭЇ][a-zа-яёэї]{2,21}$");
        Matcher matcher = pattern.matcher(name);

        if(!matcher.find()) {
            result = pr.getProperty("firstAndSecondNameDescription")
                    + Constants.LN_SEP;
        }

        return result;
    }

    public String validateEmail(String email, Properties pr) {
        String result = "";
        Pattern pattern =
                Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]" +
                        "+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);

        if(!matcher.find()) {
            result = pr.getProperty("correctEmail") + Constants.LN_SEP;;
        }

        return result;
    }

    public String validatePassword(String name, Properties pr) {
        String result = "";
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcher = pattern.matcher(name);

        if(!matcher.find()) {
            result = pr.getProperty("correctPassword");
        }

        return result;
    }

    private String getNextPage(HttpServletRequest req) {
        if(req.getSession().getAttribute("language").equals(Constants.UA)) {
            return Constants.REGISTER_RESULT;
        }
        return Constants.REGISTER_RESULT_EN;
    }

}
