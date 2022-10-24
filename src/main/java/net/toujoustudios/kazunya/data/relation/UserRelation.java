package net.toujoustudios.kazunya.data.relation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class UserRelation {

    private String id;
    private String target;
    private UserRelationType type;
    private Date date;

}
