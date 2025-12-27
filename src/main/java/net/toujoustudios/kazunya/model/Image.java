package net.toujoustudios.kazunya.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Image {

    private final String type;
    private final String url;

}
