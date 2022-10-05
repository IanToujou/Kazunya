package net.toujoustudios.kazunya.data.skill;

public enum SkillType {

    ACTING("Acting", 100, 1),
    ANIMAL_HANDLING("Animal Handling", 100, 1),
    BIOLOGY("Biology", 100, 1),
    CARPENTRY("Carpentry", 100, 1),
    CLEANING("Cleaning", 100, 1),
    COOKING("Cooking", 100, 1),
    DIPLOMACY("Diplomacy", 100, 1),
    DRAWING("Drawing", 100, 1),
    ECONOMICS("Economics", 100, 1),
    ELECTRONICS("Electronics", 100, 1),
    ENDURANCE("Endurance", 100, 1),
    FISHING("Fishing", 100, 1),
    GRAPHIC_DESIGN("Graphic Design", 100, 1),
    HACKING("Hacking", 100, 1),
    LOCK_PICKING("Lock Picking", 100, 1),
    MATHEMATICS("Mathematics", 100, 1),
    MUSIC("Music", 100, 1),
    PROGRAMMING("Programming", 100, 1),
    SINGING("Singing", 100, 1),
    SOCIALIZING("Socializing", 100, 1),
    STEALTH("Stealth", 100, 1),
    STRENGTH("Strength", 100, 1),
    VIDEO_EDITING("Video Editing", 100, 1),
    WRITING("Writing", 100, 1);

    private final String name;
    private final int maxLevel;
    private final int baseExperience;

    SkillType(String name, int maxLevel, int baseExperience) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.baseExperience = baseExperience;
    }

    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

}
