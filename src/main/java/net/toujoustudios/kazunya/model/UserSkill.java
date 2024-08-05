package net.toujoustudios.kazunya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserSkill {

    private SkillType skillType;
    private int experience;

}
