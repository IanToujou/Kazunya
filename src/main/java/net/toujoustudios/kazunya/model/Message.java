package net.toujoustudios.kazunya.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Message {

    private final String type;
    private final String message;

}
