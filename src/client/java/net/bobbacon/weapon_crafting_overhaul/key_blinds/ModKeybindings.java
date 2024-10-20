package net.bobbacon.weapon_crafting_overhaul.key_blinds;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;

public class ModKeybindings {

//    public static final KeyBinding talentTreeKey = KeyBindingHelper.registerKeyBinding(
//            new KeyBinding(createTranslationKey("talent_tree"), GLFW.GLFW_KEY_N, KeyBinding.MISC_CATEGORY));


    public static void init(){
//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            while (talentTreeKey.wasPressed()) {
//
//                IPlayerEntityMixin player= (IPlayerEntityMixin) client.player;
//                client.setScreen(new TalentTreeScreen(player.getTalents()));
//            }
//        });
    }
    public static String createTranslationKey(String name){
        return "key."+ WeaponCraftingOverhaul.MOD_ID+ "." + name;
    }
}
