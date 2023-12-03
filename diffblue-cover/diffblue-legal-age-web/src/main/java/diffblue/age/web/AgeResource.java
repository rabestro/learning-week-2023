package diffblue.age.web;

import diffblue.legalage.LegalAgePredicate;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AgeResource extends ServerResource {

    @Get("txt")
    public String legalAgeValidator() {
        var legalAgePredicate = new LegalAgePredicate();
        var birthdayString = getQueryValue("birthday");
        var response = new JSONObject();

        final LocalDate birthday;
        try {
            birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException e) {
            response.put("error", "Invalid birthday format. Use yyyy-mm-dd.");
            return response.toString();
        }

        var isLegalAge = legalAgePredicate.test(birthday);
        var message = isLegalAge ? "You are of legal age." : "You are under legal age.";
        response.put("message", "Happy Birthday! " + message);
        response.put("birthday", birthday.toString());
        response.put("isLegalAge", isLegalAge);
        return response.toString();
    }
}
