package net.toujoustudios.kazunya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.toujoustudios.kazunya.type.InteractionGender;
import net.toujoustudios.kazunya.type.InteractionType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleplayImage {

    private int id;
    private String url;
    private InteractionType type;
    private List<InteractionGender> genders;

}
