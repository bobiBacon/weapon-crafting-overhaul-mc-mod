package net.bobbacon.races.key_blinds;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.gui.TalentTreeScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class ModKeybindings {

    public static final KeyBinding talentTreeKey = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(createTranslationKey("talent_tree"), GLFW.GLFW_KEY_N, KeyBinding.MISC_CATEGORY));


    public static void init(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (talentTreeKey.wasPressed()) {
                if (client.currentScreen instanceof TalentTreeScreen){
                    client.currentScreen.close();
                }
                client.setScreen(new TalentTreeScreen());
            }
        });
    }
    public static String createTranslationKey(String name){
        return "key."+ RacesModForMyServer.MOD_ID+ "." + name;
    }
}
