package net.toujoustudios.kazunya.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(fluent = true)
public class Image {

    private final String type;
    private final String url;
    private final List<Character> genders;

}
