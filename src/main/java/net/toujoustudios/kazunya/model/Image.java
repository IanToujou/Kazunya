package net.toujoustudios.kazunya.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Data
@Accessors(fluent = true)
public class Image {

    @Nullable
    private final String type;
    @NotNull
    private final String url;
    @NotNull
    private final List<Character> genders;

}
