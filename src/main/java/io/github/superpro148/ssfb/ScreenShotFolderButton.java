package io.github.superpro148.ssfb;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import io.github.superpro148.ssfb.config.SSFBConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.io.File;
import java.util.List;

public class ScreenShotFolderButton implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SSFBConfig.CONFIG_LIST.readConfig();
        ScreenEvents.AFTER_INIT.register(ScreenShotFolderButton::afterScreenInit);
    }

    public static ButtonWidget createButton(int x, int y) {
        return new ButtonWidget(x, y, 20, 20, Text.of("ꕒ"), button -> openShotDir());
    }

    public static void openShotDir() {
        Util.getOperatingSystem().open(new File(FabricLoader.getInstance().getGameDir().toFile(), "screenshots"));
    }

    public static void afterScreenInit(MinecraftClient client, Screen screen, int sw, int sh) {
        if (screen instanceof GameMenuScreen) {
            List<ClickableWidget> buttons = Screens.getButtons(screen);
            var buttonPos = getScaledPos(SSFBConfig.BUTTON_POS.getValue(), sw, sh);
            buttons.add(createButton(buttonPos[0], buttonPos[1]));
        }
    }

    public static int[] getScaledPos(SSFBConfig.Position posEnum, int scaledWidth, int scaledHeight) {
        int x = 0;
        int y = 0;
        switch(posEnum) {
            case CUSTOM -> {
                x = SSFBConfig.POS_X.getValue();
                y = SSFBConfig.POS_Y.getValue();
            }
            case LEFT_ONE -> {
                x = scaledWidth / 2 - 126;
                y = scaledHeight / 4 + 8;
            }
            case LEFT_TWO -> {
                x = scaledWidth / 2 - 126;
                y = scaledHeight / 4 + 32;
            }
            case LEFT_THREE -> {
                x = scaledWidth / 2 - 126;
                y = scaledHeight / 4 + 56;
            }
            case LEFT_FOUR -> {
                x = scaledWidth / 2 - 126;
                y = scaledHeight / 4 + 80;
            }
            case LEFT_FIVE -> {
                x = scaledWidth / 2 - 126;
                y = scaledHeight / 4 + 104;
            }
            case RIGHT_ONE -> {
                x = scaledWidth / 2 + 106;
                y = scaledHeight / 4 + 8;
            }
            case RIGHT_TWO -> {
                x = scaledWidth / 2 + 106;
                y = scaledHeight / 4 + 32;
            }
            case RIGHT_THREE -> {
                x = scaledWidth / 2 + 106;
                y = scaledHeight / 4 + 56;
            }
            case RIGHT_FOUR -> {
                x = scaledWidth / 2 + 106;
                y = scaledHeight / 4 + 80;
            }
            case RIGHT_FIVE -> {
                x = scaledWidth / 2 + 106;
                y = scaledHeight / 4 + 104;
            }
        }
        return new int[]{x, y};
    }

}