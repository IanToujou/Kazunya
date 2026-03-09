package net.toujoustudios.kazunya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleplayInteraction {

    private int id;
    private String name;
    private List<RoleplayImage> images;
    private List<RoleplayMessage> messages;

}
