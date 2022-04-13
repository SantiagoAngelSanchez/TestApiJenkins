package tasks;

import interactions.Post;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;


public class RegisterUser implements Task {
    private final Object userInfo;

    public RegisterUser(Object userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("/register").with(requestSpecification -> requestSpecification.body(userInfo).contentType(ContentType.JSON)));

    }

    public static RegisterUser whithInfo(Object userInfo){
        return Tasks.instrumented(RegisterUser.class,userInfo);
    }
}
