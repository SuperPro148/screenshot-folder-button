package io.github.superpro148.ssfb.config;

import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import dev.isxander.yacl.gui.controllers.cycling.EnumController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import io.github.superpro148.configlib148.ConfigList;
import io.github.superpro148.configlib148.ConfigOption;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SSFBConfig {

    public static ConfigList CONFIG_LIST = new ConfigList("screenshot-folder-button");

    public static ConfigOption<Boolean> SHOW_BUTTON = CONFIG_LIST.register(new ConfigOption<>("show_button", Boolean.class, true));
    public static ConfigOption<Position> BUTTON_POS = CONFIG_LIST.register(new ConfigOption<>("button_pos", Position.class, Position.LEFT_ONE));
    public static ConfigOption<Integer> POS_X = CONFIG_LIST.register(new ConfigOption<>("pos_x", Integer.class, 0));
    public static ConfigOption<Integer> POS_Y = CONFIG_LIST.register(new ConfigOption<>("pos_y", Integer.class, 0));

    public static Screen createGui(Screen parent) {
        var a = Option.createBuilder(Boolean.class)
                .name(Text.translatable("option.ssfb.show_button"))
                .tooltip(Text.translatable("tooltip.ssfb.show_button"))
                .binding(
                        true,
                        () -> SHOW_BUTTON.getValue(),
                        newValue -> SHOW_BUTTON.setValue(newValue)
                )
                .controller(TickBoxController::new)
                .build();
        var b = Option.createBuilder(Position.class)
                .name(Text.translatable("option.ssfb.button_pos"))
                .tooltip(Text.translatable("tooltip.ssfb.button_pos"))
                .binding(
                        Position.LEFT_ONE,
                        () -> BUTTON_POS.getValue(),
                        newValue -> BUTTON_POS.setValue(newValue)
                )
                .controller(option -> new EnumController<>(
                        option,
                        enumConst -> Text.translatable("enum.ssfb.position." + enumConst.toString().toLowerCase())
                ))
                .build();
        var c = Option.createBuilder(Integer.class)
                .name(Text.translatable("option.ssfb.pos_x"))
                .tooltip(Text.translatable("tooltip.ssfb.pos_x"))
                .binding(
                        0,
                        () -> POS_X.getValue(),
                        newValue -> POS_X.setValue(newValue)
                )
                .controller(option -> new IntegerSliderController(
                        option,
                        0,
                        1920,
                        1
                ))
                .build();
        var d = Option.createBuilder(Integer.class)
                .name(Text.translatable("option.ssfb.pos_y"))
                .tooltip(Text.translatable("tooltip.ssfb.pos_y"))
                .binding(
                        0,
                        () -> POS_Y.getValue(),
                        newValue -> POS_Y.setValue(newValue)
                )
                .controller(option -> new IntegerSliderController(
                        option,
                        0,
                        1080,
                        1
                ))
                .build();

        return YetAnotherConfigLib.createBuilder()
                .title(Text.of("Screenshot Folder Button"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("category.ssfb.button_options"))
                        .option(a)
                        .option(b)
                        .option(c)
                        .option(d)
                        .build())
                .save(SSFBConfig::save)
                .build()
                .generateScreen(parent);
    }

    private static void save() {
        CONFIG_LIST.saveConfig();
    }

    public enum Position {
        LEFT_ONE,
        LEFT_TWO,
        LEFT_THREE,
        LEFT_FOUR,
        LEFT_FIVE,
        RIGHT_ONE,
        RIGHT_TWO,
        RIGHT_THREE,
        RIGHT_FOUR,
        RIGHT_FIVE,
        CUSTOM
    }

}
