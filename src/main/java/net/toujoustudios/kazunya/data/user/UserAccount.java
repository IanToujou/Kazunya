package net.toujoustudios.kazunya.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAccount {

    private String name;
    private String discriminator;
    private String avatar;

    public String getFullName() {
        return name + "#" + discriminator;
    }

}
