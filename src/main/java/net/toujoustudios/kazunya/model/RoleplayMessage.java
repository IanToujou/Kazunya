package net.toujoustudios.kazunya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.toujoustudios.kazunya.type.InteractionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleplayMessage {

    private int id;
    private String text;
    private InteractionType type;

}
