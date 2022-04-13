package questions;

import models.Users;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetUsersQuestion implements Question {
    @Override
    public Users answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Users.class);
    }

    //public static GetUsersQuestion page(){
      //  return new GetUsersQuestion();
    //}
}