package net.toujoustudios.kazunya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserBan {

    private String reason;
    private Date until;
    private Date date;

}
