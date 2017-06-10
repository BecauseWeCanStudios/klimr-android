package ru.raaas.klimr.fragments.data;

import com.google.gson.*;
import java.lang.reflect.Type;
import ru.raaas.klimr.fragments.data.Person;
/**
 * Created by user on 31.05.17.
 */
public class PersonDeserializer implements JsonDeserializer<Person> {
    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Person per = new Person();
        per.setId(jsonObject.get("id").getAsInt());

/*        per.setFName((FacialHair) context.deserialize(jsonObject.get("facialHair"), FacialHair.class));

        JsonArray weapons = jsonObject.getAsJsonArray("weapons");
        for(JsonElement weapon : weapons) {
            if(weapon.isJsonPrimitive()) {
                dwarf.addWeapon(new Weapon(weapon.getAsString()));
            } else {
                dwarf.addWeapon((UniqueWeapon) context.deserialize(weapon, UniqueWeapon.class));
            }
        }*/

        return per;
    }
}
