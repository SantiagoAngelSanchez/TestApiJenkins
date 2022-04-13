
import models.Datum;
import models.RegisterUserInfo;
import net.serenitybdd.junit.runners.SerenityRunner;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import org.junit.Test;
import org.junit.runner.RunWith;

import questions.GetUsersQuestion;
import questions.ResponseCode;
import tasks.GetUsers;
import tasks.RegisterUser;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.*;


@RunWith(SerenityRunner.class)
public class SerenityInitialTest {

    private static final String restApiUrl="http://localhost:5000/api";
    @Test
    public void getUsers(){
        Actor julian=Actor.named("julian the trainer").whoCan(CallAnApi.at(restApiUrl));

        julian.attemptsTo(GetUsers.fromPage(1));

        //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
        julian.should(seeThat("el codigo de respuesta", ResponseCode.was(),is(equalTo(200))));
        Datum user= new GetUsersQuestion().answeredBy(julian).getData().stream().filter(x->x.getId()==1).findFirst().orElse(null);

        julian.should(seeThat("usuario no es nulo",actor -> user,notNullValue()));
        julian.should(seeThat("el email del usuario",actor -> user.getEmail(),equalTo("george.bluth@reqres.in")));

    }

    @Test
    public void registerUsers(){
        Actor julian=Actor.named("julian the trainer").whoCan(CallAnApi.at(restApiUrl));
        String registerUserInfo="{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        julian.attemptsTo(RegisterUser.whithInfo(registerUserInfo));
        julian.should(seeThat("el codigo de respuesta", ResponseCode.was(),is(equalTo(200))));

    }


    @Test
    public void registerUsers2(){
        Actor julian=Actor.named("julian the trainer").whoCan(CallAnApi.at(restApiUrl));
        RegisterUserInfo registerUserInfo=new RegisterUserInfo();
        registerUserInfo.setName("morpheus");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("eve.holt@reqres.in");
        registerUserInfo.setPassword("pistol");

        julian.attemptsTo(RegisterUser.whithInfo(registerUserInfo));
        julian.should(seeThat("el codigo de respuesta", ResponseCode.was(),is(equalTo(200))));

    }


}
