package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetUsers implements Task {
    private final int page;

    public GetUsers(int page) {
        this.page = page;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("/users?page="+page).with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                .header("header1","1")
        ));

    }
    public static GetUsers fromPage(int page){
        return Tasks.instrumented(GetUsers.class,page);
    }

}
