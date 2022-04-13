package questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Tasks;

public class ResponseCode implements Question {
    @Override
    public Object answeredBy(Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }

    public static ResponseCode was(){
        return new ResponseCode();
    }
}
