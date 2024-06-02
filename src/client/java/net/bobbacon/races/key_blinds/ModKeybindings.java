package net.bobbacon.races.key_blinds;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.gui.TalentTreeScreen;
import net.bobbacon.races.player.IPlayerEntityMixin;
import net.bobbacon.races.talent.CombatTalentTree;
import net.bobbacon.races.talent.CraftingTalentTree;
import net.bobbacon.races.talent.TalentTree;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class ModKeybindings {

    public static final KeyBinding talentTreeKey = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(createTranslationKey("talent_tree"), GLFW.GLFW_KEY_N, KeyBinding.MISC_CATEGORY));


    public static void init(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (talentTreeKey.wasPressed()) {

                IPlayerEntityMixin player= (IPlayerEntityMixin) client.player;
                client.setScreen(new TalentTreeScreen(player.getTalents()));
            }
        });
    }
    public static String createTranslationKey(String name){
        return "key."+ RacesModForMyServer.MOD_ID+ "." + name;
    }
}
